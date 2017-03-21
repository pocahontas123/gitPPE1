package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import model.classe.Adherent;
import model.dataaccesslayer.AdherentDB;

import com.toedter.calendar.JDateChooser;

public class AfficherAdherent extends JPanel{
	private JPanel contentPan = new JPanel();
	private JPanel ListeAdherents, ficheAdherentPan;
	private JLabel Nom, Prenom, CodePostal, Ville,  DateNaissance, TypeAdhesion, Telephone, Email, Paiement, listeAd;
	private JLabel NomT, PrenomT, CodePostalT, VilleT,  DateNaissanceT, TypeAdhesionT, TelephoneT, EmailT, PaiementT;
	private Font police, police1;
	private JComboBox combo;
    private Dimension dim2 = new Dimension(500, 400);
    
	public AfficherAdherent() { 
	    this.setSize(500, 400);
	    this.setVisible(true);
	    contentPan.setPreferredSize(dim2);
	    this.setBackground(Color.WHITE);
	    this.initComposant();
	}
	
	private void initComposant() {
		police = new Font("Arial", Font.BOLD, 20);
	     police1 = new Font("Arial", Font.BOLD, 18);		

	    ListeAdherents = new JPanel();
	    ListeAdherents.setPreferredSize(new Dimension(220, 80));
	    TitledBorder titleBorder = BorderFactory.createTitledBorder("Liste Adherents");
	    ListeAdherents.setBorder(titleBorder);
	    titleBorder.setTitleFont(police);
	    
	    ficheAdherentPan = new JPanel();
	    ficheAdherentPan.setPreferredSize(new Dimension(220, 20));
	    TitledBorder titleBorder1 = BorderFactory.createTitledBorder("Fiche adh�rent");
	    ficheAdherentPan.setBorder(titleBorder1);
	    titleBorder1.setTitleFont(police);
	    

	      listeAd = new JLabel("Liste des Adh�rents : ");
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
		
		combo.addItem("S�lectionner...");
	    for(int i = 0; i < adherent.size(); i++) {
	    	combo.addItem(((Adherent) adherent.get(i)).getNom()+" "+((Adherent) adherent.get(i)).getPrenom());
	    }
	    combo.setPreferredSize(new Dimension(200, 30));
	    
	    combo.addActionListener(new BoutonListenerCombo());

	    ListeAdherents.setVisible(true);
	    ficheAdherentPan.setVisible(false);
	    
	    ListeAdherents.add(listeAd);

	    ListeAdherents.add(combo);
	    contentPan.setLayout(new BorderLayout());
	    contentPan.add(ListeAdherents, BorderLayout.NORTH);
	    contentPan.add(ficheAdherentPan, BorderLayout.CENTER);
	    contentPan.setBackground(Color.WHITE);
	    ListeAdherents.setBackground(Color.WHITE);
	    ficheAdherentPan.setBackground(Color.WHITE);

	    this.add(contentPan);
	}
	
	
	class BoutonListenerCombo implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	 if ((String)combo.getSelectedItem() != "S�lectionner...") {
	    		 ficheAdherentPan.setVisible(true);
	    		 
	    		 AdherentDB adDb = new AdherentDB();
	    		 Adherent ad;
	    		 String[] splitArray = null;
	    		 String nomPrenom = (String)combo.getSelectedItem();
	    		 splitArray = nomPrenom.split(" ");
	    		 ad = adDb.getAdherent(splitArray[0],splitArray[1]);
			   
				 NomT.setText(ad.getNom());
				 PrenomT.setText(ad.getPrenom());
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
			   
				 TitledBorder bf = BorderFactory.createTitledBorder("Fiche Adherent "+ NomT.getText() +" "+ PrenomT.getText());
				 ficheAdherentPan.setBorder(bf);
				 bf.setTitleFont(police);
	    
	    	 }else {
	    		 ficheAdherentPan.setVisible(false);

	    		 JOptionPane jop1 = new JOptionPane();
		    	 jop1.showMessageDialog(null, "Veuillez s�lectionner un Adh�rent ", "Information", JOptionPane.INFORMATION_MESSAGE);	
	    	 } 	 
	    }
	}
	
	
}
