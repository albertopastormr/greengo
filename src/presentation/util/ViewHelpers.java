package presentation.util;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewHelpers {

	public static JComboBox selectActive() {

		JComboBox activeComboBox = new JComboBox();
		activeComboBox.addItem("true");
		activeComboBox.addItem("false");

		return activeComboBox;
	}

	public static JButton buttonsForms(String tittle){
		JButton button = new JButton(tittle);
		button.setForeground(Color.white);
		button.setBackground(new Color(0, 204, 102));
		button.setBorder(new CompoundBorder(new LineBorder( new Color(51, 153, 51)), new EmptyBorder(5, 15, 5, 15)));

		MouseAdapter mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent m) {
				button.setBackground(Color.lightGray);
			}
			@Override
			public void mouseExited(MouseEvent m) {
				button.setBackground(new Color(0, 204, 102));
			}
		};
		button.addMouseListener(mouseAdapter);

		return button;
	}

	public static JPanel createFieldPanel(int column){
		JPanel panel = new JPanel(new GridLayout(column, 2, 0, 7));
		Border border = panel.getBorder();
		Border margin = new EmptyBorder(10, 10, 10, 10);
		panel.setBorder(new CompoundBorder(border, margin));

		return  panel;
	}

	public static JDialog createSpecificTable(TableModel model){

		JPanel jPanel = new JPanel(new BorderLayout());
		jPanel.add(new TablePanel<>(model), BorderLayout.CENTER);

		JDialog jDialog = new JDialog();
		jDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		jDialog.add(jPanel);
		jDialog.pack();
		jDialog.setVisible(true);
		jDialog.setLocationRelativeTo(null);

		return jDialog;
	}

    public static JDialog createShowWindow(String data, String title){
        JDialog dialog = new JDialog();
        dialog.setTitle(title);
        JPanel panel = new JPanel(new BorderLayout());

        String[] texts = data.split("\n");

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));

        //add the attributes to the labelPanel
        for(int i = 0; i < texts.length; i++){
            JLabel vel = new JLabel(texts[i]);
            vel.setFont(new Font("Monospaced", Font.BOLD, 15));
            labelPanel.add(vel);
        }

        //Empty border
        labelPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton closeButton = buttonsForms("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        buttonPanel.add(closeButton);

        //Add the non-main panels to the main one.
        panel.add(labelPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setContentPane(panel);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        dialog.pack();
        dialog.setLocationRelativeTo(null);

        return dialog;
    }
}
