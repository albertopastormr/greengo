package presentation.contract;

import presentation.PanelTabs;
import presentation.contract.forms.FormCreateContract;
import presentation.contract.forms.FormDropContract;
import presentation.contract.forms.FormShowContract;
import presentation.contract.forms.FormUpdateContract;
import presentation.util.ViewHelpers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContractToolbar extends JToolBar{

	public ContractToolbar(PanelTabs panelTabs){
		initGUI(panelTabs);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setFloatable(false);
		setRollover(true);
	}

	private void initGUI(PanelTabs panelTabs) {
		// Create contract
		JButton create = ViewHelpers.buttonsForms("Create contract");
		create.setToolTipText("Create contract");
		create.setVerticalTextPosition(SwingConstants.BOTTOM);
		create.setHorizontalTextPosition(SwingConstants.CENTER);
		create.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {
				FormCreateContract formCreateContract = new FormCreateContract();
				formCreateContract.setVisible(true);
			}
		});

		// Drop contract
		JButton drop = ViewHelpers.buttonsForms("Drop contract");
		drop.setToolTipText("Drop contract");
		drop.setVerticalTextPosition(SwingConstants.BOTTOM);
		drop.setHorizontalTextPosition(SwingConstants.CENTER);
		drop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FormDropContract formDropContract = new FormDropContract();
				formDropContract.setVisible(true);
			}
		});

		// Show contract
		JButton show = ViewHelpers.buttonsForms("Show contract");
		show.setToolTipText("Show contract");
		show.setVerticalTextPosition(SwingConstants.BOTTOM);
		show.setHorizontalTextPosition(SwingConstants.CENTER);
		show.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FormShowContract formShowContract = new FormShowContract();
				formShowContract.setVisible(true);
			}
		});

		// Update contract
		JButton update = ViewHelpers.buttonsForms("Update contract");
		update.setToolTipText("Update contract");
		update.setVerticalTextPosition(SwingConstants.BOTTOM);
		update.setHorizontalTextPosition(SwingConstants.CENTER);
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FormUpdateContract formUpdateContract = new FormUpdateContract();
				formUpdateContract.setVisible(true);
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
