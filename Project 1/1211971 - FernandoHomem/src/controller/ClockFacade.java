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
	
	public int returnClockState(){
		if(clock.getCurrentState() == clock.getSelectedHourSetState()){
			return 0;
		}
		else if(clock.getCurrentState() == clock.getSelectedMinuteSetState()){
			return 1;
		}
		else if(clock.getCurrentState() == clock.getSelectedSecondSetState()){
			return 2;
		}
		else if(clock.getCurrentState() == clock.getExibitionSetState()){
			return 3;
		}
		else{
			return -1; // estado inválido
		}
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
	
	public void CounPressedHour(){
		clock.CountPressedHour();
	}
	
	public void CounPressedMinute(){
		clock.CountPressedMinute();
	}
	
	public void CounPressedSecond(){
		clock.CountPressedSecond();
	}
	
	public static void FacadeRegistraObserver(Subject EngineClock){
		AnalogClockGUI.getInstancce().addObserverAnalogClockGUI(EngineClock);
		DigitalClockGUI.getInstancce().addObserverDigitalClockGUI(EngineClock);
	}
}
