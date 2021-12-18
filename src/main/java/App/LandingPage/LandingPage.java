package App.LandingPage;

import App.App;

import java.sql.SQLException;

public class LandingPage {
    public void menu() {
        System.out.println("menu");
        programMenu();
    }
    protected void programMenu() {
        System.out.println("Podaj co chcesz zrobić");
        System.out.println("""
                1. login\s
                2. rejestracja\s
                3. koniec""");
        menuOptions();
    }

    protected void menuOptions() {
        switch (App.readInt()) {
            case 1 -> {
                login();
                programMenu();
            }
            case 2 -> {
                System.out.println("rejestracja");
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
        Login login = new Login();
        login.login();
    }

    protected void register() {
        Registration register = new Registration();
        register.registerNewUser();
    }
}
