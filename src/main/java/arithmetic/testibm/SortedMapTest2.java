package arithmetic.testibm;

import java.util.*;

public class SortedMapTest2 {
	
	public static void main(String[] args) {
		
		Map<String,Object> hashMap = new HashMap<String,Object>();
		hashMap.put("1", "a");
		hashMap.put("5", "b");
		hashMap.put("2", "c");
		hashMap.put("4", "d");
		hashMap.put("3", "e");
		Set<Map.Entry<String, Object>> entry = hashMap.entrySet();
		for(Map.Entry<String, Object> temp : entry){
			System.out.println("hashMap:"+temp.getKey()+" 值"+temp.getValue());  
		}
		
		System.out.println("\n");  
		
		SortedMap<String,Object> sortedMap = new TreeMap<String,Object>();
		sortedMap.put("1", "a");
		sortedMap.put("5", "b");
		sortedMap.put("2", "c");
		sortedMap.put("4", "d");
		sortedMap.put("3", "e");
		Set<Map.Entry<String, Object>> entry2 = sortedMap.entrySet();
		for(Map.Entry<String, Object> temp : entry2){
			System.out.println("sortedMap:"+temp.getKey()+" 值"+temp.getValue());  
		}
		
	}
 
}