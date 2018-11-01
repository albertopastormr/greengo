package presentation.vehicle;

import presentation.PanelTabs;
import presentation.service.forms.*;
import presentation.util.ViewHelpers;
import presentation.vehicle.forms.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VehicleToolbar extends  JToolBar {

	public VehicleToolbar(PanelTabs panelTabs) {
		initGUI(panelTabs);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setFloatable(false);
		setRollover(true);
	}

	private void initGUI(PanelTabs panelTabs) {
		// Create vehicle
		JButton create = ViewHelpers.buttonsForms("Create vehicle");
		create.setToolTipText("Create vehicle");
		create.setVerticalTextPosition(SwingConstants.BOTTOM);
		create.setHorizontalTextPosition(SwingConstants.CENTER);
		create.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FormCreateVehicle formCreateVehicle = new FormCreateVehicle();
				formCreateVehicle.setVisible(true);
			}
		});

		// Drop vehicle
		JButton drop = ViewHelpers.buttonsForms("Drop vehicle");
		drop.setToolTipText("Drop vehicle");
		drop.setVerticalTextPosition(SwingConstants.BOTTOM);
		drop.setHorizontalTextPosition(SwingConstants.CENTER);
		drop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FormDropVehicle formDropVehicle = new FormDropVehicle();
				formDropVehicle.setVisible(true);
			}
		});

		// Show vehicle
		JButton show = ViewHelpers.buttonsForms("Show vehicle");
		show.setToolTipText("Show vehicle");
		show.setVerticalTextPosition(SwingConstants.BOTTOM);
		show.setHorizontalTextPosition(SwingConstants.CENTER);
		show.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FormShowVehicle formShowVehicle = new FormShowVehicle();
				formShowVehicle.setVisible(true);
			}
		});

		// Update vehicle
		JButton update = ViewHelpers.buttonsForms("Update vehicle");
		update.setToolTipText("Update vehicle");
		update.setVerticalTextPosition(SwingConstants.BOTTOM);
		update.setHorizontalTextPosition(SwingConstants.CENTER);
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FormUpdateVehicle formUpdateVehicle = new FormUpdateVehicle();
				formUpdateVehicle.setVisible(true);
			}
		});

		// Show all active vehicle
		JButton showActivesVehicles = ViewHelpers.buttonsForms("Show actives vehicles ");
		showActivesVehicles.setToolTipText("Show actives vehicles");
		showActivesVehicles.setVerticalTextPosition(SwingConstants.BOTTOM);
		showActivesVehicles.setHorizontalTextPosition(SwingConstants.CENTER);
		showActivesVehicles.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FormShowAllActiveVehicle formShowAllActiveVehicle = new FormShowAllActiveVehicle();
				formShowAllActiveVehicle.setVisible(true);
			}
		});

		add(create);
		addSeparator();
		add(drop);
		addSeparator();
		add(show);
		addSeparator();
		add(update);
		addSeparator();
		add(showActivesVehicles);
	}
}
