CleverSIDC - Student Tracking System

Description
This project is an implementation of the CleverSIDC (Student Identification Code) system, designed for 
the COMP 352 - Data Structures and Algorithms course at Concordia University. The system manages student records, 
identified by unique 8-digit codes, across various geographic locations. It features dynamic data structures that adapt
to the number of entries, balancing memory and runtime efficiency.

Installation
1. Ensure Java is installed on your system.
2. Download the project files to your local machine.
3. Open the project in an IDE like Eclipse or compile the Java files from the command line.

Usage
You can run the 'Driver.java' to start the application. This simple program demonstrates various functionalities of the CleverSIDC 
system, including adding, removing, and retrieving student records, as well as navigating through records based on 
their unique IDs, you can try out the available methods.
You can also run 'TestCleverSIDC.java' which uses NASTA_test_file1.txt, 
the getAllSortedPositions method on this file will take a couple of minutes to run because of the size of the file,
the point was to test the effectiveness of the said method for the enormous amounts of data. 

Features
- Dynamically adapts data structures based on the size of the dataset.
- Efficient operations for managing and tracking student records.
- Includes methods for generating unique IDs, adding and removing records, and navigating between records.
- Linked Positional list is used for the small amount of entries, AVL tree for big.
- In both versions each entry can be retrieved by its position, index and key.
- Merge Sort is used for sorting in small version, in order traversal for big version. 

LinkedPositionalList class

Space Complexity:
O(n): Each node requires space for its data and two pointers.

Time Complexity:
Add/Remove at known position: O(1) - Directly accessing and modifying pointers.
Search/Remove by key or index: O(n) - Potentially traverses the entire list.
Get size/first/last/next/previous: O(1) - Immediate access to nodes or maintained size variable.
Unique key generation: O(n) - May check each element for uniqueness.
Sorting: O(n log n) - Merge sort complexity.
Range query: O(n) - Traverses the list to count keys in range.
The list is efficient for operations with direct node access but less so for those requiring traversal.

AVLPositionalList:

Space Complexity:
O(n): Each node in the AVL tree (AVLNode) holds data (key and value), two child node references (left, right), 
and height information. Therefore, the space complexity is linear with the number of nodes.

Time Complexity:
Insertion: O(log n) - Due to the AVL tree's self-balancing nature, the height is maintained at around log n, making insertions logarithmic in complexity.
Deletion: O(log n) - Similar to insertion, deletions require at most a traversal from root to leaf, plus additional balancing operations.
Search: O(log n) - AVL tree properties ensure that the search path is logarithmic in relation to the number of nodes.
Generating Unique Key: O(n) - In the worst case, this could iterate over all existing keys to ensure uniqueness.
In-order Traversal: O(n) - Traverses each node once.
Finding Next/Previous Key: O(log n) - Operations involve a traversal which is generally logarithmic in height.
Range Queries: O(n) - Involves traversing potentially the entire tree to count keys.

The AVL tree structure enhances the efficiency of most operations compared to a simple linked list due to its balanced nature, especially for searches, insertions, and deletions, which are typically O(log n). However, operations that require traversing multiple nodes, like generating a unique key or range queries, still have a linear time complexity in the worst case.

Contributing
This project is an academic submission and is not open for external contributions.

License
This project is proprietary and part of an academic course. It is not licensed for public use.

Contact
For queries related to this project, please contact Kateryna Gurina at katerynagurina@gmail.com .
