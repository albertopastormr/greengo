package presentation.city;

import presentation.PanelTabs;
import presentation.city.forms.*;
import presentation.util.ViewHelpers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CityToolbar extends JToolBar {

	public CityToolbar(PanelTabs panelTabs){
		initGUI(panelTabs);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setFloatable(false);
		setRollover(true);
	}

	private void initGUI(PanelTabs panelTabs) {
		// Create city
		JButton create = ViewHelpers.buttonsForms("Create city");
		create.setToolTipText("Create city");
		create.setVerticalTextPosition(SwingConstants.BOTTOM);
		create.setHorizontalTextPosition(SwingConstants.CENTER);
		create.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {
				FormCreateCity formCreateCity = new FormCreateCity();
				formCreateCity.setVisible(true);
			}
		});

		// Drop city
		JButton drop = ViewHelpers.buttonsForms("Drop city");
		drop.setToolTipText("Drop city");
		drop.setVerticalTextPosition(SwingConstants.BOTTOM);
		drop.setHorizontalTextPosition(SwingConstants.CENTER);
		drop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FormDropCity formDropCity = new FormDropCity();
				formDropCity.setVisible(true);
			}
		});

		// Show city
		JButton show = ViewHelpers.buttonsForms("Show city");
		show.setToolTipText("Show city");
		show.setVerticalTextPosition(SwingConstants.BOTTOM);
		show.setHorizontalTextPosition(SwingConstants.CENTER);
		show.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FormShowCity formShowCity = new FormShowCity();
				formShowCity.setVisible(true);
			}
		});

		// Update city
		JButton update = ViewHelpers.buttonsForms("Update city");
		update.setToolTipText("Update city");
		update.setVerticalTextPosition(SwingConstants.BOTTOM);
		update.setHorizontalTextPosition(SwingConstants.CENTER);
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FormUpdateCity formUpdateCity = new FormUpdateCity();
				formUpdateCity.setVisible(true);
			}
		});

		// Show clients from city
		JButton showClientsFromCity = ViewHelpers.buttonsForms("Show clients from city");
		showClientsFromCity.setToolTipText(" Show clients from city");
		showClientsFromCity.setVerticalTextPosition(SwingConstants.BOTTOM);
		showClientsFromCity.setHorizontalTextPosition(SwingConstants.CENTER);
		showClientsFromCity.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FormShowClientsFromCity formShowClientsFromCity = new FormShowClientsFromCity();
				formShowClientsFromCity.setVisible(true);
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
		add(showClientsFromCity);
	}
}
