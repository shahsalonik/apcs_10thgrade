// Name: Saloni Shah
// Date: 09/14/2020 (due date)

public class Modes
{
   public static void main(String[] args)
   {
      int[] tally = {0,0,10,5,10,0,7,1,0,6,0,10,3,0,0,1};
      display(tally);
      int[] modes = calculateModes(tally);
      display(modes);
      int sum = 0;
      for(int k = 0; k < tally.length; k++)
          sum += tally[k];
      System.out.println("kth \tindex"); 
      for(int k = 1; k <= sum; k++)
          System.out.println(k + "\t\t" + kthDataValue(tally, k));          
   }
     
  /**
   * precondition: tally.length > 0
   * postcondition: returns an int array that contains the modes(s);
   *                the array's length equals the number of modes.
   *
   * @param tally the array with the frequencies of the index numbers
   */
   public static int[] calculateModes(int[] tally)
   {
      int maxNum = findMax(tally);
      int count = 0;
      int pos = 0;
      
      //forms a new array based on the number of modes in the array passed
      for(int i = 0; i < tally.length; i++){
      
         if(tally[i] == maxNum){
            count++;
         }
      }
     
      //creates the new array
      int[] array = new int[count]; 
      
      //puts the modes into the new array
      for(int x = 0; x < tally.length; x++){
      
         if(tally[x] == maxNum){
            array[pos] = x;
            pos++;
         }
      }
      
      //returns the array of modes 
      return array;
   }
     
  /**  
   * precondition:  tally.length > 0
   * 	             0 < k <= total number of values in data collection
   * postcondition: returns the kth value in the data collection
   *                represented by tally
   * 
   * @param tally the array with the frequencies of the index numbers
   * @param k the number in the array whose value needs to be found
   */
   public static int kthDataValue(int[] tally, int k)
   {
      int count = 0;
      
      //checks to see which number occurs at the kth place in the array passed.
      for (int x = 0; x < tally.length; x++) {
      
         int y = tally[count];         //y is equal to the value at the specified index
      
         if (k <= tally[x]) {          //if k is less than or equal to the value at that position, return the index
            return x;
         }
         else {
            k = k - y;  //subtract y from the value of k and repeat
         }
         count++; //increase count
      }
      
      return -1;
   }
     
  /**
   * precondition:  nums.length > 0
   * postcondition: returns the maximal value in nums
   *
   * @param nums the array of integers
   */
   public static int findMax(int[] nums)
   {
      int pos = 0;
      for(int k = 1; k < nums.length; k++)
         if(nums[k] > nums[pos])
            pos = k;
      return nums[pos];
   }
   
   public static void display(int[] args)
   {
      for(int k = 0; k < args.length; k++)
         System.out.print(args[k] + " ");
      System.out.println();
      System.out.println();
   }
}
