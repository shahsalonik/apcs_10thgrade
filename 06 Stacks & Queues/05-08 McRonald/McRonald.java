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
   
      //new queue
      Queue<Customer> customer = new LinkedList<Customer>();
      //default longest queue size
      longestQueue = customer.size();
      //total mins to help calculat longest wait time
      int total = 0;
      longestWaitTime = total;
      
      //first for-loop, repeats until closing time.
      for(int i = 0; i < TIME; i++) {
      //since the chances of a customer coming in are 0.2, if any randomly generated number is less than 0.2,
      //adds that customer to the queue and increments number of customers
      //also checks if the current queue size is longer than the longest queue size
      //checks for longest wait time and the initial customer's wait time too
         if(Math.random() < 0.2) {
            customer.add(new Customer(i, timeToOrderAndBeServed()));
            customers++;
            total = 0;
            Iterator<Customer> it = customer.iterator();
            while(it.hasNext()) {
               total += it.next().getWait();
            }
            if(total > longestWaitTime) {
               longestWaitTime = total;
            }
            if(customer.size() > longestQueue) {
               longestQueue = customer.size();
            }
            if(!customer.isEmpty()) {
               thisCustomersTime = customer.peek().getWait();
            }  
            totalMinutes += total;
         }
         //if thisCustomersTime equals 1,
         //removes the customer from the queue and sets the new customer's wait time to 0 (temp)
         //if the queue is not empty, then sets the first customer's wait time to thisCustomersTime
         if(thisCustomersTime == 1) {
            customer.remove();
            thisCustomersTime = 0;
            if(!customer.isEmpty()) {
               thisCustomersTime = customer.peek().getWait();
            }
         }
         //otherwise decrements time
         //set the wait time of the first customer to the updated time
         else {
            thisCustomersTime--;
            if(!customer.isEmpty()) {
               customer.peek().setWait(thisCustomersTime);
            }
         }
         //displays the time and queue of the current iteration
         displayTimeAndQueue(customer, i);
         //if there are customers, disply info in the format of:
         //ARRIVAL TIME is now being served for SERVING TIME minutes.
         if(!customer.isEmpty()) {
            of.println("\t" + customer.peek() + " is now being served for " + thisCustomersTime + " minutes.");
         }
      }
   
      //loop to be used in the case of the customer arriving after (or very close to) 1080 minutes
      //repeats until the queue is empty, but only does it if the queue is still not empty after the first for-loop
      //does the same as the above queue, just does not add any new customers because McRonald's is officially closed.
      if(!customer.isEmpty()) {
         for(int x = customer.peek().getArrival(); !customer.isEmpty(); x++) {
            if(thisCustomersTime == 1) {
               customer.remove();
               thisCustomersTime = 0;
               if(!customer.isEmpty()) {
                  thisCustomersTime = customer.peek().getWait();
               }
            }
            else {
               thisCustomersTime--;
               if(!customer.isEmpty()) {
                  customer.peek().setWait(thisCustomersTime);
               }
            }
            displayTimeAndQueue(customer, x);
            if(!customer.isEmpty()) {
               of.println("\t" + customer.peek() + " is now being served for " + thisCustomersTime + " minutes.");
            }
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
      public Customer(int t, int w) {
         arrivedAt = t;
         orderAndBeServed = w;
      }
    
    //returns the time they arrived
      public int getArrival() {
         return arrivedAt;
      }
    
    //returns their wait time
      public int getWait() {
         return orderAndBeServed;
      }
      
      //sets wait time
      public void setWait(int wait) {
         orderAndBeServed = wait;
      }
    
    //toString method
      public String toString() {
         return "" + arrivedAt;
      }
    
   }
}