import java.io.*;
import java.util.Scanner;

@SuppressWarnings("ThrowablePrintedToSystemOut")
public class UI {

    public  Scanner sc;
    public  Backend bc;

    UI(){
        sc = sc = new Scanner(System.in);
        bc = new Backend("stop.txt","stop_times.txt","transfers.txt");
    }

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
            try {
                int in = Integer.parseInt(input);
                if (isValidStop(input)) {
                    return input;
                }
            } catch(Exception e){
                System.out.println("\n..Invalid Stop..\n..Please Enter an Integer..");
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
                 1. Find Bus Stop
                 2. Go Back
                Please enter the number of the action you wish to take :\s""");
    }

    private  void shortestPath(){
        try {
            boolean exit = false;
            while (!exit) {
                printSPActions();
                String input = sc.nextLine();
                if (input .equalsIgnoreCase("1")){
                    String stop1 = getBusStop("first");
                    String stop2 = getBusStop("second");

                    bc.getShortestPath(Integer.parseInt(stop1),Integer.parseInt(stop2));
                } else if(input .equalsIgnoreCase("2")){
                    exit = true;
                    System.out.println("\n...Closing Shortest Path Search...\n");
                } else {
                    System.out.println("\nInvalid input please input a single digit either 1 or 2.\n");
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


    private void printArrivActions(){
        System.out.print("""
                                
                Possible Actions:
                 1. Find Trips With Arrival Times
                 2. Go Back
                Please enter the number of the action you wish to take :\s""");
    }
    private  void arrivalSearch(){
        try {
            boolean exit = false;
            while (!exit) {
                printArrivActions();
                String input = sc.nextLine();
                if(input .equalsIgnoreCase("1")){
                    String stop1 = getInput();
                    int hour = Integer.parseInt(getInput());
                    //bc.getArrivalTimes(hour,min,sec);
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



}
