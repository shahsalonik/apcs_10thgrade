// Name: Saloni Shah        
// Date: 04/19/2021 (due date)
import java.util.*;
import java.io.*;
public class Huffman
{
   public static Scanner keyboard = new Scanner(System.in);
   public static void main(String[] args) throws IOException
   {
      //Prompt for two strings 
      System.out.print("Encoding using Huffman codes");
      System.out.print("\nWhat message? ");
      String message = keyboard.nextLine();
   
      System.out.print("\nEnter middle part of filename:  ");
      String middlePart = keyboard.next();
   
      huffmanize( message, middlePart );
   }
   public static void huffmanize(String message, String middlePart) throws IOException
   {
         //Make a frequency table of the letters
         
      char[] freqCheck = message.toCharArray();
      HashMap<Character, Integer> freqTable = new HashMap<Character, Integer>();
         
      for(char c : freqCheck) {
         if(freqTable.containsKey(c)) {
            freqTable.put(c, freqTable.get(c) + 1); 
         }
         else {
            freqTable.put(c, 1);
         }
      }
         
      	//Put each letter-frequency pair into a HuffmanTreeNode. Put each 
   		//        node into a priority queue (or a min-heap).   
         
      PriorityQueue<HuffmanTreeNode> pq = new PriorityQueue<HuffmanTreeNode>();
      for(char ch : freqTable.keySet()) {
         pq.add(new HuffmanTreeNode(ch, freqTable.get(ch)));
      }
           
      	//Use the priority queue of nodes to build the Huffman tree
      HashMap<Character, String> helper = new HashMap<Character, String>();
      for(char k : freqTable.keySet()) {
         helper.put(k, "");
      }
      while(pq.size() > 1) {
         HuffmanTreeNode child1 = pq.remove();
         HuffmanTreeNode child2 = pq.remove();
         HuffmanTreeNode parent = new HuffmanTreeNode('*', (child1.getFreq() + child2.getFreq()), child1, child2);
         letterSearch(child1, '0', helper);
         letterSearch(child2, '1', helper);
         pq.add(parent);
      } 
      HuffmanTreeNode last = pq.remove();
         
         //System.out.println the binary message 
      	//Write the binary message to the hard drive using the file name ("message." + middlePart + ".txt")
         //System.out.println the scheme from the tree--needs a recursive helper method
      	//Write the scheme to the hard drive using the file name ("scheme." + middlePart + ".txt")
         
      PrintStream toPrint = new PrintStream(new File("scheme." + middlePart + ".txt"));
      for(char ch : helper.keySet()) {
         toPrint.println(ch + helper.get(ch));
      }
      toPrint = new PrintStream(new File("message." + middlePart + ".txt"));
      for(char mes : message.toCharArray()) {
         toPrint.print(helper.get(mes));
      }  
            
   }
   
   //Process the string letter-by-letter and search the tree for the 
   		//       letter. It's recursive. As you recur, build the path through the tree, 
   		//       where going left is 0 and going right is 1.
   public static void letterSearch(HuffmanTreeNode h, char c, HashMap<Character, String> m) {
      
      if(h.getLetter() != '*') {
         m.put(h.getLetter(), c + m.get(h.getLetter()));
         return;
      }
      else {
         letterSearch(h.getLeft(), c, m);
         letterSearch(h.getRight(), c, m);
      }
   }
}
	/*
	  * This tree node stores two values.  Look at TreeNode's API for some help.
	  * The compareTo method must ensure that the lowest frequency has the highest priority.
	  */
class HuffmanTreeNode implements Comparable<HuffmanTreeNode>
{
   
   private char letter;
   private int frequency;
   private HuffmanTreeNode left, right;
   
   public HuffmanTreeNode(char l, int freq) {
      letter = l;
      frequency = freq;
   }
   
   public HuffmanTreeNode(char l, int freq, HuffmanTreeNode initLeft, HuffmanTreeNode initRight)
   { 
      letter = l; 
      frequency = freq;
      left = initLeft; 
      right = initRight; 
   }  
   
   public char getLetter() {
      return letter;
   } 
   
   public int getFreq() {
      return frequency;
   }
   
   public HuffmanTreeNode getLeft() {
      return left;
   }
   
   public void setLeft(HuffmanTreeNode ln) {
      left = ln;
   }
   
   public HuffmanTreeNode getRight() {
      return right;
   }
   
   public void setRight(HuffmanTreeNode rn) {
      right = rn;
   }
   
   public int compareTo(HuffmanTreeNode h) {
      
      return (getFreq() - h.getFreq());
   }
   
   public String toString() {
      return letter + ": " + frequency;
   }
   
}
