package DataAcessLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Classes.TypeAdhesion;

public class TypeAdhesionDB {
	
	PreparedStatement st = null;
	Statement state = null;
	ResultSet result = null;
	
	
	//récupère ce que rapporte 1 type d'adhesion particulier
	public int getTotalAdhesion(int idAdhesion) throws SQLException {
		//Chargement du driver JDBC pour MySQL
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
		String sql = "SELECT Tarif from typeadhesion WHERE idTypeAdhesion = '"+idAdhesion+"'";
		
		state = ConnexionDB.getInstance().createStatement();
		result = state.executeQuery( sql );

		int tarif = 0;
		while( result.next() ) {
			tarif = result.getInt( "Tarif" );
			//System.out.println(tarif);
		} 
		
		sql = "SELECT COUNT(*) AS nbAdherents from adherent WHERE TypeAdhesion = '"+idAdhesion+"'";
		result = state.executeQuery( sql );
		
		int nb = 0;
		while( result.next() ) {
			nb = result.getInt( "nbAdherents" );

		} 
		return ( tarif * nb);
	}
	
	public int getTotalAdhesions() throws SQLException {
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
		//get the number of different type of adhesion i have
		String sql = "SELECT COUNT(*) AS nbTypeAdhesion from typeadhesion";
		result = state.executeQuery( sql );
		
		int nbAdhesionsDiff = 0;
		while( result.next()) {
			nbAdhesionsDiff = result.getInt("nbTypeAdhesion");		
		}

		int resultat = 0;
		
		//for every different type i use getTarifAdhesion(i) on it
		//IMPORTANT: database must be like this 1, 2, 3, 4, 5 and not like this 1, 3, 2, 5, 6 ...
		for(int i = 1; i <= nbAdhesionsDiff; i++) {
			resultat = resultat + getTotalAdhesion(i);
			//System.out.println(resultat);
		}
		return resultat;
	}
	
	
	
	public TypeAdhesion getTypeAdhesion(int idAdherent) {
		//Chargement du driver JDBC pour MySQL
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
		
		//information d'accès à la base de données
		TypeAdhesion leTypeAdhesion = null;
		
		try {
			String sql = "SELECT idTypeAdhesion, Libelle, Tarif from typeadhesion WHERE idTypeAdhesion = '"+idAdherent+"'";
			
			//Etape 4 : exécution requête
			state = ConnexionDB.getInstance().createStatement();
			//Etape 4 : exécution requête
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
	
	public void saveTypeAdhesion(TypeAdhesion adhesion) {
		//Chargement du driver JDBC pour MySQL
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
		//Établissement de la connexion
		try {	
			String sql = "INSERT INTO typeadhesion (idTypeAdhesion, Libelle, Tarif) VALUES (?, ?, ?)";
			
			st = ConnexionDB.getInstance().prepareStatement( sql );
			
			st.setInt(1, adhesion.getIdTypeAdhesion());
			st.setString(2, adhesion.getLibelle());
			st.setInt(3, adhesion.getTarif());
			
			//Etape 4 : exécution requête
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
		//Établissement de la connexion
		try {
			String sql = "DELETE FROM typeadhesion WHERE idTypeAdhesion= ?";
			
			st = ConnexionDB.getInstance().prepareStatement( sql );
			
			st.setInt(1, idTypeAdherent);
			
			st.executeUpdate();
			System.out.println("Le type d'adhésion a été supprimé de la base de donnée");
			
			st.close();
		} catch ( SQLException e ) {
			 e.printStackTrace();
		}
	}
}
