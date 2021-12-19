package App.UserProfile;

public class UserProfile {
    private boolean profileLogout = false;

    public void startProfile(String username, String password){
        while (!profileLogout){
            User user = new User(username, password);
            profileMenu(user);
        }
    }
    protected void profileMenu(User user){
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

    protected void menuOptions(User user){
        switch (App.App.readInt()){
            case 1:
                System.out.println("pokaz moje dane");
                System.out.println("username: " + user.getUsername() + "\n" +
                        "password: " + user.getPassword() + "\n" +
                        "displayName: " + user.getDisplayName() + "\n" +
                        "email: " + user.getEmail() + "\n" +
                        "");

                break;
            case 2:
                System.out.println("zmien dane");
                break;
            case 3:
                System.out.println("opcja 3");
                break;
            case 4:
                System.out.println("opcja 4");
                break;
            case 5:
                System.out.println("opcja 5");
                break;
            case 6:
                System.out.println("opcja 6");
                break;
            case 7:
                System.out.println("opcja 7");
                ;
            case 0:
                logout();
                break;
            default:
                System.out.println("nie rozpoznano");
                profileMenu(user);
                break;
        }
    }

    public void logout(){
        System.out.println("zostałeś pomyślnie wylogowany");
        profileLogout = true;
    }

}
