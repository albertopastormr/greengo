package presentation.vehicle.forms;

import business.vehicle.TBicycleVehicle;
import business.vehicle.TCarVehicle;
import business.vehicle.TVehicle;
import presentation.controller.AppController;
import presentation.controller.Event;
import presentation.controller.LightContext;
import presentation.util.Util;
import presentation.util.ViewHelpers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormUpdateVehicle extends JDialog{

    /*Attributes*/

    /*JTextField fields*/
    private JTextField idText;
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

    public FormUpdateVehicle(){
        setTitle("Update vehicle");
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

        JPanel ret = ViewHelpers.createFieldPanel(6);

        //Id
        JLabel idLabel = new JLabel("Id");
        ret.add(idLabel);

        idText = new JTextField(10);
        ret.add(idText);

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

        //Type
        /*JLabel typeLabel = new JLabel("Type");
        ret.add(typeLabel);

        selectType();
        ret.add(typeComboBox);*/

        /*//plate
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
        */
        return ret;
    }

    private JPanel buttonsPanel(){
        JPanel ret = new JPanel(new FlowLayout());

        JButton update = ViewHelpers.buttonsForms("UPDATE");

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    TVehicle vehicle = new TVehicle();
                    vehicle.setId(Util.parseNoNegativeInt(idText.getText()));
                    vehicle.setCity(Util.parseNoNegativeInt(cityText.getText()));
                    vehicle.setOccupied(Util.parseActive(occupiedComboBox.getSelectedItem().toString()));
                    vehicle.setEstimatedDuration(Util.parseNoNegativeInt(estimatedDurationText.getText()));
                    vehicle.setBrand(Util.parseString(brandText.getText()));
                    vehicle.setActive(true);
                    vehicle.setNumKmTravelled(Util.parseNoNegativeInt(kmTravelledText.getText()));
                    AppController.getInstance().execute(new LightContext(Event.UPDATE_VEHICLE, vehicle));
                    dispose();
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(getRootPane(), e.getMessage(), "ERROR IN UPDATE VEHICLE", JOptionPane.ERROR_MESSAGE);
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

        ret.add(update);
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
        typeComboBox.setToolTipText("You must set the actual type of the vehicle that you want to update.");

    }

    public static void main(String[] Args){
        FormUpdateVehicle f = new FormUpdateVehicle();
        f.setVisible(true);
    }


}
