// Name: Saloni Shah
// Date: 04/06/2021 (due date)

public class HeapSort
{
   public static int SIZE;  //9 or 100
	
   public static void main(String[] args)
   {
      //Part 1: Given a heap, sort it. Do this part first. 
      /*SIZE = 9;  
      double heap[] = {-1,99,80,85,17,30,84,2,16,1};
      
      display(heap);
      sort(heap);
      display(heap);
      System.out.println(isSorted(heap));*/
      
      //Part 2:  Generate 100 random numbers, make a heap, sort it.
      SIZE = 100;
      double[] heap = new double[SIZE + 1];
      heap = createRandom(heap);
      display(heap);
      makeHeap(heap, SIZE);
      display(heap); 
      sort(heap);
      display(heap);
      System.out.println(isSorted(heap));
   }
   
	//******* Part 1 ******************************************
   public static void display(double[] array)
   {
      for(int k = 1; k < array.length; k++)
         System.out.print(array[k] + "    ");
      System.out.println("\n");	
   }
   
   public static void sort(double[] array)
   {
      /* enter your code here */
      int size = SIZE;
      for(int n = size; n > 2; n--){
         swap(array, 1, n);
         heapDown(array, 1, n-1);
      }
   
      if(array[1] > array[2])   //just an extra swap, if needed.
         swap(array, 1, 2);
   }
  
   public static void swap(double[] array, int a, int b)
   {
      double temp = array[a];
      array[a] = array[b];
      array[b] = temp;
   }
   
   public static void heapDown(double[] array, int k, int size) //array, current value, size of the array
   {
      //base case
      if(k >= size || 2 * k >= size) {
         return;
      }
      else {
         //setting a basic maxChild
         int maxChildIndex = 2 * k;
         //maxChildIndex increases if the right child is less than the left child
         if((2 * k) + 1 <= size && array[2 * k] < array[(2 * k) + 1]) {
            maxChildIndex++;
         }
         //checks if the current is less than its greatest child
         if(array[k] < array[maxChildIndex]) {
            //swaps the two and then recurs
            swap(array, k, maxChildIndex);
            heapDown(array, maxChildIndex, size);
         }
      }
   }
   
   public static boolean isSorted(double[] arr)
   {
      //returns false if the one of the children is greater than its parent 
      for(int x = 1; x < arr.length/2; x++) {
         if(arr[x] > arr[2 * x] || arr[x] > arr[(2 * x) + 1]) {
            return false;
         }
      } 
      return true; 
   }
   
   //****** Part 2 *******************************************

   //Generate 100 random numbers (between 1 and 100, formatted to 2 decimal places) 
   public static double[] createRandom(double[] array)
   {  
      array[0] = -1;   //because it will become a heap
      
      for(int x = 1; x < array.length; x++) {
         array[x] = (double) ((int) (Math.random() * 100 + 1));
      }
      
      return array;
   }
   
   //turn the random array into a heap
   public static void makeHeap(double[] array, int size)
   {
      for(int k = 1; k < size/2; k++) {
         heapDown(array, k, size);
      }
   }
}
