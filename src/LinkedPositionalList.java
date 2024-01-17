import java.util.ArrayList;
import java.util.Random;

public class LinkedPositionalList implements PositionalList,List, SIDCDataStructure{
	
    //---------------- nested Node class ----------------
        
    private static class Node implements Position{
        private int key;  // SIDC - 8-digit long integer
        private String value; // Student information as a String (e.g., "John Doe 01/01/2000")
        private Node prev;
        private Node next;

        public Node(int key, String value, Node prev, Node next) {
            this.key = key;
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
        public int getKey() {
            if (next == null) // convention for defunct node
                throw new IllegalStateException("Position no longer valid");
            return key;
        }
        public String getValue( ) throws IllegalStateException {
            if (next == null) // convention for defunct node
                throw new IllegalStateException("Position no longer valid");
            return value;
        }
        public Node getPrev( ) {
            return prev;
        }
        public Node getNext( ) {
            return next;
        }
        public void setElement (int k, String s) {
        	key = k;
        	value = s;
        }
        public void setPrev(Node p) {
            prev = p;
        }
        public void setNext(Node n) {
            next = n;
        }
        
        public String toString() {
            return "Key: " + key + ", Value: " + value;
        }
    } //----------- end of nested Node class -----------
    

    // instance variables of the LinkedPositionalList
    private Node header; // header sentinel
    private Node trailer; // trailer sentinel
    private int size = 0; // number of elements in the list

    /** Constructs a new empty list. */
    public LinkedPositionalList( ) {
        header = new Node(-1, null, null, null); // create header
        trailer = new Node(-1, null, header, null); // trailer is preceded by header
        header.setNext(trailer); // header is followed by trailer
    }
    
    // private utilities
    /** Validates the position and returns it as a node. */
    private Node validate(Position p) throws IllegalArgumentException {
        if (!(p instanceof Node)) throw new IllegalArgumentException("Invalid p");
        Node node = (Node) p; // safe cast
        if (node.getNext( ) == null) // convention for defunct node
            throw new IllegalArgumentException("p is no longer in the list");
        return node;
    }

    /** Returns the given node as a Position (or null, if it is a sentinel). */
    private Position position(Node node) {
        if (node == header || node == trailer)
            return null; // do not expose user to the sentinels
        return node;
    }

    // public accessor methods
    /** Returns the number of elements in the linked list. */
    public int size( ) { 
    	return size; }

    /** Tests whether the linked list is empty. */
    public boolean isEmpty( ) { 
    	return size == 0; }

    /** Returns the first Position in the linked list (or null, if empty). */
    public Position first( ) {
        return position(header.getNext( ));
    }

    /** Returns the last Position in the linked list (or null, if empty). */
    public Position last( ) {
        return position(trailer.getPrev( ));
    }

    /** Returns the Position immediately before Position p (or null, if p is first). */
    public Position before(Position p) throws IllegalArgumentException {
        Node node = validate(p);
        return position(node.getPrev( ));
    }

    /** Returns the Position immediately after Position p (or null, if p is last). */
    public Position after(Position p) throws IllegalArgumentException {
        Node node = validate(p);
        return position(node.getNext( ));
    }
    // private utilities
    /** Adds element e to the linked list between the given nodes. */
    private Position addBetween(int key, String value, Node pred, Node succ) {
        Node newest = new Node(key, value, pred, succ); // create and link a new node
        pred.setNext(newest);
        succ.setPrev(newest);
        size++;
        return newest;
    }
	
    // public update methods
    /** Inserts element e at the front of the linked list and returns its new Position. */
    public Position addFirst(int key, String value) {
        return addBetween(key, value, header, header.getNext( )); // just after the header
    }

    /** Inserts element e at the back of the linked list and returns its new Position. */
    public Position addLast(int key, String value) {
        return addBetween(key, value, trailer.getPrev( ), trailer); // just before the trailer
    }

    /** Inserts element e immediately before Position p, and returns its new Position.*/
    public Position addBefore(Position p, int key, String value)
    throws IllegalArgumentException {
        Node node = validate(p);
        return addBetween(key, value, node.getPrev(), node);
    }

    /** Inserts element e immediately after Position p, and returns its new Position. */
    public Position addAfter(Position p, int key, String value) throws IllegalArgumentException {
        Node node = validate(p);
        return addBetween(key, value, node, node.getNext());
    }

    /** Replaces the element stored at Position p and returns the replaced element. */
    public String set(Position p, int key, String value) throws IllegalArgumentException {
        Node node = validate(p);
        String answer = node.getValue( );
        node.setElement(key, value);
        return answer;
    }
    /** Removes the element stored at Position p and returns it (invalidating p). */
    public String removePosition(Position p) throws IllegalArgumentException {
        Node node = validate(p);
        Node predecessor = node.getPrev( );
        Node successor = node.getNext( );
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        String answer = node.getValue( );
        node.setElement(0, null); // help with garbage collection
        node.setNext(null); // and convention for defunct node
        node.setPrev(null);
        return answer;
    }
    /** Returns the position of the element having index i*/
	public Position positionAtIndex(int i) throws IndexOutOfBoundsException {
		if (i < 0 || i >= size)throw new IndexOutOfBoundsException("Index out of Bounds");
		int index = 0;
		Node cursor = null;
		if (i < size/2) {
			cursor = header.getNext();
			while (index!= i) {
				cursor = cursor.getNext();
				index++;
			}
		}
		else {
			index = size - 1;
			cursor = trailer.getPrev();
			while (index != i) {
				cursor = cursor.getPrev();
				index--;
			}
		}
		return position(cursor);
	}
	
	/* Returns index of the element at the postion p  */
	public int indexOf(Position p) throws IllegalArgumentException {
        Node node = validate(p);
        Node cursor = header.getNext();
		int index = 0;
		while(cursor != node) {
			cursor = cursor.next;
			index++;
		}
		return index;
	}
	
	//Returns (but does not remove) the element at index i.
	public String get(int i) throws IndexOutOfBoundsException{
		if (i < 0 || i >= size)throw new IndexOutOfBoundsException("Index out of Bounds");
        Node node = validate(positionAtIndex(i));
        return node.getValue( );
	}
	
	//Replaces the element at index i with e, and returns the replaced element.
	public String set(int i, int key, String value) throws IndexOutOfBoundsException{
		if (i < 0 || i >= size)throw new IndexOutOfBoundsException("Index out of Bounds");
        Node node = validate(positionAtIndex(i));
        String answer = node.getValue( );
        node.setElement(key, value);
        return answer;
	}
	
	//Inserts element e to be at index i, shifting all subsequent elements later.	
	public void add(int i, int key, String value) throws IndexOutOfBoundsException {
	    if (i < 0 || i > size) throw new IndexOutOfBoundsException("Index out of Bounds");
	    
	    if (i == size) {
	        addLast(key, value);
	    } 
	    else {
	        Node node = validate(positionAtIndex(i));
	        addBetween(key, value, node.getPrev(), node);
	    }
	}
	
	//Removes/returns the element at index i,
	public String removeIndex(int i) throws IndexOutOfBoundsException{
		if (i < 0 || i >= size)throw new IndexOutOfBoundsException("Index out of Bounds");
		Node node = validate(positionAtIndex(i));
	    Node predecessor = node.getPrev( );
	    Node successor = node.getNext( );
	    predecessor.setNext(successor);
	    successor.setPrev(predecessor);
	    size--;
	    String answer = node.getValue( );
	    node.setElement(0, null); // help with garbage collection
	    node.setNext(null); // and convention for defunct node
	    node.setPrev(null);
	    return answer;    
	}
	
	//Find the position by the specified key
	public Position positionAtKey(int i)throws IllegalArgumentException{
		Node cursor = header.getNext();
		while(cursor.getKey()!= i) {
			cursor = cursor.getNext();
	        if (cursor.getNext( ) == null) // convention for defunct node
	            throw new IllegalArgumentException("p is no longer in the list");
		}
		return (Position)cursor;
	}
	
	public String getValues(int key) {
		return positionAtKey(key).getValue();
	}
	
	//Removes/returns the element by its key
	public String removeKey(int key) throws IllegalArgumentException{
		Node node = validate(positionAtKey(key));
	    Node predecessor = node.getPrev( );
	    Node successor = node.getNext( );
	    predecessor.setNext(successor);
	    successor.setPrev(predecessor);
	    size--;
	    String answer = node.getValue( );
	    node.setElement(0, null); // help with garbage collection
	    node.setNext(null); // and convention for defunct node
	    node.setPrev(null);
	    return answer;
	}
	
  	//Return the key of the node of the next position
	public int nextKey(int key) throws IllegalArgumentException {
		Node answer = validate(positionAtKey(key)).getNext();
		return answer.getKey();
	}

   	//Return the key of the node of the prev position
	public int prevKey(int key) throws IllegalArgumentException {
		Node answer = validate(positionAtKey(key)).getPrev();
		return answer.getKey();
	}
	
	private ArrayList<Position> mergeSortPositions(ArrayList<Position> list) {
	    if (list.size() <= 1) {
	        return list;
	    }

	    // Splitting list into two halves
	    int mid = list.size() / 2;
	    ArrayList<Position> left = new ArrayList<>(list.subList(0, mid));
	    ArrayList<Position> right = new ArrayList<>(list.subList(mid, list.size()));

	    left = mergeSortPositions(left);
	    right = mergeSortPositions(right);

	    return mergePositions(left, right);
	}

	private ArrayList<Position> mergePositions(ArrayList<Position> left, ArrayList<Position> right) {
	    ArrayList<Position> result = new ArrayList<>();
	    int leftIndex = 0, rightIndex = 0;

	    // Merging two halves based on the key
	    while (leftIndex < left.size() && rightIndex < right.size()) {
	        Node leftNode = (Node) left.get(leftIndex);
	        Node rightNode = (Node) right.get(rightIndex);

	        if (leftNode.getKey() < rightNode.getKey()) {
	            result.add(left.get(leftIndex++));
	        } else {
	            result.add(right.get(rightIndex++));
	        }
	    }

	    // Copying remaining elements
	    result.addAll(left.subList(leftIndex, left.size()));
	    result.addAll(right.subList(rightIndex, right.size()));

	    return result;
	}

	public ArrayList<Position> getAllSortedPositions() {
	    ArrayList<Position> positions = new ArrayList<>();
	    Node current = header.getNext();
	    while (current != trailer) {
	        positions.add(current);
	        current = current.getNext();
	    }
	    return mergeSortPositions(positions);
	}
	
	public int rangeKey(int key1, int key2) {
	    ArrayList<Position> sortedPositions = getAllSortedPositions();
	    int count = 0;

	    for (Position pos : sortedPositions) {
	        Node node = (Node) pos; // Assuming Position can be safely cast to Node
	        int key = node.getKey();
	        if (key >= key1 && key <= key2) {
	            count++;
	        }
	    }
	    return count;
	}
	
	public int generateUniqueKey() {
        Random random = new Random();
        int newKey;

        do {
            newKey = 10000000 + random.nextInt(90000000); // Generate a random 8-digit number
        } while (keyExists(newKey)); // Check if the key already exists in the list

        return newKey;
    }

    // Helper method to check if a key exists in the list
    private boolean keyExists(int key) {
        Node current = header.getNext();
        while (current != trailer) {
            if (current.getKey() == key) {
                return true; // Key found
            }
            current = current.getNext();
        }
        return false; // Key not found
    }

	@Override
	public void add(int key, String value) {
		addLast(key,value);
		
	}
	
}
