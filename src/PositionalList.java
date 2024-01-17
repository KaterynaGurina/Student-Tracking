
/** An interface for positional lists. */

public interface PositionalList{

    /** Returns the number of elements in the list. */
    int size();

    /** Tests whether the list is empty. */
    boolean isEmpty();

    /** Returns the first Position in the list (or null, if empty). */
    Position first();

    /** Returns the last Position in the list (or null, if empty). */
    Position last();

    /** Returns the Position immediately before Position p (or null, if p is first). */
    Position before(Position p) throws IllegalArgumentException;

    /** Returns the Position immediately after Position p (or null, if p is last). */
    Position after(Position p) throws IllegalArgumentException;

    /** Inserts element e at the front of the list and returns its new Position. */
    Position addFirst(int i, String s);

    /** Inserts element e at the back of the list and returns its new Position. */
    Position addLast(int i, String s);

    /** Inserts element e immediately before Position p and returns its new Position. */
    Position addBefore(Position p, int i, String s) throws IllegalArgumentException;

    /** Inserts element e immediately after Position p and returns its new Position. */
    Position addAfter(Position p, int i, String s) throws IllegalArgumentException;

    /** Replaces the element stored at Position p and returns the replaced element. */
    String set(Position p, int i, String s) throws IllegalArgumentException;

    /** Removes the element stored at Position p and returns it (invalidating p). */
    String removePosition(Position p) throws IllegalArgumentException;
    
    /** Returns the position of the element having index i*/
    Position positionAtIndex(int i) throws IndexOutOfBoundsException;  	
	
    /**Returns the index of the element at position p*/
    int indexOf(Position p);

}
