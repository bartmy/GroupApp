package App.LandingPage;

import App.UserProfile.User;
import Database.Database;

import java.sql.SQLException;

public class Registration {


    public void registerNewUser() {
//        User user2 = new User("username", "password");
        User user = new User();
        Database database = new Database();
        String username = user.getUsername();
        if (database.usernameAvailable(username)){
            System.out.println("you have been added!");
            database.addUserToDatabase(username, user.getPassword());
        } else {
            System.out.println("username taken, please select new");
        }


    }
    public void checkUsernameAvailability(){

    }
}
