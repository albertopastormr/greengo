package presentation.service;

import presentation.PanelTabs;
import presentation.service.forms.*;
import presentation.util.ViewHelpers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServiceToolbar extends JToolBar {

	public ServiceToolbar(PanelTabs panelTabs) {
		initGUI(panelTabs);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setFloatable(false);
		setRollover(true);
		setBackground(new Color(240, 240, 240));
	}

	private void initGUI(PanelTabs panelTabs) {
		// Create service
		JButton create = ViewHelpers.buttonsForms("Create service");
		create.setToolTipText("Create service");
		create.setIcon(new ImageIcon("resources/images/add.png"));
		create.setVerticalTextPosition(SwingConstants.BOTTOM);
		create.setHorizontalTextPosition(SwingConstants.CENTER);
		create.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FormCreateService formCreateService = new FormCreateService();
				formCreateService.setVisible(true);
			}
		});

		// Drop service
		JButton drop = ViewHelpers.buttonsForms("Drop service");
		drop.setToolTipText("Drop service");
		drop.setIcon(new ImageIcon("resources/images/drop.png"));
		drop.setVerticalTextPosition(SwingConstants.BOTTOM);
		drop.setHorizontalTextPosition(SwingConstants.CENTER);
		drop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FormDropService formDropService = new FormDropService();
				formDropService.setVisible(true);
			}
		});

		// Show service
		JButton show = ViewHelpers.buttonsForms("Show service");
		show.setToolTipText("Show service");
		show.setIcon(new ImageIcon("resources/images/show.png"));
		show.setVerticalTextPosition(SwingConstants.BOTTOM);
		show.setHorizontalTextPosition(SwingConstants.CENTER);
		show.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FormShowService formShowService = new FormShowService();
				formShowService.setVisible(true);
			}
		});

		// Update service
		JButton update = ViewHelpers.buttonsForms("Update service");
		update.setToolTipText("Update service");
		update.setIcon(new ImageIcon("resources/images/update.png"));
		update.setVerticalTextPosition(SwingConstants.BOTTOM);
		update.setHorizontalTextPosition(SwingConstants.CENTER);
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FormUpdateService formUpdateService = new FormUpdateService();
				formUpdateService.setVisible(true);
			}
		});

		// Show service by level
		JButton showByLevel = ViewHelpers.buttonsForms("Show service by level");
		showByLevel.setToolTipText("Show service by level");
		showByLevel.setIcon(new ImageIcon("resources/images/showByLevel.png"));
		showByLevel.setVerticalTextPosition(SwingConstants.BOTTOM);
		showByLevel.setHorizontalTextPosition(SwingConstants.CENTER);
		showByLevel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FormShowServiceByLevel formShowServiceByLevel = new FormShowServiceByLevel();
				formShowServiceByLevel.setVisible(true);
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
		add(showByLevel);
	}
}
