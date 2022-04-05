import java.util.Scanner;

@SuppressWarnings("ThrowablePrintedToSystemOut")
public class UI {

    public  Scanner sc;
    public  Backend bc;

    /*
    * This class acts as the buffer between the user and the backend of the project where the actual data
    *  is being queried and searched it does this through text prompts and having an instance of the backend class
    */
    UI(){
        sc = sc = new Scanner(System.in);
        bc = new Backend("stops.txt","stop_times.txt","transfers.txt");
    }

    /*
    * @brief this method prints the Welcome Message
    *
    */
    public  void welcome(){
        System.out.println("\nWelcome to Bus Information Manager\n");
    }

    /*
     * @brief this method prints the  main menu
     *
     */
    public  void printMainActions(){
        System.out.print("""
                Possible Actions:
                 1. Find the Shortest Path
                 2. Search for a Bus Stop
                 3. Search by arrival time
                 4. Exit/Finish the Bus Information Manager
                Please enter the number of the action you wish to take :\s""");
    }

    /*
     * @brief this method allows the user to move between the main tasks
     *
     */
    public  void getActions(){
        try {
            welcome();
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

//    /*
//     * @brief this method checks if its a valid stop
//     *
//     * @param stop this takes in a stop name and checks if it is a correct stop
//     */
//    private  boolean isValidStop(String stop){
//        return true;
//    } //TO DO:

    /*
     * @brief this method gets the user to input a bus stop
     *
     * @param stopADJ this takes and adjective to enhance the message
     *
     * @return String this returns the users input / bus they are searching for
     */
    private String getBusStop(String stopADJ){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print("Input the "+stopADJ+" stop you wish to use : ");
            String input = sc.nextLine();
            try {
                int parseInt = Integer.parseInt(input);
                return input;
            } catch(Exception e){
                System.out.println("\n..Invalid Stop..\n..Please Enter an Integer..\n");
            }
        }
    }

//    /*
//     * @brief this makes the user input a bus stop
//     *
//     * @return String this returns the users input / bus they are searching for
//     */
//    private  String getBusStop(){
//
//        while(true){
//            System.out.print("Input the stop you wish to use : ");
//            String input = sc.nextLine();
//            if(isValidStop(input)){
//                return input;
//            }
//            System.out.println("\n..Invalid Stop..");
//        }
//    }

    /*
     * @brief this method gets the user to input a value or string
     *
     * @return String this returns the users input
     */
    private String getInput(){
            System.out.print("Input the search term : ");
            return sc.nextLine();
    }

    /*
    *  @ brief this prints all the shortest path actions
    *
    */
    private  void printSPActions(){
        System.out.print("""
                                
                Possible Actions:
                 1. Find Bus Stop
                 2. Find all Reachable Stops
                 3. Go Back
                Please enter the number of the action you wish to take :\s""");
    }

    /*
     *  @ brief this allows the user to search for shortest path between two bus stops
     *  and to return to the main menu
     *
     */
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
                    String stop = getBusStop("starting");
                    bc.getAllStops(Integer.parseInt(stop));
                } else if(input .equalsIgnoreCase("3")){
                    exit = true;
                    System.out.println("\n...Closing Shortest Path Search...\n");
                } else {
                    System.out.println("\nInvalid input please input a single digit from 1 to 3.");
                }

            }
        } catch (Exception e){
            System.err.println(e + "Invalid Input System error");
        }

    }

    /*
     *  @ brief this prints all the actions the user has in respect to the searching for bus stop
     *
     */
    private  void printBSActions(){
        System.out.print("""
                                
                Possible Actions:
                 1. Full Name Search
                 2. Prefix Search
                 3. Go Back
                Please enter the number of the action you wish to take :\s""");
    }

    /*
     *  @ brief this allows the user to search for bus stops between two bus stops
     *  and to return to the main menu
     *
     */
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
                    System.out.println("\nInvalid input please input a single digit between 1 and 3.");
                }

            }
        } catch (Exception e){
            System.err.println(e + "Invalid Input System error");
        }
    }

    /*
     *  @ brief this prints all the actions of the arrival task
     *
     */
    private void printArrivActions(){
        System.out.print("""
                                
                Possible Actions:
                 1. Find Trips With Arrival Times
                 2. Go Back
                Please enter the number of the action you wish to take :\s""");
    }

    /*
     *  @ brief this allows the user to search for trips with a certain arrival time
     *  and to return to the main menu
     *
     */
    private  void arrivalSearch(){
            boolean exit = false;
            while (!exit) {
                printArrivActions();
                String input = sc.nextLine();
                if(input .equalsIgnoreCase("1")){
                    String time = getTime();
                    String[] arr = time.split(":");
                    try {
                        bc.getArrivalTimes(Integer.parseInt(arr[0]),Integer.parseInt(arr[1]),Integer.parseInt(arr[2]));
                    } catch (Exception e){
                        System.out.println("..Invalid time Try again..");
                    }
                } else if(input .equalsIgnoreCase("2")){
                    exit = true;
                    System.out.println("\n...Closing Arrival Time Search...\n");
                } else {
                    System.out.println("\nInvalid input please input a single digit either 1 or 2.");
                }
            }
    }

    /*
     *  @ brief: this gets the users time input
     *
     *  @return: the String containing the time the user inputted
     */
    private String getTime(){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print("Input the time you wish to search for in the form \"hour:minute:second\" :  ");
            String input = sc.nextLine();
            try {
                String[] arr = input.split(":");
                int hour = Integer.parseInt(arr[0]);
                int min = Integer.parseInt(arr[1]);
                int sec = Integer.parseInt(arr[2]);

                if(isValidTime(hour,min,sec)) {
                    return ""+hour+":"+min+":"+sec;
                }
                System.out.println("\n..Invalid Time..\n..Please Enter a Time between 00:00:00 and 23:59:59..\n");
            } catch(Exception e){
                System.out.println("\n..Invalid Time..\n..Please Enter a Time between 00:00:00 and 23:59:59..\n");
            }
        }
    }

    /*
     * @brief
     * this checks if a time is valid i.e 00:00:00 < time < 24:00:00 and that the rest of the values are correct
     * i.e min cannot be 78 or -1
     *
     * @param:
     *   hour: the hour
     *   min : the minute of the time
     *   sec : the second of the time
     * @return:
     *   boolean : this lets you know if it is a valid time
     * */
    public boolean isValidTime(int hour, int min, int sec){
        if(hour > 23 || hour <0) return false;
        if(min > 59|| min < 0) return false;
        return sec <= 59 && sec>=0;
    }

}
