// Name: Saloni Shah           
// Date: 04/12/2021 (due date)
import java.util.*;
import java.io.*;
public class deHuffman
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nWhat binary message (middle part)? ");
      String middlePart = keyboard.next();
      Scanner sc = new Scanner(new File("message."+middlePart+".txt")); 
      String binaryCode = sc.next();
      Scanner huffLines = new Scanner(new File("scheme."+middlePart+".txt")); 
      	
      TreeNode root = huffmanTree(huffLines);
      String message = dehuff(binaryCode, root);
      System.out.println(message);
      	
      sc.close();
      huffLines.close();
   }
   public static TreeNode huffmanTree(Scanner huffLines)
   {
      //null temp root and a pointer
      TreeNode root = new TreeNode(null);
      TreeNode pointer;
      
      //continues for as long as there is a next line
      while(huffLines.hasNextLine()) {
         //sets the pointer to the root every run (resets)
         pointer = root;
         //unseparated code
         String code = huffLines.nextLine();
         //the letter that corresponds to the code
         char letter = code.charAt(0); 
         //correct code without the letter
         code = code.substring(1);
         
         for(int i = 0; i < code.length(); i++) {
            //if the char is 0, adds a new null node to the left
            if(code.charAt(i) == '0') {
               if(pointer.getLeft() == null) {
                  pointer.setLeft(new TreeNode(null));
               } 
               pointer = pointer.getLeft(); 
            }
            //if the char is 1, adds a new null node to the right
            else {
               if(pointer.getRight() == null) {
                  pointer.setRight(new TreeNode(null));
               } 
               pointer = pointer.getRight(); 
            }
         }
         //sets the value to the letter at the very end
         pointer.setValue(letter);
      }
      
      //returns the tree
      return root;
   }
   public static String dehuff(String text, TreeNode root)
   {
      //decode is for the final message, pos is the current position, and temp is a temp treenode
      String decode = "";
      int pos = 0;
      TreeNode temp = root;
      
      //continues for as long as the position is less than the length
      while(pos < text.length()) {
         //the nodes with characters are the only ones that are not null
         //adds the value if that is the case and resets
         if(temp.getValue() != null){
            decode += temp.getValue();
            temp = root;
         }
         //otherwise if the char in the string is 0, goes to the left
         else if(text.charAt(pos) == '0') {
            pos++;
            temp = temp.getLeft();
         }
         //otherwise, if the char in the string is 1, goes to the right
         else if(text.charAt(pos) == '1') {
            pos++;
            temp = temp.getRight();
         }
      }
      
      //adds the value one last time before returning the decoded message.
      decode += temp.getValue();
      return decode;
   }
}

 /* TreeNode class for the AP Exams */
class TreeNode
{
   private Object value; 
   private TreeNode left, right;
   
   public TreeNode(Object initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public Object getValue()
   { 
      return value; 
   }
   
   public TreeNode getLeft() 
   { 
      return left; 
   }
   
   public TreeNode getRight() 
   { 
      return right; 
   }
   
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode theNewRight)
   { 
      right = theNewRight;
   }
}