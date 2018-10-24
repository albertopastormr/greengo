package presentation.util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;

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

	public static boolean parseDates(Date d1, Date d2) throws ParseException { //Return true id d2 > d1.
        GregorianCalendar gc1 = new GregorianCalendar();
        GregorianCalendar gc2 = new GregorianCalendar();

        gc1.setTimeInMillis(d1.getTime());
        gc2.setTimeInMillis(d2.getTime());

        /*We know that GregorianCalendar has an equal method, but this operation takes in care the minutes and hour, and we don't want to be that precise*/

        int day1 = gc1.get(GregorianCalendar.DAY_OF_MONTH);
        int year1 = gc1.get(GregorianCalendar.YEAR);
        int month1 = gc1.get(GregorianCalendar.MONTH);

        int day2 = gc2.get(GregorianCalendar.DAY_OF_MONTH);
        int year2 = gc2.get(GregorianCalendar.YEAR);
        int month2 = gc2.get(GregorianCalendar.MONTH);


		if((day1 == day2 && month1 == month2 && year1 == year2) ||
                (year2 < year1 || month2 < month1 ||
                        ( (year1 == year2 && month1 == month2) && day2 < day1)))throw new ParseException("Fail in date", 0);
		else return true;
	}

	public static boolean parseDateNull(Date d) throws NullPointerException{
		if(d == null) throw new NullPointerException("Date field is empty");
		else return true;
	}

	public static void inform(String s) {
		JOptionPane.showMessageDialog(null, s);
	}
}