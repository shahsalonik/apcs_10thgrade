// Name: Saloni Shah
 // Date: 03/08/2021 (due date)

/* 
   Assignment:  This hashing program results in collisions.
   You are to implement three different collision schemes: linear 
   probing, rehashing, and chaining.  Then implement a search 
   algorithm that is appropriate for each collision scheme.
 */
import java.util.*;
import javax.swing.*;
public class Hashing
{
   public static void main(String[] args)
   {
      int arrayLength = Integer.parseInt(JOptionPane.showInputDialog(
                         "Hashing!\n"+
                         "Enter the size of the array:  "));//20
       
      int numItems = Integer.parseInt(JOptionPane.showInputDialog(
                         "Add n items:  "));               //15
     
      int scheme = Integer.parseInt(JOptionPane.showInputDialog(
           "The Load Factor is " + (double)numItems/arrayLength +
           "\nWhich collision scheme?\n"+
           "1. Linear Probing\n" +
           "2. Rehashing\n"+
           "3. Chaining"));
      Hashtable table = null;
      switch( scheme )
      {
         case 1:   
            table = new HashtableLinearProbe(arrayLength);
            break;
         case 2: 
            table = new HashtableRehash(arrayLength);
            break;
         case 3:  
            table = new HashtableChaining(arrayLength);
            break;
         default:  System.exit(0);    
      }
      for(int i = 0; i < numItems; i++)
         table.add("Item" + i);
      int itemNumber = Integer.parseInt(JOptionPane.showInputDialog(
                       "Search for:  Item0" + " to "+ "Item"+(numItems-1)));
      while( itemNumber != -1 )
      {
         String key = "Item" + itemNumber;
         int index = table.indexOf(key); 
         if( index >= 0)    //found it
            System.out.println(key + " found  at index " + index);
         else
            System.out.println(key + " not found!");
         itemNumber = Integer.parseInt(JOptionPane.showInputDialog(
                       "Search for:  Item0" + " to "+ "Item"+(numItems-1)));                           
      } 
      System.exit(0);
   }
}

/*********************************************/
interface Hashtable
{
   void add(Object obj);
   int indexOf(Object obj);
}
/***************************************************/ 

class HashtableLinearProbe implements Hashtable
{
   private Object[] array;
  
   public HashtableLinearProbe(int size)//constructor
   {
      array = new Object[size];     
   }
   
   /**
    * Adds an Object to the HashTable by checking if the desired index is null
    * If it is not null, goes through the HashTable to find a null index using linear probing.
    * @param obj the Object to be inserted
    */
   public void add(Object obj)
   {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      if(array[index] == null)  //empty
      {
         array[index] = obj; //insert it
         System.out.println(obj + "\t" + code + "\t" + index);
      }
      else //collision
      {
         System.out.println(obj + "\t" + code + "\tCollision at "+ index);
         index = linearProbe(index);
         array[index] = obj;
         System.out.println(obj + "\t" + code + "\t" + index);
      }
   }  
   
   /**
    * Looks through each index of the array in a linear way.
    * @param index the index of the array
    * @return an index that is null
    */
   public int linearProbe(int index)
   {      
      while(array[index] != null && index < array.length - 1) {
         index++;
      }
      
      if(index == array.length - 1) {
         index = 0;
      }
      
      while(array[index] != null && index < array.length - 1) {
         index++;
      }
      
      return index;
   }
   
   /**
    * A method to look for an Object and return the index of it.
    * @param obj the Object that is being looked for
    * @return either the index of the array or -1 (not found)
    */
   public int indexOf(Object obj)     
   {
      int index = Math.abs(obj.hashCode() % array.length);
      while(array[index] != null)
      {
         if(array[index].equals(obj))  //found it
         {
            return index;
         }
         else //search for it in a linear probe manner
         {
            while(!array[index].equals(obj) && index < array.length - 1) {
               System.out.println("Looking at index " + index);
               index++;
            }
         
            if(index == array.length - 1) {
               index = 0;
            }
         
            while(!array[index].equals(obj) && index < array.length - 1) {
               System.out.println("Looking at index " + index);
               index++;
            }
         }
      }
      return -1; //not found
   }
}

/*****************************************************/
class HashtableRehash implements Hashtable
{
   private Object[] array;
   private int constant;  
   
   public HashtableRehash(int size) //constructor
   {
      array = new Object[size];
      constant = relPrime(size);
   }
   
   /**
    * Determines an integer that is relatively prime compared to the size of the array.
    * @param arSize the size of the array
    * @return a relatively prime integer for the array
    */
   private int relPrime(int arSize) {
      if(arSize % 2 == 1) {
         return 2;
      }
      else if(arSize % 2 == 0 && arSize % 3 == 0) {
         return 1;
      }
      else {
         return 3;
      }
   }
   
   /**
    * Tries to add an Object to the Hashtable by checking if the index is null
    * If it is not, tries to find the next null index by using rehashing
    * @param obj the Object to be added
    */
   public void add(Object obj)
   {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      if(array[index] == null)  //empty
      {
         array[index] = obj; //insert it
         System.out.println(obj + "\t" + code + "\t" + index);
      }
      else //collision
      {
         System.out.println(obj + "\t" + code + "\tCollision at "+ index);
         index = rehash(index);
         array[index] = obj;
         System.out.println(obj + "\t" + code + "\t" + index);
      }
   }  
   
   /**
    * Similar to linear probing, except instead of adding 1 to the index, 
    * a constant is added instead.
    * @param index the index where the rehashing starts
    * @return an index that is null
    */
   public int rehash(int index)
   {
      while(array[index] != null && index < array.length - 1) {
         index = (index + constant) % array.length;
      }
      
      if(index >= array.length - 1) {
         index = 0;
      }
      
      while(array[index] != null && index < array.length - 1) {
         index = (index + constant) % array.length;
      }
      
      return index;
      
   }
   
   /**
    * A method to look for an Object and return the index of it.
    * @param obj the Object that is being looked for
    * @return either the index of the array or -1 (not found)
    */
   public int indexOf(Object obj)
   {
      int index = Math.abs(obj.hashCode() % array.length);
      while(array[index] != null)
      {
         if(array[index].equals(obj))  //found it
         {
            return index;
         }
         else //search for it in a rehashing manner
         {
            while(!array[index].equals(obj) && index < array.length - 1) {
               System.out.println("Looking at index " + index);
               index = (index + constant) % array.length;
            }
            
            if(index >= array.length - 1) {
               index = 0;
            }
            
            while(!array[index].equals(obj) && index < array.length - 1) {
               System.out.println("Looking at index " + index);
               index = (index + constant) % array.length;
            }
         }
      }
      return -1; //not found
   }
}

/********************************************************/
class HashtableChaining implements Hashtable
{
   private LinkedList[] array;
   
   public HashtableChaining(int size)
   {
      array = new LinkedList[size]; //instantiate the array
      for(int x = 0; x < size; x++) { //instantiate the LinkedLists
         array[x] = new LinkedList();
      }  
                            
   }
   
   /**
    * Doesn't need to look for a null spot because each index is a Linked List
    * @param obj the Object to be added
    */
   public void add(Object obj)
   {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      array[index].addFirst(obj);
      System.out.println(obj + "\t" + code + " " + " at " +index + ": "+ array[index]);
   }  

   /**
    * A method to look for an Object and return the index of it.
    * @param obj the Object that is being looked for
    * @return either the index of the array or -1 (not found)
    */
   public int indexOf(Object obj)
   {
      int index = Math.abs(obj.hashCode() % array.length);
      if( !array[index].isEmpty() )
      {
         if(array[index].equals(obj))  //found it
         {
            return index;
         }
         else //search for it in a chaining manner
         {
            for(Object o : array[index]) {
               if(o.equals(obj)) {
                  return index;
               }
            }
         }  
      }
      return -1; //not found
   }
}