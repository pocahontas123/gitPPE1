package model.dataaccesslayer;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.classe.Adherent;
import model.classe.TypeAdhesion;

public class TypeAdhesionDB {
	
	PreparedStatement st = null;
	Statement state = null;
	ResultSet result = null;
	
	public boolean verifExistTypeAdhesion(TypeAdhesion typeAdhesion) {
		boolean resultat = false;
		
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}try {
			
			String sql = "SELECT COUNT(*) AS TypeAdhesionExist FROM typeAdhesion WHERE idTypeAdhesion = "+typeAdhesion.getIdTypeAdhesion()+"";
			state = ConnexionDB.getInstance().createStatement();
			result = state.executeQuery( sql );
			
			while( result.next() ) {
				if(result.getInt("TypeAdhesionExist") == 0) {
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
	
	
	
	public TypeAdhesion getTypeAdhesion(String Libelle) {
		//Chargement du driver JDBC pour MySQL
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}

		TypeAdhesion leTypeAdhesion = null;
		
		//Établissement de la connexion
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}try {
			String sql = "SELECT idTypeAdhesion, Libelle, Tarif from typeadhesion WHERE Libelle = '"+Libelle+"'";
			

			state = ConnexionDB.getInstance().createStatement();
			//Etape 4 : exécution requête
			result = state.executeQuery( sql );
			
			//Etape 5 : (parcours Resultset)
			while( result.next() ) {
				int idType = result.getInt( "idTypeAdhesion" );
				String libelle = result.getString( "Libelle" );
				int tarif = result.getInt( "Tarif" );
				
				leTypeAdhesion = new TypeAdhesion(idType, libelle, tarif);
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
		return leTypeAdhesion;
	}

	
	public ArrayList getTypeAdhesions() {
		//Chargement du driver JDBC pour MySQL
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
		
		//information d'accès à la base de données
		TypeAdhesion leTypeAdhesion = null;
		
	    ArrayList Adhesion = new ArrayList();

		//Établissement de la connexion
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}try {
			String sql = "SELECT idTypeAdhesion, Libelle, Tarif from typeadhesion";
			
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
				Adhesion.add(leTypeAdhesion);
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
		return Adhesion;
	}
	
	
	
	public boolean saveTypeAdhesion(TypeAdhesion adhesion) {
		boolean existResult=false;
		
		//Chargement du driver JDBC pour MySQL
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
		//Établissement de la connexion
		try {
			String sql;
			
			if(adhesion.getIdTypeAdhesion() == -1) {
				sql = "INSERT INTO typeadhesion (NULL, Libelle, Tarif) VALUES (?, ?, ?)";
				
				st = ConnexionDB.getInstance().prepareStatement( sql );

				st.setString(2, adhesion.getLibelle());
				st.setInt(3, adhesion.getTarif());
				
				//Etape 4 : exécution requête
				st.executeUpdate();				
			}else {
				boolean exist = verifExistTypeAdhesion(adhesion);
				if(!exist) {
					sql = "INSERT INTO typeadhesion (idTypeAdhesion, Libelle, Tarif) VALUES (?, ?, ?)";
					
					st = ConnexionDB.getInstance().prepareStatement( sql );
					
					st.setInt(1, adhesion.getIdTypeAdhesion());
					st.setString(2, adhesion.getLibelle());
					st.setInt(3, adhesion.getTarif());
					
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
