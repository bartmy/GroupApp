package App.LandingPage;

import App.App;

public class LandingPage {

    public LandingPage(){
        menu();
    }


    private void menu() {
        System.out.println("menu");
        programMenu();
    }
    private void programMenu() {
        System.out.println("What do you want to do ?");
        System.out.println("""
                1. login\s
                2. register\s
                3. end""");
        menuOptions();
    }

    private void menuOptions() {
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
                App.wrongChoice();
                programMenu();
            }
        }
    }
    private void login(){
        System.out.println("login");
        new Login();
    }

    private void register() {
        System.out.println("register");
        new Registration();
    }
}
