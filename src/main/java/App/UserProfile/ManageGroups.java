package App.UserProfile;

import App.App;
import Database.Database;

public class ManageGroups extends ProfileMenu{

    protected void startManageGroups(User user, Group group){
        while(!previousStep){
            menageGroups(user, group);
        }
    }
    private void menageGroups(User user, Group group){
        System.out.println("""

                 what do you want to do?\s
                1. see members\s
                2. add users\s
                3. see group data\s
                4. change group info\s
                0. back""");
        manageGroupsOptions(user, group);
    }

    private void manageGroupsOptions(User user, Group group){
        String groupName = group.getGroupName();
        switch (App.readInt()) {
            case 1 -> group.printGroupMembers(groupName);
            case 2 -> addGroupMember(group);
            case 3 -> group.printGroupDetails(group);
            case 4 -> {
                if (user.getUsername().equals(group.getOwner())) {
                    GroupChange groupChange = new GroupChange();
                    groupChange.startGroupChange(group);
                }else{
                    System.out.println("you are not group owner");
//                    previousStep();
                }
            }
            case 0 -> previousStep();
            default -> {
                App.wrongChoice();
                menageGroups(user, group);
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
            database.updateStatement(
                    "INSERT INTO users_to_groups(userID, groupID) " +
                            "VALUES (" + user.getUserID() + "," + group.getGroupID() + ");");
        }else System.out.println("addGroupMember failed");
    }
}
