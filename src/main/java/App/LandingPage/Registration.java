package App.LandingPage;

import App.UserProfile.User;
import Database.Database;

public class Registration {


    public void registerNewUser() {
//        User user2 = new User("username", "password");
        User user = new User();
        Database database = new Database();
        System.out.println("username from register: " + user.getUsername());
        if (database.usernameNotUsed(user.getUsername())) {
            System.out.println("you have been added!");
            database.addUserToDatabase(user.getUsername(), user.getPassword());
        } else {
            System.out.println("username taken, please select new");
        }
    }
}
