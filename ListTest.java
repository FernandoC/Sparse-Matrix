// ListTest.java
// A test client for the List ADT

class ListTest{
   public static void main(String[] args){
      List A = new List();
      List B = new List();
      List C = null;

      for(int i=1; i<=10; i++){
         A.insertBack(i);
         B.insertBack(11-i);
      }
      System.out.println("A = " + A);
      System.out.println("B = " + B);

      for(int i=1; i<=6; i++){
         A.insertFront(B.getBack());
         B.deleteBack();
      }

      System.out.println("A.insertFront() of last 6 elements of B  = " + A);
      System.out.println("B.deleteBack() * 6 = " + B);

      System.out.println("A " + (A.equals(B)?"equals":"does not equal") + " B");
      System.out.println("A " + (A.equals(C)?"equals":"does not equal") + " C");

      System.out.println("A.getFront() = " + A.getFront());
      System.out.println("A.getBack() = " + A.getBack());

      A.moveTo(5);
      System.out.println("A.moveTo(5)+ A.getCurrent() = " + A.getCurrent());
      A.insertBeforeCurrent(9);
      System.out.println("A.insertBeforeCurrent(9) = " + A);
      A.insertAfterCurrent(7);
      System.out.println("A.insertAfterCurrent(7) = " + A);
      A.getIndex();
      System.out.println("A.getIndex() = " + A.getIndex());
      A.movePrev();
      System.out.println("A.movePrev() + A.getIndex() = " + A.getIndex());
      A.deleteCurrent();
      System.out.println("A.deleteCurrent() = " + A);
      A.deleteFront();
      System.out.println("A.deleteFront()  = " + A);
      System.out.println("A.getLength() = " + A.getLength()); 


   }
}


