package App;

import App.App;

import java.sql.SQLException;


public class GroupS {
    public static void main(String[] args) throws SQLException {

        Database sql = new Database();
        sql.testConnectToDatabase();
        sql.addUserToDatabase("test2", "test2");

//
//        App appStart = new App();
//        while (App.isProgramOn()){
//            appStart.startProgram();
//        }
    }
}
