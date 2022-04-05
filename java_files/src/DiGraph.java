//@author: Max Cunningham
import java.util.*;

public class DiGraph<V> {
    public int numV, numE;

    public HashMap<V,HashMap<V, Double>> edg;

    /*
    * This class is a directed graph data structure used to store the relationship between bus stops it contains
    * waited edges and uses dijkstra search to find the shortest path between 2 stops if 1 exists
    */
    DiGraph() {
        this.edg = new HashMap<>();
    }

    /*
     * @brief: This method returns all edges connected to edge v and also the cost of those edges
     *
     * @param:
     *       v: this is the vertex that the user wants the edges connected to
     *
     * @return:
     *       the hashmap containing all adjacent vertices and their associated edge costs
     */
    public HashMap<V,Double> getEdgesMap(V v){
        return edg.get(v);
    }

    /*
     * @brief: this method returns a list of all edges connected to V but does not return their associated costs
     *
     * @param:
     *      v: this is the vertex that the user wants the edges connected to
     *
     * @return:
     *        The list containing all adjacent vertices
     */
    public ArrayList<V> getEdges(V v){

        HashMap<V,Double> map = getEdgesMap(v);
        return new ArrayList<>(map.keySet());
    }

    /*
     * @brief: This method adds an edge to the graph and if one or both of the verticies are not in the graph
     *         adds them to the graph as well
     *
     * @param:
     *       V1: the initial/starting vertex
     *
     *       V2: the secondary / destination vertex
     *
     *       dist: the associated cost between the two vertices
     *
     * @return:
     *      NULL
     */
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

    /*
     * @brief: This method adds a vertex to the grpah
     *
     * @param:
     *       v: the vertex to be added
     *
     * @return:
     *         NULL
     */
    public void addVertex(V v){
        numV++;
        edg.put(v, new HashMap<>());
    }

    /*
     * @brief: the method gets the cost associated with an edge between two vertices
     *
     * @param:
     *       V1: the initial/starting vertex
     *
     *       V2: the secondary / destination vertex
     *
     * @return:
     *          the associated cost with the edge between V1 and V2
     */
    public double getDist(V V1, V V2) {
        if(!edg.get(V1).containsKey(V2)){
            return -1.0;
        }
        return edg.get(V1).get(V2);
    }

    /*
     * @brief: This method checks if there exists an edge between two vertices
     *
     * @param:
     *       V1: the initial/starting vertex
     *
     *       V2: the secondary / destination vertex
     *
     * @return:
     *         if the edge exists or not
     */
    public boolean isEdge(V V1, V V2) {
        return edg.get(V1).containsKey(V2);
    }

    /*
     * @brief: This returns the number of vertices in the graph
     *
     * @param:
     *        NULL
     *
     * @return:
     *          the number of vertices
     */
    public int getV() {
        return numV;
    }

    /*
     * @brief: This method searches for the shortest distance between two vertices
     *
     * @param:
     *       V1: the initial/starting vertex
     *
     *       V2: the secondary / destination vertex
     *
     * @return:
     *         A string containing the total distance between the two vertices and teh path to get there if one exists
     *         or it says that no such path exists
     */
    public String dijkstra(V V1, V V2) {
        try {
            if(!edg.containsKey(V1) || !edg.containsKey(V2)){
                String ans = "\nGraph does not contain stop ";
                if(!edg.containsKey(V1) && !edg.containsKey(V2)){
                    ans = ans +V1+" or "+V2+".";
                } else if(!edg.containsKey(V1)){
                    ans = ans + V1+".";
                } else{
                    ans = ans + V2+".";
                }
                return ans;
            }


            HashMap<V, Double> distFromV = new HashMap<>();
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
                if (curNode.equals(V2)) {
                    ArrayList<V> arr_list =  new ArrayList<>();
                    double dist = distFromV.get(curNode);
                    while (curNode != null) {
                        arr_list.add(curNode);
                        curNode = edgeTo.get(curNode);
                    }
                    String ans = "Distance: "+dist+ "\n Route: ";
                    for(int i=arr_list.size()-1;i>0;i--){
                        ans = ans + arr_list.get(i)+(i%10==0?"\n":"")+" -> ";
                    }
                    ans = ans + arr_list.get(0);
                    return ans;
                }
            }
            return "\nDistance: Location Unreachable";
        } catch (Exception e){
            System.out.println(e);
            return "\nThere Was an Error Place Not Found";
        }

    }

    /*
     * @brief: This method prints all stops that can be reached via the initial stop in order of distance from the
     *         initial stop
     *
     * @param:
     *          V1: the initial/starting vertex
     *
     * @return:
     *          NULL
     */
    public void printReachableStops(V V1){
        if(!edg.containsKey(V1)){
            System.out.println("\nThe graph does not contain stop "+V1);
            return;
        }

        HashMap<V, Double> distFromV = new HashMap<>();
        HashMap<V, V> edgeTo = new HashMap<>();
        HashSet<V> visited = new HashSet<>();
        distFromV.put(V1, 0.0);
        edgeTo.put(V1, null);
        V curNode = V1;
        int count =1;

        while ( curNode != null) {
            visited.add(curNode);
            System.out.print(curNode+(count%20==0?",\n":", "));
            count++;
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
        }
        System.out.println("");
    }

    /*
     * @brief: This method finds the next smallest node that can be reached in the graph
     *
     * @param:
     *       dist: the current distance for each node from the initial node
     *
     *       visited: all nodes that have been visited so far
     *
     * @return:
     *      The node with the smallest distance from the starting node
     */
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
