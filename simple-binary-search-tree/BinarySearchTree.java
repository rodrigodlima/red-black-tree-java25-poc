public class BinarySearchTree {
    private Node root;
    
    public BinarySearchTree(Node root) {
        this.root = root;
        if (root == null) {
            System.out.println("value null");
        }
    }
}


class Node {
    private int value;
    private Node leftChild;
    private Node rightChild;
    
    public Node(int value, Node leftChild, Node rightChild) {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
}

