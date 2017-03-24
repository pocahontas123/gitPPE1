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

//H�rite de JPanel
public class AfficherCategorie extends JPanel{
	//variables membres
	private JPanel contentPan = new JPanel();
	private JPanel ListeCategories, ficheCategoriePan;
	private JLabel libelle, prix, listeCat;
	private JLabel libelleT, prixT;
	private Font police, police1;
	private JComboBox combo;
    private Dimension dim2 = new Dimension(500, 350);
    
  //Constructeur par d�faut que l'on retrouve dans la class pagePrincipale.java
	public AfficherCategorie() { 
	    this.setSize(500, 400);
	    this.setVisible(true);
	    contentPan.setPreferredSize(dim2);
		this.setBackground(Color.white);
		
	    this.initComposant();
	}
	
	//M�thode d'initialisation des composants de la page
	private void initComposant() {
		//Cr�ation de la police de caract�res
		police = new Font("Arial", Font.BOLD, 20);
	    police1 = new Font("Arial", Font.BOLD, 18);		

	    //Cr�ation et initialisation du JPanel NORTH
		ListeCategories = new JPanel();
		ListeCategories.setPreferredSize(new Dimension(220, 80));
	    TitledBorder titleBorder = BorderFactory.createTitledBorder("Liste Categories");
	    ListeCategories.setBorder(titleBorder);
	    titleBorder.setTitleFont(police);
	    
	    //Cr�ation et initialisation du JPanel CENTER
	    ficheCategoriePan = new JPanel();
	    ficheCategoriePan.setPreferredSize(new Dimension(220, 20));
	    TitledBorder titleBorder1 = BorderFactory.createTitledBorder("Fiche Cat�gorie");
	    ficheCategoriePan.setBorder(titleBorder1);
	    titleBorder1.setTitleFont(police);
	    
	    //Cr�ation et initialisation des divers JLabel gauche et droite
	    listeCat = new JLabel("Liste des Cat�gories : ");
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
		//r�cup�re les diff�rentes cat�gories qui existent dans la BDD
		typeAdhesion = typeAdhesionDB.getTypeAdhesions();
		
		combo.addItem("S�lectionner...");
	    for(int i = 0; i < typeAdhesion.size(); i++) {
	    	//Affiche les infos r�cup�r�es dans l'objet typeAdhesion dans le combo
	    	combo.addItem(((TypeAdhesion) typeAdhesion.get(i)).getLibelle());
	    }
	    
	    combo.setPreferredSize(new Dimension(200, 30));
	    //Ajout d'un listener sur le combo
	    combo.addActionListener(new BoutonListenerCombo());
	    
	    //G�re la visibilit� de base des �l�ments NORTH et CENTER
	    ListeCategories.setVisible(true);
	    ficheCategoriePan.setVisible(false);
	    
	    //Ajoute la listeCat et le combo au JPanel ListeCategorie NORTH
	    ListeCategories.add(listeCat);
	    ListeCategories.add(combo);
	    
	    //Cr�e un BorderLayout "contentPan"
	    contentPan.setLayout(new BorderLayout());
	    //Met dans ce contentPan, l'�l�ment du NORTH et du CENTER
	    contentPan.add(ListeCategories, BorderLayout.NORTH);
	    contentPan.add(ficheCategoriePan, BorderLayout.CENTER);
	    
	    //Couleur de fond
	    contentPan.setBackground(Color.WHITE);
	    ListeCategories.setBackground(Color.WHITE);
	    ficheCategoriePan.setBackground(Color.WHITE);

	    this.add(contentPan);
	}
	
	//Listener du combo
	class BoutonListenerCombo implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	//Si je n'ai pas s�lectionn� dans le combo "S�lectionner..." alors
	    	 if ((String)combo.getSelectedItem() != "S�lectionner...") {
	    		 //Rend la partie du CENTER visible
	    		 ficheCategoriePan.setVisible(true);
	    		 
	    		 TypeAdhesionDB typeDb = new TypeAdhesionDB();
	    		 TypeAdhesion type;
	    		 //R�cup�re le nom du Libelle
	    		 String nom = (String)combo.getSelectedItem();
	    		 //Avec le nom du Libelle, on r�cup�re les infos sur cette cat�gorie
	    		 type = typeDb.getTypeAdhesion(nom);
			   
	    		 //On remplit les diff�rents JLabel de droite avec les informations de contenues dans l'objet "type"
				 libelleT.setText(type.getLibelle());
				 String tarif = String.valueOf(type.getTarif());
				 prixT.setText(tarif+" �");
				 
				 //Met un bordure avec le nom et pr�nom de l'adh�rent s�lectionn�
				 TitledBorder bf = BorderFactory.createTitledBorder("Fiche categorie "+ libelleT.getText());
				 ficheCategoriePan.setBorder(bf);
				 bf.setTitleFont(police);
	    
	    	 }else {
	    		 //Par contre si j'ai bien s�lectionn� "S�lectionner..."
	    		 ficheCategoriePan.setVisible(false);
	    		 
	    		 //Affichage d'une pop-up "Veuillez s�lectionner une categorie" du genre "Information"
	    		 JOptionPane jop1 = new JOptionPane();
		    	 jop1.showMessageDialog(null, "Veuillez s�lectionner une categorie ", "Information", JOptionPane.INFORMATION_MESSAGE);	
	    	 } 	 
	    }
	}
}
