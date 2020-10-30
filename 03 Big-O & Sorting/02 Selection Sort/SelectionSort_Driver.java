// Name: Saloni Shah
// Date: 11/04/2020 (due date)

import java.util.*;
import java.io.*;

public class SelectionSort_Driver
{
   public static void main(String[] args) throws Exception
   {
     //Part 1, for doubles
      int n = (int)(Math.random()*100)+20;
      double[] array = new double[n];
      for(int k = 0; k < array.length; k++)
         array[k] = Math.random()*100;	
      
      Selection.sort(array);
      print(array);
      if( isAscending(array) )
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
      System.out.println();
      
      //Part 2, for Strings
      int size = 100;
      Scanner sc = new Scanner(new File("declaration.txt"));
      Comparable[] arrayStr = new String[size];
      for(int k = 0; k < arrayStr.length; k++)
         arrayStr[k] = sc.next();	
   
      Selection.sort(arrayStr);
      print(arrayStr);
      System.out.println();
      
      if( isAscending(arrayStr) )
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
   }
  
   public static void print(double[] a)
   {
      for(double d: a)         //for-each
         System.out.print(d+" ");
      System.out.println();
   }
  
   public static void print(Object[] papaya)
   {
      for(Object abc : papaya)     //for-each
         System.out.print(abc+" ");
   }
  
   public static boolean isAscending(double[] a)
   {
      //checks if sorted in ascending order by comparing two adjacent values
      for(int x = 0; x < a.length - 1; x++) {
         if(a[x] > a[x+1]) {
            return false;
         }
      }
      return true;
   }
   
   @SuppressWarnings("unchecked")
   public static boolean isAscending(Comparable[] a)
   {
      //checks if sorted in ascending order by comparing two adjacent values
      for(int x = 0; x < a.length - 1; x++) {
         if(a[x].compareTo(a[x+1]) > 0) {
            return false;
         }
      }
      return true;
   }
}
/////////////////////////////////////////////////////

class Selection
{
   public static void sort(double[] array)
   {
      //finds the maximum position and swaps it 
      //continues doing that for the entire array
      int maxPos;
      for(int k = 0; k < array.length; k++)
      {
         maxPos = findMax(array, array.length - k - 1);
         swap(array, maxPos, array.length - k - 1);
      }
   }
   
   // upper controls where the inner loop of the Selection Sort ends
   private static int findMax(double[] array, int upper)
   {
      int maxPos = 0;
      
      //iteratively looks through each index and checks if it is greater than the previous max
      //returns the final max
      for(int i = 1; i <= upper; i++) {
         if(array[i] > array[maxPos]) {
            maxPos = i;
         }
      }
      
      return maxPos;
   }
   private static void swap(double[] array, int a, int b)
   {
      //swap code
      double temp = array[a];
      array[a] = array[b];
      array[b] = temp;
   }   	
   
	/*******  for Comparables ********************/
   @SuppressWarnings("unchecked")
   public static void sort(Comparable[] array)
   {
      //finds the maximum position and swaps it 
      //continues doing that for the entire array
      int maxPos;
      for(int k = 0; k < array.length; k++)
      {
         maxPos = findMax(array, array.length - k - 1);
         swap(array, maxPos, array.length - k - 1);
      }
   }
   
   @SuppressWarnings("unchecked")
   public static int findMax(Comparable[] array, int upper)
   {
      int maxPos = 0; 
      
      //iteratively looks through each index and checks if it is greater than the previous max
      //returns the final max
      for(int i = 1; i <= upper; i++) {
         if(array[i].compareTo(array[maxPos]) > 0) {
            maxPos = i;
         }
      }
      
      return maxPos;
   }
   public static void swap(Object[] array, int a, int b)
   {
      //swap code
      Object temp = array[a];
      array[a] = array[b];
      array[b] = temp;
   }
}

