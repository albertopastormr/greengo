package presentation.main_office;

import presentation.PanelTabs;
import presentation.main_office.forms.*;
import presentation.util.ViewHelpers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainOfficeToolbar extends JToolBar {

	public MainOfficeToolbar(PanelTabs panelTabs) {
		initGUI(panelTabs);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setFloatable(false);
		setRollover(true);
		setBackground(new Color(240, 240, 240));
	}

	private void initGUI(PanelTabs panelTabs) {
		// Create main office
		JButton create = ViewHelpers.buttonsForms("Create main office");
		create.setToolTipText("Create main office");
		create.setIcon(new ImageIcon("resources/images/add.png"));
		create.setVerticalTextPosition(SwingConstants.BOTTOM);
		create.setHorizontalTextPosition(SwingConstants.CENTER);
		create.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {
				FormCreateMainOffice formCreateMainOffice = new FormCreateMainOffice();
				formCreateMainOffice.setVisible(true);
			}
		});

		// Drop main office
		JButton drop = ViewHelpers.buttonsForms("Drop main office");
		drop.setToolTipText("Drop main office");
		drop.setIcon(new ImageIcon("resources/images/drop.png"));
		drop.setVerticalTextPosition(SwingConstants.BOTTOM);
		drop.setHorizontalTextPosition(SwingConstants.CENTER);
		drop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FormDropMainOffice formDropMainOffice = new FormDropMainOffice();
				formDropMainOffice.setVisible(true);
			}
		});

		// Show main office
		JButton show = ViewHelpers.buttonsForms("Show main office");
		show.setToolTipText("Show main office");
		show.setIcon(new ImageIcon("resources/images/show.png"));
		show.setVerticalTextPosition(SwingConstants.BOTTOM);
		show.setHorizontalTextPosition(SwingConstants.CENTER);
		show.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FormShowMainOffice formShowMainOffice = new FormShowMainOffice();
				formShowMainOffice.setVisible(true);
			}
		});

		// Update main office
		JButton update = ViewHelpers.buttonsForms("Update main office");
		update.setToolTipText("Update main office");
		update.setIcon(new ImageIcon("resources/images/update.png"));
		update.setVerticalTextPosition(SwingConstants.BOTTOM);
		update.setHorizontalTextPosition(SwingConstants.CENTER);
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FormUpdateMainOffice formUpdateMainOffice = new FormUpdateMainOffice();
				formUpdateMainOffice.setVisible(true);
			}
		});

		// Total salary main office
		JButton showTotalSalary = ViewHelpers.buttonsForms("Show total salary");
		showTotalSalary.setToolTipText("Show total salary");
		showTotalSalary.setIcon(new ImageIcon("resources/images/totalSalary.png"));

		showTotalSalary.setVerticalTextPosition(SwingConstants.BOTTOM);
		showTotalSalary.setHorizontalTextPosition(SwingConstants.CENTER);
		showTotalSalary.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FormTotalSalaryMainOffice formTotalSalaryMainOffice = new FormTotalSalaryMainOffice();
				formTotalSalaryMainOffice.setVisible(true);
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
		add(showTotalSalary);
	}
}
