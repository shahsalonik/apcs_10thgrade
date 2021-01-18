//Updated on 12.14.2020 v2
//Name: Saloni Shah
//Date: 01/19/2021
import java.util.*;
import java.io.*;
public class McRonald
{
   public static final int TIME = 1080;     //18 hrs * 60 min
   public static double CHANCE_OF_CUSTOMER = .2;
   public static int customers = 0;
   public static int totalMinutes = 0;
   public static int longestWaitTime = 0;
   public static int longestQueue = 0;
   public static int serviceWindow = 0;      // to serve the front of the queue
   public static int thisCustomersTime;
   public static PrintWriter outfile = null; // file to display the queue information
      
   public static int timeToOrderAndBeServed()
   {
      return (int)(Math.random() * 6 + 2);
   }
  
   public static void displayTimeAndQueue(Queue<Customer> q, int min)
   { 
      //Billington's
      outfile.println(min + ": " + q);	
      //Jurj's
      //outfile.println("Customer#" + intServiceAreas[i] + 
      //                            " leaves and his total wait time is " + (intMinute - intServiceAreas[i]));                     	
      
   }
   
   public static int getCustomers()
   {
      return customers;
   }
   public static double calculateAverage()
   {
      return (int)(1.0 * totalMinutes/customers * 10)/10.0;
   }
   public static int getLongestWaitTime()
   {
      return longestWaitTime;
   }
   public static int getLongestQueue()
   {
      return longestQueue;
   }
            
   public static void main(String[] args)
   {     
    //set up file      
      try
      {
         outfile = new PrintWriter(new FileWriter("McRonald 1 Queue 1 ServiceArea.txt"));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
      
      mcRonald(TIME, outfile);   //run the simulation
      
      outfile.close();	
   }
   
   public static void mcRonald(int TIME, PrintWriter of)
   {
      //new customer queue  
      Queue<Customer> customer = new LinkedList<Customer>();
      //initial time until served for the first customer
      //between 2 and 7 mins
      int timeUntilServed = ((int) ((Math.random() * 6) + 2));
      //adds first customer's time to the total mins
      totalMinutes += timeUntilServed;
      //default longest wait time
      longestWaitTime = timeUntilServed;
      //default longest queue
      longestQueue = customer.size();
      
      //first for-loop, repeats until closing time.
      for(int i = 0; i < TIME; i++) {
      //since the chances of a customer coming in are 0.2, if any randomly generated number is less than 0.2,
      //adds that customer to the queue and increments number of customers
      //also checks if the current queue size is longer than the longest queue size
         if(Math.random() < 0.2) {
            customer.add(new Customer(i));
            customers++;
            if(customer.size() > longestQueue) {
               longestQueue = customer.size();
            }
         }
         //if the timeUntilServed equals 1,
         //removes the customer from the queue
         //generates a new timeUntilServed and adds it on to the total
         //checks if the longest wait time has been reached yet
         if(timeUntilServed == 1) {
            customer.remove();
            timeUntilServed = ((int) ((Math.random() * 6) + 2));
            totalMinutes += timeUntilServed;
            if(timeUntilServed > longestWaitTime) {
               longestWaitTime = timeUntilServed;
            }
            //if the queue is not empty
            //set the wait time of the first customer to the newly generated time
            if(!customer.isEmpty()) {
               customer.peek().setWait(timeUntilServed);
            }
         }
         else {
            //decrements time for the customer and the displayed time
            if(!customer.isEmpty()) {
               customer.peek().decrementTime();
               timeUntilServed--;
            }
         }
         //displays the time and queue of the current iteration
         displayTimeAndQueue(customer, i);
         //if there are customers, disply info in the format of:
            //ARRIVAL TIME is now being served for SERVING TIME minutes.
         if(!customer.isEmpty()) {
            of.println("\t" + customer.peek() + " is now being served for " + timeUntilServed + " minutes.");
         }
      }
      
         //loop to be used in the case of the customer arriving after (or very close to) 1080 minutes
         //repeats until the queue is empty
         //does the same as the above queue, just does not add any new customers because McRonald's is officially closed.
         for(int x = customer.peek().getArrival(); !customer.isEmpty(); x++) {
            if(timeUntilServed == 1) {
               customer.remove();
               timeUntilServed = ((int) ((Math.random() * 6) + 2));
               while(timeUntilServed == 0) {
                  timeUntilServed = ((int) ((Math.random() * 6) + 2));
               }
               if(!customer.isEmpty()) {
                  customer.peek().setWait(timeUntilServed);
               }
            }
            else {
               if(!customer.isEmpty()) {
                  customer.peek().decrementTime();
                  timeUntilServed--;
               }
            }
            displayTimeAndQueue(customer, x);
            if(!customer.isEmpty()) {
               of.println("\t" + customer.peek() + " is now being served for " + timeUntilServed + " minutes.");
            }
         }
        
        
              
      /*   report the data to the screen    */  
      System.out.println("1 queue, 1 service window, probability of arrival = "+ CHANCE_OF_CUSTOMER);
      System.out.println("Total customers served = " + getCustomers());
      System.out.println("Average wait time = " + calculateAverage());
      System.out.println("Longest wait time = " + longestWaitTime);
      System.out.println("Longest queue = " + longestQueue);
   }
   
   static class Customer      
   {
      private int arrivedAt;
      private int orderAndBeServed;
      
    /**********************************
       Complete the Customer class with  
       constructor, accessor methods, toString.
    ***********************************/
    
    //constructor with an input arrival time
      public Customer(int t) {
         arrivedAt = t;
         orderAndBeServed = 0;
      }
    
    //returns the time they arrived
      public int getArrival() {
         return arrivedAt;
      }
    
    //returns their wait time
      public int getWait() {
         return orderAndBeServed;
      }
    
    //sets their wait time
      public void setWait(int w) {
         orderAndBeServed = w;
      }
    
    //decrements their wait time
      public void decrementTime() {
         orderAndBeServed -= 1;
      }
    
    //toString method
      public String toString() {
         return "" + arrivedAt;
      }
    
   }
}