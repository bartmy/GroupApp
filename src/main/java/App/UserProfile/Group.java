package App.UserProfile;

import Database.Database;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter

public class Group {
    private String groupName ="";
    private String owner = "";

    public void newGroup(String username){
        readGroupName();
        this.owner = username;

    }
    private void readGroupName(){
        System.out.print("Podaj nazwe grupy: ");
        this.groupName = App.App.readString();
    }
    public void printUsersGroups(String username){
        Database database = new Database();
        database.getUserGroups(username);
    }
}
