public class Warmup {

   public static void main (String[] args) {
   
   int[][] array = {{3, 3, 3, 3}, {3, 3, 3, 3}, {3, 3, 3, 3}, {3, 3, 3, 3}, {3, 3, 3, 3}};
   recursive_display(array, 0);
   
   }  

   public static void recursive_display(int array[][], int n) {
   
      int rows = array.length;
      int columns = array[0].length;
      int area = rows * columns;
   
      if(n < area) {
         int row = n/columns;
         int column = n % columns;
         if(column == 0 && n != 0) {
            System.out.println();
         }
         System.out.print(array[row][column]);
         recursive_display(array, n+1);
      }
   
   }

}