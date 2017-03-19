package vue;

import java.awt.BorderLayout;
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
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import model.classe.Adherent;
import model.classe.TypeAdhesion;
import model.dataaccesslayer.AdherentDB;
import model.dataaccesslayer.TypeAdhesionDB;
import vue.ModifierAdherent.BoutonListener;
import vue.ModifierAdherent.BoutonListenerCombo;
import vue.ModifierAdherent.BoutonListenerRaz;

public class ModifierCategorie extends JPanel{
	private JPanel contentPan = new JPanel();
	private JPanel boutonPan;
	private JPanel ListeCategories;
	private JButton raz = new JButton ("RAZ");
	private Font police, police1;

	private JButton b = new JButton ("Envoyer");
	private Dimension dim = new Dimension(80, 50);
	private Dimension dim2 = new Dimension(500, 300);

	private JTextField libelleT;
	private JTextField prixT;
	private JComboBox combo, combo2;
	  
	private JPanel AdherentPan ;  
	private JLabel libelle, prix, typeAdhesion, listeCat;
	
	public ModifierCategorie() {
		this.setSize(500, 400);
		this.setVisible(true);
    	contentPan.setPreferredSize(dim2);

	    this.initComposant();	
	}
	
	private void initComposant() {
	     police = new Font("Arial", Font.BOLD, 20);
	     police1 = new Font("Arial", Font.BOLD, 18);		

	    ListeCategories = new JPanel();
	    ListeCategories.setPreferredSize(new Dimension(220, 80));
	    TitledBorder titleBorder = BorderFactory.createTitledBorder("Liste catégories");
	    ListeCategories.setBorder(titleBorder);
	    titleBorder.setTitleFont(police);
	    
	    listeCat = new JLabel("Liste des Catégories : ");
	    listeCat.setFont(police1);
	    listeCat.setHorizontalAlignment(JLabel.RIGHT);
	    listeCat.setPreferredSize(new Dimension(220, 20));
	    
	    combo = new JComboBox();
	    combo.setPreferredSize(new Dimension(100, 20));
	    TypeAdhesionDB typeDB = new TypeAdhesionDB();
		ArrayList type = new ArrayList();
		
		type = typeDB.getTypeAdhesions();
		
		combo.addItem("Sélectionner...");
	    for(int i = 0; i < type.size(); i++) {
	    	combo.addItem(((TypeAdhesion) type.get(i)).getLibelle());
	    }
	    combo.setPreferredSize(new Dimension(200, 30));
	    combo.addActionListener(new BoutonListenerCombo());
	
	    AdherentPan = new JPanel();
	    AdherentPan.setPreferredSize(new Dimension(165, 225));

	    TitledBorder titleBorder2 = BorderFactory.createTitledBorder("Modifier Cathégorie");
	    AdherentPan.setBorder(titleBorder2);
	    titleBorder2.setTitleFont(police);
	    libelle = new JLabel("Libelle : ");
	    libelle.setFont(police1);
	    libelle.setHorizontalAlignment(JLabel.RIGHT);
	    libelle.setPreferredSize(new Dimension(220, 20));
	    
	    libelleT = new JTextField();
	    
	    prix = new JLabel("Prix : ");
	    prix.setFont(police1);
	    prix.setHorizontalAlignment(JLabel.RIGHT);
	    prix.setPreferredSize(new Dimension(220, 20));
	    
	    prixT = new JTextField();
	    

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
	    boutonPan.setPreferredSize(new Dimension(50, 60));

	    ListeCategories.setVisible(true);
	    AdherentPan.setVisible(false);
	    boutonPan.setVisible(false);
	    
	    contentPan.setLayout(new BorderLayout());
	    ListeCategories.add(listeCat);

	    ListeCategories.add(combo);
	    contentPan.add(ListeCategories, BorderLayout.NORTH);
	    contentPan.add(AdherentPan, BorderLayout.CENTER);
	    contentPan.add(boutonPan, BorderLayout.SOUTH);
	    this.add(contentPan);   
	}
	
	class BoutonListenerCombo implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if ((String)combo.getSelectedItem()!="Sélectionner...") {
				AdherentPan.setVisible(true);
	    		boutonPan.setVisible(true);
	    		
	    		TypeAdhesionDB typeDb = new TypeAdhesionDB();
	    		TypeAdhesion type;

	    		String nom = (String)combo.getSelectedItem();

	    		type = typeDb.getTypeAdhesion(nom);
	    		
	    		
				libelleT.setText(type.getLibelle());
				String tarif = String.valueOf(type.getTarif());
				prixT.setText(tarif+" euros");
				TitledBorder bf = BorderFactory.createTitledBorder("Modifier Categorie "+ libelleT.getText());
				AdherentPan.setBorder(bf);
				bf.setTitleFont(police);
				System.out.println(type.getLibelle());

			}else {
				AdherentPan.setVisible(false);
				boutonPan.setVisible(false);
				
	    		JOptionPane jop1 = new JOptionPane();
		    	jop1.showMessageDialog(null, "Veuillez sélectionner une catégorie ", "Information", JOptionPane.INFORMATION_MESSAGE);	
			}	
		}
	}
	
	
	class BoutonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if(!libelleT.getText().isEmpty() && !prixT.getText().isEmpty()) {
				System.out.println("Si zones de text non vides: ");
				boolean exist;
				
				String[] splitArray = null;
	    		 String nomPrenom= prixT.getText();
	    		 splitArray = nomPrenom.split(" ");
				
				
				
				TypeAdhesionDB  typeDB = new TypeAdhesionDB();    
			    TypeAdhesion type = typeDB.getTypeAdhesion((String)combo.getSelectedItem());
				
			    
			    type.setLibelle(libelleT.getText());
				System.out.println(prixT.getText());

			    int tarif = Integer.parseInt(splitArray[0]); 

			    type.setTarif(tarif);
			    
			    exist = typeDB.updateCategorie(type);
			    
				if(exist) {
			    	JOptionPane jop1 = new JOptionPane();
		    		
			    	jop1.showMessageDialog(null, "La catégorie "+ type.getLibelle()+" a été modifié avec succès ", "Information", JOptionPane.INFORMATION_MESSAGE);
			    	
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
			}else {
		    	 JOptionPane jop3 = new JOptionPane();

		    	 jop3.showMessageDialog(null, "Champ(s) Vide(s) ", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}		
	}
	
	class BoutonListenerRaz implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			libelleT.setText("");
			prixT.setText("");
		}		
	}




		


}
