package model;

import java.awt.Color;
import java.util.ArrayList;

import view.Board;

public class ConjuntoDePinos implements client.Observer {
	
	//Static used as a counter
	private static int ObserverIDTracker = 0;
	//Used to track the observer
	private int ObserverID;
	
	
	PinoEstruturado[] GreenPino = new PinoEstruturado[4];
	PinoEstruturado[] RedPino = new PinoEstruturado[4];
	PinoEstruturado[] BluePino = new PinoEstruturado[4];
	PinoEstruturado[] YellowPino = new PinoEstruturado[4];

	int verde6 = 0;
	int vermelho6 = 0;
	int azul6 = 0;
	int amarelo6 = 0;


	private static ConjuntoDePinos cpfirstInstance = null;

	//Singleton da Class -- ConjuntoDePinos
	public static ConjuntoDePinos getInstancce(){
		if(cpfirstInstance == null){

			cpfirstInstance = new ConjuntoDePinos();
		}

		return cpfirstInstance;
	}

	private ConjuntoDePinos(){}

	public PinoEstruturado getGreen(int numero){
		return this.GreenPino[numero-1];
	}

	public PinoEstruturado getRed(int numero){
		return this.RedPino[numero-1];
	}

	public PinoEstruturado getBlue(int numero){
		return this.BluePino[numero-1];
	}

	public PinoEstruturado getYellow(int numero){
		return this.YellowPino[numero-1];
	}
	
	public void initializeConjuntoDePinos(){

		for(int i = 1; i < 5; i++){
			this.RedPino[i-1] = new PinoEstruturado();
			this.GreenPino[i-1] = new PinoEstruturado();
			this.BluePino[i-1] = new PinoEstruturado();
			this.YellowPino[i-1] = new PinoEstruturado();
		}

		for(int i = 1; i < 5; i++){
			if( i == 1 ){

				this.RedPino[i-1].setNumero(i);
				this.RedPino[i-1].setColor(Color.red);
				this.RedPino[i-1].setCasa(1);
				this.BluePino[i-1].setNumero(i);
				this.BluePino[i-1].setColor(Color.blue);
				this.BluePino[i-1].setCasa(1);
				this.YellowPino[i-1].setNumero(i);
				this.YellowPino[i-1].setColor(Color.yellow);
				this.YellowPino[i-1].setCasa(1);
				this.GreenPino[i-1].setNumero(i);
				this.GreenPino[i-1].setColor(Color.green);
				this.GreenPino[i-1].setCasa(1);

			}

			else{
				this.RedPino[i-1].setNumero(i);
				this.RedPino[i-1].setColor(Color.red);
				this.RedPino[i-1].setCasa(0);
				this.BluePino[i-1].setNumero(i);
				this.BluePino[i-1].setColor(Color.blue);
				this.BluePino[i-1].setCasa(0);
				this.YellowPino[i-1].setNumero(i);
				this.YellowPino[i-1].setColor(Color.yellow);
				this.YellowPino[i-1].setCasa(0);
				this.GreenPino[i-1].setNumero(i);
				this.GreenPino[i-1].setColor(Color.green);
				this.GreenPino[i-1].setCasa(0);
			}
		}
	}

	public void LoadGameConjuntoDePinos(ArrayList<Integer> ListOfAllLoadInfo){
		
		for(int i = 1; i < 5; i++){
			this.RedPino[i-1] = new PinoEstruturado();
			this.GreenPino[i-1] = new PinoEstruturado();
			this.BluePino[i-1] = new PinoEstruturado();
			this.YellowPino[i-1] = new PinoEstruturado();
		}

		this.RedPino[0].setNumero(ListOfAllLoadInfo.get(0));
		this.RedPino[0].setColor(Color.red);
		this.RedPino[0].setCasa(ListOfAllLoadInfo.get(1));
		this.RedPino[1].setNumero(ListOfAllLoadInfo.get(2));
		this.RedPino[1].setColor(Color.red);
		this.RedPino[1].setCasa(ListOfAllLoadInfo.get(3));
		this.RedPino[2].setNumero(ListOfAllLoadInfo.get(4));
		this.RedPino[2].setColor(Color.red);
		this.RedPino[2].setCasa(ListOfAllLoadInfo.get(5));
		this.RedPino[3].setNumero(ListOfAllLoadInfo.get(6));
		this.RedPino[3].setColor(Color.red);
		this.RedPino[3].setCasa(ListOfAllLoadInfo.get(7));

		this.BluePino[0].setNumero(ListOfAllLoadInfo.get(8));
		this.BluePino[0].setColor(Color.blue);
		this.BluePino[0].setCasa(ListOfAllLoadInfo.get(9));
		this.BluePino[1].setNumero(ListOfAllLoadInfo.get(10));
		this.BluePino[1].setColor(Color.blue);
		this.BluePino[1].setCasa(ListOfAllLoadInfo.get(11));
		this.BluePino[2].setNumero(ListOfAllLoadInfo.get(12));
		this.BluePino[2].setColor(Color.blue);
		this.BluePino[2].setCasa(ListOfAllLoadInfo.get(13));
		this.BluePino[3].setNumero(ListOfAllLoadInfo.get(14));
		this.BluePino[3].setColor(Color.blue);
		this.BluePino[3].setCasa(ListOfAllLoadInfo.get(15));

		this.YellowPino[0].setNumero(ListOfAllLoadInfo.get(16));
		this.YellowPino[0].setColor(Color.yellow);
		this.YellowPino[0].setCasa(ListOfAllLoadInfo.get(17));
		this.YellowPino[1].setNumero(ListOfAllLoadInfo.get(18));
		this.YellowPino[1].setColor(Color.yellow);
		this.YellowPino[1].setCasa(ListOfAllLoadInfo.get(19));
		this.YellowPino[2].setNumero(ListOfAllLoadInfo.get(20));
		this.YellowPino[2].setColor(Color.yellow);
		this.YellowPino[2].setCasa(ListOfAllLoadInfo.get(21));
		this.YellowPino[3].setNumero(ListOfAllLoadInfo.get(22));
		this.YellowPino[3].setColor(Color.yellow);
		this.YellowPino[3].setCasa(ListOfAllLoadInfo.get(23));

		this.GreenPino[0].setNumero(ListOfAllLoadInfo.get(24));
		this.GreenPino[0].setColor(Color.green);
		this.GreenPino[0].setCasa(ListOfAllLoadInfo.get(25));
		this.GreenPino[1].setNumero(ListOfAllLoadInfo.get(26));
		this.GreenPino[1].setColor(Color.green);
		this.GreenPino[1].setCasa(ListOfAllLoadInfo.get(27));
		this.GreenPino[2].setNumero(ListOfAllLoadInfo.get(28));
		this.GreenPino[2].setColor(Color.green);
		this.GreenPino[2].setCasa(ListOfAllLoadInfo.get(29));
		this.GreenPino[3].setNumero(ListOfAllLoadInfo.get(30));
		this.GreenPino[3].setColor(Color.green);
		this.GreenPino[3].setCasa(ListOfAllLoadInfo.get(31));
		/*
		for(int i = 1; i < 5; i++){
			
			System.out.println("RED " + i + ": " + "CASA: " + RedPino[i-1].casa + " NUMERO: " + RedPino[i-1].numero);
			System.out.println("GREEN " + i +  ": " + "CASA: " + GreenPino[i-1].casa + " NUMERO: " + GreenPino[i-1].numero);
			System.out.println("BLUE " + i +  ": " + "CASA: " + BluePino[i-1].casa + " NUMERO: " + BluePino[i-1].numero);
			System.out.println("YELLOW " + i +  ": " + "CASA: " + GreenPino[i-1].casa + " NUMERO: " + GreenPino[i-1].numero);
		}*/
	}
	
	/****************** Start: client.Observer ******************/
	@Override
	public void updateClient(String NovaJogada) {
		
		//Break lines into pieces
		String[] quebraNovaJogada = NovaJogada.split("#");
		String[] round = quebraNovaJogada[0].split(" ");
		
		Board.round = Integer.parseInt(round[3]);
		
		System.out.println("ROUND: " + Board.round);
		
		String [] RedPinos = quebraNovaJogada[1].split(" ");
		
		this.RedPino[0].setNumero(Integer.parseInt(RedPinos[1]));
		this.RedPino[0].setColor(Color.red);
		this.RedPino[0].setCasa(Integer.parseInt(RedPinos[2]));
		this.RedPino[1].setNumero(Integer.parseInt(RedPinos[3]));
		this.RedPino[1].setColor(Color.red);
		this.RedPino[1].setCasa(Integer.parseInt(RedPinos[4]));
		this.RedPino[2].setNumero(Integer.parseInt(RedPinos[5]));
		this.RedPino[2].setColor(Color.red);
		this.RedPino[2].setCasa(Integer.parseInt(RedPinos[6]));
		this.RedPino[3].setNumero(Integer.parseInt(RedPinos[7]));
		this.RedPino[3].setColor(Color.red);
		this.RedPino[3].setCasa(Integer.parseInt(RedPinos[8]));
		
		String [] BluePinos = quebraNovaJogada[2].split(" ");
		
		this.BluePino[0].setNumero(Integer.parseInt(BluePinos[1]));
		this.BluePino[0].setColor(Color.blue);
		this.BluePino[0].setCasa(Integer.parseInt(BluePinos[2]));
		this.BluePino[1].setNumero(Integer.parseInt(BluePinos[3]));
		this.BluePino[1].setColor(Color.blue);
		this.BluePino[1].setCasa(Integer.parseInt(BluePinos[4]));
		this.BluePino[2].setNumero(Integer.parseInt(BluePinos[5]));
		this.BluePino[2].setColor(Color.blue);
		this.BluePino[2].setCasa(Integer.parseInt(BluePinos[6]));
		this.BluePino[3].setNumero(Integer.parseInt(BluePinos[7]));
		this.BluePino[3].setColor(Color.blue);
		this.BluePino[3].setCasa(Integer.parseInt(BluePinos[8]));
		
		String [] YellowPinos = quebraNovaJogada[3].split(" ");
		
		this.YellowPino[0].setNumero(Integer.parseInt(YellowPinos[1]));
		this.YellowPino[0].setColor(Color.blue);
		this.YellowPino[0].setCasa(Integer.parseInt(YellowPinos[2]));
		this.YellowPino[1].setNumero(Integer.parseInt(YellowPinos[3]));
		this.YellowPino[1].setColor(Color.blue);
		this.YellowPino[1].setCasa(Integer.parseInt(YellowPinos[4]));
		this.YellowPino[2].setNumero(Integer.parseInt(YellowPinos[5]));
		this.YellowPino[2].setColor(Color.blue);
		this.YellowPino[2].setCasa(Integer.parseInt(YellowPinos[6]));
		this.YellowPino[3].setNumero(Integer.parseInt(YellowPinos[7]));
		this.YellowPino[3].setColor(Color.blue);
		this.YellowPino[3].setCasa(Integer.parseInt(YellowPinos[8]));
		
		String [] GreenPinos = quebraNovaJogada[4].split(" ");
		
		this.GreenPino[0].setNumero(Integer.parseInt(GreenPinos[1]));
		this.GreenPino[0].setColor(Color.blue);
		this.GreenPino[0].setCasa(Integer.parseInt(GreenPinos[2]));
		this.GreenPino[1].setNumero(Integer.parseInt(GreenPinos[3]));
		this.GreenPino[1].setColor(Color.blue);
		this.GreenPino[1].setCasa(Integer.parseInt(GreenPinos[4]));
		this.GreenPino[2].setNumero(Integer.parseInt(GreenPinos[5]));
		this.GreenPino[2].setColor(Color.blue);
		this.GreenPino[2].setCasa(Integer.parseInt(GreenPinos[6]));
		this.GreenPino[3].setNumero(Integer.parseInt(GreenPinos[7]));
		this.GreenPino[3].setColor(Color.blue);
		this.GreenPino[3].setCasa(Integer.parseInt(GreenPinos[8]));
		for(int i = 0 ; i < 9; i++){
			System.out.println(RedPinos[i]);
		}
		for(int i = 0 ; i < 9; i++){
			System.out.println(BluePinos[i]);
		}
		for(int i = 0 ; i < 9; i++){
			System.out.println(YellowPinos[i]);
		}
		for(int i = 0 ; i < 9; i++){
			System.out.println(GreenPinos[i]);
		}
		
	}
	
	public void addPinoEstruturadotoClient(client.Subject Client){
		
		//Incrementando o contado statico
		this.ObserverID = ++ObserverIDTracker;

		//Mensagem que notifica o usuario de um novo observador
		System.out.println("Client: New Observer" + this.ObserverID);
		
		Client.register(this);
		
	}
	
	/****************** END: model.Observer ******************/

}
