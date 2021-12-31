package App;

import App.LandingPage.LandingPage;

import java.util.Scanner;

public class App {
    private static boolean programOn = true;

    public void startProgram(){
        System.out.println("program start");

        while (programOn){
            new LandingPage();
        }
    }

    // generic methods to be used frequently

    public static String readString(){
        Scanner read = new Scanner(System.in);
        return read.nextLine();
    }
    public static Integer readInt(){
        Scanner read = new Scanner(System.in);
        return read.nextInt();
    }
    public static void wrongChoice(){
        System.out.println("did not recognize, try again!");
    }
    public static void programEnd(){
        programOn = false;
    }

    public static boolean isProgramOn() {
        return programOn;
    }
}
