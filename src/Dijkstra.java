import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Dijkstra {

    public JButton btn;
    public JLabel info;
    public JTextArea path;
    private int distance;
    private ArrayList<String> pathRaw;

    Dijkstra(){
        this.btn = new JButton("GO");
        this.btn.setBackground(Color.decode("#49B8C5"));
        this.btn.setFont(new Font("Georgia", Font.BOLD, 16));
        this.info = new JLabel("");
        this.info.setFont(new Font("Georgia", Font.BOLD, 12));
        this.info.setForeground(Color.decode("#ff4c4c"));
        this.path = new JTextArea("");
        this.path.setEditable(false);
        this.path.setFont(new Font("Georgia", Font.BOLD, 14));
        this.path.setForeground(Color.decode("#707070"));
        this.distance = 0;
        this.pathRaw = new ArrayList<String>();
    }

    public int getDistance(){
        return distance;
    }

    public ArrayList<String> getPathRaw(){
        return this.pathRaw;
    }

    public String shortestPath(Cities[] cities, int countOfCities, String currentPosition, String destination){
        // SOURCE AND DEST already highlighted on map
        // Calc and display shortest path from SOURCE to DEST
        int currentIndex;  // current index of unchecked city to be worked with
        int nextIndex = 0;  // next index to be worked with
        AdjacentNodes currentAdjacent;  // current node in the list

        //System.out.println("hello: " + currentPosition + "\t" + destination);

        for (currentIndex = 0; currentIndex < countOfCities; currentIndex++) {
            if (cities[currentIndex].getName().equalsIgnoreCase(currentPosition)) {
                cities[currentIndex].setBestDistance(0);
                break;
            }// end if
        }// end for

        while (notFullyVisited(cities, countOfCities)) {

            Cities temp = new Cities();
            currentAdjacent = cities[currentIndex].getAdjacentHead();

            // Dijkstra's Algorithm
            while (!(currentAdjacent == null)) {

                // adds current city's best distance to one of it's adjacent
                // city's best distance
                int currentDistance = cities[currentIndex].getBestDistance() +
                        currentAdjacent.getCitiesDistance();

                if (currentDistance < currentAdjacent.getCities().getBestDistance()) {
                    currentAdjacent.getCities().setBestDistance(currentDistance);
                    currentAdjacent.getCities().setIsNext(cities[currentIndex]);
                }// end if

                currentAdjacent = currentAdjacent.next;



                // import java.util.*;
                //import java.lang.*;
                //import java.io.*;
                //
                //class ShortestPath {
                //    // A utility function to find the vertex with minimum distance value,
                //    // from the set of vertices not yet included in shortest path tree
                //    static final int V = 9;
                //    int minDistance(int dist[], Boolean sptSet[])
                //    {
                //        // Initialize min value
                //        int min = Integer.MAX_VALUE, min_index = -1;
                //
                //        for (int v = 0; v < V; v++)
                //            if (sptSet[v] == false && dist[v] <= min) {
                //                min = dist[v];
                //                min_index = v;
                //            }
                //
                //        return min_index;
                //    }
                //
                //    // A utility function to print the constructed distance array
                //    void printSolution(int dist[])
                //    {
                //        System.out.println("Vertex \t\t Distance from Source");
                //        for (int i = 0; i < V; i++)
                //            System.out.println(i + " \t\t " + dist[i]);
                //    }
                //
                //    // Function that implements Dijkstra's single source shortest path
                //    // algorithm for a graph represented using adjacency matrix
                //    // representation
                //    void dijkstra(int graph[][], int src)
                //    {
                //        int dist[] = new int[V]; // The output array. dist[i] will hold
                //        // the shortest distance from src to i
                //
                //        // sptSet[i] will true if vertex i is included in shortest
                //        // path tree or shortest distance from src to i is finalized
                //        Boolean sptSet[] = new Boolean[V];
                //
                //        // Initialize all distances as INFINITE and stpSet[] as false
                //        for (int i = 0; i < V; i++) {
                //            dist[i] = Integer.MAX_VALUE;
                //            sptSet[i] = false;
                //        }
                //
                //        // Distance of source vertex from itself is always 0
                //        dist[src] = 0;
                //
                //        // Find shortest path for all vertices
                //        for (int count = 0; count < V - 1; count++) {
                //            // Pick the minimum distance vertex from the set of vertices
                //            // not yet processed. u is always equal to src in first
                //            // iteration.
                //            int u = minDistance(dist, sptSet);
                //
                //            // Mark the picked vertex as processed
                //            sptSet[u] = true;
                //
                //            // Update dist value of the adjacent vertices of the
                //            // picked vertex.
                //            for (int v = 0; v < V; v++)
                //
                //                // Update dist[v] only if is not in sptSet, there is an
                //                // edge from u to v, and total weight of path from src to
                //                // v through u is smaller than current value of dist[v]
                //                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v])
                //                    dist[v] = dist[u] + graph[u][v];
                //        }
                //
                //        // print the constructed distance array
                //        printSolution(dist);
                //    }
                //
                //    // Driver method
                //    public static void main(String[] args)
                //    {
                //        /* Let us create the example graph discussed above */
                //        int graph[][] = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
                //                                      { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                //                                      { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
                //                                      { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
                //                                      { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                //                                      { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
                //                                      { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
                //                                      { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                //                                      { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
                //        ShortestPath t = new ShortestPath();
                //        t.dijkstra(graph, 0);
                //    }
                //} 
            }// end while

            // Set the current city's adjacent city as visited
            cities[currentIndex].setVisited(true);

            temp.setBestDistance(Integer.MAX_VALUE);

            // loop to find the next city with lowest distance
            for (currentIndex = 0; currentIndex < countOfCities; currentIndex++){
                if((cities[currentIndex].getBestDistance() < temp.getBestDistance())
                        && (cities[currentIndex].getVisited() == false))
                {
                    temp.setBestDistance(cities[currentIndex].getBestDistance());
                    nextIndex = currentIndex;
                }// end if
            }// end for

            currentIndex = nextIndex;

        }// end while

        String retval = shortestPathPoints(cities, countOfCities, destination);

        return retval;
    }

    private boolean notFullyVisited(Cities[] cities, int countOfCities){
        // initializing notVisited to false
        boolean notVisited = false;

        //loop to iterate the array elements (objects)
        for (int i = 0; i < countOfCities; i++){

            //calling getVisited function for each object and inversing the result using '!'
            //statements inside will be executed if city is not visited (getVisited returns false)
            if (!(cities[i].getVisited())){

                //set the notVisited to true as any one city from array if Cities is not visited
                notVisited = true;
                break;
            }// end if
        }// end for

        return notVisited;

    }// end notFullyVisited

    private String shortestPathPoints(Cities[] cities, int countOfCities, String s){
        // to print the shortest way between the source and destination

        StringBuilder sb = new StringBuilder("");
        this.pathRaw = new ArrayList<String>(); // reset

        Cities current = null;
        String[] points = new String[150]; //max points including the starting point
        int[] currentDistances = new int[150]; // displays current distance taken so far next to city name

        int pointsCount = -1;
        int distCount = -1;

        for (int i = 0; i < countOfCities; i++){
            if(cities[i].getName().equalsIgnoreCase(s)){
                current = cities[i];
                break;
            }// end if
        }// end for

        distance = current.getBestDistance(); // this is the DEST node, its best distance is total distance from SOURCE to DEST

        while (current != null){
            points[++pointsCount] = current.getName();
            currentDistances[++distCount] = current.getBestDistance();

            current = current.getIsNext();
        }// end while

        for (int i = pointsCount; i > 0; i--){

            sb.append("- (" + Integer.toString(currentDistances[i]) + ") "  + points[i] + "\n");
            pathRaw.add(points[i]);

            pointsCount--;
            distCount--;
        }// end for


        pathRaw.add(points[pointsCount]);
        sb.append("- (" + Integer.toString(currentDistances[distCount--]) + ") " + points[pointsCount--]);

        return sb.toString();
    }
}
