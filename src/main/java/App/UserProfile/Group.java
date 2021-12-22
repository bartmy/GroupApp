package App.UserProfile;

import Database.Database;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter

public class Group {
    private int groupID;
    private String groupName ="";
    private String owner = "";

    public void newGroup(String username){
        takeGroupName();
        this.owner = username;
    }
    private void takeGroupName(){
        System.out.print("Group name: ");
        this.groupName = App.App.readString();
    }
    private void addGroupToDatabase(String groupName,String owner){
        Database database = new Database();
//        database.updateStatement();

    }
    public void printUsersGroups(String username){
        Database database = new Database();
        database.getUserGroups(username);
    }
    public String readData(String groupName, String whatToGet){
        Database database = new Database();
        return database.getDataFromDatabase(
                "user_groups" ,whatToGet, "groupName", groupName);
    }
    /**
     created due to method readData work only for Strings
     */
    private void setGroupID(String groupName){
        String id = readData(groupName, "id");
        this.groupID = Integer.parseInt(id);
    }
}
