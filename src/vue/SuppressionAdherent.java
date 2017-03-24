package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import vue.AfficherAdherent.BoutonListenerCombo;
import model.classe.Adherent;
import model.classe.TypeAdhesion;
import model.dataaccesslayer.AdherentDB;
import model.dataaccesslayer.TypeAdhesionDB;

public class SuppressionAdherent extends JPanel{
	private JPanel contentPan = new JPanel();
	private JPanel ListeAdherents;
	private JPanel ficheAdherentPan ; 
	private JPanel boutonPan;
	private Font police, police1;
	private JComboBox combo;
	private JLabel Nom, Prenom, CodePostal, Ville,  DateNaissance, TypeAdhesion, Telephone, Email, Paiement, listeAd;
	private JLabel NomT, PrenomT, CodePostalT, VilleT,  DateNaissanceT, TypeAdhesionT, TelephoneT, EmailT, PaiementT;
	
	private JButton b = new JButton ("Supprimer");
	private Dimension dim = new Dimension(100, 50);
	private Dimension dim2 = new Dimension(500, 430);
	
	private TitledBorder titleBorder1 ;
	
	
	public SuppressionAdherent() {
		this.setSize(500, 400);
		this.setVisible(true);
    	contentPan.setPreferredSize(dim2);
		this.setBackground(Color.white);

	    this.initComposant();
	}

	private void initComposant(){
		police = new Font("Arial", Font.BOLD, 20);
	    police1 = new Font("Arial", Font.BOLD, 18);		

	    ListeAdherents = new JPanel();
	    ListeAdherents.setPreferredSize(new Dimension(80, 75));
	    TitledBorder titleBorder = BorderFactory.createTitledBorder("Liste Adherents");
	    ListeAdherents.setBorder(titleBorder);
	    titleBorder.setTitleFont(police);
	    
	    ficheAdherentPan = new JPanel();
	    ficheAdherentPan.setPreferredSize(dim2);
	    titleBorder1 = BorderFactory.createTitledBorder("Fichie Adhérent");
	    ficheAdherentPan.setBorder(titleBorder);
	    titleBorder1.setTitleFont(police);
	   
	    b.setPreferredSize(dim);
	    b.addActionListener(new BoutonListenerSupprimer());
	    boutonPan = new JPanel();
	    boutonPan.add(b);
	    
	    

	    listeAd = new JLabel("Liste des Adhérents : ");
	    listeAd.setFont(police1);
	    listeAd.setHorizontalAlignment(JLabel.RIGHT);
	    listeAd.setPreferredSize(new Dimension(220, 20));
	    
	
	    

	   
	    Nom = new JLabel("Nom : ");
	    Nom.setFont(police1);
	    Nom.setHorizontalAlignment(JLabel.RIGHT);
	    Nom.setPreferredSize(new Dimension(220, 20));
	    
	    NomT = new JLabel("");
	    NomT.setFont(police1);
	    NomT.setHorizontalAlignment(JLabel.LEFT);
	    NomT.setPreferredSize(new Dimension(220, 20));
	    
	    Prenom = new JLabel("Prenom : ");
	    Prenom.setFont(police1);
	    Prenom.setHorizontalAlignment(JLabel.RIGHT);
	    Prenom.setPreferredSize(new Dimension(220, 20));
	    
	    PrenomT = new JLabel("");
	    PrenomT.setFont(police1);
	    PrenomT.setHorizontalAlignment(JLabel.LEFT);
	    PrenomT.setPreferredSize(new Dimension(220, 20));
	    
	    CodePostal = new JLabel("Code Postal : ");
	    CodePostal.setFont(police1);
	    CodePostal.setHorizontalAlignment(JLabel.RIGHT);
	    CodePostal.setPreferredSize(new Dimension(220, 20));
	    
	    CodePostalT = new JLabel("");
	    CodePostalT.setFont(police1);
	    CodePostalT.setHorizontalAlignment(JLabel.LEFT);
	    CodePostalT.setPreferredSize(new Dimension(220, 20));

	    Ville = new JLabel("Ville : ");
	    Ville.setFont(police1);
	    Ville.setHorizontalAlignment(JLabel.RIGHT);
	    Ville.setPreferredSize(new Dimension(220, 20));
	    

	    VilleT = new JLabel("");
	    VilleT.setFont(police1);
	    VilleT.setHorizontalAlignment(JLabel.LEFT);
	    VilleT.setPreferredSize(new Dimension(220, 20));
	    
	     
	    DateNaissance = new JLabel("Date de Naissance : ");
	    DateNaissance.setFont(police1);
	    DateNaissance.setHorizontalAlignment(JLabel.RIGHT);
	    DateNaissance.setPreferredSize(new Dimension(220, 20));
	    
	    DateNaissanceT = new JLabel("");
	    DateNaissanceT.setFont(police1);
	    DateNaissanceT.setHorizontalAlignment(JLabel.LEFT);
	    DateNaissanceT.setPreferredSize(new Dimension(220, 20));

	    
	    TypeAdhesion = new JLabel("Type Adhesion : ");
	    TypeAdhesion.setFont(police1);
	    TypeAdhesion.setHorizontalAlignment(JLabel.RIGHT);
	    TypeAdhesion.setPreferredSize(new Dimension(220, 20));
	    
	    
	    TypeAdhesionT = new JLabel("");
	    TypeAdhesionT.setFont(police1);
	    TypeAdhesionT.setHorizontalAlignment(JLabel.LEFT);
	    TypeAdhesionT.setPreferredSize(new Dimension(220, 20));
	    
	    
	    Telephone = new JLabel("Telephone : ");
	    Telephone.setFont(police1);
	    Telephone.setHorizontalAlignment(JLabel.RIGHT);
	    Telephone.setPreferredSize(new Dimension(220, 20));
	    

	    TelephoneT = new JLabel("");
	    TelephoneT.setFont(police1);
	    TelephoneT.setHorizontalAlignment(JLabel.LEFT);
	    TelephoneT.setPreferredSize(new Dimension(220, 20));
	    
	    
	    Email = new JLabel("Email : ");
	    Email.setFont(police1);
	    Email.setHorizontalAlignment(JLabel.RIGHT);
	    Email.setPreferredSize(new Dimension(220, 20));
	    

	    EmailT = new JLabel("");
	    EmailT.setFont(police1);
	    EmailT.setHorizontalAlignment(JLabel.LEFT);
	    EmailT.setPreferredSize(new Dimension(220, 20));
	    
	    
	    Paiement = new JLabel("Paiement : ");
	    Paiement.setFont(police1);
	    Paiement.setHorizontalAlignment(JLabel.RIGHT);
	    Paiement.setPreferredSize(new Dimension(220, 20));
	    
	    
	    PaiementT = new JLabel("");
	    PaiementT.setFont(police1);
	    PaiementT.setHorizontalAlignment(JLabel.LEFT);
	    PaiementT.setPreferredSize(new Dimension(220, 20));	    
	    
	    ficheAdherentPan.add(Nom);
	    ficheAdherentPan.add(NomT);
	    ficheAdherentPan.add(Prenom);
	    ficheAdherentPan.add(PrenomT);
	    ficheAdherentPan.add(CodePostal);
	    ficheAdherentPan.add(CodePostalT);
	
	    ficheAdherentPan.add(Ville);
	    ficheAdherentPan.add(VilleT);
	    ficheAdherentPan.add(DateNaissance);
	    ficheAdherentPan.add(DateNaissanceT);
	    ficheAdherentPan.add(TypeAdhesion);
	    ficheAdherentPan.add(TypeAdhesionT);
	    
	    ficheAdherentPan.add(Telephone);
	    ficheAdherentPan.add(TelephoneT);
	    ficheAdherentPan.add(Email);
	    ficheAdherentPan.add(EmailT);
	    ficheAdherentPan.add(Paiement);
	    ficheAdherentPan.add(PaiementT);
	    
	    combo = new JComboBox();
	    combo.setPreferredSize(new Dimension(100, 20));
	    AdherentDB adherentDB = new AdherentDB();
		ArrayList adherent = new ArrayList();
		
		adherent = adherentDB.selectAdherents();
		
		combo.addItem("Sélectionner...");
	    for(int i = 0; i < adherent.size(); i++) {
	    	combo.addItem(((Adherent) adherent.get(i)).getNom()+" "+((Adherent) adherent.get(i)).getPrenom());
	    }
	    
	    combo.setPreferredSize(new Dimension(200, 30));
	    combo.addActionListener(new BoutonListenerCombo());

	    ListeAdherents.setVisible(true);
	    ficheAdherentPan.setVisible(false);
	    boutonPan.setVisible(false);
	    ListeAdherents.add(listeAd);

	    ListeAdherents.add(combo);

	    contentPan.setLayout(new BorderLayout());
	    contentPan.add(ListeAdherents, BorderLayout.NORTH);
	    contentPan.add(ficheAdherentPan, BorderLayout.CENTER);
	    contentPan.add(boutonPan, BorderLayout.SOUTH);
	    contentPan.setBackground(Color.WHITE);
	    ListeAdherents.setBackground(Color.WHITE);
	    ficheAdherentPan.setBackground(Color.WHITE);
	    boutonPan.setBackground(Color.WHITE);

	    this.add(contentPan);
	}

	class BoutonListenerCombo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if ((String)combo.getSelectedItem() != "Sélectionner...") {
			
				ficheAdherentPan.setVisible(true);
				boutonPan.setVisible(true);
			
				AdherentDB adDb = new AdherentDB();
			   
			    Adherent adherent;
			    String[] splitArray = null;
				String nomPrenom = (String)combo.getSelectedItem();
			    splitArray = nomPrenom.split(" ");
			    adherent = adDb.getAdherent(splitArray[0],splitArray[1]);
			
				NomT.setText(adherent.getNom());
				PrenomT.setText(adherent.getPrenom());
				CodePostalT.setText(adherent.getCodePostal());
				VilleT.setText(adherent.getVille());
				DateNaissanceT.setText(adherent.getAnneeNaissance().toString());
				TypeAdhesionT.setText(adherent.getTypeAdhesion().getLibelle());
				TelephoneT.setText(adherent.getTelephone());
				EmailT.setText(adherent.getEmail());
				
				if(String.valueOf(adherent.getPaiement()) == String.valueOf(false)) {
					PaiementT.setText("non");
				}else {
					PaiementT.setText("oui");
				}
			    
				titleBorder1 = BorderFactory.createTitledBorder("Fiche Adherent "+ NomT.getText() +" "+ PrenomT.getText());
				ficheAdherentPan.setBorder(titleBorder1);
				titleBorder1.setTitleFont(police);
			}
			 //Cause une erreur. L'onglet correspondant à SuppressionAdherent ne charge plus
			else {

				ficheAdherentPan.setVisible(false);
				boutonPan.setVisible(false);
			    
	    		JOptionPane jop2 = new JOptionPane();
		    	jop2.showMessageDialog(null, "Veuillez sélectionner un Adhérent ", "Information", JOptionPane.INFORMATION_MESSAGE);	
			
			}
		}
	}
	
	class BoutonListenerSupprimer implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if ((String)combo.getSelectedItem() != "Sélectionner...") {
				System.out.println("Bouton listener bouton supprimer");	
			
				
			
				boolean resutlVerif;
				AdherentDB adDb = new AdherentDB();
			   
			    Adherent adherent;
			    String[] splitArray = null;
				String nomPrenom = (String)combo.getSelectedItem();
			    splitArray = nomPrenom.split(" ");
			    adherent = adDb.getAdherent(splitArray[0],splitArray[1]);
				
			    AdherentDB adherentDB = new AdherentDB();
		    	resutlVerif = adherentDB.supprimerAdherent(adherent);
	    	
		    	if (!resutlVerif) {
		    		JOptionPane jop = new JOptionPane();
		    		jop.showMessageDialog(null, "Adhérent pas supprimé ", "Erreur", JOptionPane.ERROR_MESSAGE);
		    		
		    	}else {
		    		JOptionPane jop2 = new JOptionPane();
		    		jop2.showMessageDialog(null, "L'adhérent "+ adherent.getNom()+ " "+ adherent.getPrenom()+" a été supprimé avec succès ", "Information", JOptionPane.INFORMATION_MESSAGE);
		    		
		    		contentPan.removeAll();
		    		contentPan.revalidate();
		    		contentPan.repaint();
	    		   
					URL imageM2l = PagePrincipale.class.getResource("/MDL.jpg");
					URL  imageTir = PagePrincipale.class.getResource("/s-l225.png");
					ImageIcon  iconM2l = new ImageIcon(imageM2l);
					ImageIcon iconTir = new ImageIcon(imageTir);
					JPanel image = new JPanel();
		
				    image.add(new JLabel(iconM2l));
				    image.add(new JLabel(iconTir));
		    		image.setBackground(Color.WHITE);

		    		image.setVisible(true);
		    		   
		    		contentPan.add(image, BorderLayout.CENTER);		
		    	}	
			}	
		}
	}
}