package App.UserProfile;

public class UserProfile {
    public void startProfile(String username, String password){
        User user = new User(username, password);
        System.out.println("jestesmy w profilu");
        System.out.println("moj login to " + user.getUsername());
        System.out.println("moje haslo to " + user.getPassword());

    }

}
