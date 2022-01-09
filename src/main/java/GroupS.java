import App.App;
import App.LandingPage.Login;

public class GroupS {
    public static void main(String[] args) throws Exception {

//        App appStart = new App();
//        while (App.isProgramOn()){
//            appStart.startProgram();
//        }

        Login login = new Login();
        login.testLogin("bartekm", "haslo");

    }
}
