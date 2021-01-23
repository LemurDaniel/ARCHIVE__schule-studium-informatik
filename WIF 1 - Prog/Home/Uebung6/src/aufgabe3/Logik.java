package aufgabe3;

import javapack.Einlesen;

public class Logik {

	public static void main(String[] args) {
		int[][] test = { 	{-1, -2, 0, 15},
							{3, 4, -1, 8},
							{-6, 15, -8, 11},
							{2, 15, 18, 20},
							{-5, -4, -3, -2},
							{20, 30, 20, 40},	};
		
		int[] a = new int[Einlesen.liesInt("Länge von Feld a> ")];		
		for(int i=0; i<a.length; i++) {
			a[i] = Einlesen.liesInt("a[" +i+ "]> ");
		}
		
		System.out.println(	"\n1. Aussage: Mindestens ein Feldelement hat einen Wert größer als 1.\n"+ 
							"2. Aussage: Mindestens ein, höchstens drei Feldelemente haben einen Wert größer als 1.\n"+ 
							"3. Aussage: Genau ein Feldelement ist negativ.\n" + 
							"4. Aussage: Alle Feldelemente, die größer als 0 sind, sind auch größer als 10.\n"+ 
							"5. Aussage: Jedes Feldelement ist entweder größer als 10 oder es ist kleiner als -5.\n");
		
		System.out.println("1.Aussage     2.Aussage     3.Aussage     4.Aussage     5.Aussage     Eingabe");
		for(int[] t:test) {
			logik(t);
		}
		System.out.println();
		logik(a);

	}
	
	
		public static void logik(int[] a) {
			boolean w = false;
			
			// Aussage 1
			for(int i:a) {
				if(i>1) {
					w = true;
					break;
				}
			}
			System.out.format("%-14s", w);

			// Aussage 2
			if(w && a.length>3) {
					for(int i=0; i<a.length; i++) {
					if(a[i]>1) {
						for(i++; i<a.length; i++) {
							if(a[i]>1) {
								for(i++; i<a.length; i++) {
									if(a[i]>1) {
										for(i++; i<a.length; i++) {
											if(a[i]>1) {
												w = false;
												i=a.length;
											}
										}
									}
								}
							}
						}
					}
				}
			}
			System.out.format("%-14s", w);
					
			// Aussage 3
			w = false;
			for(int i=0; i<a.length; i++) {
				if(a[i]<-0) {
					w=true;
					for(i++; i<a.length; i++) {
						if(a[i]<-0) {
							w=false;
							i=a.length;
						}
					}
				}
			}
			System.out.format("%-14s", w);
			
			// Aussage 4
			w=true;
			for(int i:a) {
				if(i>0 && i<=10) {
					w=false;
					break;
				}
			}
			System.out.format("%-14s", w);
			
			// Aussage 5
			w=true;
			for(int i:a) {
				if(i<=10 && i>=-5) {
					w=false;
					break;
				}
			}
			System.out.format("%-14s", w);
			for(int i=0; i<a.length; i++) {
				System.out.print(a[i]+ (i==a.length-1 ? "\n":", ") );
			}
		}
	

}
