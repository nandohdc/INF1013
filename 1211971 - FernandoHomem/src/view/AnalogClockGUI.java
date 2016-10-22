package view;

import model.*;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AnalogClockGUI extends JPanel implements Observer{
	
	//Observer
	private static int ObserverIDTracker = 0;
	private int ObserverID;
	
	//Frame
	private static String windowTitle = "Analog Clock";
	private static int width = 450;
	private static int height = 480;
	private static int xCenter = 226;
	private static int yCenter = 226;
	
	//Panel
	private static BufferedImage image;
	Graphics2D graphSettings;
	
	//Time
	private static int hour = 0;
	private static int minute = 0;
	private static int second = 0;
	
	//Singleton da Class -- AnalogClockGUI
	private static AnalogClockGUI InstanceAnalogClockGUI = null;
	
	public static AnalogClockGUI getInstancce(){
		if(InstanceAnalogClockGUI == null){

			InstanceAnalogClockGUI = new AnalogClockGUI();
		}

		return InstanceAnalogClockGUI;
	}
	
	
	public AnalogClockGUI(){
		
		this.setBackground(Color.white);
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		graphSettings = (Graphics2D) g;
		graphSettings.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
		image = loadBackgroundImage();
		graphSettings.drawImage(image,0,0,null);
		drawPointerHour(graphSettings);
		drawPointerMinute(graphSettings);
		drawPointerSecond(graphSettings);
		revalidate();
		repaint();
	}
	
	private BufferedImage loadBackgroundImage() {
		
		BufferedImage result = null;
		try {

			result = ImageIO.read(new File("src/images/analogico.jpg"));

		} catch (IOException e) {

			System.out.println("Erro: Image Background not found!");
			System.exit(0);
		}
		
		return result;

	}
	
	private void drawPointerHour(Graphics2D graphs){
		double localHour;
		int radius = 230;
		
		localHour = getHour() % 12.0 / 12.0 * Math.PI * 2.0;
		
		int xCord2 = (int) ((xCenter) + (Math.sin(localHour) * radius/2));
		int yCord2 = (int) ((yCenter) - (Math.cos(localHour) * radius/2));;
		
		graphs.setStroke(new BasicStroke(4));
		graphs.setColor(Color.BLACK);
		graphs.drawLine(xCenter, yCenter, xCord2, yCord2);
		
	}
	
	private void drawPointerMinute(Graphics2D graphs){
		double localMinute;
		int radius = 250;
		
		localMinute = getMinute() % 60.0 / 60.0 * Math.PI * 2.0;
		
		int xCord2 = (int) ((xCenter) + (Math.sin(localMinute) * radius/2));
		int yCord2 = (int) ((yCenter) - (Math.cos(localMinute) * radius/2));;
		
		graphs.setStroke(new BasicStroke(3));
		graphs.setColor(Color.BLUE);
		graphs.drawLine(xCenter, yCenter, xCord2, yCord2);
		
	}
	
	private void drawPointerSecond(Graphics2D graphs){
		double localSecond;
		int radius = 280;
		
		localSecond = getSecond() % 60.0 / 60.0 * Math.PI * 2.0;
		
		int xCord2 = (int) ((xCenter) + (Math.sin(localSecond) * radius/2));
		int yCord2 = (int) ((yCenter) - (Math.cos(localSecond) * radius/2));;
		
		graphs.setStroke(new BasicStroke(2));
		graphs.setColor(Color.RED);
		graphs.drawLine(xCenter, yCenter, xCord2, yCord2);
		
	}

	
	public void createGUI(){
		JFrame window = new JFrame();
		window.setTitle(windowTitle);
		window.setSize(width, height);
		window.getContentPane().setBackground(Color.white);
		window.setLocationRelativeTo(null);
		window.add(this,BorderLayout.CENTER);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
	public int getHour(){
		return hour;
	}
	
	public int getMinute(){
		return minute;
	}
	
	public int getSecond(){
		return second;
	}
	
	public void setHour(int newHour){
		hour = newHour;
	}
	
	public void setMinute(int newMinute){
		minute = newMinute;
	}
	
	public void setSecond(int newSecond){
		second = newSecond;
	}

	@Override
	public void update(int Hour, int Minute, int Second) {
		setHour(Hour);
		setMinute(Minute);
		setSecond(Second);
	}
	
	public void addObserverAnalogClockGUI(Subject EngineClock){

		//Incrementando o contado statico
		this.ObserverID = ++ObserverIDTracker;

		//Mensagem que notifica o usuario de um novo observador
		System.out.println("New Observer" + this.ObserverID);

		//Add o observer para a Arralist
		EngineClock.register(this);
	}
		
}