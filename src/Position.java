

public interface Position {
		
	/*
	 * Returns the element stored at this position.
	 * 
	 * @return the stored element
	 * 
	 * @throws IllegalStateException if position no longer valid
	 */
	int getKey( ) throws IllegalStateException;
	
	String getValue( ) throws IllegalStateException;
	
}
