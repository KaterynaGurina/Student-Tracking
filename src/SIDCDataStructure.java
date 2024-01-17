import java.util.ArrayList;

public interface SIDCDataStructure<E> {
	
    int size();

    // Method to remove an entry by key
	String removeKey(int key) throws IllegalArgumentException;

    // Method to get a value by key
	String getValues(int key);

	//Find the position by the specified key
  	Position positionAtKey(int key)throws IllegalArgumentException;
  	
  	//Return the key of the node of the next position
  	int nextKey(int key) throws IllegalArgumentException;
  	 
   	//Return the key of the node of the prev position
  	int prevKey(int key) throws IllegalArgumentException;
	
	ArrayList<Position> getAllSortedPositions();
	
	//Returns the number of keys that are within the specified range of two arguments
	int rangeKey(int key1, int key2);
	
	int generateUniqueKey();

	void add(int key, String value);
	 
}