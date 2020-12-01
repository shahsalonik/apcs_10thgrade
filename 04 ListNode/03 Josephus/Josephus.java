// Name: Saloni Shah
// Date: 12/07/2020 (due date)

import java.util.*;
import java.io.*;

public class Josephus
{
   private static String WINNER = "Josephus";
   
   public static void main(String[] args) throws FileNotFoundException
   {
      ListNode p = new ListNode("A", null);
      p.setNext(p);
      p = insert(p, "B");
      p = insert(p, "C");
      p = insert(p, "D");
      print(p);
        
      /* run numberCircle first with J_numbers.txt  */
      Scanner sc = new Scanner(System.in);
      System.out.print("How many names (2-20)? ");
      int n = Integer.parseInt(sc.next());
      System.out.print("How many names to count off each time?"  );
      int countOff = Integer.parseInt(sc.next());
      ListNode winningPos = numberCircle(n, countOff, "J_numbers.txt");
      System.out.println(winningPos.getValue() + " wins!");  
     
      /* run josephusCircle next with J_names.txt  */
      System.out.println("\n ****  Now start all over. **** \n");
      System.out.print("Where should "+WINNER+" stand?  ");
      int winPos = Integer.parseInt(sc.next());        
      ListNode theWinner = josephusCircle(n, countOff, "J_names.txt", winPos);
      System.out.println(theWinner.getValue() + " wins!");  
   }
   
   public static ListNode numberCircle(int n, int countOff, String filename) throws FileNotFoundException
   {
      ListNode p = null;
      p = readNLinesOfFile(n, new File(filename));
      p = countingOff(p, countOff, n);
      return p;
   }
   
   public static ListNode josephusCircle(int n, int countOff, String filename, int winPos) throws FileNotFoundException
   {
      ListNode p = null;
      p = readNLinesOfFile(n, new File(filename));
      replaceAt(p, WINNER, winPos);
      p = countingOff(p, countOff, n);
      return p;
   }

   /* reads the names, calls insert(), builds the linked list.
	 */
   public static ListNode readNLinesOfFile(int n, File f) throws FileNotFoundException
   {
      Scanner infile = new Scanner(f); 
      ListNode head = new ListNode(infile.next(), null);
      ListNode prevNode = head;
      
      //goes through the file until the specified number of lines
      //puts them into a linked list
      for(int x = 0; x < n-1; x++) {
         ListNode temp = new ListNode(infile.next(), null);
         prevNode.setNext(temp);
         prevNode = temp;
      }
      prevNode.setNext(head); //ciruclar linked list
      head = prevNode; //head is the last node put into the list
      return head; //returns the list
   }
   
   /* helper method to build the list.  Creates the node, then
    * inserts it in the circular linked list.
	 */
   public static ListNode insert(ListNode p, Object obj)
   {
      //puts the node into the list and makes that node the head
      ListNode head = p;
      ListNode temp = new ListNode (obj, head.getNext());
      head.setNext(temp);
      head = temp;
      
      //returns the list with the inserted node as the head
      return head;
   }
   
   /* Runs a Josephus game, counting off and removing each name. Prints after each removal.
      Ends with one remaining name, who is the winner. 
	 */
   public static ListNode countingOff(ListNode p, int count, int n)
   {
      //prints original
      print(p);
      while(p.getNext() != p && n > 1) {
         p = remove(p, count); 
         print(p);
         n--;
      } 
      
      //returns the remaining node
      return p.getNext();
   }
   
   /* removes the node after counting off count-1 nodes.
	 */
   public static ListNode remove(ListNode p, int count)
   {
      ListNode prevNode = null;
      
      //traverses the linked list until reaching the count number
      for(int i = 0; i < count; i++){
         prevNode = p;
         p = p.getNext();
      }
      
      //removes the one at that position
      prevNode.setNext(p.getNext());
      
      return p;
   }
   
   /* prints the circular linked list.
	 */
   public static void print(ListNode p)
   {
      //starts at the node after the head 
      ListNode start = p.getNext();
      
      //prints each node until reaching the initial value
      do {
         System.out.print(start.getValue() + " ");
         start = start.getNext();
      }
      while(start != p.getNext());
      System.out.println();
   }
	
   /* replaces the value (the string) at the winning node.
	 */
   public static void replaceAt(ListNode p, Object obj, int pos)
   {
      //traverses the list until reaching the specified position; then, replaces the value of the node with the one passed
      for(int x = 1; x < pos; x++) {
         p = p.getNext();
      }
      p = p.getNext();
      p.setValue(obj);
   }
}
/**********************************************************
     ----jGRASP exec: java Josephus_Teacher
 A B C D
 How many names (2-20)? 5
 How many names to count off each time? 2
 1 2 3 4 5
 3 4 5 1
 5 1 3
 3 5
 3
 3 wins!
 
  ****  Now start all over. **** 
 
 Where should Josephus stand?  3
 Michael Hannah Josephus Ruth Matthew
 Josephus Ruth Matthew Michael
 Matthew Michael Josephus
 Josephus Matthew
 Josephus
 Josephus wins!
 
  ----jGRASP: operation complete.
  
  **************************************************/