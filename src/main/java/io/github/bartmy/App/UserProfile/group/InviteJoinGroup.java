package io.github.bartmy.App.UserProfile.group;

import io.github.bartmy.App.App;
import io.github.bartmy.App.UserProfile.user.User;
import io.github.bartmy.Database.Database;

public class InviteJoinGroup {

    protected void sendInvite(Group group, String username){
        try {
            User user = new User(username);
            Database database = new Database();
            database.updateStatement(
                    "INSERT INTO pending_invites(userID, groupID, inviteSentByUser) " +
                            "VALUES (" + user.getId() + "," + group.getId() + ", 0);"); //0 is for inviteSentByUser to be false
            System.out.println("invite sent to " + user.getUsername() + " to join '" + group.getGroupName() + " ");
        }catch (Exception e) {
            throw new IllegalStateException("sendInvite failed", e);
        }
    }
    public void joinGroup(User user, String groupName){
        try {
            Group group = new Group(groupName);
            Database database = new Database();
            database.updateStatement(
                    "INSERT INTO pending_invites(userID, groupID, inviteSentByUser) " +
                            "VALUES (" + user.getId() + "," + group.getId() + ", 1);"); //1 is for inviteSentByUser to be true
            System.out.println("invite sent from " + user.getUsername() + " to join group '" + group.getGroupName() + "' ");
        }catch (Exception e) {
            throw new IllegalStateException("joinGroup failed", e);
        }
    }
    private void acceptInvite(Group group, Integer id){
        try {
            Database database = new Database();
            int userID = database.getIntDataFromDatabase(
                    "pending_invites", "userID", "id", ""+id);
            database.updateStatement(
                    "INSERT INTO users_to_groups(userID, groupID) " +
                        "VALUES (" + userID + "," + group.getId() + ");");
            database.updateStatement(
                    "DELETE FROM pending_invites\n" +
                        "where id = " + id + " and userID = " + userID + " and groupID = " + group.getId() + ";");
            System.out.println("approved");
        }catch (Exception e) {
            System.out.println("acceptInvite failed");
        }
    }
    private void acceptInvite(User user, Integer id){
        try {
            Database database = new Database();
            int groupID = database.getIntDataFromDatabase(
                    "pending_invites", "groupID", "id", ""+id);
            database.updateStatement(
                    "INSERT INTO users_to_groups(userID, groupID) " +
                        "VALUES (" + user.getId() + "," + groupID + ");");
            database.updateStatement(
                    "DELETE FROM pending_invites\n" +
                        "where id = " + id + " and userID = " + user.getId() + " and groupID = " + groupID + ";");
            System.out.println("approved");
        }catch (Exception e) {
            System.out.println("acceptInvite failed");
        }
    }
    private void rejectInvite(User user, Integer id){
        try {
            Database database = new Database();
            database.updateStatement(
                    "DELETE FROM pending_invites\n" +
                        "where id = " + id + " and userID = " + user.getId() + ";");
            System.out.println("rejected");
        }catch (Exception e) {
            System.out.println("rejectInvite failed");
        }
    }
    private void rejectInvite(Group group, Integer id){
        try {
            Database database = new Database();
            database.updateStatement(
                    "DELETE FROM pending_invites\n" +
                        "where id = " + id + " and groupID = " + group.getId() + ";");
            System.out.println("rejected");
        }catch (Exception e) {
            System.out.println("rejectInvite failed");
        }
    }
    private void takeActionOnInvite(User user, Integer id){
        System.out.println("1 accept / 2 reject / 0 back");
        switch (App.readInt()) {
            case 1 -> acceptInvite(user, id);
            case 2 -> rejectInvite(user, id);
            case 0 -> System.out.println("leave 0 ");
            default -> System.out.println("leave");
        }
    }
    private void takeActionOnInvite(Group group, Integer id){
        System.out.println("1 accept / 2 reject / 0 back");
        switch (App.readInt()) {
            case 1 -> acceptInvite(group, id);
            case 2 -> rejectInvite(group, id);
            case 0 -> System.out.println("leave 0 ");
            default -> System.out.println("leave");
        }
    }
    protected void getUserPendingInvites(User user){
        Database database = new Database();
        System.out.println("pending invites for " + user.getUsername() + ": ");
        database.getInvitesSentToUser(user.getUsername());
        database.getInvitesSentByUser(user.getUsername());

    }
    private void getGroupPendingInvites(Group group){
        Database database = new Database();
        System.out.println("pending invites to join " + group.getGroupName() + ": ");
        database.getInvitesSentToGroup(group.getGroupName());
        database.getInvitesSentByGroup(group.getGroupName());
    }
    private Integer inviteChoice(){
        System.out.println("which id# you want to accept/reject ");
        return App.readInt();
    }
    protected void forGroupInvites(Group group){
        getGroupPendingInvites(group);
        int choice = inviteChoice();
        takeActionOnInvite(group, choice);
    }
    public void forUserInvites(User user){
        getUserPendingInvites(user);
        int choice = inviteChoice();
        takeActionOnInvite(user, choice);
    }

}
