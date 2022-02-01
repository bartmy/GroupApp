package io.github.bartmy.App.UserProfile.user;

import io.github.bartmy.App.App;
import io.github.bartmy.App.UserProfile.Profile;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter

@Entity
@Table(name = "users")
public class User extends Profile {

    @Id
    @GeneratedValue(generator="inc")
    @GenericGenerator(name="inc", strategy = "increment")
    private int id;
    private String username;
    private String password;
    private String displayName = null;
    private String email = null;


    /**
     * Hibernate (JPA) needs it.
     */
    @SuppressWarnings("unused")
    User() {
    }


    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.displayName = readUserData(username, "displayName");
        this.email = readUserData(username, "email");
        setID(username);
    }
    public User(String username){
        this.username = username;
        this.password = readUserData(username, "password");
        this.displayName = readUserData(username, "displayName");
        this.email = readUserData(username, "email");
        setID(username);
    }


    public void printMyData(){
        System.out.println("username: " + getUsername() + "\n" +
                "password: " + getPassword() + "\n" +
                "displayName: " + getDisplayName() + "\n" +
                "email: " + getEmail() + "\n" +
                "");
    }

    private String readUserData(String username, String whatToGet){
        return readData("users", whatToGet, "username", username);
    }
    /**
     created due to method readData work only for Strings
     */
    private void setID(String username){
        String id = readUserData(username, "id");
        this.id = Integer.parseInt(id);
    }

    /**
     used only to register user
     */

    public User(Integer thisValueDoesNothing){
    }
    public void registerUser(){
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
        updateData("users", "email", this.email, "username", this.username);
    }
    public void emailValidation(String username){
        if ((readUserData(username, "email")) == null){
            System.out.println("you do not have email, please update it");
            readEmail();
        }
    }
}
