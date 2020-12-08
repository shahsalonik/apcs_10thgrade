
public class Autoboxing {

   public static void main(String[] args) {
   
      int[] intArray = {1, 2, 3, 4, 5};
   
      ListNode head;
      head = new ListNode(3, null);
      head = new ListNode(2, head);
      head = new ListNode(1, head);
      
      System.out.println("Array data:");
      for(int x = 0; x < intArray.length; x++) {
         System.out.println(intArray[x]);
      }
      
      System.out.println();
      
      System.out.println("ListNode data:");
      ListNode pointer;
      for(pointer = head; pointer != null; pointer = pointer.getNext()) {
         System.out.println(pointer.getValue());
      }
   
   }

}