 // Name: Saloni Shah      
 // Date: 12/14/2020 (due date)
 // use for-each loops or iterators, not regular for-loops
import java.io.*;
import java.util.*;
public class IteratorLab
{
   public static void main(String[] args)
   {
         
      System.out.println("Iterator Lab\n");
      int[] rawNumbers = {-9, 4, 2, 5, -10, 6, -4, 24, 20, -28};
      for(int n : rawNumbers )
         System.out.print(n + " ");    
      ArrayList<Integer> numbers = createNumbers(rawNumbers);
      System.out.println("\nArrayList: "+ numbers);      //Implicit Iterator!
      System.out.println("Count negative numbers: " + countNeg(numbers));
      System.out.println("Average: " + average(numbers));
      System.out.println("Replace negative numbers: " + replaceNeg(numbers));
      System.out.println("Delete zeros: " + deleteZero(numbers));
      String[] rawMovies = {"High_Noon", "High_Noon", "Star_Wars", "Tron", "Mary_Poppins", 
               "Dr_No", "Dr_No", "Mary_Poppins", "High_Noon", "Tron"};
      ArrayList<String> movies = createMovies(rawMovies);
      System.out.println("Movies: " + movies);
      System.out.println("Movies: " +  removeDupes(movies));      
      
      
   }
      // pre: an array of just int values 
   	// post: return an ArrayList containing all the values
   public static ArrayList<Integer> createNumbers(int[] rawNumbers) 
   {
      ArrayList<Integer> newList = new ArrayList<Integer>();
      
      //for each int in the array passed, add it to the ArrayList
      //return the list
      for(int i : rawNumbers) {
         newList.add(i);
      }
      return newList;
   }
      // pre: an array of just Strings  
   	// post: return an ArrayList containing all the Strings
   public static ArrayList<String> createMovies(String[] rawWords) 
   {
      ArrayList<String> myList = new ArrayList<String>();
      for ( String str : rawWords )
         myList.add( str );
      return myList;
   }
   
   	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: return the number of negative values in the ArrayList a
   public static int countNeg(ArrayList<Integer> a)
   {
      int count = 0;
      
      //for each number in the list passed, if the number is negative, increment count
      //return count
      for(Integer num : a) {
         if(num < 0) {
            count++;
         }  
      }
      return count;
   }
   	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: return the average of all values in the ArrayList a
   public static double average(ArrayList<Integer> a)
   {
      double sum = 0;
      int size = a.size();
       
      //add all the numbers 
      for(Integer num : a) {
         sum += num;
      }
      
      //return the sum divided by the size, which is the average 
      return sum/size;
       
   }
     	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: replaces all negative values with 0 
   public static ArrayList<Integer> replaceNeg(ArrayList<Integer> a)
   {
      ListIterator<Integer> noNeg = a.listIterator();
      
      //while there is a next number
      //if it is less than zero, set it to 0
      //return the list
      while(noNeg.hasNext()) {
         if(noNeg.next() < 0) {
            noNeg.set(0);
         }
      }
      
      return a;
      
   }
     	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: deletes all zeros in the ArrayList a
   public static ArrayList<Integer> deleteZero(ArrayList<Integer> a)
   {
      ListIterator<Integer> noZero = a.listIterator();
      
      //while there is a next number
      //if it is equal to zero, remove it
      //return the list without zeros
      while(noZero.hasNext()) {
         if(noZero.next() == 0) {
            noZero.remove();
         }
      }
      
      return a;
   }
      // pre: ArrayList a is not empty and contains only String objects
   	// post: return ArrayList without duplicate movie titles
		// strategy: start with an empty array and add movies as needed
   public static ArrayList<String> removeDupes(ArrayList<String> a)
   {
      ArrayList<String> noDupMovie = new ArrayList<String>();
      
      //for each movie in a, check if it is there in the new list
      //if it already is, then break and repeat
      //if it isn't, add it into the list
      //return the new list
      for(String str : a) {
         boolean isThere = false;
         for(String s : noDupMovie) {
            if(str.equals(s)) {
               isThere = true;
               break;
            }
         } 
         
         if(!isThere) {
            noDupMovie.add(str);
         }
      }
      
      return noDupMovie;
      
   }
   
   public static ArrayList<Character> noVowels(ArrayList<Character> a) {
   
      ListIterator<Character> noVowelList = a.listIterator();
      
      while(noVowelList.hasNext())  {
         
         char c = noVowelList.next();
         
         if( c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
            noVowelList.remove();
         }
         
      }
      
      return a;   
   }
   
}

