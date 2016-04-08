package swingList;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import static javax.swing.UIManager.setLookAndFeel;

/**
 * Created by Сергеева on 04.04.2016.
 */
public class SwingJListDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                    setLookAndFeel(Constants.NIMBUS_LF);
                    createAndShowGUI();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static void createAndShowGUI() throws Exception{
        JFrame frame = new JFrame("Swing JList Demo");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //create JList with a List of String names of employee
        SwingJList<String> swingJList = new SwingJList<>(Arrays.asList(Constants.LIST_DATA));

        JTextField nameField = new JTextField(26);

        //Adding a list selection listener
        swingJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedName = swingJList.getSelectedValue();
                    nameField.setText(selectedName);
                }
            }
        });

        //create an action listener to add a new item to a list
        JButton addButton = new JButton("Add Employee");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                if (name != null && !"".equals(name)) {
                    swingJList.addElement(name);
                } else{
                    JOptionPane.showMessageDialog(null,
                            "Employee name is empty",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //create an action listener to remove existed item from a list
        JButton deleteButton = new JButton("Delete Employee");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                if (name != null && !"".equals(name)) {
                    swingJList.removeElement(name);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Please select employee name from the list",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //Put the lists in a ScrollPane to handle scrolling
        JScrollPane listScrolling = new JScrollPane(swingJList);
        listScrolling.setPreferredSize(new Dimension(250, 200));

        listScrolling.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Employee List",
                TitledBorder.CENTER, TitledBorder.TOP
        ));

        //create control panel
        JPanel buttonPane = new JPanel();
        buttonPane.add(nameField);
        buttonPane.add(addButton);
        buttonPane.add(deleteButton);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listScrolling, buttonPane);
        splitPane.setDividerLocation(250);
        splitPane.setEnabled(false);

        frame.add(splitPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void setLookAndFeel(String lf) throws Exception {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (lf.equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

        }catch(Exception e){
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
    }
}
