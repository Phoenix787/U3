package combobox.dbService;

import org.hibernate.HibernateException;

/**
 * Created by Сергеева on 08.04.2016.
 */
public class DBException extends Exception {
    public DBException(Exception e) {
        System.err.println("Ошибка базы данных:  " + e);
    }
}
