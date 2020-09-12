// Name:
// Date:
 
import java.text.DecimalFormat;
public class SmartCard_Driver
{
   public static void main(String[] args) 
   {
      Station downtown = new Station("Downtown", 1);
      Station center = new Station("Center City", 1);
      Station uptown = new Station("Uptown", 2);
      Station suburbia = new Station("Suburb", 4);
   
      SmartCard jimmy = new SmartCard(20.00); 
      jimmy.board(center);                    //Boarded at Center City.  SmartCard has $20.00
      jimmy.exit(suburbia);              //From Center City to Suburb costs $2.75.  SmartCard has $17.25
      jimmy.exit(uptown);				//Error:  did not board?!
      System.out.println();
   			
      SmartCard susie = new SmartCard(1.00); 
      susie.board(uptown);            		//Boarded at Uptown.  SmartCard has $1.00
      susie.exit(suburbia);				//Insuffient funds to exit. Please add more money.
      System.out.println();
   
      SmartCard kim = new SmartCard(.25);    
      kim.board(uptown);            		    //Insuffient funds to board. Please add more money.
      System.out.println();
   
      SmartCard b = new SmartCard(10.00);   
      b.board(center);            		    //Boarded at Center City.  SmartCard has $10.00
      b.exit(downtown);					//From Center City to Downtown costs $0.50.  SmartCard has $9.50
      System.out.println();
        
      SmartCard mc = new SmartCard(10.00);  
      mc.board(suburbia);            		    //Boarded at Suburbia.  SmartCard has $10.00
      mc.exit(downtown);					//From Suburbia to Downtown costs $2.75.  SmartCard has $7.25
      System.out.println();
    
      //Make more test cases.  What else needs to be tested?
   }
} 	

//Note SmartCard is not denoted as public.  Why?
class SmartCard 
{
   public final static DecimalFormat df = new DecimalFormat("$0.00");
   public final static double MIN_FARE = 0.5;
   /* enter your code below */
   
   private double balance;
   private int boardingZone;
   private boolean cardPresented;
   private boolean isBoarded = false;
   private String boarded;
   
   public SmartCard() {
   
      balance = 0;
      boardingZone = 0;
      cardPresented = false;
   
   }
   
   public SmartCard(double money) {
   
      balance += money;
      boardingZone = 0;
      cardPresented = false;
   
   }
   
   public SmartCard(double addMoney, int zone, boolean cardGiven) {
   
      balance = addMoney;
      boardingZone = zone;
      cardPresented = cardGiven;
   
   }
   
   public void addMoney(double moneyAdded) {
   
      balance += moneyAdded;
   
   }
   
   public String getBalance() {
   
      return df.format(balance);
   
   }
   
   public boolean isBoarded() {
   
      if(cardPresented) {
         isBoarded = true;
         return true;
      }
      return false;
   
   }
   
   public void board(Station s) {
   
      boarded = s.getName();
      boardingZone = s.getZone();
   
      if(isBoarded){
         System.out.println("Error: already boarded?!");
         return;
      }
      else { 
         isBoarded = true;
         if(balance < 0.50) {
            System.out.println("Insufficient funds to board. Please add more money.");
            return;
         }
         
         else {
            System.out.println("Boarded at " + boarded + ". SmartCard has " + df.format(balance));
         }
      }
   
   }
   
   public double cost(Station s) {
   
      double cost = 0.00;
      int endZone = s.getZone();
      int zonesCrossed;
      
      if(endZone == boardingZone) {
         cost = 0.50;
      }
      else if (endZone > boardingZone) {
         zonesCrossed = endZone - boardingZone;
         cost = 0.5 + (0.75 * zonesCrossed);
      }
      else{
         zonesCrossed = boardingZone - endZone;
         cost = 0.5 + (0.75 * zonesCrossed);
      }
      
      return cost;
   
   }
   
   public void exit(Station s) {
   
      double costCheck = cost(s);
      String exitStation = s.getName();
      int endZone = s.getZone();
   
      if(!isBoarded) {
         System.out.println("Error: Did not board?!");
         return;
      }
      else {
         isBoarded = false;
         if(costCheck > balance) {
            System.out.println("Insufficient funds to exit. Please add more money.");
            return; 
         }
         else {
            balance = balance - costCheck;
            System.out.println("From " + boarded + " to " + exitStation + " costs " + df.format(costCheck) + ". SmartCard has " + df.format(balance)); 
         }
      }
   }
   
   //the next 3 methods are for use ONLY by Grade-It
   //these accessor methods only return your private data
   //they do not make any changes to your data
   double getMoneyRemaining()
   {
    //enter your code here and replace the return with yours
      return -1;
   }
   
   Station getBoardedAt()
   {
    //enter your code here and replace the return with yours
      return null;   
   }
  
   boolean getIsOnBoard()
   {
   //enter your code here and replace the return with yours
      return false;
   }
}
   
//Note Station is not a public class.  Why?
class Station
{
   private String name;
   private int zone;
     
   public Station() {
     
      name = "";
      zone = 0;
     
   }
   
   public Station (String stationName, int zoneNumber) {
   
      name = stationName;
      zone = zoneNumber;
   
   }
   
   public void setName(String stationName) {
   
      name = stationName;
   
   }
   
   
   public void setZone(int zoneNumber) {
   
      zone = zoneNumber;
   
   }
   
   public String getName() {
   
      return name;
   
   }
   
   public int getZone() {
   
      return zone;
   
   }
    
    
   public String toString() {
   
      return "Station name: " + name + " Zone number: " + zone;
   
   } 
     
     
}

 /*******************  Sample Run ************

 Boarded at Center City.  SmartCard has $20.00
 From Center City to Suburb costs $2.75.  SmartCard has $17.25
 Error: did not board?!
 
 Boarded at Uptown.  SmartCard has $1.00
 Insufficient funds to exit. Please add more money.
 
 Insufficient funds to board. Please add more money.
 
 Boarded at Center City.  SmartCard has $10.00
 From Center City to Downtown costs $0.50.  SmartCard has $9.50
 
 Boarded at Suburb.  SmartCard has $10.00
 From Suburb to Downtown costs $2.75.  SmartCard has $7.25
 
 ************************************************/