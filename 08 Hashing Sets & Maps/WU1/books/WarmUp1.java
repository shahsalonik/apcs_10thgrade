import java.util.*;
import java.io.*;

public class WarmUp1 {

   public static void main (String[] args) throws Exception {
   
      wordFreq("book1.txt");
   
   }
   
   public static void wordFreq(String f) throws Exception {
   
      TreeMap<String, Integer> map = new TreeMap<String, Integer>();
      Scanner infile = new Scanner(new File(f));
      
      //read each word into the map
      while(infile.hasNext()) {
         
         
         String[] words = infile.next().split("[., \"!?]");
         
         if(words.length == 0) {
            continue;
         }
         
         //add it to the map w value 1
         if(map.get(words[0]) == null) {
            map.put(words[0], 1);
         } 
         else {
         //if it's a duplicate word, increase value by 1
            map.put(words[0], map.get(words[0]) + 1);
         }
         
      }
      
      for(String key : map.keySet()) {
         System.out.println(key + ": " + map.get(key));
      }
         
   
   }

}