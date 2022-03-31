import java.util.ArrayList;
import java.util.HashMap;

public class Backend {
    DiGraph<Integer> graph;
    boolean hasGraph, hasTST, hasTimes;
    TST tst;
    HashMap<String,ArrayList<String>> times;
    Input input;

    Backend(String stops, String stop_time,String transfers){
        graph = new DiGraph<>();
        tst = new TST();
        times = new HashMap<>();
        input = new Input(stops,stop_time,transfers);
        hasGraph = false;
        hasTimes = false;
        hasTST = false;
    }


    public void getShortestPath(int stop1, int stop2){
        if(!hasGraph){
            input.makeGraph(graph);
            hasGraph = true;
        }
        System.out.println(graph.dijkstra(stop1,stop2));
    }

    public void getFNStop(String stop){
        if(!hasTST){
            input.makeTST(tst);
        }
        System.out.println(tst.getInfo(stop));
    }

    public void getPrefixStop(String prefix){
        if(!hasTST){
            input.makeTST(tst);
        }
        System.out.println(tst.getPrefix(prefix));
    }

    public void getArrivalTimes(int hours, int minutes, int seconds){
        if(!hasTimes){
            input.makeHashMap(times);
        }
        String time = hours +":"+minutes+":"+seconds;
        System.out.println("\n---------------");
        if(times.containsKey(time)){
            ArrayList<String> arrayList = times.get(time);
            for (String s : arrayList) {
                printInfo(s);
            }
        } else {
            System.out.println(" No Trips Match The Time "+time);
        }
        System.out.print("---------------\n");
    }

    public void printInfo(String st){
        String[] arr = st.split(",");
        System.out.println("Trip ID: "+arr[0]+ ", Arrival Time: "+arr[1].trim() + ", stop ID: "+arr[3] +", Stop Sequence: "+arr[4]+", Shape Dist Travelled: "+arr[arr.length-1]);
    }

}
