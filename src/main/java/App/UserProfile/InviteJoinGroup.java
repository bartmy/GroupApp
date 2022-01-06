package App.UserProfile;

import App.App;
import Database.Database;

public class InviteJoinGroup {

    protected void sendInvite(Group group, String username){
        try {
            User user = new User(username);
            Database database = new Database();
            database.updateStatement(
                    "INSERT INTO pending_invites(userID, groupID) " +
                            "VALUES (" + user.getUserID() + "," + group.getGroupID() + ");");
            System.out.println("invite sent to " + user.getUsername() + " to join '" + group.getGroupName() + " ");
        }catch (Exception e) {
            throw new IllegalStateException("sendInvite failed", e);
        }
    }
    protected void joinGroup(User user, String groupName){
        try {
            Group group = new Group(groupName);
            Database database = new Database();
            database.updateStatement(
                    "INSERT INTO pending_invites(userID, groupID) " +
                            "VALUES (" + user.getUserID() + "," + group.getGroupID() + ");");
            System.out.println("invite sent from " + user.getUsername() + " to join group '" + group.getGroupName() + "' ");
        }catch (Exception e) {
            throw new IllegalStateException("joinGroup failed", e);
        }
    }
    private void acceptInvite(Group group, User user){
        Database database = new Database();
        database.updateStatement(
                "INSERT INTO users_to_groups(userID, groupID) " +
                        "VALUES (" + user.getUserID() + "," + group.getGroupID() + ");");
        database.updateStatement(
                "DELETE FROM pending_invites\n" +
                        "where userID = " + user.getUserID() + " and groupID = " + group.getGroupID() + ";");
        System.out.println("approved");
    }
    private void rejectInvite(Group group, User user){
        Database database = new Database();
        database.updateStatement(
                "DELETE FROM pending_invites\n" +
                        "where userID = " + user.getUserID() + " and groupID = " + group.getGroupID() + ";");
        System.out.println("rejected");
    }
    protected void takeActionOnInvite(Group group, User user){
        System.out.println("1 accept / 2 reject / 0 back");
        switch (App.readInt()) {
            case 1 -> acceptInvite(group, user);
            case 2 -> rejectInvite(group, user);
            case 0 -> System.out.println("leave 0 ");
            default -> System.out.println("leave");
        }
    }
    protected void getUserPendingInvites(String username){
        Database database = new Database();
        database.getPendingInvitations(username);
    }
    protected void getGroupPendingInvites(String groupName){
        Database database = new Database();
        database.getPendingApplications(groupName);
    }

}
