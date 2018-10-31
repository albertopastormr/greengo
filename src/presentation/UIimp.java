package presentation;

import presentation.controller.AppController;
import presentation.controller.Event;
import presentation.controller.LightContext;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UIimp extends UI{

	private PanelTabs tabbedPane;

	@Override
	public void execute() {
		setTitle("GreenGO");
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/images/logo.jpg"));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);

		tabbedPane = new PanelTabs();
		setContentPane(tabbedPane);

		tabbedPane.initialize();

		pack();
		setVisible(true);
	}

	@Override
	public void update(LightContext context) {
		switch (context.getEvent()){
			case RELOAD_VEHICLE:
				ArrayList vehicleList = new ArrayList();
				AppController.getInstance().execute(new LightContext(Event.SHOWALL_VEHICLE, vehicleList));
				tabbedPane.update(vehicleList);
				break;
			case SHOWALL_ACTIVE_VEHICLE:

				break;
			case SHOW_VEHICLE:

				break;
			case RELOAD_SERVICE:

				break;
			case SHOW_SERVICE_BY_LEVEL:

				break;
			case SHOW_SERVICE:

				break;
			case RELOAD_RENTAL:

				break;
			case SHOW_RENTAL:

				break;
			case REALOAD_MAIN_OFFICE:

				break;
			case SHOW_MAIN_OFFICE:

				break;
			case TOTAL_SALARY_MAIN_OFFICE:

				break;
			case RELOAD_EMPLOYEE:

				break;
			case SHOW_EMPLOYEE:

				break;

			case SET_SALARY_EMPLOYEE:

				break;
			case RELOAD_CONTRACT:

				break;
			case SHOW_CONTRACT:

				break;
			case RELOAD_CLIENT:

				break;
			case SHOW_CLIENT:

				break;
			case SHOW_CLIENTS_N_RENTAL_CLIENT:

				break;
			case RELOAD_CITY:

				break;
			case SHOW_CITY:

				break;
			case SHOW_CLIENTS_FROM_CITY:

				break;
		}
	}
}
