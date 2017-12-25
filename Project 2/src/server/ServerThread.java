package server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ServerThread extends Thread {
	private Socket newSocketThread = null;
	private Server newServerThread = null;
	private int idThread = -1;
	private Scanner in = null;
	private PrintStream saida = null;

	public ServerThread(Server server, Socket newSocket) {
		newServerThread = server;
		newSocketThread = newSocket;
		idThread = newSocketThread.getPort();
	}

	public void run() {
		System.out.println("Server Thread " + idThread + " running");
		while (true) {
			while (in.hasNextLine()) {
				//System.out.println(in.nextLine());
				newServerThread.handle(this.idThread, in.nextLine());
			}
		}
	}

	public void send(String newMsg) {
		String msg = newMsg;
		
		saida.println(msg);
	}

	public void openStream() throws IOException {
		in = new Scanner(newSocketThread.getInputStream());
		saida = new PrintStream(newSocketThread.getOutputStream());
	}

	public void closeConnection() throws IOException {
		if (in != null)
			in.close();;
		if (newSocketThread != null)
			newSocketThread.close();
		if(newServerThread != null)
			newServerThread.closeConnection();
	}

}
