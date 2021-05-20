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
   //wVertex getPrevious();   //for Dijkstra 7
   //void setPrevious(wVertex v);  //for Dijkstra 7
   ArrayList<Edge> getAdjacencies();
   void addEdge(wVertex v, double weight);   
   int compareTo(wVertex other);
}

class wVertex implements Comparable<wVertex>, wVertexInterface
{
   private final String name;
   private ArrayList<Edge> adjacencies;
   private double minDistance = Double.POSITIVE_INFINITY;
   //private wVertex previous;  //for building the actual path in Dijkstra 7
   
   /*  enter your code for this class here   */ 
   
   public wVertex(String argName) {
      name = argName;
      adjacencies = new ArrayList<Edge>();
   }
   
   public String getName() {
      return name;
   }
   
   public double getMinDistance() {
      return minDistance;
   }
   
   public void setMinDistance(double m) {
      minDistance = m;
   }
   
   public ArrayList<Edge> getAdjacencies() {
      return adjacencies;
   }
   
   public void addEdge(wVertex v, double weight) {
      adjacencies.add(new Edge(v, weight));
   }
     
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
  
   /*  enter your code for Graphs 6 */ 
   
   public List<wVertex> getVertices() {
      return vertices;
   }
   
   public Map<String, Integer> getNameToIndex() {
      return nameToIndex;
   }
   
   public wVertex getVertex(int i); 
   {
      return vertices.get(i);
   }
     
   public wVertex getVertex(String vertexName) {
      return getVertex(nameToIndex.get(vertexName));
   }
   
   public void addVertex(String v) {   
      if(!nameToIndex.containsKey(v)) {
         nameToIndex.put(v, vertices.size());
         vertices.add(new wVertex(v));
      }
      
   }
   
   public void addEdge(String source, String target, double weight) {
      addVertex(source);
      addVertex(target);
      getVertex(source).getAdjacencies.add(new Edge(getVertex(target), weight));
   }
   
   public void minimumWeightPath(String vertexName) {   //Dijkstra's
      PriorityQueue<wVertex> pq = new PriorityQueue<wVertex>();
      wVertex source = getVertex(vertexName);
      
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
   
   
   /*  enter your code for two new methods in Graphs 7 */
   
   
}   


