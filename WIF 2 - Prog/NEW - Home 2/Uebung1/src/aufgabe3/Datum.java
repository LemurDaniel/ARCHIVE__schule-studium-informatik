package aufgabe3;

import java.time.LocalDate;

public class Datum {

	public static int ISO_Norm = 1;
	public static int DIN_Norm = 2;
	public static int optional = 3;
	
	private static final String[] months = {"Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "Novermber", "Dezember"};
	
	
	//
	private int day, month, year;
	
	
	//
	Datum(String datum){
	
		String parts[] = new String[3];
//		for(int i=0; i<parts.length; i++) {
//			parts[i] = "";
//		}
//		
//		for(int i=0, s=0; i<datum.length() && s<parts.length; i++) {
//			
//			if (Character.isDigit(datum.charAt(i))) 
//				parts[s] += datum.charAt(i);
//			
//			else if( datum.charAt(i)=='.' && parts[s].length()!=0)
//				s++;
//		}
//		day = Integer.valueOf(parts[0]);
//		month = Integer.valueOf(parts[1]);
//		year = Integer.valueOf(parts[2]);
//		
		if(datum.matches("\\s*\\d+[-]\\d{1,2}[-]\\d{1,2}\\s*")) {
			parts = datum.split("-");
			day = Integer.valueOf(parts[2]);
			month = Integer.valueOf(parts[1]);
			year = Integer.valueOf(parts[0]);
		}
		else if(datum.matches("\\s*\\d{1,2}[.]\\d{1,2}[.]\\d+\\s*")) {
			parts = datum.split("\\.");
			day = Integer.valueOf(parts[0]);
			month = Integer.valueOf(parts[1]);
			year = Integer.valueOf(parts[2]);
		}
		else if(datum.matches("\\s*\\d{1,2}\\s+[a-zA-Z]{3,9}[,]\\s+\\d+\\s*")) {
			parts = datum.split(",");
			day = Integer.parseInt(parts[0].replaceAll("\\D",""));
			month = monthString_Int(parts[0].replaceAll("[^a-zA-Z]", ""));
			year = Integer.parseInt(parts[1].replaceAll("\\D", ""));
		}
		else
			System.out.println("Datum entspricht keinem gültigen Datumsformat");
		
		ueberpruefe();	
	}
	
	Datum(int day, String month, int year){
		this(year, Datum.monthString_Int(month), day);
	}
	
	Datum(int year, int month, int day){
		this.year = year;
		this.month = month;
		this.day = day;
		
		ueberpruefe();
	}
	
	//
	private static int monthString_Int(String m) {
		for(int i=0; i<months.length; i++) {
			if (months[i].equals(m))
				return i+1;
		}
		return -1;
	}
	
	//
	private void ueberpruefe() {
		if (!istKorrekt()) {
			System.out.println("Eingegebens Datum inkorrekt, heutiges Datum gesetzt");
			LocalDate today = LocalDate.now();
			this.day = today.getDayOfMonth();
			this.month = today.getMonthValue();
			this.year = today.getYear();		
		}
	}
	
	private boolean istKorrekt() {
		if(year < 1000 || year > 9999) 
			return false;
				
		if (month == -1) 
			return false;
				
		if (day < 0 || day > monthDays())
			return false;		
		
		return true;
	}

	private int monthDays() {
		switch(month) {
		case 1: case 3: case 5: case 7: case 8: case 10: case 12: return 31;
		case 4: case 6: case 9: case 11: return 30;
		case 2: return istSchaltjahr() ? 29:28;		
		default:
			return -1;
		}
	}
	
	private boolean istSchaltjahr() {
		return year%4 == 0 &&  (year%100 != 0 || (year%400 == 0 && year%100 == 0) );
	}
	
	
	//
	public void ausgabe(int variante) {
		String day = (this.day<9 ? "0":"") +this.day;
		String month = (this.month<9 ? "0":"") +this.month;
		
		if( variante == ISO_Norm) 
			System.out.printf("%d-%s-%s \n", year, month, day);
		
		if( variante == DIN_Norm) 
			System.out.printf("%d %s, %d \n", this.day, months[this.month-1], year);
			
		if( variante == optional) 
			System.out.printf("%s.%s.%d \n", day, month, year);					
	}
}
