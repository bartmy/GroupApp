package App.UserProfile;

import App.App;
import Database.Database;
import lombok.*;

@NoArgsConstructor

public class ProfileMenu extends Profile {


    protected void startProfileMenu(User user){
        while (!profileLogout) {
            profileMenu(user);
        }
    }
    private void profileMenu(User user){
        System.out.println("""

                 what do you want to do ?\s
                1. my data\s
                2. change my data\s
                3. my groups\s
                4. enter group\s
                5. create new group\s
                0. logout""");
        menuOptions(user);
    }
    private void menuOptions(User user){
        switch (App.readInt()) {
            case 1 -> user.printMyData();
            case 2 -> {
                UserChange userChange = new UserChange();
                userChange.startUserChange(user);
            }
            case 3 -> printMyGroups(user);
            case 4 -> enterGroup(user);
            case 5 -> {
                while(!previousStep) newGroup(user);
            }
            case 0 -> logout();
            default -> {
                App.wrongChoice();
                profileMenu(user);
            }
        }
    }
    private void enterGroup(User user){
        int choice = chooseGroupToEdit(user);
        if (choice==0){
            previousStep();
        }else {
            Group group = new Group(choice);
            if (user.getUsername().equals(group.getOwner())) {
                ManageGroups manageGroups = new ManageGroups();
                manageGroups.startManageGroups(user, group);
            }else{
                System.out.println("you are not group owner");
                previousStep();
            }
        }
    }

    private Integer chooseGroupToEdit(User user){
        Database database = new Database();
        System.out.println("\n type ID of the group you want to edit ? 0 to go back");
        database.pickMyGroupToEdit(user.getUsername());
        return App.readInt();
    }

    private void newGroup(User user){
        System.out.print("Group name (0 - go back): ");
        String groupName = App.readString();
        if (groupName.equals("0")){
            previousStep();
        }else {
            new Group(user,groupName);
        }
    }
}