package App;

import Database.Database;


public class GroupS {
    public static void main(String[] args) {
//
//        Database db = new Database();
//        db.findUsernameInDatabase("myszaq");

        App appStart = new App();
        while (App.isProgramOn()){
            appStart.startProgram();
        }
    }
}
