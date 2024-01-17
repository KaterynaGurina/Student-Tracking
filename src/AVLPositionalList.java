import java.util.ArrayList;
import java.util.Random;

public class AVLPositionalList implements SIDCDataStructure {
    private class AVLNode {
        int key;
        String value;
        AVLNode left, right;
        int height;

        AVLNode(int key, String value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }
        public String toString() {
            return "Key: " + key + ", Value: " + value;
        }
    }
    
    // Inner class AVLPosition to encapsulate the position concept
    private class AVLPosition implements Position {
        private int key; // Encapsulated key of the node
        private String value; // Encapsulated value of the node

        AVLPosition(int key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int getKey() throws IllegalStateException {
            return key;
        }

        @Override
        public String getValue() throws IllegalStateException {
            return value;
        }
        
        public String toString() {
            return "Key: " + key + ", Value: " + value;
        }
    }
    
    private AVLNode root;

    public AVLPositionalList() {
        this.root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(AVLNode node) {
        if (node == null) return 0;
        return size(node.left) + 1 + size(node.right);
    }

    @Override
    public String getValues(int key) {
        AVLNode node = getNode(root, key);
        return node != null ? node.value : null;
    }

    @Override
    public int generateUniqueKey() {
        Random random = new Random();
        int newKey;
        do {
            newKey = 10000000 + random.nextInt(90000000); // Generate a random 8-digit number
        } while (getNode(root, newKey) != null); // Check if the key already exists
        return newKey;
    }

    private AVLNode getNode(AVLNode node, int key) {
        if (node == null) {
            return null;
        }
        if (key < node.key) {
            return getNode(node.left, key);
        } 
        else if (key > node.key) {
            return getNode(node.right, key);
        } 
        else {
            return node;
        }
    }

    private AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }
    
    private AVLNode maxValueNode(AVLNode node) {
        AVLNode current = node;
        while (current.right != null) {
            current = current.right;
        }
        return current;
    }
    

    public ArrayList allKeys() {
    	ArrayList keys = new ArrayList<Integer>();
        inOrderTraversal(root, keys);
        return keys;
    }

    private void inOrderTraversal(AVLNode node, ArrayList keys) {
        if (node == null) return;
        inOrderTraversal(node.left, keys);
        keys.add(node.key);
        inOrderTraversal(node.right, keys);
    }

    @Override
    public int nextKey(int key) {
        AVLNode node = getNode(root, key);
        if (node == null) {
            return -1; // Key not found
        }
        if (node.right != null) {
            AVLNode minNode = minValueNode(node.right);
            return minNode.key;
        } else {
            AVLNode successor = null;
            AVLNode ancestor = root;
            while (ancestor != node) {
                if (node.key < ancestor.key) {
                    successor = ancestor;
                    ancestor = ancestor.left;
                } else {
                    ancestor = ancestor.right;
                }
            }
            return successor != null ? successor.key : -1;
        }
    }

    @Override
    public int prevKey(int key) {
        AVLNode node = getNode(root, key);
        if (node == null) {
            return -1; // Key not found
        }
        if (node.left != null) {
            AVLNode maxNode = maxValueNode(node.left);
            return maxNode.key;
        } else {
            AVLNode predecessor = null;
            AVLNode ancestor = root;
            while (ancestor != node) {
                if (node.key > ancestor.key) {
                    predecessor = ancestor;
                    ancestor = ancestor.right;
                } else {
                    ancestor = ancestor.left;
                }
            }
            return predecessor != null ? predecessor.key : -1;
        }
    }


    @Override
    public int rangeKey(int key1, int key2) {
        ArrayList<Integer> keys = allKeys();
        int count = 0;
        for (int key : keys) {
            if (key >= key1 && key <= key2) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String removeKey(int key) throws IllegalArgumentException {
        AVLNode[] removedNodeWrapper = new AVLNode[]{null};
        root = deleteNode(root, key, removedNodeWrapper);
        if (removedNodeWrapper[0] == null) {
            throw new IllegalArgumentException("Key not found: " + key);
        }
        return removedNodeWrapper[0].value;
    }

    private int height(AVLNode N) {
        if (N == null)
            return 0;
        return N.height;
    }

    private int getBalance(AVLNode N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    private AVLNode deleteNode(AVLNode node, int key, AVLNode[] removedNodeWrapper) {
        if (node == null) return null;

        if (key < node.key) {
            node.left = deleteNode(node.left, key, removedNodeWrapper);
        } else if (key > node.key) {
            node.right = deleteNode(node.right, key, removedNodeWrapper);
        } else {
            removedNodeWrapper[0] = node; // Save the node to be removed

            if (node.left == null || node.right == null) {
                AVLNode temp = (node.left != null) ? node.left : node.right;
                if (temp == null) {
                    node = null;
                } else {
                    node = temp; // Copy the non-null child
                }
            } else {
                AVLNode temp = minValueNode(node.right);
                node.key = temp.key;
                node.value = temp.value;
                node.right = deleteNode(node.right, temp.key, new AVLNode[1]);
            }
        }

        if (node == null) return node;

        // Update height and balance the tree
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        int balance = getBalance(node);

        // Balance the tree
        // Left Left Case
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rightRotate(node);
        }

        // Left Right Case
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && getBalance(node.right) <= 0) {
            return leftRotate(node);
        }

        // Right Left Case
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }


	@Override
    public Position positionAtKey(int key) throws IllegalArgumentException {
        AVLNode node = getNode(root, key);
        if (node != null) {
            return new AVLPosition(node.key, node.value); // Assuming AVLPosition implements Position
        } else {
            throw new IllegalArgumentException("Key not found");
        }
    }

	@Override
    public ArrayList<Position> getAllSortedPositions() {
        ArrayList<Position> positions = new ArrayList<>();
        inOrderTraversalPositions(root, positions);
        return positions;
    }


    private void inOrderTraversalPositions(AVLNode node, ArrayList<Position> positions) {
        if (node == null) return;
        inOrderTraversalPositions(node.left, positions);
        positions.add(new AVLPosition(node.key, node.value)); // Assuming AVLPosition implements Position
        inOrderTraversalPositions(node.right, positions);
    }

    public void add(int key, String value) {
        root = insert(root, key, value);
    }

    // Recursive method to insert a new key-value pair and rebalance the tree
    private AVLNode insert(AVLNode node, int key, String value) {
        // Perform the normal BST insertion
        if (node == null) {
            return new AVLNode(key, value);
        }

        if (key < node.key) {
            node.left = insert(node.left, key, value);
        } else if (key > node.key) {
            node.right = insert(node.right, key, value);
        } else {
            return node;
        }

        // Update height of this ancestor node
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Get the balance factor of this ancestor node to check whether it became unbalanced
        int balance = getBalance(node);

        // If this node becomes unbalanced, then perform AVL rotations

        // Left Left Case
        if (balance > 1 && key < node.left.key) {
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && key > node.right.key) {
            return leftRotate(node);
        }

        // Left Right Case
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // Return the (possibly) balanced node
        return node;
    }


}
