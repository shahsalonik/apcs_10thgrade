// Name: Saloni Shah
// Date: 12/07/2020 (due date)

//  implements some of the List and LinkedList interfaces: 
//	 	  size(), add(i, o), remove(i);  addFirst(o), addLast(o); 
//  This class also overrides toString().
//  the list is zero-indexed.
//  Uses DLNode.

public class DLL        //DoubleLinkedList
{
   private int size;
   private DLNode head = new DLNode(); //dummy node--very useful--simplifies the code
   
   public int size()
   {
      return size;
   }
   
   /* appends obj to end of list; increases size;
   	  @return true  */
   public boolean add(Object obj)
   {
      addLast(obj);
      return true;   
   }
   
   /* inserts obj at position index.  increments size. 	*/
   public void add(int index, Object obj) throws IndexOutOfBoundsException  //this the way the real LinkedList is coded
   {
      if( index > size || index < 0 )
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      else {
         DLNode temp = new DLNode(obj, null, null);
         DLNode current = head;
         
         //goes until the specified index
         //then, inserts the node into the position specified by setting previous and next values
         //increases the size
         for(int x = 0; x < index; x++) {
            current = current.getNext();
         }
         temp.setNext(current.getNext());
         current.getNext().setPrev(temp);
         current.setNext(temp);
         temp.setPrev(current);
         size++;
      }              
                    
   }
   
   /* return obj at position index. 	*/
   public Object get(int index)
   { 
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      else {
         DLNode current = head;
         
         if(index == 0) {
            return current.getNext().getValue();
         }
         
         //goes to the specified position
         //then, returns the value of that position
         for(int x = 0; x < index; x++) {
            current = current.getNext();
         }
         return current.getValue();
      }
   }
   
   /* replaces obj at position index. 
        returns the obj that was replaced*/
   public Object set(int index, Object obj)
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      else {
      
         DLNode current = head;
         
         //goes to the specified position
         //then, sets the value of that position to the passed value
         //returns the value that was removed
         for(int x = 0; x < index; x++) {
            current = current.getNext();
         }
         Object temp = current.getNext().getValue();
         current = current.getNext();
         current.setValue(obj);
         return temp;
      }
      
   }
   
   /*  removes the node from position index (zero-indexed).  decrements size.
       @return the object of the node that was removed.    */
   public Object remove(int index)
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      else {
         DLNode current = head;
         
         //goes to the specified postion
         for(int i=0; i <= index; i++) {
            current = current.getNext();
         }
         
         //removes the value by setting the previous and next nodes to one before and after the current
         //reduces the size
         //returns the value that was removed
         DLNode prev = current.getPrev();
         prev.setNext(current.getNext());
         current.getNext().setPrev(prev);
         current.setNext(null);
         current.setPrev(null);
         this.size--;
         return current.getValue();
      }
   }
   
   /* inserts obj at front of list, increases size   */
   public void addFirst(Object obj)
   {
      //adds a node to the front of the list by setting new prev and next values of the head and the current node
      //increases the size
      DLNode newNode = new DLNode(obj, head, null);
      newNode.setNext(head.getNext());
      head.setNext(newNode);
      newNode.getNext().setPrev(newNode); 
      size++;
   }
   
   /* appends obj to end of list, increases size    */
   public void addLast(Object obj)
   {
      //adds a new node to the end of the list by setting a new prev
      //increases size
      DLNode newNode = new DLNode(obj, head.getPrev(), head);
      head.getPrev().setNext(newNode);
      head.setPrev(newNode);
      size++;
   }
   
   /* returns the first element in this list  */
   public Object getFirst()
   {
      //returns the first value
      return head.getNext().getValue();
   }
   
   /* returns the last element in this list  */
   public Object getLast()
   {
      //returns the last value
      return head.getPrev().getValue();      
   }
   
   /* returns and removes the first element in this list, or
       returns null if the list is empty  */
   public Object removeFirst()
   {
      if(head.getNext() == null) {
         return null;
      }
      else {
      //removes the first by replacing the prev and the next with null
      //reduces size
      //returns the removed value
         DLNode replacement = head.getNext().getNext();
         replacement.setPrev(head);
         DLNode removed = head.getNext();
         removed.setPrev(null);
         removed.setNext(null);
         head.setNext(replacement);
         this.size--;
         return removed.getValue();
      }
   }
   
   /* returns and removes the last element in this list, or
       returns null if the list is empty  */
   public Object removeLast()
   {
      if(head.getPrev() == null) {
         return null;
      }
      else {
      //removes the last by replacing the prev and the next with null
      //reduces size
      //returns the removed value
         DLNode replacement = head.getPrev().getPrev();
         replacement.setNext(head);
         DLNode removed = head.getPrev();
         removed.setPrev(null);
         removed.setNext(null);
         head.setPrev(replacement);
         this.size--;
         return removed.getValue();
      }
   }
   
   /*  returns a String with the values in the list in a 
       friendly format, for example   [Apple, Banana, Cucumber]
       The values are enclosed in [], separated by one comma and one space.
    */
   public String toString()
   {
   //returns the list as an easy to read string
      String beg = "";
      DLNode current = head.getNext();
      while(current != head){
         beg = beg + current.getValue();
         if(current.getNext() != head) {
            beg = beg + ", ";
         }
         current = current.getNext();  
      }
      return "[" + beg + "]";
   }
}