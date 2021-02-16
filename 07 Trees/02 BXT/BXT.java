// Name: Saloni Shah 
// Date: 02/16/2021 (due date)
/*  Represents a binary expression tree.
 *  The BXT builds itself from postorder expressions. It can
 *  evaluate and print itself.  Also prints inorder and postorder strings. 
 */
 
import java.util.*;

public class BXT
{
   private TreeNode root;   
   
   public BXT()
   {
      root = null;
   }
   public TreeNode getRoot()   //for Grade-It
   {
      return root;
   }
    
   public void buildTree(String str)
   {
      String[] partsArray = str.split(" ");
      Stack<TreeNode> stack = new Stack<TreeNode>();
      for(String s : partsArray) 
      { 
         if(!isOperator(s)) 
         { 
            stack.push(new TreeNode(s)); 
         } 
         else 
         { 
            TreeNode right = (TreeNode)stack.pop(); 
            TreeNode left = (TreeNode)stack.pop(); 
            TreeNode tree = new TreeNode(s, left, right); 
            stack.push(tree); 
         } 
      } 
      root = (TreeNode)stack.pop();
   }
   
   public double evaluateTree()
   {
      return evaluateNode(root);
   }
   
   private double evaluateNode(TreeNode t)  //recursive
   {
      if(isOperator((String) t.getValue())) {
         return computeTerm((String) t.getValue(), evaluateNode(t.getLeft()), evaluateNode(t.getRight()));
      }
      else {
         return Double.parseDouble((String) t.getValue());
      }
   }
   
   private double computeTerm(String s, double a, double b)
   {
      if(s.equals("+")) {
         return a + b;
      }
      else if(s.equals("-")) {
         return a - b;
      }
      else if(s.equals("*")) {
         return a * b;
      }
      else if(s.equals("/")) {
         return a / b;
      }
      else {
         return -1;
      }
   }
   
   private boolean isOperator(String s)
   {
      String op = "+-*/";
      return op.contains(s);
   }
   
   public String display()
   {
      return display(root, 0);
   }
   
   private String display(TreeNode t, int level)
   {
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
    
   public String inorderTraverse()
   {
      return inorderTraverse(root);
   }
   
   private  String inorderTraverse(TreeNode t)
   {
      String toReturn = "";
      if(t == null)
         return "";   
      toReturn +=	inorderTraverse(t.getLeft());   //recurse left
      toReturn += t.getValue() + " ";             //inorder visit
      toReturn +=	inorderTraverse(t.getRight());  //recurse right  
      return toReturn;
   }
   
   public String preorderTraverse()
   {
      return preorderTraverse(root);
   }
   
   private String preorderTraverse(TreeNode root)
   {
      String toReturn = "";
      if(root == null)
         return "";
      toReturn += root.getValue() + " ";              //preorder visit
      toReturn += preorderTraverse(root.getLeft());   //recurse left
      toReturn += preorderTraverse(root.getRight());  //recurse right
      return toReturn;
   }
   
  /* extension*/
   public String inorderTraverseWithParentheses()
   {
      return inorderTraverseWithParentheses(root);
   }
   
   private String inorderTraverseWithParentheses(TreeNode t)
   {
      String toReturn = "";
      if(t == null) {
         return "";
      }
      if(t.getLeft() != null && isOperator((String)(t.getLeft().getValue()))) {
         toReturn +=	inorderTraverseWithParentheses(t.getLeft());   //recurse left
         toReturn += t.getValue() + " ";             //inorder visit
         toReturn +=	inorderTraverseWithParentheses(t.getRight());  //recurse right   
      }
      else if(t.getRight() != null && isOperator((String)(t.getRight().getValue()))) {
         toReturn += inorderTraverseWithParentheses(t.getLeft());   //recurse left
         if(!(isLower((String) t.getRight().getValue(), (String) t.getValue()))) {
            toReturn += t.getValue() + " ";             //inorder visit
            toReturn +=	inorderTraverseWithParentheses(t.getRight());  //recurse right 
         }
         else {
            toReturn += t.getValue() + " ( ";             //inorder visit
            toReturn +=	inorderTraverseWithParentheses(t.getRight()) + ") ";  //recurse right 
         }
          
      }
      else {
         toReturn +=	inorderTraverseWithParentheses(t.getLeft());   //recurse left
         toReturn += t.getValue() + " ";             //inorder visit
         toReturn +=	inorderTraverseWithParentheses(t.getRight());  //recurse right 
      }
      return toReturn;
   }
   
   private static boolean isLower(String op1, String op2)
   {
       if((op1.equals("+") || op1.equals("-")) && (op2.equals("*") || op2.equals("/")))
      {
         return true;
      }
      else if((op1.equals("+") && op2.equals("-")) || (op1.equals("-") && (op2.equals("*")))) {
         return true;
      }
      else if (op1.equals(op2)) {
         return true;
      }
      else {
         return false;
      }
      
   }
   
}