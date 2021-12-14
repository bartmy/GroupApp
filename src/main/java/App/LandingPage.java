package App;

import App.App;

public class LandingPage {
    public void menu(){
        System.out.println("menu");
        programMenu();
    }
    protected void programMenu(){
        System.out.println("Podaj co chcesz zrobić");
        System.out.println("""
                1. login\s
                2. rejestracja\s
                3. koniec""");
        menuOptions();
    }

    protected void menuOptions(){
        switch (App.readInt()) {
            case 1 -> {
                login();
                programMenu();
            }
            case 2 -> {
                register();
                programMenu();
            }
            case 3 -> App.programEnd();
            default -> {
                System.out.println("nie rozpoznano");
                programMenu();
            }
        }
    }
    protected void login(){
        System.out.println("login");
//        Login login = new Login();
//        String loginUsername = login.enterUsername();
//
//        if (login.loginValidation(loginUsername)){
//            User user = login.getUser(loginUsername);
//            UserPofile profile = new UserPofile();
//            profile.profile(user);
        }
    protected void register(){
        System.out.println("rejestracja");

//        System.out.println("rejestracja");
//        Register register = new Register();
//        register.registration();
    }
}
