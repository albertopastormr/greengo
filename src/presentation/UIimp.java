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

		pack();
		setVisible(true);
	}

	@Override
	public void update(LightContext context) {
		switch (context.getEvent()){
			case CREATE_VEHICLE:
			case DROP_VEHICLE:
			case UPDATE_VEHICLE:
			case SWITCH_TO_VEHICLE:
				ArrayList vehicleList = new ArrayList();
				AppController.getInstance().execute(new LightContext(Event.SHOWALL_VEHICLE, vehicleList));
				tabbedPane.update(vehicleList);
				break;
			case SHOWALL_ACTIVE_VEHICLE:

				break;
			case SHOW_VEHICLE:

				break;
			case CREATE_SERVCE:
			case DROP_SERVICE:
			case UPDATE_SERVICE:
			case SWITCH_TO_SERVICE:

				break;
			case SHOW_SERVICE_BY_LEVEL:

				break;
			case SHOW_SERVICE:

				break;

			case CREATE_RENTAL:
			case DROP_RENTAL:
			case UPDATE_RENTAL:
			case SWITCH_TO_RENTAL:

				break;
			case SHOW_RENTAL:

				break;

			case CREATE_MAIN_OFFICE:
			case DROP_MAIN_OFFICE:
			case UPDATE_MAIN_OFFICE:
			case SWITCH_TO_MAIN_OFFICE:

				break;
			case SHOW_MAIN_OFFICE:

				break;
			case TOTAL_SALARY_MAIN_OFFICE:

				break;

			case CREATE_EMPLOYEE:
			case DROP_EMPLOYEE:
			case UPDATE_EMPLOYEE:
			case SWITCH_TO_EMPLOYEE:

				break;
			case SHOW_EMPLOYEE:

				break;

			case SET_SALARY_EMPLOYEE:

				break;

			case CREATE_CONTRACT:
			case DROP_CONTRACT:
			case UPDATE_CONTRACT:
			case SWITCH_TO_CONTRACT:

				break;
			case SHOW_CONTRACT:

				break;

			case CREATE_CLIENT:
			case DROP_CLIENT:
			case UPDATE_CLIENT:
			case SWITCH_TO_CLIENT:

				break;
			case SHOW_CLIENT:

				break;
			case SHOW_CLIENTS_N_RENTAL_CLIENT:

				break;

			case CREATE_CITY:
			case DROP_CITY:
			case UPDATE_CITY:
			case SWITCH_TO_CITY:

				break;
			case SHOW_CITY:

				break;
			case SHOW_CLIENTS_FROM_CITY:

				break;
		}
	}
}
