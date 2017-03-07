package DataAcessLayer;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Classes.TypeAdhesion;

public class TypeAdhesionDB {
	
	PreparedStatement st = null;
	Statement state = null;
	ResultSet result = null;
	
	public TypeAdhesion getTypeAdhesion(String Libelle) {
		//Chargement du driver JDBC pour MySQL
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
		
		//information d'acc�s � la base de donn�es
		TypeAdhesion leTypeAdhesion = null;
		
		
		//�tablissement de la connexion
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}try {
			String sql = "SELECT idTypeAdhesion, Libelle, Tarif from typeadhesion WHERE Libelle = '"+Libelle+"'";
			
			//Etape 4 : ex�cution requ�te
			state = ConnexionDB.getInstance().createStatement();
			//Etape 4 : ex�cution requ�te
			result = state.executeQuery( sql );
			
			//Etape 5 : (parcours Resultset
			while( result.next() ) {
				int idType = result.getInt( "idTypeAdhesion" );
				String libelle = result.getString( "Libelle" );
				int tarif = result.getInt( "Tarif" );
				
				leTypeAdhesion = new TypeAdhesion(idType, libelle, tarif);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return leTypeAdhesion;
	}
	
	
	public ArrayList getTypeAdhesions() {
		//Chargement du driver JDBC pour MySQL
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
		
		//information d'acc�s � la base de donn�es
		TypeAdhesion leTypeAdhesion = null;
		
	    ArrayList Adhesion = new ArrayList();

		//�tablissement de la connexion
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}try {
			String sql = "SELECT idTypeAdhesion, Libelle, Tarif from typeadhesion";
			
			//Etape 4 : ex�cution requ�te
			state = ConnexionDB.getInstance().createStatement();
			//Etape 4 : ex�cution requ�te
			result = state.executeQuery( sql );
			//Etape 5 : (parcours Resultset
			while( result.next() ) {
				int idType = result.getInt( "idTypeAdhesion" );
				String libelle = result.getString( "Libelle" );
				int tarif = result.getInt( "Tarif" );
				
				leTypeAdhesion = new TypeAdhesion(idType, libelle, tarif);
				Adhesion.add(leTypeAdhesion);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return Adhesion;
	}
	
	
	
	
	public void saveTypeAdhesion(TypeAdhesion adhesion) {
		//Chargement du driver JDBC pour MySQL
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
		//�tablissement de la connexion
		try {	
			String sql = "INSERT INTO typeadhesion (idTypeAdhesion, Libelle, Tarif) VALUES (?, ?, ?)";
			
			st = ConnexionDB.getInstance().prepareStatement( sql );
			
			st.setInt(1, adhesion.getIdTypeAdhesion());
			st.setString(2, adhesion.getLibelle());
			st.setInt(3, adhesion.getTarif());
			
			//Etape 4 : ex�cution requ�te
			st.executeUpdate();
			st.close();	
		} catch ( SQLException e ) {
			 e.printStackTrace();
		}
	}
	
	public void supprimerTypeAdhesion(int idTypeAdherent) {
		//Chargement du driver JDBC pour MySQL
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
		//�tablissement de la connexion
		try {
			String sql = "DELETE FROM typeadhesion WHERE idTypeAdhesion= ?";
			
			st = ConnexionDB.getInstance().prepareStatement( sql );
			
			st.setInt(1, idTypeAdherent);
			
			st.executeUpdate();
			System.out.println("Le type d'adh�sion a �t� supprim� de la base de donn�e");
			
			st.close();
		} catch ( SQLException e ) {
			 e.printStackTrace();
		}
	}
}
