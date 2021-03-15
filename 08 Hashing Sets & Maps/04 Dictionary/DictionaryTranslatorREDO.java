// Name: Saloni Shah
// Date: 03/15/2021 (due date)

import java.io.*;
import java.util.*;

public class DictionaryTranslatorREDO
{
   Map<String, Set<String>> eng2spn;
   
   Map<String, Set<String>> spn2eng;
   static Scanner userInput = new Scanner(System.in);
   public DictionaryTranslatorREDO() {
   
      Scanner infile = null;
     
      try
      {
         infile = new Scanner(new File("spanglish.txt"));
      }
      catch(Exception e)
      {
         System.out.println("File not Found");
      }
      
      eng2spn = makeDictionary(infile);
      spn2eng = reverse(eng2spn);
   
   }
   
   private void spanishToEnglish(String spword) {
   
    //check if the word is in the dictionary
      Set<String> engtranslated = spn2eng.get(spword);
      if(engtranslated != null) {
         System.out.println("Here are the translated words!" + engtranslated);
         System.out.println("Do you know any other translations for " + spword + "? Type yes or no: ");
         if(userInput.next().equalsIgnoreCase("Yes")) {
            System.out.print("Please type it now!: ");
            String extraTranslate = userInput.next();
            spn2eng.get(spword).add(extraTranslate);
         }
      }
      else {
         System.out.println("Sorry, we don't have that word!");
      }
   }
   
   private void englishToSpanish(String engword) {
      Set<String> sptranslated = eng2spn.get(engword);
      if(sptranslated != null) {
         System.out.println("Here are the translated words!" + sptranslated);
         System.out.println("Do you know any other translations for " + engword + "? Type yes or no: ");
         if(userInput.next().equalsIgnoreCase("Yes")) {
            System.out.print("Please type it now!: ");
            String extraTranslate = userInput.next();
            eng2spn.get(engword).add(extraTranslate);
         }
      }
      else {
         System.out.println("Sorry, we don't have that word!");
      }
   }
   
   public static void main(String[] args) 
   {
      DictionaryTranslatorREDO translator = new DictionaryTranslatorREDO();

      int choice = -1;
      while(choice != 0) {
         System.out.println("Hello! To translate to English, press 1. To translate to Spanish, press 2. To exit, type 0: ");
         choice = userInput.nextInt();
      
         switch(choice) {
         
            case 0:
               System.out.println("Thanks for using the translator!");
               translator.saveToFile();
               System.exit(0);
            
            case 1:
            //translate to english given a spanish input
               System.out.println("Which Spanish word would you like to translate?: ");
               String spword = userInput.next();
               translator.spanishToEnglish(spword);
               break;
            
            case 2:
            //translate to spanish w english input
               System.out.println("Which English word would you like to translate?: ");
               String engword = userInput.next();
               translator.englishToSpanish(engword); 
               break;
            
            default:
               System.out.println("Invalid choice.");
               break;
         }
      } 
      
      translator.saveToFile(); 
      
   }
   
   private void saveToFile() {
      try {
         System.setOut(new PrintStream(new FileOutputStream("dictionaryOutput.txt")));
      }
      catch(FileNotFoundException e) {
         System.exit(0);
      }
      System.out.println("ENGLISH TO SPANISH");
      display(eng2spn);
      
      System.out.println("SPANISH TO ENGLISH");
      display(spn2eng);
   }
   
   public Map<String, Set<String>> makeDictionary(Scanner infile)
   {
      //reads each line and adds it to the dictionary
      Map<String, Set<String>> dict =  new TreeMap<String, Set<String>>();
      
      while(infile.hasNext()) {
         add(dict, infile.next(), infile.next());
      }
      
      return dict;
   
   }
   
   public void add(Map<String, Set<String>> dictionary, String word, String translation)
   { 
      //if the dictionary is empty, adds the word, then the translation into it
      if(dictionary.isEmpty()) {
         Set<String> temp = new HashSet<String>();
         temp.add(translation);
         dictionary.put(word, temp);
      }
      //otherise, if the dictionary already has the word, adds the other translation 
      else if(dictionary.containsKey(word)) {
         dictionary.get(word).add(translation);
      }
      //otherwise, adds the word and the translation.
      else {
         Set<String> temp2 = new HashSet<String>();
         temp2.add(translation);
         dictionary.put(word, temp2);
      }
   
   }
   
   public void display(Map<String, Set<String>> m)
   {
      //iterates through the whole map
      for(String str: m.keySet()) {
         System.out.println(str + " " + m.get(str));
      }
   }
   
   public Map<String, Set<String>> reverse(Map<String, Set<String>> dictionary)
   {
      //iterates through the keyset (then the values) and adds the reversed one as a translation
      Map<String, Set<String>> reversed = new TreeMap<String, Set<String>>();
      for(String s : dictionary.keySet()) {
         for(String str : dictionary.get(s)) {
            add(reversed, str, s);
         }
      }
      return reversed;
   }
}


   /********************
	INPUT:
   	holiday
		fiesta
		holiday
		vacaciones
		party
		fiesta
		celebration
		fiesta
     <etc.>
  *********************************** 
	OUTPUT:
		ENGLISH TO SPANISH
			banana [banana]
			celebration [fiesta]
			computer [computadora, ordenador]
			double [doblar, doble, duplicar]
			father [padre]
			feast [fiesta]
			good [bueno]
			hand [mano]
			hello [hola]
			holiday [fiesta, vacaciones]
			party [fiesta]
			plaza [plaza]
			priest [padre]
			program [programa, programar]
			sleep [dormir]
			son [hijo]
			sun [sol]
			vacation [vacaciones]

		SPANISH TO ENGLISH
			banana [banana]
			bueno [good]
			computadora [computer]
			doblar [double]
			doble [double]
			dormir [sleep]
			duplicar [double]
			fiesta [celebration, feast, holiday, party]
			hijo [son]
			hola [hello]
			mano [hand]
			ordenador [computer]
			padre [father, priest]
			plaza [plaza]
			programa [program]
			programar [program]
			sol [sun]
			vacaciones [holiday, vacation]

**********************/