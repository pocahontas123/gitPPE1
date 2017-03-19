package model.classe;

import java.io.Serializable;
import java.sql.Date;

public class Adherent {
	//variables membres
	private int id;
	private TypeAdhesion typeAdhesion;
	private String nom;
	private String prenom;
	private String codePostal;
	private String ville;	
	private String telephone;
	private String email;
	private boolean paiement;	
	private java.sql.Date anneeNaissance;
	
	//constructeur sans id
	public Adherent(String nom, String prenom, String codePostal, String ville, java.sql.Date anneeNaissance, TypeAdhesion typeAdhesion, String telephone, String email, boolean paiement) {
		this.id = -1;
		this.nom = nom;
		this.prenom = prenom;
		this.codePostal = codePostal;
		this.ville = ville;
		this.anneeNaissance = anneeNaissance;
		this.typeAdhesion = typeAdhesion;	
		this.telephone = telephone;
		this.email = email;
		this.paiement = paiement;
	}
	
	//constructeur avec id
	public Adherent(int id, String nom, String prenom, String codePostal, String ville, java.sql.Date anneeNaissance, TypeAdhesion typeAdhesion, String telephone, String email, boolean paiement) {
		this(nom, prenom, codePostal, ville, anneeNaissance, typeAdhesion, telephone, email, paiement);
		
		this.id = id;	
	}
	
	//getter
	public int getId() {
		return this.id;
	}
	public String getNom() {
		return this.nom;
	}
	public String getPrenom() {
		return this.prenom;
	}
	public String getCodePostal() {
		return this.codePostal;
	}
	public String getVille() {
		return this.ville;
	}
	public java.sql.Date getAnneeNaissance() {
		return this.anneeNaissance;
	}
	public TypeAdhesion getTypeAdhesion() {
		return this.typeAdhesion;
	}
	public String getTelephone() {
		return this.telephone;
	}
	public String getEmail() {
		return this.email;
	}
	public boolean getPaiement() {
		return this.paiement;
	}
	
	//setter
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public void setAnneeNaissance(java.sql.Date anneeNaissance) {
		this.anneeNaissance = anneeNaissance;
	}	
	public void setTypeAdhesion(TypeAdhesion typeAdhesion) {
		this.typeAdhesion = typeAdhesion;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPaiement(boolean paiement) {
		this.paiement = paiement;
	}
	
}