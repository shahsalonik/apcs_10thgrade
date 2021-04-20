// Name: Saloni Shah
// Date: 04/26/2021 (due date)
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graphs3: EdgeList,
 *              Graphs4: DFS-BFS
 *          and Graphs5: EdgeListCities
 */

/* Graphs 3: EdgeList 
 */
interface VertexInterface
{
   String toString(); // Don't use commas in the list.  Example: "C [C D]"
   String getName();
   ArrayList<Vertex> getAdjacencies();
   void addEdge(Vertex v);
} 

class Vertex implements VertexInterface 
{
   private final String name;
   private ArrayList<Vertex> adjacencies;
  
  /* enter your code here  */
  
   public Vertex(String n) {
      name = n;
      adjacencies = new ArrayList<Vertex>();
   }
  
   public String toString() { // Don't use commas in the list.  Example: "C [C D]"
      String toReturn = getName() + " [";
      for(Vertex v : adjacencies) {
         toReturn += v.getName() + " ";
      }
      toReturn += "]";
      
      if(toReturn.length() > 3 && toReturn.substring(toReturn.length() - 2, toReturn.length() - 1).equals(" ")) {
         toReturn = toReturn.substring(0, toReturn.length() - 2) + "]";
         return toReturn;
      }
      
      return toReturn.trim();
   }
   
   public String getName() {
      return name;
   }
   
   public ArrayList<Vertex> getAdjacencies() {
      return adjacencies;
   }
   
   public void addEdge(Vertex v) {
      adjacencies.add(v);
   }
  
}   

interface AdjListInterface 
{ 
   List<Vertex> getVertices();
   Vertex getVertex(int i) ;
   Vertex getVertex(String vertexName);
   Map<String, Integer> getVertexMap();
   void addVertex(String v);
   void addEdge(String source, String target);
   String toString();  //returns all vertices with their edges (omit commas)
}

  
/* Graphs 4: DFS and BFS 
 */
interface DFS_BFS
{
   List<Vertex> depthFirstSearch(String name);
   List<Vertex> depthFirstSearch(Vertex v);
   List<Vertex> breadthFirstSearch(String name);
   List<Vertex> breadthFirstSearch(Vertex v);
   /*  three extra credit methods */
   List<Vertex> depthFirstRecur(String name);
   List<Vertex> depthFirstRecur(Vertex v);
   void depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable);
}

/* Graphs 5: Edgelist with Cities 
 */
interface EdgeListWithCities
{
   void graphFromEdgeListData(String fileName) throws FileNotFoundException;
   int edgeCount();
   int vertexCount(); //count the vertices in the object
   boolean isReachable(String source, String target);
   boolean isConnected();
}


public class AdjList implements AdjListInterface// , DFS_BFS , EdgeListWithCities
{
   private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
   private Map<String, Integer> nameToIndex = new TreeMap<String, Integer>();
  
 /* enter your code here  */
 
   public List<Vertex> getVertices() {
      return vertices;
   }
   
   public Vertex getVertex(int i) {
      return vertices.get(i);
   }
   
   public Vertex getVertex(String vertexName) {
      return getVertex(nameToIndex.get(vertexName));
   }
   
   public Map<String, Integer> getVertexMap() {
      return nameToIndex;
   }
   
   public void addVertex(String v) {
      if(nameToIndex.get(v) != null) {
         return;
      }
      nameToIndex.put(v, vertices.size());
      vertices.add(new Vertex(v));
   }
   
   public void addEdge(String source, String target) {
      if(nameToIndex.get(source) == null) {
         addVertex(source);
      }
      if(nameToIndex.get(target) == null) {
         addVertex(target);
      }
      int s = nameToIndex.get(source);
      int t = nameToIndex.get(target);
      vertices.get(s).addEdge(vertices.get(t));
   }
   
   public String toString() {  //returns all vertices with their edges (omit commas)
      String toReturn = "";
     
      for(Vertex v : vertices) {
         toReturn += v.toString() + "\n";
      }
      toReturn = toReturn.replace(",", "");
      return toReturn.trim();
   }
 
 
 
 
 /*  three extra credit methods, recursive version  */
   List<Vertex> depthFirstRecur(String name)
   {
      return null;
   }
   
   List<Vertex> depthFirstRecur(Vertex v)
   {
      return null;
   }
   
   void depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable)
   {
      
   }   
}


