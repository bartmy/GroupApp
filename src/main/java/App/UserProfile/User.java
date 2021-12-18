package App.UserProfile;

import App.App;
import lombok.*;

@AllArgsConstructor
@ToString
@Getter
@Setter

public class User {
    private String username;
    private String password;
    private int userID;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
    /**
     used only to register user
     */
    public User(){
        readUsername();
        readPassword();
    }
    private void readUsername(){
        System.out.print("user Username: ");
        this.username = App.readString();
    }
    private void readPassword(){
        System.out.print("user Password: ");
        this.password = App.readString();
    }
}
