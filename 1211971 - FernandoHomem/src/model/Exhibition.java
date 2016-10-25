package model;

public class Exhibition implements ClockState {
EngineClock clock;
	
	public Exhibition(EngineClock newClock){
		clock = newClock;
	}

	@Override
	public void AButtonPressed() {
		System.out.println("Passando para o estado - Hora Selecionada");
		clock.setClockState(clock.getSelectedHourSetState());
		clock.buttonAjust = true;
		
	}

	@Override
	public void BButtonPressed() {
		System.out.println("Você não pode incrementar nesse estado.");
		clock.buttonIncrement = false;
		
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
