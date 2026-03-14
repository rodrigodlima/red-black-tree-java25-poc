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
        if (Objects.isNull(value)) {
            BinarySearchTree tree = new BinarySearchTree(root)
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

