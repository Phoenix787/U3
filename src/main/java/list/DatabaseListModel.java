package list;

import javax.swing.*;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Сергеева on 07.04.2016.
 */
public class DatabaseListModel extends AbstractListModel {
    //здесь мы будем хранить данные
    private final ArrayList<String> data = new ArrayList<String>();



    /**
     * загрузка из базы данных
     * @param rs - результат запроса к базе данных
     * @param column - название столбца, данные которого будут использованы для заполнения списка.
     *  для оповещения всех слушателей используем методы fireXXX(), каждый из которых соообщает о некотором событии:
     *               - обновление элементов списка
     *               - добавление в список новых данных
     *               - удаление данных из списка.
     */
    public void setDataSource(ResultSet rs, String column) {
        try {
            //получаем данные
            data.clear();
            while (rs.next()) {
                synchronized (data) {
                    data.add(rs.getString(column));
                }
                //оповещаем виды (если они есть) об обновлении всего списка.
                fireIntervalAdded(this, 0, data.size());
            }
            rs.close();
            fireContentsChanged(this, 0, data.size());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//методы модели для выдачи данных списку
    @Override
    public int getSize() {
        synchronized (data){
            return data.size();
        }

    }

    @Override
    public Object getElementAt(int index) {
        synchronized (data){
            return data.get(index);
        }
    }
}
