public class Warmup2 {

   public static void main (String[] args) {
   
      System.out.println(grid_moves(2, 2));
      System.out.println(lattice_moves(2, 2, 2));
      System.out.println(gridPointMoves(2, 2, 1, 2));
      
   }  

   public static int grid_moves(int m, int n) {
   
      if(m == 0 && n== 0) {
         return 1;
      }
      else if(m == 0) {
         return grid_moves(m, n-1);
      }
      else if(n == 0) {
         return grid_moves(m-1, n);
      }
      else {
         return grid_moves(m, n-1) + grid_moves(m-1, n);
      }
   
   }
   
   public static int lattice_moves(int m, int n, int l) {
   
      if (m == 0 && n == 0 && l == 0) {
         return 1;
      }
      else if (m == 0 && l == 0) {
         return lattice_moves(m, n-1, l);
      }
      else if (n == 0  && l == 0) {
         return lattice_moves(m-1, n, l);
      }
      else if (m == 0  && n == 0) {
         return lattice_moves(m, n, l-1);
      }
      else if (m == 0) {
         return lattice_moves(m, n-1, l) + lattice_moves(m, n, l-1);
      }
      else if (n == 0) {
         return lattice_moves(m-1, n, l) + lattice_moves(m, n, l-1);
      }
      else if (l == 0) {
         return lattice_moves(m-1, n, l) + lattice_moves(m, n-1, l);
      }
      else {
         return lattice_moves(m, n-1, l) + lattice_moves(m-1, n, l) + lattice_moves(m, n, l-1);
      }
   
   }
   
   
   public static int gridPointMoves(int m, int n, int x, int y) {
      return grid_moves(x, y) * grid_moves(m-x, n-y);
   }

}