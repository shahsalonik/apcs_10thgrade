// Name: Saloni Shah
// Date: 06/01/2021 (due date)
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graphs6: Dijkstra
 *              Graphs7: Dijkstra with Cities
 */

class Edge 
{
   public final wVertex target;  //if it's public, you don't need accessor methods
   public final double weight;   //if it's public, you don't need accessor methods
  
   public Edge(wVertex argTarget, double argWeight) 
   {
      target = argTarget;
      weight = argWeight;
   }
}

interface wVertexInterface 
{
   String getName();
   double getMinDistance();
   void setMinDistance(double m);
   wVertex getPrevious();   //for Dijkstra 7
   void setPrevious(wVertex v);  //for Dijkstra 7
   ArrayList<Edge> getAdjacencies();
   void addEdge(wVertex v, double weight);   
   int compareTo(wVertex other);
}

class wVertex implements Comparable<wVertex>, wVertexInterface
{
   private final String name;
   private ArrayList<Edge> adjacencies;
   private double minDistance = Double.POSITIVE_INFINITY;
   private wVertex previous;  //for building the actual path in Dijkstra 7
   
   /*  enter your code for this class here   */ 
   
   /**
   * Constructor for wVertex; initializes the adjacencies list
   * @param argName - the name of the vertex
   */
   public wVertex(String argName) {
      name = argName;
      adjacencies = new ArrayList<Edge>();
   }
   
   /**
   * Returns the name of the wVertex
   * @return a String representing the name
   */
   public String getName() {
      return name;
   }
   
   /**
   * Returns the minimum distance
   * @return a double representing the min distance
   */
   public double getMinDistance() {
      return minDistance;
   }
   
   /**
   * Sets the minimum distance
   * @param m - a double representing the new min distance
   */
   public void setMinDistance(double m) {
      minDistance = m;
   }
   
   /**
   * Returns the previous vertex
   * @return wVertex representing the previous vertex
   */
   public wVertex getPrevious() {   
      return previous;
   }
   
   /**
   * Sets the previous vertex
   * @param v - a wVertex representing the previous vertex
   */
   public void setPrevious(wVertex v) {
      previous = v;
   }
   
   /**
   * Returns the adjacencies list
   * @return an ArrayList with the edges
   */
   public ArrayList<Edge> getAdjacencies() {
      return adjacencies;
   }
   
   /**
   * Adds a new edge
   * @param v - a new wVertex representing the new edge
   * @param weight - the weight of the edge
   */
   public void addEdge(wVertex v, double weight) {
      adjacencies.add(new Edge(v, weight));
   }
   
   /**
   * Compares two wVertices by subtracting the distance
   * @return an int representing the differences between the min distances
   */
   public int compareTo(wVertex other) {
      return (int) (minDistance - other.minDistance);
   }
   
}

interface AdjListWeightedInterface 
{
   List<wVertex> getVertices();
   Map<String, Integer> getNameToIndex();
   wVertex getVertex(int i);   
   wVertex getVertex(String vertexName);
   void addVertex(String v);
   void addEdge(String source, String target, double weight);
   void minimumWeightPath(String vertexName);   //Dijkstra's
}

/* Interface for Graphs 7:  Dijkstra with Cities 
 */

interface AdjListWeightedInterfaceWithCities 
{       
   List<String> getShortestPathTo(wVertex v);
   AdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException ;
}
 

public class AdjListWeighted implements AdjListWeightedInterface //,AdjListWeightedInterfaceWithCities
{
   private List<wVertex> vertices = new ArrayList<wVertex>();
   private Map<String, Integer> nameToIndex = new HashMap<String, Integer>();
   //the constructor is a no-arg constructor 
   public AdjListWeighted() {
   
   }
   
   /*  enter your code for Graphs 6 */ 
   
   /**
   * Returns the vertices
   * @return a list representing the vertices
   */
   public List<wVertex> getVertices() {
      return vertices;
   }
   
   /**
   * Returns the map of the names to indices
   * @return a map representing names and their corresponding indices
   */
   public Map<String, Integer> getNameToIndex() {
      return nameToIndex;
   }
   
   /**
   * Returns the wVertex with that index
   * @param i - the index of the vertex
   * @return a wVertex with that index
   */
   public wVertex getVertex(int i) {
      return vertices.get(i);
   }
     
   /**
   * Returns the wVertex with that name
   * @param vertexName - the name of the vertex
   * @return a wVertex with that name
   */
   public wVertex getVertex(String vertexName) {
      return getVertex(nameToIndex.get(vertexName));
   }
   
   /**
   * Adds a new wVertex
   */
   public void addVertex(String v) {   
      if(!nameToIndex.containsKey(v)) {
         nameToIndex.put(v, vertices.size());
         vertices.add(new wVertex(v));
      }
      
   }
   
   /**
   * Adds a new edge with the source, target and weight specified.
   * @param source - the starting point
   * @param target - the ending point
   * @param weight - the weight
   */
   public void addEdge(String source, String target, double weight) {
      addVertex(source);
      addVertex(target);
      getVertex(source).getAdjacencies().add(new Edge(getVertex(target), weight));
   }
   
   /**
   * Figures out a path with the lowest weight sum.
   * @param vertexName - the starting point
   */
   public void minimumWeightPath(String vertexName) {   //Dijkstra's
      PriorityQueue<wVertex> pq = new PriorityQueue<wVertex>();
      wVertex source = getVertex(vertexName);
      
      for(wVertex t : vertices) {
         t.setMinDistance(Double.POSITIVE_INFINITY);
      }
      
      pq.add(source);
      source.setMinDistance(0);
      pq.add(source);
      
      while(!pq.isEmpty()) {
         
         wVertex v = pq.remove();
         
         for(Edge e: v.getAdjacencies()) {
            if(v.getMinDistance() + e.weight < e.target.getMinDistance()) {
               e.target.setMinDistance(v.getMinDistance() + e.weight);
               e.target.setPrevious(v);
               pq.add(e.target);
            }  
         }
      }
   } 
   
   
   /**
   * Returns the shortest path and returns the names stored in a list.
   * @param v - the starting point
   * @return a list of the path
   */
   public List<String> getShortestPathTo(wVertex v) {
      LinkedList<wVertex> list = new LinkedList<wVertex>();
      list.addFirst(v);
      wVertex prev = v.getPrevious();
      List<String> toRet = new ArrayList<String>();
      
      if(prev == null) {
         for(wVertex x : list) {
            toRet.add(x.getName());
         }
         return toRet;
      }
         
      list.addFirst(prev);
      
      while(prev.getMinDistance() != 0.0) {
         prev = prev.getPrevious();
         list.addFirst(prev);
      }
      
      for(wVertex c : list) {
         toRet.add(c.getName());
      }
      
      return toRet; 
   }
   
   
   /**
   * Makes a graph with the data provided.
   * @param vertexNames - the names of the vertices
   * @param edgeListData - the weights and the edges
   * @return the graph itself
   */
   public AdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException {
      Scanner vNames = new Scanner(vertexNames);
      Scanner eData = new Scanner(edgeListData);
      vNames.next();
      
      while(vNames.hasNext()) {
         addVertex(vNames.next());
      }
      
      while(eData.hasNext()) {
         addEdge(eData.next(), eData.next(), eData.nextInt());
         if(eData.hasNextLine()) {
            eData.nextLine();
         }
      }
      
      return this;
      
   }
           
}
   
   
   /*  enter your code for two new methods in Graphs 7 */
   
   


