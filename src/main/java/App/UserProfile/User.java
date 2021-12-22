package App.UserProfile;

import App.App;
import Database.Database;
import lombok.*;

@AllArgsConstructor
@ToString
@Getter
@Setter

public class User {
    private String username;
    private String password;
    private int userID;
    private String displayName = null;
    private String email = null;


    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.displayName = readData(username, "displayName");
        this.email = readData(username, "email");
        setID(username);
    }

    public void updateUsername(String username){
        System.out.print("New username: ");
        String newUsername = App.readString();
        updateUserData(username, "username", newUsername);
    }
    public void updatePassword(String username){
        System.out.print("New password: ");
        String newPassword = App.readString();
        updateUserData(username, "password", newPassword);
    }
    public void updateDisplayName(String username){
        System.out.print("New display name: ");
        String newDisplayName = App.readString();
        updateUserData(username, "displayName", newDisplayName);
    }
    public void updateEmail(String username){
        System.out.print("New email: ");
        String newEmail = App.readString();
        updateUserData(username, "email", newEmail);
    }

    private void updateUserData(String username, String whatToUpdate, String newValue){
        Database database = new Database();
        database.updateStatement(
                "UPDATE users\n" +
                "SET " + whatToUpdate + " = '" + newValue + "'\n" +
                "WHERE username = '" + username + "';");

    }
    public String readData(String username, String whatToGet){
        Database database = new Database();
        return database.getDataFromDatabase(
                "users" ,whatToGet, "username", username);
    }
    /**
     created due to method readData work only for Strings
     */
    private void setID(String username){
        String id = readData(username, "id");
        this.userID = Integer.parseInt(id);
    }

    /**
     used only to register user
     */
    public User(){
        readUsername();
        readPassword();
        readEmail();
    }
    private void readUsername(){
        System.out.print("user Username: ");
        this.username = App.readString();
    }
    private void readPassword(){
        System.out.print("user Password: ");
        this.password = App.readString();
    }
    private void readEmail(){
        System.out.print("user email: ");
        this.email = App.readString();
    }
}
