package model.dataaccesslayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.classe.TypeAdhesion;

public class TypeAdhesionDB {
	
	PreparedStatement st = null;
	Statement state = null;
	ResultSet result = null;
	
	
	public boolean verifExistTypeAdhesion(TypeAdhesion typeAdhesion)  {
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
			try {
				if (!result.isClosed()) {
				    try {
				    	result.close();
				    } catch (SQLException e) { /* ignor� */}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    try {
				if (!state.isClosed()) {
				    try {
				    	state.close();
				    } catch (SQLException e) { /* ignor� */}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		        	
		}
		return resultat;
	}
	
	
	public TypeAdhesion getTypeAdhesion(String Libelle)  {
		//Chargement du driver JDBC pour MySQL
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}

		TypeAdhesion leTypeAdhesion = null;
		
	
		try {
			String sql = "SELECT idTypeAdhesion, Libelle, Tarif from typeadhesion WHERE Libelle = '"+Libelle+"'";
			

			state = ConnexionDB.getInstance().createStatement();
			//Etape 4 : ex�cution requ�te
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
			try {
				if (!result.isClosed()) {
				    try {
				    	result.close();
				    } catch (SQLException e) { /* ignor� */}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    try {
				if (!state.isClosed()) {
				    try {
				    	state.close();
				    } catch (SQLException e) { /* ignor� */}
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
		}finally {
			try {
				if (!result.isClosed()) {
				    try {
				    	result.close();
				    } catch (SQLException e) { /* ignor� */}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    try {
				if (!state.isClosed()) {
				    try {
				    	state.close();
				    } catch (SQLException e) { /* ignor� */}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        	
		}	
		return Adhesion;
	}
	
	
	public TypeAdhesion getTypeAdhesion(int idTypeAdhesion)  {
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
			String sql = "SELECT idTypeAdhesion, Libelle, Tarif from typeadhesion WHERE idTypeAdhesion = '"+idTypeAdhesion+"'";
			
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
		}finally {
			try {
				if (!result.isClosed()) {
				    try {
				    	result.close();
				    } catch (SQLException e) { /* ignor� */}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    try {
				if (!state.isClosed()) {
				    try {
				    	state.close();
				    } catch (SQLException e) { /* ignor� */}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        	
		}	
		return leTypeAdhesion;
	}
	
	
	public boolean saveTypeAdhesion(TypeAdhesion adhesion)  {
		boolean existResult = false;
		
		//Chargement du driver JDBC pour MySQL
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
		//�tablissement de la connexion
		try {
			String sql;
			
			//Si je n'ai pas d'id de fourni
			if(adhesion.getIdTypeAdhesion() == -1) {
				//je mets NULL et j'aurais par conc�quent un id automatique
				sql = "INSERT INTO typeadhesion (idTypeAdhesion, Libelle, Tarif) VALUES (NULL, ?, ?)";
				
				st = ConnexionDB.getInstance().prepareStatement( sql );

				st.setString(1, adhesion.getLibelle());
				st.setInt(2, adhesion.getTarif());
				
				//Etape 4 : ex�cution requ�te
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
			
		    try {
				if (!st.isClosed()) {
				    try {
				    	st.close();
				    } catch (SQLException e) { /* ignor� */}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        	
	     
		}
		return existResult;
	}
	
	public boolean supprimerTypeAdhesion(TypeAdhesion adhesion) {
		boolean existResult = false;
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
			
			st.setInt(1, adhesion.getIdTypeAdhesion());
			
			st.executeUpdate();
			System.out.println("Le type d'adh�sion a �t� supprim� de la base de donn�e");
			existResult = true;
				
		} catch ( SQLException e ) {
			existResult = false;
			e.printStackTrace();
		}finally {
		    try {
				if (!st.isClosed()) {
				    try {
				    	st.close();
				    } catch (SQLException e) { /* ignor� */}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	     
	        	
		}
		return existResult;
	}
	
	public boolean updateCategorie(TypeAdhesion typeAdhesion)  {
		boolean existResult = false;
		//Chargement du driver JDBC pour MySQL
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
		
		//�tablissement de la connexion
		try {
			//Cr�ation d'un statement
			String sql = "UPDATE typeAdhesion SET Libelle = ?, Tarif = ? WHERE idTypeAdhesion= ?";
			
			st = ConnexionDB.getInstance().prepareStatement( sql );
		
			st.setString(1, typeAdhesion.getLibelle());
			st.setInt(2, typeAdhesion.getTarif());
			st.setInt(3, typeAdhesion.getIdTypeAdhesion());
			
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
				    } catch (SQLException e) { /* ignor� */}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	       
	        	
		}
		return existResult;
	}
	
		
}
