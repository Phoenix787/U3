package datepicker;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Сергеева on 06.04.2016.
 */
public class DatePickerDemo {
    public static void main(String[] args) {
        createAndShowGUI();
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("DatePicker Demo");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        UtilDateModel model = new UtilDateModel();
        model.setDate(1990,8,24);
        model.setSelected(true);
        Properties properties = new Properties();
        properties.put("text.today", "Сегодня");
        properties.put("text.month", "Месяц");
        properties.put("text.year", "Год");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());

        Date selectedDate = (Date) datePicker.getModel().getValue();

        frame.add(datePicker);

        frame.setSize(200, 100);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
