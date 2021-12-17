package App.LandingPage;

import App.UserProfile.User;
import Database.Database;

import java.sql.SQLException;

public class Registration {


    public void registerNewUser() throws SQLException {
//        User user2 = new User("username", "password");
        User user = new User();
        Database database = new Database();

        database.addUserToDatabase(user.getUsername(), user.getPassword());

    }
    public void checkUsernameAvailability(){

    }
}
