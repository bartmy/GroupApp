package io.github.bartmy.App.UserProfile;

import io.github.bartmy.App.App;

public class UserChange extends User{

    protected void startUserChange(User user){
        while(!previousStep){
            changeUserMenu(user);
        }
    }

    private void changeUserMenu(User user){
        System.out.println("""

                 what do you want to change?\s
                1. my data\s
                2. change username\s
                3. change password\s
                4. change display name\s
                5. change email\s
                0. back""");
        changeUserMenuOptions(user);
    }

    private void changeUserMenuOptions(User user){
        String username = user.getUsername();
        switch (App.readInt()) {
            case 1 -> user.printMyData();
            case 2 -> updateUsername(username);
            case 3 -> updatePassword(username);
            case 4 -> updateDisplayName(username);
            case 5 -> updateEmail(username);
            case 0 -> previousStep();
            default -> {
                App.wrongChoice();
                changeUserMenu(user);
            }
        }
    }

    private void updateUsername(String username){
        System.out.print("New username: ");
        String newUsername = App.readString();
        updateUserData(username, "username", newUsername);
    }
    private void updatePassword(String username){
        System.out.print("New password: ");
        String newPassword = App.readString();
        updateUserData(username, "password", newPassword);
    }
    private void updateDisplayName(String username){
        System.out.print("New display name: ");
        String newDisplayName = App.readString();
        updateUserData(username, "displayName", newDisplayName);
    }
    private void updateEmail(String username){
        System.out.print("New email: ");
        String newEmail = App.readString();
        updateUserData(username, "email", newEmail);
    }

    private void updateUserData(String username, String whatToUpdate, String newValue){
        updateData("users", whatToUpdate, newValue,"username",username);
    }
}
