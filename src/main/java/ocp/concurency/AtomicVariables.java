package ocp.concurency;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

@SuppressWarnings ("unused")
public class AtomicVariables {	
	public static void main(String ... args) {
		AtomicInteger i = new AtomicInteger(0);
		int delta = 3;
		
		//i = 2; Compilation error.
		//AtomicInteger extends number, it does not extend Integer.
		//Autoboxing does not work.
		
		i.compareAndSet(0, 2); //boolean - returns true if succesful
		
		//Are EXACTLY the same thing!
		//return the new int
		i.addAndGet(delta);
		i.getAndAdd(delta);
		
		//try to set value until successful
		i.getAndSet(0); 
		i.getAndDecrement();
		i.getAndIncrement();
		
		AtomicBoolean bool;
		AtomicLong l;
		AtomicReference<Object> ref;
		//Double and Float do not have atomic variables
	}
}
