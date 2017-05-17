import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.AbstractTableModel;

/**
 * Created by RH Anesi on 4/17/2017.
 */
public class RubiksModel extends AbstractTableModel {

    ResultSet resultSet;
    int numberOfRows;
    int numberOfColumns;

    RubiksModel(ResultSet rs) {
        this.resultSet = rs;

        try {
            numberOfRows = 0;
            while (resultSet.next()) {
                numberOfRows++;
            }

            numberOfColumns = resultSet.getMetaData().getColumnCount();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getRowCount() {
        return numberOfRows;
    }

    @Override
    public int getColumnCount() {
        return numberOfColumns;
    }

    @Override
    public Object getValueAt(int row, int col) {
        //Attempts to retrieve object at location (row, col)
        try {
            resultSet.absolute(row + 1);
            Object o = resultSet.getObject(col + 1);
            return o;
        } catch (SQLException e) {
            return "ERROR! Unable to fetch record at location " + row + ", " + col;
        }
    }
}
