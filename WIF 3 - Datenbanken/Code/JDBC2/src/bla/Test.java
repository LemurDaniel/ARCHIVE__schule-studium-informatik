package bla;

import java.sql.SQLException;
import java.util.List;

import controller.Artikel_Manager;
import entity.Artikel;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Artikel_Manager t = new Artikel_Manager();
		try {
//			for(int i=0; i<2000; i++) {
//				t.insert(new Artikel(1, ((int)(Math.random()*91)+10), ((int)(Math.random()*9001)+1000)/100.0, "Test"));
//				System.out.println(i);
//			}
			
//			List<Artikel> list = t.findAll();
//			list.stream().forEach(a-> System.out.println(a));
			t.execute("select * from Artikel where id < 100");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
