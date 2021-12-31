package App.LandingPage;

import App.App;
import App.UserProfile.UserProfile;
import Database.Database;

public class Login {
    Database database = new Database();

    public Login(){
        login();
    }

    public Login(String username, String password){
        testLogin(username, password);
    }

    private void login(){
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
            new UserProfile(username, password);
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
