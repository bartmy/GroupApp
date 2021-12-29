package App.LandingPage;

import App.App;
import App.UserProfile.UserProfile;
import Database.Database;

public class Login {
    Database database = new Database();

    protected void login(){
        System.out.print("Username: ");
        String username = App.readString();
        System.out.print("Password: ");
        String password = App.readString();

        passwordValidation(username, password);
    }

    private void passwordValidation(String username, String password){
        String pw = getPasswordForUsername(username);
        if (password.equals(pw)){
            System.out.println("login successful");
            UserProfile up = new UserProfile();
            up.startProfile(username, password);
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
