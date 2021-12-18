package App.LandingPage;

import Database.Database;

public class Login {
    Database database = new Database();

    public void login(){

    }

    public void passwordValidation(String username, String password){
        String pw = database.getPasswordForUsernameInDatabase(username);
        if (password.equalsIgnoreCase(pw)){

        }
    }


}
