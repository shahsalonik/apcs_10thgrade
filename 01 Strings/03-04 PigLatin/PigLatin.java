// Name: Saloni Shah   
// Date: 09/21/2020 (due date)
import java.util.*;
import java.io.*;
public class PigLatin
{
   public static void main(String[] args) 
   {
      //part_1_using_pig();
      part_2_using_piglatenizeFile();
      
      /* extension only    */
      String pigLatin = pig("What!?");
      System.out.print(pigLatin + "\t\t" + pigReverse(pigLatin));   //Yahwta!?
      pigLatin = pig("{(Hello!)}");
      System.out.print("\n" + pigLatin + "\t\t" + pigReverse(pigLatin)); //{(Yaholle!)}
      pigLatin = pig("\"McDonald???\"");
      System.out.println("\n" + pigLatin + "  " + pigReverse(pigLatin));//"YaDcmdlano???"
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
      //store the punctuation at the beginning of the string
      String beginningPunctuation = getBeginningPunctuation(s);
      //store the punctuation at the end of the string
      String endPunctuation = getEndPunctuation(s);
     
     //the new string without any punctuation
      s = s.substring(beginningPunctuation.length(), s.lastIndexOf(endPunctuation));
      
      //stores whether or not the string starts with a capital letter.
      boolean isUpperCase = isCapitalLetter(s);
      char firstLetter = s.charAt(0);
      
      //changes the first letter of the string to lower case 
      firstLetter = Character.toLowerCase(s.charAt(0));
      
      //stores the suffix (later)
      String pigWord = "";
      //stores the index of the first occurring vowel
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
       
        
         if(s.contains("y")) {
          
          //if the string contains y and if y is at 0, print normally
            if(s.indexOf("y") == 0) {
               pigWord = firstLetter + s.substring(1, vowelIndex);
               s = s.substring(vowelIndex) + pigWord + ay;
            }
            //if the string contains y and if y is not 0 and if the index of y is less 
            //than the vowel index, consider y to be the first vowel.
            else if (s.indexOf("y") != 0 && s.indexOf("y") < vowelIndex) {
               pigWord = firstLetter + s.substring(1, s.indexOf("y"));
               s = s.substring(s.indexOf("y")) + pigWord + ay;
            }
            //print normally in all other cases
            else {
               pigWord = firstLetter + s.substring(1, vowelIndex);
               s = s.substring(vowelIndex) + pigWord + ay;
            }
            
         }
         
         //if the string contains "qu", move it and anything that comes before and after
         //it (until the first vowel) to the end (as a suffix)
         else if(containsQU(s, vowelIndex)) {
            pigWord = firstLetter + s.substring(1, s.toLowerCase().indexOf("qu") + 2);
            s = s.substring(s.toLowerCase().indexOf("qu") + 2) + pigWord + ay;
         }
         
         //print normally in all other cases
         else {
            pigWord = firstLetter + s.substring(1, vowelIndex);
            s = s.substring(vowelIndex) + pigWord + ay;
         }
      }
      
      //the new first letter of the string after the cases have been run through
      char newFirstLetter = s.charAt(0);
      
      //if the first letter is upper case, endure that the first letter of the new string is
      //also upper case.
      if(isUpperCase) {
         newFirstLetter = Character.toUpperCase(s.charAt(0));
         s = newFirstLetter + s.substring(1);
      }
      
      //returns the stored beginning punctuation, the string, and the end punctuation as one.
      return beginningPunctuation + s + endPunctuation;
   }
   
   /*
    * Returns the punctuation at the beginning of the string that is inputed by the user.
    * Goes through two for-loops. The outer one checks the string and the inner one checks
    * the punctuation. If punctuation is found, then the boolean foundPunct is true and
    * breaks out of the loop.
    * 
    * @param s the input string of the method
    * @return the beginning punctuation of the string
    */
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
   
   /*
    * Returns ending punctuation. Uses two for-loops (one going through the string and one
    * going through punctuation). Starts at the end of the string and goes backward. The
    * boolean foundPunct is true if punctuation is found and breaks out of the loop.
    * 
    * @param s the string inputed by the user.
    * @return the end punctuation of the string.
    */
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
   
   /*
    * Returns the first occurring case of a vowel in the string. Uses two for-loops to
    * look for it (the outer one going through the string and the inner one going through 
    * the vowel string).
    * 
    * @param s the string that is inputed by the user.
    * @return an int that shows the vowel index.
    */
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
   
   /*
    * Checks whether the first letter in the string is capital. If yes, breaks out of the 
    * loops. The outer loops checks the string's characters and the inner loops looks at
    * the capital letters. 
    * 
    * @param s the string that is inputed by the user.
    * @return a boolean that tells the user whether the first letter is capital.
    */
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
   
   /*
    * Checks whether the string contains "qu" and its variations. Returns true if yes, false
    * if no.
    * 
    * @param s the string that is inputed by the user.
    * @param vowelIndex the index of the first occurring vowel in the string.
    * @return true if the string contains "qu"; false if not.
    */
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
   
   public static String reverseString(String s) {
      
      //puts the string's characters into an array
      char[] originalArray = s.toCharArray();
      
      //new string temp: for reversal purposes
      String temp = "";
      
      //reverses the string
      for(int i = 0; i < originalArray.length; i++) {
         temp = temp + originalArray[originalArray.length - i - 1]; 
      }
      
      return temp; 
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
      
      String piggedString = "";
      
      //2 while loops (one for lines, other for words in the lines)
      while(infile.hasNextLine()) {
      
      //used to scan in the lines
         String line = infile.nextLine();
         Scanner lineScanner = new Scanner(line);
         
         //while the line has a next word
         while(lineScanner.hasNext()) {
         
         //go to that word
            piggedString = lineScanner.next();
            //print the translated bit
            outfile.print(pig(piggedString) + " ");
         
         }
         //go to the next line
         outfile.println();
      }
   
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
         
      //converts the string to piglatin    
      pig(s);
      
      //store the punctuation at the beginning of the string
      String beginningPunctuation = getBeginningPunctuation(s);
      //store the punctuation at the end of the string
      String endPunctuation = getEndPunctuation(s);
     
     //the new string without any punctuation
      s = s.substring(beginningPunctuation.length(), s.lastIndexOf(endPunctuation));
      
      //stores whether or not the string starts with a capital letter.
      boolean isUpperCase = isCapitalLetter(s);
      char firstLetter = isUpperCase? Character.toLowerCase(s.charAt(0)) : s.charAt(0);
      
      //concatinates the new lowercase letter to the rest of the piglatenized string
      s = firstLetter + s.substring(1);
      
      //reverseString method
      String temp = reverseString(s);
      
      //checks if the first (initial) letter was uppercase; 
      //if yes, adds on the uppercase first letter to the reversed string
      //if no, returns the reversed string
      if(isUpperCase) {
      
      //uppercase first letter to add to the beginning of the string
         String newFirstLetter = temp.substring(0,1).toUpperCase();
         s = newFirstLetter + temp.substring(1);
      }
      else {
         s = temp;
      }
   
      //adds the beginning and end punctuation to the string and returns that value
      return beginningPunctuation + s + endPunctuation;
         
   }
}
