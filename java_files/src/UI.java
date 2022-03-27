import java.io.*;
import java.util.Scanner;

@SuppressWarnings("ThrowablePrintedToSystemOut")
public class UI {

    public  Scanner sc = new Scanner(System.in);
    public  Backend bc = new Backend();

    public  void welcome(){
        System.out.println("\nWelcome to Bus Information Manager\n");
    }

    public  void printMainActions(){
        System.out.print("""
                Possible Actions:
                 1. Find the Shortest Path
                 2. Search for a Bus Stop
                 3. Search by arrival time
                 4. Exit/Finish the Bus Information Manager
                Please enter the number of the action you wish to take :\s""");
    }

    public  void getActions(){
        try {
            boolean exit = false;
            while (!exit) {
                printMainActions();
                String input = sc.nextLine();
                if(input .equalsIgnoreCase("1")){
                    shortestPath();
                } else if (input .equalsIgnoreCase("2")){
                    busSearch();
                } else if(input .equalsIgnoreCase("3")){
                    arrivalSearch();
                } else if(input .equalsIgnoreCase("4")){
                    exit = true;
                    System.out.println("\nThank you for using Bus Information Manager, see you soon.");
                    sc.close();
                } else {
                    System.out.println("\nInvalid input please input a single digit between 1 and 4.\n");
                }

            }
        } catch (Exception e){
            System.err.println(e);
        }

    }

    private  boolean isValidStop(String stop){
        return true;
    } //TO DO:

    private String getBusStop(String stopADJ){
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

    private  String getBusStop(){

        while(true){
            System.out.print("Input the stop you wish to use : ");
            String input = sc.nextLine();
            if(isValidStop(input)){
                return input;
            }
            System.out.println("\n..Invalid Stop..");
        }
    }

    private String getInput(){
            System.out.print("Input the search term : ");
            return sc.nextLine();
    }

    private  void printStops(){
        System.out.println("\nPrintStops\n");
    } // TO DO

    private  void printSPActions(){
        System.out.print("""
                                
                Possible Actions:
                 1. List Bus Stops
                 2. Bus Stop
                 3. Go Back
                Please enter the number of the action you wish to take :\s""");
    }

    private  void shortestPath(){
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

                    bc.getShortestPath(stop1,stop2);
                } else if(input .equalsIgnoreCase("3")){
                    exit = true;
                    System.out.println("\n...Closing Shortest Path Search...\n");
                } else {
                    System.out.println("\nInvalid input please input a single digit between 1 and 3.\n");
                }

            }
        } catch (Exception e){
            System.err.println(e + "Invalid Input System error");
        }

    }

    private  void printBSActions(){
        System.out.print("""
                                
                Possible Actions:
                 1. Full Name Search
                 2. Prefix Search
                 3. Go Back
                Please enter the number of the action you wish to take :\s""");
    }

    private  void busSearch(){
        try {
            boolean exit = false;
            while (!exit) {
                printBSActions();
                String input = sc.nextLine();
                if(input .equalsIgnoreCase("1")){
                    String stop1 = getInput();
                    bc.getFNStop(stop1);
                } else if (input .equalsIgnoreCase("2")){
                    String stop1 = getInput();
                    bc.getPrefixStop(stop1);
                } else if(input .equalsIgnoreCase("3")){
                    exit = true;
                    System.out.println("\n...Closing Bus Stop Search...\n");
                } else {
                    System.out.println("\nInvalid input please input a single digit between 1 and 3.\n");
                }

            }
        } catch (Exception e){
            System.err.println(e + "Invalid Input System error");
        }
    }

    private  void arrivalSearch(){

    }



}
