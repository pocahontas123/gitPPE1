package vue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

import model.dataaccesslayer.AdherentDB;
import model.dataaccesslayer.ConnexionDB;



public class PagePrincipale extends JFrame {

	private URL imageM2l;
	private URL imageTir;
	private ImageIcon iconM2l;
	private ImageIcon iconTir;
	private JPanel image= new JPanel();

	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu gestion = new JMenu("Gestion");
	private JMenu gestionAdherent = new JMenu("Gestion adhérent");
	private JMenu gestionCategorie = new JMenu("Gestion catégorie");
	private JMenu rechercheAdherent = new JMenu("Recherche adhérent");
	private JMenu rapportRecapitulatif = new JMenu("Rapport récapitulatif");
	
	private JMenuItem item1 = new JMenuItem("Quitter");
	
	private JRadioButtonMenuItem radioBoutonAfficher1 = new JRadioButtonMenuItem("Afficher");
	private JRadioButtonMenuItem radioBoutonAjouter2 = new JRadioButtonMenuItem("Ajouter");
	private JRadioButtonMenuItem radioBoutonModifier3 = new JRadioButtonMenuItem("Modifier");
	private JRadioButtonMenuItem radioBoutonSupprimer4 = new JRadioButtonMenuItem("Supprimer");
	
	private JRadioButtonMenuItem radioBoutonAfficher5 = new JRadioButtonMenuItem("Afficher");
	private JRadioButtonMenuItem radioBoutonAjouter6 = new JRadioButtonMenuItem("Ajouter");
	private JRadioButtonMenuItem radioBoutonModifier7 = new JRadioButtonMenuItem("Modifier");
	private JRadioButtonMenuItem radioBoutonSupprimer8 = new JRadioButtonMenuItem("Supprimer");
	
	private JRadioButtonMenuItem radioBoutonRechercheNom9 = new JRadioButtonMenuItem("Recherche par nom");
	private JRadioButtonMenuItem radioBoutonRecherchePrenom10 = new JRadioButtonMenuItem("Recherche par prénom");
	
	private JRadioButtonMenuItem radioBoutonRapportRecap11 = new JRadioButtonMenuItem("Rapport somme par catégorie");
	private JRadioButtonMenuItem radioBoutonRapportAdherentPasPayer12 = new JRadioButtonMenuItem("Rapport adhérents pas payés");
	
	private JPanel contentPane = new JPanel();


	
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public PagePrincipale() {
		//titre de la Frame
		this.setTitle("Application Gestion Adherents");
		//taille de la frame
		this.setSize(600, 600);
		
		//Centre la frame
		this.setLocationRelativeTo(null);
		//Empêche le changement de taille de la fenêtre
		this.setResizable(false);
		//la fenêtre reste par dessus tout
		//this.setAlwaysOnTop(true);

		this.contentPane.setLayout(new BorderLayout());
		      
		imageM2l = PagePrincipale.class.getResource("/MDL.jpg");
		imageTir = PagePrincipale.class.getResource("/s-l225.png");
		iconM2l = new ImageIcon(imageM2l);
		iconTir = new ImageIcon(imageTir);
		image.add(new JLabel(iconM2l));
		image.add(new JLabel(iconTir));
		image.setVisible(true);
		contentPane.add(image, BorderLayout.CENTER);

	    this.setContentPane(contentPane);
	    this.initComposant();
	    this.setVisible(true);
	}
	
	
	
	private void initComposant(){
		//Crée mes boutons
		ButtonGroup bg = new ButtonGroup();
		bg.add(radioBoutonAfficher1);
		bg.add(radioBoutonAjouter2);
		bg.add(radioBoutonModifier3);
		bg.add(radioBoutonSupprimer4);
		
		bg.add(radioBoutonAfficher5);
		bg.add(radioBoutonAjouter6);
		bg.add(radioBoutonModifier7);
		bg.add(radioBoutonSupprimer8);
		
		bg.add(radioBoutonRechercheNom9);
		bg.add(radioBoutonRecherchePrenom10);
		
		bg.add(radioBoutonRapportRecap11);
		bg.add(radioBoutonRapportAdherentPasPayer12);
		//Rajoute mes boutons à mon sous-menu 1

		this.gestionAdherent.add(radioBoutonAfficher1);
		this.gestionAdherent.add(radioBoutonAjouter2);
		this.gestionAdherent.add(radioBoutonModifier3);
		this.gestionAdherent.add(radioBoutonSupprimer4);
		
		
		//Rajoute mes boutons à mon sous-menu 2
		this.gestionCategorie.add(radioBoutonAfficher5);
		this.gestionCategorie.add(radioBoutonAjouter6);
		this.gestionCategorie.add(radioBoutonModifier7);
		this.gestionCategorie.add(radioBoutonSupprimer8);
		
		this.rechercheAdherent.add(radioBoutonRechercheNom9);
		this.rechercheAdherent.add(radioBoutonRecherchePrenom10);
		
		this.rapportRecapitulatif.add(radioBoutonRapportRecap11);
		this.rapportRecapitulatif.add(radioBoutonRapportAdherentPasPayer12);
	
		
		//Rajoute à mon menu mes deux sous-menus
		this.gestion.add(this.gestionAdherent);
		this.gestion.add(this.gestionCategorie);
		this.gestion.addSeparator();

		//Rajoute à mon menu "Quitter"
		this.gestion.add(item1);
		
		this.menuBar.add(gestion);
		this.menuBar.add(rechercheAdherent);
		this.menuBar.add(rapportRecapitulatif);
		this.setJMenuBar(menuBar);
		
		radioBoutonAfficher1.addActionListener(new jrmListener());
		radioBoutonAjouter2.addActionListener(new jrmListener());
		radioBoutonModifier3.addActionListener(new jrmListener());
		radioBoutonSupprimer4.addActionListener(new jrmListener());
			
		radioBoutonAfficher5.addActionListener(new jrmListener());
		radioBoutonAjouter6.addActionListener(new jrmListener());
		radioBoutonModifier7.addActionListener(new jrmListener());
		radioBoutonSupprimer8.addActionListener(new jrmListener());
		
		radioBoutonRechercheNom9.addActionListener(new jrmListener());
		radioBoutonRecherchePrenom10.addActionListener(new jrmListener());
		
		radioBoutonRapportRecap11.addActionListener(new jrmListener());
		radioBoutonRapportAdherentPasPayer12.addActionListener(new jrmListener());
		
		item1.addActionListener(new itemListener());
	  }

	class itemListener implements ActionListener {
	
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == item1) {
				 int reponse = JOptionPane.showConfirmDialog(null,
                         "Voulez-vous quitter l'application",
                         "Confirmation",
                         JOptionPane.YES_NO_OPTION,
                         JOptionPane.QUESTION_MESSAGE);
    if (reponse==JOptionPane.YES_OPTION){
    	System.out.println("Choix 3 : Quitter l'application");
    	ConnexionDB.closeConnexion();
		System.exit(0);
    }
}
				
			}
		}
	
	
	class jrmListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {		

			if(e.getSource() == radioBoutonAfficher1) {
				System.out.println("Choix 1 : Afficher");
				
				AfficherAdherent afficherAdherent = new AfficherAdherent();
		    	contentPane.removeAll();
		    	contentPane.add(afficherAdherent, BorderLayout.CENTER);

		    	contentPane.revalidate();
	    		contentPane.repaint();
				
			}else if (e.getSource() == radioBoutonAjouter2) {
				System.out.println("Choix 1 : Ajouter");
				AjoutAdherent ajouAd = new AjoutAdherent();
				contentPane.removeAll();
				contentPane.add(ajouAd, BorderLayout.CENTER);

				contentPane.revalidate();
				contentPane.repaint();
					
			}else if (e.getSource() == radioBoutonModifier3) {	
				System.out.println("Choix 1 : Modifier");
				
				ModifierAdherent ajouAd = new ModifierAdherent();
				contentPane.removeAll();
				contentPane.add(ajouAd, BorderLayout.CENTER);

				contentPane.revalidate();
				contentPane.repaint();
				
				
			}else if (e.getSource() == radioBoutonSupprimer4) {	
				System.out.println("Choix 1 : Supprimer");
				SuppressionAdherent suppressionAdherent = new SuppressionAdherent();
				contentPane.removeAll();
				contentPane.add(suppressionAdherent, BorderLayout.CENTER);

				contentPane.revalidate();
				contentPane.repaint();
				
				
			}else if (e.getSource() == radioBoutonAfficher5) {	
				System.out.println("Choix 2 : Afficher");
				
				AfficherCategorie afficherCategorie = new AfficherCategorie();
		    	contentPane.removeAll();
		    	contentPane.add(afficherCategorie, BorderLayout.CENTER);

		    	contentPane.revalidate();
	    		contentPane.repaint();
	    		
	    		
			}else if (e.getSource() == radioBoutonAjouter6) {	
				System.out.println("Choix 2 : Ajouter");
				
				AjouterCategorie ajouterCategorie = new AjouterCategorie();
		    	contentPane.removeAll();
		    	contentPane.add(ajouterCategorie, BorderLayout.CENTER);

		    	contentPane.revalidate();
	    		contentPane.repaint();	
				
			
			}else if (e.getSource() == radioBoutonModifier7) {	
				System.out.println("Choix 2 : Modifier");
				
				ModifierCategorie modifierCategorie = new ModifierCategorie();
		    	contentPane.removeAll();
		    	contentPane.add(modifierCategorie, BorderLayout.CENTER);

		    	contentPane.revalidate();
	    		contentPane.repaint();	
	    		
	    		
			}else if (e.getSource() == radioBoutonSupprimer8) {	
				System.out.println("Choix 2 : Supprimer");	
				
				SupprimerCategorie supprimerCategorie = new SupprimerCategorie();
		    	contentPane.removeAll();
		    	contentPane.add(supprimerCategorie, BorderLayout.CENTER);

		    	contentPane.revalidate();
	    		contentPane.repaint();	
			
			}else if (e.getSource() == radioBoutonRechercheNom9) {
				System.out.println("Recherche nom");
				
				RechercheNom rechercheNom = new RechercheNom();
		    	contentPane.removeAll();
		    	contentPane.add(rechercheNom, BorderLayout.CENTER);

		    	contentPane.revalidate();
	    		contentPane.repaint();			
				
			}else if (e.getSource() == radioBoutonRecherchePrenom10) {
				System.out.println("Recherche prénom");
				
				RecherchePrenom recherchePrenom = new RecherchePrenom();
		    	contentPane.removeAll();
		    	contentPane.add(recherchePrenom, BorderLayout.CENTER);

		    	contentPane.revalidate();
	    		contentPane.repaint();	
	    		
			}else if (e.getSource() == radioBoutonRapportRecap11) {
				System.out.println("Rapport récapitulatif");
				
				RapportRecapitulatif rapportRecapitulatif = new RapportRecapitulatif();
		    	contentPane.removeAll();
		    	contentPane.add(rapportRecapitulatif, BorderLayout.CENTER);

		    	contentPane.revalidate();
	    		contentPane.repaint();	
	    		
			}else if (e.getSource() == radioBoutonRapportAdherentPasPayer12) {
				System.out.println("Rapport adhérents pas payés");
				
				
				
				
				RapportAdherentPasPayes rapportAdherentPasPayes = new RapportAdherentPasPayes();
		    	contentPane.removeAll();
		    	contentPane.add(rapportAdherentPasPayes, BorderLayout.CENTER);

		    	contentPane.revalidate();
	    		contentPane.repaint();	
				
			}
		}
	}	
}
