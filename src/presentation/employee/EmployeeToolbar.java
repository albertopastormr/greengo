package presentation.employee;

import presentation.PanelTabs;
import presentation.employee.forms.FormCreateEmployee;
import presentation.employee.forms.FormDropEmployee;
import presentation.employee.forms.FormShowEmployee;
import presentation.employee.forms.FormUpdateEmployee;
import presentation.util.ViewHelpers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeToolbar extends JToolBar {

	public EmployeeToolbar(PanelTabs panelTabs) {
		initGUI(panelTabs);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setFloatable(false);
		setRollover(true);
	}

	private void initGUI(PanelTabs panelTabs) {
		// Create employee
		JButton create = ViewHelpers.buttonsForms("Create employee");
		create.setToolTipText("Create employee");
		create.setVerticalTextPosition(SwingConstants.BOTTOM);
		create.setHorizontalTextPosition(SwingConstants.CENTER);
		create.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {
				FormCreateEmployee formCreateEmployee = new FormCreateEmployee();
				formCreateEmployee.setVisible(true);
			}
		});

		// Drop employee
		JButton drop = ViewHelpers.buttonsForms("Drop employee");
		drop.setToolTipText("Drop employee");
		drop.setVerticalTextPosition(SwingConstants.BOTTOM);
		drop.setHorizontalTextPosition(SwingConstants.CENTER);
		drop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FormDropEmployee formDropEmployee = new FormDropEmployee();
				formDropEmployee.setVisible(true);
			}
		});

		// Show employee
		JButton show = ViewHelpers.buttonsForms("Show employee");
		show.setToolTipText("Show employee");
		show.setVerticalTextPosition(SwingConstants.BOTTOM);
		show.setHorizontalTextPosition(SwingConstants.CENTER);
		show.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FormShowEmployee formShowEmployee = new FormShowEmployee();
				formShowEmployee.setVisible(true);
			}
		});

		// Update employee
		JButton update = ViewHelpers.buttonsForms("Update employee");
		update.setToolTipText("Update employee");
		update.setVerticalTextPosition(SwingConstants.BOTTOM);
		update.setHorizontalTextPosition(SwingConstants.CENTER);
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FormUpdateEmployee formUpdateEmployee = new FormUpdateEmployee();
				formUpdateEmployee.setVisible(true);
			}
		});

		add(create);
		addSeparator();
		add(drop);
		addSeparator();
		add(show);
		addSeparator();
		add(update);
	}
}
