package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Artikel database table.
 * 
 */
@Entity
@NamedQuery(name="Artikel.findAll", query="SELECT a FROM Artikel a")
public class Artikel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String bezeichnung;

	private int menge;

	private int mindestbestand;

	private double preis;

	public Artikel() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBezeichnung() {
		return this.bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public int getMenge() {
		return this.menge;
	}

	public void setMenge(int menge) {
		this.menge = menge;
	}

	public int getMindestbestand() {
		return this.mindestbestand;
	}

	public void setMindestbestand(int mindestbestand) {
		this.mindestbestand = mindestbestand;
	}

	public double getPreis() {
		return this.preis;
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}

	@Override
	public String toString() {
		return "Artikel [id=" + id + ", bezeichnung=" + bezeichnung + ", menge=" + menge + ", mindestbestand="
				+ mindestbestand + ", preis=" + preis + "]";
	}

}