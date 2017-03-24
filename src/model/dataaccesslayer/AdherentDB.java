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
	
	//variable membres
	private PreparedStatement st = null;
	private Statement state = null;
	private ResultSet result = null;
	
	public ArrayList getRecap() {
		try {	
			//Chargement du driver JDBC pour MySQL
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
		
		//variables locales
		int i = 0;
		ArrayList<ArrayList> k = new ArrayList();
        
	  	try {
	  		//Création d'un statement
	   		String sql = "SELECT typeadhesion.Tarif * COUNT(*) as prixtotalparcat, Libelle , COUNT(*) AS nombreAdparcat FROM `adherent` INNER JOIN typeadhesion ON typeadhesion.idTypeAdhesion = adherent.TypeAdhesion GROUP BY adherent.TypeAdhesion";
			//Établissement de la connexion avec la bdd
	   		state = ConnexionDB.getInstance().createStatement();
			//exécution requête
			result = state.executeQuery( sql );
			
			//parcours Resultset
			while( result.next() ) {	
				int nbAdhCat = result.getInt( "nombreAdparcat" );
				String libelle = result.getString( "Libelle" );
				int prixTotCat = result.getInt( "prixtotalparcat" );
		        ArrayList l = new ArrayList();
	
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
	
	//Met les Paiement des adhérents en FALSE
	public void updatePaiementsAdherents() {
			try {	
				//Chargement du driver JDBC pour MySQL
				Class.forName( "com.mysql.jdbc.Driver" );
				
			} catch ( ClassNotFoundException e ) {
				System.out.println( "Erreur chargement du driver: " +e.getMessage() );
			}
			
			try {
				//Création d'un statement
				String sql = "UPDATE adherent SET  Paiement = false ";
				//Établissement de la connexion
				st = ConnexionDB.getInstance().prepareStatement( sql );
				//exécution requête
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

		
	//Retourne le nombre d'adhérent qui on payé
	public int compterAdherentsPayes()  {
		//variable local
		int count = 0;
		
		try {	
			//Chargement du driver JDBC pour MySQL
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );	
		}
		
		try {
			//Création d'un statement
			String sql = "SELECT COUNT(*) AS nbAdherentsPayes FROM adherent WHERE paiement = 1";
			//Établissement de la connexion
			state = ConnexionDB.getInstance().createStatement();
			//exécution requête
			result = state.executeQuery( sql );
			
			//parcours Resultset
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
	
	//Compte et return le nombre d'adhérent
	public int compterAdherents() {
		//variable local
		int count = 0;
		
		try {	
			//Chargement du driver JDBC pour MySQL
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
		
		try {
			//Création d'un statement
			String sql = "SELECT COUNT(*) AS nbAdherents FROM adherent";
			//Établissement de la connexion
			state = ConnexionDB.getInstance().createStatement();
			//exécution requête
			result = state.executeQuery( sql );
			
			//parcours Resultset
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
	
	
	//Retourne les adhérents qui n'ont pas payés
	public ArrayList afficherAdherentsPasPayer() {
		//Variables locales
		Adherent leAdherent = null;
		ArrayList Adherents = new ArrayList();
		
		try {	
			//Chargement du driver JDBC pour MySQL
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );

		}
		
		try {
			//Création d'un statement
			String sql = "SELECT * FROM adherent WHERE paiement = 0";
			//Établissement de la connexion
			state = ConnexionDB.getInstance().createStatement();
			//exécution requête
			result = state.executeQuery( sql );
			
			//parcours Resultset
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
				   
				//crée un objet avec les informations
				leAdherent = new Adherent(idAdherent, nom1, prenom, codePostal, ville, dateNaissance, typeAdhesion, telephone, email, paiement);
				//Ajoute l'objet dans un tableau d'objet ArrayList
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
	
	//retourne les adhérents par leurs noms
	public ArrayList rechercheNomsAdherents(String nom) {
		try {
			//Chargement du driver JDBC pour MySQL
			Class.forName( "com.mysql.jdbc.Driver" );
		
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );	
		}
		
		//variables locales
		Adherent leAdherent = null;	
		ArrayList Adherents = new ArrayList();
		
		try {
			//Création d'un statement
			String sql = "SELECT * FROM adherent WHERE Nom LIKE '%"+nom+"%'";
			//Établissement de la connexion
			state = ConnexionDB.getInstance().createStatement();
			//exécution requête
			result = state.executeQuery( sql );
		
			//parcours Resultset
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
				 
				//crée un objet avec les informations
				leAdherent = new Adherent(idAdherent, nom1, prenom, codePostal, ville, dateNaissance, typeAdhesion, telephone, email, paiement);
				//Ajoute l'objet dans un tableau d'objet ArrayList
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
		try {
			//Chargement du driver JDBC pour MySQL
			Class.forName( "com.mysql.jdbc.Driver" );
		
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );	
		}
		
		//variables locales
		Adherent leAdherent = null;
		ArrayList Adherents = new ArrayList();
		
		try {
			//Création d'un statement
			String sql = "SELECT * FROM adherent WHERE Prenom LIKE '%"+prenomRecherche+"%'";
			//Établissement de la connexion
			state = ConnexionDB.getInstance().createStatement();
			//exécution requête
			result = state.executeQuery( sql );
		
			//parcours Resultset
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
				   
				//crée un objet avec les informations
				leAdherent = new Adherent(idAdherent, nom, prenom, codePostal, ville, dateNaissance, typeAdhesion, telephone, email, paiement);
				//Ajoute l'objet dans un tableau d'objet ArrayList
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
	
	//Enregistre un adhérent qui n'existe pas encore et en fonction du -1 en id
	public boolean saveAdherent(Adherent adherent) {
		//variables locales
		boolean existResult = false;
		
		try {	
			//Chargement du driver JDBC pour MySQL
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}

		try {
			String sql;
		
			if(adherent.getId() == -1) {
				boolean exist = verifExistAdherent(adherent);
				if(!exist) {
					//Création d'un statement
					sql = "INSERT INTO adherent VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					//Établissement de la connexion
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
					//Création d'un statement
					sql = "INSERT INTO adherent VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					//Établissement de la connexion
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
	
	//Vérifie si l'adhérent existe ou non	Return true ou false
	public boolean verifExistAdherent(Adherent adherent)  {
		//variables locales
		boolean resultat = false;
		
		try {	
			//Chargement du driver JDBC pour MySQL
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
		
		try {
			//Création d'un statement
			String sql = "SELECT COUNT(*) AS adherentExist FROM adherent WHERE Nom = '"+adherent.getNom()+"' AND Prenom = '"+adherent.getPrenom()+"'";
			//Établissement de la connexion
			state = ConnexionDB.getInstance().createStatement();
			//exécution requête
			result = state.executeQuery( sql );
			
			//parcours Resultset
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
	
	
	//Retourne tous les adhérents
	public ArrayList selectAdherents()  {
		try {
			//Chargement du driver JDBC pour MySQL
			Class.forName( "com.mysql.jdbc.Driver" );
		
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );	
		}
		
		//variables locales
		Adherent leAdherent = null;
		ArrayList Adherents = new ArrayList();
		
		try {
			//Création d'un statement
			String sql = "SELECT * FROM adherent";
			//Établissement de la connexion
			state = ConnexionDB.getInstance().createStatement();
			//exécution requête
			result = state.executeQuery( sql );
			
			//parcours Resultset
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
				   
				//crée un objet avec les informations
				leAdherent = new Adherent(idAdherent, nom, prenom, codePostal, ville, dateNaissance, typeAdhesion, telephone, email, paiement);
				//Ajoute l'objet dans un tableau d'objet ArrayList
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

	
	//récupère les informations d'un adhérent particulié
	public Adherent getAdherent(int idAdherent) {
		//Chargement du driver JDBC pour MySQL
		try {
			//Chargement du driver JDBC pour MySQL
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
		
		//variables locales
		Adherent leAdherent = null;
		
		try {
			//Création d'un statement
			String sql = "SELECT * FROM adherent WHERE idAdherent = '"+idAdherent+"'";
			//Établissement de la connexion
			state = ConnexionDB.getInstance().createStatement();
			//exécution requête
			result = state.executeQuery( sql );
			
			//parcours Resultset
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

	//Récupèrer les informations d'un adhérent à partir de son nom et prénom
	public Adherent getAdherent(String nom, String Prenom)  {
		try {	
			//Chargement du driver JDBC pour MySQL
			Class.forName( "com.mysql.jdbc.Driver" );
			
		} catch ( ClassNotFoundException e ) {
			System.out.println( "Erreur chargement du driver: " +e.getMessage() );
		}
		
		//variables locales
		Adherent leAdherent = null;
		
		try {
			//Création d'un statement
			String sql = "SELECT * FROM adherent WHERE Nom = '"+nom+"' AND Prenom = '"+Prenom+"'";
			//Établissement de la connexion
			state = ConnexionDB.getInstance().createStatement();
			//exécution requête
			result = state.executeQuery( sql );
			
			//parcours Resultset
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
	
	//Met à jours les informations d'un adhérent et retourne true si OK
	public boolean updateAdherent(Adherent adherent)  {
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
			String sql = "UPDATE adherent SET idAdherent = ?, Nom = ?, Prenom = ?, CodePostal = ?, Ville = ?"
					+ ", DateNaissance = ?, TypeAdhesion = ?, Telephone = ?, Email = ?, Paiement = ? WHERE idAdherent = ?";
			//Établissement de la connexion
			st = ConnexionDB.getInstance().prepareStatement( sql );
			
			//Mise à jours des informations...
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
			
			//Exécuter la mise à jours
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

	//Supprime un adhérent et retourne true si OK
	public boolean supprimerAdherent(Adherent adherent)  {
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
			String sql = "DELETE FROM adherent WHERE idAdherent = ?";
			//Établissement de la connexion
			st = ConnexionDB.getInstance().prepareStatement( sql );
			
			//Modification des informations
			st.setInt(1, adherent.getId());
			
			//Exécution du changement
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
