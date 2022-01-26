import java.util.*;
/**
 * This class implements the Dijkstra's shortest 
 * path algorithm and counts the number of times
 * decrease-key function was performed for various
 * complete graphs of size n
 */
public class Dijkstra{

    //constants
    public static final Double INFINITY = Double.MAX_VALUE;
    public static final int VISITED = 0;
    public static final int NOT_VISITED = -1;



    //instance variables
    Graph G; //adjacency matrix
    int numberOfVertices; //|V|
    HashMap<Node,Node> prev; //stores previous visited node
    Node[] map; //maps vertex number to corresponding node
    int[] visited; //vertices explored or not
    TreeSet<Node> priorityQueue; //priority queue
    int sourceVertex; //start vertex
    public int decreaseKeyCount = 0; //count number of times decrease_key() was called
    
    /**
    * Node class maintains each vertex and it's distance from source 
    */
    class Node implements Comparable<Node>{
        public int id;
        public  double dist;

        /**
         * Override the compareTo method
         * Maintain the node with minimum distance at front
         * @param other
         * @return
         */
        @Override
        public int compareTo(Node other){
            //if distances are same then compare vertex id's
            if(this.dist == other.dist){
                return Integer.valueOf(this.id).compareTo(other.id);
            }
            else{
                return Double.valueOf(this.dist).compareTo(other.dist);
            }
        }

        public Node(int id){
            this.id = id;
            this.dist = INFINITY;
        }
    }

    /** 
     *Constructor with number of vertices in graph 
     and the source vertex arguments
     @param numberOfVertices - |V|
     @param sourceVertex - start vertex
    */ 
    public Dijkstra(int numberOfVertices, int sourceVertex){
        this.G = new Graph(numberOfVertices);
        this.numberOfVertices = numberOfVertices;
        this.sourceVertex = sourceVertex;
        this.prev  = new HashMap<>();
        this.priorityQueue = new TreeSet<>();    
        this.visited = new int[this.numberOfVertices];
        this.map = new Node[this.numberOfVertices];

    }

    /**
     * Initializes distance, visited, priority queue and sourceVertex
     */
    public void initialize(){
        for(int i=0; i<this.numberOfVertices; i++){
            Node newNode = new Node(i);
            
            //if source vertex initialize distance as 0
            //Also, mark explored
            if(i == sourceVertex){
                newNode.dist = 0;
                this.visited[i] = VISITED;
            }
            else{
                this.visited[i] = NOT_VISITED;
            }

            //add vertices to priority queue
            priorityQueue.add(newNode);
            //maintain a mapping from vertex to respective Node
            map[i] = newNode;

        }
    }    

    /**
     * Finds the shortest path to every vertex from sourceVertex
     * using Dijkstra's algorithm
     * Updates the distance and prev instance variables
     */
    public void shortestPath(){
        //initialization of distance, visited ,etc.
        initialize();
        
        while(!priorityQueue.isEmpty()){
            // get Vertex with minimum distance
            Node U = priorityQueue.pollFirst();
            // iterate over all vertices v adjacent to u
            for(int v=0; v<G.numberOfVertices; v++){
                Node V = map[v]; //Vertex node
                // every adjacent and unvisited vertex v 
                if(G.isAdjacent(V.id, U.id) && visited[V.id] == NOT_VISITED){
                    double tempDistance = U.dist + G.adjMatrix[U.id][V.id];
                    if(tempDistance < V.dist){
                        prev.put(V, U);
                        //decrease key:
                        decrease_key(V, tempDistance);
                    }
                }

            }
        }
    }

    /**
     * Performs the equivalent of decreaseKey in Priority Queue
     * @param V - current vertex Node
     * @param tempDistance - smaller distance
     */
    public void decrease_key(Node V, double tempDistance){
        priorityQueue.remove(V);
        V.dist = tempDistance;
        priorityQueue.add(V);
        this.decreaseKeyCount++;
    }

    /**
     * Gets the number of times decrease_key method was called
     * @return - returns decreaseKeyCount
     */
    public int getDecreaseKeyCount(){
        return this.decreaseKeyCount;
    }


    /**
     * Prints the shortest path from source to the dest
     * and the total distance of the path
     * @param source - start vertex
     * @param dest - end vertex
     */
    public void printShortestPath(int dest){
       Node temp = map[dest];
       ArrayList<Integer> path = new ArrayList<>();
       //add source vertex to path
       path.add(dest);
       //while dest vertex Node is reached
       while(temp != map[this.sourceVertex]){
           //find previous node
            Node previous = prev.get(temp);
            if(previous == null) break;
            path.add(previous.id);
            //find previous node's previous and so on
            temp = previous;
       }
       System.out.println(path);
       System.out.println("Distance: " + map[dest].dist);
    }

  
    
    public static void main(String[] args) {

        final int NUM_STEPS = 11;
        int[] decrease_key_count = new int[NUM_STEPS];
        int[] graphSize = new int[NUM_STEPS];
        Dijkstra d;
        for(int i=0; i<NUM_STEPS; i++){
            int n = (int)Math.pow(2, i);
            d = new Dijkstra(n, 0);
            d.shortestPath();
            decrease_key_count[i] = d.decreaseKeyCount;
            graphSize[i] = n;
        }

        for(int i=0; i<NUM_STEPS;i++){
            System.out.print(graphSize[i] + ", ");
        }
        System.out.println();
        for(int i=0; i<NUM_STEPS;i++){
            System.out.print(decrease_key_count[i] + ", ");
        }

    }
}