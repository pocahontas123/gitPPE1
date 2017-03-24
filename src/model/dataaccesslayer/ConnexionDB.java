package model.dataaccesslayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionDB {
	//Variables membres
	private static String url = "jdbc:mysql://localhost/adherentppe1?useSSL=false";
	private static String user = "root";
	private static String passwd = "";
	private static Connection connect;
	private static ConnexionDB instance = new ConnexionDB();

	//Se connect à la bdd
	private ConnexionDB() {
		try{
			connect = DriverManager.getConnection(url, user, passwd);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Crée une instance de la connection
	public static Connection getInstance() throws SQLException{
		if (connect.isClosed()) {
			instance = new ConnexionDB();
		}
		
		return connect;
	}
	
	//ferme la connexion
	public static void closeConnexion()  {
		try {
			if(!connect.isClosed()) {
				try {
					connect.close();
				
				}catch (SQLException e){
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
}