package App.UserProfile;

import Database.Database;
import lombok.*;

@NoArgsConstructor

public class Profile {

    public void startProfile(String username, String password){
            User user = new User(username, password);
            user.emailValidation(username); // check if user has email, if not he needs to update it
            ProfileMenu profileMenu = new ProfileMenu();
            profileMenu.startProfileMenu(user);
    }

    /**
     printing list of groups for chosen group
     */
    protected void printMyGroups(User user){
        System.out.println("My groups");
        Group group = new Group();
        group.printUsersGroups(user.getUsername());
    }


    protected boolean profileLogout = false;
    protected void logout(){
        System.out.println("you have been logged out!");
        profileLogout = true;
    }

    protected boolean previousStep = false;
    protected void previousStep(){
        System.out.println("back to previous step");
        previousStep = true;
    }
    /**
     * SELECT table.whatToGet
     * FROM table
     * WHERE whereKey = searchValue;
     */
    protected String readData(String table, String whatToGet, String keyID, String searchValue){
        Database database = new Database();
        return database.getDataFromDatabase(
                table ,whatToGet, keyID, searchValue);
    }
    /**
     UPDATE table
     SET whatToUpdate = newValue
     WHERE keyID = keyValue;
     */

    protected void updateData(String table,
                              String whatToUpdate, String newValue,
                              String keyID, String keyValue){
        Database database = new Database();
        database.updateStatement(
                "UPDATE " + table + "\n" +
                        "SET " + whatToUpdate + " = '" + newValue + "'\n" +
                        "WHERE " + keyID + " = '" + keyValue + "';");
    }
}
