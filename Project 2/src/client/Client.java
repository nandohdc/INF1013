package client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client implements Runnable {
	private Socket newClient = null;
	private ClientThread newClientThread = null;
	private Thread newThread = null;
	private Scanner keyboard = null;
	private PrintStream saida = null;

	public Client(String newServerName, int NewPort) {
		try {
			newClient = new Socket(newServerName, NewPort);
			System.out.println("O cliente se conectou ao servidor!");
			startClient();
		} catch (UnknownHostException e) {
			System.out.println("Host Unknown: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Unexpected exception: " + e.getMessage());
		}
	}

	private void startClient() throws IOException {
		keyboard = new Scanner(System.in);
		saida = new PrintStream(newClient.getOutputStream());
		if (newThread == null) {
			newClientThread = new ClientThread(this, newClient);
			newThread = new Thread(this);
			newThread.start();
		}
	}

	public void closeConnection() throws IOException {
		if (newThread != null) {
			newThread.interrupt();
			newThread = null;
		}
		if (keyboard != null)
			keyboard.close();
		if (saida != null)
			saida.close();
		if (newClient != null)
			newClient.close();
		System.out.println("O cliente terminou de executar!");
	}

	public void handle(String newMsg) {
		if (newMsg.equals("###")) {
			System.out.println("O usuario saiu");
		} else {
			System.out.println(newMsg);
		}
	}

	@Override
	public void run() {
		while (newThread != null) {

			String msg = keyboard.nextLine();

			while (msg.compareTo("###") != 0) {
				saida.println(msg);
				msg = keyboard.nextLine();
			}
			try {
				this.closeConnection();
			} catch (IOException e) {
				System.out.println("Error closing connection" + e);
			}
		}

	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		if (args.length != 2) {
			System.out.println("Error: Insert the servername + port!");
		} else {
			new Client(args[0], Integer.parseInt(args[1]));
		}
	}

}
