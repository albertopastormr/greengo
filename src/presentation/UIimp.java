package presentation;

import business.city.TCity;
import business.client.TClient;
import business.contract.TContract;
import business.employee.TEmployee;
import business.mainoffice.TMainOffice;
import business.rental.TRental;
import business.rental.TRentalDetails;
import business.service.TService;
import business.vehicle.TVehicleDetails;
import presentation.client.ClientPanel;
import presentation.client.ClientTableModel;
import presentation.controller.AppController;
import presentation.controller.Event;
import presentation.controller.LightContext;
import presentation.service.ServicePanel;
import presentation.service.ServiceTableModel;
import presentation.util.TableModel;
import presentation.util.TablePanel;
import presentation.util.Util;
import presentation.util.ViewHelpers;
import presentation.vehicle.VehiclePanel;
import presentation.vehicle.VehicleTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
				Util.inform("Added vehicle with id " + (context.getData()));
				AppController.getInstance().execute(new LightContext(Event.RELOAD_VEHICLE, null));
				break;
			case DROP_VEHICLE:
				Util.inform("Deleted vehicle with id " + (context.getData()));
				AppController.getInstance().execute(new LightContext(Event.RELOAD_VEHICLE, null));
				break;
			case UPDATE_VEHICLE:
				Util.inform("Updated vehicle with id " + (context.getData()));
				AppController.getInstance().execute(new LightContext(Event.RELOAD_VEHICLE, null));
				break;
			case SHOW_VEHICLE:
				 JDialog vehicleDialog = ViewHelpers.createShowWindow(((TVehicleDetails)context.getData()).toString(), "Show vehicle");
				 vehicleDialog.setVisible(true);
				break;
			case RELOAD_VEHICLE:
				List<TVehicleDetails> vehicleList = new ArrayList<>();
				AppController.getInstance().execute(new LightContext(Event.SHOWALL_VEHICLE, vehicleList));
				break;
            case SHOWALL_AVAILABLE_VEHICLE:
				TableModel modelActive = new VehicleTableModel(VehiclePanel.getColumnId());
				modelActive.setList((List<TVehicleDetails>) context.getData());
				ViewHelpers.createSpecificTable(modelActive, "Show all available vehicles");
				break;
			case CREATE_SERVICE:
				Util.inform("Added service with id " + (context.getData()));
				AppController.getInstance().execute(new LightContext(Event.RELOAD_SERVICE, null));
				break;
			case DROP_SERVICE:
				Util.inform("Deleted vehicle with id " + (context.getData()));
				AppController.getInstance().execute(new LightContext(Event.RELOAD_SERVICE, null));
				break;
			case UPDATE_SERVICE:
				Util.inform("Updated vehicle with id " + (context.getData()));
				AppController.getInstance().execute(new LightContext(Event.RELOAD_SERVICE, null));
				break;
			case SHOW_SERVICE:
                JDialog serviceDialog = ViewHelpers.createShowWindow(((TService)context.getData()).toString(), "Show service");
                serviceDialog.setVisible(true);
				break;
			case RELOAD_SERVICE:
				List<TService> serviceList = new ArrayList<>();
				AppController.getInstance().execute(new LightContext(Event.SHOWALL_SERVICE, serviceList));
				break;
			case SHOW_SERVICE_BY_LEVEL:
				TableModel modelService = new ServiceTableModel(ServicePanel.getColumnId());
				modelService.setList((List<TService>) context.getData());
				ViewHelpers.createSpecificTable(modelService, "Show services by lavel");
				break;
			case CREATE_RENTAL:
				Util.inform("Added rental with id " + (context.getData()));
				AppController.getInstance().execute(new LightContext(Event.RELOAD_RENTAL, null));
				break;
			case DROP_RENTAL:
				Util.inform("Deleted rental with id " + (context.getData()));
				AppController.getInstance().execute(new LightContext(Event.RELOAD_RENTAL, null));
				break;
			case UPDATE_RENTAL:
				Util.inform("Updated rental with id " + (context.getData()));
				AppController.getInstance().execute(new LightContext(Event.RELOAD_RENTAL, null));
				break;
			case SHOW_RENTAL:
                JDialog rentalDialog = ViewHelpers.createShowWindow(((TRentalDetails)context.getData()).toString(), "Show rental");
                rentalDialog.setVisible(true);
			case RELOAD_RENTAL:
				List<TRental> rentalList = new ArrayList<>();
				AppController.getInstance().execute(new LightContext(Event.SHOWALL_RENTAL, rentalList));
				break;
			case CREATE_MAIN_OFFICE:
				Util.inform("Added main office with id " + (context.getData()));
				AppController.getInstance().execute(new LightContext(Event.RELOAD_MAIN_OFFICE, null));
				break;
			case DROP_MAIN_OFFICE:
				Util.inform("Deleted main office with id " + (context.getData()));
				AppController.getInstance().execute(new LightContext(Event.RELOAD_MAIN_OFFICE, null));
				break;
			case UPDATE_MAIN_OFFICE:
				Util.inform("Updated main office with id " + (context.getData()));
				AppController.getInstance().execute(new LightContext(Event.RELOAD_MAIN_OFFICE, null));
				break;
			case SHOW_MAIN_OFFICE:
                JDialog mainOfficeDialog = ViewHelpers.createShowWindow(((TMainOffice)context.getData()).toString(), "Show main office");
                mainOfficeDialog.setVisible(true);
				break;
			case RELOAD_MAIN_OFFICE:
				List<TMainOffice> mainOfficeList = new ArrayList<>();
				AppController.getInstance().execute(new LightContext(Event.SHOWALL_MAIN_OFFICE, mainOfficeList));
				break;
			case TOTAL_SALARY_MAIN_OFFICE:
				Util.inform("Current salary of the main office is " + context.getData());
				break;
			case CREATE_EMPLOYEE:
				Util.inform("Added employee with id " + (context.getData()));
				AppController.getInstance().execute(new LightContext(Event.RELOAD_EMPLOYEE, null));
				break;
			case DROP_EMPLOYEE:
				Util.inform("Deleted employee with id " + (context.getData()));
				AppController.getInstance().execute(new LightContext(Event.RELOAD_EMPLOYEE, null));
				break;
			case UPDATE_EMPLOYEE:
				Util.inform("Updated employee with id " + (context.getData()));
				AppController.getInstance().execute(new LightContext(Event.RELOAD_EMPLOYEE, null));
				break;
			case SHOW_EMPLOYEE:
                JDialog employeeDialog = ViewHelpers.createShowWindow(((TEmployee)context.getData()).toString(), "Show employee");
                employeeDialog.setVisible(true);
				break;
			case RELOAD_EMPLOYEE:
				List<TEmployee> employeeList = new ArrayList<>();
				AppController.getInstance().execute(new LightContext(Event.SHOWALL_EMPLOYEE, employeeList));
				break;
			case SET_SALARY_EMPLOYEE:
				Util.inform("Updated the salary to employee " + context.getData() + "successfully");
				break;
			case CREATE_CONTRACT:
				Util.inform("Added contract with id " + (context.getData()));
				AppController.getInstance().execute(new LightContext(Event.RELOAD_CONTRACT, null));
				break;
			case DROP_CONTRACT:
				Util.inform("Deleted contract with id " + (context.getData()));
				AppController.getInstance().execute(new LightContext(Event.RELOAD_CONTRACT, null));
				break;
			case UPDATE_CONTRACT:
				Util.inform("Updated contract with id " + (context.getData()));
				AppController.getInstance().execute(new LightContext(Event.RELOAD_CONTRACT, null));
				break;
			case SHOW_CONTRACT:
                JDialog contractDialog = ViewHelpers.createShowWindow(((TContract)context.getData()).toString(), "Show contract");
                contractDialog.setVisible(true);
				break;
			case RELOAD_CONTRACT:
				List<TContract> contractList = new ArrayList<>();
				AppController.getInstance().execute(new LightContext(Event.SHOWALL_CONTRACT, contractList));
				break;
			case CREATE_CLIENT:
				Util.inform("Added client with id " + (context.getData()));
				AppController.getInstance().execute(new LightContext(Event.RELOAD_CLIENT, null));
				break;
			case DROP_CLIENT:
				Util.inform("Deleted client with id " + (context.getData()));
				AppController.getInstance().execute(new LightContext(Event.RELOAD_CLIENT, null));
				break;
			case UPDATE_CLIENT:
				Util.inform("Updated client with id " + (context.getData()));
				AppController.getInstance().execute(new LightContext(Event.RELOAD_CLIENT, null));
				break;
			case SHOW_CLIENT:
				JDialog clientDialog = ViewHelpers.createShowWindow(((TClient)context.getData()).toString(), "Show client");
                clientDialog.setVisible(true);
				break;
			case RELOAD_CLIENT:
				ArrayList clientList = new ArrayList();
				AppController.getInstance().execute(new LightContext(Event.SHOWALL_CLIENT, clientList));
				break;
			case SHOW_CLIENTS_N_RENTAL_CLIENT:
				TableModel modelNRentals = new ClientTableModel(ClientPanel.getColumnId());
				modelNRentals.setList((List<TClient>) context.getData());
				ViewHelpers.createSpecificTable(modelNRentals, "Show clients with N rentals");
				break;
			case CREATE_CITY:
				Util.inform("Added city with id " + (context.getData()));
				AppController.getInstance().execute(new LightContext(Event.RELOAD_CITY, null));
				break;
			case DROP_CITY:
				Util.inform("Deleted city with id " + (context.getData()));
				AppController.getInstance().execute(new LightContext(Event.RELOAD_CITY, null));
				break;
			case UPDATE_CITY:
				Util.inform("Updated city with id " + (context.getData()));
				AppController.getInstance().execute(new LightContext(Event.RELOAD_CITY, null));
				break;
			case SHOW_CITY:
                JDialog cityDialog = ViewHelpers.createShowWindow(((TCity)context.getData()).toString(), "Show city");
                cityDialog.setVisible(true);
				break;
			case RELOAD_CITY:
				List<TCity> cityList = new ArrayList<>();
				AppController.getInstance().execute(new LightContext(Event.SHOWALL_CITY, cityList));
				break;
			case SHOW_CLIENTS_FROM_CITY:
				TableModel modelCityClients = new ClientTableModel(ClientPanel.getColumnId());
				modelCityClients.setList((List<TClient>) context.getData());
				ViewHelpers.createSpecificTable(modelCityClients, "Show clients from city");
				break;

			//all these operations do the same.
			case SHOWALL_CITY:
			case SHOWALL_VEHICLE:
			case SHOWALL_CLIENT:
			case SHOWALL_CONTRACT:
			case SHOWALL_EMPLOYEE:
			case SHOWALL_MAIN_OFFICE:
			case SHOWALL_RENTAL:
			case SHOWALL_SERVICE:
				tabbedPane.update((List) context.getData());
				break;
			case ERROR:
				Util.inform(((Exception)context.getData()).toString());
				break;
		}
	}
}
