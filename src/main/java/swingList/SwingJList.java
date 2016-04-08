package swingList;

import javax.swing.*;
import java.util.List;

/**
 * Created by Сергеева on 04.04.2016.
 */
public class SwingJList<T> extends JList<T> {
    public SwingJList(List<T> listData) {
        super(new DefaultListModel<T>());
        listData.forEach(element->addElement(element));
    }

    public void addElement(T element) {
        ((DefaultListModel<T>) getModel()).add(Constants.NEW_ELEMENT_IDX, element);
    }

    public void removeElement(Object element) {
        ((DefaultListModel<T>) getModel()).removeElement(element);
    }

}
