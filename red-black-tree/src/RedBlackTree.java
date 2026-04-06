public class RedBlackTree {
    private Node root;
    
    public RedBlackTree(Node root) {
        this.root = root;
        if (root == null) {
            System.out.println("value null");
        }
    }
    public Node getRoot() {                                                                                                                                                                                                                         
        return root;                                                                                                                                                                                                                                
    }

    public void insert(int value) {
        Node inserted = insertBST(value);
        if (inserted != null) {
            fixInsert(inserted);
        }
    }

    public Node insertBST(int value) {
        if (root == null) {
            root = new Node(value, null, null, Color.BLACK, null);
            return null;
        }
        Node actual = root;
        while (actual != null) {
            if (value < actual.getValue()) {
                if (actual.getLeftChild() == null) {
                    Node newNode = new Node(value, null, null, Color.RED, actual);
                    actual.setLeftChild(newNode);
                    return newNode;
                } else {
                    actual = actual.getLeftChild();
                }
            } else {
                if (actual.getRightChild() == null) {
                    Node newNode = new Node(value, null, null, Color.RED, actual);
                    actual.setRightChild(newNode);
                    return newNode;
                } else {
                    actual = actual.getRightChild();
                }
            }
        }
        return null;
    }
    public void leftRotate(Node x){
        Node y = x.getRightChild();
        x.setRightChild(y.getLeftChild()); 
        y.setParent(x.getParent());
        if ( x == root) {
            root = y;
        } else {
            if (x.getParent().getLeftChild() == x) {
                x.getParent().setLeftChild(y);
            } else {
                x.getParent().setRightChild(y);
            }
        }
        y.setLeftChild(x);
        x.setParent(y);
    }

    public void rightRotate(Node x){
        Node y = x.getLeftChild();
        x.setLeftChild(y.getRightChild()); 
        y.setParent(x.getParent());
        if ( x == root) {
            root = y;
        } else {
            if (x.getParent().getRightChild() == x) {
                x.getParent().setRightChild(y);
            } else {
                x.getParent().setLeftChild(y);
            }
        }
        y.setRightChild(x);
        x.setParent(y);
    }
    
    public void fixInsert(Node z) {
        while (z.getParent() != null && z.getParent().getColor() == Color.RED) {
            if (z.getParent() == z.getParent().getParent().getLeftChild()) {
                Node uncle = z.getParent().getParent().getRightChild();
                if (uncle != null && uncle.getColor() == Color.RED) {
                    uncle.setColor(Color.BLACK);
                    z.getParent().setColor(Color.BLACK);
                    z.getParent().getParent().setColor(Color.RED);
                    z = z.getParent().getParent();
                } else {
                    if (z == z.getParent().getRightChild()) {
                        z = z.getParent();
                        leftRotate(z);
                    }
                    z.getParent().setColor(Color.BLACK);
                    z.getParent().getParent().setColor(Color.RED);
                    rightRotate(z.getParent().getParent());
                }
            } else {
                Node uncle = z.getParent().getParent().getLeftChild();
                if (uncle != null && uncle.getColor() == Color.RED) {
                    uncle.setColor(Color.BLACK);
                    z.getParent().setColor(Color.BLACK);
                    z.getParent().getParent().setColor(Color.RED);
                    z = z.getParent().getParent();
                } else {
                    if (z == z.getParent().getLeftChild()) {
                        z = z.getParent();
                        rightRotate(z);
                    }
                    z.getParent().setColor(Color.BLACK);
                    z.getParent().getParent().setColor(Color.RED);
                    leftRotate(z.getParent().getParent());
                }
            }
        }
        root.setColor(Color.BLACK);
    }
}

enum Color {RED,BLACK}

class Node {
    private int value;
    private Node leftChild;
    private Node rightChild;
    private Node parent;
    private Color color;

    public Node(int value, Node leftChild, Node rightChild, Color color, Node parent ) {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.parent = parent;
        this.color = color;
    }
    public int getValue(){
        return value;
    }
    public Node getLeftChild(){
        return leftChild;
    }
    public Node getRightChild(){
        return rightChild;
    }
    public Color getColor(){
        return color;
    }
    public Node getParent(){
        return parent;
    }
    public void setLeftChild(Node leftChild) {                                                    
        this.leftChild = leftChild;                                                               
    }                                                                                             
    public void setRightChild(Node rightChild) {                                                  
        this.rightChild = rightChild;
    }
    public void setParent(Node parent) {
        this.parent = parent;
    }
    public void setColor(Color color) {
        this.color = color;
    }

}