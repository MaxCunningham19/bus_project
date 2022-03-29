import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Input {
    String stops,stop_times, transfers;

    public Input(String stops, String stop_times, String transfers) {
        this.stops = stops;
        this.stop_times = stop_times;
        this.transfers = transfers;
    }

    public void getGraph(DiGraph<Integer> graph){
        try {
            File file = new File(stop_times);

            BufferedReader br
                    = new BufferedReader(new FileReader(file));


            br.readLine();
            String st;
            int lastStop=-1;
            int nextStop;
            String lastTime = "";
            String tripId="";
            while ((st = br.readLine()) != null){

                String[] arr = st.split(",");
                nextStop = Integer.parseInt(arr[3]);
                if(lastStop!=-1 && tripId.equalsIgnoreCase(arr[0])) {
                    graph.addEdge(lastStop, nextStop, getTime(lastTime, arr[1]));
                }
                lastStop = nextStop;
                tripId = arr[0];

            }



        } catch (Exception e){
            System.err.println(e);
        }
    }

    /*
    * this method fills the graph with edges from the two input files transfers and stop_times
    * param:
    *   graph : this is the graph that will be used to calculate the cost of a trip for the first piece of functionality
    *
    *return:
    *   null
    * */
    public void makeCostGraph(DiGraph<Integer> graph){
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
            while ((st = br.readLine()) != null){

                String[] arr = st.split(",");
                cost = 2.0; // set the cost to an automatic 2 assuming transport type is 0

                if(arr[2].equalsIgnoreCase("2")){ // if transport type is 2 then calculate and change the cost
                    cost = Double.parseDouble(arr[3]) / 100.0;
                }

                // get the stop ids
                int v1 = Integer.parseInt(arr[0]);
                int v2 = Integer.parseInt(arr[1]);
                graph.addEdge(v1,v2,cost);
            }

            // set up the file reader for the stop_times file
            file = new File(stop_times);
            br = new BufferedReader(new FileReader(file));

            // read in the column titles and initialise the values
            br.readLine();
            int lastStop;
            int curStop;
            String tripId;

            // read in the first input line
            st = br.readLine();
            String[] arr = st.split(",");
            lastStop = Integer.parseInt(arr[3]);
            tripId = arr[0];

            //loop through comparing the current line to the last line
            while ((st = br.readLine()) != null){

                arr = st.split(",");

                curStop = Integer.parseInt(arr[3]);
                // if the stops are part of the same trip and is at a valid time then add the edge to the graph with cost 1
                if(tripId.equalsIgnoreCase(arr[0]) && isValidTime(arr[1])) {
                    graph.addEdge(lastStop, curStop, 1.0);
                }
                // set the last stop to the current stop and set the tripID to the current one
                lastStop = curStop;
                tripId = arr[0];
            }


        } catch (Exception e){
            System.err.println(e);
        }
    }

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
                if(loc_arr[2].equalsIgnoreCase("WB")||loc_arr[2].equalsIgnoreCase("EB")
                        ||loc_arr[2].equalsIgnoreCase("NB")||loc_arr[2].equalsIgnoreCase("SB")){
                    stopName = "";
                    for(int i=1;i<loc_arr.length;i++){
                        stopName = stopName + loc_arr[i] + " ";
                    }
                    stopName = stopName + loc_arr[0];
                    arr[2] = stopName;

                    stopInfo = "";
                    for(int i=0;i<arr.length;i++){
                        if(i!=2) {
                            stopInfo = stopInfo + arr[i] + ",";
                        } else {
                            stopInfo = stopInfo + st + ",";
                        }
                    }
                }
                tst.put(stopName,stopInfo);
            }

        } catch (Exception e){
            System.err.println(e);
        }
    }

    public boolean isValidTime(String time){
        String[] times = time.split(":");
        int hour = Integer.parseInt(times[0]);
        int min = Integer.parseInt(times[1]);
        int sec = Integer.parseInt(times[2]);
        if(hour > 23) return false;
        if(min > 59) return false;
        return sec <= 59;
    }

    private double getTime(String timeOne, String timeTwo){
        return -1.0;
    }

}
