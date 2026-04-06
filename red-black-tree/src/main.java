public class main {
    public static void main(String[] args) {
    RedBlackTree tree = new RedBlackTree((null));
    tree.insert(10);                                                                                                                                                                                                                                
    tree.insert(5);                                                                                                                                                                                                                                 
    tree.insert(15);                                                                                                                                                                                                                                
    tree.insert(3); 
    System.out.println(tree.getRoot().getLeftChild().getValue());
  }
}
