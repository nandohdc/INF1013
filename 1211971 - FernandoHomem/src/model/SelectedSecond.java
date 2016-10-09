package model;

public class SelectedSecond implements ClockState {
	EngineClock clock;
	
	public SelectedSecond(EngineClock newClock){
		clock = newClock;
	}

	@Override
	public void AButtonPressed() {
		System.out.println("Passando para o estado - Exibicao Selecionado");
		clock.setClockState(clock.getExibitionSetState());
		
	}

	@Override
	public void BButtonPressed() {
		System.out.println("Passando para o estado - Incrementar a Minuto");
		clock.incrementSecond();
		
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
