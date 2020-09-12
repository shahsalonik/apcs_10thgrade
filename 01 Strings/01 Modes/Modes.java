// Name: Saloni Shah
// Date: 09/08/2020

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
   */
   public static int[] calculateModes(int[] tally)
   {
      int maxNum = findMax(tally);
      int count = 0;
      int pos = 0;
      
      //looks through each index of tally and checks which indexes are equal to the max number
      //increases the count based on that
      for(int i = 0; i < tally.length; i++){
      
         if(tally[i] == maxNum){
            count++;
         }
      }
     
      //creates an array based on the number of modes there are
      int[] array = new int[count]; 
      
      //if the number at the index equals to the max number, put that index at that spot the array
      for(int x = 0; x < tally.length; x++){
      
         if(tally[x] == maxNum){
            array[pos] = x;
            pos++;
         }
      }
      
      //returns the array made 
      return array;
   }
     
  /**  
   * precondition:  tally.length > 0
   * 	             0 < k <= total number of values in data collection
   * postcondition: returns the kth value in the data collection
   *                represented by tally
   */
   public static int kthDataValue(int[] tally, int k)
   {
      int count = 0;
      
      //
      for (int x = 0; x < tally.length; x++) {
      
         int y = tally[count];         //y is equal to the value at the specified index
      
         if (k <= tally[x]) {          //if k is less than or equal to the value at that position, return the index
            return x;
         }
         else {
            k = k - y;  //subtract y from the value of k 
         }
         count++; //increase count
      }
      
      return -1;
   }
     
  /**
   * precondition:  nums.length > 0
   * postcondition: returns the maximal value in nums
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
