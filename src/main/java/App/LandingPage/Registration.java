package App.LandingPage;

import App.UserProfile.User;
import Database.Database;

public class Registration {
    Database database = new Database();

    public void registerNewUser() {
//        User user2 = new User("username", "password");
        User user = new User();
        System.out.println("username from register: " + user.getUsername());
        if (usernameNotUsed(user.getUsername())) {
            System.out.println("you have been added!");
            addUserToDatabase(user.getUsername(), user.getPassword());
        } else {
            System.out.println("username taken, please select new");
        }
    }
    /**
     adding new user to mysql database
     */
    private void addUserToDatabase(String username, String password) {
        database.updateStatement(
                "INSERT INTO users(username, password) VALUES('" + username + "', '" + password + "');");
    }
    /**
     returning is username is available
     */
    private boolean usernameNotUsed(String username){
        if (database.searchForUsername(username) == 0) return true;
        else return false;
    }

}
