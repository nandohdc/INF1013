package model;

public interface ClockState {
	//Diferentes Estados são esperados
	
	//Botao A pressionado
	void AButtonPressed();
	
	//Botao B pressionado
	void BButtonPressed();
	
	//Botao A liberado
	void AButtonReleased();
	
	//Botao B liberado
	void BButtonReleased();

}
