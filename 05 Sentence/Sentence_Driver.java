// Name: Saloni Shah
// Date: 10/05/2020
  
public class Sentence_Driver
{
   public static void main(String[] args)
   {
      System.out.println("PALINDROME TESTER");
      Sentence s = new Sentence( "\"Hello there!\" she said." );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
      
      s = new Sentence( "A Santa lived as a devil at NASA." );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
     
   
      s = new Sentence( "Flo, gin is a sin! I golf." );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
      
   
      s = new Sentence( "Eva, can I stab bats in a cave?" );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
   
      s = new Sentence( "Madam, I'm Adam." );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
      
   // Lots more test cases.  Test every line of code.  Test
   // the extremes, test the boundaries.  How many test cases do you need?
   
   }
}

class Sentence
{
   private String mySentence;
   private int myNumWords;
   
   //Precondition:  str is not empty.
   //               Words in str separated by exactly one blank.
   public Sentence( String str )
   { 
      mySentence = str;
      
      int count = 0;
      
      for(int x = 0; x < str.length(); x++) {
         if(str.charAt(x) == ' ') {
            count++;
         }
      }
      
      myNumWords = count+1;
   }
   
   public int getNumWords()
   {  
      return myNumWords;  
   }
   
   public String getSentence()
   {
      return mySentence; 
   }
   
   //Returns true if mySentence is a palindrome, false otherwise.
   public boolean isPalindrome()
   {
   
      mySentence = removeBlanks(mySentence);
      mySentence = lowerCase(mySentence);
      mySentence = removePunctuation(mySentence);
      
      if(isPalindrome(mySentence, 0, mySentence.length()-1)) {
         return true;
      }
      else {
         return false;
      }
   }
   //Precondition: s has no blanks, no punctuation, and is in lower case.
   //Returns true if s is a palindrome, false otherwise.
   public static boolean isPalindrome( String s, int start, int end )
   {
      
      if(s.charAt(start) != s.charAt(end)) {
         return false;
      }
      else {
         s = s.substring(start + 1, end);
         isPalindrome (s, start, end);
      }
      
      return true;
   }
   //Returns copy of String s with all blanks removed.
   //Postcondition:  Returned string contains just one word.
   public static String removeBlanks( String s )
   {  
      String temp = new String(s);
      for(int x = 0; x < s.length(); x++) {
         if(temp.charAt(x) == ' ') {
            temp = temp.replace(temp.charAt(x), '\0');
         }
      }
      
      return temp;
   }
   
   //Returns copy of String s with all letters in lowercase.
   //Postcondition:  Number of words in returned string equals
   //						number of words in s.
   public static String lowerCase( String s )
   {  
      String temp = new String(s);
     
      temp = temp.toLowerCase();
     
      return temp;
   }
   
   //Returns copy of String s with all punctuation removed.
   //Postcondition:  Number of words in returned string equals
   //						number of words in s.
   public static String removePunctuation( String s )
   { 
      String punct = ".,'?!:;\"(){}[]<>"; 
      String temp = new String(s);
      
      for(int z = 0; z < temp.length(); z++) {
         for(int a = 0; a < punct.length(); a++) {
            if(temp.charAt(z) == punct.charAt(a)) {
               temp = temp.replace(temp.charAt(z), '\0');
            }
         }
      }
      
      return temp;
      
   }
}

 /*****************************************
   
 PALINDROME TESTER
 "Hello there!" she said.
 4
 false
 
 A Santa lived as a devil at NASA.
 8
 true
 
 Flo, gin is a sin! I golf.
 7
 true
 
 Eva, can I stab bats in a cave?
 8
 true
 
 Madam, I'm Adam.
 3
 true

 **********************************************/

