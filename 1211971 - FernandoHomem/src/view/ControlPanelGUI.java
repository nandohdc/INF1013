package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.ClockFacade;

@SuppressWarnings("serial")
public class ControlPanelGUI extends JPanel {
	//ClockFacade
	ClockFacade clockfacade;
	
	// Frame
	private static String windowTitle = "Control Panel";
	private final static int width = 350;
	private final static int height = 90;

	// Panel
	private static JButton[] buttons = new JButton[2];
	private static String buttonTitle01 = "Ajustar";
	private static String buttonTitle02 = "Incrementar";

	// Singleton da Class -- ControlPanel
	private static ControlPanelGUI InstanceControlPanel = null;

	public static ControlPanelGUI getInstancce() {
		if (InstanceControlPanel == null) {

			InstanceControlPanel = new ControlPanelGUI();
		}

		return InstanceControlPanel;
	}

	public ControlPanelGUI() {
		clockfacade = new ClockFacade();
		this.setBackground(Color.GRAY);
	}

	private void createButtons() {

		buttons[0] = new JButton(buttonTitle01);
		buttons[1] = new JButton(buttonTitle02);
		
		buttons[0].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				clockfacade.SetNewStatusThread(false);
				clockfacade.PressAjust();
				DigitalClockGUI.getInstancce().setTextColor(Color.red);
				if(clockfacade.getCurrentState() == clockfacade.getExibitionState()){
					clockfacade.SetNewStatusThread(true);
					clockfacade.StartThread();
					DigitalClockGUI.getInstancce().setTextColor(Color.black);
				}
			}
		});
		
		buttons[1].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				clockfacade.SetNewStatusThread(false);
				clockfacade.PressIncrement();
				
				if(clockfacade.getCurrentState() == clockfacade.getSelectedHourState()){
					clockfacade.CounPressedHour();
				}
				
				if(clockfacade.getCurrentState() == clockfacade.getSelectedMinuteState()){
					clockfacade.CounPressedMinute();
				}
				
				if(clockfacade.getCurrentState() == clockfacade.getSelectedSecondState()){
					clockfacade.CounPressedSecond();
				}
			}
		});

		for (int i = 0; i < 2; i++) {
			buttons[i].setBackground(Color.white);
			this.add(buttons[i], BorderLayout.CENTER);
		}
		
	}

	public void createControlPanelGUI() {
		JFrame ControlPanel = new JFrame();
		ControlPanel.setTitle(windowTitle);
		ControlPanel.setSize(width, height);
		ControlPanel.getContentPane().setBackground(Color.white);
		ControlPanel.setLocationRelativeTo(null);
		
		this.createButtons();
		
		ControlPanel.add(this, BorderLayout.CENTER);
		ControlPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ControlPanel.setVisible(true);
	}

}
