package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import controller.ClockFacade;

@SuppressWarnings("serial")
public class ControlPanelGUI extends JPanel {
	// ClockFacade
	ClockFacade clockfacade;

	// Frame
	private static String windowTitle = "Control Panel";
	private final static int width = 350;
	private final static int height = 120;

	// Panel
	private static JToggleButton[] buttons = new JToggleButton[2];
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

		buttons[0] = new JToggleButton(buttonTitle01);
		buttons[1] = new JToggleButton(buttonTitle02);

		buttons[0].addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				if (buttons[0].isSelected()) {
					buttonAjustPressed();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		buttons[1].addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
					if (buttons[0].isSelected() && !buttons[1].isSelected()) {
						clockfacade.PressIncrement();

						if (clockfacade.returnClockState() == 0) {
							clockfacade.CounPressedHour();
						}

						if (clockfacade.returnClockState() == 1) {
							clockfacade.CounPressedMinute();
						}

						if (clockfacade.returnClockState() == 2) {
							clockfacade.CounPressedSecond();
						}
					}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		for (int i = 0; i < 2; i++) {
			buttons[i].setBackground(Color.white);
			buttons[i].setPreferredSize(new Dimension(80, 80));
			this.add(buttons[i], BorderLayout.CENTER);
		}

	}

	private void buttonAjustPressed() {
		;
		clockfacade.PressAjust();
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
