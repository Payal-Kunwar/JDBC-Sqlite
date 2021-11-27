package net.sqlitetask;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertSel {

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C://sqlite/db/movies_data.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Insert a new row into the movies table
     *
     * @param name
     * @param capacity
     */
    public void insert(String name, String actor, String actress, String director, String year_of_release) {

        String sql = "INSERT INTO movies(name,actor,actress,director,year_of_release) VALUES(?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, actor);
            pstmt.setString(3, actress);
            pstmt.setString(4, director);
            pstmt.setString(5, year_of_release);
            pstmt.executeUpdate();
            System.out.println("Data inserted successfully!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * select all rows in the movies table
     */
    public void selectAll(){
        String sql = "SELECT * FROM movies";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" +
                        rs.getString("name") + "\t" +
                        rs.getString("actor") + "\t" +
                        rs.getString("actress") + "\t" +
                        rs.getString("director") + "\t" +
                        rs.getString("year_of_release"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectQuery(String actor_name) {
        String sql = "SELECT * FROM movies WHERE actor = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            // set the value
            pstmt.setString(1,actor_name);
            //
            ResultSet rs  = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" +
                        rs.getString("name") + "\t" +
                        rs.getString("actor") + "\t" +
                        rs.getString("actress") + "\t" +
                        rs.getString("director") + "\t" +
                        rs.getString("year_of_release"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        InsertSel data = new InsertSel();
        int choice;

        System.out.println("Operations:\n1. Insert\n2. Display all\n3. Select by query\n");
        Scanner in = new Scanner(System.in);
        do{
            System.out.print("Enter your choice: ");
            choice = in.nextInt();
            in.nextLine();
            switch(choice)
            {
                case 1: System.out.print("Enter the name of movie: ");
                    String name = in.nextLine();
                    System.out.print("\nEnter the name of actor: ");
                    String actor = in.nextLine();
                    System.out.print("\nEnter the name of actress: ");
                    String actress = in.nextLine();
                    System.out.print("\nEnter the name of director: ");
                    String director = in.nextLine();
                    System.out.print("\nEnter the year of release: ");
                    String year_of_release = in.nextLine();
                    data.insert(name, actor, actress, director, year_of_release);
                    break;
                case 2: data.selectAll();
                    break;
                case 3: System.out.print("\nEnter the name of actor: ");
                    String actor_name=in.nextLine();
                    data.selectQuery(actor_name);
                    break;
                default: System.out.println("Invalid input");
            }
        }while(choice != 4);
    }

}