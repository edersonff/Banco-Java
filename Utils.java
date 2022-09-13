import java.util.Scanner;

public class Utils {
	static private Scanner sc = new Scanner(System.in);

	public static String scan(String text) {
		print(text);
		return sc.next();
	}
	public static float scanFlo(String text) {
		print(text);
		return sc.nextFloat();
	}
	
	public static void slash() {
		print("================================\n");
	}
	
	public static void print(String text) {
		System.out.println(text); 
	}

	public static void clear() {
		for(int i = 0; i<2; i++) {
			print("\n");
		}
	}
}
