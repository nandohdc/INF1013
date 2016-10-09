package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import model.Subject;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Observer;

@SuppressWarnings("serial")
public class DigitalClockGUI extends JPanel implements Observer {
	// Observer
	private static int ObserverIDTracker = 0;
	private int ObserverID;

	// Frame
	private static String windowTitle = "Digital Clock";
	private static int width = 450;
	private static int height = 130;

	// Panel
	private static JFrame ControlPanel = null;
	private static JLabel Time;
	private static String textTime;
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
		this.setBackground(Color.WHITE);
		Time = new JLabel();
		ControlPanel = new JFrame();
	}

	private void createTimeLabel() {
		
		while(getHour() >= 24){
			setHour(getHour() - 24);
		}
		
		while(getMinute() >= 60){
			setMinute(getMinute() - 60);
		}
		
		while(getSecond() >= 60){
			setSecond(getSecond() - 60);
		}

		textTime = Integer.toString(getHour()) + " : " + Integer.toString(getMinute()) + " : "
				+ Integer.toString(getSecond());

		Time.setText(textTime);
		Time.setFont(new Font("serif", Font.BOLD, 40));
		this.add(Time, BorderLayout.CENTER);

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
	
	public void setTextColor(Color color){
		Time.setForeground(color);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		graphSettings = (Graphics2D) g;
		this.createTimeLabel();

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
