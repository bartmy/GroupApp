package App;

import java.sql.*;


public class Database {

    String url = "jdbc:mysql://127.0.0.1:3306/groups-app";
    String usrname = "root";
    String pw = "bartek1";

    public void testConnectToDatabase(){
        System.out.println("Connecting database...");

        try (Connection connection = DriverManager.getConnection(url, usrname, pw)) {
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void addUserToDatabase(String username, String password) throws SQLException {
        System.out.println("dodajemy");
        try {
            connect = DriverManager.getConnection(url, usrname, pw);

            preparedStatement = connect.prepareStatement("INSERT INTO users(username, password) VALUES('" + username + "', '" + password + "');");
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
        System.out.println("dodane");

    }


}
