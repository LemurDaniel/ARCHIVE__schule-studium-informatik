package aufgabe6;

import java.lang.StringBuffer;
import java.lang.StringBuilder;

import javapack.Einlesen;
@SuppressWarnings("unused")
public class String_Tests {

	public static void main(String[] args) {
		test(Einlesen.liesInt("Anzahl Durchläufe> "));

	}
	
	public static void test(int cycles) {
		
		
		System.out.println("Benchmark:     ");
		System.out.println("	Nano Time:");
		//Nano
		// 1
		String test_String = "";
		long now = System.nanoTime();
		for(int i=0; i<cycles; i++) {
			//test_String += "0";
		}
		long time1 = System.nanoTime() -now;
		System.out.println("	String-Objekt:    " + time1 + "ns");
		
		// 2
		StringBuffer test_String2 = new StringBuffer();
		now = System.nanoTime();
		for(int i=0; i<cycles; i++) {
			test_String2.append('0');
		}
		long time2 = System.nanoTime() -now;
		System.out.println("	String-Buffer:    " + time2 + "ns    (char)");
		
		// 3
		test_String2 = new StringBuffer();
		now = System.nanoTime();
		for(int i=0; i<cycles; i++) {
			test_String2.append("0");
		}
		long time3 = System.nanoTime() -now;
		System.out.println("	String-Buffer:    " + time3 + "ns    (String)");
		
		// 4
		StringBuilder test_String3 = new StringBuilder();
		now = System.nanoTime();
		for(int i=0; i<cycles; i++) {
			test_String3.append('0');
		}
		long time4 = System.nanoTime() -now;
		System.out.println("	String-Builder:   " + time4 + "ns    (char)");
		
		// 5
		test_String3 = new StringBuilder();
		now = System.nanoTime();
		for(int i=0; i<cycles; i++) {
			test_String3.append("0");
		}
		long time5 = System.nanoTime() -now;
		System.out.println("	String-Builder:   " + time5 + "ns    (String)");
		
		
		System.out.println();
		System.out.println("	Milli Time:");
		//Milli
		// 1
		test_String = "";
		now = System.currentTimeMillis();
		for(int i=0; i<cycles; i++) {
			test_String += "0";
		}
		long time6 = System.currentTimeMillis() -now;
		System.out.println("	String-Objekt:    " + time6 + "ms");
		
		// 2
		test_String2 = new StringBuffer();
		now = System.currentTimeMillis();
		for(int i=0; i<cycles; i++) {
			test_String2.append('0');
		}
		long time7 = System.currentTimeMillis() -now;
		System.out.println("	String-Buffer:    " + time7 + "ms    (char)");
		
		// 3
		test_String2 = new StringBuffer();
		now = System.currentTimeMillis();
		for(int i=0; i<cycles; i++) {
			test_String2.append("0");
		}
		long time8 = System.currentTimeMillis() -now;
		System.out.println("	String-Buffer:    " + time8 + "ms    (String)");
		
		// 4
		test_String3 = new StringBuilder();
		now = System.currentTimeMillis();
		for(int i=0; i<cycles; i++) {
			test_String3.append('0');
		}
		long time9 = System.currentTimeMillis() -now;
		System.out.println("	String-Builder:   " + time9 + "ms    (char)");
		
		// 5
		test_String3 = new StringBuilder();
		now = System.currentTimeMillis();
		for(int i=0; i<cycles; i++) {
			test_String3.append("0");
		}
		long time0 = System.currentTimeMillis() -now;
		System.out.println("	String-Builder:   " + time0 + "ms    (String)");
		System.out.println("	");
		
	}

}
