/**
 * Created by RH Anesi on 4/14/2017.
 */

import java.sql.*;

public class Main {

    public static void main(String[] args) {

        //Initialize database to populate Rubik's Record Manager GUI
        ResultSet rs = Database.setup();

        if (rs == null) {

            Database.shutdown();
            System.exit(1);
        }

        try {
            RubiksModel rubiksModel = new RubiksModel(rs);
            RubiksGUI giu = new RubiksGUI(rubiksModel);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    public static void shutdown() {
        //Safely shutdown database
        Database.shutdown();
        System.exit(0);
    }
}
