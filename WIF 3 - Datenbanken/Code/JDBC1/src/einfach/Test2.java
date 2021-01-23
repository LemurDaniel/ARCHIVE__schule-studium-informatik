package einfach;

import java.sql.SQLException;

import controller.Artikel_Manager;

public class Test2 {

	public static void main(String[] args) {
		Artikel_Manager am = new Artikel_Manager();
		try {
			am.findAll().forEach(n -> System.out.println(n));
			System.out.println(am.findById(1));
			System.out.println(am.findById(7));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
