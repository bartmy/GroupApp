package App;

import Database.Database;

import java.sql.SQLException;


public class GroupS {
    public static void main(String[] args) throws SQLException {
//
//        Database db = new Database();
//        db.printUsersList();

        App appStart = new App();
        while (App.isProgramOn()){
            appStart.startProgram();
        }
    }
}
