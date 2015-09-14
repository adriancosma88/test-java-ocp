package ocp.threads;

public class ThreadTest {
	public static void main (String ... args) {
		System.out.println(new Runnable() {public void run() {}});
		
		
		/**
		 * Thread priorities
		 * 
		 * -----
		 * Not all JVMs have the same priority levels
		 */
		int priority = 0;
		
		priority = Thread.MIN_PRIORITY; //1
		priority = Thread.NORM_PRIORITY; //5
		priority = Thread.MAX_PRIORITY; //10
		
		System.out.println(priority);
		
		/**
		 * Synchronized Blocks
		 * 
		 * -----
		 * If no object is mentioned in a synchronized block,
		 * the lock will be aquired for this object.
		 * 
		 * -----
		 * In order to have an effect, notify(), must be called
		 * from a different thread.
		 * 
		 */
		
		/**
		 * Thread methods
		 * 
		 * -----
		 * join() throws InterrupedException, thus it must ALWAYS
		 * handle or throw this exception
		 * 
		 */
	}
}
