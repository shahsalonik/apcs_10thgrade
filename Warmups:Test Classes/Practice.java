import java.io.*;
import java.util.*;

public class Practice {

   public static Map<Integer, Set<String>> byYear (HashMap<String, Integer> songInput) {
   
      Map<Integer, Set<String>> yearList = new TreeMap<Integer, Set<String>>();
   
      Set<String> temp = new TreeSet<String>();
   
      for(String str : songInput.keySet()) {
      
         if(yearList.containsKey(songInput.get(str))) {
         
            yearList.get(songInput.get(str)).add(str);
         
         }
         
         else {
         
            yearList.put(songInput.get(str), temp);
         
           //temp.add(str);
         
         }
      }
   
      return yearList;
   }
   
   public static void main (String[] args) {
   
      HashMap<String, Integer> test = new HashMap<String, Integer>();
      test.put("Golden", 2019);
      test.put("Fine Line", 2019);
      test.put("everything means nothing", 2020);
      test.put("Wipeout", 1960);
      
      System.out.println(byYear(test));
      
      
   
   }
   
}