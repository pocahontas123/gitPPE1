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
	public static PagePrincipale frame=null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				
					AdherentDB adherentDB = new AdherentDB();
					
					AdminDB adminDB = new AdminDB();
					
					String format = "yyyy";
					java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format ); 
					java.util.Date date = new java.util.Date();
					
					int annee = Integer.parseInt(formater.format( date ));
					if(annee-1 == Integer.parseInt(formater.format( adminDB.getDateDerVisite())))
					{
					
					
					adherentDB.updatePaiementsAdherents();
					
					
					}
					adminDB.updateDateDerVisite(date);
					frame = new PagePrincipale();
					frame.setSize(600, 300);
					//Centre la frame
					frame.setLocationRelativeTo(null);
					//Empêche le changement de taille de la fenêtre
					frame.setResizable(false);
					//fermeture de l'application au clic sur le bouton-croix rouge
					frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					frame.addWindowListener(new WindowAdapter(){
			             public void windowClosing(WindowEvent e){
			                   int reponse = JOptionPane.showConfirmDialog(frame,
			                                        "Voulez-vous quitter l'application",
			                                        "Confirmation",
			                                        JOptionPane.YES_NO_OPTION,
			                                        JOptionPane.QUESTION_MESSAGE);
			                   if (reponse==JOptionPane.YES_OPTION){
			                   	ConnexionDB.closeConnexion();

			               		System.exit(0);
			                   }
			             }
			    });
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
