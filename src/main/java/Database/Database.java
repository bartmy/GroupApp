package Database;

import java.sql.*;


public class Database {

    String url = "jdbc:mysql://127.0.0.1:3306/groups-app";
    String sqlUsername = "root";
    String sqlPassword = "bartek1";

    private Connection connect = null;
    private PreparedStatement preparedStatement = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    /**
     gives password for requested user
     */
    public String getPasswordForUsername(String username){
        String pw = "";
        try {
            connectToDatabase();
            preparedStatement = connect.prepareStatement(
                    "SELECT users.password FROM users WHERE username = '" + username + "';");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                pw = resultSet.getString("password");
            }
        } catch (Exception e) {
        }
        return pw;
    }
    public String getDataForUsername(String username, String whatToGet){
        String var = "";
        try {
            connectToDatabase();
            preparedStatement = connect.prepareStatement(
                    "SELECT users." + whatToGet + " FROM users WHERE username = '" + username + "';");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                var = resultSet.getString(""+ whatToGet);
            }
        } catch (Exception e) {
        }
        return var;
    }
    /**
     * checking if provided username is already in the database
     0 - username not found = available
     1 - username found = already taken
     */
    public Integer searchForUsername(String username){
        int a = 2; // initializing value with random number, so it can be returned at the end of method
        try {
            connectToDatabase();
            preparedStatement = connect.prepareStatement("SELECT COUNT(1)\n" +
                            "FROM users\n" +
                            "WHERE users.username = '" + username + "';");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                a = resultSet.getInt("COUNT(1)");
            }
        } catch (Exception e) {
        }
        System.out.println(a);
        return a;
    }

    /**
     for testing
     */
    public void getUserGroups(String username){
        try {
            connectToDatabase();
            preparedStatement = connect
                    .prepareStatement("SELECT users.username, user_groups.group_name FROM users  \n" +
                            "JOIN users_to_groups ON users.id = users_to_groups.user_id\n" +
                            "JOIN user_groups ON users_to_groups.group_id = user_groups.id\n" +
                            "WHERE users.username = '" + username + "'\n" +
                            "GROUP BY user_groups.group_name \n" +
                            ";");
            resultSet = preparedStatement.executeQuery();
            System.out.println("Group name: ");
            int lp = 0;
            while (resultSet.next()) {
                lp++;
                String group_name  = resultSet.getString("group_name");
                System.out.println(lp + ". " + group_name);
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
