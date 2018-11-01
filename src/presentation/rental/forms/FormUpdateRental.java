package presentation.rental.forms;

import business.rental.TRental;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import presentation.controller.AppController;
import presentation.controller.Event;
import presentation.controller.LightContext;
import presentation.util.Util;
import presentation.util.ViewHelpers;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Properties;

public class FormUpdateRental extends JDialog {

	/*Attributes*/

	/*JTextField fields*/
	private JTextField idText;
	private JTextField kmRentedText;

	/*JComboBox fields*/
	private JComboBox activeComboBox;

	/*Date*/
	private JDatePickerImpl dateFrom;
	private JDatePickerImpl dateTo;

	public FormUpdateRental(){
		setTitle("Update rental");
		Util.addEscapeListener(this);
		setResizable(false);
		initGUI();

	}

	private void initGUI(){
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		mainPanel.add(fieldsPanel());
		mainPanel.add(buttonsPanel());

		add(mainPanel);
		pack();
		setLocationRelativeTo(null);
	}

	private JPanel fieldsPanel(){
		JPanel ret = ViewHelpers.createFieldPanel(5);

		//Id
		JLabel idLabel = new JLabel("Id");
		ret.add(idLabel);

		idText = new JTextField(10);
		ret.add(idText);

		//Km rented
		JLabel kmRentedLabel = new JLabel("Km to travel");
		ret.add(kmRentedLabel);

		kmRentedText = new JTextField(10);
		ret.add(kmRentedText);

		//Active
		JLabel activeLabel = new JLabel("Active");
		ret.add(activeLabel);

		activeComboBox = ViewHelpers.selectActive();
		ret.add(activeComboBox);

		//Date from
		JLabel dateFromLabel = new JLabel("From");
		ret.add(dateFromLabel);

		JDatePanelImpl dateFromPicker = createDatePanel();
		dateFrom = new JDatePickerImpl(dateFromPicker, new DateLabelFormatter());

		ret.add(dateFrom);

		//Date To
		JLabel dateToLabel = new JLabel("To");
		ret.add(dateToLabel);

		JDatePanelImpl dateToPicker = createDatePanel();
		dateTo = new JDatePickerImpl(dateToPicker, new DateLabelFormatter());

		ret.add(dateTo);

		return ret;
	}

	private JPanel buttonsPanel(){
		JPanel ret = new JPanel(new FlowLayout());

		JButton update = ViewHelpers.buttonsForms("UPDATE");

		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				TRental rent = new TRental();
				try{
					rent.setId(Util.parseNoNegativeInt(idText.getText()));
					rent.setNumKmRented(Util.parseNoNegativeInt(kmRentedText.getText()));
					rent.setActive(Util.parseActive(activeComboBox.getSelectedItem().toString()));

					Date dateFromToCheck = (Date)dateFrom.getModel().getValue();
					Date dateToToCheck = (Date)dateTo.getModel().getValue();
					if(Util.parseDateNull(dateFromToCheck) && Util.parseDateNull(dateToToCheck)
							&& Util.parseDates(dateFromToCheck, dateToToCheck)){

						rent.setDateFrom(dateFromToCheck);
						rent.setDateTo(dateToToCheck);
						dispose();
						AppController.getInstance().execute(new LightContext(Event.UPDATE_RENTAL, rent));
					}
				}
				catch(Exception exc){
					JOptionPane.showMessageDialog(getRootPane(), exc.getMessage(),
							"ERROR IN UPDATE RENTAL", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton cancel = ViewHelpers.buttonsForms("CANCEL");

		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				dispose();
			}
		});

		ret.add(update);
		ret.add(cancel);

		return ret;
	}

	private JDatePanelImpl createDatePanel(){
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);

		return datePanel;
	}

	public static void main(String[] Args){
		FormUpdateRental f = new FormUpdateRental();
		f.setVisible(true);
	}

}
