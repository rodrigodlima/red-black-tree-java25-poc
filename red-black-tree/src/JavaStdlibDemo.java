import java.util.TreeMap;
import java.util.TreeSet;

public class JavaStdlibDemo {

    public static void main(String[] args) {
        System.out.println("=== TreeSet (Red-Black Tree under the hood) ===\n");

        TreeSet<Integer> set = new TreeSet<>();
        int[] values = {10, 5, 15, 3, 7, 12, 20};

        for (int v : values) {
            set.add(v);
            System.out.println("Inserted: " + v + " → tree: " + set);
        }

        System.out.println("\nIn-order iteration: " + set);
        System.out.println("Smallest value:  " + set.first());
        System.out.println("Largest value:   " + set.last());
        System.out.println("Smallest >= 8:   " + set.ceiling(8));
        System.out.println("Largest  <= 11:  " + set.floor(11));

        System.out.println("\n=== TreeMap (Red-Black Tree under the hood) ===\n");

        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(10, "root");
        map.put(5,  "left");
        map.put(15, "right");
        map.put(3,  "left leaf");
        map.put(7,  "right leaf");

        System.out.println("Map in order:");
        map.forEach((k, v) -> System.out.println("  " + k + " → " + v));

        System.out.println("\nLookup key 7:      " + map.get(7));
        System.out.println("Contains key 99?   " + map.containsKey(99));

        System.out.println("\n=== All operations run in guaranteed O(log n) ===");
        System.out.println("Internally it's the same Red-Black Tree we implemented!");
    }
}
