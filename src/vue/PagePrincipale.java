package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;



public class PagePrincipale extends JFrame {

  
 
	
	private URL imageM2l;
	private URL imageTir;
	private ImageIcon iconM2l;
	private ImageIcon iconTir;
	private JPanel image= new JPanel();

	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu test1 = new JMenu("Gestion");
	private JMenu test1_2 = new JMenu("Gestion adhérent");
	private JMenu test1_3 = new JMenu("Gestion catégorie");
	
	private JMenuItem item1 = new JMenuItem("Quitter");
	
	private JRadioButtonMenuItem jrmi7 = new JRadioButtonMenuItem("Afficher");

	private JRadioButtonMenuItem jrmi1 = new JRadioButtonMenuItem("Ajouter");
	private JRadioButtonMenuItem jrmi2 = new JRadioButtonMenuItem("Modifier");
	private JRadioButtonMenuItem jrmi3 = new JRadioButtonMenuItem("Supprimer");
	
	private JRadioButtonMenuItem jrmi8 = new JRadioButtonMenuItem("Afficher");

	private JRadioButtonMenuItem jrmi4 = new JRadioButtonMenuItem("Ajouter");
	private JRadioButtonMenuItem jrmi5 = new JRadioButtonMenuItem("Modifier");
	private JRadioButtonMenuItem jrmi6 = new JRadioButtonMenuItem("Supprimer");

	
	private JPanel contentPane = new JPanel();


	
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public PagePrincipale() {
		//titre de la Frame
		this.setTitle("Application Gestion Adherents");
		//taille de la frame
		this.setSize(600, 500);
		//fermeture de l'application au clic sur le bouton-croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Centre la frame
		this.setLocationRelativeTo(null);
		//Empêche le changement de taille de la fenêtre
		this.setResizable(false);
		//la fenêtre reste par dessus tout
		//this.setAlwaysOnTop(true);

		 this.contentPane.setLayout(new BorderLayout());
		   
		   
		   imageM2l = PagePrincipale.class.getResource("/MDL.jpg");
			  imageTir = PagePrincipale.class.getResource("/s-l225.png");
			   iconM2l = new ImageIcon(imageM2l);
			     iconTir = new ImageIcon(imageTir);
		   image.add(new JLabel(iconM2l));
		   image.add(new JLabel(iconTir));
		    
		   
			image.setVisible(true);
		   
			contentPane.add(image, BorderLayout.CENTER);

			
			



		    this.setContentPane(contentPane);

		    this.initComposant();
		    
		    this.setVisible(true);
	}
	
	
	
	private void initComposant(){
		//Crée mes boutons
		ButtonGroup bg = new ButtonGroup();
		bg.add(jrmi1);
		bg.add(jrmi2);
		bg.add(jrmi3);
		bg.add(jrmi4);
		bg.add(jrmi5);
		bg.add(jrmi6);
		bg.add(jrmi7);
		bg.add(jrmi8);
		//Rajoute mes boutons à mon sous-menu 1
		this.test1_2.add(jrmi7);

		this.test1_2.add(jrmi1);
		this.test1_2.add(jrmi2);
		this.test1_2.add(jrmi3);
		this.test1_2.addSeparator();
		
		//Rajoute mes boutons à mon sous-menu 2
		this.test1_3.add(jrmi8);

		this.test1_3.add(jrmi4);
		this.test1_3.add(jrmi5);
		this.test1_3.add(jrmi6);
		this.test1_3.addSeparator();
		
		//Rajoute à mon menu mes deux sous-menus
		this.test1.add(this.test1_2);
		this.test1.add(this.test1_3);
		this.test1.addSeparator();

		//Rajoute à mon menu "Quitter"
		this.test1.add(item1);
		this.menuBar.add(test1);
		this.setJMenuBar(menuBar);
		
		jrmi1.addActionListener(new jrmListener());
		jrmi2.addActionListener(new jrmListener());
		jrmi3.addActionListener(new jrmListener());
		
		jrmi4.addActionListener(new jrmListener());
		jrmi5.addActionListener(new jrmListener());
		jrmi6.addActionListener(new jrmListener());
		
		item1.addActionListener(new itemListener());
		
		
		
		
	  }

	class itemListener implements ActionListener {
	
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == item1) {
				System.out.println("Choix 3 : Quitter l'application");
				System.exit(0);
			}
		}
	}
	
	class jrmListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {		

			if(e.getSource() == jrmi1) {
				System.out.println("Choix 1 : Ajouter");
				
				 AjoutAdherent ajouAd = new AjoutAdherent();
			    	contentPane.removeAll();
			    	contentPane.add(ajouAd, BorderLayout.CENTER);

			    	contentPane.revalidate();
		    		contentPane.repaint();
				
				
			}else if (e.getSource() == jrmi2) {
				System.out.println("Choix 1 : Modifier");
				
				
			}else if (e.getSource() == jrmi3) {	
				System.out.println("Choix 1 : Supprimer");
				
			}else if (e.getSource() == jrmi4) {	
				System.out.println("Choix 2 : Ajouter");
			
			}else if (e.getSource() == jrmi5) {	
				System.out.println("Choix 2 : Modifier");
			
			}else if (e.getSource() == jrmi6) {	
				System.out.println("Choix 2 : Supprimer");
			}	
		}
	}


	
}
