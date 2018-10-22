package presentation.util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;

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

	public static String parseaString(String s){
		if (s == null || s.isEmpty()){
			throw new NullPointerException("Field empty");
		}
		return s;
	}

	public static int parseaIntNoNegativo(String s) throws ParseException, NumberFormatException, NullPointerException {
		if (s == null || s.isEmpty()){
			throw new ParseException("Value incorrect", 0);
		}

		int x = Integer.parseInt(s);
		if (x < 0){
			throw new ParseException("Value incorrect", 0);
		}
		return x;
	}

	public static float parseaFloatNoNegativo(String s)throws ParseException, NumberFormatException, NullPointerException{
		if (s == null || s.isEmpty()){
			throw new ParseException("Value incorrect", 0);
		}

		float x = Float.parseFloat(s);
		if (x < 0){
			throw new ParseException("Value incorrect", 0);
		}
		return x;
	}

	public static boolean parseaActiva(String s){
		return s.equals("true");
	}

	public static void informar(String s) {
		JOptionPane.showMessageDialog(null, s);
	}
}