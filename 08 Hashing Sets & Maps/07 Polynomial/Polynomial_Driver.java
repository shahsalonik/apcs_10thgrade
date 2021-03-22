 // Name: Saloni Shah  
 // Date: 03/22/2021 (due date)

import java.util.*;

public class Polynomial_Driver
{
   public static void main(String[] args)
   {
      Polynomial poly = new Polynomial();    // 2x^3 + -4x + 2
      poly.makeTerm(1, -4);
      poly.makeTerm(3, 2);
      poly.makeTerm(0, 2);
      System.out.println("Map:  " + poly.getMap());
      System.out.println("String:  " + poly.toString());
      double evaluateAt = 2.0;
      System.out.println("Evaluated at "+ evaluateAt +": " +poly.evaluateAt(evaluateAt));
      
      System.out.println("-----------");
      Polynomial poly2 = new Polynomial();  // 2x^4 + x^2 + -5x + -3
      poly2.makeTerm(1, -5);
      poly2.makeTerm(4, 2);
      poly2.makeTerm(0, -3);
      poly2.makeTerm(2, 1);
      System.out.println("Map:  " + poly2.getMap()); 
      System.out.println("String:  " +poly2.toString());
      evaluateAt = -10.5;
      System.out.println("Evaluated at "+ evaluateAt +": " +poly2.evaluateAt(evaluateAt));
      
      
      System.out.println("-----------");
      System.out.println("Sum: " + poly.add(poly2));
      System.out.println("Product:  " + poly.multiply(poly2));
      
      /*  Another case:   (x+1)(x-1) -->  x^2 + -1    */
      System.out.println("===========================");
      Polynomial poly3 = new Polynomial();   // (x+1)
      poly3.makeTerm(1, 1);
      poly3.makeTerm(0, 1);
      System.out.println("Map:  " + poly3.getMap());
      System.out.println("String:  " + poly3.toString());
   //       
      Polynomial poly4 = new Polynomial();    // (x-1)
      poly4.makeTerm(1, 1);
      poly4.makeTerm(0, -1);
      System.out.println("Map:  " + poly4.getMap());
      System.out.println("String:  " + poly4.toString());
      System.out.println("Product:  " + poly4.multiply(poly3));   // x^2 + -1 
   //    
   //    /*  testing the one-arg constructor  */
      System.out.println("==========================="); 
      Polynomial poly5 = new Polynomial("2x^3 + 4x^2 + 6x^1 + -3");
      System.out.println("Map:  " + poly5.getMap());  
      System.out.println(poly5);
   
   }
}
interface PolynomialInterface
{
   public void makeTerm(Integer exp, Integer coef);
   public Map<Integer, Integer> getMap();
   public double evaluateAt(double x);
   public Polynomial add(Polynomial other);
   public Polynomial multiply(Polynomial other);
   public String toString();
}

class Polynomial implements PolynomialInterface
{

   private Map<Integer, Integer> polMap;
   
   public Polynomial() {
      polMap = new TreeMap<Integer, Integer>();
   }
   
   /** Converts all the string numbers to integers in order to put it into the map.
   *This allows for an easier conversion to a Polynomial.
   *@param input the initial string of a Polynomial
   */
   public Polynomial(String input) {
      polMap = new TreeMap<Integer, Integer>();
      
      int count = 0;
      
      for(int x = 0; x < input.length(); x++) {
         if(input.charAt(x) == '+') {
            count++;
         }
      }
      
      count += 1;
      
      int[] inputArray = new int[count*2];
      
      for(int y = 0; y < inputArray.length; y += 2) {
         if(input.contains("x") && input.contains("^")) {
            inputArray[y] = Integer.parseInt(input.substring(0, input.indexOf("x")));
            inputArray[y + 1] = Integer.parseInt(input.substring(input.indexOf("^") + 1, input.indexOf(" ")));
            input = input.substring(input.indexOf(" ") + 3);
         }
         else if (input.contains("x")) {
            inputArray[y] = Integer.parseInt(input.substring(0, input.indexOf("x")));
            inputArray[y + 1] = Integer.parseInt(input.substring(input.indexOf("x") + 1, input.indexOf(" ")));
            input = input.substring(input.indexOf(" ") + 3);
         }
         else {
            inputArray[y] = Integer.parseInt(input);
            break;
         }
      }
      
      for(int z = 0; z < inputArray.length; z += 2) {
         polMap.put(inputArray[z + 1], inputArray[z]);
      }
      
   }
   
   /**
    * Makes the polynomial term by checking if the exp is already there.
    * If it is, the coefficient is added to the one that is in the map.
    * Then, puts the exponent and the modified coefficient into the map.
    */
   public void makeTerm(Integer exp, Integer coef) {
      int expCheck = 0;
      if(polMap.containsKey(exp)) {
         expCheck = polMap.get(exp);
      }
      expCheck += coef;
      polMap.put(exp, expCheck);
   }
   
   /**
    * Returns the map
    * @return the polynomial map
    */
   public Map<Integer, Integer> getMap() {
      return polMap;
   }
   
   /**
    * Evaluates a polynomial using substitution
    * @param x the double that will replace all the 'x's in the polynomials
    * @return the final value
    */
   public double evaluateAt(double x) {
      double result = 0.0;
      
      for(int pow : polMap.keySet()) {
         result += (polMap.get(pow) * Math.pow(x, pow));
      }
      
      return result;
   }
   
   /**
    * Adds two polynomials together.
    * @param other the other operand
    * @return the polynomial result
    */
   public Polynomial add(Polynomial other) {
      
      Polynomial result = new Polynomial();
      
      for(int otherPow : other.getMap().keySet()) {
         result.makeTerm(otherPow, other.getMap().get(otherPow));
      }
      
      for(int actPow : polMap.keySet()) {
         result.makeTerm(actPow, polMap.get(actPow));
      }
      
      return result;
      
   }
   
   /**
    * Multiplies two polynomials with each other
    * @param the multiplier
    * @return the product of the two polynomials
    */
   public Polynomial multiply(Polynomial other) {
      Polynomial result = new Polynomial();
      
      for(int otherPow : other.getMap().keySet()) {
         for(int actPow : polMap.keySet()) {
            result.makeTerm((otherPow + actPow), (polMap.get(actPow) * other.getMap().get(otherPow)));
         } 
      }
      
      return result;
   }
   
   /**
    * Converts the polynomial in the map to a String. Returns the String.
    * @return the polynomial as a String
    */
   public String toString() {
      String finalExp = "";
      for(int pow : polMap.keySet()) { 
         if(polMap.get(pow) == 0) {
            finalExp += "";
         }
         else {
            if(pow == 0) {
               finalExp = finalExp + "" + polMap.get(pow);
            }
            else if(pow == 1) {
               if(polMap.get(pow) == 1) {
                  finalExp = "x + " + finalExp;
               }
               else {
                  finalExp = polMap.get(pow) + "x + " + finalExp;
               }
            }
            else {
               if(polMap.get(pow) == 1) {
                  finalExp = "x^" + pow + " + " + finalExp;
               }
               else {
                  finalExp = polMap.get(pow) + "x^" + pow + " + " + finalExp;
               }
            }
         }
      }
      return finalExp;
   }
}


/***************************************  
  ----jGRASP exec: java Polynomial_teacher
 Map:  {0=2, 1=-4, 3=2}
 String:  2x^3 + -4x + 2
 Evaluated at 2.0: 10.0
 -----------
 Map:  {0=-3, 1=-5, 2=1, 4=2}
 String:  2x^4 + x^2 + -5x + -3
 Evaluated at -10.5: 24469.875
 -----------
 Sum: 2x^4 + 2x^3 + x^2 + -9x + -1
 Product:  4x^7 + -6x^5 + -6x^4 + -10x^3 + 22x^2 + 2x + -6
 
  ----jGRASP: operation complete.
 ********************************************/