package model.dataaccesslayer;

import java.io.Serializable;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.classe.Adherent;
import model.classe.TypeAdhesion;
import model.dataaccesslayer.ConnexionDB;



public class AdherentDB {
	private PreparedStatement st = null;
	private Statement state = null;
	private ResultSet result = null;
	
	public boolean saveAdherent(Adherent adherent) {
		boolean existResult=false;
		//Chargement du driver JDBC pour MySQL
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}

		try {
			String sql;
		
			if(adherent.getId() == -1) {
				boolean exist = verifExistAdherent(adherent);
				if(!exist) {
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
					existResult = true;
				}
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
					existResult = false;
				}else {
					existResult = true;
				}
				
				
			}
			
		} catch ( SQLException e ) {
			 e.printStackTrace();
		}finally {
			
		    if (st != null) {
		        try {
		        	st.close();
		        } catch (SQLException e) { /* ignoré */}
		    }
		        try {
		        	ConnexionDB.closeConnexion();
		        } catch (SQLException e) { /* ignoré */}
		    
			
		}
		
		return existResult;

	}
	
	public boolean verifExistAdherent(Adherent adherent) {
		boolean resultat = false;
		
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}try {
			
			String sql = "SELECT COUNT(*) AS adherentExist FROM adherent WHERE Nom = '"+adherent.getNom()+"' AND Prenom = '"+adherent.getPrenom()+"'";
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
		}finally {
			if (result != null) {
		        try {
		        	result.close();
		        } catch (SQLException e) { /* ignoré */}
		    }
		    if (state != null) {
		        try {
		        	state.close();
		        } catch (SQLException e) { /* ignoré */}
		    }
		        try {
		        	ConnexionDB.closeConnexion();
		        } catch (SQLException e) { /* ignoré */}	
		}
		return resultat;
	}
	
	

	public Adherent selectAdherents() {
		//Établissement de la connexion
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );	
		}
		
		Adherent lesAdherents = null;
		
		try {
			String sql = "SELECT * FROM adherent";
			state = ConnexionDB.getInstance().createStatement();
			//Etape 4 : exécution requête
			result = state.executeQuery( sql );
		
			//Etape 5 : (parcours Resultset
			while( result.next() ) {
				int idAdherent = result.getInt( "idAdherent" );
				String nom = result.getString( "Nom" );
				String prenom =  result.getString( "Prenom" );
				String codePostal = result.getString( "CodePostal" ) ;
				String ville = result.getString( "Ville" );
				Date dateNaissance = result.getDate( "DateNaissance" );
				TypeAdhesion typeAdhesion = (TypeAdhesion) result.getObject( "TypeAdhesion" );

				lesAdherents = new Adherent(idAdherent, nom, prenom, codePostal, ville, dateNaissance, typeAdhesion);
			
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if (result != null) {
		        try {
		        	result.close();
		        } catch (SQLException e) { /* ignoré */}
		    }
		    if (state != null) {
		        try {
		        	state.close();
		        } catch (SQLException e) { /* ignoré */}
		    }
	        try {
	        	ConnexionDB.closeConnexion();
	        } catch (SQLException e) { /* ignoré */}
		}
		return lesAdherents;
	}


	public Adherent selectAdherent(int idAdherent) {
		//Chargement du driver JDBC pour MySQL
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
		
		Adherent leAdherent = null;
		
		//Établissement de la connexion
		try {
			//Etape 4 : exécution requête
			String sql = "SELECT * FROM adherent WHERE idAdherent = '"+idAdherent+"'";
			//Etape 4 : exécution requête
			state = ConnexionDB.getInstance().createStatement();
			
			result = state.executeQuery( sql );
			
			//Etape 5 : (parcours Resultset
			while( result.next() ) {
				int idDeAdherent = result.getInt( "idAdherent" );
				String nom = result.getString( "Nom" );
				String prenom =  result.getString( "Prenom" );
				String codePostal = result.getString( "CodePostal" ) ;
				String ville = result.getString( "Ville" );
				Date dateNaissance = result.getDate( "DateNaissance" );
				TypeAdhesion typeAdhesion = (TypeAdhesion) result.getObject( "TypeAdhesion" );
				
				leAdherent = new Adherent(idDeAdherent, nom, prenom, codePostal, ville, dateNaissance, typeAdhesion);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if (result != null) {
		        try {
		        	result.close();
		        } catch (SQLException e) { /* ignoré */}
		    }
		    if (state != null) {
		        try {
		        	state.close();
		        } catch (SQLException e) { /* ignoré */}
		    }
		        try {
		        	ConnexionDB.closeConnexion();
		        } catch (SQLException e) { /* ignoré */}
		    return 	leAdherent;	
		}
	}

	public void updateAdherent(Adherent adherent) {
		//Chargement du driver JDBC pour MySQL
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
		
		//Établissement de la connexion
		try {
			//Création d'un statement
			String sql = "UPDATE adherent SET idAdherent = ?, Nom = ?, Prenom = ?, CodePostal = ?, Ville = ?"
					+ ", DateNaissance = ? , TypeAdhesion = ? WHERE idAdherent= ?";
			
			st = ConnexionDB.getInstance().prepareStatement( sql );
		
			st.setInt(1, adherent.getId());
			st.setString(2, adherent.getNom());
			st.setString(3, adherent.getPrenom());
			st.setString(4, adherent.getCodePostal());
			st.setString(5, adherent.getVille());
			st.setDate(6, adherent.getAnneeNaissance());
			st.setInt(7, adherent.getTypeAdhesion().getIdTypeAdhesion());
			st.setInt(8, adherent.getId());
			
			st.executeUpdate();
			st.close();
			ConnexionDB.closeConnexion();

		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			
		    if (st != null) {
		        try {
		        	st.close();
		        } catch (SQLException e) { /* ignoré */}
		    }
		        try {
		        	ConnexionDB.closeConnexion();
		        } catch (SQLException e) { /* ignoré */}
		}
	}

	public void supprimerAdherent(Adherent adherent) {
		//Chargement du driver JDBC pour MySQL
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
		
		//Établissement de la connexion
		try {
			String sql = "DELETE FROM adherent WHERE idAdherent = ?";
			
			st = ConnexionDB.getInstance().prepareStatement( sql );
			
			st.setInt(1, adherent.getId());
			
			st.executeUpdate();
			System.out.println("La personne a été supprimé de la base de donnée");
			
			

			
		} catch ( SQLException e ) {
			 e.printStackTrace();
		}finally {
			
		    if (st != null) {
		        try {
		        	st.close();
		        } catch (SQLException e) { /* ignoré */}
		    }
		        try {
		        	ConnexionDB.closeConnexion();
		        } catch (SQLException e) { /* ignoré */}	
		}
	}
}
