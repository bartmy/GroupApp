package App.LandingPage;

import App.App;

public class LandingPage {
    public void menu() {
        System.out.println("menu");
        programMenu();
    }
    protected void programMenu() {
        System.out.println("What do you want to do ?");
        System.out.println("""
                1. login\s
                2. register\s
                3. end""");
        menuOptions();
    }

    protected void menuOptions() {
        switch (App.readInt()) {
            case 1 -> {
                login();
                programMenu();
            }
            case 2 -> {
                System.out.println("register");
                register();
                programMenu();
            }
            case 3 -> App.programEnd();
            default -> {
                App.wrongChoice();
                programMenu();
            }
        }
    }
    protected void login(){
        System.out.println("login");
        Login login = new Login();
        login.login();
    }

    protected void register() {
        Registration register = new Registration();
        register.registerNewUser();
    }
}
