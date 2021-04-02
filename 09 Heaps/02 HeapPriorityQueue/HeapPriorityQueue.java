 //Name: Saloni Shah 
 //Date: 04/06/2021 (due date)

import java.util.*;

/* implement the API for java.util.PriorityQueue
 * test this class with HeapPriorityQueue_Driver.java.
 * test this class with LunchRoom.java.
 * add(E) and remove()  must work in O(log n) time
 */
public class HeapPriorityQueue<E extends Comparable<E>> 
{
   private ArrayList<E> myHeap;
   
   public HeapPriorityQueue()
   {
      myHeap = new ArrayList<E>();
      myHeap.add(null);
   }
   
   /**
    * Adds the generic passed to the heap, then sorts based on descending order
    * @param obj
    * @return true when the generic is added
    */
   public boolean add(E obj)
   {
      myHeap.add(obj);
      heapUp(1);
      return true;
   }
   
   /**
    * Removes the smallest element by sorting based on ascending order
    * @return the value of the removed generic 
    */
   public E remove()
   {
      heapDown(1, myHeap.size());
      E removed = myHeap.remove(1);
      return removed;
   }
   
   /**
    * Returns the value of the first element of the heap
    * @return the value of the first element of the heap
    */
   public E peek()
   {
      if(myHeap.size() == 1) {
         return null;
      }
      else {
         return myHeap.get(1);
      }
   }
   
   /**
    * Checks whether the heap is empty
    * @return true if the first value of the heap is null
    */
   public boolean isEmpty()
   {
      return (peek() == null);
   }
   
   /**
    * Sorts the heap in descending order.
    * @param k the place from where the sorting should take place
    */
   private void heapUp(int k)
   {
      int current = myHeap.size() - 1;
      while(current > 1) {
         int parent = (current / 2);
         if(myHeap.get(parent).compareTo(myHeap.get(current)) > 0) {
            swap(parent, current);
            current = parent;
         }
         else {
            break;
         }
      }
   }
   
   /**
    * Swaps two elements given their positions
    * @param a the index of the first element to be swapped
    * @param b the index of the second element to be swapped
    */
   private void swap(int a, int b)
   {
      E temp = myHeap.get(a);
      myHeap.set(a, myHeap.get(b));
      myHeap.set(b, temp);
   }
   
   /**
    * Sorts the heap in ascending order.
    * @param k the index from where the swapping should take place
    * @param size the size of the heap
    */
   private void heapDown(int k, int size)
   {
      if(k >= size || 2 * k >= size) {
         return;
      }
      else {
         int maxChild = 2 * k;
         if(maxChild + 1 < size && myHeap.get(maxChild).compareTo(myHeap.get(maxChild+1)) > 0) {
            maxChild++;
         }
         if(myHeap.get(k).compareTo(myHeap.get(maxChild)) > 0){
            swap(k, maxChild);
            heapDown(maxChild, size);
         }
      }
   }
   
   /**
    * Returns a string representation of the heap
    * @return a string version of the heap 
    */
   public String toString()
   {
      return myHeap.toString();
   }  
}
