package model.dataaccesslayer;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.classe.Adherent;
import model.classe.TypeAdhesion;
import model.dataaccesslayer.TypeAdhesionDB;
import model.dataaccesslayer.ConnexionDB;



public class AdherentDB {
	private PreparedStatement st = null;
	private Statement state = null;
	private ResultSet result = null;
	
	public ArrayList getRecap() {
		//Établissement de la connexion
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
		
		int i = 0;
		ArrayList<ArrayList> k=new ArrayList();
        
     
       		try {
			String sql = "SELECT typeadhesion.Tarif * COUNT(*) as prixtotalparcat, Libelle , COUNT(*) AS nombreAdparcat FROM `adherent` INNER JOIN typeadhesion ON typeadhesion.idTypeAdhesion = adherent.TypeAdhesion GROUP BY adherent.TypeAdhesion";
			state = ConnexionDB.getInstance().createStatement();
			//Etape 4 : exécution requête
			result = state.executeQuery( sql );
			
			//Etape 5 : (parcours Resultset
			while( result.next() ) {
				
				
			
				
					
				
				int nbAdhCat = result.getInt( "nombreAdparcat" );
				String libelle = result.getString( "Libelle" );
				

				int prixTotCat = result.getInt( "prixtotalparcat" );
		        ArrayList l=new ArrayList();

				l.add(nbAdhCat);
		        l.add(libelle);
		        l.add(prixTotCat);
		        k.add(i,l);
				i++;

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
		return k;

	}
	public void updatePaiementsAdherents() {
		
			//Chargement du driver JDBC pour MySQL
			try {	
				Class.forName( "com.mysql.jdbc.Driver" );
				
			} catch ( ClassNotFoundException e ) {
				System.out.println( "Erreur chargement du driver: " +e.getMessage() );
			}
			
			//Établissement de la connexion
			try {
				//Création d'un statement
				String sql = "UPDATE adherent SET  Paiement = false ";
				
				st = ConnexionDB.getInstance().prepareStatement( sql );
			
				
				
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
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			        	
			}
		}

		
	
	public int compterAdherentsPayes()  {
		int count = 0;
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}try {
			String sql = "SELECT COUNT(*) AS nbAdherentsPayes FROM adherent WHERE paiement = 1";
			state = ConnexionDB.getInstance().createStatement();
			result = state.executeQuery( sql );
			
			while(result.next()) {
				count = result.getInt("nbAdherentsPayes");	
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
		return count;
	}
	
	public int compterAdherents() {
		int count = 0;
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}try {
			String sql = "SELECT COUNT(*) AS nbAdherents FROM adherent";
			state = ConnexionDB.getInstance().createStatement();
			result = state.executeQuery( sql );
			
			while(result.next()) {
				count = result.getInt("nbAdherents");	
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
		return count;
	}
	
	
	
	public ArrayList afficherAdherentsPasPayer() {
		
		Adherent leAdherent = null;
		ArrayList Adherents = new ArrayList();
		
		try {	
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );

		}
		try {
			String sql = "SELECT * FROM adherent WHERE paiement = 0";
			state = ConnexionDB.getInstance().createStatement();
			result = state.executeQuery( sql );
			
			while( result.next() ) {
				int idAdherent = result.getInt( "idAdherent" );
				String nom1 = result.getString( "Nom" );
				String prenom =  result.getString( "Prenom" );
				String codePostal = result.getString( "CodePostal" ) ;
				String ville = result.getString( "Ville" );
				Date dateNaissance = result.getDate( "DateNaissance" );
				int idTypeAdhesion = result.getInt( "TypeAdhesion" );
				String telephone = result.getString( "Telephone" );
				String email = result.getString( "Email" );
				boolean paiement = result.getBoolean( "Paiement" );
				
				TypeAdhesionDB adhesionDB = new TypeAdhesionDB();
				TypeAdhesion typeAdhesion = adhesionDB.getTypeAdhesion(idTypeAdhesion);
				   

				leAdherent = new Adherent(idAdherent, nom1, prenom, codePostal, ville, dateNaissance, typeAdhesion, telephone, email, paiement);
				Adherents.add(leAdherent);
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
		return Adherents;
	}
	
	
	public ArrayList rechercheNomsAdherents(String nom) {
		//Établissement de la connexion
		try {
			Class.forName( "com.mysql.jdbc.Driver" );
		
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );	
		}
		
		Adherent leAdherent = null;
		
		ArrayList Adherents = new ArrayList();
		
		try {
			String sql = "SELECT * FROM adherent WHERE Nom LIKE '%"+nom+"%'";
			state = ConnexionDB.getInstance().createStatement();
			//Etape 4 : exécution requête
			result = state.executeQuery( sql );
		
			//Etape 5 : (parcours Resultset
			while( result.next() ) {
				int idAdherent = result.getInt( "idAdherent" );
				String nom1 = result.getString( "Nom" );
				String prenom =  result.getString( "Prenom" );
				String codePostal = result.getString( "CodePostal" ) ;
				String ville = result.getString( "Ville" );
				Date dateNaissance = result.getDate( "DateNaissance" );
				int idTypeAdhesion = result.getInt( "TypeAdhesion" );
				String telephone = result.getString( "Telephone" );
				String email = result.getString( "Email" );
				boolean paiement = result.getBoolean( "Paiement" );
				
				TypeAdhesionDB adhesionDB = new TypeAdhesionDB();
				TypeAdhesion typeAdhesion = adhesionDB.getTypeAdhesion(idTypeAdhesion);
				   

				leAdherent = new Adherent(idAdherent, nom1, prenom, codePostal, ville, dateNaissance, typeAdhesion, telephone, email, paiement);
				Adherents.add(leAdherent);
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
		return Adherents;
	}
	
	public ArrayList recherchePrenomsAdherents(String prenomRecherche) {
		//Établissement de la connexion
		try {
			Class.forName( "com.mysql.jdbc.Driver" );
		
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );	
		}
		
		Adherent leAdherent = null;
		
		ArrayList Adherents = new ArrayList();
		
		try {
			String sql = "SELECT * FROM adherent WHERE Prenom LIKE '%"+prenomRecherche+"%'";
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
				int idTypeAdhesion = result.getInt( "TypeAdhesion" );
				String telephone = result.getString( "Telephone" );
				String email = result.getString( "Email" );
				boolean paiement = result.getBoolean( "Paiement" );
				
				TypeAdhesionDB adhesionDB = new TypeAdhesionDB();
				TypeAdhesion typeAdhesion = adhesionDB.getTypeAdhesion(idTypeAdhesion);
				   

				leAdherent = new Adherent(idAdherent, nom, prenom, codePostal, ville, dateNaissance, typeAdhesion, telephone, email, paiement);
				Adherents.add(leAdherent);
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
		return Adherents;
	}	
	
	
	public boolean saveAdherent(Adherent adherent) {
		boolean existResult = false;
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
				sql = "INSERT INTO adherent VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				st = ConnexionDB.getInstance().prepareStatement( sql );
				st.setString(1, adherent.getNom());
				st.setString(2, adherent.getPrenom());
				st.setString(3, adherent.getCodePostal());
				st.setString(4, adherent.getVille());
				st.setDate(5, adherent.getAnneeNaissance());
				st.setInt(6, adherent.getTypeAdhesion().getIdTypeAdhesion());
				st.setString(7, adherent.getTelephone());
				st.setString(8, adherent.getEmail());
				st.setBoolean(9, adherent.getPaiement());
				
				st.executeUpdate();
				}else {
					existResult = true;
				}
			}else {
				boolean exist = verifExistAdherent(adherent);
				if(!exist) {			
					sql = "INSERT INTO adherent VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					
					st = ConnexionDB.getInstance().prepareStatement( sql );
					
					st.setInt(1, adherent.getId());
					st.setString(2, adherent.getNom());
					st.setString(3, adherent.getPrenom());
					st.setString(4, adherent.getCodePostal());
					st.setString(5, adherent.getVille());
					st.setDate(6, adherent.getAnneeNaissance());
					st.setInt(7, adherent.getTypeAdhesion().getIdTypeAdhesion());
					st.setString(8, adherent.getTelephone());
					st.setString(9, adherent.getEmail());
					st.setBoolean(10, adherent.getPaiement());
					
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
	
	public boolean verifExistAdherent(Adherent adherent)  {
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
	
	
	
	public ArrayList selectAdherents()  {
		//Établissement de la connexion
		try {
			Class.forName( "com.mysql.jdbc.Driver" );
		
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );	
		}
		
		Adherent leAdherent = null;
		
		ArrayList Adherents = new ArrayList();
		
		try {
			String sql = "SELECT * FROM adherent";
			state = ConnexionDB.getInstance().createStatement();
			//Etape 4 : exécution requête
			result = state.executeQuery( sql );
			
			while( result.next() ) {
				
				int idAdherent = result.getInt( "idAdherent" );
				String nom = result.getString( "Nom" );
				String prenom =  result.getString( "Prenom" );
				String codePostal = result.getString( "CodePostal" ) ;
				String ville = result.getString( "Ville" );
				Date dateNaissance = result.getDate( "DateNaissance" );
				int idTypeAdhesion = result.getInt( "TypeAdhesion" );
				String telephone = result.getString( "Telephone" );
				String email = result.getString( "Email" );
				boolean paiement = result.getBoolean( "Paiement" );
				
				TypeAdhesionDB adhesionDB = new TypeAdhesionDB();
				TypeAdhesion typeAdhesion = adhesionDB.getTypeAdhesion(idTypeAdhesion);
				   

				leAdherent = new Adherent(idAdherent, nom, prenom, codePostal, ville, dateNaissance, typeAdhesion, telephone, email, paiement);
				Adherents.add(leAdherent);
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
		return Adherents;
	}


	public Adherent getAdherent(int idAdherent) {
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
				int idTypeAdhesion = result.getInt( "TypeAdhesion" );
				String telephone = result.getString( "Telephone" );
				String email = result.getString( "Email" );
				boolean paiement = result.getBoolean( "Paiement" );

				TypeAdhesionDB adhesionDB = new TypeAdhesionDB();
				TypeAdhesion typeAdhesion = adhesionDB.getTypeAdhesion(idTypeAdhesion);
				   

				leAdherent = new Adherent(idDeAdherent, nom, prenom, codePostal, ville, dateNaissance, typeAdhesion, telephone, email, paiement);
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
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
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
		return 	leAdherent;
	}

	
	public Adherent getAdherent(String nom, String Prenom)  {
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
			String sql = "SELECT * FROM adherent WHERE Nom = '"+nom+"' AND Prenom = '"+Prenom+"'";
			//Etape 4 : exécution requête
			state = ConnexionDB.getInstance().createStatement();
			
			result = state.executeQuery( sql );
			
			//Etape 5 : (parcours Resultset
			while( result.next() ) {
				int idDeAdherent = result.getInt( "idAdherent" );
				String nomB = result.getString( "Nom" );
				String prenom =  result.getString( "Prenom" );
				String codePostal = result.getString( "CodePostal" ) ;
				String ville = result.getString( "Ville" );
				Date dateNaissance = result.getDate( "DateNaissance" );
				int idTypeAdhesion = result.getInt( "TypeAdhesion" );
				String telephone = result.getString( "Telephone" );
				String email = result.getString( "Email" );
				boolean paiement = result.getBoolean( "Paiement" );

				TypeAdhesionDB adhesionDB = new TypeAdhesionDB();
				TypeAdhesion typeAdhesion=adhesionDB.getTypeAdhesion(idTypeAdhesion);
				   

				leAdherent = new Adherent(idDeAdherent, nomB, prenom, codePostal, ville, dateNaissance, typeAdhesion, telephone, email, paiement);
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
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        	
		}
		return 	leAdherent;
	}
	
	
	public boolean updateAdherent(Adherent adherent)  {
		boolean existResult = false;
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
					+ ", DateNaissance = ?, TypeAdhesion = ?, Telephone = ?, Email = ?, Paiement = ? WHERE idAdherent = ?";
			
			st = ConnexionDB.getInstance().prepareStatement( sql );
		
			st.setInt(1, adherent.getId());
			st.setString(2, adherent.getNom());
			st.setString(3, adherent.getPrenom());
			st.setString(4, adherent.getCodePostal());
			st.setString(5, adherent.getVille());
			st.setDate(6, adherent.getAnneeNaissance());
			st.setInt(7, adherent.getTypeAdhesion().getIdTypeAdhesion());
			st.setString(8, adherent.getTelephone());
			st.setString(9, adherent.getEmail());
			st.setBoolean(10, adherent.getPaiement());
			st.setInt(11, adherent.getId());
			
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

	public boolean supprimerAdherent(Adherent adherent)  {
		boolean existResult = false;
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
}
