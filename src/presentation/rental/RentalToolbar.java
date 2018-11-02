package presentation.rental;

import presentation.PanelTabs;
import presentation.rental.forms.FormCreateRental;
import presentation.rental.forms.FormDropRental;
import presentation.rental.forms.FormShowRental;
import presentation.rental.forms.FormUpdateRental;
import presentation.util.ViewHelpers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RentalToolbar extends JToolBar {

	public RentalToolbar(PanelTabs panelTabs) {
		initGUI(panelTabs);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setFloatable(false);
		setRollover(true);
		setBackground(new Color(240, 240, 240));
	}

	private void initGUI(PanelTabs panelTabs) {
		// Create rental
		JButton create = ViewHelpers.buttonsForms("Create rental");
		create.setToolTipText("Create rental");
		create.setIcon(new ImageIcon("resources/images/add.png"));
		create.setVerticalTextPosition(SwingConstants.BOTTOM);
		create.setHorizontalTextPosition(SwingConstants.CENTER);
		create.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FormCreateRental formCreateRental = new FormCreateRental();
				formCreateRental.setVisible(true);
			}
		});

		// Drop rental
		JButton drop = ViewHelpers.buttonsForms("Drop rental");
		drop.setToolTipText("Drop rental");
		drop.setIcon(new ImageIcon("resources/images/drop.png"));
		drop.setVerticalTextPosition(SwingConstants.BOTTOM);
		drop.setHorizontalTextPosition(SwingConstants.CENTER);
		drop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FormDropRental formDropRental = new FormDropRental();
				formDropRental.setVisible(true);
			}
		});

		// Show rental
		JButton show = ViewHelpers.buttonsForms("Show rental");
		show.setToolTipText("Show rental");
		show.setIcon(new ImageIcon("resources/images/show.png"));
		show.setVerticalTextPosition(SwingConstants.BOTTOM);
		show.setHorizontalTextPosition(SwingConstants.CENTER);
		show.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FormShowRental formShowRental = new FormShowRental();
				formShowRental.setVisible(true);
			}
		});

		// Update rental
		JButton update = ViewHelpers.buttonsForms("Update rental");
		update.setToolTipText("Update rental");
		update.setIcon(new ImageIcon("resources/images/update.png"));
		update.setVerticalTextPosition(SwingConstants.BOTTOM);
		update.setHorizontalTextPosition(SwingConstants.CENTER);
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FormUpdateRental formUpdateRental = new FormUpdateRental();
				formUpdateRental.setVisible(true);
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
