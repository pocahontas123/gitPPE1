package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import model.classe.TypeAdhesion;
import model.dataaccesslayer.TypeAdhesionDB;

//hérite de JPanel
public class AjouterCategorie extends JPanel {
	//Mes variables membres
	private JPanel contentPan = new JPanel();
	private JPanel boutonPan;
	private Font police, police1;

	private JButton raz = new JButton ("RAZ");
	private JButton b = new JButton ("Envoyer");
	
	private Dimension dim = new Dimension(80, 50);
	private Dimension dim2 = new Dimension(500, 200);
	
	private JTextField libelleT;
	private JTextField prixT;
	
	private JPanel AdherentPan ;  
	private JLabel libelle, prix;
	
	//Constructeur par défaut
	public AjouterCategorie() {
		this.setSize(500, 400);
		this.setVisible(true);
    	contentPan.setPreferredSize(dim2);
		this.setBackground(Color.white);

		//Initialisation des composants à l'appel du constructeur par défaut
	    this.initComposant();
	}
	
	
	//Création et initialise les composants de la page
	private void initComposant() {
		//Création de la police
	    police = new Font("Arial", Font.BOLD, 20);
	    police1 = new Font("Arial", Font.BOLD, 18);		

	    //Création et initialisation du JPanel NORTH
	    AdherentPan = new JPanel();
	    AdherentPan.setPreferredSize(new Dimension(165, 200));
	    TitledBorder titleBorder = BorderFactory.createTitledBorder("Ajout catégorie");
	    AdherentPan.setBorder(titleBorder);
	    titleBorder.setTitleFont(police);
	    
	    //Création des JLabel de gauche et des JTextField de droite
	    libelle = new JLabel("Libelle : ");
	    libelle.setFont(police1);
	    libelle.setHorizontalAlignment(JLabel.RIGHT);
	    libelle.setPreferredSize(new Dimension(220, 20));
	    
	    libelleT = new JTextField();
	    
	    prix = new JLabel("Prix : ");
	    prix.setFont(police1);
	    prix.setHorizontalAlignment(JLabel.RIGHT);
	    prix.setPreferredSize(new Dimension(220, 20));
	    
	    //Masque pour le prix
	    try{ 
	    	MaskFormatter telMask = new MaskFormatter("### €");
	    	prixT = new JFormattedTextField(telMask);
	    	
	    	}catch(ParseException e){e.printStackTrace();
	    }
	    
	    //Correction "bug"
	    ((JFormattedTextField) prixT).setFocusLostBehavior( JFormattedTextField.PERSIST ); 
	    
	    //la taille des deux JTextField
	    libelleT.setPreferredSize(new Dimension(200, 30));
	    prixT.setPreferredSize(new Dimension(200, 30));
	    
	    //Ajout des JLabel et JTextField au JPanel du Centre
	    AdherentPan.add(libelle);
	    AdherentPan.add(libelleT);
	    AdherentPan.add(prix);
	    AdherentPan.add(prixT);
	    
	    //Taille pour les deux boutons
	    b.setPreferredSize(dim);
	    raz.setPreferredSize(dim);
	    
	    //Listener pour les deux boutons (b = envoyer ; raz = RAZ)
	    b.addActionListener(new BoutonListener());
	    raz.addActionListener(new BoutonListenerRaz());
	
	    boutonPan = new JPanel();
	    boutonPan.add(b);
	    boutonPan.add(raz);
	
	    //Création d'un BorderLayout
	    contentPan.setLayout(new BorderLayout());
	
	    //Les JPanel dans le contentPan
	    contentPan.add(AdherentPan, BorderLayout.CENTER);
	    contentPan.add(boutonPan, BorderLayout.SOUTH);
	    
	    //Couleur de fond
	    contentPan.setBackground(Color.WHITE);
	    AdherentPan.setBackground(Color.WHITE);
	    boutonPan.setBackground(Color.WHITE);

	    this.add(contentPan);
	    
	}
	
	//Listener Bouton RAZ
	class BoutonListenerRaz implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//Vide les JTextField de Libelle et prix
			libelleT.setText("");
			prixT.setText("");
	    }
	}
	
	//Listener sur le bouton "b"
	class BoutonListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	System.out.println("Début BoutonListener");
	    	
	    	//Si les différents texts ne sont pas vide
	    	if(!libelleT.getText().isEmpty() && !prixT.getText().isEmpty()) {	
	    		System.out.println("Si zones de text non vides: ");
	    		boolean resutlVerif;

	    		//Récupère le tarif sans le sigle Euro
	    		String[] splitArray = null;
	    		String nomPrenom= prixT.getText();
	    		splitArray = nomPrenom.split(" ");
				int tarif = Integer.parseInt(splitArray[0]); 
				
				//Crée un objet à partir des élements précédent (JTexField et du split)
		    	TypeAdhesion type = new TypeAdhesion(libelleT.getText(),tarif);
		    	TypeAdhesionDB typeDB = new TypeAdhesionDB();
		    	//On sauvegarde le nouveau type et on récupère la situation dans un boolean
		    	resutlVerif = typeDB.saveTypeAdhesion(type);
		    	
		    	//Boolean pas ok
		    	if (resutlVerif) {
		    		//Catégorie existe déjà
		    		JOptionPane jop = new JOptionPane();

		    		jop.showMessageDialog(null, "Categorie existe déjà ", "Erreur", JOptionPane.ERROR_MESSAGE);
		    	}else {
		    		//Si catégorie n'existe pas encore c'est ok
		    		JOptionPane jop1 = new JOptionPane();

		    		jop1.showMessageDialog(null, "La catégorie "+ type.getLibelle()+" est enregistrée avec succès ", "Information", JOptionPane.INFORMATION_MESSAGE);
		    		
		    		//On supprime tout
		    		contentPan.removeAll();
		    		contentPan.revalidate();
		    		contentPan.repaint();
		    		   
		    		//On récupère mes deux images
	    			URL imageM2l = PagePrincipale.class.getResource("/MDL.jpg");
	    			URL  imageTir = PagePrincipale.class.getResource("/s-l225.png");
	    			ImageIcon  iconM2l = new ImageIcon(imageM2l);
	    			ImageIcon iconTir = new ImageIcon(imageTir);
	    			JPanel image = new JPanel();
	    			
	    			//On rajoute mes deux images à mon JPanel
	    		    image.add(new JLabel(iconM2l));
	    		    image.add(new JLabel(iconTir));
	    		    //Couleur de fond
		    		image.setBackground(Color.WHITE);
		    		//image visible
		    		image.setVisible(true);
		    		
		    		//Ajoute mes images JPanel image dans contentPan
		    		contentPan.add(image, BorderLayout.CENTER);		
		    	}
	    	}else {
	    		//Champ(s) vide(s)
	    		JOptionPane jop3 = new JOptionPane();

	    		jop3.showMessageDialog(null, "Champ(s) Vide(s) ", "Erreur", JOptionPane.ERROR_MESSAGE);	
	    	}
	    }
	}	

	    

}
