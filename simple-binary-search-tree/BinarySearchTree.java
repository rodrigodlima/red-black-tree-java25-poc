import java.util.Objects;

public class BinarySearchTree {
    private Node root;
    
    public BinarySearchTree(Node root) {
        this.root = root;
        if (root == null) {
            System.out.println("value null");
        }
    }
    
    public void insert(int value) {
        if (root == null) {
            BinarySearchTree == 
        }
        while (root != null) {
            if root.value 
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
    public int getValue() {
        return value;
    }
    public Node getLeftChild() {
        return leftChild;
    }
    public Node getRightChild() {
        return rightChild;
    }
}

