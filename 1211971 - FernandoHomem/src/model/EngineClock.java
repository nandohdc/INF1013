package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class EngineClock implements Subject, ActionListener {

	// Design Pattern State - ClockState
	private static ClockState clockCurrentState;
	private ClockState exibitionState;
	private ClockState selectedHourState;
	private ClockState selectedMinuteState;
	private ClockState selectedSecondState;

	// Creates an ArrayList to hold all observers
	private static ArrayList<model.Observer> observers = new ArrayList<model.Observer>();

	// Timer
	private Timer timer;
	private final int DELAY = 1000;

	// Time
	private static long milliseconds;
	private static int Hour = 0;
	private static int Minute = 0;
	private static int Second = 0;
	private static int pressedHour;
	private static int pressedMinute;
	private static int pressedSecond;

	// Buttons Ajust and Increment
	boolean buttonAjust = false;
	boolean buttonIncrement = false;

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

		// Starting Timer
		timer = new Timer(DELAY, this);
		timer.start();
	}

	private void StartClock(long milliseconds) {
		int localHour, localMinute, localSecond;

		localHour = (int) (milliseconds / (1000 * 60 * 60)) % 24 - 3;
		localMinute = (int) (milliseconds / (1000 * 60)) % 60;
		localSecond = (int) (milliseconds / (1000)) % 60;

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

	/********************* Start: Engine Clock - actionPerformed ********************/

	@Override
	public void actionPerformed(ActionEvent e) {

		milliseconds = System.currentTimeMillis();
		EngineClock.getInstance().StartClock(milliseconds);

	}

	/********************* End: Engine Clock - actionPerformed ********************/
}
