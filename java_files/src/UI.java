import java.io.*;
import java.util.Scanner;

public class UI {

    public static void welcome(){
        System.out.println("\nWelcome to Bus Information Manager\n");
    }

    public static void printMainActions(){
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
            while (!exit) {
                printMainActions();
                String input = sc.nextLine();
                if(input .equalsIgnoreCase("1")){
                    shortestPath();
                } else if (input .equalsIgnoreCase("2")){
                    busSearch();
                    System.out.println("\nBusSearch\n");
                } else if(input .equalsIgnoreCase("3")){
                    arrivalSearch();
                    System.out.println("\narrivalSearch\n");
                } else if(input .equalsIgnoreCase("4")){
                    exit = true;
                    System.out.println("\nThank you for using Bus Information Manager, see you soon.");
                } else {
                    System.out.println("\nInvalid input please input a single digit between 1 and 4.\n");
                }
            }
        } catch (Exception e){
            System.err.println("Invalid Input System error 1");
        }
        sc.close();
    }

    private static boolean isValidStop(String stop){
        return true;
    }

    private static String getBusStop(String stopADJ){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print("Input the "+stopADJ+" stop you wish to use : ");
            String input = sc.nextLine();
            if(isValidStop(input)){
                return input;
            }
            System.out.println("\n..Invalid Stop..");
        }
    }

    private static String getBusStop(){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print("Input the stop you wish to use : ");
        }
    }

    private static void printStops(){
        System.out.println("\nPrintStops\n");
    }

    private static void printSPActions(){
        System.out.print("""
                Possible Actions:
                 1. List Bus Stops
                 2. Bus Stop
                 3. Finish Looking For The Shortest Path
                Please enter the number of the action you wish to take :\s""");
    }

    private static void shortestPath(){
        Scanner sc = new Scanner(System.in);
        try {
            boolean exit = false;
            while (!exit) {
                printSPActions();
                String input = sc.nextLine();
                if(input .equalsIgnoreCase("1")){
                    printStops();
                } else if (input .equalsIgnoreCase("2")){
                    String stop1 = getBusStop("first");
                    String stop2 = getBusStop("second");
                    Backend search = new Backend();
                    search.getShortestPath(stop1,stop2);
                } else if(input .equalsIgnoreCase("3")){
                    exit = true;
                    System.out.println("\n...Closing Shortest Path Search...\n");
                    sc.close();
                } else {
                    System.out.println("\nInvalid input please input a single digit between 1 and 4.\n");
                }

            }
        } catch (Exception e){
            System.err.println(e + "Invalid Input System error");
        }
        sc.close();

    }

    private static void busSearch(){

    }

    private static void arrivalSearch(){

    }



}
