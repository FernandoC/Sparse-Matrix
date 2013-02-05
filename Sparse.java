/////////////////////////////////////////////////////////////////////////
// Filename: Sparse.java
// Author: Fernando Carrillo (fcarril1)
// Class: CMPS 101 Patrick Tantalo
// Assignment: pa3
// Description: Reads in an input file parses it and turns information
//     into two matrices. The matrix is essentially an array of doubly 
//     LinkedLists. Sparce.java then procedes to add, subtact, multiply
//     them to each other. The program can also transpose matrices or
//     apply a scalar multiplication. It then prints these results to 
//     an out file.  
/////////////////////////////////////////////////////////////////////////
import java.io.*;
import java.util.Scanner;
import java.lang.Integer;
import java.lang.Double;

class Sparse{
    public static void main(String[] args) throws IOException{
        Scanner in = null;
        PrintWriter out = null;
        String line = null, print = null;
        String[] token = null;
        int i=0, n=0, a=0, b=0;
        int row, column, value;
        double number;
        Matrix A, B;

        if(args.length < 2){
            System.out.println("Usage: Sparse infile outfile");
            System.exit(1);
        }

        in = new Scanner(new File(args[0]));
        out = new PrintWriter(new FileWriter(args[1]));

        // Gets n, a and b from the first line in the file.
        if( in.hasNextLine() ){
            line = in.nextLine()+" ";
            token = line.split("\\s+");
            n = Integer.parseInt(token[0]);
            a = Integer.parseInt(token[1]);
            b = Integer.parseInt(token[2]);
        }
		
        // Creates an n length array to hold List
        A = new Matrix(n);
        B = new Matrix(n);
		 
	//Reads blank line
	line = in.nextLine()+" ";
		 
	// Parses array A by reading in the next a lines. 
	for ( i = 0; i < a; i++){
            //reads in the variables every line.
            line = in.nextLine()+" ";
            token = line.split("\\s+");
            row = Integer.parseInt(token[0]);
            column = Integer.parseInt(token[1]);
            number = Double.parseDouble(token[2]);	

            //Inserts value into listA at index[column].
            A.changeEntry( row, column, number);			
        }

        //Reads blank line 
        line = in.nextLine()+" ";	
	
        // Parses array B by reading in the next b lines.
        for ( i = 0; i < b; i++){
            //reads in the variables every line.
            line = in.nextLine()+" ";
            token = line.split("\\s+");
            row = Integer.parseInt(token[0]);
            column = Integer.parseInt(token[1]);
            number = Double.parseDouble(token[2]);	  		
	 
           //Inserts value into B at index[column].
           B.changeEntry( row, column, number);
        }

        //Prints out A
        out.println("A has " + A.NNZ + " non-zero entries:");
        out.println(A);

        //Prints out B
        out.println("B has " + B.NNZ + " non-zero entries:");
        out.println(B);

        //Prints out (1.5)*A
        out.println("(1.5)*A =");
        out.println(A.scalarMult(1.5));

        //Prints out A+B  
        out.println("A+B =");
        out.println(A.add(B));

        //Prints out A+A  
        out.println("A+A =");
        out.println(A.add(A));

        //Prints out B-A  
        out.println("B-A =");
        out.println(B.sub(A));

        //Prints out A-A  
        out.println("A-A =");
        out.println(A.sub(A));

        //Prints out Transpose(A)  
        out.println("Transpose(A) =");
        out.println(A.transpose());

        //Prints out A*B  
        out.println("A*B =");
        out.println(A.mult(B));

        //Prints out B*B  
        out.println("B*B =");
        out.print(B.mult(B));		
		
        in.close();
        out.close();
    }
}
