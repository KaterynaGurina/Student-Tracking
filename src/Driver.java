
public class Driver {
	public static void main(String[] args) {
		LinkedPositionalList ln = new LinkedPositionalList();
		ln.addFirst(40188793, "Kateryna Gurina 12/06/1998");

		System.out.println(ln.first());
		
		int key1 = ln.generateUniqueKey();
		ln.addLast(key1, " James Plank 34/08/1964");		
		int key2 = ln.generateUniqueKey();
		ln.addLast(key2, "Willow Smith 87/65/1443");		
		int key3 = ln.generateUniqueKey();
		ln.addLast(key3, "France Ferdinant 11/12/1889");		
		int key4 = ln.generateUniqueKey();
		ln.addLast(key4, "Dostoevski 99/86/3532");		
		int key5 = ln.generateUniqueKey();
		ln.addLast(key5, "Neil DeGrace Tayson 22/33/4444");		
		int key6 = ln.generateUniqueKey();
		ln.addLast(key6, "I dont know how to spell famous peoples names 11/22/3333");		
		int key7 = ln.generateUniqueKey();
		ln.addLast(key7, "Me Myself And I 33/44/5555");		
		int key8 = ln.generateUniqueKey();
		ln.addLast(key8, "Bred Pitt 66/77/8888");		
		int key9 = ln.generateUniqueKey();
		ln.addLast(key9, "Blablabla 3597238765");		
		System.out.println("All Sorted Positions:");
		
        for (Position pos : ln.getAllSortedPositions()) {
            System.out.println(pos); // pos.toString() will be called automatically
        }
        
        AVLPositionalList lst = new AVLPositionalList();
        lst.add(key1, "Poauwbef ojsegrb 9584769");
        lst.add(key2, "hetherth ojsegrb 9584769");
        lst.add(key3, "rete ojsegrb 9584769");
        lst.add(key4, "Poauwbegffff ojsegrb 9584769");
        lst.add(key5, "ddd rr 9584769");
        lst.add(key6, "Poauwggggbef f 9584769");
        lst.add(key7, "eeee dd 9584769");
        lst.add(key8, "hhh jjj 9584769");
        
        for (Position pos : lst.getAllSortedPositions()) {
            System.out.println(pos); // pos.toString() will be called automatically
        }
        
	}
}
