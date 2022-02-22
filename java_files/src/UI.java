import java.io.*;
import java.util.Scanner;

public class UI {

    public static void welcome(){
        System.out.println("\nWelcome to Bus Information Manager\n");
    }

    public static void printActions(){
        System.out.print("""
                Possible Actions:
                 1. Find the Shortest Path
                 2. Search for a Bus Stop
                 3. Search by arrival time
                 4. Exit/Finish the Bus Information Manager
                Please enter the number of the action you wish to take :\s""");
    }

    public static void getActions(){
        Scanner sc = new Scanner(System.in);
        try {
            boolean exit = false;
//            while (!exit) {
//                printActions();
//                while (true) {
//                    String input = sc.next();
//
//                }

//                }
        } catch (Exception e){
            System.err.println("Invalid Input System error");
        }
        sc.close();
    }

    private static void shortestPath(){

    }

    private static void busSearch(){

    }

    private static void arrivalSearch(){

    }



}
