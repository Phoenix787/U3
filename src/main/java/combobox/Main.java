package combobox;

import combobox.dbService.DBService;
import combobox.view.MainWindow;

/**
 * Created by Сергеева on 08.04.2016.
 */
public class Main {
    public static void main(String[] args) {
        new MainWindow(new DBService());
    }
}
