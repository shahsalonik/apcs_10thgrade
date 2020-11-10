public class Test{

   public static void main(String[] args) {
      Comparable[] a = {1, 7, 9, 5, 4, 12};
      
      insertionSort(a);
   }
   
   public static void insertionSort(Comparable[] a)
   {
      for (int i = 1; i < a.length; i++)
      {
         Comparable temp = a[i];
         int j = i - 1;
         while (j >= 0 && temp.compareTo(a[j]) > 0)
         {
            a[j + 1] = a[j];
            j--;
         }
         a[j + 1] = temp;  
      }
   }
   
}