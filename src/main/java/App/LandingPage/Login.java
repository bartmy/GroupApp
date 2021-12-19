package App.LandingPage;

import App.App;
import App.UserProfile.UserProfile;
import Database.Database;

public class Login {
    Database database = new Database();

    public void login(){
        System.out.print("Username: ");
        String username = App.readString();
        System.out.print("Password: ");
        String password = App.readString();

        passwordValidation(username, password);
    }

    public void passwordValidation(String username, String password){
        String pw = database.getPasswordForUsername(username);
        if (password.equals(pw)){
            System.out.println("login poprawny");
            UserProfile up = new UserProfile();
            up.startProfile(username, password);
        }else {
            System.out.println("wrong username or password");
        }
    }


}
