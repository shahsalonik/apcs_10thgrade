//Name: Saloni Shah
//Date: 02/16/2021 (due date)

interface BSTinterface
{
   public int size();
   public boolean contains(String obj);
   public void add(String obj);
   //public void addBalanced(String obj);
   //public boolean remove(String obj);
   public String min();
   public String max();
   public String toString();
}

/*******************
Represents a binary search tree holding Strings. 
Implements (most of) BSTinterface, above. 
The recursive methods all have a public method calling a private helper method. 
Copy the display() method from TreeLab. 
**********************/
class BST implements BSTinterface
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
     
   }
   public TreeNode getRoot()   //for Grade-It
   {
   
   }
   /***************************************
   @param s -- one string to be inserted
   ****************************************/
   public void add(String s) 
   {
      
   }
   private TreeNode add(TreeNode t, String s) //recursive helper method
   {      
   
   }
   
   public String display()
   {
      
   }
   private String display(TreeNode t, int level) //recursive helper method
   {
   
   }
   
   public boolean contains( String obj)
   {
      
   }
   private boolean contains(TreeNode t, String x) //recursive helper method
   {
   
   }
   
   public String min()
   {
      
   }
   private String min(TreeNode t)  //use iteration
   {
      
   }
   
   public String max()
   {
      
   }
   private String max(TreeNode t)  //recursive helper method
   {
      
   }
   
   public String toString()
   {
      
   }
   private String toString(TreeNode t)  //an in-order traversal.  Use recursion.
   {
   
   }
}
