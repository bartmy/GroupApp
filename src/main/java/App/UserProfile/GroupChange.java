package App.UserProfile;

import App.App;

public class GroupChange extends Group{

    protected void startGroupChange(Group group){
        while(!previousStep){
            changeGroupData(group);
        }
    }
    private void changeGroupData(Group group){
        System.out.println("""

                 what do you want to change?\s
                1. see group data\s
                2. groupName\s
                3. owner\s
                0. back""");
        changeGroupDataOptions(group);
    }

    private void changeGroupDataOptions(Group group){
        String groupName = group.getGroupName();
        switch (App.readInt()) {
            case 1 -> printGroupDetails(group);
            case 2 -> updateGroupName(groupName);
            case 3 -> updateOwner(groupName);
            case 0 -> previousStep();
            default -> {
                App.wrongChoice();
                changeGroupData(group);
            }
        }
    }

    private void updateGroupName(String groupName){
        System.out.print("New group name: ");
        String newGroupName = App.readString();
        updateGroupData(groupName,"groupName",newGroupName);
    }
    private void updateOwner(String owner){
        System.out.print("New owner: ");
        String newOwner = App.readString();
        updateGroupData(owner,"owner", newOwner);
    }
    private void updateGroupData(String groupName, String whatToUpdate, String newValue){
        updateData("user_groups", whatToUpdate, newValue,"groupName",groupName);
    }

}
