import controller.ClockFacade;
import model.EngineClock;
import view.AnalogClockGUI;
import view.ControlPanelGUI;
import view.DigitalClockGUI;

public class builder {

	public static void main(String[] args) {
		EngineClock eClock = new EngineClock();
		new ClockFacade();
		new AnalogClockGUI();
		new ControlPanelGUI();
		new DigitalClockGUI();
		
		ClockFacade.FacadeRegistraObserver(eClock);

		AnalogClockGUI.getInstancce().createGUI();
		ControlPanelGUI.getInstancce().createControlPanelGUI();
		DigitalClockGUI.getInstancce().createDigitalClockGUI();

	}
}
