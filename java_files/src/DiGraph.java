//@author: Max Cunningham
import java.util.*;

public class DiGraph<V> {
    public int numV, numE;

    public HashMap<V,HashMap<V, Double>> edg;

    DiGraph() {
        this.edg = new HashMap<>();
    }

    public HashMap<V,Double> getEdgesMap(V v){
        return edg.get(v);
    }

    public ArrayList<V> getEdges(V v){
        HashMap<V,Double> map = getEdgesMap(v);
        return new ArrayList<>(map.keySet());
    }

    public void addEdge(V V1, V V2, Double dist) {
        numE++;
        if (!edg.containsKey(V1)) {
            addVertex(V1);
        }
        if(!edg.containsKey(V2)){
            addVertex(V2);
        }
        if(edg.get(V1).containsKey(V2)){
            dist = Math.min(edg.get(V1).get(V2),dist);
        }
        edg.get(V1).put(V2, dist);
    }

    public void addVertex(V v){
        numV++;
        edg.put(v, new HashMap<>());
    }

    public double getDist(V V1, V V2) {
        if(!edg.get(V1).containsKey(V2)){
            return -1.0;
        }
        return edg.get(V1).get(V2);
    }

    public boolean isEdge(V V1, V V2) {
        return edg.get(V1).containsKey(V2);
    }

    public int getV() {
        return numV;
    }


    public HashMap<V,Double> dijkstra(V V1, DiGraph graph ) {
        HashMap<V,Double> distFromV = new HashMap<V,Double>();
        HashMap<V,V> edgeTo = new HashMap<>();
        HashSet<V> visited = new HashSet<>();
        distFromV.put(V1, null);
        edgeTo.put(V1,null);
        V curNode = V1;
        for (int k = 0; k < graph.getV() && curNode !=null; k++) {
            visited.add(curNode);
            ArrayList<V> list = graph.getEdges(curNode);
            for (Object vert : list) {
                if (graph.isEdge(curNode, vert)) {
                    if (distFromV.get((V)vert) == null) {
                        distFromV.put((V)vert, distFromV.get(curNode) + graph.getDist(curNode, vert));
                    } else {
                        distFromV.put((V)vert, Math.min(distFromV.get(vert), distFromV.get(curNode) + graph.getDist(curNode, vert)));
                    }
                }
            }
            curNode = nextSmallest(distFromV, visited);
        }
        return distFromV;
    }

    public String dijkstra(V V1, V V2) {
        try {
            HashMap<V, Double> distFromV = new HashMap<V, Double>();
            HashMap<V, V> edgeTo = new HashMap<>();
            HashSet<V> visited = new HashSet<>();
            distFromV.put(V1, 0.0);
            edgeTo.put(V1, null);
            V curNode = V1;
            for (int k = 0; k < getV() && curNode != null; k++) {
                visited.add(curNode);
                ArrayList<V> list = getEdges(curNode);
                for (V vert:list) {
                    if (isEdge(curNode, (V) vert)) {
                        if (distFromV.get((V) vert) == null) {
                            distFromV.put((V) vert, distFromV.get(curNode) + getDist(curNode, (V) vert));
                            edgeTo.put((V) vert, curNode);
                        } else {
                            if (distFromV.get(curNode) + getDist(curNode, (V) vert) < distFromV.get(vert)) {
                                distFromV.put((V) vert, distFromV.get(curNode) + getDist(curNode, (V) vert));
                                edgeTo.put((V) vert, curNode);
                            }
                        }
                    }
                }
                curNode = nextSmallest(distFromV, visited);
                if (curNode == V2) {
                    ArrayList<V> arr_list =  new ArrayList<>();
                    double dist = distFromV.get(curNode);
                    while (curNode != null) {
                        arr_list.add(curNode);
                        curNode = edgeTo.get(curNode);
                    }
                    String ans = "Distance: "+dist+ " Route: ";
                    for(int i=arr_list.size()-1;i>0;i--){
                        ans = ans + arr_list.get(i)+" -> ";
                    }
                    ans = ans + arr_list.get(0);
                    return ans;
                }
            }
            return "Distance: Location Unreachable";
        } catch (Exception e){
            System.out.println(e);
            return "There Was an error place not found";
        }

    }

    public V nextSmallest(HashMap<V,Double> dist, HashSet<V> visited){
        V smallest = null;
        boolean visit = false;
        for(V vert : dist.keySet()){
            if(!visited.contains(vert) && dist.get(vert)!=null){
                if(!visit){
                    smallest = (V)vert;
                    visit = true;
                }
                if(dist.get(vert)<dist.get(smallest)){
                    smallest = (V)vert;
                }
            }
        }
        return smallest;
    }


}
