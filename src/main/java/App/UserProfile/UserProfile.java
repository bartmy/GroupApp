package App.UserProfile;

public class UserProfile {
    private boolean profileLogout = false;
    private boolean stopChange = false;

    public void startProfile(String username, String password){
        while (!profileLogout){
            User user = new User(username, password);
            profileMenu(user);
        }
    }
    private void profileMenu(User user){
        System.out.println("\n co robimy ? \n" +
                "1. pokaz moje dane \n" +
                "2. zmien dane \n" +
                "3. moje grupy \n" +
                "4. utworz nową grupe \n" +
                "5. pokaz moje dane \n" +
                "6. pokaz moje dane \n" +
                "0. logout");
        menuOptions(user);
    }

    private void menuOptions(User user){
        switch (App.App.readInt()) {
            case 1 -> printMyData(user);
            case 2 -> {
                while(!stopChange) changeMenu(user);
            }
            case 3 -> printMyGroups(user);
            case 4 -> newGroup(user);
            case 0 -> logout();
            default -> {
                System.out.println("nie rozpoznano");
                profileMenu(user);
            }
        }
    }
    private void changeMenu(User user){
        System.out.println("\n what do you want to change? \n" +
                "1. username \n" +
                "2. password \n" +
                "3. display name \n" +
                "4. email \n" +
                "0. back");
        changeMenuOptions(user);
    }

    private void changeMenuOptions(User user){
        String username = user.getUsername();
        switch (App.App.readInt()) {
            case 1 -> user.updateUsername(username);
            case 2 -> user.updatePassword(username);
            case 3 -> user.updateDisplayName(username);
            case 4 -> user.updateEmail(username);
            case 0 -> stopChange();
            default -> {
                System.out.println("nie rozpoznano");
                profileMenu(user);
            }
        }
    }
    private void printMyData(User user){
        System.out.println("username: " + user.getUsername() + "\n" +
                "password: " + user.getPassword() + "\n" +
                "displayName: " + user.getDisplayName() + "\n" +
                "email: " + user.getEmail() + "\n" +
                "");
    }
    private void printMyGroups(User user){
        Group group = new Group();
        group.printUsersGroups(user.getUsername());
    }

    private void newGroup(User user){
        Group group = new Group();
        group.newGroup(user);
    }


    private void logout(){
        System.out.println("zostałeś pomyślnie wylogowany");
        profileLogout = true;
    }
    private void stopChange(){
        System.out.println("back to previous step");
        stopChange = true;
    }

}
