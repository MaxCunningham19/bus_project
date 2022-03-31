import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;


/*
* This class reads in the data from the input files and deals with them appropraitly for the
* tasks of this project
*
*/
public class Input {
    String stops,stop_times, transfers; // these are the file names to be read in

    public Input(String stops, String stop_times, String transfers) {
        this.stops = stops;
        this.stop_times = stop_times;
        this.transfers = transfers;
    }


    /*
    * @brief
    * this method fills the graph with edges from the two input files transfers and stop_times
    *
    * @param:
    *   graph : this is the graph that will be used to calculate the cost of a trip for the first piece of functionality
    *
    * @return:
    *   null
    * */
    public void makeGraph(DiGraph<Integer> graph){
        try {
            // set up the file reader for the transfers time
            File file = new File(transfers);

            BufferedReader br
                    = new BufferedReader(new FileReader(file));
            // read in the column labels
            br.readLine();
            // initialise the string and cost values
            String st;
            double cost;
            while ((st = br.readLine()) != null) {

                String[] arr = st.split(",");
                cost = 2.0; // set the cost to an automatic 2 assuming transport type is 0

                if (arr[2].equalsIgnoreCase("2")) { // if transport type is 2 then calculate and change the cost
                    cost = Double.parseDouble(arr[3]) / 100.0;
                }

                // get the stop ids
                int v1 = Integer.parseInt(arr[0]);
                int v2 = Integer.parseInt(arr[1]);
                graph.addEdge(v1, v2, cost);
            }
        } catch (Exception e){
            System.out.println("Error when reading from transfers");
        }

        try {
            // set up the file reader for the stop_times file
            File file = new File(stop_times);
            BufferedReader br = new BufferedReader(new FileReader(file));

            // read in the column titles and initialise the values
            br.readLine();
            int lastStop;
            int curStop;
            String tripId;

            // read in the first input line
            String st = br.readLine();
            String[] arr = st.split(",");
            lastStop = Integer.parseInt(arr[3]);
            tripId = arr[0];
            //loop through comparing the current line to the last line
            while ((st = br.readLine()) != null) {

                arr = st.split(",");

                curStop = Integer.parseInt(arr[3]);
                // if the stops are part of the same trip and is at a valid time then add the edge to the graph with cost 1
                if (tripId.equalsIgnoreCase(arr[0]) && isValidTime(arr[1])) {
                    graph.addEdge(lastStop, curStop, 1.0);
                }
                // set the last stop to the current stop and set the tripID to the current one
                lastStop = curStop;
                tripId = arr[0];
            }
        } catch (Exception e){
            System.out.println(" Error when reading from stop_times.txt");
            System.err.println(e);
        }

    }

    /*
     * @brief
     * this method fills the hashmap with all stop times and their associated data
     *
     * @param:
     *   map : this is the hashmap where the data will be stored
     *
     * @return:
     *   null
     * */
    public void makeHashMap(HashMap<String, ArrayList<String>> map){
        try {
            // set up the file reader for the stop_times file
            File file = new File(stop_times);
            BufferedReader br = new BufferedReader(new FileReader(file));

            // read in the column titles and initialise the values
            br.readLine();

            // read in the first input line
            String st = br.readLine();
            String[] arr = st.split(",");


            while ((st = br.readLine()) != null) {

                arr = st.split(",");
                String time = arr[1].trim();
                // if the stops are part of the same trip and is at a valid time then add the edge to the graph with cost 1
                if (isValidTime(time)) {
                    if (!map.containsKey(time)) {
                        map.put(time, new ArrayList<>());
                    }
                    map.get(time).add(st);
                }
            }

        } catch (Exception e){
            System.out.println(" Error when reading from stop_times.txt");
        }
    }

    /*
     * @brief
     * this method fills the TST with all the stop names and stop info
     *
     * @param:
     *   tst: this is the TST where you want the stops to be stored
     *
     * @return:
     *   null
     * */
    public void makeTST(TST tst){
        try {
            // set up the file reader for the transfers time
            File file = new File(stops);

            BufferedReader br
                    = new BufferedReader(new FileReader(file));
            // read in the column labels
            br.readLine();
            // initialise the string and cost values
            String st;
            double cost;
            while ((st = br.readLine()) != null){

                String[] arr = st.split(",");
                String[] loc_arr = arr[2].split(" ");
                String stopName = arr[2];
                String stopInfo = st;

                if(loc_arr[0].equalsIgnoreCase("WB")||loc_arr[0].equalsIgnoreCase("EB")
                        ||loc_arr[0].equalsIgnoreCase("NB")||loc_arr[0].equalsIgnoreCase("SB")){
                    stopName = "";
                    for(int i=1;i<loc_arr.length;i++){
                        stopName = stopName + loc_arr[i] + " ";
                    }
                    stopName = stopName + loc_arr[0];
                    arr[2] = stopName;

                    stopInfo = "";
                    for(int i=0;i<arr.length;i++){
                        stopInfo = stopInfo + arr[i] + ",";

                    }
                }
                tst.put(stopName,stopInfo);
            }

        } catch (Exception e){
            System.err.println(e);
        }
    }

    /*
     * @brief
     * this checks if a time is valid i.e 00:00:00 < time < 24:00:00 and that the rest of the values are correct
     * i.e min cannot be 78 or -1
     *
     * @param:
     *   time: this is the time in hour:min:sec format to be checked
     * @return:
     *   boolean : this lets you know if it is a valid time
     * */
    public boolean isValidTime(String time){
        try {
            String[] times = time.split(":");
            int hour = Integer.parseInt(times[0].strip());
            int min = Integer.parseInt(times[1].strip());
            int sec = Integer.parseInt(times[2].strip());
            if (hour > 23 || hour < 0) return false;
            if (min > 59 || min < 0) return false;
            return sec <= 59 && sec >= 0;
        } catch (Exception e){
            System.out.println("..Invalid Time..");
        }
        return false;
    }


}
