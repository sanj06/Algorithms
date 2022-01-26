/**
 * This class implements the adjacency matrix
 * way of representing graphs and related functionality
 */
public class Graph{

    //double[][] adjMatrix = {{0,1,5}, {1,0,2}, {5,2,0}};;
    double[][] adjMatrix;
    int numberOfVertices;

    /**
     * Constructor to initialize the adjacency matrix
     * @param numberOfVertices - |V|
     */
    public Graph(int numberOfVertices){
        this.numberOfVertices = numberOfVertices;
        this.adjMatrix = 
                new double[this.numberOfVertices][this.numberOfVertices];
        this.populateGraph();
    }

    /**
     * This method populates a complete graph
     * of size numberOfVertices with random values
     * between 0 and 1
     */
    public void populateGraph(){
        for(int i=0; i<this.numberOfVertices; i++){
            for(int j=i; j<this.numberOfVertices; j++){
                if(i == j){
                    this.adjMatrix[i][j] = 0;
                    continue;
                }
                double edgeWeight = Math.random();
                this.adjMatrix[i][j] = edgeWeight;
                this.adjMatrix[j][i] = edgeWeight;

            }
        }
        
    }

    /**
     * This method checks whether there is an edge 
     * between the given two vertices
     * @param vertex_1 - first vertex
     * @param vertex_2 - second vertex
     * @return
     */
    public boolean isAdjacent(int vertex_1, int vertex_2){
        if(this.adjMatrix[vertex_1][vertex_2] > 0.0){
            return true;
        }
        return false;
    }

    /**
     * This method prints the graph as 
     * an adjacency matrix
     */
    public void printGraph(){
        for(int i=0; i<this.numberOfVertices; i++){
            for(int j=0; j<this.numberOfVertices; j++){
                System.out.print(this.adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(3);
        g.printGraph();
    }

}
