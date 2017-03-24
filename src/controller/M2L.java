package controller;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import vue.AjoutAdherent;
import vue.Login;
import vue.PagePrincipale;
import model.classe.Adherent;
import model.classe.TypeAdhesion;
import model.dataaccesslayer.AdherentDB;
import model.dataaccesslayer.AdminDB;
import model.dataaccesslayer.ConnexionDB;
import model.dataaccesslayer.TypeAdhesionDB;

public class M2L  {
	public static PagePrincipale frame = null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdherentDB adherentDB = new AdherentDB();
					AdminDB adminDB = new AdminDB();
					
					//formatage date
					String format = "yyyy";
					java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format ); 
					java.util.Date date = new java.util.Date();
					
					int annee = Integer.parseInt(formater.format( date ));
					
					//Met à jours les paiements des adhérents
					if(annee-1 == Integer.parseInt(formater.format( adminDB.getDateDerVisite() ))) {
						adherentDB.updatePaiementsAdherents();
					}
					//Met à jours la date de la dernière visite de l'appl.
					adminDB.updateDateDerVisite(date);
					
					//Création de la frame de la page principale
					frame = new PagePrincipale();
					//la taille de la frame
					frame.setSize(600, 300);
					//Centre la frame
					frame.setLocationRelativeTo(null);
					//Empêche le changement de taille de la fenêtre
					frame.setResizable(false);
					
					//fermeture de l'application au clic sur le bouton-croix rouge
					frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					//Listener sur la frame au niveau du bouton-crois rouge
					frame.addWindowListener(new WindowAdapter(){
			             public void windowClosing(WindowEvent e){
			            	 int reponse = JOptionPane.showConfirmDialog(frame,
			                                        "Voulez-vous quitter l'application",
			                                        "Confirmation",
			                                        JOptionPane.YES_NO_OPTION,
			                                        JOptionPane.QUESTION_MESSAGE);
			                 if(reponse == JOptionPane.YES_OPTION) {
			                	 //Ferme la connexion à la base de données
			                	 ConnexionDB.closeConnexion();
			                	 //Ferme l'application
			               		 System.exit(0);
			                 }
			             }
			    });
					//rend la frame visible
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
