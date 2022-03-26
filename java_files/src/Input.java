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
    *
    *
    *
    * param:
    *   graph : this is the graph that will be used to calculate the cost of a trip for the first piece of functionality
    *
    *
    * */
    public void makeCostGraph(DiGraph<Integer> graph){
        try {
            File file = new File(transfers);

            BufferedReader br
                    = new BufferedReader(new FileReader(file));


            br.readLine();
            String st;
            double cost;
            while ((st = br.readLine()) != null){
                String[] arr = st.split(",");
                cost = 2.0;
                if(arr[2].equalsIgnoreCase("2")){
                    cost = Double.parseDouble(arr[3]) / 100.0;
                }
                int v1 = Integer.parseInt(arr[0]);
                int v2 = Integer.parseInt(arr[1]);
                graph.addEdge(v1,v2,cost);
            }

            file = new File(stop_times);
            br = new BufferedReader(new FileReader(file));

            br.readLine();
            int lastStop=-1;
            int nextStop;
            String tripId="";
            while ((st = br.readLine()) != null){

                String[] arr = st.split(",");
                nextStop = Integer.parseInt(arr[3]);
                if(lastStop!=-1 && tripId.equalsIgnoreCase(arr[0]) && isValidTime(arr[1])) {
                    graph.addEdge(lastStop, nextStop, 1.0);
                }
                lastStop = nextStop;
                tripId = arr[0];

            }


        } catch (Exception e){
            System.err.println(e);
        }
    }

    public void makeTST(){}

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
