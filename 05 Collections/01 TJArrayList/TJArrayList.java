// Name: Saloni Shah
// Date: 12/14/2020 (due date)

/**
 * Implements the cheat sheet's List interface.  Implements generics.
 * The backing array is an array of (E[]) new Object[10];
 * @override toString()
 */ 
public class TJArrayList<E>
{
   private int size;							//stores the number of objects
   private E[] myArray;
   public TJArrayList()                //default constructor makes 10 cells
   {
      myArray = (E[]) new Object[10];
      size = 0;
   }
   public int size()
   {
      return size;
   }
 	/* appends obj to end of list; increases size;
      must be an O(1) operation when size < array.length, 
         and O(n) when it doubles the length of the array.
	  @return true  */
   public boolean add(E obj)
   {
      //adds the object to the end of the list
      if(size < myArray.length) {
         myArray[size] = obj;
      }
      
      //when the length of the array is doubled,
      //the original array is copied into the new array and
      //the object is added at the end
      //the new array is then put into the old array
      //size is incremented and returns true
      E[] newArray = (E[]) new Object[myArray.length * 2];
      for(int x = 0; x < myArray.length; x++) {
         newArray[x] = myArray[x];
      }
      newArray[size] = obj;
      myArray = newArray;
      size++;
      return true;
   }
   /* inserts obj at position index.  increments size. 
		*/
   public void add(int index, E obj) throws IndexOutOfBoundsException  //this the way the real ArrayList is coded
   {
      if(index > size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
         
      for(int x = size - 1; x >= index; x--) {
         myArray[x+1] = myArray[x];
      }   
       
      size++;
      myArray[index] = obj;
   }

   /* return obj at position index.  
		*/
   public E get(int index) throws IndexOutOfBoundsException
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
     
      return myArray[index];
   }
   /**
    * Replaces obj at position index. 
    * @return the object is being replaced.
    */  
   public E set(int index, E obj) throws IndexOutOfBoundsException  
   { 
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      
      E replaced = myArray[index];
      myArray[index] = obj;
      return replaced;
      
   }
 /*  removes the node from position index. shifts elements 
     to the left.   Decrements size.
	  @return the object at position index.
	 */
   public E remove(int index) throws IndexOutOfBoundsException  
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
     
      E replaced = myArray[index];
      
      for(int x = index + 1; x < myArray.length; x++) {
         myArray[x-1] = myArray[x];
      }
      
      size--;
      return replaced;
   }
	   /*
		   This method compares objects.
         Must use .equals(), not ==
     	*/
   public boolean contains(E obj)
   {
      for(int i = 0; i < myArray.length; i++) {
         if(obj.equals(myArray[i])) {
            return true;
         }
      }
      return false;
   }
	 /*returns a String of E objects in the array with 
       square brackets and commas.
     	*/
   public String toString()
   {
      String str = myArray[0] + "";
      
      for(int x = 1; x < size; x++) {
         str = str + ", " + myArray[x];
      }
      
      return "[" + str + "]";
   }
}