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
                2. add users or pending invites\s
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
            case 5 -> {
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
        InviteJoinGroup invite = new InviteJoinGroup();
        System.out.print("0 to see pending invites or type name of group you want to join: ");
        String username = App.readString();
        if (username.equals("0")){
            invite.getGroupPendingInvites(group.getGroupName());
        }else{
            Database database = new Database();
            if (database.isUsernameTaken(username) == 1){
                invite.sendInvite(group, username);
            }else System.out.println("addGroupMember failed");
        }

    }
}
