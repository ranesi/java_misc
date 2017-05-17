/**
 * Created by RH Anesi on 4/17/2017.
 *
 * Static class which contains database connection, statement, and result set objects,
 * as well as the methods which operate upon them.
 */

import java.sql.*;

public class Database {

    ///////////STATIC FIELDS/////////////

    private static Connection conn = null;
    private static Statement statement = null;
    private static ResultSet rs = null;

    /////////////////////////////////////

    static ResultSet setup() {
        //Attempt to resolve database
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("Error resolving database.");
            return null;
        }
        //Establish database connection
        try {
            String DB_URL = "jdbc:mysql://localhost:3306/";
            String DB_NAME = "cube_records";
            String USER = "ranesi";
            String PWD = System.getenv("MYSQL_PW");

            conn = DriverManager.getConnection(DB_URL + DB_NAME, USER, PWD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        //Create table; initial database population
        try {
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.execute("DROP TABLE IF EXISTS rubiks_records");

            String createTable = "CREATE TABLE rubiks_records (id INT AUTO_INCREMENT, NAME VARCHAR(255) NOT NULL, TIME FLOAT, PRIMARY KEY(id))";
            statement.execute(createTable);

            //Populate database with some sample entries
            preparedInsert("CubeStormer II", 5.27);
            preparedInsert("Fakhri Raihaan", 27.93);
            preparedInsert("Ruxin Liu", 99.33);
            preparedInsert("Mats Valk", 6.27);

            String selectAll = "SELECT * FROM rubiks_records";
            rs = statement.executeQuery(selectAll);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return rs;
    }

    static ResultSet selectAll() {
        //Creates, returns result set selecting all entries from the Rubik's Records table
        try {
            String selectAll = "SELECT * FROM rubiks_records";
            rs = statement.executeQuery(selectAll);
        } catch (SQLException e) {
            e.printStackTrace();
            rs = null;
        }
        return rs;
    }

    static void insertInto(String name, double time) {
        //Inserts an entry into the Rubik's record database
        //
        //Will always insert towards the "end" of the table
        try {
            rs.moveToInsertRow();
            rs.updateString(2, name);
            rs.updateFloat(3, (float)time);
            rs.insertRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void updateEntry(String name, double time, int rowNumber) {
        //Updates a row (name and time).
        //Operation performed by row number.
        try {
            rs.absolute(rowNumber);
            rs.updateString(2, name);
            rs.updateFloat(3, (float)time);
            rs.updateRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void updateName(String name, int rowNumber) {
        //Updates the name for a specified row in the database.
        //Operation performed by row number.
        try {
            rs.absolute(rowNumber);
            rs.updateString(2, name);
            rs.updateRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void updateTime(double time, int rowNumber) {
        //Updates the time column for the specified row.
        //Operation performed by row number.
        try {
            rs.absolute(rowNumber);
            rs.updateFloat(3, (float)time);
            rs.updateRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void deleteFrom(int rowNumber) {
        //Deletes record from database.
        //Operation performed by row number.
        try {
            rs.absolute(rowNumber);
            rs.deleteRow();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    static void preparedInsert(String name, double time){
        //Insert into DB using prepared statement (used only for initial setup)
        try {
            final String insertSql = "INSERT INTO rubiks_records VALUES (NULL, ?, ?)"; // null = pk; auto_increment=true
            PreparedStatement ps = conn.prepareStatement(insertSql);
            ps.setString(1, name);
            ps.setFloat(2, (float)time);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void shutdown() {
        //Attempts to close the record set, statement, and database connection, respectively
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
