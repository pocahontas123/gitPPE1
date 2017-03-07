package IHM;

import DataAcessLayer.*;
import Classes.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



public class AjoutAdherent extends JFrame {

	private JPanel contentPane = new JPanel();
	private JLabel titre = new JLabel("Ajout Adherent");
	 private JButton b = new JButton ("Envoyer");
	  private Dimension dim = new Dimension(80, 50);
	  private JTextField NomT;
	  private JTextField PrenomT;
	  private JFormattedTextField CodePostalT;
	  private JTextField VilleT;
	  private JTextField DateT;
	  private  JComboBox combo;
	  
	  

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public AjoutAdherent() {
		 this.setTitle("Ajout Adherent");
		    this.setSize(500, 400);
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    this.setLocationRelativeTo(null);
		    this.setResizable(false);
		    initComposant();

		    this.setContentPane(contentPane);
	}
	
	
	
	private void initComposant(){
	    Font police = new Font("Arial", Font.BOLD, 20);
	    titre = new JLabel("Ajout Adherent");
	    titre.setFont(police);
	    titre.setHorizontalAlignment(JLabel.CENTER);
	    titre.setPreferredSize(new Dimension(220, 20));
	    JPanel AdherentPan = new JPanel();
	    AdherentPan.setPreferredSize(new Dimension(165, 225));
	    AdherentPan.setBorder(BorderFactory.createTitledBorder("Ajout Adherent"));
	    JLabel  Nom = new JLabel("Nom : ");
	    Nom.setFont(police);
	    Nom.setHorizontalAlignment(JLabel.CENTER);
	    Nom.setPreferredSize(new Dimension(220, 20));
	    
	     NomT = new JTextField();	    
	    
	    JLabel Prenom = new JLabel("Prenom");
	    Prenom.setFont(police);
	    Prenom.setHorizontalAlignment(JLabel.CENTER);
	    Prenom.setPreferredSize(new Dimension(220, 20));
	    
	     PrenomT = new JTextField();	    

	    JLabel CodePostal = new JLabel("Code Postal");
	    CodePostal.setFont(police);
	    CodePostal.setHorizontalAlignment(JLabel.CENTER);
	    CodePostal.setPreferredSize(new Dimension(220, 20));
	    
	      CodePostalT = new JFormattedTextField(NumberFormat.getNumberInstance());


	    JLabel  Ville = new JLabel("Ville");
	    Ville.setFont(police);
	    Ville.setHorizontalAlignment(JLabel.CENTER);
	    Ville.setPreferredSize(new Dimension(220, 20));
	    
	     VilleT = new JTextField();	    

	     
	    JLabel  DateNaissance = new JLabel("Date de Naissance");
	    DateNaissance.setFont(police);
	    DateNaissance.setHorizontalAlignment(JLabel.CENTER);
	    DateNaissance.setPreferredSize(new Dimension(220, 20));
	    
	      DateT = new JTextField();

	    
	    
	    JLabel TypeAdhesion = new JLabel("Type Adhesion");
	    TypeAdhesion.setFont(police);
	    TypeAdhesion.setHorizontalAlignment(JLabel.CENTER);
	    TypeAdhesion.setPreferredSize(new Dimension(220, 20));
	    
	      combo = new JComboBox();
	    
	     combo.setPreferredSize(new Dimension(100, 20));
	     TypeAdhesionDB adhesionDB= new TypeAdhesionDB();
		    ArrayList adhesion = new ArrayList();

		    adhesion=adhesionDB.getTypeAdhesions();
		    
		    for(int i = 0; i < adhesion.size(); i++)

		    {
		    	
		    	combo.addItem(((TypeAdhesion) adhesion.get(i)).getLibelle());

		    }               
	     

	    
	     NomT.setPreferredSize(new Dimension(200, 30));
	     PrenomT.setPreferredSize(new Dimension(200, 30));
	     CodePostalT.setPreferredSize(new Dimension(200, 30));
	     VilleT.setPreferredSize(new Dimension(200, 30));
	     DateT.setPreferredSize(new Dimension(200, 30));
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

	     AdherentPan.add(TypeAdhesion);
	     AdherentPan.add(combo);

	    b.setPreferredSize(dim);

	   b.addActionListener(new BoutonListener());
	   JPanel boutonPan = new JPanel();
	    boutonPan.add(b);
	    //On parcourt le tableau initialisé
	    //afin de créer nos boutons
	    
	   contentPane.setLayout(new BorderLayout());

	   contentPane.add(titre, BorderLayout.NORTH);
	   contentPane.add(AdherentPan, BorderLayout.CENTER);
	   contentPane.add(boutonPan, BorderLayout.SOUTH);
	  }
	class BoutonListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	if(!NomT.getText().isEmpty() && !PrenomT.getText().isEmpty() && !CodePostalT.getText().isEmpty() && !VilleT.getText().isEmpty() && !DateT.getText().isEmpty() )
{			boolean resutlVerif;
	    	TypeAdhesion typeAd ;
	    	 TypeAdhesionDB adhesionDB= new TypeAdhesionDB();
	    	 typeAd=adhesionDB.getTypeAdhesion((String)combo.getSelectedItem());
	    	// String date =  DateT.getText();
	 		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	 		
	 		java.util.Date dateStr;
			try {
				dateStr = format.parse(DateT.getText());
				java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());
		    	Adherent ad= new Adherent(NomT.getText(), PrenomT.getText(), CodePostalT.getText(), VilleT.getText(),dateDB, typeAd);
		    	AdherentDB adDb = new AdherentDB();
		    	resutlVerif=   adDb.saveAdherent(ad);
		    	
		    	if (resutlVerif)
		    	{
		    		JOptionPane jop = new JOptionPane();

		    		jop.showMessageDialog(null, "Adhérent déjà existant ", "Erreur", JOptionPane.ERROR_MESSAGE);
		    	}
		    	else
		    	{
		    		JOptionPane jop1 = new JOptionPane();

		    		jop1.showMessageDialog(null, "Adhérent enregistré avec succès ", "Information", JOptionPane.INFORMATION_MESSAGE);
		    	
		    		
		    	}
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	 		
	    	}
	    	else{
	    		JOptionPane jop3 = new JOptionPane();

	    		jop3.showMessageDialog(null, "Champ(s) Vide(s) ", "Erreur", JOptionPane.ERROR_MESSAGE);
	    		
	    	}
	    }
	  }
	
}
