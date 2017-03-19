package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import model.classe.Adherent;
import model.classe.TypeAdhesion;
import model.dataaccesslayer.AdherentDB;
import model.dataaccesslayer.TypeAdhesionDB;

import com.toedter.calendar.JDateChooser;

public class ModifierAdherent extends JPanel{
	private JPanel contentPan = new JPanel();
	private JPanel boutonPan;
	private JPanel ListeAdherents;
	private JButton raz = new JButton ("RAZ");

	private JButton b = new JButton ("Envoyer");
	private Dimension dim = new Dimension(80, 50);
	private Dimension dim2 = new Dimension(500, 500);

	private JTextField NomT;
	private JTextField PrenomT;
	private JFormattedTextField CodePostalT;
	private JTextField VilleT;
	private JTextField TelephoneT;
	private JTextField EmailT;
	private JDateChooser DateT;
	private JComboBox combo, combo2, combo10;
	private Font police, police1;
	  private TitledBorder titleBorder, titleBorder2;
	private JPanel AdherentPan ;  
	private JLabel TypeAdhesion, Nom, Prenom, CodePostal,  Ville, DateNaissance, Telephone, Email, Paiement, listeAd;
	  private Pattern patMail = Pattern.compile("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");

	
	
	public ModifierAdherent() {
		this.setSize(500, 400);
		this.setVisible(true);
    	contentPan.setPreferredSize(dim2);

	    this.initComposant();
	}
	
	
	
	private void initComposant(){
	     police = new Font("Arial", Font.BOLD, 20);
	     police1 = new Font("Arial", Font.BOLD, 18);		

	    ListeAdherents = new JPanel();
	    ListeAdherents.setPreferredSize(new Dimension(220, 80));
	     titleBorder = BorderFactory.createTitledBorder("Liste Adherents");
	    ListeAdherents.setBorder(titleBorder);
	    titleBorder.setTitleFont(police);
	    
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
	    
	    AdherentPan = new JPanel();
	    AdherentPan.setPreferredSize(new Dimension(300, 300));

	     titleBorder2 = BorderFactory.createTitledBorder("Modifier Adherent");
	    AdherentPan.setBorder(titleBorder2);
	    titleBorder2.setTitleFont(police);
	    
	    
	    listeAd = new JLabel("Liste des Adhérents : ");
	    listeAd.setFont(police1);
	    listeAd.setHorizontalAlignment(JLabel.RIGHT);
	    listeAd.setPreferredSize(new Dimension(220, 20));
	    
	    Nom = new JLabel("Nom : ");
	    Nom.setFont(police1);
	    Nom.setHorizontalAlignment(JLabel.RIGHT);
	    Nom.setPreferredSize(new Dimension(220, 20));
	    
	    NomT = new JTextField();	
	    
	    Prenom = new JLabel("Prenom : ");
	    Prenom.setFont(police1);
	    Prenom.setHorizontalAlignment(JLabel.RIGHT);
	    Prenom.setPreferredSize(new Dimension(220, 20));
	    
	    PrenomT = new JTextField();	    

	    CodePostal = new JLabel("Code Postal : ");
	    CodePostal.setFont(police1);
	    CodePostal.setHorizontalAlignment(JLabel.RIGHT);
	    CodePostal.setPreferredSize(new Dimension(220, 20));
	    

	    try{
	    	 
	    	  MaskFormatter telMask = new MaskFormatter("#####");
	    	  CodePostalT = new JFormattedTextField(telMask);
	    	}catch(ParseException e){e.printStackTrace();}

	    Ville = new JLabel("Ville : ");
	    Ville.setFont(police1);
	    Ville.setHorizontalAlignment(JLabel.RIGHT);
	    Ville.setPreferredSize(new Dimension(220, 20));
	    
	    VilleT = new JTextField();	    

	     
	    DateNaissance = new JLabel("Date de Naissance : ");
	    DateNaissance.setFont(police1);
	    DateNaissance.setHorizontalAlignment(JLabel.RIGHT);
	    DateNaissance.setPreferredSize(new Dimension(220, 20));
	    
	    DateT = new JDateChooser();
	    DateT.setDateFormatString("yyyy-MM-dd");

	    Telephone = new JLabel("Telephone : ");
	    Telephone.setFont(police1);
	    Telephone.setHorizontalAlignment(JLabel.RIGHT);
	    Telephone.setPreferredSize(new Dimension(220, 20));
	    

	    try{
	    	 
	    	  MaskFormatter telMask = new MaskFormatter("0#.##.##.##.##");
	    	   TelephoneT = new JFormattedTextField(telMask);
	    	}catch(ParseException e){e.printStackTrace();}
	    
	    
	    Email = new JLabel("Email : ");
	    Email.setFont(police1);
	    Email.setHorizontalAlignment(JLabel.RIGHT);
	    Email.setPreferredSize(new Dimension(220, 20));
	    
	    EmailT = new JTextField();
	    
	    	    
	    TypeAdhesion = new JLabel("Type Adhesion : ");
	    TypeAdhesion.setFont(police1);
	    TypeAdhesion.setHorizontalAlignment(JLabel.RIGHT);
	    TypeAdhesion.setPreferredSize(new Dimension(220, 20));
	    
	    combo2 = new JComboBox();
	    
	    combo2.setPreferredSize(new Dimension(100, 20));
	    TypeAdhesionDB adhesionDB = new TypeAdhesionDB();
		ArrayList adhesion = new ArrayList();
	
		adhesion = adhesionDB.getTypeAdhesions();
		
		combo2.addItem("Sélectionner...");
	    for(int i = 0; i < adhesion.size(); i++) {
	    	combo2.addItem(((TypeAdhesion) adhesion.get(i)).getLibelle());
	    }
	    
	    Paiement = new JLabel("Paiement : ");
	    Paiement.setFont(police1);
	    Paiement.setHorizontalAlignment(JLabel.RIGHT);
	    Paiement.setPreferredSize(new Dimension(220, 20));
	    
	    combo10 = new JComboBox(); 
	    combo10.addItem("oui");
	    combo10.addItem("non");
	    
	    NomT.setPreferredSize(new Dimension(200, 30));
	    PrenomT.setPreferredSize(new Dimension(200, 30));
	    CodePostalT.setPreferredSize(new Dimension(200, 30));
	    VilleT.setPreferredSize(new Dimension(200, 30));
	    DateT.setPreferredSize(new Dimension(200, 30));
	    TelephoneT.setPreferredSize(new Dimension(200, 30));
	    EmailT.setPreferredSize(new Dimension(200, 30));
	    combo2.setPreferredSize(new Dimension(200, 30));
	    combo10.setPreferredSize(new Dimension(200, 30));
	
	   
	    
	    AdherentPan.add(Nom);
	    AdherentPan.add(NomT);
	    AdherentPan.add(Prenom);
	    AdherentPan.add(PrenomT);
	    AdherentPan.add(CodePostal);
	    AdherentPan.add(CodePostalT);
	
	    AdherentPan.add(Ville);
	    AdherentPan.add(VilleT);
	    AdherentPan.add(DateNaissance);
	    AdherentPan.add(DateT);
	    AdherentPan.add(Telephone);
	    AdherentPan.add(TelephoneT);
	    AdherentPan.add(Email);
	    AdherentPan.add(EmailT);
	
	    AdherentPan.add(TypeAdhesion);
	    AdherentPan.add(combo2);
	    
	    AdherentPan.add(Paiement);
	    AdherentPan.add(combo10);
	    
	    b.setPreferredSize(dim);
	    raz.setPreferredSize(dim);
	    
	    b.addActionListener(new BoutonListener());
	    raz.addActionListener(new BoutonListenerRaz());
	    

	    boutonPan = new JPanel();
	    boutonPan.add(b);
	    boutonPan.add(raz);
	    boutonPan.setPreferredSize(new Dimension(50, 60));

	    
	    
	    EmailT.addFocusListener(new FocusListener() {
	          public void focusLost(FocusEvent e) {
	      	    if (!patMail.matcher(EmailT.getText()).matches()) {
	      	    	EmailT.setForeground(Color.red);
	            	  JOptionPane jop3 = new JOptionPane();
	            		
			    		jop3.showMessageDialog(null, "Email Invalide!", "Erreur", JOptionPane.ERROR_MESSAGE);	
	              }
	          }

	          public void focusGained(FocusEvent e) {
	      	    	EmailT.setForeground(Color.black);

	          }
	      });
	    
	    
	    
	    
	    ((JTextField)DateT.getDateEditor().getUiComponent()).addFocusListener(new FocusListener() {
	          public void focusLost(FocusEvent e) {
	        	  if(!((JTextField)DateT.getDateEditor().getUiComponent()).getText().isEmpty())
	        	  {
	        	try{
					java.sql.Date dateDB = new java.sql.Date(DateT.getDate().getTime());

	        	}catch(Exception ex) {
	    			ex.printStackTrace();
	        		 JOptionPane jop3 = new JOptionPane();
	            		
			    		jop3.showMessageDialog(null, "Date de Naissance Invalide!", "Erreur", JOptionPane.ERROR_MESSAGE);
	        	}	
	        		 
	          }
	          }
	          public void focusGained(FocusEvent e) {
	        	  

	          }
	      });
	    
	    
	    ListeAdherents.setVisible(true);
	    AdherentPan.setVisible(false);
	    boutonPan.setVisible(false);
	    
	    
	    
	    contentPan.setLayout(new BorderLayout());
	    ListeAdherents.add(listeAd);

	    ListeAdherents.add(combo);
	    contentPan.add(ListeAdherents, BorderLayout.NORTH);
	    contentPan.add(AdherentPan, BorderLayout.CENTER);
	    contentPan.add(boutonPan, BorderLayout.SOUTH);
	    
	    this.add(contentPan);
	}
	
	  
	class BoutonListenerCombo implements ActionListener {
		

		public void actionPerformed(ActionEvent e) {
			if ((String)combo.getSelectedItem() != "Sélectionner...") {		 
	    		 AdherentPan.setVisible(true);
	    		 boutonPan.setVisible(true);
	    		 
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
				 //s'occupe de mettre la date
				 String dateValue = ad.getAnneeNaissance().toString();
				 try {
					java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateValue);
					DateT.setDate(date);
					
				 } catch (ParseException e1) {
					e1.printStackTrace();
				 }
				 
				 TelephoneT.setText(ad.getTelephone());
				 EmailT.setText(ad.getEmail());
				 
		    	 String paiement;
		    	 if( ad.getPaiement() == true) {
		    		 paiement = "oui";
		    	 }else {
		    		 paiement = "non";
		    	 }
				 Object obj2 = combo10.getSelectedItem();
				 combo10.setSelectedItem(paiement);
				 obj2 = combo10.getSelectedItem();
				 
				 
				 //s'occupe de mettre le type de l'adhesion de la personne séléctionnée
				 Object obj = combo2.getSelectedItem();
				 combo2.setSelectedItem(ad.getTypeAdhesion().getLibelle());
				 obj = combo2.getSelectedItem();
					titleBorder2 = BorderFactory.createTitledBorder("Modifier Adherent "+ NomT.getText() +" "+ PrenomT.getText());
					AdherentPan.setBorder(titleBorder2);
					titleBorder2.setTitleFont(police);
		    
			}else {
				AdherentPan.setVisible(false);
				boutonPan.setVisible(false);
				
	    		JOptionPane jop1 = new JOptionPane();
		    	jop1.showMessageDialog(null, "Veuillez sélectionner un Adhérent ", "Information", JOptionPane.INFORMATION_MESSAGE);	
			}
		}
	}
	
	
	
	class BoutonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if(!NomT.getText().isEmpty() && !PrenomT.getText().isEmpty() && !CodePostalT.getText().isEmpty() && !VilleT.getText().isEmpty() && !((JTextField)DateT.getDateEditor().getUiComponent()).getText().isEmpty() && !TelephoneT.getText().isEmpty() && !EmailT.getText().isEmpty() && (String)combo2.getSelectedItem() != "Sélectionner..." && patMail.matcher(EmailT.getText()).matches()) {
				AdherentDB adDB = new AdherentDB();    
				
				String[] splitArray = null;
	    		String nomPrenom = (String)combo.getSelectedItem();
	    		splitArray = nomPrenom.split(" ");
	    		Adherent ad = adDB.getAdherent(splitArray[0],splitArray[1]);
				 
				boolean exist;
				
			    TypeAdhesion typeAd;
			    TypeAdhesionDB adhesionDB = new TypeAdhesionDB();
			    System.out.println((String)combo2.getSelectedItem());

			    typeAd = adhesionDB.getTypeAdhesion((String)combo2.getSelectedItem());
			   
			    try{
				java.sql.Date dateDB = new java.sql.Date(DateT.getDate().getTime());

				ad.setCodePostal(CodePostalT.getText());
				ad.setNom(NomT.getText());
				ad.setVille(VilleT.getText());
				ad.setTelephone(TelephoneT.getText());
				ad.setEmail(EmailT.getText());

				ad.setPrenom(PrenomT.getText());
				ad.setTypeAdhesion(typeAd);
				ad.setAnneeNaissance(dateDB);
				boolean paiement;
		    	if((String)combo10.getSelectedItem() == "oui") {
		    		paiement = true;
		    	}else {
		    		paiement = false;
		    	}
		    	
				ad.setPaiement(paiement);
				
			    exist = adDB.updateAdherent(ad);
			     
			    if(exist) {
			    	JOptionPane jop1 = new JOptionPane();
			    		
			    	jop1.showMessageDialog(null, "L'adhérent "+ ad.getNom()+ " "+ ad.getPrenom()+" a été modifié avec succès ", "Information", JOptionPane.INFORMATION_MESSAGE);
			    	
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
			    		   
			        image.setVisible(true);
			    		   
			        contentPan.add(image, BorderLayout.CENTER);	
			        
			    }else {
			    	JOptionPane jop = new JOptionPane();

			    	jop.showMessageDialog(null, "Erreur de mise à jours ", "Erreur", JOptionPane.ERROR_MESSAGE);
			     }
			}catch(Exception ex) {
    			ex.printStackTrace();
        		 JOptionPane jop3 = new JOptionPane();
            		
		    		jop3.showMessageDialog(null, "Date de Naissance Invalide!", "Erreur", JOptionPane.ERROR_MESSAGE);
        	}
		     }else {
		    	 JOptionPane jop3 = new JOptionPane();

		    	 jop3.showMessageDialog(null, "Champ(s) Vide(s) et/ou Invalide(s) ", "Erreur", JOptionPane.ERROR_MESSAGE);	
			 }	
		}
		
	}
	
	class BoutonListenerRaz implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			NomT.setText("");
			PrenomT.setText("");
			CodePostalT.setText("");
			VilleT.setText("");
			TelephoneT.setText("");
			EmailT.setText("");
			((JTextField)DateT.getDateEditor()).setText("");	
		}
		
	}
	
}
