package presentation;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame{

	public MainWindow() {
		setTitle("GreenGO");
		initGUI();
	}

	private void initGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/images/logo.jpg"));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);

			/*
			PanelTabs tabbedPane = new PanelTabs();
			setContentPane(tabbedPane);
			tabbedPane.actualizarMarca();
			*/

		pack();
		setVisible(true);
	}
}
