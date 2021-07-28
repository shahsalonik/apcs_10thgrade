// Name: Saloni Shah
// Date: 02/08/2020 (due date)

import java.util.*;

public class TreeLab
{
   public static TreeNode root = null;
   //public static String s = "XCOMPUTERSCIENCE";
   //public static String s = "XThomasJeffersonHighSchool"; 
   public static String s = "AMERICAN";
   //public static String s = "XA";   //comment out lines 44-46 below
   //public static String s = "XAF";  //comment out lines 44-46 below
   //public static String s = "XAFP";  //comment out lines 44-46 below
   //public static String s = "XDFZM";  //comment out lines 44-46 below 
   
   public static void main(String[] args)
   {
      root = buildTree( root, s );
      System.out.print( display( root, 0) );
   
      System.out.print("\nPreorder: " + preorderTraverse(root));
      System.out.print("\nInorder: " + inorderTraverse(root));
      System.out.print("\nPostorder: " + postorderTraverse(root));
   
      System.out.println("\n\nNodes = " + countNodes(root));
      System.out.println("Leaves = " + countLeaves(root));
      System.out.println("Only children = " + countOnlys(root));
      System.out.println("Grandparents = " + countGrandParents(root));
   
      System.out.println("\nHeight of tree = " + height(root));
      System.out.println("Longest path = " + longestPath(root));
      System.out.println("Min = " + min(root));
      System.out.println("Max = " + max(root));	
   
      System.out.println("\nBy Level: ");
      System.out.println(displayLevelOrder(root));
   }
 
   public static TreeNode buildTree(TreeNode root, String s)
   {
      root = new TreeNode("" + s.charAt(1), null, null);
      for(int pos = 2; pos < s.length(); pos++)
         insert(root, "" + s.charAt(pos), pos, 
            (int)(1 + Math.log(pos) / Math.log(2)));
      insert(root, "A", 17, 5); 
      insert(root, "B", 18, 5); 
      insert(root, "C", 37, 6); //B's right child
      return root;
   }

   public static void insert(TreeNode t, String s, int pos, int level)
   {
      TreeNode p = t;
      for(int k = level - 2; k > 0; k--)
      {
         if((pos & (1 << k)) == 0)
            p = p.getLeft();
         else
            p = p.getRight();
      }
      if((pos & 1) == 0)
         p.setLeft(new TreeNode(s, null, null));
      else
         p.setRight(new TreeNode(s, null, null));
   }
   
   private static String display(TreeNode t, int level)
   {
      // turn your head towards left shoulder visualize tree
      String toRet = "";
      if(t == null)
         return "";
      toRet += display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); //recurse left
      return toRet;
   }
   
   public static String preorderTraverse(TreeNode t)
   { 
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += t.getValue() + " ";              //preorder visit
      toReturn += preorderTraverse(t.getLeft());   //recurse left
      toReturn += preorderTraverse(t.getRight());  //recurse right
      return toReturn;
   }
   
   public static String inorderTraverse(TreeNode t)
   {
      String toReturn = "";
      if(t == null)
         return "";   
      toReturn +=	inorderTraverse(t.getLeft());   //recurse left
      toReturn += t.getValue() + " ";             //inorder visit
      toReturn +=	inorderTraverse(t.getRight());  //recurse right  
      return toReturn;
   }
   
   public static String postorderTraverse(TreeNode t)
   {
      String toReturn = "";
      if(t == null)
         return "";   
      toReturn +=	postorderTraverse(t.getLeft());   //recurse left
      toReturn +=	postorderTraverse(t.getRight());  //recurse right 
      toReturn += t.getValue() + " ";               //postorder visit 
      return toReturn;
   }
   
   public static int countNodes(TreeNode t)
   {
      //counts nodes by going left and right and adding 1
      if(t == null) {
         return 0;
      }
      return 1 + countNodes(t.getLeft()) + countNodes(t.getRight());
   }
   
   public static int countLeaves(TreeNode t)
   {
      //counts leaves by determining whether it has any children
      if(t == null) {
         return 0;
      }  
      else if (t.getLeft() == null && t.getRight() == null) {
         return 1;
      }
      else {
         return countLeaves(t.getLeft()) + countLeaves(t.getRight());
      }
   }
   
   /*  there are clever ways and hard ways to count grandparents  */ 
   public static int countGrandParents(TreeNode t)
   {
   //checks if height >= 2, if yes, return 1 and recurs until the end
      if(t == null) {
         return 0;
      }
      if(height(t) >= 2) {
         return 1 + countGrandParents(t.getLeft()) + countGrandParents(t.getRight());
      }
      else {
         return countGrandParents(t.getLeft()) + countGrandParents(t.getRight());
      }
   }
   
   public static int countOnlys(TreeNode t)
   {
      //counts only children
      if(t == null) {
         return 0;
      }
      else {
         if(t.getLeft() != null && t.getRight() == null) {
            return 1 + countOnlys(t.getLeft());
         }
         else if (t.getLeft() == null && t.getRight() != null) {
            return 1  + countOnlys(t.getRight());
         }
         else {
            return countOnlys(t.getLeft()) + countOnlys(t.getRight());
         }
      }
   }
   
  /* returns the max of the heights to the left and the heights to the right  
     returns -1 in case the tree is null
    */
   public static int height(TreeNode t)
   {
      //returns max of the heights + 1 
      if(t == null) {
         return -1;
      }
      else {
         return 1 + Math.max(height(t.getLeft()), height(t.getRight()));
      }
   }
   
 /* return the max of the sum of the heights to the left and the heights to the right  
 */
   public static int longestPath(TreeNode t)
   {
      if(t == null) {
         return 0;
      }  
      else {
         int leftLongestPath = longestPath(t.getLeft());
         int rightLongestPath = longestPath(t.getRight());
         int rootLongestPath = 2 + height(t.getLeft()) + height(t.getRight());
         
         int temp = Math.max(leftLongestPath, rightLongestPath);
         int max = Math.max(temp, rootLongestPath);
         return max;
      }
   }
   
   /*  Object must be cast to Comparable in order to call .compareTo  
    */
   @SuppressWarnings("unchecked")
   public static Object min(TreeNode t)
   {
      if(t == null) {
         return "z";
      } 
      //has a default min object, finds the min on both sides, then compares them to each other
      Comparable minObj = (Comparable) t.getValue();
      Comparable leftMin = (Comparable) min(t.getLeft());
      Comparable rightMin = (Comparable) min(t.getRight());
      if(leftMin.toString().compareTo(minObj.toString()) < 0) {
         minObj = leftMin;
      }
      if(rightMin.toString().compareTo(minObj.toString()) < 0) {
         minObj = rightMin;
      }
      return (Object) minObj;
   }
   
   /*  Object must be cast to Comparable in order to call .compareTo  
    */
   @SuppressWarnings("unchecked")
   public static Object max(TreeNode t)
   {
      if(t == null) {
         return "A";
      } 
      //has a default max object, finds the max on both sides, then compares them to each other
      Comparable maxObj = (Comparable) t.getValue();
      Comparable leftMax = (Comparable) max(t.getLeft());
      Comparable rightMax = (Comparable) max(t.getRight());
      if(leftMax.toString().compareTo(maxObj.toString()) > 0) {
         maxObj = leftMax;
      }
      if(rightMax.toString().compareTo(maxObj.toString()) > 0) {
         maxObj = rightMax;
      }
      return (Object) maxObj;
   }
      
   /* This method is not recursive.  Use a local queue
    * to store the children of the current TreeNode.
    */
   public static String displayLevelOrder(TreeNode t)
   {
      Queue<TreeNode> levelQueue = new LinkedList<TreeNode>();
      levelQueue.add(t);
      String levelOrder = "";
      //adds and removes until the queue is empty
      while(!levelQueue.isEmpty()) 
      { 
         TreeNode n = levelQueue.remove(); 
         levelOrder += n.getValue(); 
         if(n.getLeft() != null) {
            levelQueue.add(n.getLeft());
         } 
         if(n.getRight() != null) {
            levelQueue.add(n.getRight()); 
         }
      }
      return levelOrder;
   }
}

/***************************************************
    ----jGRASP exec: java TreeLab
 		  	E
 		E
 			C
 	M
 			N
 		T
 			E
 C
 			I
 		U
 			C
 	O
 			S
 					C
 				B
 		P
 				A
 			R
 
 Preorder: C O P R A S B C U C I M T E N E C E 
 Inorder: R A P B C S O C U I C E T N M C E E 
 Postorder: A R C B S P C I U O E N T C E E M C 
 
 Nodes = 18
 Leaves = 8
 Only children = 3
 Grandparents = 5
 
 Height of tree = 5
 Longest path = 8
 Min = A
 Max = U
 
 By Level: 
 COMPUTERSCIENCEABC    
 *******************************************************/
