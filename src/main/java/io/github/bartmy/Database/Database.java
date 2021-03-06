package io.github.bartmy.Database;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    String url = "jdbc:mysql://127.0.0.1:3306/groups-app";
    String sqlUsername = "root";
    String sqlPassword = "bartek1";

    private Connection connect = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    /**
     * SELECT table.whatToGet
     * FROM table
     * WHERE whereKey = searchValue;
     */
    public String getDataFromDatabase(String table, String whatToGet, String whereKey, String searchValue){
        String str = "";
        try {
            connectToDatabase();
            preparedStatement = connect.prepareStatement(
                    "SELECT " + table + "." + whatToGet + " \n" +
                            "FROM " + table + " \n" +
                            "WHERE " + whereKey + " = '" + searchValue + "';");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                str = resultSet.getString(""+ whatToGet);
            }
        } catch (Exception e) {
            throw new IllegalStateException("getDataFromDatabase failed!", e);
        }
        return str;
    }
    public Integer getIntDataFromDatabase(String table, String whatToGet, String whereKey, String searchValue){
        String str = getDataFromDatabase(table, whatToGet,whereKey,searchValue);
        return Integer.parseInt(str);
    }
    /**
     * checking if provided username is already in the database
     0 - username not found = not taken
     1 - username found = already taken
     */
    public Integer isUsernameTaken(String username){
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
            throw new IllegalStateException("isUsernameTaken failed!", e);
        }
        System.out.println(a);
        return a;
    }
    /**
     * checking if provided groupName is already in the database
     0 - groupName not found = not taken
     1 - groupName found = already taken
     */
    public Integer checkForGroup(String groupName){
        int a = 2; // initializing value with random number, so it can be returned at the end of method
        try {
            connectToDatabase();
            preparedStatement = connect.prepareStatement("SELECT COUNT(1)\n" +
                            "FROM user_groups\n" +
                            "WHERE user_groups.groupName = '" + groupName + "';");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                a = resultSet.getInt("COUNT(1)");
            }
        } catch (Exception e) {
            throw new IllegalStateException("isGroupAvailable failed!", e);
        }
        System.out.println(a);
        return a;
    }

    /**
     prints group names for selected user
     */
    public void getUserGroups(String username){
        try {
            connectToDatabase();
            preparedStatement = connect
                    .prepareStatement("SELECT users.username, user_groups.groupName FROM users  \n" +
                            "JOIN users_to_groups ON users.id = users_to_groups.userID\n" +
                            "JOIN user_groups ON users_to_groups.groupID = user_groups.id\n" +
                            "WHERE users.username = '" + username + "'\n" +
                            "GROUP BY user_groups.groupName \n" +
                            ";");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String groupName  = resultSet.getString("groupName");
                System.out.println("- " + groupName);
            }
        } catch (Exception e) {
            throw new IllegalStateException("getUserGroups failed!", e);
        }
    }
    public void pickMyGroupToEdit(String username){
        try {
            connectToDatabase();
            preparedStatement = connect
                    .prepareStatement("SELECT users.username, user_groups.groupName, user_groups.id FROM users  \n" +
                            "JOIN users_to_groups ON users.id = users_to_groups.userID\n" +
                            "JOIN user_groups ON users_to_groups.groupID = user_groups.id\n" +
                            "WHERE users.username = '" + username + "'\n" +
                            "GROUP BY user_groups.groupName \n" +
                            ";");
            resultSet = preparedStatement.executeQuery();
            System.out.println("Group name: ");
            while (resultSet.next()) {
                String groupName  = resultSet.getString("groupName");
                String id  = resultSet.getString("id");
                System.out.println(id + ". " + groupName);
            }
        } catch (Exception e) {
            throw new IllegalStateException("pickMyGroupToEdit failed!", e);
        }
    }

    public void getGroupMembers(String groupName){
        try {
            connectToDatabase();
            preparedStatement = connect
                    .prepareStatement("SELECT user_groups.groupName, users.username, users.id FROM users\n" +
                            "JOIN users_to_groups ON users.id = users_to_groups.userID\n" +
                            "JOIN user_groups ON users_to_groups.groupID = user_groups.id\n" +
                            "WHERE user_groups.groupName = '" + groupName + "'\n" +
                            "GROUP BY users.username\n" +
                            ";");
            resultSet = preparedStatement.executeQuery();
            System.out.println("Group members: username, userID");
            while (resultSet.next()) {
                String username  = resultSet.getString("username");
                String id  = resultSet.getString("id");
                System.out.println("- " + username + " id#" + id);
            }
        } catch (Exception e) {
            throw new IllegalStateException("getGroupMembers failed!", e);
        }
    }
    public ArrayList<String> getGroupMembersList(String groupName){
        ArrayList<String> list = new ArrayList<>();
        try {
            connectToDatabase();
            preparedStatement = connect
                    .prepareStatement("SELECT user_groups.groupName, users.username, users.id FROM users\n" +
                            "JOIN users_to_groups ON users.id = users_to_groups.userID\n" +
                            "JOIN user_groups ON users_to_groups.groupID = user_groups.id\n" +
                            "WHERE user_groups.groupName = '" + groupName + "'\n" +
                            "GROUP BY users.username\n" +
                            ";");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String username  = resultSet.getString("username");
                list.add(username);
            }
        } catch (Exception e) {
            throw new IllegalStateException("getGroupMembersList failed!", e);
        }
        return list;
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
    public Integer getGroupSize(String groupName){
        String str = "";
        try {
            connectToDatabase();
            preparedStatement = connect.prepareStatement(
                    "SELECT user_groups.groupName, COUNT(user_groups.groupName) AS numberOfMembers FROM users  \n" +
                            "JOIN users_to_groups ON users.id = users_to_groups.userID\n" +
                            "JOIN user_groups ON users_to_groups.groupID = user_groups.id\n" +
                            "WHERE user_groups.groupName = '" + groupName + "';");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                str = resultSet.getString("numberOfMembers");
            }
        } catch (Exception e) {
            throw new IllegalStateException("getGroupSize failed!", e);
        }
        return Integer.parseInt(str);
    }
    /**
     application from users to join certain group
     */
    public void getInvitesSentToGroup(String groupName){
        try {
            connectToDatabase();
            preparedStatement = connect
                    .prepareStatement("SELECT users.username, user_groups.groupName, pending_invites.id, pending_invites.inviteSentByUser FROM users  \n" +
                            "JOIN pending_invites ON users.id = pending_invites.userID\n" +
                            "JOIN user_groups ON pending_invites.groupID = user_groups.id\n" +
                            "WHERE user_groups.groupName = '" + groupName + "' and pending_invites.inviteSentByUser = 1\n" +
                            "GROUP BY pending_invites.id \n" +
                            ";");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String username  = resultSet.getString("username");
                String id  = resultSet.getString("id");
                System.out.println("-from: " + username + " id#" + id);
            }
        } catch (Exception e) {
            throw new IllegalStateException("getInvitesSentToGroup failed!", e);
        }
    }
    public void getInvitesSentByGroup(String groupName){
        try {
            connectToDatabase();
            preparedStatement = connect
                    .prepareStatement("SELECT users.username, user_groups.groupName, pending_invites.id, pending_invites.inviteSentByUser FROM users  \n" +
                            "JOIN pending_invites ON users.id = pending_invites.userID\n" +
                            "JOIN user_groups ON pending_invites.groupID = user_groups.id\n" +
                            "WHERE user_groups.groupName = '" + groupName + "' and pending_invites.inviteSentByUser = 0\n" +
                            "GROUP BY pending_invites.id \n" +
                            ";");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String username  = resultSet.getString("username");
                String id  = resultSet.getString("id");
                System.out.println("-to: " + username + " id#" + id);
            }
        } catch (Exception e) {
            throw new IllegalStateException("getInvitesSentByGroup failed!", e);
        }
    }
    /**
     invitations of user to join different groups
     */
    public void getInvitesSentToUser(String username){
        try {
            connectToDatabase();
            preparedStatement = connect
                    .prepareStatement("SELECT users.username, user_groups.groupName, pending_invites.id, pending_invites.inviteSentByUser FROM users  \n" +
                            "JOIN pending_invites ON users.id = pending_invites.userID\n" +
                            "JOIN user_groups ON pending_invites.groupID = user_groups.id\n" +
                            "WHERE users.username = '" + username + "' and pending_invites.inviteSentByUser = 0\n" +
                            "GROUP BY pending_invites.id \n" +
                            ";");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String groupName  = resultSet.getString("groupName");
                String id  = resultSet.getString("id");
                System.out.println("-from " + groupName + " id#" + id);
            }
        } catch (Exception e) {
            throw new IllegalStateException("getInvitesSentToUser failed!", e);
        }
    }
    public void getInvitesSentByUser(String username){
        try {
            connectToDatabase();
            preparedStatement = connect
                    .prepareStatement("SELECT users.username, user_groups.groupName, pending_invites.id, pending_invites.inviteSentByUser FROM users  \n" +
                            "JOIN pending_invites ON users.id = pending_invites.userID\n" +
                            "JOIN user_groups ON pending_invites.groupID = user_groups.id\n" +
                            "WHERE users.username = '" + username + "' and pending_invites.inviteSentByUser = 1\n" +
                            "GROUP BY pending_invites.id \n" +
                            ";");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String groupName  = resultSet.getString("groupName");
                String id  = resultSet.getString("id");
                System.out.println("-to " + groupName + " id#" + id);
            }
        } catch (Exception e) {
            throw new IllegalStateException("getInvitesSentByUser failed!", e);
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
