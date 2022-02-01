package io.github.bartmy.App.LandingPage;

import io.github.bartmy.App.App;
import io.github.bartmy.App.LandingPage.login.Login;
import io.github.bartmy.App.LandingPage.registration.Registration;

public class LandingPage {

    public void startLandingPage() {
        programMenu();
    }
    public void programMenu() {
        System.out.println("What do you want to do ?");
        System.out.println("""
                1. login\s
                2. register\s
                3. end""");
        menuOptions();
    }
    public String menu() {
//        System.out.println("What do you want to do ?");
//        System.out.println("""
//                1. login\s
//                2. register\s
//                3. end""");
//        menuOptions();
        return """
                1. login\s
                2. register\s
                3. end""";
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
