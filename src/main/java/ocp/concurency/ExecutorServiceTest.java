package ocp.concurency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;

public class ExecutorServiceTest {
	public static void main(String[] args) {
		ForkJoinPool fjp = new ForkJoinPool();
		System.out.println(fjp instanceof ExecutorService); 
		
		/**
		 * 
		 *  Tasks that can't execute immediately in a fixed thread pool
		 *  are queued, not pooled.
		 * 
		 */
	}
}
