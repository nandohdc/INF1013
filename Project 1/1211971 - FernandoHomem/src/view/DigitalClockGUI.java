package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import model.Subject;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.ClockFacade;
import model.Observer;

@SuppressWarnings("serial")
public class DigitalClockGUI extends JPanel implements Observer {
	// Observer
	private static int ObserverIDTracker = 0;
	private int ObserverID;

	// Frame
	private static String windowTitle = "Digital Clock";
	private static int width = 280;
	private static int height = 260;

	// Panel
	private static JFrame ControlPanel = null;
	private static JLabel Time;
	private static String textHour;
	private static String textMinute;
	private static String textSecond;
	private static int xDrawString = 20;
	private static int yDrawString = 130;
	Graphics2D graphSettings;

	// Time
	private static int hour = 0;
	private static int minute = 0;
	private static int second = 0;

	// Singleton da Class -- AnalogClockGUI
	private static DigitalClockGUI InstanceDigitalClockGUI = null;

	public static DigitalClockGUI getInstancce() {
		if (InstanceDigitalClockGUI == null) {

			InstanceDigitalClockGUI = new DigitalClockGUI();
		}

		return InstanceDigitalClockGUI;
	}

	public DigitalClockGUI() {
		this.setBackground(Color.black);
		Time = new JLabel();
		ControlPanel = new JFrame();
		this.add(Time, BorderLayout.CENTER);
	}

	private void createTimeLabel(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		while(getHour() >= 24){
			setHour(getHour() - 24);
		}
		
		while(getMinute() >= 60){
			setMinute(getMinute() - 60);
		}
		
		while(getSecond() >= 60){
			setSecond(getSecond() - 60);
		}
		
		textHour = Integer.toString(getHour());
		textMinute = Integer.toString(getMinute());
		textSecond = Integer.toString(getSecond());
		
		g.setFont(new Font("serif", Font.BOLD, 40));
		
		if(ClockFacade.getInstance().returnClockState() == 0){//hora
			g2d.setColor(Color.RED);
			g2d.drawString(textHour, xDrawString, yDrawString);
			g2d.setColor(Color.WHITE);
			g2d.drawString(":", xDrawString + 60, yDrawString);
			g2d.drawString(textMinute, xDrawString + 100, yDrawString);
			g2d.drawString(":", xDrawString + 160, yDrawString);
			g2d.drawString(textSecond, xDrawString + 200, yDrawString);

		} else if(ClockFacade.getInstance().returnClockState() == 1){//minuto
			g2d.setColor(Color.WHITE);
			g2d.drawString(textHour, xDrawString, yDrawString);
			g2d.drawString(":", xDrawString + 60, yDrawString);
			g2d.setColor(Color.RED);
			g2d.drawString(textMinute, xDrawString + 100, yDrawString);
			g2d.setColor(Color.WHITE);
			g2d.drawString(":", xDrawString + 160, yDrawString);
			g2d.drawString(textSecond, xDrawString + 200, yDrawString);

			
		} else if(ClockFacade.getInstance().returnClockState() == 2){//segundo
			g2d.setColor(Color.WHITE);
			g2d.drawString(textHour, xDrawString, yDrawString);
			g2d.drawString(":", xDrawString + 60, yDrawString);
			g2d.drawString(textMinute, xDrawString + 100, yDrawString);
			g2d.drawString(":", xDrawString + 160, yDrawString);
			g2d.setColor(Color.RED);
			g2d.drawString(textSecond, xDrawString + 200, yDrawString);
			
		} else{//exibicao
			g2d.setColor(Color.WHITE);
			g2d.drawString(textHour, xDrawString, yDrawString);
			g2d.drawString(":", xDrawString + 60, yDrawString);
			g2d.drawString(textMinute, xDrawString + 100, yDrawString);
			g2d.drawString(":", xDrawString + 160, yDrawString);
			g2d.drawString(textSecond, xDrawString + 200, yDrawString);
		}

	}

	public void createDigitalClockGUI() {
		
		ControlPanel.setTitle(windowTitle);
		ControlPanel.setSize(width, height);
		ControlPanel.getContentPane().setBackground(Color.white);
		ControlPanel.setLocationRelativeTo(null);
		ControlPanel.add(this, BorderLayout.CENTER);
		ControlPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ControlPanel.setVisible(true);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		graphSettings = (Graphics2D) g;
		graphSettings.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
		this.createTimeLabel(g);

		revalidate();
		repaint();
	}
	
	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public int getSecond() {
		return second;
	}

	public void setHour(int newHour) {
		hour = newHour;
	}

	public void setMinute(int newMinute) {
		minute = newMinute;
	}

	public void setSecond(int newSecond) {
		second = newSecond;
	}

	@Override
	public void update(int Hour, int Minute, int Second) {
		setHour(Hour);
		setMinute(Minute);
		setSecond(Second);

	}

	public void addObserverDigitalClockGUI(Subject EngineClock) {

		// Incrementando o contado statico
		this.ObserverID = ++ObserverIDTracker;

		// Mensagem que notifica o usuario de um novo observador
		System.out.println("New Observer" + this.ObserverID);

		// Add o observer para a Arralist
		EngineClock.register(this);
	}

}
