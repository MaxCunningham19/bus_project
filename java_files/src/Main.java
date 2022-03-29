import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
//        DiGraph<Integer> graph = new DiGraph<>();
//        graph.addEdge(1,2,2.0);
//        graph.addEdge(2,3,3.0);
//        graph.addEdge(1,4,3.0);
//        graph.addEdge(4,3,1.0);
//        System.out.println(graph.dijkstra(1,3,new ArrayList<>()));

        TST tst = new TST();
        tst.put("Cat", null);
        tst.put("Catalog", null);
        tst.put("DOG","1888,51874,WB HASTINGS ST FS HOLDOM AVE-,HASTINGS ST @ HOLDOM AVE,49.280436,-122.981419,ZN 99, ,0,");
        System.out.println(tst.getInfo("DOGs"));
    }
}
