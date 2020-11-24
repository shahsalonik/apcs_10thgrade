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
      ListNode list = new ListNode(infile.next(), null);
      
      for(int x = 1; x < n; x++) {
         list = insert(list, infile.next());
      }
      
      return list;
   }
   
   /* helper method to build the list.  Creates the node, then
    * inserts it in the circular linked list.
	 */
   public static ListNode insert(ListNode p, Object obj)
   {
      ListNode ins = p;
      
      while(ins.getNext() != p) {
         ins = ins.getNext();
      }
      
      ListNode val = new ListNode (obj, p);
      ins.setNext(val);
      
      return ins;
   }
   
   /* Runs a Josephus game, counting off and removing each name. Prints after each removal.
      Ends with one remaining name, who is the winner. 
	 */
   public static ListNode countingOff(ListNode p, int count, int n)
   {
      print(p);
      while(p.getNext() != p && n > 1) {
         p = remove(p, count);
         print(p);
         n--;
      } 
      
      return p;
   }
   
   /* removes the node after counting off count-1 nodes.
	 */
   public static ListNode remove(ListNode p, int count)
   {
      if(count == 1) {
         ListNode last = p;
         while(p.getNext() != p) {
            last = last.getNext();
         }
         last.setNext(p.getNext());
      }
      else {
         for(int i=1; i < count; i++){
            p = p.getNext();
         }
         p.setNext(p.getNext().getNext());
      }
      return p.getNext();
   }
   
   /* prints the circular linked list.
	 */
   public static void print(ListNode p)
   {
      ListNode temp = p;
      while(temp != p) {
         System.out.println(p.getValue());
         p = p.getNext();
      }
   }
	
   /* replaces the value (the string) at the winning node.
	 */
   public static void replaceAt(ListNode p, Object obj, int pos)
   {
      for(int x = 0; x < pos - 1; x++) {
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