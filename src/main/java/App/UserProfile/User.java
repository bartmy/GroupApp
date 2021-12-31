package App.UserProfile;

import App.App;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@Setter

public class User extends UserProfile{
    private String username;
    private String password;
    private int userID;
    private String displayName = null;
    private String email = null;


    protected User(String username, String password){
        this.username = username;
        this.password = password;
        this.displayName = readUserData(username, "displayName");
        this.email = readUserData(username, "email");
        setID(username);
    }
    protected User(String username){
        this.username = username;
        this.password = readUserData(username, "password");
        this.displayName = readUserData(username, "displayName");
        this.email = readUserData(username, "email");
        setID(username);
    }


    protected void printMyData(){
        System.out.println("username: " + getUsername() + "\n" +
                "password: " + getPassword() + "\n" +
                "displayName: " + getDisplayName() + "\n" +
                "email: " + getEmail() + "\n" +
                "");
    }
    protected void updateUsername(String username){
        System.out.print("New username: ");
        String newUsername = App.readString();
        updateUserData(username, "username", newUsername);
    }
    protected void updatePassword(String username){
        System.out.print("New password: ");
        String newPassword = App.readString();
        updateUserData(username, "password", newPassword);
    }
    protected void updateDisplayName(String username){
        System.out.print("New display name: ");
        String newDisplayName = App.readString();
        updateUserData(username, "displayName", newDisplayName);
    }
    protected void updateEmail(String username){
        System.out.print("New email: ");
        String newEmail = App.readString();
        updateUserData(username, "email", newEmail);
    }

    private void updateUserData(String username, String whatToUpdate, String newValue){
        updateData("users", whatToUpdate, newValue,"username",username);
    }

    private String readUserData(String username, String whatToGet){
        return readData("users", whatToGet, "username", username);
    }
    /**
     created due to method readData work only for Strings
     */
    private void setID(String username){
        String id = readUserData(username, "id");
        this.userID = Integer.parseInt(id);
    }

    /**
     used only to register user
     */
    public User(){
        readUsername();
        readPassword();
    }
    private void readUsername(){
        System.out.print("Username: ");
        this.username = App.readString();
    }
    private void readPassword(){
        System.out.print("Password: ");
        this.password = App.readString();
    }
    private void readEmail(){
        System.out.print("Email: ");
        this.email = App.readString();
    }
    protected void emailValidation(String username){
        if ((readUserData(username, "email")).isEmpty()){
            System.out.println("you do not have email, please update it");
            readEmail();
        }
    }
}
