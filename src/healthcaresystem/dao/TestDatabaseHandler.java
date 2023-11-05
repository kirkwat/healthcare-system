package healthcaresystem.dao;

import java.sql.*;

public class TestDatabaseHandler {

    public static void main(String[] args) {
        DatabaseHandler dbHandler = new DatabaseHandler();

        //tests go here

        dbHandler.closeConnection();
    }
}
