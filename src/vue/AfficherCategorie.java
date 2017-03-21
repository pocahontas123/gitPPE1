package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import model.classe.Adherent;
import model.classe.TypeAdhesion;
import model.dataaccesslayer.AdherentDB;
import model.dataaccesslayer.TypeAdhesionDB;
import vue.AfficherAdherent.BoutonListenerCombo;

public class AfficherCategorie extends JPanel{
	private JPanel contentPan = new JPanel();
	private JPanel ListeCategories, ficheCategoriePan;
	private JLabel libelle, prix, listeCat;
	private JLabel libelleT, prixT;
	private Font police, police1;
	private JComboBox combo;
    private Dimension dim2 = new Dimension(500, 350);
    
	public AfficherCategorie() { 
	    this.setSize(500, 400);
	    this.setVisible(true);
	    contentPan.setPreferredSize(dim2);
		this.setBackground(Color.white);

	    this.initComposant();
	}
	
	private void initComposant() {
		police = new Font("Arial", Font.BOLD, 20);
	     police1 = new Font("Arial", Font.BOLD, 18);		

		ListeCategories = new JPanel();
		ListeCategories.setPreferredSize(new Dimension(220, 80));
	    TitledBorder titleBorder = BorderFactory.createTitledBorder("Liste Categories");
	    ListeCategories.setBorder(titleBorder);
	    titleBorder.setTitleFont(police);
	    
	    ficheCategoriePan = new JPanel();
	    ficheCategoriePan.setPreferredSize(new Dimension(220, 20));
	    TitledBorder titleBorder1 = BorderFactory.createTitledBorder("Fiche Catégorie");
	    ficheCategoriePan.setBorder(titleBorder1);
	    titleBorder1.setTitleFont(police);
	    
	    listeCat = new JLabel("Liste des Catégories : ");
	    listeCat.setFont(police1);
	    listeCat.setHorizontalAlignment(JLabel.RIGHT);
	    listeCat.setPreferredSize(new Dimension(220, 20));
	    
	    
	    
	    libelle = new JLabel("Libelle : ");
	    libelle.setFont(police1);
	    libelle.setHorizontalAlignment(JLabel.RIGHT);
	    libelle.setPreferredSize(new Dimension(220, 20));
	    
	    libelleT = new JLabel("");
	    libelleT.setFont(police1);
	    libelleT.setHorizontalAlignment(JLabel.LEFT);
	    libelleT.setPreferredSize(new Dimension(220, 20));
	    
	    prix = new JLabel("Prix : ");
	    prix.setFont(police1);
	    prix.setHorizontalAlignment(JLabel.RIGHT);
	    prix.setPreferredSize(new Dimension(220, 20));
	    
	    prixT = new JLabel("");
	    prixT.setFont(police1);
	    prixT.setHorizontalAlignment(JLabel.LEFT);
	    prixT.setPreferredSize(new Dimension(220, 20));
	    
	    ficheCategoriePan.add(libelle);
	    ficheCategoriePan.add(libelleT);
	    
	    ficheCategoriePan.add(prix);
	    ficheCategoriePan.add(prixT);
	    
	    combo = new JComboBox();
	    combo.setPreferredSize(new Dimension(100, 20));
	    
	    TypeAdhesionDB typeAdhesionDB = new TypeAdhesionDB();
		ArrayList typeAdhesion = new ArrayList();
		
		typeAdhesion = typeAdhesionDB.getTypeAdhesions();
		
		combo.addItem("Sélectionner...");
	    for(int i = 0; i < typeAdhesion.size(); i++) {
	    	combo.addItem(((TypeAdhesion) typeAdhesion.get(i)).getLibelle());
	    }
	    combo.setPreferredSize(new Dimension(200, 30));
	    
	    combo.addActionListener(new BoutonListenerCombo());
	    
	    ListeCategories.setVisible(true);
	    ficheCategoriePan.setVisible(false);
	    ListeCategories.add(listeCat);

	    ListeCategories.add(combo);
	    contentPan.setLayout(new BorderLayout());
	    contentPan.add(ListeCategories, BorderLayout.NORTH);
	    contentPan.add(ficheCategoriePan, BorderLayout.CENTER);
	    contentPan.setBackground(Color.WHITE);
	    ListeCategories.setBackground(Color.WHITE);
	    ficheCategoriePan.setBackground(Color.WHITE);

	    this.add(contentPan);
	}
	
	class BoutonListenerCombo implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	 if ((String)combo.getSelectedItem()!="Sélectionner...") {
	    		 ficheCategoriePan.setVisible(true);
	    		 TypeAdhesionDB typeDb = new TypeAdhesionDB();
			   
	    		 TypeAdhesion type;
	    		 String nom = (String)combo.getSelectedItem();

	    		 type = typeDb.getTypeAdhesion(nom);
			   
				 libelleT.setText(type.getLibelle());
				 String tarif = String.valueOf(type.getTarif());
				 prixT.setText(tarif+" €");
				 
			   
				 TitledBorder bf = BorderFactory.createTitledBorder("Fiche categorie "+ libelleT.getText());
				 ficheCategoriePan.setBorder(bf);
				 bf.setTitleFont(police);
	    
	    	 }else {
	    		 ficheCategoriePan.setVisible(false);

	    		 JOptionPane jop1 = new JOptionPane();
		    	 jop1.showMessageDialog(null, "Veuillez sélectionner une categorie ", "Information", JOptionPane.INFORMATION_MESSAGE);	
	    	 } 	 
	    }
	}
}
