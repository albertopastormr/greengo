package presentation.vehicle.forms;

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

public class FormShowVehicle extends JDialog {
    /*Attributes*/

    private JTextField idText;

    public FormShowVehicle(){
        setTitle("Show vehicle");
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
        JPanel ret = ViewHelpers.createFieldPanel(1);

        //id
        JLabel idLabel = new JLabel("Id");
        ret.add(idLabel);

        idText = new JTextField(10);
        ret.add(idText);

        return ret;
    }

    private JPanel buttonsPanel() {
        JPanel ret = new JPanel(new FlowLayout());

        JButton drop = ViewHelpers.buttonsForms("SHOW");

        drop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TVehicle v = new TVehicle();
                try{
                    v.setId(Util.parseNoNegativeInt(idText.getText()));
                    AppController.getInstance().execute(new LightContext(Event.SHOW_VEHICLE, v));
                    dispose();
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(getRootPane(), e.getMessage(), "ERROR IN SHOW VEHICLE",
                            JOptionPane.ERROR_MESSAGE);
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

        ret.add(drop);
        ret.add(cancel);

        return ret;
    }

    public static void main(String[] Args){
        FormShowVehicle f = new FormShowVehicle();
        f.setVisible(true);
    }
}
