package ocp.concurency;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class ConcurrentCollections {
	public static void main(String[] args) {
		CopyOnWriteArraySet<String> copyOnWriteArraySet = new CopyOnWriteArraySet<String>();
		CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<String>();
	}
}
