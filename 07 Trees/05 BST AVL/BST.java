// Name: 
// Date: 

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




   /*  start the addBalanced methods */
   public void addBalanced(String value)  
   {
      size++;
      root = add(root, value);
      balanceTree(null, root, true);   // for an AVL tree.  You may change this line.
   }
   
}