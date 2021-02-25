// Name: Saloni Shah
// Date: 01/18/2021
//uses PostfixEval

import java.util.*;
public class InfixPostfixEval
{
   public static final String LEFT  = "([{<";
   public static final String RIGHT = ")]}>";
   public static final String operators = "+ - * / % ^ !";
   
   public static void main(String[] args)
   {
      System.out.println("Infix  \t-->\tPostfix\t\t-->\tEvaluate");
      /*build your list of Infix expressions here  */
      ArrayList<String> infixExp = new ArrayList<String>();
      infixExp.add("P + ( Q - R ) * A / B");
      infixExp.add("1 - ( 3 / 6 ) + 4");
      infixExp.add("8 + 1 * 2 - 9 / 3");
      infixExp.add("3 + 4 * 5");
      infixExp.add("3 * 4 + 5");
      infixExp.add("1.3 + 2.7 + -6 * 6");
      infixExp.add("( 33 + -43 ) * ( -55 + 65 )");
      infixExp.add("3 * 4 + 5 / 2 - 5");
      infixExp.add("8 + 1 * 2 - 9 / 3");
      infixExp.add("3 * ( 4 * 5 + 6 )");
      infixExp.add("3 + ( 4 - 5 - 6 * 2 )");
      infixExp.add("2 + 7 % 3");
      infixExp.add("( 2 + 7 ) % 3");
      
         
      for( String infix : infixExp )
      {
         String pf = infixToPostfix(infix);  //get the conversion to work first
         //System.out.println(infix + "\t\t\t" + pf );  
         System.out.println(infix + "\t\t\t" + pf + "\t\t\t" + PostfixEval.eval(pf));  //PostfixEval must work!
      }
   }
   
   public static String infixToPostfix(String infix)
   {
      List<String> nums = new ArrayList<String>(Arrays.asList(infix.split(" ")));
            /* enter your code here  */
      Stack<Character> opStack = new Stack<Character>();
      String exp = "";
      for( String s : nums ) {
      
      //checks if the string is an operator. if yes,...
         if(!isOperator(s) && !s.equals("(") && !s.equals(")")) {
         //...inputs it into the final string. if no...
            exp = exp + s + " ";
         }
         //...checks if it is an open parenthesis. if yes,...
         else if(s.equals("(")) {
         //...pushes it into the operator stack. if no...
            opStack.push(s.charAt(0));
         }
         //...checks if it is a closed parenthesis. if yes...
         else if(s.equals(")")) {
         //...until the stack is not empty, and until the only character remaining is an open parenthesis,
            while(!opStack.isEmpty()  && opStack.peek() != '(') {
            //pop and add to the final expression
                  exp = exp + opStack.pop() + " ";
            }
            //pop the open parenthesis to ensure that it won't be a part of the final string
            opStack.pop();
            
         }
         //if none of the above,
         else {
            //pop and add to the final expression until the stack is not empty, 
            //and until the operations are of lower precedence than the one before
            while(!opStack.isEmpty() && isLower(s.charAt(0), opStack.peek())) {
               exp = exp + opStack.pop() + " ";
            }
            //push into the empty stack
            opStack.push(s.charAt(0));
            
         }
      }
      
      //pop all remaining operators
      while(!opStack.isEmpty()) {
         exp = exp + opStack.pop() + " ";
      }
      
      //return the final expression
      return exp;
      
   }
   
   //returns true if c1 has strictly lower precedence than c2
   public static boolean isLower(char c1, char c2)
   {
   //follows PEMDAS to determine precedence
      if((c1 == '+' || c1 == '-') && (c2 == '*' || c2 == '/'))
      {
         return true;
      }
      else if((c1 == '+' && c2 == '-') || (c1 == '-' && c2 == '+')) {
         return true;
      }
      else if (c1 == c2) {
         return true;
      }
      else {
         return false;
      }
      
   }
   
   public static boolean isOperator(String op)
   {
      return operators.contains(op);
   }
     
}


/********************************************

 Infix  	-->	Postfix		-->	Evaluate
 3 + 4 * 5			3 4 5 * + 			23.0
 3 * 4 + 5			3 4 5 + * 			27.0
 1.3 + 2.7 + -6 * 6			1.3 2.7 + -6 6 * + 			-32.0
 ( 33 + -43 ) * ( -55 + 65 )			33 -43 + -55 65 + * 			-100.0
 3 * 4 + 5 / 2 - 5			3 4 5 2 5 - / + * 			6.999999999999999
 8 + 1 * 2 - 9 / 3			8 1 2 9 3 / - * + 			7.0
 3 * ( 4 * 5 + 6 )			3 4 5 6 + * * 			132.0
 3 + ( 4 - 5 - 6 * 2 )			3 4 5 - 6 2 * - + 			-10.0
 2 + 7 % 3			2 7 3 % + 			3.0
 ( 2 + 7 ) % 3			2 7 + 3 % 			0.0
     
***********************************************/
