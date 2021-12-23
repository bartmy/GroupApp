package App.UserProfile;

import App.App;
import Database.Database;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter

public class Group extends UserProfile{
    private int groupID;
    private String groupName ="";
    private String owner = "";

    protected Group(String groupName){
        this.groupName = groupName;
        this.owner = readGroupData(groupName,"owner");
        getGroupID(groupName);
    }

    protected void updateGroupName(String groupName){
        System.out.print("New group name: ");
        String newGroupName = App.readString();
        updateGroupData(groupName,"groupName",newGroupName);
    }
    protected void updateOwner(String owner){
        System.out.print("New owner: ");
        String newOwner = App.readString();
        updateGroupData(owner,"owner", newOwner);
    }
    private void updateGroupData(String groupName, String whatToUpdate, String newValue){
        updateData("user_groups", whatToUpdate, newValue,"groupName",groupName);
    }

    /**
     only for creating groups
     */
    protected Group(User user){
        takeGroupName();
        this.owner = user.getUsername();
        addGroupToDatabase(getGroupName(), getOwner());
        getGroupID(getGroupName());
        linkGroupToUser(user.getUserID(), getGroupID());
    }
    private void takeGroupName(){
        System.out.print("Group name: ");
        this.groupName = App.readString();
    }
    private void addGroupToDatabase(String groupName,String owner){
        Database database = new Database();
        database.updateStatement(
                "INSERT INTO user_groups(groupName, owner) " +
                        "VALUES('" + groupName + "', '" + owner + "');");
    }
    private void linkGroupToUser(int userID, int groupID){
        Database database = new Database();
        database.updateStatement(
                "INSERT INTO users_to_groups(userID, groupID) " +
                        "VALUES (" + userID + "," + groupID + ");");
    }

    protected void printUsersGroups(String username){
        Database database = new Database();
        database.getUserGroups(username);
    }
    private String readGroupData(String groupName, String whatToGet){
        return readData("user_groups", whatToGet, "groupName", groupName);
    }
    /**
     created due to method readData work only for Strings
     */
    private void getGroupID(String groupName){
        String id = readGroupData(groupName, "id");
        this.groupID = Integer.parseInt(id);
    }
}
