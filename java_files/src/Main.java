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
        tst.put("Cat", "11259,59845,SB IOCO RD FS SUTER BROOK WAY,IOCO RD @ SUTER BROOK WAY,49.278864,-122.827419,ZN 99, ,0,");
        tst.put("Catalog", "11108,61013,NB SHAUGHNESSY ST FS MCALLISTER AVE,SHAUGHNESSY ST @ MCALLISTER AVE,49.262588,-122.781242,ZN 99, ,0,");
        tst.put("DOG","1888,51874,WB HASTINGS ST FS HOLDOM AVE-,HASTINGS ST @ HOLDOM AVE,49.280436,-122.981419,ZN 99, ,0,");
        ArrayList<StopInfo> list = tst.keysWithPrefix("Ca");
        for(StopInfo info : list){
            System.out.println(info.print());
        }

    }
}
