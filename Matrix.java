/////////////////////////////////////////////////////////
// Filename: Matrix.java
// Author: Fernando Carrillo (fcarril1)
// Description: 
//////////////////////////////////////////////////////////
import java.io.*;
import java.util.Scanner;
import java.lang.Integer;
import java.lang.Double;

class Matrix{ 

    private class Entry{
        //Fields
        int column;
        double value;

        // Constructor
        Entry( int j, double x ){ this.column = j; this.value = x; }

        //equals function
        //public boolean equals(Entry){return true;}
		
        //toString function.
        public String toString() { return String.valueOf(value); }
    }

    //Fields
    List[] row;
    int NNZ, size = 0;

// Constructor ////////////////////////////////////////////////////////// 

    // Makes a new n x n zero Matrix.  pre: n>=1
    Matrix(int n) {
        NNZ = 0;
        size = n;
        row = new List[n+1];
        for(int i = 1; i <= n; i++){
            row[i] = new List();
        }
    }
 
// Access functions ////////////////////////////////////////////////////

    // Returns n, the number of rows and columns of this Matrix
    int getSize(){
        return size;
    }

    // Returns the number of non-zero entries in this Matrix 
    int getNNZ() {return NNZ;}

    // overrides Object's equals() method 
    public boolean equals(Object x){
        Entry temp = ((Entry)(x));
        //if(this.column == temp.column && this.value == temp.value){return true;}
        //else { return false; }
        return true; 
    }

// Manipulation procedures //////////////////////////////////////////// 

    // sets this Matrix to the zero state 
    void makeZero(){
        for(int i = 1; i < row.length; i++){
            row[i].makeEmpty();
        }
    }

    // returns a new Matrix having the same entries as this Matrix
    Matrix copy(){
        Entry entryTmp;
        Matrix temp = new Matrix( this.row.length );
        temp.size = this.size;
        for(int i = 1; i < row.length; i++){
            row[i].moveTo(0);
            while ( !this.row[i].offEnd()){
                entryTmp = new Entry( ((Entry)(this.row[i].getCurrent())).column, ((Entry)(this.row[i].getCurrent())).value );
                temp.row[i].insertBack(entryTmp);
                temp.NNZ++;
                this.row[i].moveNext();
            }
        }
        return temp;
    }

    // changes ith row, jth column of this Matrix to x 
    // pre: 1<=i<=getSize(), 1<=j<=getSize() 
    void changeEntry(int i, int j, double x){
        if (x == 0){
            if( row[i].getLength() > 0){
                row[i].moveTo(0);
                while( !row[i].offEnd() && ((Entry)(row[i].getCurrent())).column < j){
                    row[i].moveNext();
                }
                if (!row[i].offEnd()){
                    if (((Entry)(row[i].getCurrent())).column == j){
                        row[i].deleteCurrent();
                        NNZ--;
                    }
                }
            }
        }
        if (x != 0){
            Entry tmp = new Entry(j, x);
            if(row[i].getLength() == 0){
                row[i].insertBack(tmp);
                NNZ++;
            }
            else{
                row[i].moveTo(0);
                while( !row[i].offEnd() && ((Entry)row[i].getCurrent()).column < j ){
                    row[i].moveNext();
                }
                if (row[i].offEnd()){
                    row[i].insertBack(tmp);
                    NNZ++;
                }
                else if(j == ((Entry)row[i].getCurrent()).column){
                    row[i].insertBeforeCurrent(tmp);
                    row[i].deleteCurrent();
                }
                else{
                    row[i].insertBeforeCurrent(tmp);
                    NNZ++;
               }
            } 
        }
    }	
	
    // returns a new Matrix that is the scalar product of this Matrix with x 
    Matrix scalarMult(double x){ 
        double tempVal;
        Matrix tmp = copy();
        for(int i = 1; i < row.length; i++){
            tmp.row[i].moveTo(0);
            while ( !tmp.row[i].offEnd()){
                tempVal = ((Entry)(tmp.row[i].getCurrent())).value * x;
                ((Entry)(tmp.row[i].getCurrent())).value = tempVal;
                tmp.row[i].moveNext();
            }
        }
        return tmp;
    }
    
    // returns a new Matrix that is the sum of this Matrix with M 
    // pre: getSize()==M.getSize()
    Matrix add(Matrix M){
        M = M.copy();
        if(this.getSize() != M.getSize()){
            throw new RuntimeException("Matrix Error: add called uneven Matrices");  
        }
        else{
            Matrix tmp = new Matrix(M.getSize());
            for (int i = 1; i <= this.getSize(); i++){
                tmp.row[i] = addSub(this.row[i], M.row[i], 1); 
            }
            return tmp;
        }
    }
	
    // returns a new Matrix that is the difference of this Matrix with M 
    // pre: getSize()==M.getSize()
    Matrix sub(Matrix M){
        M = M.copy();
        if(this.getSize() != M.getSize()){
            throw new RuntimeException("Matrix Error: subtract called uneven Matrices");
        }
        else{
            Matrix tmp = new Matrix(M.getSize());
            for (int i = 1; i <= this.getSize(); i++){
                tmp.row[i] = addSub(this.row[i], M.row[i], -1);
            }
            return tmp;
        }

    }

    //addSub(): Adds or subtracts two lists.
    List addSub(List L, List R, int fun){
        int colTmp, lCol, rCol;
        double valTmp;
        List listTmp = new List();
        Entry entryTmp;
        L.moveTo(0);
        R.moveTo(0);
        while(!L.offEnd() && !R.offEnd()){
            //compare Current of both
            lCol = ((Entry)(L.getCurrent())).column;
            rCol = ((Entry)(R.getCurrent())).column;
            if (lCol < rCol){
                valTmp = ((Entry)(L.getCurrent())).value;
                entryTmp = new Entry(lCol, valTmp);
                listTmp.insertBack(entryTmp);
                L.moveNext();
            }
            else if (rCol < lCol){
                valTmp = ((Entry)(R.getCurrent())).value;
                if(fun==-1)valTmp = -valTmp;  
                entryTmp = new Entry(rCol, valTmp);
                listTmp.insertBack(entryTmp);
                R.moveNext();
            }
            else{
                if( fun == 1){
                    valTmp = ((Entry)(L.getCurrent())).value + ((Entry)(R.getCurrent())).value;
                    entryTmp = new Entry(lCol, valTmp);
                    listTmp.insertBack(entryTmp);
                    L.moveNext();
                    R.moveNext();
                }
                if (fun == -1){
                     valTmp = ((Entry)(L.getCurrent())).value - ((Entry)(R.getCurrent())).value;
                     if (valTmp != 0){
                         entryTmp = new Entry(lCol, valTmp);
                         listTmp.insertBack(entryTmp);
                     }
                     L.moveNext();
                     R.moveNext();
                }                
            }
        }
        while(!L.offEnd()){
            colTmp = ((Entry)(L.getCurrent())).column;
            valTmp = ((Entry)(L.getCurrent())).value;
            entryTmp = new Entry(colTmp, valTmp);
            listTmp.insertBack(entryTmp);
            L.moveNext(); 
        }
        while(!R.offEnd()){
            colTmp = ((Entry)(R.getCurrent())).column;
            valTmp = ((Entry)(R.getCurrent())).value;
            if(fun==-1) valTmp = -valTmp;
            entryTmp = new Entry(colTmp, valTmp);
            listTmp.insertBack(entryTmp);
            R.moveNext();
        }
        return listTmp;
    }
	
    // returns a new Matrix that is the transpose of this Matrix 
    Matrix transpose() {
        int colTmp;
        Double valTmp;
        Matrix tmp = new Matrix(this.getSize());
        for (int i = 1; i <= this.getSize(); i++){
            this.row[i].moveTo(0);
            while(!this.row[i].offEnd()){
                colTmp = ((Entry)(this.row[i].getCurrent())).column;
                valTmp = ((Entry)(this.row[i].getCurrent())).value;
                tmp.changeEntry(colTmp, i , valTmp); 
                this.row[i].moveNext();
            }
        }
        return tmp;
    }   

    // returns a new Matrix that is the product of this Matrix with M 
    // pre: getSize()==M.getSize()
    Matrix mult(Matrix M){
        M = M.transpose();
        int colTmp;
        double valTmp, prod;
        Matrix tmp;
        if(this.getSize() != M.getSize()){
            throw new RuntimeException("Matrix Error: multiply called uneven Matrices");
        }
        else{
            tmp = new Matrix(M.getSize());
            for (int i = 1; i <= this.getSize(); i++){
                for (int j = 1; j <= this.getSize(); j++){
                    prod = dot( this.row[i], M.row[j] );
                    tmp.changeEntry( i, j, prod);
                }
            }
        }
        return tmp;
    }	

    private static double dot(List P, List Q){
        int pCol, qCol;
        double pVal, qVal, sum=0;
        P.moveTo(0);
        Q.moveTo(0);
        while(!P.offEnd() && !Q.offEnd()){
            pCol = ((Entry)(P.getCurrent())).column;
            qCol = ((Entry)(Q.getCurrent())).column;
            pVal = ((Entry)(P.getCurrent())).value;
            qVal = ((Entry)(Q.getCurrent())).value;

            if (pCol < qCol){ P.moveNext(); }
            else if (qCol < pCol){ Q.moveNext(); }
            else{
                sum += pVal*qVal;
                P.moveNext();
                Q.moveNext(); 
            }
        }
        return sum;
    } 
    
// Other functions /////////////////////////////////////////////////////////////// 
    // overrides Object's toString() method
    public String toString(){
        String tmp = new String("");
        for(int i = 1; i < row.length; i++){
            if (row[i].getLength() > 0){
                tmp = tmp + String.valueOf(i) + ":";
                row[i].moveTo(0);
                while(!row[i].offEnd()){
                    tmp = tmp + " (" + String.valueOf(((Entry)(row[i].getCurrent())).column);
                    tmp = tmp + ", " + String.valueOf(((Entry)(row[i].getCurrent())).value)+ ")";
                    row[i].moveNext();
                }
                tmp = tmp + "\n";
            }
        }
        return tmp;
    }
}
