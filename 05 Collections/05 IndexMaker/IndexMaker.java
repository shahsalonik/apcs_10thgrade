// Name: Saloni Shah
// Date: 12/18/2020 (due date)
// This program takes a text file, creates an index (by line numbers)
// for all the words in the file and writes the index
// into the output file.  The program prompts the user for the file names.

import java.util.*;
import java.io.*;

public class IndexMaker
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nEnter input file name: ");
      String inFileName = keyboard.nextLine().trim();
      Scanner inputFile = new Scanner(new File(inFileName));
      String outFileName = "fishIndex.txt";
      PrintWriter outputFile = new PrintWriter(new FileWriter(outFileName));
      indexDocument(inputFile, outputFile);
      inputFile.close(); 						
      outputFile.close();
      System.out.println("Done.");
   }
   public static void indexDocument(Scanner inputFile, PrintWriter outputFile)
   {
      DocumentIndex index = new DocumentIndex();
      String line = null;
      int lineNum = 0;
      while(inputFile.hasNextLine())
      {
         lineNum++;
         index.addAllWords(inputFile.nextLine(), lineNum);
      }
      for(IndexEntry entry : index)
         outputFile.println(entry);
   }   
}
class DocumentIndex extends ArrayList<IndexEntry>
{
    //constructors
    //default
   public DocumentIndex() {
      super();
   }
   
   //passed size
   public DocumentIndex(int size) {
      super(size);
   }
   
  /** extracts all the words from str, skipping punctuation and whitespace 
 and for each word calls addWord().  In this situation, a good way to 
 extract while also skipping punctuation is to use String's split method, 
 e.g., str.split("[., \"!?]")       */
   public void addAllWords(String str, int lineNum) 
   {
      //makes an array with the words, splitting based on punctuation
      String[] wordsArray = str.split("[., \"!?]");
      
      //calls addWord for each of the words in the array
      if(wordsArray.length > 0) {
         for(int x = 0; x < wordsArray.length; x++) {
            if(wordsArray[x].length() > 0) {
               addWord(wordsArray[x], lineNum);
            }
         }
      }
   }
    
   /** calls foundOrInserted, which returns a position.  At that position,  
   updates that IndexEntry's list of line numbers with lineNum. */
   public void addWord(String word, int lineNum)
   {
      //adds the line numbers next to the word
      int pos = foundOrInserted(word);
      IndexEntry entry = get(pos);
      entry.add(lineNum);
   }
        
    /** traverses this DocumentIndex and compares word to the words in the 
    IndexEntry objects in this list, looking for the correct position of 
    word. If an IndexEntry with word is not already in that position, the 
    method creates and inserts a new IndexEntry at that position. The 
    method returns the position of either the found or the inserted 
    IndexEntry.*/
   private int foundOrInserted(String word)
   {
   
      IndexEntry newEntry = new IndexEntry(word);
      
      //a loop that checks where the word should go alphabetically.
      //does not add repeats
      for(int pos = 0; pos < size(); pos++) {
         if(get(pos).getWord().compareTo(word.toUpperCase()) == 0) {
            return pos;
         }
         else if(get(pos).getWord().compareTo(word.toUpperCase()) > 0) {
            add(pos, newEntry);
            return pos; 
         }
         else {
            continue;
         }
      }
      
      //if none of the requirements are fulfilled, adds at the end
      add(newEntry);
      return size() - 1;
      
   }
}
   
class IndexEntry implements Comparable<IndexEntry>
{
     //fields
     
   private String word;
   private ArrayList<Integer> numsList;
     
     //constructors
     //converts the word to uppercase
   public IndexEntry(String inputStr) {
      word = inputStr.toUpperCase();
      numsList = new ArrayList<Integer>();
   }
   
   
     /**  appends num to numsList, but only if it is not already in that list.    
          */
   public void add(int num)
   {
   //adds only if the num isn't already there
      if(!numsList.contains(num)) {
         numsList.add(num);
      }
   }
      
   	/** this is a standard accessor method  */
   public String getWord()
   {
      return word;
   }
      
     /**  returns a string representation of this Index Entry.  */
   public String toString()
   {
   
   //makes a string of all the words with the line numbers where they occur
      String str = word + " ";
      
      for(Integer n : numsList) {
         str += n + ", ";
      }
      str = str.substring(0, str.length()-2);
      
      //returns a string without the last comma
      return str;
      
   }
   
   //compareTo method
   public int compareTo(IndexEntry ie) {
      return word.compareTo(ie.getWord());
   }
   
}

