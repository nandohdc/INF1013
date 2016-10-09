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
		
	}

	@Override
	public void BButtonPressed() {
		System.out.println("Passando para o estado - Incrementar a Minuto");
		clock.incrementMinute();
		
	}

	@Override
	public void AButtonReleased() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void BButtonReleased() {
		// TODO Auto-generated method stub
		
	}

}
