package ocp.threads;

/**
 * 
 * If two different class instances are started, synchronizing 
 * the write() method has no effect on estimating an expected 
 * result.
 *
 */
public class Letters extends Thread {
	private String name;
	public Letters (String name) {
		this.name = name;
	}

	public void write() {
		System.out.println(name);
		System.out.println(name);
	}
	
	public static void main(String[] args) {
		new Letters("X").start();
		new Letters("Y").start();
	}
	
	public void run() {
		/**
		 * Legal, synchronize on all class instances.
		 */
		synchronized (Letters.class) {
			write();
		}
	}
	
	public void run2() {
		/**
		 * Perfectly legal statement, because it returns a
		 * PrintStream object.
		 */
		synchronized (System.out) {
			write();
		}
	}
}
