package Database;

import java.sql.*;


public class Database {

    String url = "jdbc:mysql://127.0.0.1:3306/groups-app";
    String sqlUsername = "root";
    String sqlPassword = "bartek1";

    private Connection connect = null;
    private PreparedStatement preparedStatement = null;
//    private Statement statement = null;
    private ResultSet resultSet = null;

    /**
     adding new user to mysql database
     */
    public void addUserToDatabase(String username, String password) {
        updateStatement(
                "INSERT INTO users(username, password) VALUES('" + username + "', '" + password + "');");
    }

    public boolean usernameAvailable(String username){
        return lookForUsernameInDatabase(username) == 0;

    }

    /**
     0 - username not found
     1 - username found = already taken
     */
    public Integer lookForUsernameInDatabase(String username){
        int a = 2;
        try {
            connectToDatabase();
            preparedStatement = connect.prepareStatement("SELECT COUNT(1)\n" +
                            "FROM users\n" +
                            "WHERE users.username = '" + username + "';");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                a = resultSet.getInt("COUNT(1)");
//                System.out.println("asd: " + a);
            }
        } catch (Exception e) {
        }
//        System.out.println("1 zajete 0 wolne" + a);
        return a;
    }


    public void printUsersList(){
        try {
            connectToDatabase();
            preparedStatement = connect
                    .prepareStatement("SELECT users.username FROM users;");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                System.out.println("User: " + username);
            }
        } catch (Exception e) {
        }
    }

    /**
     method created to shorten process of creating statements, not sure if required or there is other possibility to do the same
     you can add your own exception
     */
    public void prepareAndExecuteStatement(String ourPreparedStatement, String ourException){
        try {
            connectToDatabase();
            preparedStatement = connect.prepareStatement(ourPreparedStatement);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new IllegalStateException(ourException, e);
        }
    }
    /**
     updating data in the database
     */
    public void updateStatement(String ourPreparedStatement){
        try {
            connectToDatabase();
            preparedStatement = connect.prepareStatement(ourPreparedStatement);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            throw new IllegalStateException("Cannot make an update!", e);
        }
    }
    /**
     connecting to database
     */
    public void connectToDatabase(){
        try {
            connect = DriverManager.getConnection(url, sqlUsername, sqlPassword);
        } catch (Exception e) {
            throw new IllegalStateException("Cannot connect to database!", e);
        }
    }
}
