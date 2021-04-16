// Name: Saloni Shah
// Date: 04/19/2021 (due date)
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graph0 AdjMat_0_Driver,
 *              Graph1 WarshallDriver,
 *          and Graph2 FloydDriver
 */

interface AdjacencyMatrix
{
   void addEdge(int source, int target);
   void removeEdge(int source, int target);
   boolean isEdge(int from, int to);
   String toString();   //returns the grid as a String
   int edgeCount();
   List<Integer> getNeighbors(int source);
   //public List<String> getReachables(String from);  //Warshall extension
}

interface Warshall      //User-friendly functionality
{
   boolean isEdge(String from, String to);
   Map<String, Integer> getVertices();     
   void readNames(String fileName) throws FileNotFoundException;
   void readGrid(String fileName) throws FileNotFoundException;
   void displayVertices();
   void allPairsReachability();  // Warshall's Algorithm
}

interface Floyd
{
   int getCost(int from, int to);
   int getCost(String from, String to);
   void allPairsWeighted(); 
}

public class AdjMat implements AdjacencyMatrix//,Warshall//,Floyd 
{
   private int[][] grid = null;   //adjacency matrix representation
   private Map<String, Integer> vertices = null;   // name maps to index (for Warshall & Floyd)
   /*for Warshall's Extension*/  ArrayList<String> nameList = null;  //reverses the map, index-->name
	  
   /*  enter your code here  */  
   
   //size variable
   private int size;
   
   /**
    * Size constructor; initializes the size of the grid, the grid, and the vertices map.
    * @param matSize - size of the matrix
    */
   public AdjMat(int matSize) {
      size = matSize;
      grid = new int[matSize][matSize];
      vertices = new TreeMap<String, Integer>();
   }
   
   /**
    * Adds an edge by changing the 0 at the value grid[source][target] to 1
    * @param source - the row
    * @param target - the column
    */
   public void addEdge(int source, int target) {
      
      grid[source][target] = 1;
   
   } 
   /**
    * Removes an edge by changing the 1 at the value grid[source][target] to 0
    * @param source - the row
    * @param target - the column
    */
   public void removeEdge(int source, int target) {
   
      grid[source][target] = 0;
   
   }
   
   /**
    * Checks for 1 or 0 (indicates an edge if it is 1)
    * @param from - row
    * @param to - column
    * @return true or false depending on whether the value is a 1 or 0
    */
   public boolean isEdge(int from, int to) {
   
      if(grid[from][to] == 1) {
         return true;
      }
      else {
         return false;
      }
   
   }
   
   /**
    * Returns a string representation of the grid
    * @return a string representation of the grid
    */
   public String toString() {   //returns the grid as a String
   
      String toReturn = "";
      for(int x = 0; x < grid.length; x++) {
         for(int y = 0; y < grid[0].length; y++) {
            toReturn += grid[x][y] + " ";
         }
         toReturn += "\n";
      }
      
      return toReturn;
   }
   
   /** 
    * Returns the sum of all the ones in the grid
    * @return the sum of the ones in the grid
    */
   public int edgeCount() {
      int sum = 0;
      
      for(int x = 0; x < grid.length; x++) {
         for(int y = 0; y < grid[0].length; y++) {
            if(grid[x][y] == 1) {
               sum++;
            }
         }
      }
      
      return sum;
   }
   
   /**
    * Looks for all the ones in the column and returns a list of them
    * @param source - the row
    * @return a list of all the neighbors (ones in the grid)
    */
   public List<Integer> getNeighbors(int source) {
      
      List<Integer> neighborList = new ArrayList<Integer>();
      
      for(int x = 0; x < grid[source].length; x++) {
         if(grid[source][x] == 1) {
            neighborList.add(x);
         }
      }
      
      return neighborList;
      
   }
   
   //Warshall's Methods
   
   /**
    * Checks whether the value at the specified location is a 1
    * @param from - the row
    * @param to - the column
    * @return true or false depending on the edge
    */
   public boolean isEdge(String from, String to) {
      
      return isEdge(vertices.get(from), vertices.get(to));
      
   }
   
   /**
    * Returns the vertices map
    * @return the vertices map 
    */
   public Map<String, Integer> getVertices() {
      return vertices;
   }
     
   /**
    * Reads all the city names and their indices into the vertices map
    * @param fileName - the name of the file that the cities should be read from
    * @throws FileNotFoundException - in case the file doesn't exist
    */
   public void readNames(String fileName) throws FileNotFoundException {
      Scanner infile = new Scanner(new File(fileName));
      int count = infile.nextInt();
      infile.nextLine();
      for(int i = 0; i < count; i++){
         vertices.put(infile.next(), i);
      }
   }
   
   /**
    * Reads the adjacency matrix from the map
    * @param fileName - the name of the file that the grid should be read from
    * @throws FileNotFoundException - in case the file doesn't exist
    */
   public void readGrid(String fileName) throws FileNotFoundException {
      
      Scanner infile = new Scanner(new File(fileName));
      int rows = infile.nextInt();
      infile.nextLine();
      
      for(int x = 0; x < rows; x++) {
         String[] rowArray = infile.nextLine().split(" ");
         for(int y = 0; y < rows; y++){
            grid[x][y] = Integer.parseInt(rowArray[y]);
         }
      }
      
   }
   
   /**
    * Displays the index and then the city
    */
   public void displayVertices() {
      for(String str : vertices.keySet()) {
         System.out.println(vertices.get(str) + " - " + str);
      }
   }
   
   /**
    * Uses Warshall's algorithm to check reachability
    * if [i, v] and [v, j] are edges, then [i, j] is reachable
    */
   public void allPairsReachability() {  // Warshall's Algorithm
      for(int v = 0; v < grid.length; v++) {
         for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid.length; j++) {
               if(grid[i][v] == 1 && grid[v][j] == 1) {
                  grid[i][j] = 1;
               }
            }
         }
      }
   }
   
   /**
    * Returns a list representation of all the reachable cities from a source
    * @param from - the row
    * @return a List of type String with names of all the reachable cities
    */
   public List<String> getReachables(String from) {
      
      List<String> reachList = new ArrayList<String>();
      int row = vertices.get(from);
      
      for(String s : vertices.keySet()) {
         if(grid[row][vertices.get(s)] == 1) {
            reachList.add(s);
         }
      }
      
      return reachList;
      
   }
   
   
}