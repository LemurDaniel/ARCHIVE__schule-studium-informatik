package aufgabe6;

public class Operatorpräzedenz {

	public static void main(String[] args) {
		
		int ii;  
		ii = 10 + 5 * 2; // 20
		
		boolean r;  		 
		r = true != false || true; // true
		 		 
		ii = (10 + 5) * 2;  // 30
		
		
		/*	Höchste Präzedenz
		 * 
		 *	. ---> Komponentenzugriff
		 * 	[] ---> Array-Index
		 * 	() ---> Gruppieren von Ausdrücken
		 */
		
		/*	2. Stufe
		 * 
		 * 	++ -- ---> Inkrement/Dekrement (präfix oder postfix)
		 * 	! ---> Logisches NICHT
		 * 	~ ---> Bit-Komplement
		 * 	- ---> Vorzeichenumkehr
		 * 	new ---> Objekterzeugung
		 * 	(Typ) ---> Typumwandlung (Casting)
		 */
		
		/*	3. Stufe
		 * 
		 *	* / ---> Multiplikation/Division
		 * 	% ---> Modulo
		 */
		
		/*	4. Stufe
		 * 
		 *	+ - ---> Addition/Subtraktion
		 *	+ ---> Stringverkettung
		 */
		
		/*	5. Stufe
		 * 
		 *	<< >> >>> ---> Bitweiser Links-/Rechts-Shift
		 */
		
		/*	6. Stufe
		 * 
		 *	< > <= >= ---> Vergleichsoperatoren
		 *	instanceof ---> Typprüfung
		 */
		
		/*	7. Stufe
		 * 
		 * 	== != ---> Gleichheit, Ungleichheit
		 */
		
		/*	8. Stufe
		 * 
		 *	& ---> AND (bitwise; logisch mit unbedingter Auswertung)
		 */
		
		/*	9. Stufe
		 * 
		 * 	^ ---> XOR
		 */
		
		/*	10. Stufe
		 * 
		 *	| ---> OR (bitwise; logisch mit unbedingter Auswertung)
		 */
		
		/*	11. Stufe
		 * 
		 *	&& ---> Logisches AND (mit bedingter Auswertung)
		 */
		
		/*	12. Stufe
		 * 
		 * 	|| ---> Logisches OR (mit bedeingter Auswertung)
		 */
		
		/*	13. Stufe
		 * 
		 *	? : ---> Kurzform für if...then...else (Konditionaloperator)
		 */
		
		/* 14. Stufe
		 * 
		 *	= += -= *= /= %= ^=	---> Verschiedene Zuweiseungen 
		 *	&= |= <<= >>= >>>= ---> Weitere Zuweisungen
		 */
	

	}

}
