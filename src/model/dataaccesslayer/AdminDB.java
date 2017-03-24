package model.dataaccesslayer;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.classe.Adherent;
import model.classe.TypeAdhesion;

public class AdminDB {
	//Variables membres
	private PreparedStatement st = null;
	private Statement state = null;
	private ResultSet result = null;
	
	//Permet de se connecter � l'application (v�rifie utilisateur et mdp correct � celui de la bdd)
	public boolean connexion(String utilisateur, String motdepasse) {
		//variables locales
		int count = 0;
		boolean valeur = false;
		
		try {
			//Chargement du driver JDBC pour MySQL
			Class.forName( "com.mysql.jdbc.Driver" );
		
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );	
		}
		
		try {
	  		//Cr�ation d'un statement
			String sql = "SELECT COUNT(*) AS nb FROM administrateur WHERE login = '"+utilisateur+"' AND motdepasse = '"+motdepasse+"'";
			//�tablissement de la connexion
			state = ConnexionDB.getInstance().createStatement();
			//ex�cution requ�te
			result = state.executeQuery( sql );
			
			//parcours Resultset
			while( result.next() ) {
				count = result.getInt( "nb" );
			}
			
			System.out.println(count);
			//Traduit la valeur avec le bon boolean
			if(count != 0) {
				valeur = true;
			}else {
				valeur = false;
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
		return valeur;
	}
	
	//R�cup�re la date de la derni�re visite
	public Date getDateDerVisite() {
		try {
			//Chargement du driver JDBC pour MySQL
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
		//Variable locale
		Date dateVisite = null;
		
		try {	
			//Cr�ation d'un statement
			String sql = "SELECT DateDerVisite FROM administrateur";
			//�tablissement de la connexion
			state = ConnexionDB.getInstance().createStatement();
			//ex�cution requ�te
			result = state.executeQuery( sql );
			
			//parcours Resultset
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
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}      	
		}
		return 	dateVisite;
	}
	
	
	//Met � jours la date pour celle actuelle
	public void updateDateDerVisite(Date dateAujour) {
		//variable locale
		java.sql.Date dateDB = new java.sql.Date(dateAujour.getTime());
		
		try {	
			//Chargement du driver JDBC pour MySQL
			Class.forName( "com.mysql.jdbc.Driver" );
				
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
			
		try {
			//Cr�ation d'un statement
			String sql = "UPDATE administrateur SET  DateDerVisite = ? ";
			//�tablissement de la connexion avec la bdd
			st = ConnexionDB.getInstance().prepareStatement( sql );
			//changement
			st.setDate(1,dateDB );
			//Ex�cution du changement
			st.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {	
		    try {
				if (!st.isClosed()) {
				    try {
				    	st.close();
				    } catch (SQLException e) { /* ignor� */}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	        	
		}
	}

	
}
