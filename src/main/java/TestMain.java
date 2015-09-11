import java.util.Locale;


public class TestMain {
	public static void main (String args[]) {
		Locale locale;
		try {
			locale = new Locale("en");
		} catch (NullPointerException e) {
			locale = Locale.getDefault();
		}		
	}
}
