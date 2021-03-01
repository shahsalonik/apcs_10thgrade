// Name: Saloni Shah 
// Date: 03/01/2021
import java.util.*;

interface BSTinterface<E>
{
   public int size();
   public boolean contains(E element);
   public E add(E element);
   //public E addBalanced(E element);
   public E remove(E element);
   public E min();
   public E max();
   public String display();
   public String toString();
   public List<E> toList();  //returns an in-order list of E
}

/*******************
  Copy your BST code.  Implement generics.
**********************/
public class BST_Generic<E extends Comparable<E>> implements BSTinterface<E>
{

   private TreeNode<E> root;
   private int size;
   public BST_Generic()
   {
      root = null;
      size = 0;
   }
   public int size()
   {
      return size;
   }
   public TreeNode<E> getRoot()   //for Grade-It
   {
      return root;
   }
   
   public boolean contains(E element)
   {
      return contains(root, element);
   }
   private boolean contains(TreeNode<E> t, Comparable<E> element) //recursive helper method
   {
      if(t == null) {
         return false;
      }
      else if (element.compareTo(t.getValue()) < 0) {
         return contains(t.getLeft(), element); 
      }
      else if (element.compareTo(t.getValue()) > 0) {
         return contains(t.getRight(), element);
      }
      else {
         return true;
      }
   }
   
   public E add(E element) 
   {
      if(root == null) {
         size++;
         root = new TreeNode(element, null, null);
         return element;
      }
      else {
         size++;
         add(root, element);
         return element;
      }
   }
   
   private TreeNode<E> add(TreeNode<E> t, E element) //recursive helper method
   {      
      if(t == null) {
         TreeNode newNode = new TreeNode(element);
         return newNode;
      }
      else if(t.getValue().compareTo(element) > 0) {
         t.setLeft(add(t.getLeft(), element));
      }
      else if (t.getValue().compareTo(element) <= 0) {
         t.setRight(add(t.getRight(), element));
      }
      return balanceTree(t);
   }
   
   public int height(TreeNode<E> t)
   {
      //returns max of the heights + 1 
      if(t == null) {
         return -1;
      }
      else {
         return 1 + Math.max(height(t.getLeft()), height(t.getRight()));
      }
   }
   
   private int getBalance(TreeNode current) {
      
      if(current == null) {
         return 0;
      }
      else {
         return (height(current.getLeft()) - height(current.getRight()));
      }
   }
   
   private TreeNode<E> leftRotate(TreeNode<E> leftNode) {
   
      TreeNode rightNode = leftNode.getRight();
      
      leftNode.setRight(rightNode.getLeft());
      rightNode.setLeft(leftNode);
      
      return rightNode;
   
   }
   
   private TreeNode<E> rightRotate(TreeNode<E> rightNode) {
   
      TreeNode leftNode = rightNode.getLeft();
   
      rightNode.setLeft(leftNode.getRight());
      leftNode.setRight(rightNode);
   
      return leftNode;
   
   }
   
   
   private TreeNode<E> balanceTree(TreeNode<E> beginNode) {
      
      if(getBalance(beginNode) < -1) {
      
         if(getBalance(beginNode.getRight()) > 0) {
            beginNode.setRight(rightRotate(beginNode.getRight())); 
         }
         beginNode = leftRotate(beginNode);
      
      }
      else if (getBalance(beginNode) > 1) {
         if(getBalance(beginNode.getLeft()) < 0) {
            beginNode.setLeft(leftRotate(beginNode.getLeft()));
         }
         beginNode = rightRotate(beginNode);
      }
      return beginNode;
   }
   
   
   public E remove(E element)
   {
      size--;
      remove(root, element);
      return element;
   }
   
   private TreeNode<E> remove(TreeNode<E> current, E element)
   {
      TreeNode<E> parent = null;
      TreeNode<E> begin = current;
      
      //repeats for as long as we have not reached the end of the tree
      while(current != null) {
      //if element is found
         if (element.equals(( current.getValue()))) {
            //if the element is a leaf
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
         //element is less than the current node
         else if(current.getValue().compareTo(element) > 0) {
            parent = current;
            current = current.getLeft();
         }
         //element is greater than the current node
         else if (current.getValue().compareTo(element) < 0) {
            parent = current;
            current = current.getRight();
         }
         //elementelement not found, return empty node
         else {
            return new TreeNode("");
         }
      }
      return begin;
   }

   
   
   public E min() {
      return min(root);
   }
      
   private E min(TreeNode<E> t) {
      if(t == null) {
         return null;
      }
      else {
         while(t != null && t.getLeft() != null) {
            t = t.getLeft();
         }
         return t.getValue();
      }
   }
   
   public E max() {
      return max(root);
   }
   
   private E max(TreeNode<E> t) {
      if(t == null) {
         return null;
      }
      else if (t.getRight() != null) {
         return max(t.getRight());
      }
      return t.getValue();
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
   
   public List<E> toList() {
      return toList(root); 
   }
   
   private List<E> toList(TreeNode<E> t) {
   
      List<E> eList = new ArrayList<E>();
      
      if(t == null) {
         return eList;
      }
      
      eList.addAll(toList(t.getLeft()));
      eList.add(t.getValue());
      eList.addAll(toList(t.getRight()));
      
      return eList;
   }

}

/*******************
  Copy your TreeNode code.  Implement generics.
**********************/
class TreeNode<E extends Comparable<E>>
{

   private E value; 
   private TreeNode<E> left, right;
   
   public TreeNode(E initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(E initValue, TreeNode<E> initLeft, TreeNode<E> initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public E getValue()
   { 
      return value; 
   }
   
   public TreeNode<E> getLeft() 
   { 
      return left; 
   }
   
   public TreeNode<E> getRight() 
   { 
      return right; 
   }
   
   public void setValue(E theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode<E> theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode<E> theNewRight)
   { 
      right = theNewRight;
   }
   
}