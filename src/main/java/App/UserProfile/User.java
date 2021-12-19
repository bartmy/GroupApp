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
    }
    public void updateData(String whatToUpdate, String newValue){
        Database database = new Database();
        database.updateStatement("UPDATE users\n" +
                "SET " + whatToUpdate + " = '" + newValue + "'\n" +
                "WHERE username = '" + getUsername() + "';");

    }
    public String readData(String username, String whatToGet){
        Database database = new Database();
        return database.getDataForUsername(username,whatToGet);
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
