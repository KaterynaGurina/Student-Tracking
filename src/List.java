// A simplified version of the java.util.List interface. 
//from the book


public interface List {
		
	//Returns the number of elements in this list.
	int size( );
	
	//Returns whether the list is empty.
	boolean isEmpty( );
	
	//Returns (but does not remove) the element at index i.
	String get(int i) throws IndexOutOfBoundsException;
	
	//Replaces the element at index i with e, and returns the replaced element.
	String set(int i, int j, String s) throws IndexOutOfBoundsException;
	
	//Inserts element e to be at index i, shifting all subsequent elements later.
	void add(int i, int j, String s) throws IndexOutOfBoundsException;
	
	//Removes/returns the element at index i, shifting subsequent elements earlier.
	String removeIndex(int i) throws IndexOutOfBoundsException;
	 
	}
