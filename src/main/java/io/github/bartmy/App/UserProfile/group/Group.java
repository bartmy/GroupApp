package io.github.bartmy.App.UserProfile.group;

import io.github.bartmy.App.App;
import io.github.bartmy.App.UserProfile.Profile;
import io.github.bartmy.App.UserProfile.user.User;
import io.github.bartmy.Database.Database;
import lombok.*;

@NoArgsConstructor
@ToString
@Getter
@Setter

public class Group extends Profile {
    private int groupID;
    private String groupName ="";
    private String owner;

    protected Group(String groupName){
        this.groupName = groupName;
        this.owner = readGroupData(groupName,"owner");
        getGroupID(groupName);
    }
    public Group(Integer groupID){
        this.groupID = groupID;
        this.groupName = readData("user_groups", "groupName", "id", ""+groupID);
        this.owner = readGroupData(groupName,"owner");
    }

    protected void printGroupMembers(String groupName){
        Database database = new Database();
        database.getGroupMembers(groupName);
    }
    protected void groupSize(String groupName){
        Database database = new Database();
        database.getGroupMembers(groupName);
    }
    protected void printGroupDetails(Group group){
        System.out.println("group name: " + group.getGroupName() +
                " Group ID: " + group.getGroupID() +
                " owner: " + group.getOwner());
    }
    public void printUsersGroups(String username){
        Database database = new Database();
        database.getUserGroups(username);
    }
    private String readGroupData(String groupName, String whatToGet){
        return readData("user_groups", whatToGet, "groupName", groupName);
    }
    /**
     only for creating groups
     */
    protected Group(User user){
        takeGroupName();
        this.owner = user.getUsername();
        addGroupToDatabase(getGroupName(), getOwner());
        getGroupID(getGroupName());
        linkGroupToUser(user.getId(), getGroupID());
    }
    public Group(User user, String groupName){
        this.groupName = groupName;
        this.owner = user.getUsername();
        addGroupToDatabase(getGroupName(), getOwner());
        getGroupID(getGroupName());
        linkGroupToUser(user.getId(), getGroupID());
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

    /**
     created due to method readData work only for Strings
     */
    private void getGroupID(String groupName){
        String id = readGroupData(groupName, "id");
        this.groupID = Integer.parseInt(id);
    }
}
