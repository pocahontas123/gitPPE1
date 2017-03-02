package DataAcessLayer;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Classes.Adherent;



public class AdherentDB {
	PreparedStatement st = null;
	Statement state = null;
	ResultSet result = null;
	
	public void saveAdherent(Adherent adherent) {
		//Chargement du driver JDBC pour MySQL
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}

		try {
			String sql;
			if(adherent.getId() == -1) {
			
				sql = "INSERT INTO adherent VALUES (NULL, ?, ?, ?, ?, ?, ?)";
				st = ConnexionDB.getInstance().prepareStatement( sql );
				st.setString(1, adherent.getNom());
				st.setString(2, adherent.getPrenom());
				st.setString(3, adherent.getCodePostal());
				st.setString(4, adherent.getVille());
				st.setDate(5, adherent.getAnneeNaissance());
				st.setInt(6, adherent.getTypeAdhesion().getIdTypeAdhesion());
				
				st.executeUpdate();
			}else {
				boolean exist = verifExistAdherent(adherent);
				if(!exist) {			
					sql = "INSERT INTO adherent VALUES (?, ?, ?, ?, ?, ?, ?)";
					
					st = ConnexionDB.getInstance().prepareStatement( sql );
					
					st.setInt(1, adherent.getId());
					st.setString(2, adherent.getNom());
					st.setString(3, adherent.getPrenom());
					st.setString(4, adherent.getCodePostal());
					st.setString(5, adherent.getVille());
					st.setDate(6, adherent.getAnneeNaissance());
					st.setInt(7, adherent.getTypeAdhesion().getIdTypeAdhesion());
					
					st.executeUpdate();
				}else {
					System.out.println("Adh�rent existe d�j�");
				}
			}
		} catch ( SQLException e ) {
			 e.printStackTrace();
		}
	}
	
	public boolean verifExistAdherent(Adherent adherent) {
		boolean resultat = false;
		
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}try {
			
			String sql = "SELECT COUNT(*) AS adherentExist FROM adherent WHERE idAdherent = "+adherent.getId()+"";
			state = ConnexionDB.getInstance().createStatement();
			result = state.executeQuery( sql );
			
			while( result.next() ) {
				if(result.getInt("adherentExist") == 0) {
					resultat = false;
				}else {
					resultat = true;
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return resultat;
	}
	
	

	public void selectAdherents() {
		//�tablissement de la connexion
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}try {
			String sql = "SELECT * FROM adherent";
			state = ConnexionDB.getInstance().createStatement();
			//Etape 4 : ex�cution requ�te
			result = state.executeQuery( sql );
			
			//Etape 5 : (parcours Resultset
			while( result.next() ) {
				System.out.println( result.getInt( "idAdherent" ) );
				System.out.println( result.getString( "Nom" ) );
				System.out.println( result.getString( "Prenom" ) );
				System.out.println( result.getString( "CodePostal" ) );
				System.out.println( result.getString( "Ville" ) );
				System.out.println( result.getDate( "DateNaissance" ) );
				System.out.println( result.getInt( "TypeAdhesion" )+"\n" );
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}


	public void selectAdherent(int idAdherent) {
		//Chargement du driver JDBC pour MySQL
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
		
		//�tablissement de la connexion
		try {
			//Etape 4 : ex�cution requ�te
			String sql = "SELECT * FROM adherent WHERE idAdherent = '"+idAdherent+"'";
			//Etape 4 : ex�cution requ�te
			state = ConnexionDB.getInstance().createStatement();
			
			result = state.executeQuery( sql );
			//Etape 5 : (parcours Resultset
			while( result.next() ) {
				System.out.println( result.getInt( "idAdherent" ) );
				System.out.println( result.getString( "Nom" ) );
				System.out.println( result.getString( "Prenom" ) );
				System.out.println( result.getString( "CodePostal" ) );
				System.out.println( result.getString( "Ville" ) );
				System.out.println( result.getDate( "DateNaissance" ) );
				System.out.println( result.getInt( "TypeAdhesion" )+"\n" );
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateAdherent(Adherent adherent) {
		//Chargement du driver JDBC pour MySQL
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
		
		//�tablissement de la connexion
		try {
			//Cr�ation d'un statement
			String sql = "UPDATE adherent SET idAdherent = ?, Nom = ?, Prenom = ?, CodePostal = ?, Ville = ?"
					+ ", DateNaissance = ?";
			
			st = ConnexionDB.getInstance().prepareStatement( sql );
		
			st.setInt(1, adherent.getId());
			st.setString(2, adherent.getNom());
			st.setString(3, adherent.getPrenom());
			st.setString(4, adherent.getCodePostal());
			st.setString(5, adherent.getVille());
			st.setDate(6, adherent.getAnneeNaissance());
			//st.setObject(7, adherent.getTypeAdhesion());
			
			st.executeUpdate();
			st.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void supprimerAdherent(Adherent adherent) {
		//Chargement du driver JDBC pour MySQL
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
		
		//�tablissement de la connexion
		try {
			String sql = "DELETE FROM adherent WHERE idAdherent = ?";
			
			st = ConnexionDB.getInstance().prepareStatement( sql );
			
			st.setInt(1, adherent.getId());
			
			st.executeUpdate();
			System.out.println("La personne a �t� supprim� de la base de donn�e");
			
			st.close();
			
		} catch ( SQLException e ) {
			 e.printStackTrace();
		}
	}
}