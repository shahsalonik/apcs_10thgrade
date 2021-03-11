// Name: Saloni Shah
// Date: 03/15/2021 

import java.io.*;
import java.util.*;  

/* This program takes a text file, creates an index (by line numbers)
 * for all the words in the file and writes the index
 * into the output file.  The program prompts the user for the file names.
 */
public class IndexMakerMap
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nEnter input file name: ");
      String infileName = keyboard.nextLine().trim();
      Scanner inputFile = new Scanner(new File(infileName));
      
      DocumentIndex index = makeIndex(inputFile);
      
      //System.out.println( index.toString() );
      PrintWriter outputFile = new PrintWriter(new FileWriter("fishIndex.txt"));
      outputFile.println(index.toString());
      inputFile.close(); 						
      outputFile.close();
      System.out.println("Done.");
   }
   
   public static DocumentIndex makeIndex(Scanner inputFile)
   {
      DocumentIndex index = new DocumentIndex(); 	
      int lineNum = 0;
      while(inputFile.hasNextLine())
      {
         lineNum++;
         index.addAllWords(inputFile.nextLine(), lineNum);
      }
      return index;  
   }
}

class DocumentIndex extends TreeMap<String, TreeSet<Integer>>
{
  
  /** Extracts all the words from str, skipping punctuation and whitespace
   *  and for each word calls addWord(word, num).  A good way to skip punctuation
   *  and whitespace is to use String's split method, e.g., split("[., \"!?]") 
   */
   public void addAllWords(String str, int lineNum) 
   {
      str.trim();
      String[] wordsArray = str.split("[., \"!?]");
      
      //calls addWord for each of the words in the array
      if(wordsArray.length > 0) {
         for(int x = 0; x < wordsArray.length; x++) {
            if(wordsArray[x].length() > 0) {
               addWord(wordsArray[x].toUpperCase(), lineNum);
            }
         }
      }
   }

  /** Makes the word uppercase.  If the word is already in the map, updates the lineNum.
   *  Otherwise, adds word and ArrayList to the map, and updates the lineNum   
   */
   public void addWord(String word, int lineNum)
   {
      TreeSet<Integer> wordList = new TreeSet<Integer>();
      
      //checks if it's already in the list
      if(containsKey(word)) {
         //adds another value to the existing values
         get(word).add(lineNum);
      } 
      else {
         //puts the word into the list with the line numbers
         put(word, wordList);
         get(word).add(lineNum);
      }
      
   }
   
   public String toString()
   {
      //2 for-each loops; the first one puts everything into the arraylist,
      //the second one prints all of the values.
      String word = "";
      ArrayList<String> wordList = new ArrayList<String>();
      for(String entry : keySet()){
         String s = "" + get(entry);
         word = entry + " " + s.substring(1, s.length()-1);
         wordList.add(word);
      }      
      
      String finalWord = "";
      for(String str : wordList) {
         finalWord += str + "\n";
      }
      
      return finalWord;
   
   }
}

/**********************************************
     ----jGRASP exec: java -ea IndexMakerMap
 
 Enter input file name: fish.txt
 Done.
 
  ----jGRASP: operation complete.
  
************************************************/
/****************** fishIndex.txt  **************
A 12, 14, 15
ARE 16
BLACK 6
BLUE 4, 7
CAR 14
FISH 1, 2, 3, 4, 6, 7, 8, 9, 16
HAS 11, 14
LITTLE 12, 14
LOT 15
NEW 9
OF 16
OLD 8
ONE 1, 11, 14
RED 3
SAY 15
STAR 12
THERE 16
THIS 11, 14
TWO 2
WHAT 15   
   ************************/