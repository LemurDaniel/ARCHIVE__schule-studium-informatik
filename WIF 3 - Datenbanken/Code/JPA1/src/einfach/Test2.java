package einfach;

import java.sql.SQLException;

import controller.Artikel_Manager;
import entity.Artikel;

public class Test2 {

	public static void main(String[] args) {
		Artikel_Manager am = new Artikel_Manager();
		try {
			am.findAll().forEach(n -> System.out.println(n));
			System.out.println(am.findById(1));
			System.out.println(am.findById(7));
			
			Artikel a = am.findById(1);
//			a.setBezeichnung("kleiner Eimer");
//			am.update(a);
//			am.findAll().forEach(n -> System.out.println(n));
			
			System.out.println("-------");
			
			a = new Artikel(0, 20, 10, "TestArtikel");
			am.insert(a);
			am.findAll().forEach(n -> System.out.println(n));
			
			System.out.println("-------");
			
			am.delete(am.findById(14));
			am.findAll().forEach(n -> System.out.println(n));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
