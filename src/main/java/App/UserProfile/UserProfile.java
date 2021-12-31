package App.UserProfile;

import App.App;
import Database.Database;
import lombok.*;

@NoArgsConstructor

public class UserProfile {
    private boolean profileLogout = false;
    private boolean previousStep = false;

    public UserProfile(String username, String password){
        startProfile(username, password);
    }

    public void startProfile(String username, String password){
        while (!profileLogout){
            User user = new User(username, password);
            user.emailValidation(username); // check if user has email, if not he needs to update it
            profileMenu(user);
        }
    }
    private void profileMenu(User user){
        System.out.println("""

                 what do you want to do ?\s
                1. my data\s
                2. change data\s
                3. my groups\s
                4. create new group\s
                5. manage groups\s
                0. logout""");
        menuOptions(user);
    }

    private void menuOptions(User user){
        switch (App.readInt()) {
            case 1 -> user.printMyData();
            case 2 -> {
                while(!previousStep) changeUserMenu(user);
            }
            case 3 -> printMyGroups(user);
            case 4 -> newGroup(user);
            case 5 -> {
                while (!previousStep){
                    Group group = new Group(chooseGroupToEdit(user));
                    while (!previousStep){
                        if (user.getUsername().equals(group.getOwner())) {
                            menageGroups(user, group);
                        } else{
                            System.out.println("you are not group owner");
                            previousStep();
                        }
                    }
                }
            }
            case 0 -> logout();
            default -> {
                App.wrongChoice();
                profileMenu(user);
            }
        }
    }
    private void menageGroups(User user, Group group){
        System.out.println("""

                 what do you want to change?\s
                1. see group data\s
                2. groupName\s
                3. owner\s
                4. add users\s
                5. see members\s
                0. back""");
        menageGroupsOptions(user, group);
    }

    private void menageGroupsOptions(User user, Group group){
        String groupName = group.getGroupName();
        switch (App.readInt()) {
            case 1 -> group.printGroupDetails(group);
            case 2 -> group.updateGroupName(groupName);
            case 3 -> group.updateOwner(groupName);
            case 4 -> addGroupMember(group);
            case 5 -> group.printGroupMembers(groupName);
            case 0 -> previousStep();
            default -> {
                App.wrongChoice();
                menageGroups(user, group);
            }
        }
    }
    private Integer chooseGroupToEdit(User user){
        Database database = new Database();
        System.out.println("\n type ID of the group you want to edit ?");
        database.pickMyGroupToEdit(user.getUsername());
        return App.readInt();
    }

    private void changeUserMenu(User user){
        System.out.println("""

                 what do you want to change?\s
                1. username\s
                2. password\s
                3. display name\s
                4. email\s
                0. back""");
        changeUserMenuOptions(user);
    }

    private void changeUserMenuOptions(User user){
        String username = user.getUsername();
        switch (App.readInt()) {
            case 1 -> user.updateUsername(username);
            case 2 -> user.updatePassword(username);
            case 3 -> user.updateDisplayName(username);
            case 4 -> user.updateEmail(username);
            case 0 -> previousStep();
            default -> {
                App.wrongChoice();
                changeUserMenu(user);
            }
        }
    }
    /**
     adding new member to chosen group
     */
    private void addGroupMember(Group group){
        System.out.print("type username of user you want to add: ");
        String newMember = App.readString();
        Database database = new Database();
        if (database.isUsernameTaken(newMember) == 1){
            User user = new User(newMember);
            group.addGroupMember(user.getUserID(), group.getGroupID());
        }else System.out.println("addGroupMember failed");
    }
    /**
     printing list of groups for chosen group
     */
    private void printMyGroups(User user){
        System.out.println("My groups");
        Group group = new Group();
        group.printUsersGroups(user.getUsername());
    }



    private void newGroup(User user){
        new Group(user);
    }
    private void logout(){
        System.out.println("you have been logged out!");
        profileLogout = true;
    }
    private void previousStep(){
        System.out.println("back to previous step");
        previousStep = true;
    }
    /**
     * SELECT table.whatToGet
     * FROM table
     * WHERE whereKey = searchValue;
     */
    protected String readData(String table, String whatToGet, String keyID, String searchValue){
        Database database = new Database();
        return database.getDataFromDatabase(
                table ,whatToGet, keyID, searchValue);
    }
    /**
     UPDATE table
     SET whatToUpdate = newValue
     WHERE keyID = keyValue;
     */

    protected void updateData(String table,
                              String whatToUpdate, String newValue,
                              String keyID, String keyValue){
        Database database = new Database();
        database.updateStatement(
                "UPDATE " + table + "\n" +
                        "SET " + whatToUpdate + " = '" + newValue + "'\n" +
                        "WHERE " + keyID + " = '" + keyValue + "';");
    }
}
