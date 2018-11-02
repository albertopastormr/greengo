package presentation.vehicle.forms;

import business.vehicle.TBicycleVehicle;
import business.vehicle.TCarVehicle;
import presentation.controller.AppController;
import presentation.controller.Event;
import presentation.controller.LightContext;
import presentation.util.Util;
import presentation.util.ViewHelpers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormCreateVehicle extends JDialog {

    /*Attributes*/

    /*JTextField fields*/
    private JTextField cityText;
    private JTextField brandText;
    private JTextField estimatedDurationText;
    private JTextField kmTravelledText;
    private JTextField plateText;
    private JTextField serialNumberText;

    /*JComboBox fields*/
    private JComboBox occupiedComboBox;
    private JComboBox activeComboBox;
    private JComboBox typeComboBox;

    public FormCreateVehicle(){
        setTitle("Create vehicle");
        setResizable(false);
        Util.addEscapeListener(this);
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

        JPanel ret = ViewHelpers.createFieldPanel(9);

        //City
        JLabel cityLabel = new JLabel("City id");
        ret.add(cityLabel);

        cityText = new JTextField(10);
        ret.add(cityText);

        //Brand
        JLabel brandLabel = new JLabel("Brand");
        ret.add(brandLabel);

        brandText = new JTextField(10);
        ret.add(brandText);

        //Estimated duration
        JLabel estimatedDurationLabel = new JLabel("Estimated duration");
        ret.add(estimatedDurationLabel);

        estimatedDurationText = new JTextField(10);
        ret.add(estimatedDurationText);

        //Km travelled
        JLabel kmTravelledLabel = new JLabel("Km travelled");
        ret.add(kmTravelledLabel);

        kmTravelledText = new JTextField(10);
        ret.add(kmTravelledText);

        //occupied
        JLabel occupiedLabel = new JLabel("Occupied");
        ret.add(occupiedLabel);

        occupiedComboBox = ViewHelpers.selectActive();
        ret.add(occupiedComboBox);

        //active
        JLabel activeLabel = new JLabel("Active");
        ret.add(activeLabel);

        activeComboBox = ViewHelpers.selectActive();
        ret.add(activeComboBox);

        //Type
        JLabel typeLabel = new JLabel("Type");
        ret.add(typeLabel);

        selectType();
        ret.add(typeComboBox);

        //plate
        JLabel plateLabel = new JLabel("Plate");
        ret.add(plateLabel);

        plateText = new JTextField(10);
        ret.add(plateText);

        //serial number
        JLabel serialNumberLabel = new JLabel("Serial number");
        ret.add(serialNumberLabel);

        serialNumberText = new JTextField(10);
        ret.add(serialNumberText);

        plateText.setEnabled(true);
        serialNumberText.setEnabled(false);

        return ret;
    }

    private JPanel buttonsPanel(){
        JPanel ret = new JPanel(new FlowLayout());

        JButton create = ViewHelpers.buttonsForms("CREATE");

        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (typeComboBox.getSelectedItem().equals("Car")) {
                        TCarVehicle car = new TCarVehicle();
                        car.setCity(Util.parseNoNegativeInt(cityText.getText()));
                        car.setOccupied(Util.parseActive(occupiedComboBox.getSelectedItem().toString()));
                        car.setActive(Util.parseActive(activeComboBox.getSelectedItem().toString()));
                        car.setEstimatedDuration(Util.parseNoNegativeInt(estimatedDurationText.getText()));
                        car.setBrand(Util.parseString(brandText.getText()));
                        car.setNumKmTravelled(Util.parseNoNegativeInt(kmTravelledText.getText()));
                        car.setPlate(Util.parseString(plateText.getText()));
                        car.setType(Util.parseString(typeComboBox.getSelectedItem().toString()));
                        dispose();
                        AppController.getInstance().execute(new LightContext(Event.CREATE_VEHICLE, car));
                    } else {
                        TBicycleVehicle bicycle = new TBicycleVehicle();
                        bicycle.setCity(Util.parseNoNegativeInt(cityText.getText()));
                        bicycle.setOccupied(Util.parseActive(occupiedComboBox.getSelectedItem().toString()));
                        bicycle.setActive(Util.parseActive(activeComboBox.getSelectedItem().toString()));
                        bicycle.setEstimatedDuration(Util.parseNoNegativeInt(estimatedDurationText.getText()));
                        bicycle.setBrand(Util.parseString(brandText.getText()));
                        bicycle.setNumKmTravelled(Util.parseNoNegativeInt(kmTravelledText.getText()));
                        bicycle.setSerialNumber(Util.parseString(serialNumberText.getText()));
                        bicycle.setType(Util.parseString(typeComboBox.getSelectedItem().toString()));
                        dispose();
                        AppController.getInstance().execute(new LightContext(Event.CREATE_VEHICLE, bicycle));
                    }
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(getRootPane(), e.getMessage(),
                            "ERROR IN CREATE VEHICLE", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton cancel = ViewHelpers.buttonsForms("CANCEL");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        ret.add(create);
        ret.add(cancel);

        return ret;
    }

    private void selectType(){
        typeComboBox = new JComboBox();
        typeComboBox.addItem("Car");
        typeComboBox.addItem("Bicycle");

        typeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(typeComboBox.getSelectedItem().equals("Car")) {
                    plateText.setEnabled(true);
                    serialNumberText.setEnabled(false);
                }
                else{
                    plateText.setEnabled(false);
                    serialNumberText.setEnabled(true);
                }
            }
        });

    }

    public static void main(String[] Args){
        FormCreateVehicle f = new FormCreateVehicle();
        f.setVisible(true);
    }


}
