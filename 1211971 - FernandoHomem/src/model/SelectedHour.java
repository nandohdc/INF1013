package model;

public class SelectedHour implements ClockState {
	EngineClock clock;
	
	public SelectedHour(EngineClock newClock){
		clock = newClock;
	}

	@Override
	public void AButtonPressed() {
		System.out.println("Passando para o estado - Minuto Selecionado");
		clock.setClockState(clock.getSelectedMinuteSetState());
		clock.buttonAjust = true;
	}

	@Override
	public void BButtonPressed() {
		System.out.println("Passando para o estado - Incrementar a Hora");
		clock.incrementHour();
		clock.buttonIncrement = true;
	}

	@Override
	public void AButtonReleased() {
		// TODO Auto-generated method stub
		clock.buttonAjust = false;
	}

	@Override
	public void BButtonReleased() {
		// TODO Auto-generated method stub
		clock.buttonIncrement = false;
	}

}
