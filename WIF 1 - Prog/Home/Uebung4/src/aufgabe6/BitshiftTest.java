package aufgabe6;

import javapack.Einlesen;

public class BitshiftTest {

	public static void main(String[] args) {
		int a = Einlesen.liesInt("Zahl a> ");
		System.out.format("Zahl a = %32s\n", Integer.toBinaryString(a));
		System.out.format("a<<2   = %32s\n", Integer.toBinaryString(a<<2));
		System.out.format("a>>2   = %32s\n", Integer.toBinaryString(a>>2));
		System.out.format("a>>>2  = %32s\n", Integer.toBinaryString(a>>>2));
	}

}
