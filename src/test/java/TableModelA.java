import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.util.ArrayList;

public class TableModelA extends AbstractTableModel{
    Object[][] tableData;
    String[] columnNames;
    Class[] columnType;

    public TableModelA(Connection conn, String tableName) throws SQLException {
        super();
        getTableData(conn,tableName);
    }

    private void getTableData(Connection conn, String tableName) throws SQLException {
        DatabaseMetaData meta = conn.getMetaData();//get metadata
        ResultSet rs = meta.getColumns(null,null,tableName,null);//get name columns from table data

        ArrayList nameList = new ArrayList();// name column
        ArrayList typeList = new ArrayList();//type column

        while(rs.next()){
            nameList.add(rs.getString("COLUMN_NAME"));//add name column

            int typeColumn=rs.getInt("DATA_TYPE");//detect type column

            //choose type column
            switch (typeColumn){
                case Types.INTEGER:
                    typeList.add(Integer.class);
                    break;
                case Types.FLOAT:
                    typeList.add(Float.class);
                    break;
                case Types.DOUBLE:
                    typeList.add(Double.class);
                    break;
                case Types.DATE:
                case Types.TIME:
                    typeList.add(java.sql.Date.class);
                    break;
                default:
                    typeList.add(String.class);
            }
        }

        //name column save into array
        columnNames = new String[nameList.size()];
        nameList.toArray(columnNames);

        //type column save into array
        columnType = new Class[typeList.size()];
        typeList.toArray(columnType);

        Statement statement = conn.createStatement();
        rs = statement.executeQuery("SELECT * FROM " + tableName);

        ArrayList rowList = new ArrayList();//keep data from db

        while (rs.next()){
            ArrayList cellList = new ArrayList();//break into columns

            //split into records
            for (int i = 0;i < columnType.length;i++){
                Object cellValue = null;

                if(columnType[i] == String.class) cellValue = rs.getString(columnNames[i]);
                else if (columnType[i] == Integer.class) cellValue = new Integer(rs.getInt(columnNames[i]));
                else if (columnType[i] == Float.class) cellValue = new Float(rs.getFloat(columnNames[i]));
                else if (columnType[i] == Double.class) cellValue = new Double(rs.getDouble(columnNames[i]));
                else if (columnType[i] == java.sql.Date.class) cellValue = rs.getDate(columnNames[i]);
                else System.out.println("Don`t type field" + columnNames[i]);

                cellList.add(cellValue);
            }

            Object cells = cellList.toArray();
            rowList.add(cells);
        }

        tableData = new Object[rowList.size()][];

        for(int i =0; i < tableData.length;i++){
            tableData[i] = (Object[]) rowList.get(i);
        }

        if(rs != null) rs.close();
        if(statement != null) statement.close();
    }

    @Override
    public int getRowCount() {
        return tableData.length;
    }

    @Override
    public int getColumnCount() {
        if(tableData.length ==0){
        return 0;
        }else return tableData[0].length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return tableData[rowIndex][columnIndex];
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return columnType[columnIndex];
    }

    @Override
    public String getColumnName(int column){
        return columnNames[column];
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

}
