package view;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.*;

import client.Client;
import controller.FacadeMovimento;
import model.ConjuntoDePinos;
import model.Path;
import model.PinoEstruturado;
import model.Score;

@SuppressWarnings("serial")
public class Board extends JFrame {
	//Variaveis de Classe
	JButton RollDice;
	JButton Save;
	MouseHandler mHandler;
	ActionHandler aHandler;
	JLabel lImageIconDado;
	public static int round;
	JFileChooser explorer = new JFileChooser();

	private static Board boardfirstInstance = null;

	// Singleton da Class -- ConjuntoDePinos
	public static Board getInstancce() {
		if (boardfirstInstance == null) {

			boardfirstInstance = new Board();
		}

		return boardfirstInstance;
	}

	// Construtor Default é usado quando iniciamos um jogo novo
	private Board() {}

	public void initializeBoard() {
		// Instanciando o Objeto que vai ser Observado
		PinoEstruturado pPino = new PinoEstruturado();

		// Criando Objeto do tipo DrawingBoard-- Singleton
		DrawingBoard.getInstancce();

		// Criando Objeto do tipo FacadeMovimento -- Singleton
		FacadeMovimento.getInstance();

		FacadeMovimento.FacadeRegistraObserver(pPino);

		// Criando objeto do tipo ConjuntoDePinos -- Singleton
		ConjuntoDePinos.getInstancce().initializeConjuntoDePinos();

		// Criando objeto do tipo Dice -- Singleton
		Dice.getInstance();

		// Criando objeto do tipo Client -- Singleton
		Client.getInstancce().initializeClient("10.0.0.16", 5501);

		FacadeMovimento.FacadeRegistraObserverClient(Client.getInstancce());

		// Criando objeto do tipo Path -- Singleton
		Path.getInstance();

		// Criando Objeto do Tipo Score -- Singleton
		Score.getInstancce();

		mHandler = new MouseHandler();
		aHandler = new ActionHandler();
		round = 0;

		// Definindo configuracoes defaults para o JFrame
		this.setSize(768, 640);
		this.setTitle("LUDO & FRIENDS");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Centralizando a Janela com Tela do PC
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int xPos = (dim.width / 2) - (this.getWidth() / 2);
		int yPos = (dim.height / 2) - (this.getHeight() / 2);

		// Criando Box para dividir o Frame em partes: Tabuleiro e Dado.
		Box thebox = Box.createVerticalBox();

		// Criando um Label para adicionar a img e chamando a funcao que cria as
		// imagens do dado
		lImageIconDado = new JLabel();

		// Atribuindo a um container a referencia da img
		Container cDice = lImageIconDado;

		// Adicionando a image ao box
		thebox.add(cDice);

		// Criando o botao(RollDice)
		RollDice = new JButton("Roll Dice");

		// Criando o botao (Save)
		Save = new JButton("Save");

		// Adicionando ActionListner ao botao RollDice
		RollDice.addActionListener(aHandler);

		// Adicionando ActionListner ao botao Save
		Save.addActionListener(aHandler);

		// Adicionando o botao RollDice ao box
		thebox.add(RollDice);

		// Adicionando o box do Save ao box
		thebox.add(Save);

		// Adicionando o box do dado para o panel
		this.add(thebox, BorderLayout.EAST);

		// Fazendo com que a area desenhada tome conta da parte central do frame
		this.add(DrawingBoard.getInstancce(), BorderLayout.CENTER);

		// Setting a posicao de centralizacao
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		// Mostrando a frame
		this.setVisible(true);
	}

	public void LoadGameBoard(ArrayList<Integer> ListOfAllLoadInfo) {
		round = ListOfAllLoadInfo.get(0);
		ListOfAllLoadInfo.remove(0);

		// Instanciando o Objeto que vai ser Observado
		PinoEstruturado pPino = new PinoEstruturado();
		
		// Criando objeto do tipo ConjuntoDePinos -- Singleton
		ConjuntoDePinos.getInstancce().LoadGameConjuntoDePinos(ListOfAllLoadInfo);

		// Criando Objeto do tipo DrawingBoard-- Singleton
		DrawingBoard.getInstancce();

		// Criando Objeto do tipo FacadeMovimento -- Singleton
		FacadeMovimento.getInstance();

		// Criando objeto do tipo Dice -- Singleton
		Dice.getInstance();

		// Criando objeto do tipo Client -- Singleton
		Client.getInstancce().initializeClient("11.0.0.16", 5501);

		// Criando Objeto do tipo FacadeMovimento -- Singleton
		FacadeMovimento.FacadeRegistraObserver(pPino);
		FacadeMovimento.FacadeRegistraObserverClient(Client.getInstancce());

		// Criando objeto do tipo Path -- Singleton
		Path.getInstance();

		// Criando Objeto do Tipo Score -- Singleton
		Score.getInstancce();

		mHandler = new MouseHandler();
		aHandler = new ActionHandler();

		// Definindo configuracoes defaults para o JFrame
		this.setSize(768, 640);
		this.setTitle("LUDO & FRIENDS");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Centralizando a Janela com Tela do PC
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int xPos = (dim.width / 2) - (this.getWidth() / 2);
		int yPos = (dim.height / 2) - (this.getHeight() / 2);

		// Criando Box para dividir o Frame em partes: Tabuleiro e Dado.
		Box thebox = Box.createVerticalBox();

		// Criando um Label para adicionar a img e chamando a funcao que cria as
		// imagens do dado
		lImageIconDado = new JLabel();

		// Atribuindo a um container a referencia da img
		Container cDice = lImageIconDado;

		// Adicionando a image ao box
		thebox.add(cDice);

		// Criando o botao(RollDice)
		RollDice = new JButton("Roll Dice");

		// Criando o botao (Save)
		Save = new JButton("Save");

		// Adicionando ActionListner ao botao RollDice
		RollDice.addActionListener(aHandler);

		// Adicionando ActionListner ao botao Save
		Save.addActionListener(aHandler);

		// Adicionando o botao RollDice ao box
		thebox.add(RollDice);

		// Adicionando o button do Save ao box
		thebox.add(Save);

		// Adicionando o box do dado para o panel
		this.add(thebox, BorderLayout.EAST);

		// Fazendo com que a area desenhada tome conta da parte central do frame
		this.add(DrawingBoard.getInstancce(), BorderLayout.CENTER);

		// Setting a posicao de centralizacao
		this.setLocation(xPos, yPos);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		// Mostrando a frame
		this.setVisible(true);
	}

	private class ActionHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == RollDice) {
				// Cria uma referencia para guardar a ImageIcon do Dado que será
				// retorna pela MakingImageDice
				ImageIcon DadoImageIcon = null;

				if (round == 4) {
					round = 0;
				}

				switch (round) {
				case 0:
					// Set background color of Button RollDice as red --
					// indicating that is Red Round
					RollDice.setBackground(Color.red);
					break;

				case 1:
					// Set background color of Button RollDice as blue --
					// indicating that is blue Round
					RollDice.setBackground(Color.blue);
					break;

				case 2:
					// Set background color of Button RollDice as yellow --
					// indicating that is yellow Round
					RollDice.setBackground(Color.yellow);
					break;

				case 3:
					// Set background color of Button RollDice as green --
					// indicating that is green Round
					RollDice.setBackground(Color.green);
					break;
				}

				RollDice.setOpaque(true);

				RollDice.setEnabled(false);

				// Manda o dado gerar um número aleatório de novo
				FacadeMovimento.getInstance().RollDice();

				// Chama o MouseListener para tratar o evento click
				DrawingBoard.getInstancce().addMouseListener(mHandler);

				// Montando a Imagem com o numero aletatorio gerado passado com
				// parametro
				DadoImageIcon = Dice.getInstance().MakingImageDice(Dice.getInstance().getRandNum());

				// Adicionando a imagem ao label
				lImageIconDado.setIcon(DadoImageIcon);

			}

			else if (e.getSource() == Save) {

				// PrintWriter is used to write characters to a file in this
				// situation
				PrintWriter SaveOutput = createFile("src/SaveFileTxT/LudoSavedGame.txt");

				// Calling method that will save all game information
				SavingGame(SaveOutput);

				// Closes the connection to the PrintWriter
				SaveOutput.close();
			}

		}

	}

	private class MouseHandler implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			int cX = e.getX();
			int cY = e.getY();

			if (FacadeMovimento.getInstance().setClickedCoordinates(cX, cY) == true) {
				if (round == 4) {
					round = 0;
				}

				switch (round) {
				case 0:
					RollDice.setBackground(Color.red);
					break;

				case 1:
					RollDice.setBackground(Color.blue);
					break;

				case 2:
					RollDice.setBackground(Color.yellow);
					break;

				case 3:
					RollDice.setBackground(Color.green);
					break;
				}

				RollDice.setOpaque(true);

				RollDice.setEnabled(true);
				if (Dice.getInstance().getRandNum() == 20 || Dice.getInstance().getRandNum() == 10) {
					RollDice.setEnabled(false);
				}

			}

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// RollDice.setText(String.format("Pressed at %d %d", e.getX(),
			// e.getY()));
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// RollDice.setText(String.format("U RELEASED THE BUTTON", e.getX(),
			// e.getY()));
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// RollDice.setText(String.format("SELECIONE THE FUCKING PINO",
			// e.getX(), e.getY()));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// RollDice.setText(String.format("BOA SORTE!", e.getX(),
			// e.getY()));
		}

	}

	// Create the file and the PrintWriter that will write to the file
	private PrintWriter createFile(String fileName) {

		try {// try n catch -- any error for trying to read from the file

			// Creates a File Object that allows you to work with files on the
			// hard drive

			File SaveGame = new File(fileName);

			// FileWriter is used to to write streams of characters to a file
			// BufferedWriter gathers a bunch of characters and then writes
			// them all at one time
			// PrintWriter is used to write characters to the console file

			PrintWriter infoToWrite = new PrintWriter(new BufferedWriter(new FileWriter(SaveGame)));

			return infoToWrite;

		}
		// catch this when you call FileWriter

		catch (IOException e) {
			System.out.println("An I/O Error Occurred");

			// Closes the program
			System.exit(0);
		}

		return null;
	}

	// Create a string with the ConjuntodePinos + nRound and write it to the
	// file
	private void SavingGame(PrintWriter SaveOutput) {

		// Create the string that contains the Round info
		String RoundInfo = "Round:" + " ";
		RoundInfo += Integer.toString(round);

		// Writes the string on the file
		SaveOutput.println(RoundInfo);

		// Create the string that contains the ConjuntodePinos info
		String PinsInfo = "Red:" + " ";
		for (int i = 1; i < 5; i++) {
			// Gets the number of pin and transform to a string
			PinsInfo += Integer.toString(ConjuntoDePinos.getInstancce().getRed(i).getNumero()) + " ";

			if (i < 4) {
				// Gets the house of pin and transform to a string
				PinsInfo += Integer.toString(ConjuntoDePinos.getInstancce().getRed(i).getCasa()) + " ";
			} else {
				// Gets the house of pin and transform to a string
				PinsInfo += Integer.toString(ConjuntoDePinos.getInstancce().getRed(i).getCasa());
			}
		}

		// Writes the string on the file
		SaveOutput.println(PinsInfo);

		PinsInfo = "Blue:" + " ";
		for (int i = 1; i < 5; i++) {
			// Gets the numeber of pin and transform to a string
			PinsInfo += Integer.toString(ConjuntoDePinos.getInstancce().getBlue(i).getNumero()) + " ";

			if (i < 4) {
				// Gets the house of pin and transform to a string
				PinsInfo += Integer.toString(ConjuntoDePinos.getInstancce().getBlue(i).getCasa()) + " ";
			}

			else {
				PinsInfo += Integer.toString(ConjuntoDePinos.getInstancce().getBlue(i).getCasa());
			}
		}

		// Writes the string on the file
		SaveOutput.println(PinsInfo);

		PinsInfo = "Yellow:" + " ";
		for (int i = 1; i < 5; i++) {
			// Gets the number of pin and transform to a string
			PinsInfo += Integer.toString(ConjuntoDePinos.getInstancce().getYellow(i).getNumero()) + " ";

			if (i < 4) {
				// Gets the house of pin and transform to a string
				PinsInfo += Integer.toString(ConjuntoDePinos.getInstancce().getYellow(i).getCasa()) + " ";
			} else {
				PinsInfo += Integer.toString(ConjuntoDePinos.getInstancce().getYellow(i).getCasa());
			}
		}

		// Writes the string on the file
		SaveOutput.println(PinsInfo);

		PinsInfo = "Green:" + " ";
		for (int i = 1; i < 5; i++) {
			// Gets the number of pin and transform to a string
			PinsInfo += Integer.toString(ConjuntoDePinos.getInstancce().getGreen(i).getNumero()) + " ";

			if (i < 4) {// Gets the house of pin and transform to a string
				PinsInfo += Integer.toString(ConjuntoDePinos.getInstancce().getGreen(i).getCasa()) + " ";
			}

			else {
				PinsInfo += Integer.toString(ConjuntoDePinos.getInstancce().getGreen(i).getCasa());
			}
		}

		// Writes the string on the file
		SaveOutput.println(PinsInfo);
	}

	public String packGameToClient() {

		// Create the string that contains the Round info
		String PinsInfo = "Round:" + " ";
		PinsInfo += Integer.toString(round);

		// Create the string that contains the ConjuntodePinos info
		PinsInfo += "#";
		PinsInfo += "Red:" + " ";
		for (int i = 1; i < 5; i++) {
			// Gets the number of pin and transform to a string
			PinsInfo += Integer.toString(ConjuntoDePinos.getInstancce().getRed(i).getNumero()) + " ";

			if (i < 4) {
				// Gets the house of pin and transform to a string
				PinsInfo += Integer.toString(ConjuntoDePinos.getInstancce().getRed(i).getCasa()) + " ";
			} else {
				// Gets the house of pin and transform to a string
				PinsInfo += Integer.toString(ConjuntoDePinos.getInstancce().getRed(i).getCasa());
			}
		}
		PinsInfo += "#";
		PinsInfo += "Blue:" + " ";
		for (int i = 1; i < 5; i++) {
			// Gets the numeber of pin and transform to a string
			PinsInfo += Integer.toString(ConjuntoDePinos.getInstancce().getBlue(i).getNumero()) + " ";

			if (i < 4) {
				// Gets the house of pin and transform to a string
				PinsInfo += Integer.toString(ConjuntoDePinos.getInstancce().getBlue(i).getCasa()) + " ";
			}

			else {
				PinsInfo += Integer.toString(ConjuntoDePinos.getInstancce().getBlue(i).getCasa());
			}
		}
		PinsInfo += "#";
		PinsInfo += "Yellow:" + " ";
		for (int i = 1; i < 5; i++) {
			// Gets the number of pin and transform to a string
			PinsInfo += Integer.toString(ConjuntoDePinos.getInstancce().getYellow(i).getNumero()) + " ";

			if (i < 4) {
				// Gets the house of pin and transform to a string
				PinsInfo += Integer.toString(ConjuntoDePinos.getInstancce().getYellow(i).getCasa()) + " ";
			} else {
				PinsInfo += Integer.toString(ConjuntoDePinos.getInstancce().getYellow(i).getCasa());
			}
		}
		PinsInfo += "#";
		PinsInfo += "Green:" + " ";
		for (int i = 1; i < 5; i++) {
			// Gets the number of pin and transform to a string
			PinsInfo += Integer.toString(ConjuntoDePinos.getInstancce().getGreen(i).getNumero()) + " ";

			if (i < 4) {// Gets the house of pin and transform to a string
				PinsInfo += Integer.toString(ConjuntoDePinos.getInstancce().getGreen(i).getCasa()) + " ";
			}

			else {
				PinsInfo += Integer.toString(ConjuntoDePinos.getInstancce().getGreen(i).getCasa());
			}
		}
		//System.out.println(PinsInfo);
		return PinsInfo;

	}

}
