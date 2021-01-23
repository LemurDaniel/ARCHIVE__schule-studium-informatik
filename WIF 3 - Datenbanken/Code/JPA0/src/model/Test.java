package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Test {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA0");
		EntityManager em = emf.createEntityManager();
		
		TypedQuery<Artikel> querry = em.createQuery("select a from Artikel a", Artikel.class);
		querry.getResultList().forEach(e->System.out.println(e));

	}

}
