import java.util.ArrayList;
import java.util.HashMap;

public class Backend {
    DiGraph<Integer> graph;
    TST tst;
    ArrayList<TimeData> list;

    Backend(String stops, String stop_time,String transfers){
        graph = new DiGraph<>();
        tst = new TST();
        list = new ArrayList<>();
        Input input = new Input(stops,stop_time,transfers);
        input.makeGraph(graph,list);
        input.makeTST(tst);
    }


    public void getShortestPath(int stop1, int stop2){
        System.out.println(graph.dijkstra(stop1,stop2));
    }

    public void getFNStop(String stop){
        System.out.println(tst.getInfo(stop));
    }

    public void getPrefixStop(String prefix){
        System.out.println(tst.getPrefix(prefix));
    }

    public void getArrivalTimes(int hours, int minutes, int seconds){
        TimeData x = new TimeData(hours,minutes,seconds,null);
        int index = binarySearch(list,0,list.size()-1,x);
        int i = index;
        while(list.get(i).equals(x)){
            i--;
            if(i <0){
                i = 0;
                break;
            }
        }
        while(list.get(i).equals(x)){
            i++;
            System.out.println(list.get(i).getInfo());
        }
    }

    int binarySearch(ArrayList<TimeData> arr, int l, int r, TimeData x)
    {
        while (l <= r) {
            int m = l + (r - l) / 2;

            // Check if x is present at mid
            if (arr.get(m).isEqual(x))
                return m;
            // If x greater, ignore left half
            if (arr.get(m).isLess(x)) {
                l = m + 1;
            }// If x is smaller, ignore right half
            else {
                r = m - 1;
            }
        }

        // if we reach here, then element was
        // not present
        return -1;
    }
}
