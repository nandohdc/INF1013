package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable {
	private ArrayList<ServerThread> newClients = new ArrayList<ServerThread>();
	private ServerSocket newServer = null;
	private Thread newThread = null;

	public Server(int newPort) {
		try {
			newServer = new ServerSocket(newPort);
			System.out.println("Porta 80 aberta!");
			start();
		} catch (IOException e) {
			System.out.println("Erro Port " + newPort + " : " + e.getMessage());
		}
	}

	public void start() {
		if (newThread == null) {
			newThread = new Thread(this);
			newThread.start();
		}
	}

	public void stop() {
		if (newThread != null) {
			newThread.interrupt();
			;
			newThread = null;
		}
	}

	@Override
	public void run() {
		while (newThread != null) {
			try {
				System.out.println("Waiting for a client..");
				addNewThread(newServer.accept());
			} catch (IOException e) {
				System.out.println("Acceptance Error: " + e.getMessage());
				stop();
			}
		}
	}

	private void addNewThread(Socket newSocket) {
		System.out.println("Nova conexão com o cliente " + newSocket.getInetAddress().getHostAddress());
		newClients.add(new ServerThread(this, newSocket));

		try {
			newClients.get(newClients.size() - 1).openStream();
			newClients.get(newClients.size() - 1).start();

		} catch (IOException e) {
			System.out.println("Error opening Thread: " + e);
		}
	}

	public synchronized void handle(int idClient, String input) {
		int i = 0;
		if (input.equals("###")) {
			newClients.get(idClient).send("###");
			remove(idClient);
		} else {
			String msg = idClient + " : " + input;
			while(i != newClients.size()){
				newClients.get(i).send(msg);
				i++;
			}
		}
	}

	public synchronized void remove(int idClient) {
		System.out.println("Removing Client Thread " + idClient);
		try {
			newClients.get(idClient).closeConnection();
			newClients.remove(idClient);
		} catch (IOException e) {
			System.out.println("Error closing Client Connection" + e);
			newClients.get(idClient).interrupt();
		}

	}

	public void closeConnection() throws IOException {
		newServer.close();
		System.out.println("O servidor terminou de executar!");
	}

	public static void main(String[] args) throws IOException {
		;

		if (args.length != 1) {
			System.out.println("Error: Insert only the PORT!");
		} else {
			new Server(Integer.parseInt(args[0]));
		}
	}

}