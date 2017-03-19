package model.dataaccesslayer;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.classe.Adherent;
import model.classe.TypeAdhesion;

public class AdminDB {
	private PreparedStatement st = null;
	private Statement state = null;
	private ResultSet result = null;
	
	public Date getDateDerVisite() {
		//Chargement du driver JDBC pour MySQL
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
		
		Date dateVisite = null;
		
		//Établissement de la connexion
		try {
			//Etape 4 : exécution requête
			String sql = "SELECT DateDerVisite FROM administrateur";
			//Etape 4 : exécution requête
			state = ConnexionDB.getInstance().createStatement();
			
			result = state.executeQuery( sql );
			
			//Etape 5 : (parcours Resultset
		 if(result.next()){
				
				dateVisite = result.getDate( "DateDerVisite" );

			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
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
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        	
		}
		return 	dateVisite;
	}
	
	

	public void updateDateDerVisite(Date dateAujour) {
		
		java.sql.Date dateDB = new java.sql.Date(dateAujour.getTime());
			//Chargement du driver JDBC pour MySQL
			try {	
				Class.forName( "com.mysql.jdbc.Driver" );
				
			} catch ( ClassNotFoundException e ) {
				System.out.println( "Erreur chargement du driver: " +e.getMessage() );
			}
			
			//Établissement de la connexion
			try {
				//Création d'un statement
				String sql = "UPDATE administrateur SET  DateDerVisite = ? ";
				
				
				st = ConnexionDB.getInstance().prepareStatement( sql );
				st.setDate(1,dateDB );
				
				
				st.executeUpdate();
				
			} catch(SQLException e) {
				e.printStackTrace();
			}finally {	
			    try {
					if (!st.isClosed()) {
					    try {
					    	st.close();
					    } catch (SQLException e) { /* ignoré */}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			        	
			}
		}

		
	
	
}
