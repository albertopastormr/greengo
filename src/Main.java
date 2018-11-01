import presentation.UI;
import presentation.UIimp;

import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		//SwingUtilities.invokeLater(UIimp::new);

		UI.getInstance().execute();
	}
}

