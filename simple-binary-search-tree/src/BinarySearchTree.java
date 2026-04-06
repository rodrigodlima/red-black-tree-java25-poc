import java.util.Objects;

public class BinarySearchTree {
    private Node root;
    
    public BinarySearchTree(Node root) {
        this.root = root;
        if (root == null) {
            System.out.println("value null");
        }
    }
    public Node getRoot() {                                                                                                                                                                                                                         
        return root;                                                                                                                                                                                                                                
    }
    
    public void insert(int value) {
        insertBST(value);
    }

    public Node insertBST(int value) {
        if (root == null) {
            root = new Node(value, null, null);
            return null;
        }
        Node actual = root;
        while (actual != null) {
            if (value < actual.getValue()) {
                if (actual.getLeftChild() == null) {
                    Node newNode = new Node(value, null, null);
                    actual.setLeftChild(newNode);
                    return newNode;
                } else {
                    actual = actual.getLeftChild();
                }
            } else {
                if (actual.getRightChild() == null) {
                    Node newNode = new Node(value, null, null);
                    actual.setRightChild(newNode);
                    return newNode;
                } else {
                    actual = actual.getRightChild();
                }
            }
        }
        return null;
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
    public void setLeftChild(Node leftChild) {                                                    
      this.leftChild = leftChild;                                                               
    }                                                                                             
    public void setRightChild(Node rightChild) {                                                  
      this.rightChild = rightChild;
    }
}



