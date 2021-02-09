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
  
  }
    
   public void buildTree(String str)
   {
     	
   }
   
   public double evaluateTree()
   {
      return evaluateNode(root);
   }
   
   private double evaluateNode(TreeNode t)  //recursive
   {
   
   }
   
   private double computeTerm(String s, double a, double b)
   {
     
   }
   
   private boolean isOperator(String s)
   {
    
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
     
   }
   
   public String preorderTraverse()
   {
      return preorderTraverse(root);
   }
   
   private String preorderTraverse(TreeNode root)
   {
   
   }
   
  /* extension */
   // public String inorderTraverseWithParentheses()
   // {
      // return inorderTraverseWithParentheses(root);
   // }
//    
   // private String inorderTraverseWithParentheses(TreeNode t)
   // {
      // return "";
   // }
}