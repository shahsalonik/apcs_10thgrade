// Name: Saloni Shah
// Date: 10/05/2020
import java.util.*;
import java.io.*;
import java.text.DecimalFormat;
//here any additional imports that you may need

public class Cemetery
{
   public static void main (String [] args)
   {
      //File file = new File("cemetery_short.txt");
      File file = new File("cemetery.txt");
      int numEntries = countEntries(file);
      Person[] cemetery = readIntoArray(file, numEntries); 
      //see what you have
      for (int i = 0; i < cemetery.length; i++) 
         System.out.println(cemetery[i]);
       
       DecimalFormat df2 = new DecimalFormat("0.000");
         
      int min = locateMinAgePerson(cemetery);
      int max = locateMaxAgePerson(cemetery); 
      //String average = df2.format(locateMeanDeathAge(cemetery));
      //int peopleUnderThirty = deathUnderThirty(cemetery);
      System.out.println("\nIn the St. Mary Magdelene Old Fish Cemetery --> ");
      System.out.println("Name of youngest person: " + cemetery[min].getName());
      System.out.println("Age of youngest person: " + cemetery[min].getAge());    
      System.out.println("Name of oldest person: " + cemetery[max].getName());
      System.out.println("Age of oldest person: " + cemetery[max].getAge()); 
      //you may create other testing cases here
      //comment them out when you submit your file to Grade-It
      
      //System.out.println("Average age of death: " + average);
      //System.out.println("People dead under 30: " + peopleUnderThirty);
      
          
   }
   
   /* Counts and returns the number of entries in File f. 
      Returns 0 if the File f is not valid.
      Uses a try-catch block.   
      @param f -- the file object
   */
   public static int countEntries(File f)
   {
      Scanner cemFile = null;
      int entryCount = 0;
      String reader = "";
      
      try {
        
         cemFile = new Scanner(f);
         
         while(cemFile.hasNextLine()) {
            reader = cemFile.nextLine();
            entryCount++;
         }
      }
      catch(IOException e) {
         System.out.println("File not valid");
         return 0;
      }
      
      return entryCount;
   }

   /* Reads the data from file f (you may assume each line has same allignment).
      Fills the array with Person objects. If File f is not valid return null.
      @param f -- the file object 
      @param num -- the number of lines in the File f  
   */
   public static Person[] readIntoArray (File f, int num)
   {  
      Person[] personArray = new Person[num];
      Scanner personScanner = null;
      
      try {
         personScanner = new Scanner(f);
         for(int x = 0; x < num; x++) { 
            personArray[x] = makeObjects(personScanner.nextLine());
         }
      }
      
      catch(IOException e) {
         System.out.println("File not valid");
         System.exit(0);
      }
      
      return personArray;
   }
   
   /* A helper method that instantiates one Person object.
      @param entry -- one line of the input file.
      This method is made public for gradeit testing purposes.
      This method should not be used in any other class!!!
   */
   public static Person makeObjects(String entry)
   {
      String name = entry.substring(0, 25);
      String burialDate = entry.substring(25, 37);
      String age = entry.substring(37, 42);
      
      Person p = new Person(name, burialDate, age);
      
      return p;
   }  
   
   /* Finds and returns the location (the index) of the Person
      who is the youngest. (if the array is empty it returns -1)
      If there is a tie the lowest index is returned.
      @param arr -- an array of Person objects.
   */
   public static int locateMinAgePerson(Person[] arr)
   {
      int pos = 0;
      for(int k = 1; k < arr.length; k++) {
         if(arr[k].getAge() < arr[pos].getAge()) {
            pos = k; 
         }
      }
      return pos;
   }   
   
   /* Finds and returns the location (the index) of the Person
      who is the oldest. (if the array is empty it returns -1)
      If there is a tie the lowest index is returned.
      @param arr -- an array of Person objects.
   */
   public static int locateMaxAgePerson(Person[] arr)
   {
      int pos = 0;
      for(int k = 1; k < arr.length; k++) {
         if(arr[k].getAge() > arr[pos].getAge()) {
            pos = k; 
         }
      }
      return pos;
   } 
   
   public static double locateMeanDeathAge(Person[] arr) {
   
      double sum = 0;
      
      for(int x = 0; x < arr.length; x++) {
         sum += arr[x].getAge();
      }
      
      double average = sum/arr.length;
      
      return average;
   
   }
   
   public static int deathUnderThirty(Person[] arr) {
   
      int count = 0;
      
      for(int pos = 0; pos < arr.length; pos++) {
         if(arr[pos].getAge() < 30.0) {
            count++;
         }
      } 
      
      return count;
   }
          
} 

class Person
{
   //constant that can be used for formatting purposes
   private static final DecimalFormat df = new DecimalFormat("0.0000");
   /* private fields */
   private String personName;
   private String dateBuried;
   private double personAge;
      
   /* a three-arg constructor  
    @param name, burialDate may have leading or trailing spaces
    It creates a valid Person object in which each field has the leading and trailing
    spaces eliminated*/
   public Person(String name, String burialDate, String age)
   {
   
      personName = name.trim();
      dateBuried = burialDate.trim();
      personAge = calculateAge(age);
   
   }
   /* any necessary accessor methods (at least "double getAge()" and "String getName()" )
   make sure your get and/or set methods use the same data type as the field  */
   
   public double getAge() {
      return personAge;
   }
   
   public String getName() {
      return personName;
   }
   
   public String getBurialDate() { 
      return dateBuried;
   }
   
   /*handles the inconsistencies regarding age
     @param a = a string containing an age from file. Ex: "12", "12w", "12d"
     returns the age transformed into year with 4 decimals rounding
   */
   public double calculateAge(String a)
   {
   
      double age;
      a = a.trim();
      String formattedAge = "";
   
      if(a.charAt(a.length() - 1) == 'w') {
         age = Double.parseDouble(a.substring(0, a.length() - 1)) / 52.14;
      }
      else if(a.charAt(a.length() - 1) == 'd') {
         age = Double.parseDouble(a.substring(0, a.length()-1)) / 365;
      }
      else{
         age = Double.parseDouble(a);
      }
      
      formattedAge = df.format(age);
   
      return Double.parseDouble(formattedAge);
   
   }
   
   public String toString() {
      return personName + ", " + dateBuried + ", " + personAge;
   }
   
}

/******************************************

 John William ALLARDYCE, 17 Mar 1844, 2.9
 Frederic Alex. ALLARDYCE, 21 Apr 1844, 0.17
 Philip AMIS, 03 Aug 1848, 1.0
 Thomas ANDERSON, 06 Jul 1845, 27.0
 Edward ANGEL, 20 Nov 1842, 22.0
 Lucy Ann COLEBACK, 23 Jul 1843, 0.2685
 Thomas William COLLEY, 08 Aug 1833, 0.011
 Joseph COLLIER, 03 Apr 1831, 58.0
 
 In the St. Mary Magdelene Old Fish Cemetery --> 
 Name of youngest person: Thomas William COLLEY
 Age of youngest person: 0.011
 Name of oldest person: Joseph COLLIER
 Age of oldest person: 58.0
 
 **************************************/