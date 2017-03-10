package model.dataaccesslayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionDB {

  private static String url = "jdbc:mysql://localhost/adherentppe1?useSSL=false";
  private static String user = "root";
  private static String passwd = "";
  private static Connection connect;
  private static ConnexionDB instance = new ConnexionDB();

	private ConnexionDB() {
		try{
		
			connect = DriverManager.getConnection(url, user, passwd);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getInstance() throws SQLException{
		if (connect.isClosed()) {
			instance = new ConnexionDB();
		}
		
		return connect;
	}
	
	public static void closeConnexion() throws SQLException {
	
		if(!connect.isClosed()) {
			try {
				connect.close();
			
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		
	}
}