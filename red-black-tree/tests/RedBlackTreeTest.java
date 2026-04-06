public class RedBlackTreeTest {

    static int passed = 0;
    static int failed = 0;

    public static void main(String[] args) {
        testRootIsAlwaysBlack();
        testNewNodeIsRed();
        testNoConsecutiveReds();
        testBlackHeightEqual();
        testBSTOrdering();
        testSortedInsertStaysBalanced();
        testReverseSortedInsert();
        testDuplicateInsert();
        testSingleNode();

        System.out.println("\n=== Results: " + passed + " passed, " + failed + " failed ===");
        if (failed > 0) System.exit(1);
    }

    // ─── Rule 2: root is always BLACK ───────────────────────────────────────
    static void testRootIsAlwaysBlack() {
        RedBlackTree tree = new RedBlackTree(null);
        tree.insert(10);
        assertEqual("Root is BLACK after first insert",
                Color.BLACK, tree.getRoot().getColor());

        tree.insert(5);
        tree.insert(15);
        assertEqual("Root stays BLACK after more inserts",
                Color.BLACK, tree.getRoot().getColor());
    }

    // ─── Rule 1: new node starts as RED (before fixInsert may change it) ────
    static void testNewNodeIsRed() {
        RedBlackTree tree = new RedBlackTree(null);
        tree.insert(10);
        // insert 5 — it should go left as RED, no violation since parent is BLACK
        Node newNode = tree.insertBST(5);
        assertEqual("New node is inserted as RED",
                Color.RED, newNode.getColor());
        // clean up — fix the tree
        tree.fixInsert(newNode);
    }

    // ─── Rule 4: no two consecutive RED nodes ───────────────────────────────
    static void testNoConsecutiveReds() {
        RedBlackTree tree = new RedBlackTree(null);
        int[] values = {10, 5, 15, 3, 7, 12, 20, 1, 4};
        for (int v : values) tree.insert(v);

        assertTrue("No consecutive RED nodes after inserts",
                hasNoConsecutiveReds(tree.getRoot()));
    }

    // ─── Rule 5: equal black-height on all paths ────────────────────────────
    static void testBlackHeightEqual() {
        RedBlackTree tree = new RedBlackTree(null);
        int[] values = {10, 5, 15, 3, 7, 12, 20, 1, 4, 6, 8};
        for (int v : values) tree.insert(v);

        assertTrue("Black-height is equal on all paths",
                blackHeight(tree.getRoot()) != -1);
    }

    // ─── BST property: left < node < right ──────────────────────────────────
    static void testBSTOrdering() {
        RedBlackTree tree = new RedBlackTree(null);
        int[] values = {10, 5, 15, 3, 7, 12, 20};
        for (int v : values) tree.insert(v);

        assertTrue("BST ordering property is maintained",
                isBST(tree.getRoot(), Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    // ─── Sorted input: BST would degenerate, RBT should stay balanced ───────
    static void testSortedInsertStaysBalanced() {
        RedBlackTree tree = new RedBlackTree(null);
        for (int i = 1; i <= 16; i++) tree.insert(i);

        int height = treeHeight(tree.getRoot());
        int maxAllowed = 2 * (int)(Math.log(17) / Math.log(2));

        assertTrue("Sorted insert stays balanced (height " + height + " <= " + maxAllowed + ")",
                height <= maxAllowed);
        assertTrue("No consecutive REDs after sorted insert",
                hasNoConsecutiveReds(tree.getRoot()));
        assertTrue("Black-height equal after sorted insert",
                blackHeight(tree.getRoot()) != -1);
    }

    // ─── Reverse sorted input ────────────────────────────────────────────────
    static void testReverseSortedInsert() {
        RedBlackTree tree = new RedBlackTree(null);
        for (int i = 16; i >= 1; i--) tree.insert(i);

        int height = treeHeight(tree.getRoot());
        int maxAllowed = 2 * (int)(Math.log(17) / Math.log(2));

        assertTrue("Reverse sorted insert stays balanced (height " + height + " <= " + maxAllowed + ")",
                height <= maxAllowed);
        assertTrue("No consecutive REDs after reverse sorted insert",
                hasNoConsecutiveReds(tree.getRoot()));
        assertTrue("Black-height equal after reverse sorted insert",
                blackHeight(tree.getRoot()) != -1);
    }

    // ─── Duplicate insert: should not break the tree ────────────────────────
    static void testDuplicateInsert() {
        RedBlackTree tree = new RedBlackTree(null);
        tree.insert(10);
        tree.insert(10);
        tree.insert(10);

        assertTrue("No consecutive REDs after duplicate inserts",
                hasNoConsecutiveReds(tree.getRoot()));
        assertTrue("Black-height equal after duplicate inserts",
                blackHeight(tree.getRoot()) != -1);
    }

    // ─── Single node ─────────────────────────────────────────────────────────
    static void testSingleNode() {
        RedBlackTree tree = new RedBlackTree(null);
        tree.insert(42);

        assertEqual("Single node is BLACK", Color.BLACK, tree.getRoot().getColor());
        assertTrue("Single node has no left child",  tree.getRoot().getLeftChild() == null);
        assertTrue("Single node has no right child", tree.getRoot().getRightChild() == null);
    }

    // ─── Helpers ─────────────────────────────────────────────────────────────

    // Returns black-height if valid, -1 if any path has inconsistent black-height
    static int blackHeight(Node node) {
        if (node == null) return 0;

        int left  = blackHeight(node.getLeftChild());
        int right = blackHeight(node.getRightChild());

        if (left == -1 || right == -1 || left != right) return -1;

        return left + (node.getColor() == Color.BLACK ? 1 : 0);
    }

    // Returns true if no RED node has a RED child
    static boolean hasNoConsecutiveReds(Node node) {
        if (node == null) return true;

        if (node.getColor() == Color.RED) {
            if (node.getLeftChild()  != null && node.getLeftChild().getColor()  == Color.RED) return false;
            if (node.getRightChild() != null && node.getRightChild().getColor() == Color.RED) return false;
        }

        return hasNoConsecutiveReds(node.getLeftChild()) &&
               hasNoConsecutiveReds(node.getRightChild());
    }

    // Returns true if BST ordering is maintained
    static boolean isBST(Node node, int min, int max) {
        if (node == null) return true;
        if (node.getValue() <= min || node.getValue() >= max) return false;
        return isBST(node.getLeftChild(),  min, node.getValue()) &&
               isBST(node.getRightChild(), node.getValue(), max);
    }

    // Returns the height of the tree
    static int treeHeight(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(treeHeight(node.getLeftChild()), treeHeight(node.getRightChild()));
    }

    // ─── Assertion helpers ───────────────────────────────────────────────────

    static void assertTrue(String name, boolean condition) {
        if (condition) {
            System.out.println("  PASS  " + name);
            passed++;
        } else {
            System.out.println("  FAIL  " + name);
            failed++;
        }
    }

    static void assertEqual(String name, Object expected, Object actual) {
        assertTrue(name + " (expected: " + expected + ", got: " + actual + ")",
                expected.equals(actual));
    }
}
