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
   
   public boolean add(E obj)
   {
      myHeap.add(obj);
      heapUp(1);
      return true;
   }
   
   public E remove()
   {
      heapDown(1, myHeap.size());
      E removed = myHeap.remove(1);
      return removed;
   }
   
   public E peek()
   {
      if(myHeap.size() == 1) {
         return null;
      }
      else {
         return myHeap.get(1);
      }
   }
   
   public boolean isEmpty()
   {
      return (peek() == null);
   }
   
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
   
   private void swap(int a, int b)
   {
      E temp = myHeap.get(a);
      myHeap.set(a, myHeap.get(b));
      myHeap.set(b, temp);
   }
   
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
   
   public String toString()
   {
      return myHeap.toString();
   }  
}
