// Name: Saloni Shah   
// Date: 09/21/2020 (due date)
import java.util.*;
import java.io.*;
public class PigLatin
{
   public static void main(String[] args) 
   {
      part_1_using_pig();
      // part_2_using_piglatenizeFile();
      
      /*  extension only    */
      // String pigLatin = pig("What!?");
      // System.out.print(pigLatin + "\t\t" + pigReverse(pigLatin));   //Yahwta!?
      // pigLatin = pig("{(Hello!)}");
      // System.out.print("\n" + pigLatin + "\t\t" + pigReverse(pigLatin)); //{(Yaholle!)}
      // pigLatin = pig("\"McDonald???\"");
      // System.out.println("\n" + pigLatin + "  " + pigReverse(pigLatin));//"YaDcmdlano???"
   }

   public static void part_1_using_pig()
   {
      Scanner sc = new Scanner(System.in);
      while(true)
      {
         System.out.print("\nWhat word? ");
         String s = sc.next();
         if(s.equals("-1"))
         {
            System.out.println("Goodbye!"); 
            System.exit(0);
         }
         String p = pig(s);
         System.out.println( p );
      }		
   }

   public static final String punct = ",./;:'\"?<>[]{}|`~!@#$%^&*()";
   public static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
   public static final String vowels = "AEIOUaeiou";
   public static String pig(String s)
   {
      if(s.length() == 0)
         return "";
         
      String ay = "ay";
      String way = "way";
      //store beg
      String beginningPunctuation = getBeginningPunctuation(s);
      //store end
      String endPunctuation = getEndPunctuation(s);
     
     //new string
      s = s.substring(beginningPunctuation.length(), s.lastIndexOf(endPunctuation));
      
      boolean isUpperCase = isCapitalLetter(s);
      char firstLetter = s.charAt(0);
      
      firstLetter = Character.toLowerCase(s.charAt(0));
      
      //check for capitalization
         
      //START HERE with the basic case:
      //     find the index of the first vowel
      //     y is a vowel if it is not the first letter
      //     qu
      
      String pigWord = "";
      int vowelIndex = getVowelIndex(s);
     
     //no vowel
      if(vowelIndex == -1) {
         s = "***NO VOWEL***";
      }
      //first letter vowel
      else if(vowelIndex == 0) {
         s = s + way;
      }
      else {
      //qu
         if(containsQU(s, vowelIndex)) {
            pigWord = firstLetter + s.substring(1, s.toLowerCase().indexOf("qu") + 2);
            s = s.substring(s.toLowerCase().indexOf("qu") + 2) + pigWord + ay;
         }
         //y
         if(s.contains("y")) {
            if(s.indexOf("y") == 0) {
               pigWord = firstLetter + s.substring(1, vowelIndex);
               s = s.substring(vowelIndex) + pigWord + ay;
            }
            if (s.indexOf("y") < vowelIndex) {
               pigWord = firstLetter + s.substring(1, s.indexOf("y"));
               s = s.substring(s.indexOf("y")) + pigWord + ay;
            }
         }
         //normal
         else {
            pigWord = firstLetter + s.substring(1, vowelIndex);
            s = s.substring(vowelIndex) + pigWord + ay;
         }
      }
      
      char newFirstLetter = s.charAt(0);
      
      if(isUpperCase) {
         newFirstLetter = Character.toUpperCase(s.charAt(0));
         s = newFirstLetter + s.substring(1);
      }
      
      return beginningPunctuation + s + endPunctuation;
   }
   
   public static String getBeginningPunctuation(String s) {
   	
      String begPunct = "";
   	
      for(int inputChar = 0; inputChar < s.length(); inputChar++) {
      	
         boolean foundPunct = false;
      	
         for(int punctChar = 0; punctChar < punct.length(); punctChar++) {
         	
            if(s.charAt(inputChar) == punct.charAt(punctChar)) {
               begPunct = begPunct + s.charAt(inputChar);
               foundPunct = true;
               break;
            }
         	
         }
      	
         if(!foundPunct) {
            break;
         }
      	
      }
   	
      return begPunct;
   	
   }
   
   public static String getEndPunctuation(String s) {
   	
      String endPunct = "";
   	
      for(int inputChar = s.length()-1; inputChar > 0; inputChar--) {
      	
         boolean foundPunct = false;
      	
         for(int punctChar = 0; punctChar < punct.length(); punctChar++) {
         	
            if(s.charAt(inputChar) == punct.charAt(punctChar)) {
               endPunct = s.charAt(inputChar) + endPunct;
               foundPunct = true;
               break;
            }
         	
         }
      	
         if(!foundPunct) {
            break;
         }
      	
      }
   	
      return endPunct;
   	
   }
   
   public static int getVowelIndex(String s) {
   
      int vowelIndex = -1;
   
      for (int a = 0; a < s.length(); a++) {
      
         for(int x = 0; x < vowels.length(); x++) {
            
            if(s.charAt(a) == (vowels.charAt(x))) {
               vowelIndex = s.indexOf(vowels.charAt(x));
               break;
            } 
         }
         if(vowelIndex != -1) {
            break;
         }
      }
      
      return vowelIndex;
      
   }
   
   public static boolean isCapitalLetter(String s) { 
   
      boolean isUpperCase = false;
      
      for(int inputChar = 0; inputChar < s.length(); inputChar++) {
      
         for(int upperChar = 0; upperChar < letters.length()/2; upperChar++) {
         
            if(s.charAt(inputChar) == letters.charAt(upperChar)) {
               isUpperCase = true;
               break;
            }
         
         }
         
         if(isUpperCase) {
            break;
         }
      
      }
      
      return isUpperCase;
   }  
   
   public static boolean containsQU(String s, int vowelIndex) {
   
      if(s.contains("qu") && s.indexOf("qu") < vowelIndex) {
         return true;
      }
      if(s.contains("Qu") && s.indexOf("Qu") < vowelIndex) {
         return true;
      }
      if(s.contains("QU") && s.indexOf("QU") < vowelIndex) {
         return true;
      }
      if(s.contains("qU") && s.indexOf("qU") < vowelIndex) {
         return true;
      }
      return false;
   }

   public static void part_2_using_piglatenizeFile() 
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("input filename including .txt: ");
      String fileNameIn = sc.next();
      System.out.print("output filename including .txt: ");
      String fileNameOut = sc.next();
      piglatenizeFile( fileNameIn, fileNameOut );
      System.out.println("Piglatin done!");
   }

/****************************** 
*  piglatinizes each word in each line of the input file
*    precondition:  both fileNames include .txt
*    postcondition:  output a piglatinized .txt file 
******************************/
   public static void piglatenizeFile(String fileNameIn, String fileNameOut) 
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File(fileNameIn));  
      }
      catch(IOException e)
      {
         System.out.println("oops");
         System.exit(0);   
      }
   
      PrintWriter outfile = null;
      try
      {
         outfile = new PrintWriter(new FileWriter(fileNameOut));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
   	//process each word in each line
      
      
      
   
      outfile.close();
      infile.close();
   }
   
   /** EXTENSION: Output each PigLatin word in reverse, preserving before-and-after 
       punctuation.  
   */
   public static String pigReverse(String s)
   {
      if(s.length() == 0)
         return "";
         
   
      return s;
         
   }
}
