package model;

import java.util.ArrayList;

public class EngineClock implements Subject, Runnable {

	// Design Pattern State - ClockState
	private static ClockState clockCurrentState;
	private ClockState exibitionState;
	private ClockState selectedHourState;
	private ClockState selectedMinuteState;
	private ClockState selectedSecondState;

	// Creates an ArrayList to hold all observers
	private static ArrayList<model.Observer> observers = new ArrayList<model.Observer>();

	// Time
	private static long milliseconds;
	private static int Hour = 0;
	private static int Minute = 0;
	private static int Second = 0;
	private static int pressedHour;
	private static int pressedMinute;
	private static int pressedSecond;

	// Thread
	private static Thread thread = null;
	private static boolean isRunning = false;
	private static boolean statusThread = false;

	// Buttons Ajust and Increment
	boolean buttonAjust;
	boolean buttonIncrement;

	// Singleton da Class -- EngineClock
	private static EngineClock EngineClockInstance = null;

	public static EngineClock getInstance() {
		if (EngineClockInstance == null) {

			EngineClockInstance = new EngineClock();
		}

		return EngineClockInstance;
	}

	public EngineClock() {

		// Initializing - Design Pattern State
		exibitionState = new Exibition(this);
		selectedHourState = new SelectedHour(this);
		selectedMinuteState = new SelectedMinute(this);
		selectedSecondState = new SelectedSecond(this);
		clockCurrentState = exibitionState;

		// Initializing class variables
		buttonAjust = false;
		buttonIncrement = false;

		// Starting thread
		statusThread = true;
		this.startThread();
	}

	private void StartClock(long milliseconds) {
		int localHour, localMinute, localSecond;

		// System.out.printf("%d\n",milliseconds );

		localHour = (int) (milliseconds / (1000 * 60 * 60)) % 24;
		localMinute = (int) (milliseconds / (1000 * 60)) % 60;
		localSecond = (int) (milliseconds / (1000)) % 60;

		// System.out.printf("%d\n",localHour );
		// System.out.printf("%d\n",localMinute );
		// System.out.printf("%d\n",localSecond );
		System.out.printf("LocalMinute: %d - LocalMinute+PressedMinute: %d\n", localMinute,
				localMinute + pressedMinute);
		updateTime(localHour + pressedHour, localMinute + pressedMinute, localSecond + pressedSecond);
	}

	private void updateTime(int newHour, int newMinute, int newSecond) {

		setHour(newHour);
		setMinute(newMinute);
		setSecond(newSecond);

	}

	public int getHour() {
		return Hour;
	}

	public int getMinute() {
		return Minute;
	}

	public int getSecond() {
		return Second;
	}

	public void setHour(int newHour) {
		Hour = newHour;
		notifyObserver();
	}

	public void setMinute(int newMinute) {
		Minute = newMinute;
		notifyObserver();
	}

	public void setSecond(int newSecond) {
		Second = newSecond;
		notifyObserver();
	}

	public void incrementHour() {
		Hour++;
		setHour(Hour);
	}

	public void incrementMinute() {
		Minute++;
		setMinute(Minute);
	}

	public void incrementSecond() {
		Second++;
		setSecond(Second);
	}

	public void CountPressedHour() {
		pressedHour++;
	}

	public void CountPressedMinute() {
		pressedMinute++;
	}

	public void CountPressedSecond() {
		pressedSecond++;
	}

	/******************** Start: Engine Clock - State ********************/
	public void pressAjust() {
		clockCurrentState.AButtonPressed();
	}

	public void pressIncrement() {
		clockCurrentState.BButtonPressed();
	}

	public void releaseA() {
		clockCurrentState.AButtonReleased();
	}

	public void releaseB() {
		clockCurrentState.BButtonReleased();
	}

	public void setClockState(ClockState newState) {
		clockCurrentState = newState;
	}

	public ClockState getExibitionSetState() {
		return exibitionState;
	}

	public ClockState getSelectedHourSetState() {
		return selectedHourState;
	}

	public ClockState getSelectedMinuteSetState() {
		return selectedMinuteState;
	}

	public ClockState getSelectedSecondSetState() {
		return selectedSecondState;
	}

	public ClockState getCurrentState() {
		return clockCurrentState;
	}

	/******************** End: Engine Clock - Observer ********************/

	/******************** Start: Engine Clock - Observer ********************/
	@Override
	public void register(model.Observer newObserver) {
		// Adiciona um novo Observer a lista de Observers

		System.out.println("Observador Registrado!");

		observers.add(newObserver);

	}

	@Override
	public void unregister(model.Observer deleteObserver) {
		int ObserverIndex = observers.indexOf(deleteObserver);

		// Print out message
		System.out.println("Observer " + (ObserverIndex + 1) + "deleted");

		// Removes observer from the array list

		observers.remove(ObserverIndex);

	}

	@Override
	public void notifyObserver() {
		// Cycle through all observers and notifies them of changes

		for (int i = 0; i < observers.size(); i++) {
			Observer observer = (Observer) observers.get(i);
			observer.update(Hour, Minute, Second);
		}

	}

	/******************** End: Engine Clock - Observer ********************/

	/******************** Start: Engine Clock - Thread ********************/
	public void startThread() {
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		isRunning = true;
		while (isRunning) {
			if (statusThread) {
				milliseconds = System.currentTimeMillis();
				EngineClock.getInstance().StartClock(milliseconds);
			}

			updateTime(getHour(), getMinute(), getSecond());

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		thread = null;
	}

	public void interruptThread() {
		thread.interrupt();
	}

	public void setStateThread(boolean newStatusThread) {
		statusThread = newStatusThread;
	}

	/******************** End: Engine Clock - ********************/
}
