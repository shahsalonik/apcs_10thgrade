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
 
   }
   
   //returns the number of times that the Object o appears in the Tree with root n.
   public static int count(TreeNode n, Object o){
      //your code here
      return -1;
   }
   
   public static String path(TreeNode root, TreeNode n){
   return "";
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