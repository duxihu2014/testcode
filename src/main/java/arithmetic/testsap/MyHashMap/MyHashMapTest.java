package arithmetic.testsap.MyHashMap;

public class MyHashMapTest {

    public static void main(String[] args) {

        MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>();
        map.put(1, 90);
        map.put(2, 95);
        map.put(17, 85);
    
        System.out.println(map.get(1));
        System.out.println(map.get(2));
        System.out.println(map.get(17));
        System.out.println(map.get(null));
    }
}