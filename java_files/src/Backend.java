import java.util.ArrayList;
import java.util.HashMap;

public class Backend {
    DiGraph<Integer> graph;
    boolean hasGraph, hasTST, hasTimes;
    TST tst;
    HashMap<String,ArrayList<String[]>> times;
    Input input;

    /*
    * This Class is a buffer between the frontend UI and the backend objects data structures and input files
    * It implements wrapper methods to make the UI calls simple and intuitive
    */

    Backend(String stops, String stop_time,String transfers){
        graph = new DiGraph<>();
        tst = new TST();
        times = new HashMap<>();
        input = new Input(stops,stop_time,transfers);
        hasGraph = false;
        hasTimes = false;
        hasTST = false;
    }


    /*
     * @brief: This method searches the graph for if a path exists between the two stops the user is looking for then
     *          prints the path if one exists to the terminal and when called for the first time it initialises the graph data structure
     *
     * @param:
     *       stop1: the initial stop where the search begins from
     *
     *       stop2: the final stop / the users ultimate destination
     *
     * @return: NULL
     */
    public void getShortestPath(int stop1, int stop2){
        if(!hasGraph){
            System.out.println("..Loading Graph...");
            input.makeGraph(graph);
            System.out.println("..Loading Complete..");
            try {
                Thread.sleep(1000);
            } catch (Exception e){

            }
            hasGraph = true;
        }
        System.out.println("\n"+graph.dijkstra(stop1,stop2));
    }

    /*
     * @brief: This method prints all stops that can be reached from the initial stop
     *         and when called for the first time it initialises the graph data structure
     *
     * @param:
     *       stop: the initial stop where the search begins from

     *
     * @return: NULL
     */
    public void getAllStops(int stop){
        if(!hasGraph){
            System.out.println("..Loading Graph...");
            input.makeGraph(graph);
            System.out.println("..Loading Complete..\n");
            try {
                Thread.sleep(1000);
            } catch (Exception e){

            }
            hasGraph = true;
        }
        System.out.println("\n--------------------");
        graph.printReachableStops(stop);
        System.out.println("--------------------");
    }

    /*
     * @brief: This wrapper method is used when the user wants to search the TST for a certain stop
     *
     * @param:
     *      stop: this is a string that the user wishes to search for
     *
     * @return: NULL
     *
     */
    public void getFNStop(String stop){
        if(!hasTST){
            System.out.println("..Loading Data...");
            try {
                Thread.sleep(1000);
            } catch (Exception e){

            }
            input.makeTST(tst);
            System.out.println("..Loading Complete...");
            try {
                Thread.sleep(1000);
            } catch (Exception e){

            }
            hasTST = true;
        }
        System.out.println("\n"+tst.getInfo(stop.toUpperCase()));
    }

    /*
     * @brief: this wrapper method searches the TST for all stops containing the prefix inputed by the user
     *
     * @param:
     *        prefix: this is the sub-string the user is searching for
     *
     * @return: NULL
     */
    public void getPrefixStop(String prefix){
        if(!hasTST){
            System.out.println("..Loading Data...");
            try {
                Thread.sleep(1000);
            } catch (Exception e){

            }
            input.makeTST(tst);
            System.out.println("..Loading Complete...");
            try {
                Thread.sleep(1000);
            } catch (Exception e){

            }
            hasTST = true;
        }
        System.out.println(tst.getPrefix(prefix.toUpperCase()));
    }

    /*
     * @brief: this method searches the HashMap to find any stops with matching arrival times
     *
     * @param:
     *       hours: the hour portion of the search time
     *
     *       minutes:the minute portion of the search time
     *
     *       seconds: the seconds portion of the search time
     *
     * @return: NULL
     */
    public void getArrivalTimes(int hours, int minutes, int seconds){
        if(!hasTimes){
            System.out.println("\n...Loading Necessary Info..");
            input.makeHashMap(times);
            try {
                Thread.sleep(1000);
            } catch (Exception e){

            }
            System.out.println("...Info Loaded..");
            hasTimes = true;
        }
        String time = hours +":"+minutes+":"+seconds;
        System.out.println("\n---------------");
        if(times.containsKey(time)){
            ArrayList<String[]> arrayList = times.get(time);
            for (String[] s : arrayList) {
                printInfo(s);
            }
        } else {
            System.out.println(" No Trips Match The Time "+time);
        }
        System.out.print("---------------\n");
    }

    /*
     * @brief: this method takes in the full string line in the times hashmap ArrayList and processes it to
     *         show the data in an appealing manner
     *
     * @param:
     *       st: this is the full data string with no adjustments made to it
     *
     * @return: NULL
     */
    public void printInfo(String[] arr){
        System.out.println("Trip ID: "+arr[0]+ ", Arrival Time: "+arr[1].trim() + ", stop ID: "+arr[3] +", Stop Sequence: "+arr[4]+", Shape Dist Travelled: "+arr[arr.length-1]);
    }

}
