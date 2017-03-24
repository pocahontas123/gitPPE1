package model.dataaccesslayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.classe.TypeAdhesion;

public class TypeAdhesionDB {
	//variables membres
	PreparedStatement st = null;
	Statement state = null;
	ResultSet result = null;
	
	//Vérifie si un type d'ahesion existe	Retourn un true si OK
	public boolean verifExistTypeAdhesion(TypeAdhesion typeAdhesion)  {
		//variables membres
		boolean resultat = false;
		
		try {	
			//Chargement du driver JDBC pour MySQL
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
		
		try {
			//Création d'un statement
			String sql = "SELECT COUNT(*) AS TypeAdhesionExist FROM typeAdhesion WHERE idTypeAdhesion = "+typeAdhesion.getIdTypeAdhesion()+"";
			//Établissement de la connexion
			state = ConnexionDB.getInstance().createStatement();
			//exécution requête
			result = state.executeQuery( sql );
			
			//parcours Resultset
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
			try {
				if (!result.isClosed()) {
				    try {
				    	result.close();
				    } catch (SQLException e) { /* ignoré */}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    try {
				if (!state.isClosed()) {
				    try {
				    	state.close();
				    } catch (SQLException e) { /* ignoré */}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		        	
		}
		return resultat;
	}
	
	//Retourrne les infos d'un type adhésion par rapport à son libelle
	public TypeAdhesion getTypeAdhesion(String Libelle)  {
		try {	
			//Chargement du driver JDBC pour MySQL
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
		//variable locale
		TypeAdhesion leTypeAdhesion = null;
		
		try {
			//Création d'un statement
			String sql = "SELECT idTypeAdhesion, Libelle, Tarif from typeadhesion WHERE Libelle = '"+Libelle+"'";
			//Établissement de la connexion
			state = ConnexionDB.getInstance().createStatement();
			//exécution requête
			result = state.executeQuery( sql );
			
			//parcours Resultset)
			while( result.next() ) {
				int idType = result.getInt( "idTypeAdhesion" );
				String libelle = result.getString( "Libelle" );
				int tarif = result.getInt( "Tarif" );
				
				leTypeAdhesion = new TypeAdhesion(idType, libelle, tarif);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (!result.isClosed()) {
				    try {
				    	result.close();
				    } catch (SQLException e) { /* ignoré */}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    try {
				if (!state.isClosed()) {
				    try {
				    	state.close();
				    } catch (SQLException e) { /* ignoré */}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}   	
		}
		return leTypeAdhesion;
	}

	
	public ArrayList getTypeAdhesions()  {
		//Chargement du driver JDBC pour MySQL
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
		
		//variables locales
		TypeAdhesion leTypeAdhesion = null;
	    ArrayList Adhesion = new ArrayList();

		try {	
			//Établissement de la connexion
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
		
		try {
			//Création d'un statement
			String sql = "SELECT idTypeAdhesion, Libelle, Tarif from typeadhesion";	
			//Établissement de la connexion
			state = ConnexionDB.getInstance().createStatement();
			//exécution requête
			result = state.executeQuery( sql );
			
			//parcours Resultset
			while( result.next() ) {
				int idType = result.getInt( "idTypeAdhesion" );
				String libelle = result.getString( "Libelle" );
				int tarif = result.getInt( "Tarif" );
				
				//création d'un objet avec les informations
				leTypeAdhesion = new TypeAdhesion(idType, libelle, tarif);
				//Ajoute l'objet dans un tableau d'objet ArrayList
				Adhesion.add(leTypeAdhesion);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (!result.isClosed()) {
				    try {
				    	result.close();
				    } catch (SQLException e) { /* ignoré */}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    try {
				if (!state.isClosed()) {
				    try {
				    	state.close();
				    } catch (SQLException e) { /* ignoré */}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}       	
		}	
		return Adhesion;
	}
	
	//Retourne un objet typeAdhesion avec les informations sur un type adhesion par rapport à son id
	public TypeAdhesion getTypeAdhesion(int idTypeAdhesion)  {
		try {	
			//Chargement du driver JDBC pour MySQL
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
		
		//Variable locale
		TypeAdhesion leTypeAdhesion = null;
		
		try {	
			//Établissement de la connexion
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
		
		try {
			//Création d'un statement
			String sql = "SELECT idTypeAdhesion, Libelle, Tarif from typeadhesion WHERE idTypeAdhesion = '"+idTypeAdhesion+"'";
			//Établissement de la connexion
			state = ConnexionDB.getInstance().createStatement();
			//exécution requête
			result = state.executeQuery( sql );
			
			//parcours Resultset
			while( result.next() ) {
				int idType = result.getInt( "idTypeAdhesion" );
				String libelle = result.getString( "Libelle" );
				int tarif = result.getInt( "Tarif" );
				
				leTypeAdhesion = new TypeAdhesion(idType, libelle, tarif);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (!result.isClosed()) {
				    try {
				    	result.close();
				    } catch (SQLException e) { /* ignoré */}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    try {
				if (!state.isClosed()) {
				    try {
				    	state.close();
				    } catch (SQLException e) { /* ignoré */}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  	
		}	
		return leTypeAdhesion;
	}
	
	//Sauvegarde un NOUVEAU type adhesion
	public boolean saveTypeAdhesion(TypeAdhesion adhesion)  {
		//variable locale
		boolean existResult = false;
		
		try {	
			//Chargement du driver JDBC pour MySQL
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
		
		try {
			String sql;
			
			//Si je n'ai pas d'id de fourni
			if(adhesion.getIdTypeAdhesion() == -1) {
				//je mets NULL et j'aurais par concéquent un id automatique
				sql = "INSERT INTO typeadhesion (idTypeAdhesion, Libelle, Tarif) VALUES (NULL, ?, ?)";
				//Établissement de la connexion
				st = ConnexionDB.getInstance().prepareStatement( sql );
				
				//Modification des infos
				st.setString(1, adhesion.getLibelle());
				st.setInt(2, adhesion.getTarif());
				
				//exécution requête
				st.executeUpdate();				
			}else {
				//si j'ai un id fourni
				boolean exist = verifExistTypeAdhesion(adhesion);
				if(!exist) {
					//j'aurais l'id des paramètres
					sql = "INSERT INTO typeadhesion (idTypeAdhesion, Libelle, Tarif) VALUES (?, ?, ?)";
					//Établissement de la connexion
					st = ConnexionDB.getInstance().prepareStatement( sql );
					
					//Modification des infos
					st.setInt(1, adhesion.getIdTypeAdhesion());
					st.setString(2, adhesion.getLibelle());
					st.setInt(3, adhesion.getTarif());
					
					//exécution requête
					st.executeUpdate();	
					existResult = false;
				}else {
					existResult = true;
				}
			}		
		} catch ( SQLException e ) {
			 e.printStackTrace();
		}finally {
			
		    try {
				if (!st.isClosed()) {
				    try {
				    	st.close();
				    } catch (SQLException e) { /* ignoré */}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        	
	     
		}
		return existResult;
	}
	
	//Retourne true si suppresion type adhesion OK
	public boolean supprimerTypeAdhesion(TypeAdhesion adhesion) {
		//variables membres
		boolean existResult = false;
		
		try {	
			//Chargement du driver JDBC pour MySQL
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
		
		try {
			//Création d'un statement
			String sql = "DELETE FROM typeadhesion WHERE idTypeAdhesion= ?";
			//Établissement de la connexion
			st = ConnexionDB.getInstance().prepareStatement( sql );
			
			st.setInt(1, adhesion.getIdTypeAdhesion());
			
			//exécution requête
			st.executeUpdate();
			
			System.out.println("Le type d'adhésion a été supprimé de la base de donnée");
			existResult = true;
				
		} catch ( SQLException e ) {
			existResult = false;
			e.printStackTrace();
		}finally {
		    try {
				if (!st.isClosed()) {
				    try {
				    	st.close();
				    } catch (SQLException e) { /* ignoré */}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 	
		}
		return existResult;
	}
	
	//Met à jours une categorie (typeadhesion)
	public boolean updateCategorie(TypeAdhesion typeAdhesion)  {
		//variable locale
		boolean existResult = false;
		
		try {	
			//Chargement du driver JDBC pour MySQL
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
		
		try {
			//Création d'un statement
			String sql = "UPDATE typeAdhesion SET Libelle = ?, Tarif = ? WHERE idTypeAdhesion= ?";
			//Établissement de la connexion
			st = ConnexionDB.getInstance().prepareStatement( sql );
		
			st.setString(1, typeAdhesion.getLibelle());
			st.setInt(2, typeAdhesion.getTarif());
			st.setInt(3, typeAdhesion.getIdTypeAdhesion());
			
			//exécution requête
			st.executeUpdate();
			existResult = true;
			
		} catch(SQLException e) {
			existResult = false;
			e.printStackTrace();
		}finally {		
		    try {
				if (!st.isClosed()) {
				    try {
				    	st.close();
				    } catch (SQLException e) { /* ignoré */}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 	
		}
		return existResult;
	}
	
		
}
