package model;

public class SelectedMinute implements ClockState {
	EngineClock clock;
	
	public SelectedMinute(EngineClock newClock){
		clock = newClock;
	}

	@Override
	public void AButtonPressed() {
		System.out.println("Passando para o estado - Second Selecionado");
		clock.setClockState(clock.getSelectedSecondSetState());
		clock.buttonAjust = true;
	}

	@Override
	public void BButtonPressed() {
		System.out.println("Passando para o estado - Incrementar a Minuto");
		clock.incrementMinute();
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
