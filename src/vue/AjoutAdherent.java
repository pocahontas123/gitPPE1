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

//Hérite d'un JPanel
public class AjoutAdherent extends JPanel {
	//Variables membres
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
	//regex pour vérifier le mail
	private Pattern patMail = Pattern.compile("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	
	//Constructeur par défaut
	public AjoutAdherent() {
		this.setSize(500, 400);
		this.setVisible(true);
    	contentPan.setPreferredSize(dim2);
		this.setBackground(Color.white);

	    this.initComposant();
	}
	
	//Crée et initialise les composants de la page
	private void initComposant(){
		//Crée une police
	    Font police = new Font("Arial", Font.BOLD, 20);
	    
	    //Création et initialisation des JPanel (gauche) et des JTextField associés (droite)
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
	    
	    //Masque pour le code postale (5 chiffres)
	    try{ 
	    	MaskFormatter telMask = new MaskFormatter("#####");
	    	CodePostalT = new JFormattedTextField(telMask);
	    	
	    }catch(ParseException e){e.printStackTrace();
	    }
	    

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
	    
	    //Masque pour le téléphone ("0 . . . . ")
	    try{ 
	    	MaskFormatter telMask = new MaskFormatter("0#.##.##.##.##");
	    	TelephoneT = new JFormattedTextField(telMask);
	    	
	    }catch(ParseException e){e.printStackTrace();
	    }
	    
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
		//Récupère les différents types d'adhésion (catégories)
		adhesion = adhesionDB.getTypeAdhesions();
		
		combo.addItem("Sélectionner..."); 
	    for(int i = 0; i < adhesion.size(); i++) {
	    	//Affiche le libelle des différentes catégories contenues dans l'objet "adhesion"
	    	combo.addItem(((TypeAdhesion) adhesion.get(i)).getLibelle());
	    }               
	    
	    //Gère la taille des différents JTextField
	    NomT.setPreferredSize(new Dimension(200, 30));
	    PrenomT.setPreferredSize(new Dimension(200, 30));
	    CodePostalT.setPreferredSize(new Dimension(200, 30));
	    VilleT.setPreferredSize(new Dimension(200, 30));
	    DateT.setPreferredSize(new Dimension(200, 30));
	    TelephoneT.setPreferredSize(new Dimension(200, 30));
	    EmailT.setPreferredSize(new Dimension(200, 30));
	    combo10.setPreferredSize(new Dimension(200, 30));
	    combo.setPreferredSize(new Dimension(200, 30));
	    
	    //Ajoute les différents éléments aux JPanel AdherentPan
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
	    
	    //Deux listener sur les deux boutons "Envoyer" et "RAZ"
	    b.addActionListener(new BoutonListener());
	    raz.addActionListener(new BoutonListenerRaz());
	    
	    //Ajoute un "Focus" listener au JTextField Email
	    EmailT.addFocusListener(new FocusListener() {
	          public void focusLost(FocusEvent e) {
	        	  //Si mon EmailT JTextField ne coorespond pas au patern regex alors
	        	  if (!patMail.matcher(EmailT.getText()).matches()) {
	        		  //je colore l'email en rouge
	        		  EmailT.setForeground(Color.red);
	        		  //j'affiche une pop-up avec le message "Email Invalide!"
	            	  JOptionPane jop3 = new JOptionPane();
	            		
			    	  jop3.showMessageDialog(null, "Email Invalide!", "Erreur", JOptionPane.ERROR_MESSAGE);	
	              }
	          }
	          
	          //Quand je suis dans le JTextField, mon Email devient noir
	          public void focusGained(FocusEvent e) {
	      	    	EmailT.setForeground(Color.black);
	          }
	      });
	    
	    //Focus listener sur la partie date
	    ((JTextField)DateT.getDateEditor().getUiComponent()).addFocusListener(new FocusListener() {
	          public void focusLost(FocusEvent e) {
	        	  if(!((JTextField)DateT.getDateEditor().getUiComponent()).getText().isEmpty()) {
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
	    
	    //Création du BorderLayout "contentPan"
	    contentPan.setLayout(new BorderLayout());
	
	    //Assigne AdherentPan (CENTER) et boutonPan (SOUTH) à "contentPan"
	    contentPan.add(AdherentPan, BorderLayout.CENTER);
	    contentPan.add(boutonPan, BorderLayout.SOUTH);
	    
	    //Couleur de fond
	    contentPan.setBackground(Color.WHITE);
	    AdherentPan.setBackground(Color.WHITE);
	    boutonPan.setBackground(Color.WHITE);

	    this.add(contentPan);
	}
	
	//Listener du bouton "RAZ"
	class BoutonListenerRaz implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//On vide tous les texts du formulaire
			NomT.setText("");
			PrenomT.setText("");
			CodePostalT.setText("");
			VilleT.setText("");
			TelephoneT.setText("");
			EmailT.setText("");
			((JTextField)DateT.getDateEditor()).setText("");	
	    }
	}
	
	//Listener du bouton b
	class BoutonListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	
	    	System.out.println("Début BoutonListener");
	    		//Si les élements de mon formulaire ne sont pas vide
		    	if(!NomT.getText().isEmpty() && !PrenomT.getText().isEmpty() && !CodePostalT.getText().isEmpty() && !VilleT.getText().isEmpty() && !((JTextField)DateT.getDateEditor().getUiComponent()).getText().isEmpty() && !TelephoneT.getText().isEmpty() && !EmailT.getText().isEmpty() && (String)combo.getSelectedItem()!="Sélectionner..." && patMail.matcher(EmailT.getText()).matches()) {				
		    		System.out.println("Si zones de text non vides: ");
		    		
		    		boolean resutlVerif;
			    	TypeAdhesion typeAd ;
			    	TypeAdhesionDB adhesionDB = new TypeAdhesionDB();
			    	//Le type d'adhesion sélectionné dans la liste
			    	typeAd = adhesionDB.getTypeAdhesion((String)combo.getSelectedItem());
			    	
			    	//Conversion du paiement pour la bdd
			    	boolean paiement;
			    	if((String)combo10.getSelectedItem() == "oui") {
			    		paiement = true;
			    	}else {
			    		paiement = false;
			    	}
			    	
			    	try{
						java.sql.Date dateDB = new java.sql.Date(DateT.getDate().getTime());
						
						//Je crée un adhérent avec les infos
			        	Adherent ad = new Adherent(NomT.getText(), PrenomT.getText(), CodePostalT.getText(), VilleT.getText(),dateDB, typeAd, TelephoneT.getText(), EmailT.getText(), paiement);
				    	AdherentDB adDb = new AdherentDB();
				    	//Je sauvegarde mon adhérent
				    	resutlVerif = adDb.saveAdherent(ad);
			    	
				    	//Si false
				    	if(resutlVerif) {
				    		JOptionPane jop = new JOptionPane();
				    		//pop-up "Adhérent déjà existant"
				    		jop.showMessageDialog(null, "Adhérent déjà existant ", "Erreur", JOptionPane.ERROR_MESSAGE);
				    	}else {
				    		JOptionPane jop1 = new JOptionPane();
				    		//pop-up "Adhérent ajouté"
				    		jop1.showMessageDialog(null, "L'adhérent "+ ad.getNom()+ " "+ ad.getPrenom()+" est enregistré avec succès ", "Information", JOptionPane.INFORMATION_MESSAGE);
				    	
				    		//Vide le contentPan
				    		contentPan.removeAll();
				    		contentPan.revalidate();
				    		contentPan.repaint();
				    		
				    		//Récupère et crée un JPanel qui contiendra mes deux images
			    			URL imageM2l = PagePrincipale.class.getResource("/MDL.jpg");
			    			URL  imageTir = PagePrincipale.class.getResource("/s-l225.png");
			    			ImageIcon  iconM2l = new ImageIcon(imageM2l);
			    			ImageIcon iconTir = new ImageIcon(imageTir);
			    			JPanel image = new JPanel();
			    			
			    			//Mon JPanel contient mes icones
			    		    image.add(new JLabel(iconM2l));
			    		    image.add(new JLabel(iconTir));
			    		    //couleur de fond
				    		image.setBackground(Color.WHITE);
				    		//image visible
				    		image.setVisible(true);
				    		
				    		//Place l'image au Centre
				    		contentPan.add(image, BorderLayout.CENTER);
				    	}
				    	
			    	}catch(Exception ex) {
			    		ex.printStackTrace();
			    		//Date pas valide
		        		JOptionPane jop3 = new JOptionPane();
		            		
				    	jop3.showMessageDialog(null, "Date de Naissance Invalide!", "Erreur", JOptionPane.ERROR_MESSAGE);
		        	}
		    	}else {
		    		//champ(s) vide(s)
		    		JOptionPane jop3 = new JOptionPane();
	
		    		jop3.showMessageDialog(null, "Champ(s) Vide(s) et/ou Invalide(s)", "Erreur", JOptionPane.ERROR_MESSAGE);	
		        }  
		}	
	}
	
	
}