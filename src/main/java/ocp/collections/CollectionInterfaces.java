package ocp.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionInterfaces {
	public static void main(String[] args) {
		/**
		 * List
		 * 
		 * - Auto-boxing is available when adding primitives.
		 */
		List<String> arrayList = new ArrayList<String>();
		arrayList.add("unu");
		arrayList.add("trei");
		arrayList.add(1, "doi");
		System.out.println(arrayList);
		//This declaration is also legal, but it has the same effect.
		List<String> diamondList = new ArrayList<>(); 
		
		//Can only receive a List elem
		Collections.sort(arrayList);
		System.out.println(arrayList);
		Collections.sort(arrayList, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o2.compareTo(o1);
			}
		});
		System.out.println(arrayList);
		
		//There is also a sort from Arrays
		Arrays.sort(new int[] {0, 3, 5, 2});
		
		System.out.println(arrayList);
		System.out.println(Collections.binarySearch(arrayList, "zeta"));
		
		/**
		 * Comparator interface, contains only one method: compare(T o1, T o2).
		 * 
		 * An object can also implement the Comparable interface, case in which it
		 * needs to implement the compareTo(T o) method that also returns an int. 
		 * 
		 * If you try to use binarySearch() to search a list that has not been 
		 * sorted, the result will not be predictable.
		 */
		
	
	}
}
