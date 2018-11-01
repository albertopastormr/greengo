package presentation.client;

import presentation.PanelTabs;
import presentation.city.forms.*;
import presentation.client.forms.*;
import presentation.util.ViewHelpers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientToolbar extends JToolBar {

	public ClientToolbar(PanelTabs panelTabs){
		initGUI(panelTabs);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setFloatable(false);
		setRollover(true);
	}

	private void initGUI(PanelTabs panelTabs) {
		// Create client
		JButton create = ViewHelpers.buttonsForms("Create client");
		create.setToolTipText("Create client");
		create.setVerticalTextPosition(SwingConstants.BOTTOM);
		create.setHorizontalTextPosition(SwingConstants.CENTER);
		create.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {
				FormCreateClient formCreateClient = new FormCreateClient();
				formCreateClient.setVisible(true);
			}
		});

		// Drop client
		JButton drop = ViewHelpers.buttonsForms("Drop client");
		drop.setToolTipText("Drop client");
		drop.setVerticalTextPosition(SwingConstants.BOTTOM);
		drop.setHorizontalTextPosition(SwingConstants.CENTER);
		drop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FormDropClient formDropClient = new FormDropClient();
				formDropClient.setVisible(true);
			}
		});

		// Show client
		JButton show = ViewHelpers.buttonsForms("Show client");
		show.setToolTipText("Show client");
		show.setVerticalTextPosition(SwingConstants.BOTTOM);
		show.setHorizontalTextPosition(SwingConstants.CENTER);
		show.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FormShowClient formShowClient = new FormShowClient();
				formShowClient.setVisible(true);
			}
		});

		// Update client
		JButton update = ViewHelpers.buttonsForms("Update client");
		update.setToolTipText("Update client");
		update.setVerticalTextPosition(SwingConstants.BOTTOM);
		update.setHorizontalTextPosition(SwingConstants.CENTER);
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FormUpdateClient formUpdateClient = new FormUpdateClient();
				formUpdateClient.setVisible(true);
			}
		});

		// Show clients N rentals
		JButton showClientsNRentals = ViewHelpers.buttonsForms("Show clients N rentals");
		showClientsNRentals.setToolTipText(" Show clients N rentals");
		showClientsNRentals.setVerticalTextPosition(SwingConstants.BOTTOM);
		showClientsNRentals.setHorizontalTextPosition(SwingConstants.CENTER);
		showClientsNRentals.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FormShowClientsNRentals formShowClientsNRentals = new FormShowClientsNRentals();
				formShowClientsNRentals.setVisible(true);
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
		add(showClientsNRentals);
	}
}
