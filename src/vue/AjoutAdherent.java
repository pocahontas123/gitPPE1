package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.MessageFormat;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import model.classe.*;
import model.dataaccesslayer.*;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import java.util.regex.Pattern;



public class AjoutAdherent extends JPanel {
	private JPanel contentPan = new JPanel();
	private JPanel boutonPan;

	private JButton raz = new JButton ("RAZ");

	private JButton b = new JButton ("Envoyer");
	private Dimension dim = new Dimension(80, 50);
	private Dimension dim2 = new Dimension(500, 410);

	private JTextField NomT;
	private JTextField PrenomT;
	private JFormattedTextField CodePostalT;
	private JTextField VilleT;
	private JTextField TelephoneT;
	private JTextField EmailT;
	private JDateChooser DateT;
	private JComboBox combo, combo10;
	  
	private JPanel AdherentPan ;  
	private JLabel TypeAdhesion, Nom, Prenom, CodePostal,  Ville, DateNaissance, Telephone, Email, Paiement;
	  private Pattern patMail = Pattern.compile("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");

	  /**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public AjoutAdherent() {
		this.setSize(500, 400);
		this.setVisible(true);
    	contentPan.setPreferredSize(dim2);

	    this.initComposant();
	}
	
	
	
	private void initComposant(){
	    Font police = new Font("Arial", Font.BOLD, 20);
	    
	    AdherentPan = new JPanel();
	    AdherentPan.setPreferredSize(new Dimension(165, 225));
	    TitledBorder titleBorder = BorderFactory.createTitledBorder("Ajout Adherent");
	    AdherentPan.setBorder(titleBorder);
	    titleBorder.setTitleFont(police);
	    
	    Nom = new JLabel("Nom : ");
	    Nom.setFont(police);
	    Nom.setHorizontalAlignment(JLabel.RIGHT);
	    Nom.setPreferredSize(new Dimension(220, 20));
	    
	    NomT = new JTextField();	
	    
	    Prenom = new JLabel("Prenom : ");
	    Prenom.setFont(police);
	    Prenom.setHorizontalAlignment(JLabel.RIGHT);
	    Prenom.setPreferredSize(new Dimension(220, 20));
	    
	    PrenomT = new JTextField();	    

	    CodePostal = new JLabel("Code Postal : ");
	    CodePostal.setFont(police);
	    CodePostal.setHorizontalAlignment(JLabel.RIGHT);
	    CodePostal.setPreferredSize(new Dimension(220, 20));
	    

	    try{
	    	 
	    	  MaskFormatter telMask = new MaskFormatter("#####");
	    	  CodePostalT = new JFormattedTextField(telMask);
	    	}catch(ParseException e){e.printStackTrace();}
	    

	    Ville = new JLabel("Ville : ");
	    Ville.setFont(police);
	    Ville.setHorizontalAlignment(JLabel.RIGHT);
	    Ville.setPreferredSize(new Dimension(220, 20));
	    
	    VilleT = new JTextField();	    

	     
	    DateNaissance = new JLabel("Date de Naissance : ");
	    DateNaissance.setFont(police);
	    DateNaissance.setHorizontalAlignment(JLabel.RIGHT);
	    DateNaissance.setPreferredSize(new Dimension(220, 20));
	    
	    DateT = new JDateChooser();
	    DateT.setDateFormatString("yyyy-MM-dd");

	     
	    TypeAdhesion = new JLabel("Type Adhesion : ");
	    TypeAdhesion.setFont(police);
	    TypeAdhesion.setHorizontalAlignment(JLabel.RIGHT);
	    TypeAdhesion.setPreferredSize(new Dimension(220, 20));
	    
	    Telephone = new JLabel("Telephone : ");
	    Telephone.setFont(police);
	    Telephone.setHorizontalAlignment(JLabel.RIGHT);
	    Telephone.setPreferredSize(new Dimension(220, 20));
	    
	    try{
	    	 
	    	  MaskFormatter telMask = new MaskFormatter("0#.##.##.##.##");
	    	   TelephoneT = new JFormattedTextField(telMask);
	    	}catch(ParseException e){e.printStackTrace();}
	    
	    
	   
	    
	    
	    Email = new JLabel("Email : ");
	    Email.setFont(police);
	    Email.setHorizontalAlignment(JLabel.RIGHT);
	    Email.setPreferredSize(new Dimension(220, 20));
	    
	    EmailT = new JTextField();
	    
	    Paiement = new JLabel("Paiement : ");
	    Paiement.setFont(police);
	    Paiement.setHorizontalAlignment(JLabel.RIGHT);
	    Paiement.setPreferredSize(new Dimension(220, 20));
	    
	    combo10 = new JComboBox();
	    combo10.addItem("oui");
	    combo10.addItem("non");
	    
	    
	    combo = new JComboBox();
	    
	    combo.setPreferredSize(new Dimension(100, 20));
	    TypeAdhesionDB adhesionDB = new TypeAdhesionDB();
		ArrayList adhesion = new ArrayList();
	
		adhesion = adhesionDB.getTypeAdhesions();
		
		combo.addItem("S�lectionner..."); 
	    for(int i = 0; i < adhesion.size(); i++) {
	    	combo.addItem(((TypeAdhesion) adhesion.get(i)).getLibelle());
	    }               
	     
	    NomT.setPreferredSize(new Dimension(200, 30));
	    PrenomT.setPreferredSize(new Dimension(200, 30));
	    CodePostalT.setPreferredSize(new Dimension(200, 30));
	    VilleT.setPreferredSize(new Dimension(200, 30));
	    DateT.setPreferredSize(new Dimension(200, 30));
	    TelephoneT.setPreferredSize(new Dimension(200, 30));
	    EmailT.setPreferredSize(new Dimension(200, 30));
	    combo10.setPreferredSize(new Dimension(200, 30));
	    combo.setPreferredSize(new Dimension(200, 30));
	
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
	    AdherentPan.add(combo);
	    
	    AdherentPan.add(Paiement);
	    AdherentPan.add(combo10);
	
	    b.setPreferredSize(dim);
	    raz.setPreferredSize(dim);
	    b.addActionListener(new BoutonListener());
	    raz.addActionListener(new BoutonListenerRaz());
	    
	
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
	    

	    
	    boutonPan = new JPanel();
	    boutonPan.add(b);
	    boutonPan.add(raz);
	
	    contentPan.setLayout(new BorderLayout());
	
	    contentPan.add(AdherentPan, BorderLayout.CENTER);
	    contentPan.add(boutonPan, BorderLayout.SOUTH);
	    
	    this.add(contentPan);
	}
	

	
	
	class BoutonListenerRaz implements ActionListener{
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
	  
	class BoutonListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	System.out.println("D�but BoutonListener");
	    	
		    	if(!NomT.getText().isEmpty() && !PrenomT.getText().isEmpty() && !CodePostalT.getText().isEmpty() && !VilleT.getText().isEmpty() && !((JTextField)DateT.getDateEditor().getUiComponent()).getText().isEmpty() && !TelephoneT.getText().isEmpty() && !EmailT.getText().isEmpty() && (String)combo.getSelectedItem()!="S�lectionner..." && patMail.matcher(EmailT.getText()).matches()) {				
		    		System.out.println("Si zones de text non vides: ");
		    		boolean resutlVerif;
			    	TypeAdhesion typeAd ;
			    	TypeAdhesionDB adhesionDB = new TypeAdhesionDB();
			    	typeAd = adhesionDB.getTypeAdhesion((String)combo.getSelectedItem());
			    	
			    	boolean paiement;
			    	if((String)combo10.getSelectedItem() == "oui") {
			    		paiement = true;
			    	}else {
			    		paiement = false;
			    	}
			    	
			    	try{
						java.sql.Date dateDB = new java.sql.Date(DateT.getDate().getTime());

		        	Adherent ad = new Adherent(NomT.getText(), PrenomT.getText(), CodePostalT.getText(), VilleT.getText(),dateDB, typeAd, TelephoneT.getText(), EmailT.getText(), paiement);
			    	AdherentDB adDb = new AdherentDB();
			    	resutlVerif = adDb.saveAdherent(ad);
			    	
			    	if (resutlVerif) {
			    		JOptionPane jop = new JOptionPane();
	
			    		jop.showMessageDialog(null, "Adh�rent d�j� existant ", "Erreur", JOptionPane.ERROR_MESSAGE);
			    	}else {
			    		JOptionPane jop1 = new JOptionPane();
	
			    		jop1.showMessageDialog(null, "L'adh�rent "+ ad.getNom()+ " "+ ad.getPrenom()+" est enregistr� avec succ�s ", "Information", JOptionPane.INFORMATION_MESSAGE);
			    	
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
			    	}
			    	}catch(Exception ex) {
		    			ex.printStackTrace();
		        		 JOptionPane jop3 = new JOptionPane();
		            		
				    		jop3.showMessageDialog(null, "Date de Naissance Invalide!", "Erreur", JOptionPane.ERROR_MESSAGE);
		        	}
		    	}else {
		    		JOptionPane jop3 = new JOptionPane();
	
		    		jop3.showMessageDialog(null, "Champ(s) Vide(s) et/ou Invalide(s)", "Erreur", JOptionPane.ERROR_MESSAGE);	
		    	}
		    
		}	
	}
	
	
}



