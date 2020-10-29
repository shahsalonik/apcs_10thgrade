// Name: Saloni Shah
// Date: 10/26/2020 (due date)

import java.util.*;
import java.io.*;

public class MazeMaster
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter the maze's filename (no .txt): ");
      //Maze m = new Maze(sc.next()+".txt");
      Maze m = new Maze();    //extension
      m.display();      
      System.out.println("Options: ");
      System.out.println("1: Mark all dots.");
      System.out.println("2: Mark all dots and display the number of recursive calls.");
      System.out.println("3: Mark only the correct path.");
      System.out.println("4: Mark only the correct path. If no path exists, say so.");
      System.out.println("5: Mark only the correct path and display the number of steps.\n\tIf no path exists, say so.");
      System.out.print("Please make a selection: ");
      m.solve(sc.nextInt());
      m.display();      //display solved maze
   } 
}

class Maze
{
   //constants
   private final char WALL = 'W';
   private final char DOT = '.';
   private final char START = 'S';
   private final char EXIT = 'E';
   private final char TEMP = 'o';
   private final char PATH = '*';
   //instance fields
   private char[][] maze;
   private int startRow, startCol;
  
   //constructors
	
	/* 
	 * EXTENSION 
	 * This is a no-arg constructor that generates a random maze
	 */
   public Maze()
   {
   //random size
      int rowNum = (int) (Math.random() * 10 + 1);
      int colNum = (int) (Math.random() * 10 + 1);
      char[][] randomMaze = new char[rowNum][colNum];
     //random start placement 
      int begRow = (int) (Math.random() * 5);
      int begCol = (int) (Math.random() * 5);
     //random end placement 
      int endRow = (int) (Math.random() * 5);
      int endCol = (int) (Math.random() * 5);
      //if the row of the matrix is less than where S is placed, rerun the position of S
      while(rowNum <= begRow) {
         begRow = (int) (Math.random() * 5);
      }
      //if the column of the matrix is less than where S is placed, rerun the position of S
      while(colNum <= begCol) {
         begCol = (int) (Math.random() * 5); 
      }
      //if the row of the matrix is less than where E is placed, rerun the position of E
      while(rowNum <= endRow) {
         endRow = (int) (Math.random() * 5);
      }
      //if the column of the matrix is less than where E is placed, rerun the position of E
      while(colNum <= endCol) {
         endCol = (int) (Math.random() * 5);
      }
      
      //if the start and the end are the same, rerun the end position
      if(startRow == endRow && startCol == endCol) {
         endRow = (int) (Math.random() * 5);
         endCol = (int) (Math.random() * 5);
      }
      
      //starts building the maze; puts down dots
      for(int r = 0; r < randomMaze.length; r++) {
         for(int c = 0; c < randomMaze[0].length; c++) {
            randomMaze[r][c] = DOT;
         }
      }
      
      //number of walls
      int wallNums = (int) (Math.random() * (rowNum * colNum));
      
      //repeats for the number of walls
      for(int x = 0; x < wallNums; x++) {
         int randRow = (int) (Math.random() * x);
         int randCol = (int) (Math.random() * x);
         
         //if the random row is greater than the matrix, rerun it
         while(randRow >= rowNum) {
            randRow = (int) (Math.random() * x);
         }
         //if the random column is greater than the matrix, rerun it
         while(randCol >= colNum) {
            randCol = (int) (Math.random() * x);
         }
         //places a wall at the final randRow and randCol position
         randomMaze[randRow][randCol] = WALL;
      }
         
         //places start and exit
      randomMaze[begRow][begCol] = START;
      randomMaze[endRow][endCol] = EXIT;
      
      //identifies start location
      for(int r = 0; r < randomMaze.length; r++)
      {
         for(int c = 0; c < randomMaze[0].length; c++)
         { 
            if(randomMaze[r][c] == START)      //identify start location
            {
               startRow = r;
               startCol = c;
            }
         }
      }
      
      maze = randomMaze;
      
      System.out.println();
   }
	
	/* 
	 * Copy Constructor  
	 */
   public Maze(char[][] m)  
   {
      maze = m;
      for(int r = 0; r < maze.length; r++)
      {
         for(int c = 0; c < maze[0].length; c++)
         { 
            if(maze[r][c] == START)      //identify start location
            {
               startRow = r;
               startCol = c;
            }
         }
      }
   } 
	
	/* 
	 * Use a try-catch block
	 * Use next(), not nextLine()  
	 */
   public Maze(String filename)    
   {
      Scanner infile = null;
      try {
         infile = new Scanner(new File(filename));
      }
      catch(FileNotFoundException e) {
         System.out.println("Sorry, that file cannot be found");
         maze = null;
         System.exit(0);
      }
      
      int rows = infile.nextInt();
      int columns = infile.nextInt();
      
      char[][] grid = new char[rows][columns];
      
      for(int x = 0; x < rows; x++) {
         grid[x] = infile.next().toCharArray();
      }
      
      maze = grid;
      
      for(int r = 0; r < maze.length; r++)
      {
         for(int c = 0; c < maze[0].length; c++)
         { 
            if(maze[r][c] == START)      //identify start location
            {
               startRow = r;
               startCol = c;
            }
         }
      }
   }
   
   public char[][] getMaze()
   {
      return maze;
   }
   
   /**
   * Prints the maze
   */
   public void display()
   {
      if(maze==null) 
         return;
      for(int a = 0; a<maze.length; a++)
      {
         for(int b = 0; b<maze[0].length; b++)
         {
            System.out.print(maze[a][b]);
         }
         System.out.println();
      }
      System.out.println();
   }
   
   /**
   * Calls an option depending on the user input number
   * @param n the case number
   */
   public void solve(int n)
   {
      switch(n)
      {
         case 1:
            {
               markAll(startRow, startCol);
               break;
            }
         case 2:
            {
               int count = markAllAndCountRecursions(startRow, startCol);
               System.out.println("Number of recursions = " + count);
               break;
            }
         case 3:
            {
               markTheCorrectPath(startRow, startCol);
               break;
            }
         case 4:         //use mazeNoPath.txt 
            {
               if( !markTheCorrectPath(startRow, startCol) )
                  System.out.println("No path exists."); 
               break;
            }
         case 5:
            {
               if( !markCorrectPathAndCountSteps(startRow, startCol, 0) )
                  System.out.println("No path exists."); 
               break;
            }
         default:
            System.out.println("File not found");   
      }
   }
   
	/* 
	 * From handout, #1.
	 * Fill the maze, mark every step.
	 * This is a lot like AreaFill.
	 */ 
   public void markAll(int r, int c)
   {
      //rows and columns for the matrix
      int rows = maze.length;
      int columns = maze[0].length;
      
      //base: boundary conditions
      if(r < 0 || r >= rows || c < 0 || c >= columns) {
         return;
      }
      //recurs if the spot is a start or a dot. if it is an accessible dot, replace it with an asterisk
      else if(maze[r][c] == START || maze[r][c] == DOT) {
         if(maze[r][c] == DOT) {
            maze[r][c] = PATH;
         }
         markAll(r+1, c);
         markAll(r-1, c);
         markAll(r, c+1);
         markAll(r, c-1);
      }
      else {
         return;
      }
      
   }

	/* 
	 * From handout, #2.
	 * Fill the maze, mark and count every recursive call as you go.
	 * Like AreaFill's counting without a static variable.
	 */ 
   public int markAllAndCountRecursions(int r, int c)
   {  
      //rows and columns
      int rows = maze.length;
      int columns = maze[0].length;
      
      //base: boundary conditions, returns 1
      if(r < 0 || r >= rows || c < 0 || c >= columns) {
         return 1;
      }
      //recurs and returns 1 if the spot is a start or a dot. if it is an accessible dot, replace it with an asterisk
      else if(maze[r][c] == START || maze[r][c] == DOT) {
         if(maze[r][c] == DOT) {
            maze[r][c] = PATH;
         }
         return 1 + markAllAndCountRecursions(r+1, c) + markAllAndCountRecursions(r-1, c) + markAllAndCountRecursions(r, c+1) + markAllAndCountRecursions(r, c-1);
      }
      else {
         return 1;
      }
   }

   /* 
	 * From handout, #3. 
	 * Recur until you find E, then mark the True path.
	 */ 	
   
   public boolean markTheCorrectPath(int r, int c) {
   
      //rows and columns
      int rows = maze.length;
      int columns = maze[0].length;
   
      //base: bounday, returns false
      if(r < 0 || r >= rows || c < 0 || c >= columns) {
         return false;
      }
      //true if the spot is exit
      else if(maze[r][c] == EXIT) {
         return true;
      }
      //if the spot is a start and any of the pathways are accessible, returns true
      else if(maze[r][c] == START) {
         if(markTheCorrectPath(r+1, c) || markTheCorrectPath(r, c-1) || markTheCorrectPath(r, c+1) || markTheCorrectPath(r-1, c)) {
            return true;
         }
         else {
            return false;
         }
      }
      else if(maze[r][c] == DOT) {
         //temporary char to prevent stack overflow
         maze[r][c] = TEMP;
         //if the spot is a dot and any of the pathways are accessible, replaces the space with an asterisk and returns true
         if(markTheCorrectPath(r, c-1) || markTheCorrectPath(r+1, c) || markTheCorrectPath(r, c+1) || markTheCorrectPath(r-1, c)) {
            if(maze[r][c] == TEMP) {
               maze[r][c] = PATH;
            }
            return true;
         }
         //if none of the paths are accessible, replaces the temp with the original dot and returns false
         if(!markTheCorrectPath(r+1, c) && !markTheCorrectPath(r-1, c) && !markTheCorrectPath(r, c+1) && !markTheCorrectPath(r, c-1)) {
            if(maze[r][c] == TEMP) {
               maze[r][c] = DOT;
            }
            return false;
         }
         //returns false for any other conditions
         else {
            return false;
         }
      }
      else {
         return false;
      }
   
   }
	
	
   /*  4   Mark only the correct path. If no path exists, say so.
           Hint:  the method above returns the boolean that you need. */
      

   /* 
	 * From handout, #5.
	 * Solve the maze, mark the path, count the steps. 	 
	 * Mark only the correct path and display the number of steps.
	 * If no path exists, say so.
	 */ 	
   public boolean markCorrectPathAndCountSteps(int r, int c, int count)
   {
   //does the same thing as the previous method (#3 or markTheCorrectPath)
      int rows = maze.length;
      int columns = maze[0].length;
   
      if(r < 0 || r >= rows || c < 0 || c >= columns) {
         return false;
      }
      else if(maze[r][c] == EXIT) {
      //once the exit is found, print the count
         System.out.println("Number of Steps: " + count);
         return true;
      }
      //adds 1 per recursion
      else if(maze[r][c] == START) {
         if(markCorrectPathAndCountSteps(r+1, c, count+1) || markCorrectPathAndCountSteps(r, c-1, count+1) || markCorrectPathAndCountSteps(r, c+1, count+1) || markCorrectPathAndCountSteps(r-1, c, count+1)) {
            return true;
         }
         else {
            return false;
         }
      }
      //adds 1 per recursion
      else if(maze[r][c] == DOT) {
         maze[r][c] = TEMP;
         if(markCorrectPathAndCountSteps(r, c-1, count+1) || markCorrectPathAndCountSteps(r+1, c, count+1) || markCorrectPathAndCountSteps(r, c+1, count+1) || markCorrectPathAndCountSteps(r-1, c, count+1)) {
            if(maze[r][c] == TEMP) {
               maze[r][c] = PATH;
            }
            return true;
         }
         if(!markCorrectPathAndCountSteps(r+1, c, count+1) && !markCorrectPathAndCountSteps(r-1, c, count+1) && !markCorrectPathAndCountSteps(r, c+1, count+1) && !markCorrectPathAndCountSteps(r, c-1, count+1)) {
            if(maze[r][c] == TEMP) {
               maze[r][c] = DOT;
            }
            return false;
         }
         else {
            return false;
         }
      }
      else {
         return false;
      
      }
   }
}

/*****************************************
 
 ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.WW..W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 WW.....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, say so.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, say so.
 Please make a selection: 1
 WWWWWWWW
 W****W*W
 WW*WW**W
 W****W*W
 W*W*WW*E
 S*W*WW*W
 WW*****W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
 
  ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.WW..W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 WW.....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, say so.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, say so.
 Please make a selection: 2
 Number of recursions = 105
 WWWWWWWW
 W****W*W
 WW*WW**W
 W****W*W
 W*W*WW*E
 S*W*WW*W
 WW*****W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
 
  ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.WW..W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 WW.....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, say so.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, say so.
 Please make a selection: 3
 WWWWWWWW
 W....W.W
 WW.WW..W
 W***.W.W
 W*W*WW*E
 S*W*WW*W
 WW.****W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
 
     
  ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): mazeNoPath
 WWWWWWWW
 W....W.W
 WW.WW..E
 W..WW.WW
 W.W.W..W
 S.W.WW.W
 WWW....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, say so.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, say so.
 Please make a selection: 4
 No path exists.
 WWWWWWWW
 W....W.W
 WW.WW..E
 W..WW.WW
 W.W.W..W
 S.W.WW.W
 WWW....W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
 
  ----jGRASP exec: java MazeMaster_teacher
 Enter the maze's filename (no .txt): maze1
 WWWWWWWW
 W....W.W
 WW.WW..W
 W....W.W
 W.W.WW.E
 S.W.WW.W
 WW.....W
 WWWWWWWW
 
 Options: 
 1: Mark all dots.
 2: Mark all dots and display the number of recursive calls.
 3: Mark only the correct path.
 4: Mark only the correct path. If no path exists, say so.
 5: Mark only the correct path and display the number of steps.
 	If no path exists, say so.
 Please make a selection: 5
 Number of steps = 14
 WWWWWWWW
 W....W.W
 WW.WW..W
 W***.W.W
 W*W*WW*E
 S*W*WW*W
 WW.****W
 WWWWWWWW
 
 
  ----jGRASP: operation complete.
  ********************************************/