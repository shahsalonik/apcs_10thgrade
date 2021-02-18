// Name: Saloni Shah
// Date: 02/22/2021 (due date)

interface BSTinterface
{
   public int size();
   public boolean contains(String obj);
   public void add(String obj);
   //public void addBalanced(String obj);
   public void remove(String obj);
   public String min();
   public String max();
   public String display();
   public String toString();
}

/*******************
Copy your BST code.  Implement the remove() method.
Test it with BST_Delete.java
**********************/
public class BST implements BSTinterface
{
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
      if(s.compareTo((String) t.getValue()) <= 0) {
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
      
      while(current != null) {
         if (target.equals(((String) current.getValue()))) {
            //if the target is a leaf
            if(current.getLeft() == null && current.getRight() == null) {
               //leaf == root?
               if(parent == null) {
                  current.setValue("");
               }
               else {
                  parent.setLeft(null);
                  parent.setRight(null);
                  current.setValue(null);
               }
               return begin;
            }
             //if left or right is not null
            else if((current.getLeft() != null && current.getRight() == null) || (current.getLeft() == null && current.getRight() != null)) {
               if(parent == null) {
                  parent = current;
                  if(current.getLeft() != null && current.getRight() == null) {
                     parent = current.getLeft();
                     current.setValue(null);
                     current = parent;
                  }
                  else if (current.getLeft() == null && current.getRight() != null) {
                     parent = current.getRight();
                     current.setValue(null);
                     current = parent;
                  }
                  else {
                     parent = current.getLeft();
                     current.setValue(null);
                     current = parent;
                  }
               }
               else if(current == parent.getLeft()) {
                  if(current.getLeft() != null && current.getRight() == null) {
                     parent.setLeft(current.getLeft());
                  }
                  else {
                     parent.setLeft(current.getRight());
                  }
               }
               else {
                  if(current.getLeft() == null && current.getRight() != null) {
                     parent.setRight(current.getRight());
                  }
                  else {
                     parent.setRight(current.getLeft());
                  }
               }
               return begin;
            }
             //two children
            else if(current.getLeft() != null && current.getRight() != null)  {
               if(current.getLeft().getLeft() == null && current.getRight().getRight() == null) {
                  parent.setLeft(current.getLeft());
               }
               else {
                  parent.setLeft(current.getRight());
                  current.getLeft().setRight(parent.getRight());
               }
               return begin;
            }
         }
         else if(target.compareTo((String) current.getValue()) < 0) {
            parent = current;
            current = current.getLeft();
         }
         else if (target.compareTo((String) current.getValue()) > 0) {
            parent = current;
            current = current.getRight();
         }
         else {
            return new TreeNode("");
         }
      }
      return begin;
   }
}