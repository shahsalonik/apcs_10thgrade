// Name: Saloni Shah   
// Date: 11/23/2020 (due date)
import java.util.*;

public class ListLab1
{
   public static void main(String[] args)
   {
      ListNode head = new ListNode("hello", null);
      head = new ListNode("foo", head);
      head = new ListNode("boo", head);
      head = new ListNode("nonsense", head);
      head = new ListNode("computer",
         	new ListNode("science",
         		new ListNode("java",
         			new ListNode("coffee", head)
         		)
         	)
         );
      print(head);
      print(head);
      
      /**** uncomment the code below for ListLab1 Assignment  ****/
      
      ListNode a = copyNode(head);
      System.out.println("The head has a value \"" + head.getValue() + "\" at "+ head);
      System.out.println("The copy of head has a value of \"" + a.getValue() + "\" at "+ a);
   // 
      System.out.print("Copy the list: ");
      ListNode copy = copyList(head);
      print(copy);
   // 
      System.out.print("The rest of the list: ");
      ListNode theRest = rest(copy);
      print(theRest);
   // 
      System.out.println("First of the rest = " + first(theRest));
      System.out.println("Second of the rest = " + second(theRest));
      ListNode p = pointerToLast(theRest);
      System.out.println("Pointer to Last = " + p.getValue()+ " at " + p);
      ListNode c = copyOfLast(theRest);
      System.out.println("Copy of Last =    " + c.getValue()+ " at " + c);
   // 
      Scanner in = new Scanner(System.in);
      System.out.print("Insert what? ");
      String x = in.next();
      theRest = insertFirst(theRest, x);
      theRest = insertLast(theRest, x);
      print(theRest);
   }
   public static void print(ListNode head)
   {
      System.out.print("[");
      while(head != null)
      {
         System.out.print(head.getValue());
         head = head.getNext();
         if(head != null)
            System.out.print(", ");
      }
      System.out.println("]");
   }
   
   /* enter your code here */
   
   
   /**
    * Copies the node specified in the argument
    * @param arg the node to be copied
    * @return a new ListNode that is an exact copy of the one passed.
    */
   public static ListNode copyNode(ListNode arg) {
      ListNode next = arg.getNext();
      Object val = arg.getValue();
      
      ListNode copyArg = new ListNode(val, next);
      
      return copyArg;
   }
   
   /**
    * Copies the whole linked list
    * @param arg the head of the list
    * @return the copied list
    */
   public static ListNode copyList(ListNode arg) {
      
      ListNode list;
      
      if(arg.getNext() == null) {
         list = new ListNode(arg.getValue(), null);
      }
      else {
         list = new ListNode(arg.getValue(), copyList(arg.getNext()));
      }
      return list;
   }
   
   /**
    * Copies everything in the list except for the head
    * @param h the list
    * @return a copied list without the head
    */
   public static ListNode rest(ListNode h) {
      
      ListNode list;
      
      if(h.getNext() == null) {
         return null;
      }
      else {
         list = new ListNode(h.getNext().getValue(), rest(h.getNext()));
      }
      return list;
      
   }
   
   /**
    * Returns the value of the head of the list
    * @param head the head of the linked list
    * @return the value of the head
    */
   public static Object first(ListNode head) {
      if(head == null) {
         return null;
      }
      else {
         return head.getValue();
      }
   }
   
   /**
    * Returns the value of the second node of the list
    * @param head the head of the linked list
    * @return the value of the second node of the list
    */
   public static Object second(ListNode head) {
      if(head.getNext() == null) {
         return 0;
      }
      else {
         return head.getNext().getValue();
      }  
   }
   
   /**
    * Makes the pointer (the head) point to the last node
    * @param head the head of the list
    * @return the new head (the last node)
    */
   public static ListNode pointerToLast(ListNode head) {
      
      ListNode pointer;
      
      if(head == null) {
         return null;
      }
      else {
         while(head.getNext() != null) {
            head = head.getNext();
         }
      }
      
      return head;
      
   }
   
   /**
    * Returns a copy of the last node
    * @param head the head of the list
    * @return a copy of the last node
    */
   public static ListNode copyOfLast(ListNode head) {
      
      ListNode last = null;
      
      if(head == null) {
         return null;
      }
      else if(head.getNext() == null) {
         last = new ListNode(head.getValue(), null);
         return last;
      }
      else {
      
         while(head.getNext() != null) {
            head = head.getNext();
         }
         
         last = new ListNode(head.getValue(), null);
         return last;
      }
   
   }
   
   /**
    * Inserts a new value as the head
    * @param head the head of the list
    * @param arg the value of the new ListNode
    * @return the new head
    */
   public static ListNode insertFirst(ListNode head, Object arg) {
         
      if(head == null) {
         head = new ListNode(arg, null);
         return head;
      }
      else {
         head = new ListNode(arg, head);
         return head;
      }
   } 
   
   /**
    * Inserts a ListNode at the end of the list
    * @param head the head of the list
    * @param arg the value of the new ListNode
    * @return the list
    */
   public static ListNode insertLast(ListNode head, Object arg) {
      if(head == null) {
         return head;
      }
      else {
         ListNode pointer;
         
         for(pointer = head; pointer != null; pointer = pointer.getNext()) {
            if(pointer.getNext() == null) {
               ListNode newLast = new ListNode(arg, null);
               pointer.setNext(newLast);
               break;
            }
         }
         return head;
      }
   }
      
}

/*****************************************
 
 [computer, science, java, coffee, nonsense, boo, foo, hello]
 [computer, science, java, coffee, nonsense, boo, foo, hello]
 The head has a value "computer" at ListNode@15db9742
 The copy of head has a value of "computer" at ListNode@6d06d69c
 Copy the list: [computer, science, java, coffee, nonsense, boo, foo, hello]
 The rest of the list: [science, java, coffee, nonsense, boo, foo, hello]
 First of the rest = science
 Second of the rest = java
 Pointer to Last = hello at ListNode@7852e922
 Copy of Last =    hello at ListNode@4e25154f
 Insert what? p
 [p, science, java, coffee, nonsense, boo, foo, hello, p]
    
  **********************************************/
