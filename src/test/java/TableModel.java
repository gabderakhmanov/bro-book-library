import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableModel extends AbstractTableModel{
    ArrayList<Object> columnName = new ArrayList<Object>();
    ArrayList<Object> data = new ArrayList<Object>();

    public int getRowCount() {
        return 0;
    }

    public int getColumnCount() {
        return 0;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
}
