// Name: Saloni Shah 
// Date: 01/11/2021 (due date)

import java.util.*;

public class ParenMatch
{
   public static final String LEFT  = "([{<";
   public static final String RIGHT = ")]}>";
   
   public static void main(String[] args)
   {
      System.out.println("Parentheses Match");
      ArrayList<String> parenExp = new ArrayList<String>();
      /* enter test cases here */
      parenExp.add("5 + 7");
      parenExp.add("( 15 + -7 )");
      parenExp.add(") 5 + 7 (");
      parenExp.add("( ( 5.0 - 7.3 ) * 3.5 )");
      parenExp.add("< { 5 + 7 } * 3 >");
      parenExp.add("[ ( 5 + 7 ) * ] 3");
      parenExp.add("( 5 + 7 ) * 3");
      parenExp.add("5 + ( 7 * 3 )");
      parenExp.add("( ( 5 + 7 ) * 3");
      parenExp.add("[ ( 5 + 7 ] * 3 )");
      parenExp.add("[ ( 5 + 7 ) * 3 ] )");
      parenExp.add("( [ ( 5 + 7 ) * 3 ]");
      parenExp.add("( ( ( ) $ ) )");
      parenExp.add("( ) [ ]");
   
      for( String s : parenExp )
      {
         boolean good = checkParen(s);
         if(good)
            System.out.println(s + "\t good!");
         else
            System.out.println(s + "\t BAD");
      }
   }
     
   //returns the index of the left parentheses or -1 if is not
   public static int isLeftParen(String p)
   {
      return LEFT.indexOf(p);
   }
  
   //returns the index of the right parentheses or -1 if is not
   public static int isRightParen(String p)
   {
      return RIGHT.indexOf(p);
   }
   
   public static boolean checkParen(String exp)
   {
      //new stack of strings
      Stack<String> paren = new Stack<String>();
      
      for(int x = 0; x < exp.length(); x++) {
       
         //adds only if it is a parenthesis
         if(LEFT.contains("" + exp.charAt(x))) {
            paren.push("" + exp.charAt(x));
         }
         
         else if(RIGHT.contains("" + exp.charAt(x))) {
         //checks if the stack is empty
            if(paren.isEmpty()) {
               return false;
            }
         
         //the last left parenthesis added into the stack is going to match the one on the right
         //if it does not, returns false
            if(RIGHT.indexOf(exp.charAt(x)) != LEFT.indexOf(paren.pop())) {
               return false;
            }
         }
      }
      
      //returns true or false depending on whether the stack is empty
      //if the stack is not empty, that means that the number of parenthesis does not match
      return paren.isEmpty();
      
   }
}

/*****************************************

Parentheses Match
5 + 7		 good!
( 15 + -7 )		 good!
) 5 + 7 (		 BAD
( ( 5.0 - 7.3 ) * 3.5 )		 good!
< { 5 + 7 } * 3 >		 good!
[ ( 5 + 7 ) * ] 3		 good!
( 5 + 7 ) * 3		 good!
5 + ( 7 * 3 )		 good!
( ( 5 + 7 ) * 3		 BAD
[ ( 5 + 7 ] * 3 )		 BAD
[ ( 5 + 7 ) * 3 ] )		 BAD
( [ ( 5 + 7 ) * 3 ]		 BAD
( ( ( ) $ ) )		 good!
( ) [ ]		 good!
 
 *******************************************/
