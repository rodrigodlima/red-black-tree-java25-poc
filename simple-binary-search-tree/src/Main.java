public class Main {
  public static void main(String[] args) {
    BinarySearchTree tree = new BinarySearchTree((null));
    tree.insert(10);                                                                                                                                                                                                                                
    tree.insert(5);                                                                                                                                                                                                                                 
    tree.insert(15);                                                                                                                                                                                                                                
    tree.insert(3); 
    System.out.println(tree.getRoot().getLeftChild().getLeftChild().getValue());
    System.out.println(tree.getRoot().getRightChild().getValue());
  } 
}
