package controller;


import model.ClockState;
import model.EngineClock;
import model.Subject;
import view.AnalogClockGUI;
import view.DigitalClockGUI;

public class ClockFacade {
	//EngineClock
	private static EngineClock clock;
	
	//Singleton da Class -- ClockFacade
	private static ClockFacade ClockFacadeInstance = null;
	
	public static ClockFacade getInstance(){
		if(ClockFacadeInstance == null){

			ClockFacadeInstance = new ClockFacade();
		}

		return ClockFacadeInstance;
	}
	
	public ClockFacade(){
		clock = EngineClock.getInstance();
	}
	
	public ClockState getCurrentState(){
		return clock.getCurrentState();
	}
	
	public ClockState getExibitionState(){
		return clock.getExibitionSetState();
	}
	
	public ClockState getSelectedHourState(){
		return clock.getSelectedHourSetState();
	}
	
	public ClockState getSelectedMinuteState(){
		return clock.getSelectedMinuteSetState();
	}
	
	public ClockState getSelectedSecondState(){
		return clock.getSelectedSecondSetState();
	}
	
	public void HourIncrement(){
		clock.incrementHour();
	}
	
	public void MinuteIncrement(){
		clock.incrementMinute();
	}
	
	public void SecondIncrement(){
		clock.incrementSecond();
	}
	
	public void PressAjust(){
		clock.pressAjust();
	}
	
	public void PressIncrement(){
		clock.pressIncrement();
	}
	
	public void InterruptThread(){
		clock.interruptThread();
	}
	
	public void StartThread(){
		clock.startThread();
	}
	
	public void CounPressedHour(){
		clock.CountPressedHour();
	}
	
	public void CounPressedMinute(){
		clock.CountPressedMinute();
	}
	
	public void CounPressedSecond(){
		clock.CountPressedSecond();
	}
	public void SetNewStatusThread(boolean newStatusThread){
		clock.setStateThread(newStatusThread);
	}
	
	public static void FacadeRegistraObserver(Subject EngineClock){
		AnalogClockGUI.getInstancce().addObserverAnalogClockGUI(EngineClock);
		DigitalClockGUI.getInstancce().addObserverDigitalClockGUI(EngineClock);
	}
}
