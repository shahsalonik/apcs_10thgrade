// Name: Saloni Shah
// Date: 01/11/2021 (due date)

import java.util.*;

public class PostfixEval
{
   public static final String operators = "+ - * / % ^ !";
   
   public static void main(String[] args)
   {
      System.out.println("Postfix  -->  Evaluate");
      ArrayList<String> postfixExp = new ArrayList<String>();
      /*  build your list of expressions here  */
      postfixExp.add("3 4 + 8 * 5 2 ^ -");
      postfixExp.add("3 4 5 * +");
      postfixExp.add("3 4 * 5 +");
      postfixExp.add("10 20 + -6 6 * +");
      postfixExp.add("3 4 + 5 6 + *");
      postfixExp.add("3 4 5 + * 2 - 5 /");
      postfixExp.add("8 1 2 * + 9 3 / -");
      postfixExp.add("2 3 ^");
      postfixExp.add("20 3 %");
      postfixExp.add("21 3 %");
      postfixExp.add("22 3 %");
      postfixExp.add("23 3 %");
      postfixExp.add("5 !");
      postfixExp.add("1 1 1 1 1 + + + + !");
      postfixExp.add("3 2 ^");
      postfixExp.add("2 3 + ! 2 ^");
   
      
      for( String pf : postfixExp )
      {
         System.out.println(pf + "\t\t" + eval(pf));
      }
   }
   
   public static double eval(String pf)
   {
      if(pf.equals("ERROR")) {
         return -1.0;
      }
      List<String> postfixParts = new ArrayList<String>(Arrays.asList(pf.split(" ")));
      /*  enter your code here  */
      Stack<Double> equation = new Stack<Double>();
      
      //for each string in postfixParts,
      //checks if it is a number
      //if yes, converts it to a double and pushes it into the equation stack
      //if no, performs operations based on the previous number and with the operator that comes after it
      for( String s : postfixParts ) {
         if(!isOperator(s)) {
            equation.push(Double.parseDouble(s));
         }
         else {
         //if the size of the stack is 1 (i.e. only a single number with 1 operation)
         //uses 1 as the second number
            if(equation.size() == 1) {
               equation.push(eval(1, equation.pop(), s));
            }
            else {
               equation.push(eval(equation.pop(), equation.pop(), s));
            }
         }
      }
      
      return equation.pop();
      
   }
   
   public static double eval(double a, double b, String ch)
   {
      //checks for all of the operations
      if(ch.equals("+")) {
         return b + a;
      }
      else if(ch.equals("-")) {
         return b - a;
      }
      else if(ch.equals("*")) {
         return b * a;
      }
      else if(ch.equals("/")) {
         return b / a;
      }
      else if(ch.equals("%")) {
         return b % a;
      }
      else if(ch.equals("^")) {
         double num = b;
         
         for(int x = 1; x < a; x++) {
            num *= b;
         }
         
         return num;
      }
      else if(ch.equals("!")) {
         double num = 1;
         
         for(int x = 1; x <= b; x++) {
            num *= x;
         }
         
         return num;  
      }
      else {
         return 0;
      }
      
   }
   
   public static boolean isOperator(String op)
   {
      return operators.contains(op);
   }
}

/**********************************************
Postfix  -->  Evaluate
 3 4 5 * +		23
 3 4 * 5 +		17
 10 20 + -6 6 * +		-6
 3 4 + 5 6 + *		77
 3 4 5 + * 2 - 5 /		5
 8 1 2 * + 9 3 / -		7
 2 3 ^		8
 20 3 %		2
 21 3 %		0
 22 3 %		1
 23 3 %		2
 5 !		120
 1 1 1 1 1 + + + + !		120
 
 
 *************************************/