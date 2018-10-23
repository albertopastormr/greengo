package presentation.util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.Date;

public class Util {

	public static void addEscapeListener(final JDialog dialog) {
		ActionListener escListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		};

		dialog.getRootPane().registerKeyboardAction(escListener,
				KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
	}

	public static String parseString(String s){
		if (s == null || s.isEmpty()){
			throw new NullPointerException("Field empty");
		}
		return s;
	}

	public static int parseNoNegativeInt(String s) throws ParseException, NumberFormatException, NullPointerException {
		if (s == null || s.isEmpty()){
			throw new ParseException("Value incorrect", 0);
		}

		int x = Integer.parseInt(s);
		if (x < 0){
			throw new ParseException("Value incorrect", 0);
		}
		return x;
	}

	public static float parseNoNegativeFloat(String s)throws ParseException, NumberFormatException, NullPointerException{
		if (s == null || s.isEmpty()){
			throw new ParseException("Value incorrect", 0);
		}

		float x = Float.parseFloat(s);
		if (x < 0){
			throw new ParseException("Value incorrect", 0);
		}
		return x;
	}

	public static boolean parseActive(String s){
		return s.equals("true");
	}

	public static boolean parseDates(Date d1, Date d2){ //TODO. This method has to return true if d2 > d1.
		return true;
	}

	public static boolean parseDateNull(Date d) throws NullPointerException{
		if(d == null) throw new NullPointerException("Date field is empty");
		else return true;
	}

	public static void inform(String s) {
		JOptionPane.showMessageDialog(null, s);
	}
}