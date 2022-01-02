package App;

import App.LandingPage.Login;

public class GroupS {
    public static void main(String[] args) {

//        App appStart = new App();
//        while (App.isProgramOn()){
//            appStart.startProgram();
//        }
        System.out.println("test if we see this in jira");
        System.out.println("test2");
        System.out.println("test3");

        Login login = new Login();
        login.testLogin("bartekm", "haslo");
    }
}
