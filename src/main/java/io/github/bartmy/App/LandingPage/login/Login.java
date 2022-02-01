package io.github.bartmy.App.LandingPage.login;

import io.github.bartmy.App.App;
import io.github.bartmy.App.UserProfile.Profile;
import io.github.bartmy.Database.Database;

public class Login {
    Database database = new Database();

    private void startLogin(){
        System.out.print("Username: ");
        String username = App.readString();
        System.out.print("Password: ");
        String password = App.readString();

        passwordValidation(username, password); // after successful validation userProfile is triggered
    }

    private void passwordValidation(String username, String password){
        String pw = getPasswordForUsername(username);
        if (password.equals(pw)){
            System.out.println("login successful");
            Profile profile = new Profile();
            profile.startProfile(username, password);
        }else {
            System.out.println("wrong username or password");
        }
    }
    private String getPasswordForUsername(String username){
        return database.getDataFromDatabase("users","password", "username", username);
    }

    public void testLogin(String username, String password){
        passwordValidation(username, password);
    }
}
