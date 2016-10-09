package model;

public class Exibition implements ClockState {
	EngineClock clock;
	
	public Exibition(EngineClock newClock){
		clock = newClock;
	}

	@Override
	public void AButtonPressed() {
		System.out.println("Passando para o estado - Hora Selecionada");
		clock.setClockState(clock.getSelectedHourSetState());
		
	}

	@Override
	public void BButtonPressed() {
		// TODO Auto-generated method stub
		
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
