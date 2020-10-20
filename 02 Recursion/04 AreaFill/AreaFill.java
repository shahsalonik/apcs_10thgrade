// Name: Saloni Shah
// Date: 10/19/2020 (due date)

import java.util.*;
import java.io.*;

public class AreaFill
{
   private static char[][] grid = null;
   private static String filename = null;

   public static void main(String[] args) 
   {
      Scanner sc = new Scanner(System.in);
      while(true)  // what does this do?
      {
         System.out.print("Fill the Area of (-1 to exit): ");
         filename = sc.next();
         if(filename.equals("-1"))
         {
            sc.close();
            System.out.println("Good-bye");
            //System.exit(0); 
            return;  
         }
         grid = read(filename);
         String theGrid = display(grid);
         System.out.println( theGrid );
         System.out.print( "1-Fill or 2-Fill-and-Count: ");
         int choice = sc.nextInt();
         switch(choice)
         {
            case 1:
               {
                  System.out.print("\nEnter ROW COL to fill from: ");
                  int row = sc.nextInt();
                  int col = sc.nextInt(); 
                  fill(grid, row, col, grid[row][col]);
                  System.out.println( display(grid) );
                  break;
               }
            case 2:
               {
                  System.out.print("\nEnter ROW COL to fill from: ");
                  int row = sc.nextInt();
                  int col = sc.nextInt();
                  int count = fillAndCount(grid, row, col, grid[row][col]);
                  System.out.println( display(grid) );
                  System.out.println("count = " + count);
                  System.out.println();
                  break;
               }
            default:
               System.out.print("\nTry again! ");
         }
      }
   }
   
   /**
    * Reads the contents of the file into a matrix.
    * Uses try-catch.
    * @param filename The string representing the filename.
    * @returns A 2D array of chars.
    */
   public static char[][] read(String filename)
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File(filename));
      }
      catch (Exception e)
      {
         System.out.println("File not found");
         return null;
      }
      /* enter your code here */
      
      int rows = infile.nextInt();
      int columns = infile.nextInt();
      
      char[][] grid = new char[rows][columns];
      
      for(int x = 0; x < rows; x++) {
         grid[x] = infile.next().toCharArray();
      }
      return grid;
      
   }
   
   /**
    * Displays the text in the file as a matrix
    * @param g A 2-D array of chars.
    * @returns A string representing the 2D array.
    */
   public static String display(char[][] g)
   {
      int rows = g.length;
      int columns = g[0].length;
      String s = "";
      
      for(int r = 0; r < rows; r++) {
         for(int c = 0; c < columns; c++) {
            s = s + (g[r][c]);
         }
         s = s + "\n";
      }
      
      return s;
   
   }
   
   /**
    * Fills part of the matrix with a different character.
    * @param g A 2D char array.
    * @param r An int representing the row of the cell to be filled.
    * @param c An int representing the column of the cell to be filled.
    * @param ch The char which is being replaced.
    */
   public static void fill(char[][] g, int r, int c, char ch)
   {
   
      int rows = g.length;
      int columns = g[0].length;
      
      if(r < rows && c < columns && r >= 0 && c >= 0 && ch == g[r][c]) {
         g[r][c] = '*';
         fill(g, r+1, c, ch);
         fill(g, r-1, c, ch);
         fill(g, r, c-1, ch);
         fill(g, r, c+1, ch);
      }
      
   }
   
   /**
    * Fills part of the matrix with a different character.
    * Counts as you go.  Does not use a static variable.
    * @param g A 2D char array.
    * @param r An int representing the row of the cell to be filled.
    * @param c An int representing the column of the cell to be filled.
    * @param ch The char which is being replaced.
    * @return An int representing the number of characters that were replaced.
    */
   public static int fillAndCount(char[][] g, int r, int c, char ch)
   {
   
      int rows = g.length;
      int columns = g[0].length;
      
      if(r < rows && c < columns && r >= 0 && c >= 0 && ch == g[r][c]) {
         g[r][c] = '*';
         int count;
         count = 1 + fillAndCount(g, r+1, c, ch) + fillAndCount(g, r-1, c, ch) + fillAndCount(g, r, c-1, ch) + fillAndCount(g, r, c+1, ch);
         return count;
      }
   
      return 0; //never reached
   }
}