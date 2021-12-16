package App;

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
    public User(){
        newUser();
    }
    public void newUser(){
        readUsername();
        readPassword();
    }
    public void readUsername(){
        System.out.print("Username: ");
        this.username = App.readString();
    }
    public void readPassword(){
        System.out.print("Password: ");
        this.password = App.readString();
    }

}
