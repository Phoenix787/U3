package combobox.view;

import combobox.dbService.DatabaseComboboxModel;
import combobox.datasets.Category;
import combobox.dbService.DBException;
import combobox.dbService.DBService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by Сергеева on 08.04.2016.
 */
public class MainWindow extends JFrame {
    private DBService dbService;

    public MainWindow(DBService dbService) {
        this.dbService = dbService;
        try {
            setLookAndFeel("Nimbus");
            createAndShowGUI();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void createAndShowGUI() throws DBException, SQLException {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Demo combobox from database");

        JPanel panel = new JPanel();
        DatabaseComboboxModel<Category> dcm = new DatabaseComboboxModel<>();
        dcm.setDataSource(dbService.getCategories());

        JComboBox<Category> comboBox = new JComboBox<>(dcm);
        comboBox.setMaximumRowCount(10);
//        comboBox.setRenderer();

        JTextField textField = new JTextField(15);
        JLabel idCat = new JLabel("Id: ");
        JLabel nameCat = new JLabel("Name: ");


        JButton btnAdd = new JButton("Добавить категорию");
        btnAdd.addActionListener(e -> {
            String name1 = textField.getText();
            if (name1 != null && !"".equals(name1)){
                try {
                    dbService.addCategory(new Category(name1));
                    dcm.setDataSource(dbService.getCategories());
                    comboBox.setModel(dcm);
                } catch (DBException | SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Category category = (Category)((JComboBox)e.getSource()).getSelectedItem();
                idCat.setText("" + category.getId());
                nameCat.setText(category.getName());
            }
        });


        panel.add(comboBox);
        panel.add(textField);
        panel.add(btnAdd);
        panel.add(idCat);
        panel.add(nameCat);

        getContentPane().add(panel);

        setSize(500, 200);
        setVisible(true);

    }


    private static void setLookAndFeel(String lf) throws Exception {
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
