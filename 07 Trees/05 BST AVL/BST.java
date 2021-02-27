// Name: Saloni Shah 
// Date: 03/01/2021

interface BSTinterface
{
   public int size();
   public boolean contains(String obj);
   public void add(String obj);   //does not balance
   public void addBalanced(String obj);
   public void remove(String obj);
   public String min();
   public String max();
   public String display();
   public String toString();
}

public class BST implements BSTinterface
{
   /*  copy your BST code  here  */

   private TreeNode root;
   private int size;
   public BST()
   {
      root = null;
      size = 0;
   }
   public int size()
   {
      return size;
   }
   public TreeNode getRoot()   //for Grade-It
   {
      return root;
   }
   /***************************************
   @param s -- one string to be inserted
   ****************************************/
   public void add(String s) 
   {
      if(root == null) {
         root = new TreeNode(s, null, null);
         size++;
      }
      else {
         size++;
         add(root, s);
      }
   }
   private TreeNode add(TreeNode t, String s) //recursive helper method
   {      
      if(t == null) {
         TreeNode newNode = new TreeNode(s);
         return newNode;
      }
      else if(s.compareTo((String) t.getValue()) <= 0) {
         if(t.getLeft() == null) {
            TreeNode newNode = new TreeNode(s);
            t.setLeft(newNode);
         }
         else {
            t.setLeft(add(t.getLeft(), s));
         }
      }
      else if (s.compareTo((String) t.getValue()) > 0) {
         if(t.getRight() == null) {
            TreeNode newNode = new TreeNode(s);
            t.setRight(newNode);
         }
         else {
            t.setRight(add(t.getRight(), s));
         }  
      }
      return t;
   }
   
   public String display()
   {
      return display(root, 0);
   }
   private String display(TreeNode t, int level) //recursive helper method
   {
      String toRet = "";
      if(t == null) {
         return "";
      }
      toRet += display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++) {
         toRet += "\t";
      }
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); //recurse left
      return toRet;
   }
   
   public boolean contains( String obj)
   {
      return contains(root, obj);
   }
   private boolean contains(TreeNode t, String x) //recursive helper method
   {
      if(t == null) {
         return false;
      }
      else if (x.compareTo((String) t.getValue()) < 0) {
         return contains(t.getLeft(), x); 
      }
      else if (x.compareTo((String) t.getValue()) > 0) {
         return contains(t.getRight(), x);
      }
      else {
         return true;
      }
   }
   
   public String min()
   {
      return min(root);
   }
   private String min(TreeNode t)  //use iteration
   {
      if(t == null) {
         return null;
      }
      else {
         while(t != null && t.getLeft() != null) {
            t = t.getLeft();
         }
         return (String) t.getValue();
      }
   }
   
   public String max()
   {
      return max(root); 
   }
   private String max(TreeNode t)  //recursive helper method
   {
      if(t == null) {
         return null;
      }
      else if (t.getRight() != null) {
         return max(t.getRight());
      }
      return (String) t.getValue();
   }
   
   public String toString()
   {
      return toString(root);
   }
   private String toString(TreeNode t)  //an in-order traversal.  Use recursion.
   {
      String toReturn = "";
      if(t == null)
         return "";   
      toReturn +=	toString(t.getLeft());   //recurse left
      toReturn += t.getValue() + " ";             //inorder visit
      toReturn +=	toString(t.getRight());  //recurse right  
      return toReturn;
   }
   
   public void remove(String target)
   {
      root = remove(root, target);
      size--;
   }
   private TreeNode remove(TreeNode current, String target)
   {
      TreeNode parent = null;
      TreeNode begin = current;
      
      //repeats for as long as we have not reached the end of the tree
      while(current != null) {
      //if target is found
         if (target.equals(((String) current.getValue()))) {
            //if the target is a leaf
            if(current.getLeft() == null && current.getRight() == null) {
               //leaf == root?
               if(parent == null) {
                  return null;
               }
               //a leaf on the left
               else if (current == parent.getLeft()) {
                  parent.setLeft(null);
                  current.setValue(null);
               }
               //leaf on the right
               else {
                  parent.setRight(null);
                  current.setValue(null);
               }
               return begin;
            }
             //if left or right is not null
            else if((current.getLeft() != null && current.getRight() == null) || (current.getLeft() == null && current.getRight() != null)) {
               //removing root
               if(parent == null) {
                  parent = current;
                  //returns the left node
                  if(current.getLeft() != null && current.getRight() == null) {
                     return current.getLeft();
                  }
                  //returns the right node
                  else if (current.getLeft() == null && current.getRight() != null) {
                     return current.getRight();
                  }
                  //returns the left node
                  else {
                     return current.getLeft();
                  }
               }
               //if it is on the left
               else if(current == parent.getLeft()) {
                  //sets the left to the current's left
                  if(current.getLeft() != null && current.getRight() == null) {
                     parent.setLeft(current.getLeft());
                  }
                  //sets the left to the right
                  else {
                     parent.setLeft(current.getRight());
                  }
               }
               //if on the right
               else {
               //sets the right
                  if(current.getLeft() == null && current.getRight() != null) {
                     parent.setRight(current.getRight());
                  }
                  //sets the right
                  else {
                     parent.setRight(current.getLeft());
                  }
               }
               //returns the node from current one
               return begin;
            }
             //two children
            else if(current.getLeft() != null && current.getRight() != null)  {
               //current is root
               if(parent == null) {
                  parent = current;
                  if(current.getLeft().getRight().getRight() == null) {
                     current.getLeft().getRight().setRight(current.getRight());
                     return parent.getLeft();
                  }
               }
               //current is on the left
               else if(parent.getLeft().equals(current)) {
                  if(current.getLeft().getRight() == null) {
                     current.getLeft().setRight(current.getRight());
                     parent.setLeft(current.getLeft());
                  }
                  else {
                     current.getRight().getLeft().setLeft(current.getLeft());
                     parent.setLeft(current.getRight());
                  }
               }
               //current is on the right
               else {
                  if(current.getRight().getLeft() == null) {
                     current.getRight().setLeft(current.getLeft());
                     parent.setRight(current.getRight());
                  }
                  else {
                     current.getRight().getLeft().setLeft(current.getLeft());
                     parent.setRight(current.getRight());
                  }  
               }
               return begin;
            }
         }
         //target is less than the current node
         else if(target.compareTo((String) current.getValue()) < 0) {
            parent = current;
            current = current.getLeft();
         }
         //target is greater than the current node
         else if (target.compareTo((String) current.getValue()) > 0) {
            parent = current;
            current = current.getRight();
         }
         //target not found, return empty node
         else {
            return new TreeNode("");
         }
      }
      return begin;
   }

   /*  start the addBalanced methods */
   public void addBalanced(String value)  
   {
      size++;
      root = add(root, value); 
      root = balanceTree(root);
      //balanceTree(height(root), root, isBalanced(root));   // for an AVL tree.  You may change this line.
   }
   
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
   
   private static int getBalance(TreeNode current) {
      
      if(current == null) {
         return 0;
      }
      else {
         return (height(current.getLeft()) - height(current.getRight()));
      }
   }
   
   private static TreeNode leftRotate(TreeNode inPoint) {
      
      //establishing the left rotate variables
      TreeNode insLeft;
      if(inPoint.getLeft() == null) {
         insLeft = inPoint.getRight();
      }
      else {
         insLeft = inPoint.getLeft();
      }
      
      //left rotation
      insLeft.setRight(inPoint);
      inPoint.setLeft(null);
      
      return insLeft;
   
   }
   
   private static TreeNode rightRotate(TreeNode inPoint) {
   
      //establishing the right rotate variables
      TreeNode insRight = inPoint.getRight();
      
      insRight.setLeft(inPoint);
      inPoint.setRight(null);
      
      return insRight;
   
   }
   
   private static TreeNode balanceTree(TreeNode begin) {
      
      //checking the balance
      int balHeight = getBalance(begin);
      
      if(Math.abs(balHeight) <= 1) {
         return begin;
      }
      
      //left heavy
      else if(balHeight > 1) {
      //RL
         if(getBalance(begin.getLeft()) <= 0) {
            begin.setLeft(rightRotate(begin.getLeft()));
            return leftRotate(begin);
         }
         //LL
         else {
            return leftRotate(begin);
         }
      }
      //right heavy
      else if(balHeight < 0) {
      //LR
         if(getBalance(begin.getRight()) <= 0) {
            begin.setRight(leftRotate(begin.getRight()));
            return rightRotate(begin);
         }
         //RR
         else {
            return rightRotate(begin);
         }
      }
      
      return begin;
   }
   
}