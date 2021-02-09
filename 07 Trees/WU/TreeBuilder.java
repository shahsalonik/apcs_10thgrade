//Stephen Stern sstern1@fcps.edu
//2.4.21
import java.util.*;
import java.io.*;

public class TreeBuilder{
   static TreeNode target;
   public static void main (String[] args) throws Exception {
      File tree = new File("input.txt");
      File tree_small = new File("input_small.txt");
      TreeNode root = buildTree(tree);
      TreeNode root_small = buildTree(tree_small);
      //your code here
      System.out.println(path(root, target));
      
   }
   
   //returns the number of times that the Object o appears in the Tree with root n.
   public static int count(TreeNode n, Object o){
      if(n == null) {
         return 0;
      }
      else if (n.getValue().equals(o)) {
         return 1 + count(n.getLeft(), o) + count(n.getRight(), o);
      }
      else {
         return count(n.getLeft(), o) + count(n.getRight(), o);
      }
   }
   
   public static String path(TreeNode root, TreeNode n){      
      if(root == null) {
         return null;
      }
      if (root == n){
         return root.getValue().toString();
      }
      
      String s = path(root.getLeft(), n);
      if(s != null) {
         return root.getValue().toString() + s;
      }
      s = path(root.getRight(), n);
      if(s != null) {
         return root.getValue().toString() + s;
      }
      return null; 
   }
   

   public static TreeNode buildTree(File f) throws Exception{
      Scanner in = new Scanner(f);
      int size = in.nextInt();
      in.nextLine();
      TreeNode[] nodes = new TreeNode[size];
      int[][] children = new int[size][2];
   
      for(int i=0;i<size; i++){
         String[] line = in.nextLine().split(", ");
         nodes[i] = new TreeNode(line[0]);
         children[i][0] = Integer.parseInt(line[1]);
         children[i][1] = Integer.parseInt(line[2]);
      }
   
      for(int i=0;i<size;i++){
         if(children[i][0]!=-1)
            nodes[i].setLeft(nodes[children[i][0]]);
         if(children[i][1]!=-1) 
            nodes[i].setRight(nodes[children[i][1]]);
      }
      in.close();
      if(nodes.length>50341)
         target=nodes[50341];
      return nodes[0];
   }
}