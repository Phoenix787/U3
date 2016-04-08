package swingTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by Сергеева on 04.04.2016.
 */
public class SwingJTableDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    setLookAndFeel(Constants.NIMBUS_LF);
                    createAndShowGUI(args);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static void createAndShowGUI(String[] args) throws Exception {
        File dir;
        if (args.length > 0) {
            dir = new File(args[0]);
        } else{
            dir = new File(System.getProperty("user.home"));
        }
        //create a TableModel object to represent the contents of the directory
        CustomTableModel tableModel = new CustomTableModel(dir);

        //create a JTable and tell it ro display our model
        JTable table = new JTable(tableModel);

        //put the table in a ScrollPane to handle scrolling
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(250, 200));

        JTextField dirPathTestField = new JTextField(26);

        //create an action listener to display the given directory
        JButton displayDirButton = new JButton("Display Direction");
        displayDirButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String path = dirPathTestField.getText();
                if (path != null && !"".equals(path)) {
                    File newDir = new File(path);
                    CustomTableModel newTableModel = new CustomTableModel(newDir);
                    table.setModel(newTableModel);
                } else{
                    JOptionPane.showMessageDialog(null, "Directory path is empty", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel ctrPane = new JPanel();
        ctrPane.add(dirPathTestField);
        ctrPane.add(displayDirButton);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, ctrPane, tableScrollPane);
        splitPane.setDividerLocation(35);
        splitPane.setEnabled(false);

        JFrame frame = new JFrame("Swing JTable Demo");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
