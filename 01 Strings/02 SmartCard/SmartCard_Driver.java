//Name: Saloni Shah
//Date: 09/14/2020 (due date)

import java.text.DecimalFormat;

public class SmartCard_Driver {
	
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
      SmartCard d = new SmartCard(-2.00);
      d.board(center);
      System.out.println();
         
      SmartCard c = new SmartCard(100.00);
      c.board(center);
      c.board(suburbia);
      c.exit(downtown);
      System.out.println();
         
      SmartCard f = new SmartCard(100.00);
      f.exit(downtown);
      System.out.println();
         
   }
} 	

	//Note SmartCard is not denoted as public.  Why?
	/**
	 * SmartCard represents the card that passengers can use to travel between stations.
	 */
class SmartCard 
{
   public final static DecimalFormat df = new DecimalFormat("$0.00");
   public final static double MIN_FARE = 0.5;
	   /* enter your code below */
	   
   private double balance;
	   //private int boardingZone;
   private boolean isBoarded;
	   //private String boarded;
   private Station boardingStation;
	   
	   /**
	    * Creates an instance of SmartCard with the values of balance and boardingZone to 0
	    * and cardPresented to false
	    */
   public SmartCard() {
      
      balance = 0;
      isBoarded = false;
      
   }
	   
	   /**
	    * Creates an instance of SmartCard with money specified.
	    * 
	    * @param money the amount of money that is in the SmartCard.
	    */
   public SmartCard(double money) {
      
      balance = money;
      isBoarded = false;
      
   }
	   
	   /**
	    * Creates an instance of SmartCard with the values of balance, zone,
	    * and cardPresented set by the user.
	    * 
	    * @param addMoney the balance in the card.
	    * @param cardGiven whether or not the card was given.
	    */
   public SmartCard(double addMoney, boolean cardGiven) {
      
      balance = addMoney;
      isBoarded = cardGiven;
      
   }
	   
	   /**
	    * Allows the user to add money to the SmartCard.
	    * 
	    * @param moneyAdded the amount that is added to the existing balance.
	    */
   public void addMoney(double moneyAdded) {
      
      balance += moneyAdded;
      
   }
	   
	   /**
	    * Returns balance formatted as "$0.00"
	    * 
	    * @return returns the balance in the decimal format specified earlier ("$0.00")
	    */
   public String getBalance() {
      
      return df.format(balance);
      
   }
	   
	   /**
	    * Checks whether or not the passenger has boarded.
	    * 
	    * @return true if isBoarded is true; false otherwise.
	    */
   public boolean isBoarded() {
      
      return isBoarded;
   	   
   }
	   

	   /**
	    * Checks whether or not the person is able to board depending on funds and 
	    * if they have already boarded (based on isBoarded). Prints where they boarded
	    * and the balance in their SmartCard if they pass all the checks.
	    * 
	    * @param s the station name where the passenger boards.
	    */
   public void board(Station s) {
      
      boardingStation = s;
      
      if(isBoarded){
         System.out.println("Error: already boarded?!");
         return;
      }
      else { 
         if(balance < MIN_FARE) {
            System.out.println("Insufficient funds to board. Please add more money.");
            return;
         }
         else {
            isBoarded = true;
            System.out.println("Boarded at " + boardingStation.getName() + ". SmartCard has " + df.format(balance));
         }
      }
      
   }
	   
	   /**
	    * Returns the cost of traveling
	    * 
	    * @param s the place where the person is traveling.
	    * @return the cost of traveling to their desired destination.
	    */
   public double cost(Station s) {
      
      double cost = 0.00;
      int boardingZone = boardingStation.getZone();
      int endZone = s.getZone();
      int zonesCrossed;
         
         /*
          * 50 cents in the same zone. If it is out of the zone, it is 50 cents plus
          * 75 cents for every zone crossed.
          */
      if(endZone == boardingZone) {
         cost = MIN_FARE; 
      }
      else if (endZone > boardingZone) {
         zonesCrossed = endZone - boardingZone;
         cost = MIN_FARE + (0.75 * zonesCrossed);
      }
      else{
         zonesCrossed = boardingZone - endZone;
         cost = MIN_FARE + (0.75 * zonesCrossed);
      }
         
      return cost;
      
   }
	   
	   /**
	    * Checks whether or not the person can exit by checking their funds and whether they
	    * have already boarded (based on isBoarded). Prints where they came from, where
	    * they exited, the cost of traveling to that place, and the balance left on their
	    * SmartCard when they exit.
	    * 
	    * @param s the station where the passenger exits.
	    */
   public void exit(Station s) {
      	   
      if(!isBoarded) {
         System.out.println("Error: Did not board?!");
         return;
      }
      else {
         
         double costCheck = cost(s);
         String exitStation = s.getName();
         String boardStation = boardingStation.getName();
      
         isBoarded = false;
         if(costCheck > balance) {
            System.out.println("Insufficient funds to exit. Please add more money.");
            return; 
         }
         else {
            balance = balance - costCheck;
            System.out.println("From " + boardStation + " to " + exitStation + " costs " + df.format(costCheck) + ". SmartCard has " + df.format(balance)); 
         }
      }
   }
	   
	   //the next 3 methods are for use ONLY by Grade-It
	   //these accessor methods only return your private data
	   //they do not make any changes to your data
	   
	   /**
	    * Returns the money remaining on the SmartCard.
	    * @return balance
	    */
   double getMoneyRemaining()
   {
      return balance;
   }
	   
	   /**
	    * Returns the station and zone where the passenger boarded.
	    * 
	    * @return the station and zone where the passenger boarded.
	    */
   Station getBoardedAt()
   {
      return boardingStation;
   }
	  
	   /**
	    * Returns whether or not the person has boarded the subway.
	    * 
	    * @return true if boarded; false otherwise.
	    */
   boolean getIsOnBoard()
   {
      return isBoarded;
   }
}
	   
	//Note Station is not a public class.  Why?
	/**
	 * Station represents the stations that a passenger can travel to using their SmartCard.
	 *
	 */
class Station
{
   private String name;
   private int zone;
	     
	   /**
	    * Creates an instance of Station with name as "Downtown" and zone as 1.
	    */
   public Station() {
        
      name = "Downtown";
      zone = 1;
        
   }
	   	   
	   /**
	    * Creates an instance of Station.
	    * 
	    * @param stationName Name of the station.
	    * @param zoneNumber Zone number of the station; values must be between 1-5 inclusive.
	    */
   public Station (String stationName, int zoneNumber) {
      
      name = stationName;
      zone = zoneNumber;
      
   }
	   
	   /**
	    * Sets the name of the station based on the passed station name.
	    * 
	    * @param stationName The value to assign to a station name.
	    */
   public void setName(String stationName) {
      
      name = stationName;
      
   }
	   
	   /**
	    * Sets the zone number of the station based on the passed zone number.
	    * 
	    * @param zoneNumber The value to assign to the zone.
	    */
   public void setZone(int zoneNumber) {
      
      zone = zoneNumber;
      
   }
	   
	   /**
	    * Returns the name of the station.
	    * 
	    * @return the station name
	    */
   public String getName() {
      
      return name;
      
   }
	   
	   /**
	    * Returns the zone of the station.
	    * 
	    * @return the zone number
	    */
   public int getZone() {
      
      return zone;
      
   }
	    
	   /**
	    * Returns a string representation of the station name and zone number.
	    * 
	    * @return the string representation of the station name and zone number
	    */
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