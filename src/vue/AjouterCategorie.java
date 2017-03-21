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

public class AjouterCategorie extends JPanel{
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
	
	
	public AjouterCategorie() {
		this.setSize(500, 400);
		this.setVisible(true);
    	contentPan.setPreferredSize(dim2);
		this.setBackground(Color.white);

	    this.initComposant();
	}
	
	
	
	private void initComposant() {
	     police = new Font("Arial", Font.BOLD, 20);
	     police1 = new Font("Arial", Font.BOLD, 18);		

	    AdherentPan = new JPanel();
	    AdherentPan.setPreferredSize(new Dimension(165, 200));
	    TitledBorder titleBorder = BorderFactory.createTitledBorder("Ajout catégorie");
	    AdherentPan.setBorder(titleBorder);
	    titleBorder.setTitleFont(police);
	    libelle = new JLabel("Libelle : ");
	    libelle.setFont(police1);
	    libelle.setHorizontalAlignment(JLabel.RIGHT);
	    libelle.setPreferredSize(new Dimension(220, 20));
	    
	    libelleT = new JTextField();
	    
	    prix = new JLabel("Prix : ");
	    prix.setFont(police1);
	    prix.setHorizontalAlignment(JLabel.RIGHT);
	    prix.setPreferredSize(new Dimension(220, 20));
	    
	    try{
	    	 
	    	  MaskFormatter telMask = new MaskFormatter("### €");
	    	  prixT = new JFormattedTextField(telMask);
	    	}catch(ParseException e){e.printStackTrace();}
	    ((JFormattedTextField) prixT).setFocusLostBehavior( JFormattedTextField.PERSIST ); 
	    
	    libelleT.setPreferredSize(new Dimension(200, 30));
	    prixT.setPreferredSize(new Dimension(200, 30));
	    
	    AdherentPan.add(libelle);
	    AdherentPan.add(libelleT);
	    AdherentPan.add(prix);
	    AdherentPan.add(prixT);
	    
	    
	    b.setPreferredSize(dim);
	    raz.setPreferredSize(dim);
	    b.addActionListener(new BoutonListener());
	    raz.addActionListener(new BoutonListenerRaz());
	
	    boutonPan = new JPanel();
	    boutonPan.add(b);
	    boutonPan.add(raz);
	
	    contentPan.setLayout(new BorderLayout());
	
	    contentPan.add(AdherentPan, BorderLayout.CENTER);
	    contentPan.add(boutonPan, BorderLayout.SOUTH);
	    contentPan.setBackground(Color.WHITE);
	    AdherentPan.setBackground(Color.WHITE);
	    boutonPan.setBackground(Color.WHITE);

	    this.add(contentPan);
	    
	}
	
	class BoutonListenerRaz implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			libelleT.setText("");
			prixT.setText("");
	    }
	}
	
	class BoutonListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	System.out.println("Début BoutonListener");
	    	
	    	if(!libelleT.getText().isEmpty() && !prixT.getText().isEmpty()) {	
	    		System.out.println("Si zones de text non vides: ");
	    		boolean resutlVerif;

	    		
	    		String[] splitArray = null;
	    		 String nomPrenom= prixT.getText();
	    		 splitArray = nomPrenom.split(" ");
				    int tarif = Integer.parseInt(splitArray[0]); 

	    		
		    	TypeAdhesion type = new TypeAdhesion(libelleT.getText(),tarif);
		    	TypeAdhesionDB typeDB = new TypeAdhesionDB();
		    	resutlVerif = typeDB.saveTypeAdhesion(type);
		    	
		    	if (resutlVerif) {
		    		JOptionPane jop = new JOptionPane();

		    		jop.showMessageDialog(null, "Categorie existe déjà ", "Erreur", JOptionPane.ERROR_MESSAGE);
		    	}else {
		    		JOptionPane jop1 = new JOptionPane();

		    		jop1.showMessageDialog(null, "La catégorie "+ type.getLibelle()+" est enregistrée avec succès ", "Information", JOptionPane.INFORMATION_MESSAGE);
		    	
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
	    	}else {
	    		JOptionPane jop3 = new JOptionPane();

	    		jop3.showMessageDialog(null, "Champ(s) Vide(s) ", "Erreur", JOptionPane.ERROR_MESSAGE);	
	    	}
	    }
	}	

	    

}
