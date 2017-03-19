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
import javax.swing.border.TitledBorder;

import model.classe.Adherent;
import model.classe.TypeAdhesion;
import model.dataaccesslayer.AdherentDB;
import model.dataaccesslayer.TypeAdhesionDB;

public class SupprimerCategorie extends JPanel{
	private JPanel contentPan = new JPanel();
	private JPanel ListeCategorie;
	private JPanel FicheCategoriePan;
	private JPanel boutonPan;
	private Font police, police1;
	private JLabel Libelle, Tarif, LibelleT, TarifT, listeCat;
	
	
	private JComboBox combo;
	
	private JButton b = new JButton ("Supprimer");
	private Dimension dim = new Dimension(100, 50);
	private Dimension dim2 = new Dimension(500, 300);
	
	
	public SupprimerCategorie() {
		this.setSize(500, 400);
		this.setVisible(true);
    	contentPan.setPreferredSize(dim2);

	    this.initComposant();
	}
	
	private void initComposant(){
		 police = new Font("Arial", Font.BOLD, 20);
	     police1 = new Font("Arial", Font.BOLD, 18);		

		ListeCategorie = new JPanel();
		ListeCategorie.setPreferredSize(new Dimension(80, 75));
	    TitledBorder titleBorder = BorderFactory.createTitledBorder("Liste Catégories");
	    ListeCategorie.setBorder(titleBorder);
	    titleBorder.setTitleFont(police);
	    
	    b.setPreferredSize(dim);
	    b.addActionListener(new BoutonListenerSupprimer());
	    boutonPan = new JPanel();
	    boutonPan.add(b);
	    
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
	    
	    FicheCategoriePan = new JPanel();
	    FicheCategoriePan.setPreferredSize(new Dimension(300, 20));
	    
	    listeCat = new JLabel("Liste des Catégories : ");
	    listeCat.setFont(police1);
	    listeCat.setHorizontalAlignment(JLabel.RIGHT);
	    listeCat.setPreferredSize(new Dimension(220, 20));
		   
	    Libelle = new JLabel("Libelle : ");
	    Libelle.setFont(police1);
	    Libelle.setHorizontalAlignment(JLabel.RIGHT);
	    Libelle.setPreferredSize(new Dimension(220, 20));
	    
	    LibelleT = new JLabel("");
	    LibelleT.setFont(police1);
	    LibelleT.setHorizontalAlignment(JLabel.LEFT);
	    LibelleT.setPreferredSize(new Dimension(220, 20));
	    
	    Tarif = new JLabel("Tarif : ");
	    Tarif.setFont(police1);
	    Tarif.setHorizontalAlignment(JLabel.RIGHT);
	    Tarif.setPreferredSize(new Dimension(220, 20));
	    
	    TarifT = new JLabel("");
	    TarifT.setFont(police1);
	    TarifT.setHorizontalAlignment(JLabel.LEFT);
	    TarifT.setPreferredSize(new Dimension(220, 20));
	    
	    FicheCategoriePan.add(Libelle);
	    FicheCategoriePan.add(LibelleT);
	    FicheCategoriePan.add(Tarif);
	    FicheCategoriePan.add(TarifT);
	    
	    ListeCategorie.setVisible(true);
	    FicheCategoriePan.setVisible(false);
	    boutonPan.setVisible(false);
	    ListeCategorie.add(listeCat);

	    ListeCategorie.add(combo);
	    contentPan.setLayout(new BorderLayout());
	    contentPan.add(ListeCategorie, BorderLayout.NORTH);
	    contentPan.add(FicheCategoriePan, BorderLayout.CENTER);
	    contentPan.add(boutonPan, BorderLayout.SOUTH);
	    
	    this.add(contentPan);
	}
	
	class BoutonListenerCombo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if ((String)combo.getSelectedItem()!="Sélectionner...") {	
				
			    FicheCategoriePan.setVisible(true);
			    boutonPan.setVisible(true);
				
				TypeAdhesionDB typeDB = new TypeAdhesionDB();
			    TypeAdhesion type;
				String nom = (String)combo.getSelectedItem();
				type = typeDB.getTypeAdhesion(nom);
	    	
				LibelleT.setText(type.getLibelle());	
				TarifT.setText(Integer.toString(type.getTarif()));
				
				TitledBorder bf = BorderFactory.createTitledBorder("Fiche Categorie "+ LibelleT.getText());
				FicheCategoriePan.setBorder(bf);
				bf.setTitleFont(police);
				
			}else {
			    FicheCategoriePan.setVisible(false);
			    boutonPan.setVisible(false);
				
	    		JOptionPane jop2 = new JOptionPane();
		    	jop2.showMessageDialog(null, "Veuillez sélectionner une categorie ", "Information", JOptionPane.INFORMATION_MESSAGE);	
			}

		}
	}
	class BoutonListenerSupprimer implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if ((String)combo.getSelectedItem() != "Sélectionner...") {
				System.out.println("Bouton listener bouton supprimer");	
				
				boolean resutlVerif;
				TypeAdhesionDB typeDB = new TypeAdhesionDB();
			   
			    TypeAdhesion type;
				String nom = (String)combo.getSelectedItem();
				type = typeDB.getTypeAdhesion(nom);
			
			    TypeAdhesionDB typeDB2 = new TypeAdhesionDB();
		    	resutlVerif = typeDB2.supprimerTypeAdhesion(type);
	    	
		    	if (!resutlVerif) {
		    		JOptionPane jop = new JOptionPane();
		    		jop.showMessageDialog(null, "Catégorie pas supprimée ", "Erreur", JOptionPane.ERROR_MESSAGE);
		    		
		    	}else {
		    		JOptionPane jop1 = new JOptionPane();
		    		jop1.showMessageDialog(null, "La catégorie "+ type.getLibelle()+" a été supprimé avec succès ", "Information", JOptionPane.INFORMATION_MESSAGE);
		    		
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
			}	
		}
	}
	
	
	
}
