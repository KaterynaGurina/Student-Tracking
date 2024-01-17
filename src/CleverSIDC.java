import java.util.ArrayList;

public class CleverSIDC {
    
    public int threshold;
    public Object dataStructure; // Now just an Object since AVLPositionalList is not generic

    public CleverSIDC() {
        this.threshold = 1000; // Default threshold
        dataStructure = new LinkedPositionalList(); // Initial data structure
    }

    public void setSIDCThreshold(int size) {
        if (size < 100 || size > 500000) {
            throw new IllegalArgumentException("Size must be between 100 and 500,000");
        }
        this.threshold = size;
        checkAndSwitchStructure();
    }

    private void migrateData(SIDCDataStructure<String> fromStructure, SIDCDataStructure<String> toStructure) {
        ArrayList<Position> positions = fromStructure.getAllSortedPositions();
        for (Position position : positions) {
            int key = position.getKey();
            String value = position.getValue();
            toStructure.add(key, value);
        }
    }
    
    public ArrayList<Position> getAllSortedPositions() {
        if (dataStructure instanceof LinkedPositionalList) {
            return ((LinkedPositionalList)dataStructure).getAllSortedPositions();
        } else if (dataStructure instanceof AVLPositionalList) {
            return ((AVLPositionalList)dataStructure).getAllSortedPositions();
        } else {
            throw new IllegalStateException("Data structure is not recognized.");
        }
    }


    @SuppressWarnings("unchecked")
    private void checkAndSwitchStructure() {
        // If current data structure is LinkedPositionalList and size exceeds threshold, switch to AVLPositionalList
        if (dataStructure instanceof LinkedPositionalList && ((LinkedPositionalList)dataStructure).size() >= threshold) {
            AVLPositionalList newStructure = new AVLPositionalList();
            migrateData((SIDCDataStructure<String>) dataStructure, newStructure);
            dataStructure = newStructure;
            System.out.println("Switched structures from Linked Positional List to AVL Positional List");
        } 
        // If current data structure is AVLPositionalList and size is below threshold, switch to LinkedPositionalList
        else if (dataStructure instanceof AVLPositionalList && ((AVLPositionalList)dataStructure).size() < threshold) {
            LinkedPositionalList newStructure = new LinkedPositionalList();
            migrateData((SIDCDataStructure<String>) dataStructure, newStructure);
            dataStructure = newStructure;
            System.out.println("Switched structures from AVL Positional List to Linked Positional List");
        }
    }

    public void add(int key, String value) {
        if (dataStructure instanceof SIDCDataStructure) {
            ((SIDCDataStructure<String>)dataStructure).add(key, value);
            checkAndSwitchStructure();
        } else {
            throw new IllegalStateException("Data structure is not an instance of SIDCDataStructure");
        }
    }

    public String remove(int key) {
        if (dataStructure instanceof SIDCDataStructure) {
            String value = ((SIDCDataStructure<String>)dataStructure).removeKey(key);
            checkAndSwitchStructure();
            return value;
        } else {
            throw new IllegalStateException("Data structure is not an instance of SIDCDataStructure");
        }
    }

    public String getValues(int key) {
        if (dataStructure instanceof SIDCDataStructure) {
            return ((SIDCDataStructure<String>)dataStructure).getValues(key);
        } else {
            throw new IllegalStateException("Data structure is not an instance of SIDCDataStructure");
        }
    }

    public int generateUniqueKey() {
        if (dataStructure instanceof SIDCDataStructure) {
            return ((SIDCDataStructure<String>)dataStructure).generateUniqueKey();
        } else {
            throw new IllegalStateException("Data structure is not an instance of SIDCDataStructure");
        }
    }
}
