package presentation;

import presentation.city.CityPanel;
import presentation.client.ClientPanel;
import presentation.contract.ContractPanel;
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
		addTab("Employee", new ImageIcon("resources/images/employee.png"), contractTab, "Employee view");
		setMnemonicAt(0, KeyEvent.VK_4);
		employeePane = new EmployeePanel(this);
		employeeTab.add(employeePane);

		//vista main office
		JComponent mainOfficeTab = new JPanel(new GridLayout(1, 1));
		addTab("MainOffice", new ImageIcon("resources/images/mainOffice.png"), contractTab, "MainOffice view");
		setMnemonicAt(0, KeyEvent.VK_5);
		mainOfficePane = new MainOfficePanel(this);
		mainOfficeTab.add(mainOfficePane);

		//vista rental
		JComponent rentalTab = new JPanel(new GridLayout(1, 1));
		addTab("Rental", new ImageIcon("resources/images/rental.png"), contractTab, "Rental view");
		setMnemonicAt(0, KeyEvent.VK_6);
		rentalPane = new RentalPanel(this);
		rentalTab.add(rentalPane);

		//vista service
		JComponent serviceTab = new JPanel(new GridLayout(1, 1));
		addTab("Service", new ImageIcon("resources/images/service.png"), contractTab, "Service view");
		setMnemonicAt(0, KeyEvent.VK_7);
		servicePane = new ServicePanel(this);
		serviceTab.add(servicePane);

		//vista vehicle
		JComponent vehicleTab = new JPanel(new GridLayout(1, 1));
		addTab("Vehicle", new ImageIcon("resources/images/vehicle.png"), contractTab, "Vehicle view");
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

						break;
					case 1:

						break;
					case 2:

						break;
					case 3:

						break;
					case 4:

						break;
					case 5:

						break;
					case 6:

						break;
					case 7:

						break;
				}
			}
		});
	}
}
