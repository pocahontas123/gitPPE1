package Classes;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class TypeAdhesion {

	//variables membres
	public int idTypeAdhesion;
	public String libelle;
	public int tarif;
	
	//constructeur
	public TypeAdhesion(int idTypeAdhesion, String libelle, int tarif) {
		this.idTypeAdhesion = idTypeAdhesion;
		this.libelle = libelle;
		this.tarif = tarif;
	}
	
	//getter
	public int getIdTypeAdhesion() {
		return this.idTypeAdhesion;
	}
	public String getLibelle() {
		return this.libelle;
	}
	public int getTarif() {
		return this.tarif;
	}
	
	//setter
	public void setIdTypeAdhesion(int idTypeAdhesion) {
		this.idTypeAdhesion = idTypeAdhesion;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public void setTarif(int tarif) {
		this.tarif = tarif;
	}
	
	//méthode
	public void reductionTarif(int reduction) {
		double reduc = 1 - (reduction / 100.0);
		tarif = (int)Math.round(tarif * reduc);
	}
}
