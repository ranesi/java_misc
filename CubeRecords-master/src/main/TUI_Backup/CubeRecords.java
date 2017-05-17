/**
 * Created by RH Anesi on 4/6/2017.
 */
import java.io.*;
import java.sql.*;

public class CubeRecords {

    static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/cube_records"; // the db is called cube_records in for future cube-related records


    static final String MURDER_TABLE =
            "DROP TABLE rubiks_records";

    public static void main(String[] args) {

        //Retrieve username/password for MySQL from external file
        String[] procFile = getUserPassword();
        String user = procFile[0], pwd = procFile[1];

        try {

            Class.forName(DRIVER);

            try (Connection conn = DriverManager.getConnection(DB_URL, user, pwd)) {

                Statement sqlCmd = conn.createStatement();

                // Initialize database, populate with predefined entries
                initRubikTable(sqlCmd);
                populateRubikTable(conn);

                label:
                while (true) {
                    String cmd = Input.stringInput(UI.mainMenu()).toUpperCase();
                    switch (cmd) {
                        case "A":
                            // Add entry to the database
                            String newName = Input.stringInput("Please enter the record holder's name: ");
                            float newTime = Input.floatInput("Please enter the record holder's time: ");
                            insertInto(newName, newTime, conn);
                            break;
                        case "D":
                            ResultSet rs = sqlCmd.executeQuery("SELECT * FROM rubiks_records");
                            printResultsToConsole(rs);
                            rs.close();
                            break;
                        case "S":
                            // Search entries
                            String search = Input.stringInput("Enter name to search: ");
                            searchByName(search, conn);
                            break;
                        case "U":
                            // Update entry
                            String updateName = Input.stringInput("Enter name of competitor (exact): ");
                            float updateTime = Input.floatInput("Enter new time: ");
                            updateEntry(updateName, updateTime, conn);
                            break;
                        case "Q":
                            //Quit the program
                            break label;
                        default:
                            System.out.println("Please enter a valid command!");
                            break;
                    }

                }
                sqlCmd.execute(MURDER_TABLE);
                sqlCmd.close();
                conn.close();
                System.exit(0);

                } catch (Exception e) {
                    System.out.println("SOMETHING TERRIBLE HAS HAPPENED");
                }
            } catch (ClassNotFoundException cnfe) {
                System.out.println("Driver issue!");
            }

        System.exit(1);
    }

    public static void searchByName(String searchString, Connection conn) {
        String search = "%" + searchString + "%";
        try {
            String searchSql = "SELECT * FROM rubiks_records WHERE name LIKE ?";
            PreparedStatement ps = conn.prepareStatement(searchSql);

            ps.setString(1, search);
            ResultSet rs = ps.executeQuery();
            printResultsToConsole(rs);

            rs.close();
            ps.close();
        } catch (SQLException se) {
            System.out.println("Error searching by name.");
        }
    }

    public static void updateEntry(String name, float time, Connection conn) {
        try {
            String update = "UPDATE rubiks_records SET time_taken = ? WHERE name = ?";
            PreparedStatement ps = conn.prepareStatement(update);

            ps.setFloat(1, time);
            ps.setString(2, name);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException se) {
            System.out.println("Error performing update operation.");
        }
    }

    public static void insertInto(String name, float time, Connection conn) {
        try {
            String insertSql = "INSERT INTO rubiks_records VALUES (UUID(), ?, ? )";
            PreparedStatement ps = conn.prepareStatement(insertSql);

            ps.setString(1, name);
            ps.setFloat(2, time);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException se) {
            System.out.printf("Error creating new field.");
        }
    }

    public static void printResultsToConsole(ResultSet rs) throws SQLException{
        while (rs.next()) {
            String toConsole = String.format("%s:\n%.2f seconds" + UI.hr(),
                    rs.getString(1),
                    rs.getFloat(2));
            System.out.println(toConsole);
        }
    }

    public static void initRubikTable(Statement s) throws SQLException {
        String DROP_TABLE = "DROP TABLE IF EXISTS rubiks_records";
        String DEFINE_TABLE =
                "CREATE TABLE rubiks_records (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(255), time_taken FLOAT, PRIMARY KEY(id))";
        s.execute(DROP_TABLE);
        s.execute(DEFINE_TABLE);
    }

    public static void populateRubikTable(Connection conn) {
        // populate the table with some values for demonstration purposes
        insertInto("Cubestormer II", 5.270f, conn);
        insertInto("Fakhri Raihaan", 27.93f, conn);
        insertInto("Ruxin Liu", 99.33f, conn);
        insertInto("Mats Valk", 6.27f, conn);
    }

    public static String[] getUserPassword(){
        String filename = System.getProperty("user.dir") + "\\info.txt";
        String ret = "";
        try (BufferedReader bw = new BufferedReader(new FileReader(new File(filename)))) {
            String line = bw.readLine();
            while (line != null) {
                ret += line + ";";
                line = bw.readLine();
            }
            bw.close();
        } catch (IOException ioe) {
            System.out.println("Cannot find " + filename);
        }

        return ret.split(";");
    }
}
