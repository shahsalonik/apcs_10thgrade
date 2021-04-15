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
   
   int size;
   
   public AdjMat(int matSize) {
      size = matSize;
      grid = new int[matSize][matSize];
   }
   
   public void addEdge(int source, int target) {
      
      grid[source][target] = 1;
   
   } 
   public void removeEdge(int source, int target) {
   
      grid[source][target] = 0;
   
   }
   
   public boolean isEdge(int from, int to) {
   
      if(grid[from][to] == 1) {
         return true;
      }
      else {
         return false;
      }
   
   }
   
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
   
   public List<Integer> getNeighbors(int source) {
      
      List<Integer> neighborList = new ArrayList<Integer>();
      
      for(int x = 0; x < grid[source].length; x++) {
         if(grid[source][x] == 1) {
            neighborList.add(x);
         }
      }
      
      return neighborList;
      
   }
   
   
}