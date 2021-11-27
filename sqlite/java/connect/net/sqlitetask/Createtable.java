package net.sqlitetask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Createtable {

    /**
     * 
     *
     */
    public static void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:C://sqlite/db/movies_data.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE movies (\n"
                + "	id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	name VARCHAR NOT NULL,\n"
                + "	actor VARCHAR NOT NULL,\n"
                + " actress VARCHAR NOT NULL,\n"
                + " director VARCHAR NOT NULL,\n"
                + " year_of_release VARCHAR NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            System.out.println("Table Created");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        createNewTable();
    }

}
