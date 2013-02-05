// MatrixTest.java
// A test client for the Matrix ADT

class MatrixTest{
   public static void main(String[] args){
      Double sum = 1.0;
      Matrix A = new Matrix(3);
      Matrix B = null; 
      Matrix T = null;
	  Matrix S = null;

      for (int i = 1; i <= A.getSize(); i++){
         for (int j = 1; j <= A.getSize(); j++){	
            //Inserts value into listA at index[column].
            A.changeEntry( i, j, sum);
			sum++;
         }			
      }
	  System.out.println("A = \n" + A);
	  
	  B = A.copy();
	  System.out.println("B = A.copy()\n" + A);
	 
	  B.changeEntry(2, 2, 50.0);
	   System.out.println("B.changeEntry(2, 2, 50.0) = \n" + B);
	  
	  T = A.transpose();
      System.out.println("T = A.transpose()\n" + T);
	  
	  System.out.println("A.add(T)= \n" + A.add(T));
	  
	  System.out.println("A.sub(A) = \n" + A.sub(A));
	  
	  System.out.println("B.sub(A) = \n" + B.sub(A));
	  
	  S = A.scalarMult(3.0);
	  System.out.println("S = A.scalarMult(3.0)=\n" + S);
      
	  T.makeZero();
	  System.out.println("T.makeZero()=" + T);

	  /*
      for(int i=1; i<=6; i++){
         A.insertFront(B.getBack());
         B.deleteBack();
      }

      System.out.println("A.insertFront() of last 6 elements of B  = " + A);
      System.out.println("B.deleteBack() * 6 = " + B);

      C = A.copy();
      System.out.println("C = A.copy() = " + C);

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
      */

   }
}

