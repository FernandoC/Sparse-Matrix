/////////////////////////////////////////////////////////
// Filename: List.java
// Author: Fernando Carrillo (fcarril1)
// Assignment: pa3
// Description: Double ended Linked List of Objects
//////////////////////////////////////////////////////////

class List{

    private class Node{
        //Fields
        Object data;
        Node next;
        Node prev;
       
        //Constructor
        Node(Object data) { this.data = data; next = null; prev = null;}
 
        //toString:
        public String toString() {return String.valueOf(data); }
    }

    //Fields
    private Node front;
    private Node back;
    private Node current;
    private int length;
   
    //Constructor
    List() { front = back = current = null; length = 0; }

    // Acess Functions /////////////////////////////////////////////////////////////

    //getLength(): returns length of the list
    // Pre: !this.isEmpty()
    int getLength(){ return length; }

    //isEmpty(): returns true if this is an empty list, false otherwise
    boolean isEmpty() { return length==0; }

    //offEnd(): Returns true if current is undefined
    boolean offEnd(){ return current==null; }

    //getIndex(): Returns position of current in the list if element is defined
    //if element is not defined returns -1.
    int getIndex(){
        if (current == null){return -1;}
        int index;
        Node temp = front;
        for( index = 1; index < length ; index++){
            if( temp == current ){return index;}
            else { temp = temp.next; }
        }
        return index;
    }
    

    //getFront(): returns front element of this list
    // Pre: !this.isEmpty()
    Object getFront(){
        if( this.isEmpty() ) {
            throw new RuntimeException("List error: getFront() called on empty List");
        }
        return front.data;
    }

    //getBack(): returns back element of this list
    //Pre: !this.isEmpty()
    Object getBack(){
        if( this.isEmpty() ) {
            throw new RuntimeException("List error: getBack() called on empty List");
        }
        return back.data;
    }
   
    //getCurrent(): returns current element of this list
    //Pre: !this.isEmpty()
    Object getCurrent(){
        if( this.isEmpty() ) {
            throw new RuntimeException("List error: getCurrent() called on empty List");
        }
        return current.data;
    }

/*
    //equals(Object x): Returns true if this List and L are the same integer sequence
    //Ignores the current element in both Lists.
    public boolean equals(Object x){
        boolean flag = true;        
        Node N = this.front;
        Node M = L.front;

        if( this.length==L.length ){
            while ( flag && N!=null){
                flag = (N.data==M.data);
                N = N.next;
                M = M.next;
            }
            return flag;
        }else{
            return false;
        }
    }
   */
    // Manipulation Procedures ///////////////////////////////////////////////////////
    
    //makeEmpty(): Sets this List to the empty state.
    //Post: isEmpty()
    void makeEmpty(){
        if(!this.isEmpty()){
            this.front = this.back = this.current = null;
            length = 0;
        }
    }

    //moveTo(int i): If 0 <= i <= getLength()-1, moves current element
    //marker to position i in this List. Otherwise current is undefined.
    void moveTo(int i){
        current = front;
        for (int j = 0; j < i; j++){
            moveNext();
        }
    }

    //movePrev(): //Moves current one step toward front element.
    //Pre: !isEmpty(), !offEnd().
    void movePrev(){
        if( this.isEmpty() ) {
            throw new RuntimeException("List error: movePrev() called on empty List");
        }
        else if( this.offEnd() ) {
            throw new RuntimeException("List error: movePrev() called on null current");
        }
        else{
            current = current.prev;
        }
    }

    //moveNext(): //Moves current one step toward back element.
    //Pre: !isEmpty(), !offEnd().
    void moveNext(){
        if( this.isEmpty() ) {
            throw new RuntimeException("List error: moveNext() called on empty List");
        }
        else if( this.offEnd() ) {
            throw new RuntimeException("List error: moveNext() called on undefine current");
        }
        else{
            current = current.next;
        }
    }

    //insertFront(Object data): Inserts new element before the front element.
    //Post: !isEmpty()
    void insertFront(Object data){
        Node node = new Node(data);
        if( this.isEmpty() ) { front = back = node; }
        else { node.next = front; front.prev = node; front = node; }
        length++;
    }

    //insertBack(Object data): Inserts new element after the back element.
    //Post: !isEmpty().
    void insertBack(Object data){
        Node node = new Node(data);
        if( this.isEmpty() ) { front = back = node; }
        else { back.next = node; node.prev = back; back = node; }
        length++;
    }

    //insertBeforeCurrent (Object data): Inserts new element before current element.
    // Pre: !isEmpty(), !offEnd()
    void insertBeforeCurrent(Object data){
        if( !isEmpty() && !offEnd() ){
            if(getIndex()==1){
               insertFront(data);
               return;
            }
            else{
                Node node = new Node(data);
                current.prev.next = node;
                node.prev = current.prev;
                node.next = current;
                current.prev = node;
            }
        }
        length++;
        
    }

    //insertAfterCurrent (Object data): Inserts new element after current element.
    // Pre: !isEmpty(), !offEnd()
    void insertAfterCurrent(Object data){
        if( !isEmpty() && !offEnd() ){
            if(getIndex()==length){
                insertBack(data);
                return;
            }
            else{
                Node node = new Node(data);
                current.next.prev = node;
                node.next = current.next;
                current.next = node;
                node.prev = current;
            }
        }
        length++;
    }

    //deleteFront(): Deletes front element.
    //Pre: !isEmpty().
    void deleteFront(){
        if(this.isEmpty()){
            throw new RuntimeException("List Error: deleteFront called on empy List");
        }
        if(this.length > 1){front = front.next; front.prev = null;}
        else {front = back = null;}
        length--;
    }

    //deleteBack(): Deletes back element.
    //Pre: !isEmpty().
    void deleteBack(){
        if(this.isEmpty()){
            throw new RuntimeException("List Error: deleteBack called on empy List");
        }
        if(this.length > 1){back = back.prev; back.next = null;}
        else {front = back = null;}   
        length--;
    }

    //deleteCurrent(): Deletes current element.
    //Pre: !isEmpty(), !offEnd().
    //Post: offEnd().
    void deleteCurrent(){
        if(this.isEmpty()){
            throw new RuntimeException("List Error: deleteCurrent called on empy List");
        }
        if(this.length > 1){
            current.prev.next = current.next;
            current.next.prev = current.prev;
            current.next = current.prev = null;
        }
        else {front = back = current = null;}   
        length--;
    }


    // Other Functions /////////////////////////////////////////////////

    //toString(): overides Object's toString() method.
    public String toString(){
        String str = " ";
        for(Node N=front; N!=null; N=N.next){
            str += N.toString() + " ";
        }
        return str;
    }
    
}
