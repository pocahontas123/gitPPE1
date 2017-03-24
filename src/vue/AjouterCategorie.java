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

//h�rite de JPanel
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
	
	//Constructeur par d�faut
	public AjouterCategorie() {
		this.setSize(500, 400);
		this.setVisible(true);
    	contentPan.setPreferredSize(dim2);
		this.setBackground(Color.white);

		//Initialisation des composants � l'appel du constructeur par d�faut
	    this.initComposant();
	}
	
	
	//Cr�ation et initialise les composants de la page
	private void initComposant() {
		//Cr�ation de la police
	    police = new Font("Arial", Font.BOLD, 20);
	    police1 = new Font("Arial", Font.BOLD, 18);		

	    //Cr�ation et initialisation du JPanel NORTH
	    AdherentPan = new JPanel();
	    AdherentPan.setPreferredSize(new Dimension(165, 200));
	    TitledBorder titleBorder = BorderFactory.createTitledBorder("Ajout cat�gorie");
	    AdherentPan.setBorder(titleBorder);
	    titleBorder.setTitleFont(police);
	    
	    //Cr�ation des JLabel de gauche et des JTextField de droite
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
	    	MaskFormatter telMask = new MaskFormatter("### �");
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
	
	    //Cr�ation d'un BorderLayout
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
	    	System.out.println("D�but BoutonListener");
	    	
	    	//Si les diff�rents texts ne sont pas vide
	    	if(!libelleT.getText().isEmpty() && !prixT.getText().isEmpty()) {	
	    		System.out.println("Si zones de text non vides: ");
	    		boolean resutlVerif;

	    		//R�cup�re le tarif sans le sigle Euro
	    		String[] splitArray = null;
	    		String nomPrenom= prixT.getText();
	    		splitArray = nomPrenom.split(" ");
				int tarif = Integer.parseInt(splitArray[0]); 
				
				//Cr�e un objet � partir des �lements pr�c�dent (JTexField et du split)
		    	TypeAdhesion type = new TypeAdhesion(libelleT.getText(),tarif);
		    	TypeAdhesionDB typeDB = new TypeAdhesionDB();
		    	//On sauvegarde le nouveau type et on r�cup�re la situation dans un boolean
		    	resutlVerif = typeDB.saveTypeAdhesion(type);
		    	
		    	//Boolean pas ok
		    	if (resutlVerif) {
		    		//Cat�gorie existe d�j�
		    		JOptionPane jop = new JOptionPane();

		    		jop.showMessageDialog(null, "Categorie existe d�j� ", "Erreur", JOptionPane.ERROR_MESSAGE);
		    	}else {
		    		//Si cat�gorie n'existe pas encore c'est ok
		    		JOptionPane jop1 = new JOptionPane();

		    		jop1.showMessageDialog(null, "La cat�gorie "+ type.getLibelle()+" est enregistr�e avec succ�s ", "Information", JOptionPane.INFORMATION_MESSAGE);
		    		
		    		//On supprime tout
		    		contentPan.removeAll();
		    		contentPan.revalidate();
		    		contentPan.repaint();
		    		   
		    		//On r�cup�re mes deux images
	    			URL imageM2l = PagePrincipale.class.getResource("/MDL.jpg");
	    			URL  imageTir = PagePrincipale.class.getResource("/s-l225.png");
	    			ImageIcon  iconM2l = new ImageIcon(imageM2l);
	    			ImageIcon iconTir = new ImageIcon(imageTir);
	    			JPanel image = new JPanel();
	    			
	    			//On rajoute mes deux images � mon JPanel
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
