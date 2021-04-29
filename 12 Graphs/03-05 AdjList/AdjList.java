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
   void addAdjacent(Vertex v);
} 

class Vertex implements VertexInterface 
{
   private final String name;
   private ArrayList<Vertex> adjacencies;
  
  /* enter your code here  */
  
   /**
    * Constructor 
    * @param n - the name of the vertex
    */
   public Vertex(String n) {
      name = n;
      adjacencies = new ArrayList<Vertex>();
   }
  
   /**
    * Returns a String representation of the vertex and its neighbors
    * @return a String representation of the vertex and its neighbors
    */
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
   
   /**
    * Returns the name of the vertex
    * @return the name of the vertex
    */
   public String getName() {
      return name;
   }
   
   /**
    * Returns a list of all the adjacent vertices
    * @return the list of all the adjacent vertices
    */
   public ArrayList<Vertex> getAdjacencies() {
      return adjacencies;
   }
   
   /**
    * Adds a neighbor to the existing vertex
    * @param v - the vertex to be added as an adjacent
    */
   public void addAdjacent(Vertex v) {
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
   void addAdjacent(String source, String target);
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


public class AdjList implements AdjListInterface, DFS_BFS // , DFS_BFS , EdgeListWithCities
{
   private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
   private Map<String, Integer> nameToIndex = new TreeMap<String, Integer>();
  
 /* enter your code here  */
 
   /**
    * Returns the list of the vertices
    * @return the list of the vertices
    */
   public List<Vertex> getVertices() {
      return vertices;
   }
   
   /**
    * Returns a vertex given its index number 
    * @param - i the index of the vertex
    * @return the vertex to return
    */
   public Vertex getVertex(int i) {
      return vertices.get(i);
   }
   
   /**
    * Returns a vertex given its name 
    * @param - vertexName the name of the vertex
    * @return the vertex to return
    */
   public Vertex getVertex(String vertexName) {
      return getVertex(nameToIndex.get(vertexName));
   }
   
   /**
    * Returns the map of the vertex with its names
    * @return the name map
    */
   public Map<String, Integer> getVertexMap() {
      return nameToIndex;
   }
   
   /**
    * Adds a vertex given its name
    * @param - v the name of the vertex
    */
   public void addVertex(String v) {
      if(nameToIndex.get(v) != null) {
         return;
      }
      nameToIndex.put(v, vertices.size());
      vertices.add(new Vertex(v));
   }
   
   /**
    * Adds an adjacent given the name of the source and the target
    * @param source - the vertex to start
    * @param target - the vertex to end
    */
   public void addAdjacent(String source, String target) {
      if(nameToIndex.get(source) == null) {
         addVertex(source);
      }
      if(nameToIndex.get(target) == null) {
         addVertex(target);
      }
      int s = nameToIndex.get(source);
      int t = nameToIndex.get(target);
      vertices.get(s).addAdjacent(vertices.get(t));
   }
   
   /**
    * Adds an edge given the name of the source and the target
    * @param source - the vertex to start
    * @param target - the vertex to end
    */
   public void addEdge(String source, String target) {
      addAdjacent(source, target);
   }
   
   /**
    * Returns a String representation of all the vertices with their edges
    * @return a String representation of all the vertices with their edges
    */
   public String toString() {  //returns all vertices with their edges (omit commas)
      String toReturn = "";
     
      for(Vertex v : vertices) {
         toReturn += v.toString() + "\n";
      }
      toReturn = toReturn.replace(",", "");
      return toReturn.trim();
   }
   
   //DFS-BFS Methods
   
   public List<Vertex> depthFirstSearch(String name) {
      int ind = nameToIndex.get(name);
      Vertex source = getVertex(ind);
      return depthFirstSearch(source);
   }
   
   public List<Vertex> depthFirstSearch(Vertex v) {
      List<Vertex> edgeList = new ArrayList<Vertex>();
      Stack<Vertex> temp = new Stack<Vertex>();
   
      temp.push(v);
      
      while(!temp.isEmpty()) {
      
         Vertex v1 = temp.pop();
         
         if(!edgeList.contains(v1)) {
            for(Vertex v2: v1.getAdjacencies()) {
               if(!temp.contains(v2)) {
                  temp.push(v2);
               }
            }
            
            edgeList.add(v1);
            
         }
         
      }
      
      return edgeList;
   }
   
   public List<Vertex> breadthFirstSearch(String name) {
      int ind = nameToIndex.get(name);
      Vertex source = getVertex(ind);
      return breadthFirstSearch(source);
   }
   
   public List<Vertex> breadthFirstSearch(Vertex v) {
      List<Vertex> edgeList = new ArrayList<Vertex>();
      Queue<Vertex> temp = new LinkedList<Vertex>();
   
      temp.add(v);
      
      while(!temp.isEmpty()) {
      
         Vertex v1 = temp.remove();
         
         if(!edgeList.contains(v1)) {
            for(Vertex v2: v1.getAdjacencies()) {
               if(!temp.contains(v2)) {
                  temp.add(v2);
               }
            }
            
            edgeList.add(v1);
            
         }
         
      }
      
      return edgeList;
   }
 
 
 
 /*  three extra credit methods, recursive version  */
   public List<Vertex> depthFirstRecur(String name)
   {
      int ind = nameToIndex.get(name);
      Vertex source = getVertex(ind);
      return depthFirstRecur(source);
   }
   
   public List<Vertex> depthFirstRecur(Vertex v)
   {
      ArrayList<Vertex> edgeList = new ArrayList<Vertex>();
      edgeList.add(v);
      depthFirstRecurHelper(v, edgeList);
      return edgeList;
   }
   
   public void depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable)
   {   
      for(Vertex c : v.getAdjacencies()) {
         if(!(reachable.contains(c))) {
            reachable.add(c);
            depthFirstRecurHelper(c, reachable);
         } 
      }
   }   
}


