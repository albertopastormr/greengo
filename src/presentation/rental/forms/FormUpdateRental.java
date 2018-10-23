package presentation.rental.forms;

import business.rental.TRental;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import presentation.util.Util;

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
		setTitle("Modificar alquiler");
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
		JPanel ret = new JPanel(new GridLayout(5, 2, 0, 7));
		Border border = ret.getBorder();
		Border margin = new EmptyBorder(10, 10, 10, 10);
		ret.setBorder(new CompoundBorder(border, margin));

		//Id
		JLabel idLabel = new JLabel("Id");
		ret.add(idLabel);

		idText = new JTextField(10);
		ret.add(idText);

		//Km rented
		JLabel kmRentedLabel = new JLabel("Km a recorrer");
		ret.add(kmRentedLabel);

		kmRentedText = new JTextField(10);
		ret.add(kmRentedText);

		//Active
		JLabel activeLabel = new JLabel("Activo");
		ret.add(activeLabel);

		selectActive();

		ret.add(activeComboBox);

		//Date from
		JLabel dateFromLabel = new JLabel("Desde");
		ret.add(dateFromLabel);

		JDatePanelImpl dateFromPicker = createDatePanel();
		dateFrom = new JDatePickerImpl(dateFromPicker, new DateLabelFormatter());

		ret.add(dateFrom);

		//Date To
		JLabel dateToLabel = new JLabel("Hasta");
		ret.add(dateToLabel);

		JDatePanelImpl dateToPicker = createDatePanel();
		dateTo = new JDatePickerImpl(dateToPicker, new DateLabelFormatter());

		ret.add(dateTo);

		return ret;
	}

	private JPanel buttonsPanel(){
		JPanel ret = new JPanel(new FlowLayout());

		JButton update = new JButton("MODIFICAR");
		update.setForeground(Color.white);
		update.setBackground(new Color(119,171,89));

		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				TRental rent = new TRental();
				try{
					rent.setId(Util.parseNoNegativeInt(idText.getText()));
					rent.setKm_rented(Util.parseNoNegativeInt(kmRentedText.getText()));
					rent.setActive(Util.parseActive(activeComboBox.getSelectedItem().toString()));

					Date dateFromToCheck = (Date)dateFrom.getModel().getValue();
					Date dateToToCheck = (Date)dateTo.getModel().getValue();
					if(Util.parseDateNull(dateFromToCheck) && Util.parseDateNull(dateToToCheck)
							&& Util.parseDates(dateFromToCheck, dateToToCheck)){

						rent.setDateFrom(dateFromToCheck);
						rent.setDateTo(dateToToCheck);
					}
					dispose();
					//Controller invokes "Update Rental" operation.

				}
				catch(Exception exc){
					JOptionPane.showMessageDialog(getRootPane(), exc.getMessage(),
							"ERROR MODIFICAR ALQUILER", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton cancel = new JButton("CANCELAR");
		cancel.setForeground(Color.white);
		cancel.setBackground(new Color(119,171,89));

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

	private void selectActive(){
		activeComboBox = new JComboBox();
		activeComboBox.addItem("True");
		activeComboBox.addItem("False");
	}

	public static void main(String[] Args){
		FormUpdateRental f = new FormUpdateRental();
		f.setVisible(true);
	}

}
