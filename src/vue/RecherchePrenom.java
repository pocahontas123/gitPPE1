package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import model.classe.Adherent;
import model.dataaccesslayer.AdherentDB;
import vue.AfficherAdherent.BoutonListenerCombo;

public class RecherchePrenom extends JPanel{
	private JPanel contentPan = new JPanel();
	private JPanel RecherchePrenomPan, ListeAdherentsPan, FicheAdherentPan;
	private JLabel NomFicheAdherent, PrenomFicheAdherent, AdherentsTrouves, Prenom, CodePostal, Ville,  DateNaissance, TypeAdhesion, Telephone, Email, Paiement;
	private JLabel NomFicheAdherentT, PrenomFicheAdherentT, PrenomT, CodePostalT, VilleT,  DateNaissanceT, TypeAdhesionT, TelephoneT, EmailT, PaiementT;
	private JTextField  PrenomTextField;
	private JButton boutonRecherche = new JButton("Rechercher");
	private JComboBox combo;
	private Font police, police1;
	  private Dimension dim = new Dimension(120, 20);

	private Dimension dim2 = new Dimension(500, 450);
	
	
	public RecherchePrenom() { 
	    this.setSize(500, 500);
	    this.setVisible(true);
	    contentPan.setPreferredSize(dim2);

	    this.initComposant();
	}
	
	private void initComposant() {
		police = new Font("Arial", Font.BOLD, 20);
	     police1 = new Font("Arial", Font.BOLD, 18);		

		//Première partie "Rechercher par Prenom
		RecherchePrenomPan = new JPanel();
		RecherchePrenomPan.setPreferredSize(new Dimension(165, 80));
	    TitledBorder titleBorder = BorderFactory.createTitledBorder("Rechercher par prénom");
	    RecherchePrenomPan.setBorder(titleBorder);
	    titleBorder.setTitleFont(police);
	    
	    //Deuxième partie "Liste Adherents"
	    ListeAdherentsPan = new JPanel();
	    ListeAdherentsPan.setPreferredSize(new Dimension(165, 80));
	    TitledBorder titleBorder2 = BorderFactory.createTitledBorder("Liste Adherents Trouvés");
	    ListeAdherentsPan.setBorder(titleBorder2);
	    titleBorder2.setTitleFont(police);
	    
	    //Troisième partie "Fiche Adhérent"
	    FicheAdherentPan = new JPanel();
	    FicheAdherentPan.setPreferredSize(new Dimension(165, 300));
	    TitledBorder titleBorder3 = BorderFactory.createTitledBorder("Fiche adhérent");
	    FicheAdherentPan.setBorder(titleBorder3);
	    titleBorder3.setTitleFont(police);
	    
	    //Contenu première partie
	    Prenom = new JLabel("Prenom : ");
	    Prenom.setFont(police1);
	    Prenom.setHorizontalAlignment(JLabel.RIGHT);
	    Prenom.setPreferredSize(new Dimension(100, 20));
	    
	    PrenomTextField = new JTextField();
	    
	    PrenomTextField.setPreferredSize(new Dimension(200, 20));
	    
	    RecherchePrenomPan.add(Prenom);
	    RecherchePrenomPan.add(PrenomTextField);
	    RecherchePrenomPan.add(boutonRecherche);
	    
	    boutonRecherche.addActionListener(new BoutonRechercheActionListener());
	    
	    //Contenu deuxième partie
	    AdherentsTrouves = new JLabel("Adhérents trouvés : ");
	    AdherentsTrouves.setFont(police1);
	    AdherentsTrouves.setHorizontalAlignment(JLabel.RIGHT);
	    AdherentsTrouves.setPreferredSize(new Dimension(220, 20));
	    
	    combo = new JComboBox();
	    combo.setPreferredSize(new Dimension(200, 20)); 
	    combo.addActionListener(new BoutonListenerCombo());
	    
	    ListeAdherentsPan.add(AdherentsTrouves);
	    ListeAdherentsPan.add(combo);
	    
	    //Contenu troisième partie
	    NomFicheAdherent = new JLabel("Nom : ");
	    NomFicheAdherent.setFont(police1);
	    NomFicheAdherent.setHorizontalAlignment(JLabel.RIGHT);
	    NomFicheAdherent.setPreferredSize(new Dimension(220, 20));
	    
	    NomFicheAdherentT = new JLabel("");
	    NomFicheAdherentT.setFont(police1);
	    NomFicheAdherentT.setHorizontalAlignment(JLabel.LEFT);
	    NomFicheAdherentT.setPreferredSize(new Dimension(220, 20));
	    
	    PrenomFicheAdherent = new JLabel("Prenom : ");
	    PrenomFicheAdherent.setFont(police1);
	    PrenomFicheAdherent.setHorizontalAlignment(JLabel.RIGHT);
	    PrenomFicheAdherent.setPreferredSize(new Dimension(220, 20));
	    
	    PrenomFicheAdherentT = new JLabel("");
	    PrenomFicheAdherentT.setFont(police1);
	    PrenomFicheAdherentT.setHorizontalAlignment(JLabel.LEFT);
	    PrenomFicheAdherentT.setPreferredSize(new Dimension(220, 20));
	    
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
	    
	    
	    FicheAdherentPan.add(NomFicheAdherent);
	    FicheAdherentPan.add(NomFicheAdherentT);
	    FicheAdherentPan.add(PrenomFicheAdherent);
	    FicheAdherentPan.add(PrenomFicheAdherentT);
	    FicheAdherentPan.add(CodePostal);
	    FicheAdherentPan.add(CodePostalT);

	    
	
	    FicheAdherentPan.add(Ville);
	    FicheAdherentPan.add(VilleT);
	    FicheAdherentPan.add(DateNaissance);
	    FicheAdherentPan.add(DateNaissanceT);
	    FicheAdherentPan.add(TypeAdhesion);
	    FicheAdherentPan.add(TypeAdhesionT);
	    
	    FicheAdherentPan.add(Telephone);
	    FicheAdherentPan.add(TelephoneT);
	    FicheAdherentPan.add(Email);
	    FicheAdherentPan.add(EmailT);
	    FicheAdherentPan.add(Paiement);
	    FicheAdherentPan.add(PaiementT);
	    
	    //Ce qui est affiché ou pas
	    RecherchePrenomPan.setVisible(true);
	    ListeAdherentsPan.setVisible(false);
	    FicheAdherentPan.setVisible(false);
	    
	    //Placement des éléments sur la page
	    contentPan.setLayout(new BorderLayout());
	    contentPan.add(RecherchePrenomPan, BorderLayout.NORTH);
	    contentPan.add(ListeAdherentsPan, BorderLayout.CENTER);
	    contentPan.add(FicheAdherentPan, BorderLayout.SOUTH);
	    
	    //Ajout de la page en elle même
	    this.add(contentPan);
	}
	
	class BoutonRechercheActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			ArrayList<String> items = new ArrayList<String>();

			if(!PrenomTextField.getText().isEmpty())
	    	{	   
			    items.add("Sélectionner...");

				   AdherentDB adDb = new AdherentDB();

				   
				    ArrayList adherent = new ArrayList();

				    adherent=adDb.recherchePrenomsAdherents(PrenomTextField.getText());

				    if(adherent.size()!=0){
				    for(int i = 0; i < adherent.size(); i++)

				    {
				    	items.add(((Adherent) adherent.get(i)).getNom()+" "+((Adherent) adherent.get(i)).getPrenom());
				    

				    }               
				    String[] itemsS = new String[items.size()];
				    itemsS = items.toArray(itemsS);

				    combo.setModel( new DefaultComboBoxModel( itemsS )) ;
				    dim2 = new Dimension(500, 180);
				    contentPan.setPreferredSize(dim2);
				 ListeAdherentsPan.setVisible(true);
				 FicheAdherentPan.setVisible(false);
				    }else{
				    	 ListeAdherentsPan.setVisible(false);
						 FicheAdherentPan.setVisible(false);
				    	JOptionPane jop2 = new JOptionPane();

			    		jop2.showMessageDialog(null, "Aucune résultat! Réessayez SVP! ", "Erreur", JOptionPane.ERROR_MESSAGE);
			    		
				    }
				    

	    	}else {
			    ListeAdherentsPan.setVisible(false);
			    FicheAdherentPan.setVisible(false);
			    
	    		JOptionPane jop1 = new JOptionPane();
		    	jop1.showMessageDialog(null, "Veuillez saisir un prénom ", "Information", JOptionPane.INFORMATION_MESSAGE); 
			}
		}
	}
	class BoutonListenerCombo implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	if ((String)combo.getSelectedItem() != "Sélectionner...") {
	    		dim2 = new Dimension(500, 450);
			    contentPan.setPreferredSize(dim2);
	    		ListeAdherentsPan.setVisible(true);
	    		FicheAdherentPan.setVisible(true);
	    		AdherentDB adDb = new AdherentDB();
	    		Adherent ad;
	    		String[] splitArray = null;
	    		String nomPrenom = (String)combo.getSelectedItem();
	    	
	    		splitArray = nomPrenom.split(" ");
	    		
	    		ad = adDb.getAdherent(splitArray[0],splitArray[1]);
    			
	    		NomFicheAdherentT.setText(ad.getNom());
	    		PrenomFicheAdherentT.setText(ad.getPrenom());
				CodePostalT.setText(ad.getCodePostal());
				VilleT.setText(ad.getVille());
				DateNaissanceT.setText(ad.getAnneeNaissance().toString());
				TypeAdhesionT.setText(ad.getTypeAdhesion().getLibelle());
				TelephoneT.setText(ad.getTelephone());
				EmailT.setText(ad.getEmail());
				 
				if(String.valueOf(ad.getPaiement()) == String.valueOf(false)) {
					PaiementT.setText("non");
				}else {
					PaiementT.setText("oui");
				}
			   
				TitledBorder bf = BorderFactory.createTitledBorder("Fiche Adherent "+ NomFicheAdherentT.getText() +" "+ PrenomFicheAdherentT.getText());
				FicheAdherentPan.setBorder(bf);
				bf.setTitleFont(police);
	    	}else {dim2 = new Dimension(500, 180);
		    contentPan.setPreferredSize(dim2);
    		FicheAdherentPan.setVisible(false);	
    		 JOptionPane jop1 = new JOptionPane();
	    	 jop1.showMessageDialog(null, "Veuillez sélectionner un Adhérent ", "Information", JOptionPane.INFORMATION_MESSAGE);	
    	
	    	}
	    }
	}
	
	
}
