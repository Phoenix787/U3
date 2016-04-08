package combobox.dbService;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Сергеева on 07.04.2016.
 * нужно реализовать модель и рендер
 */
public class DatabaseComboboxModel<E> extends DefaultComboBoxModel<E> {
    //получение данных из запроса ResultSet
    public void setDataSource(Set<E> rs) throws SQLException{
        Iterator<E> iterator = rs.iterator();
        //очистим список
        removeAllElements();
        //добавим новые элементы из базы данных
        while (iterator.hasNext()) {
            addElement(iterator.next());
        }
    }
}
