package App;

import App.LandingPage.Login;
import Database.Database;


public class GroupS {
    public static void main(String[] args) {
//
//        Database db = new Database();
//        db.getPasswordForUsernameInDatabase("mysza");

//        App appStart = new App();
//        while (App.isProgramOn()){
//            appStart.startProgram();
//        }

        Login login = new Login();
        login.testLogin("bartekm","haslo");
    }
}
