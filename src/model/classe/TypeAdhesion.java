package model.classe;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class TypeAdhesion {

	//variables membres
	public int idTypeAdhesion;
	public String libelle;
	public int tarif;
	
	//constructeur sans idTypeAdhesion (-1)
	public TypeAdhesion(String libelle, int tarif) {
		this.idTypeAdhesion = -1;
		
		this.libelle = libelle;
		this.tarif = tarif;
	}
	
	//constructeur avec idTypeAdhesion
	public TypeAdhesion(int idTypeAdhesion, String libelle, int tarif) {
		this.idTypeAdhesion = idTypeAdhesion;
		
		this.libelle = libelle;
		this.tarif = tarif;
	}
	
	//getter (récupère l'id du type d'adhésion, libelle et tarif catégorie)
	public int getIdTypeAdhesion() {
		return this.idTypeAdhesion;
	}
	public String getLibelle() {
		return this.libelle;
	}
	public int getTarif() {
		return this.tarif;
	}
	
	//setter (modifie l'id du type adhésion, le libelle et le tarif d'une catégorie)
	public void setIdTypeAdhesion(int idTypeAdhesion) {
		this.idTypeAdhesion = idTypeAdhesion;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public void setTarif(int tarif) {
		this.tarif = tarif;
	}
	
	//méthode (reduction d'un tarif)
	public int reductionTarif(int reduction) {
		double reduc = 1 - (reduction / 100.0);
		tarif = (int)Math.round(tarif * reduc);
		return tarif;
	}
}
