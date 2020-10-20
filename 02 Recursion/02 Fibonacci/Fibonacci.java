// Name: Saloni Shah
// Date: 10/14/2020 (due date)
  
import java.util.*;
public class Fibonacci
{
   public static void main(String[] args)
   {
      long start, end, fib; //why long?
      int lastFibNumber = 43;
      int[] fibNumber = {1};
      System.out.println("\tFibonacci\tBy Iteration\tTime\tby Recursion\t Time");
      for(int n = fibNumber[0]; n <= lastFibNumber; n++)
      { 
         start = System.nanoTime();
         fib = fibIterate(n);
         end = System.nanoTime();
         System.out.print("\t\t" + n + "\t\t\t" + fib + "\t" + (end-start)/1000.);
         start = System.nanoTime();   	
         fib = fibRecur(n);      
         end = System.nanoTime();
         System.out.println("\t" + fib + "\t\t" + (end-start)/1000.);
      }
   }
   
   /**
    * Calculates the nth Fibonacci number by interation
    * @param n A variable of type int representing which Fibonacci number
    *          to retrieve
    * @returns A long data type representing the Fibonacci number
    */
   public static long fibIterate(int n)
   {
      int sum = 0; //variable to store the sum
      int a = 1; //first number
      int b = 1; //second number
      if(n == 0) {
         return 0;
      } 
      else if(n == 1 || n == 2) {
         return 1;
      }
      else {
         for(int i = 2; i < n; i++) {
            sum = a + b;
            a = b; //sets the value of a to b
            b = sum; //sets the b value to the sum
         }
         //the next number
         return sum;
      }
   }

   /**
    * Calculates the nth Fibonacci number by recursion
    * @param n A variable of type int representing which Fibonacci number
    *          to retrieve
    * @returns A long data type representing the Fibonacci number
    */
   public static long fibRecur(int n)
   { 
      if(n <= 1) {
         return n;
      }
      else {
         return fibRecur(n-1) + fibRecur(n-2);
      }    
   }
}