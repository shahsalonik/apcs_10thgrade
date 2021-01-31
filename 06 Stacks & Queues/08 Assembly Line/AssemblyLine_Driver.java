//Name: Saloni Shah    
//Date: 01/25/2021 (due date)
import java.util.*;

public class AssemblyLine_Driver
{
   static int NDISKS = 50;
   static int MAXRADIUS = 100;
   public static void main(String[] args)
   {
      AssemblyLine a = new AssemblyLine(NDISKS, MAXRADIUS);
      a.showInput();
      a.process();
      a.showOutput();
   }
}

class AssemblyLine
{
   private Queue<Disk> assemblyLineIn;
   private Queue<Pyramid> assemblyLineOut;
   private Pyramid robotArm;
	/**
		* initializes this object so the assemblyLineIn contains 
		* nDisks with random radii;  assemblyLineOut is initialized to * an empty Queue; robotArm is initialized to an empty Pyramid.
		**/
	//Part A
   public AssemblyLine( int nDisks, int maxRadius )
   {	
      assemblyLineIn = new LinkedList<Disk>();
      //nDisks of random sizes
      for(int x = 0; x < nDisks; x++)
      {
         assemblyLineIn.add(new Disk((int)((Math.random() * maxRadius) + 1)));
      }
      assemblyLineOut = new LinkedList<Pyramid>();
      robotArm = new Pyramid();   
   }

	/**
		* "flips over" the pyramid in the robotArm and adds it to the
		* assemblyLineOut queue.
		* Precondition:  robotArm is not empty and holds an inverted 
		*				pyramid of disks
		**/
	// Part B
   private void unloadRobot()
   { 
      Pyramid p = new Pyramid();
      //flip code
      while(!robotArm.isEmpty()) {
         p.push(robotArm.pop());
      }
      assemblyLineOut.add(p);
   }

	/**
		* processes all disks from assemblyLineIn; a disk is processed
		* as follows:  if robotArm is not empty and the next disk does
		* not fit on top of robotArm (which must be an inverted 
		* pyramid) then robotArm is unloaded first; the disk from
		* assemblyLineIn is added to robotArm; when all the disks
		* have been retrieved from assemblyLineIn, robotArm is unloaded.
		*  Precondition:  robotArm is empty;
		*				assemblyLineOut is empty
		**/
	//Part C
   public void process()
   {
      //goes for as long as the assembly line has disks in it
      while(!assemblyLineIn.isEmpty()) {
         Disk temp = assemblyLineIn.remove();
         
         //checks if the robot arm's first disk is bigger than the assembly line's first disk.
         if(!(robotArm.isEmpty()) && (robotArm.peek().compareTo(temp) > 0)) {
            //if yes, unloads robot and then adds the disk to the robot arm
            unloadRobot();
            robotArm.add(temp);
         }
         //otherwise checks if it is smaller
         else if ((!robotArm.isEmpty()) && (robotArm.peek().compareTo(temp) < 0)){
         //if yes, pushes to the stack
            robotArm.push(temp);
         }
         //if the arm is empty, pushes
         else if(robotArm.isEmpty()) {
            robotArm.push(temp);
         }
      }
      
      //unloads robot at the very end
      unloadRobot();
      
   }
   
   public void showInput()
   {
      System.out.println(assemblyLineIn);
   }
   public void showOutput()
   {
      System.out.println(assemblyLineOut);
   }
}
//Disk is standard and straightforward.
class Disk implements Comparable<Disk>
{
   private int radius;
   
   public Disk(int r) {
      radius = r;
   }
   
   public int getRadius() {
      return radius;
   }
   
   public int compareTo(Disk d) {
      if(radius < d.getRadius())
      {
         return -1;
      }
      else if(radius == d.getRadius()) {
         return 0;
      }
      else {
         return 1;
      }
   }
   
   public String toString() {
      return "" + radius;
   }
   
}
//Pyramid is sly.
class Pyramid extends Stack<Disk>
{
   
}