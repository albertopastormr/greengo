package presentation;

import javafx.scene.effect.Light;
import presentation.city.CityPanel;
import presentation.client.ClientPanel;
import presentation.contract.ContractPanel;
import presentation.controller.AppController;
import presentation.controller.Event;
import presentation.controller.LightContext;
import presentation.employee.EmployeePanel;
import presentation.main_office.MainOfficePanel;
import presentation.rental.RentalPanel;
import presentation.service.ServicePanel;
import presentation.vehicle.VehiclePanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class PanelTabs extends JTabbedPane {

	private CityPanel cityPane;
	private ClientPanel clientPane;
	private ContractPanel contractPane;
	private EmployeePanel employeePane;
	private MainOfficePanel mainOfficePane;
	private RentalPanel rentalPane;
	private ServicePanel servicePane;
	private VehiclePanel vehiclePane;

	PanelTabs() {
		//vista city
		JComponent cityTab = new JPanel(new GridLayout(1, 1));
		addTab("City", new ImageIcon("resources/images/city.png"), cityTab, "City view");
		setMnemonicAt(0, KeyEvent.VK_1);
		cityPane = new CityPanel(this);
		cityTab.add(cityPane);

		//vista client
		JComponent clientTab = new JPanel(new GridLayout(1, 1));
		addTab("Client", new ImageIcon("resources/images/client.png"), clientTab, "Client view");
		setMnemonicAt(0, KeyEvent.VK_2);
		clientPane = new ClientPanel(this);
		clientTab.add(clientPane);

		//vista contract
		JComponent contractTab = new JPanel(new GridLayout(1, 1));
		addTab("Contract", new ImageIcon("resources/images/contract.png"), contractTab, "Contract view");
		setMnemonicAt(0, KeyEvent.VK_3);
		contractPane = new ContractPanel(this);
		contractTab.add(contractPane);

		//vista employee
		JComponent employeeTab = new JPanel(new GridLayout(1, 1));
		addTab("Employee", new ImageIcon("resources/images/employee.png"), employeeTab, "Employee view");
		setMnemonicAt(0, KeyEvent.VK_4);
		employeePane = new EmployeePanel(this);
		employeeTab.add(employeePane);

		//vista main office
		JComponent mainOfficeTab = new JPanel(new GridLayout(1, 1));
		addTab("MainOffice", new ImageIcon("resources/images/mainOffice.png"), mainOfficeTab, "MainOffice view");
		setMnemonicAt(0, KeyEvent.VK_5);
		mainOfficePane = new MainOfficePanel(this);
		mainOfficeTab.add(mainOfficePane);

		//vista rental
		JComponent rentalTab = new JPanel(new GridLayout(1, 1));
		addTab("Rental", new ImageIcon("resources/images/rental.png"), rentalTab, "Rental view");
		setMnemonicAt(0, KeyEvent.VK_6);
		rentalPane = new RentalPanel(this);
		rentalTab.add(rentalPane);

		//vista service
		JComponent serviceTab = new JPanel(new GridLayout(1, 1));
		addTab("Service", new ImageIcon("resources/images/service.png"), serviceTab, "Service view");
		setMnemonicAt(0, KeyEvent.VK_7);
		servicePane = new ServicePanel(this);
		serviceTab.add(servicePane);

		//vista vehicle
		JComponent vehicleTab = new JPanel(new GridLayout(1, 1));
		addTab("Vehicle", new ImageIcon("resources/images/vehicle.png"), vehicleTab, "Vehicle view");
		setMnemonicAt(0, KeyEvent.VK_8);
		vehiclePane = new VehiclePanel(this);
		vehicleTab.add(vehiclePane);

		//The following line enables to use scrolling tabs.
		setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent changeEvent) {
				switch (getSelectedIndex()) {
					case 0:
						AppController.getInstance().execute(new LightContext(Event.SWITCH_TO_CITY, null));
						break;
					case 1:
						AppController.getInstance().execute(new LightContext(Event.SWITCH_TO_CLIENT, null));
						break;
					case 2:
						AppController.getInstance().execute(new LightContext(Event.SWITCH_TO_CONTRACT, null));
						break;
					case 3:
						AppController.getInstance().execute(new LightContext(Event.SWITCH_TO_EMPLOYEE, null));
						break;
					case 4:
						AppController.getInstance().execute(new LightContext(Event.SWITCH_TO_MAIN_OFFICE, null));
						break;
					case 5:
						AppController.getInstance().execute(new LightContext(Event.SWITCH_TO_RENTAL, null));
						break;
					case 6:
						AppController.getInstance().execute(new LightContext(Event.SWITCH_TO_SERVICE, null));
						break;
					case 7:
						AppController.getInstance().execute(new LightContext(Event.SWITCH_TO_VEHICLE, null));
						break;
				}
			}
		});
	}

	public void update(ArrayList newList){
		switch (getSelectedIndex()){
			case 0:
				cityPane.getModel().setList(newList);
				break;
			case 1:
				clientPane.getModel().setList(newList);
				break;
			case 2:
				contractPane.getModel().setList(newList);
				break;
			case 3:
				employeePane.getModel().setList(newList);
				break;
			case 4:
				mainOfficePane.getModel().setList(newList);
				break;
			case 5:
				rentalPane.getModel().setList(newList);
				break;
			case 6:
				servicePane.getModel().setList(newList);
				break;
			case 7:
				vehiclePane.getModel().setList(newList);
				break;


		}
	}
}
