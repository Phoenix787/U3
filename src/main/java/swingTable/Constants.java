package swingTable;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Date;

/**
 * Created by Сергеева on 04.04.2016.
 */
public class Constants {
    //здесь перечисляются классы которые присваиваются колонкам,
    // т. е. какой тип данных будет отображаться в колонках объекта таблица!!!
    public static final Class<?>[] COLUMN_CLASSES = new Class[]{
            String.class, Long.class, Date.class, Boolean.class,
            Boolean.class, Boolean.class};
    public static final String NIMBUS_LF = "Nimbus";
}
