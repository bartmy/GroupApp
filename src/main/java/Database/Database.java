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
    public void addUserToDatabase(String username, String password) throws SQLException {
        updateStatement(
                "INSERT INTO users(username, password) VALUES('" + username + "', '" + password + "');");
    }
    public void usernameAvailability(String username){
//        prepareAndExecuteStatement("SELECT username FROM users;");

    }
    public void usernameAvailability() throws SQLException {
        connectToDatabase();
        preparedStatement = connect
                .prepareStatement("SELECT users.username FROM users;");
        resultSet = preparedStatement.executeQuery();
//        printUsersList(resultSet);

    }


    public void printUsersList() throws SQLException {
        connectToDatabase();
        preparedStatement = connect
                .prepareStatement("SELECT users.username FROM users;");
        resultSet = preparedStatement.executeQuery();
//        printUsersList(resultSet);

        while (resultSet.next()) {
            String username = resultSet.getString("username");
            System.out.println("User: " + username);
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
    public void updateStatement(String ourPreparedStatement) throws SQLException {
        try {
            connectToDatabase();
            preparedStatement = connect.prepareStatement(ourPreparedStatement);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new IllegalStateException("Cannot make an update!", e);
        }
        preparedStatement.close();
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
