package presentation;

import business.city.TCity;
import business.client.TClient;
import business.contract.TContract;
import business.employee.TEmployee;
import business.mainoffice.TMainOffice;
import business.rental.TRental;
import business.service.TService;
import business.vehicle.TVehicle;
import presentation.controller.AppController;
import presentation.controller.Event;
import presentation.controller.LightContext;
import presentation.util.Util;

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
			case CREATE_VEHICLE:
				Util.inform("Added vehicle with id " + ((TVehicle)context.getData()).getId());
				AppController.getInstance().execute(new LightContext(Event.RELOAD_VEHICLE, null));
				break;
			case DROP_VEHICLE:
				Util.inform("Deleted vehicle with id " + ((TVehicle)context.getData()).getId());
				AppController.getInstance().execute(new LightContext(Event.RELOAD_VEHICLE, null));
				break;
			case UPDATE_VEHICLE:
				Util.inform("Updated vehicle with id " + ((TVehicle)context.getData()).getId());
				AppController.getInstance().execute(new LightContext(Event.RELOAD_VEHICLE, null));
				break;
			case SHOW_VEHICLE: Util.inform(((TVehicle)context.getData()).toString()); break;
			case RELOAD_VEHICLE:
				ArrayList vehicleList = new ArrayList();
				AppController.getInstance().execute(new LightContext(Event.SHOWALL_VEHICLE, vehicleList));
				tabbedPane.update(vehicleList);
				break;
			case SHOWALL_ACTIVE_VEHICLE:
				//TODO What should we do with these operations?


				break;
			case CREATE_SERVCE:
				Util.inform("Added service with id " + ((TService)context.getData()).getId());
				AppController.getInstance().execute(new LightContext(Event.RELOAD_SERVICE, null));
				break;
			case DROP_SERVICE:
				Util.inform("Deleted vehicle with id " + ((TService)context.getData()).getId());
				AppController.getInstance().execute(new LightContext(Event.RELOAD_SERVICE, null));
				break;
			case UPDATE_SERVICE:
				Util.inform("Updated vehicle with id " + ((TService)context.getData()).getId());
				AppController.getInstance().execute(new LightContext(Event.RELOAD_SERVICE, null));
				break;
			case SHOW_SERVICE: Util.inform(((TService)context.getData()).toString()); break;
			case RELOAD_SERVICE:
				ArrayList serviceList = new ArrayList();
				AppController.getInstance().execute(new LightContext(Event.SHOWALL_SERVICE, serviceList));
				tabbedPane.update(serviceList);

				break;
			case SHOW_SERVICE_BY_LEVEL:
				//TODO What should we do with these operations?


				break;

			case CREATE_RENTAL:
				Util.inform("Added rental with id " + ((TRental)context.getData()).getId());
				AppController.getInstance().execute(new LightContext(Event.RELOAD_RENTAL, null));
				break;
			case DROP_RENTAL:
				Util.inform("Deleted rental with id " + ((TRental)context.getData()).getId());
				AppController.getInstance().execute(new LightContext(Event.RELOAD_RENTAL, null));
				break;
			case UPDATE_RENTAL:
				Util.inform("Updated rental with id " + ((TRental)context.getData()).getId());
				AppController.getInstance().execute(new LightContext(Event.RELOAD_RENTAL, null));
				break;
			case SHOW_RENTAL: Util.inform(((TRental)context.getData()).toString()); break;
			case RELOAD_RENTAL:
				ArrayList rentalList = new ArrayList();
				AppController.getInstance().execute(new LightContext(Event.SHOWALL_RENTAL, rentalList));
				tabbedPane.update(rentalList);

				break;

			case CREATE_MAIN_OFFICE:
				Util.inform("Added main office with id " + ((TMainOffice)context.getData()).getId());
				AppController.getInstance().execute(new LightContext(Event.RELOAD_MAIN_OFFICE, null));
				break;
			case DROP_MAIN_OFFICE:
				Util.inform("Deleted main office with id " + ((TMainOffice)context.getData()).getId());
				AppController.getInstance().execute(new LightContext(Event.RELOAD_MAIN_OFFICE, null));
				break;
			case UPDATE_MAIN_OFFICE:
				Util.inform("Updated main office with id " + ((TMainOffice)context.getData()).getId());
				AppController.getInstance().execute(new LightContext(Event.RELOAD_MAIN_OFFICE, null));
				break;
			case SHOW_MAIN_OFFICE: Util.inform(((TMainOffice)context.getData()).toString()); break;
			case RELOAD_MAIN_OFFICE:
				ArrayList mainOfficeList = new ArrayList();
				AppController.getInstance().execute(new LightContext(Event.SHOWALL_MAIN_OFFICE, mainOfficeList));
				tabbedPane.update(mainOfficeList);

				break;
			case TOTAL_SALARY_MAIN_OFFICE:

				//TODO What should we do with these operations?

				break;

			case CREATE_EMPLOYEE:
				Util.inform("Added employee with id " + ((TEmployee)context.getData()).getId());
				AppController.getInstance().execute(new LightContext(Event.RELOAD_EMPLOYEE, null));
				break;
			case DROP_EMPLOYEE:
				Util.inform("Deleted employee with id " + ((TEmployee)context.getData()).getId());
				AppController.getInstance().execute(new LightContext(Event.RELOAD_EMPLOYEE, null));
				break;
			case UPDATE_EMPLOYEE:
				Util.inform("Updated employee with id " + ((TEmployee)context.getData()).getId());
				AppController.getInstance().execute(new LightContext(Event.RELOAD_EMPLOYEE, null));
				break;
			case SHOW_EMPLOYEE: Util.inform(((TEmployee)context.getData()).toString()); break;
			case RELOAD_EMPLOYEE:
				ArrayList employeeList = new ArrayList();
				AppController.getInstance().execute(new LightContext(Event.SHOWALL_EMPLOYEE, employeeList));
				tabbedPane.update(employeeList);
				break;

			case SET_SALARY_EMPLOYEE:
				//TODO What should we do with these operations?


				break;
			case CREATE_CONTRACT:
				Util.inform("Added contract with id " + ((TContract)context.getData()).getId());
				AppController.getInstance().execute(new LightContext(Event.RELOAD_CONTRACT, null));
				break;
			case DROP_CONTRACT:
				Util.inform("Deleted contract with id " + ((TContract)context.getData()).getId());
				AppController.getInstance().execute(new LightContext(Event.RELOAD_CONTRACT, null));
				break;
			case UPDATE_CONTRACT:
				Util.inform("Updated contract with id " + ((TContract)context.getData()).getId());
				AppController.getInstance().execute(new LightContext(Event.RELOAD_CONTRACT, null));
				break;
			case SHOW_CONTRACT: Util.inform(((TContract)context.getData()).toString()); break;
			case RELOAD_CONTRACT:
				ArrayList contractList = new ArrayList();
				AppController.getInstance().execute(new LightContext(Event.SHOWALL_CONTRACT, contractList));
				tabbedPane.update(contractList);

				break;

			case CREATE_CLIENT:
				Util.inform("Added client with id " + ((TClient)context.getData()).getId());
				AppController.getInstance().execute(new LightContext(Event.RELOAD_CLIENT, null));
				break;
			case DROP_CLIENT:
				Util.inform("Deleted client with id " + ((TClient)context.getData()).getId());
				AppController.getInstance().execute(new LightContext(Event.RELOAD_CLIENT, null));
				break;
			case UPDATE_CLIENT:
				Util.inform("Updated client with id " + ((TClient)context.getData()).getId());
				AppController.getInstance().execute(new LightContext(Event.RELOAD_CLIENT, null));
				break;
			case SHOW_CLIENT: Util.inform(((TClient)context.getData()).toString()); break;
			case RELOAD_CLIENT:
				ArrayList clientList = new ArrayList();
				AppController.getInstance().execute(new LightContext(Event.SHOWALL_CLIENT, clientList));
				tabbedPane.update(clientList);

				break;
			case SHOW_CLIENTS_N_RENTAL_CLIENT:

				//TODO What should we do with these operations?

				break;
			case CREATE_CITY:
				Util.inform("Added city with id " + ((TCity)context.getData()).getId());
				AppController.getInstance().execute(new LightContext(Event.RELOAD_CITY, null));
				break;
			case DROP_CITY:
				Util.inform("Deleted city with id " + ((TCity)context.getData()).getId());
				AppController.getInstance().execute(new LightContext(Event.RELOAD_CITY, null));
				break;
			case UPDATE_CITY:
				Util.inform("Updated city with id " + ((TCity)context.getData()).getId());
				AppController.getInstance().execute(new LightContext(Event.RELOAD_CITY, null));
				break;
			case SHOW_CITY: Util.inform(((TCity)context.getData()).toString()); break;
			case RELOAD_CITY:
				ArrayList cityList = new ArrayList();
				AppController.getInstance().execute(new LightContext(Event.SHOWALL_CITY, cityList));
				tabbedPane.update(cityList);
				break;
			case SHOW_CLIENTS_FROM_CITY:
				//TODO What should we do with these operations?


				break;
		}
	}
}
