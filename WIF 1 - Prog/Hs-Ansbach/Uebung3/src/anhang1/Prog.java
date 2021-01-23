package anhang1;

public class Prog {

	public static void main(String[] args) {

		double big_d = 1.79769313486231570e308 * 10;
		System.out.println("Unendlich =\t\t" + big_d);
		System.out.println("Unendlich – Unendlich =\t"+(big_d-big_d));
		System.out.println("Unendlich / Unendlich =\t"+(big_d/big_d));
		System.out.println("Unendlich * 0.0 =\t" + (big_d * 0.0));
		System.out.println("0.0 / 0.0 =\t\t" + (0.0/0.0));


	}

}
