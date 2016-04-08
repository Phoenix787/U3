package swingTable;

import javax.swing.table.AbstractTableModel;

import java.io.File;
import java.util.Date;

/**
 * Created by Сергеева on 04.04.2016.
 * The Class Constants contains some constants which will be used across the
 * code.
 */
public class CustomTableModel extends AbstractTableModel {
    private File dir;
    private String[] filenames;
    private String[] columnNames = TableColumn.getNames();
    private Class<?>[] columnClasses = Constants.COLUMN_CLASSES;

    public CustomTableModel(File dir) {
        this.dir = dir;
        this.filenames = dir.list();
    }

    @Override
    //Returns the number of files in directory
    public int getRowCount() {
        return filenames.length;
    }

    @Override
    // Returns a constant columns number for this model
    public int getColumnCount() {
        return Constants.COLUMN_CLASSES.length;
    }

    @Override
    //Returns the value of each cell
    public Object getValueAt(int rowIndex, int columnIndex) {
        File f = new File(dir, filenames[rowIndex]);
        TableColumn tableColumn = TableColumn.fromIndex(columnIndex);
        switch (tableColumn) {
            case NAME:
                return filenames[rowIndex];
            case SIZE:
                return f.length();
            case LAST_MODIFIED:
                return new Date(f.lastModified());
            case DIRECTORY:
                return f.isDirectory() ? Boolean.TRUE : Boolean.FALSE;
            case READABLE:
                return f.canRead() ? Boolean.TRUE : Boolean.FALSE;
            case WRITABLE:
                return f.canWrite() ? Boolean.TRUE : Boolean.FALSE;
            default:
                return null;
        }
    }

    // Returns the name of the given column index
    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Class<?> getColumnClasses(int col) {
        return columnClasses[col];
    }
}
