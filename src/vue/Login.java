package vue;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import controller.M2L;
import model.dataaccesslayer.AdminDB;
import model.dataaccesslayer.ConnexionDB;

//hérite de JPanel
public class Login extends JPanel{
	//variables membres
	private JPanel contentPan = new JPanel();
	private JPanel LoginPan = new JPanel();
	private JLabel userLabel, passwordLabel;
	private JButton loginButton;
	private JTextField userText, passwordText;
	private Dimension dim = new Dimension(150, 50);
	private Dimension dim2 = new Dimension(450, 200);

	private Font police,police1;
	public static boolean valeur;
	
	//constructeur par défaut
	public Login() { 
	    this.setSize(500, 200);
	    this.setVisible(true);
	    contentPan.setPreferredSize(dim2);
	  
	    //Chargement des composants de la page avec la méthode initComposant()
	    this.initComposant();
	}
	
	//Création et initialisation des composants de la page
	public void initComposant() {
		//Création d'une police de caractères
		police = new Font("Arial", Font.BOLD, 20);
		police1 = new Font("Arial", Font.BOLD, 14);
		
		//taille du JPanel LoginPan
		LoginPan.setPreferredSize(dim2);
		//title border de LoginPan
	    TitledBorder titleBorder = BorderFactory.createTitledBorder("Authentification");
	    LoginPan.setBorder(titleBorder);
	    titleBorder.setTitleFont(police);
	    
	    //On crée deux JLabel login et mdp et les deux JTextField correspondant
	    //On les ajoute aux JPanel LoginPan avec "add"
		userLabel = new JLabel("Login : ");
		userLabel.setFont(police);
		userLabel.setHorizontalAlignment(JLabel.RIGHT);
		userLabel.setPreferredSize(new Dimension(220, 20));
		LoginPan.add(userLabel);

		userText = new JTextField();
		userText.setPreferredSize(new Dimension(200, 30));
		LoginPan.add(userText);
		
		
		passwordLabel = new JLabel("Mot de passe : ");
		passwordLabel.setFont(police);
		passwordLabel.setHorizontalAlignment(JLabel.RIGHT);
		passwordLabel.setPreferredSize(new Dimension(220, 20));
		LoginPan.add(passwordLabel);

		passwordText = new JPasswordField();
		passwordText.setPreferredSize(new Dimension(200, 30));
		LoginPan.add(passwordText);

		
		loginButton = new JButton("Se Connecter");
		loginButton.setFont(police1);
		loginButton.setPreferredSize(dim);
		LoginPan.add(loginButton);
		
		//Listener sur le bouton "Connexion"
		loginButton.addActionListener(new BoutonListener());
		
		//couleur de fond
		LoginPan.setBackground(Color.WHITE);
		contentPan.setBackground(Color.WHITE);
		
		//Création d'un BorderLayout
	    contentPan.setLayout(new BorderLayout());
	    //On centre notre LoginPan
	    contentPan.add(LoginPan, BorderLayout.CENTER);
	    
	    this.add(contentPan);
	    
	    this.setBackground(Color.WHITE);
	}
	
	//Listener de mon bouton "Connexion"
	class BoutonListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	//Si mes texts ne sont pas vides
	    	if(!userText.getText().isEmpty() && !passwordText.getText().isEmpty()) {
    			System.out.println("Infos rentrées");
    			//Je récupère le nom d'utilisateur et mot de passe rentrés dans les deux JTextField
    			String utilisateur = userText.getText();
    			String motDePasse = passwordText.getText();
    			
    			AdminDB adminDB = new AdminDB();
    			//Je vérifie que la connexion est possible avec ces informations précédement cités mise en paramètres
    			valeur = adminDB.connexion(utilisateur, motDePasse);

    			//Si connexion OK
    			if(valeur == true) {
    				System.out.println("Connexion OK");
    				
    				//Pop-up CONNEXION OK
    	    		JOptionPane jop1 = new JOptionPane();
    	    		jop1.showMessageDialog(null, "Connexion acceptée", "Information", JOptionPane.INFORMATION_MESSAGE);	
    	    		
    	    		//On supprime tout
		    		contentPan.removeAll();
		    		contentPan.revalidate();
		    	 	contentPan.repaint();
		    		//Login.this.pagePrincipale.setPreferredSise
		    	 	
		    	 	//On rend le menu visible car on est officiellement connecté et autorisé à le voir
		    	 	PagePrincipale.setVisibeMenu();
		    	 	//On change la taille grâce à une variable/approche static
		    	 	M2L.frame.setSize(600, 600);
		    	 	M2L.frame.setLocationRelativeTo(null);
		    	 	
					//Empêche le changement de taille de la fenêtre
		    	 	M2L.frame.setResizable(false);
		    	 	
		    	 	//Gestion de l'image de fond
		    	 	URL imageM2l = PagePrincipale.class.getResource("/MDL.jpg");
		    		URL  imageTir = PagePrincipale.class.getResource("/s-l225.png");
		    		ImageIcon  iconM2l = new ImageIcon(imageM2l);
		    		ImageIcon iconTir = new ImageIcon(imageTir);
		    		JPanel image = new JPanel();

		    		image.add(new JLabel(iconM2l));
		    		image.add(new JLabel(iconTir));
		    		image.setBackground(Color.WHITE);

			        image.setVisible(true);
			    	Dimension dim2 = new Dimension(500, 500);
			    	
			    	contentPan.setPreferredSize(dim2); 
			    	//Met mon image au centre du BorderLayout
			        contentPan.add(image, BorderLayout.CENTER);	
    	    		
    			}else {
    				//Si connexion pas ok...
    	    		System.out.println("Connexion PAS OK");
    	    		JOptionPane jop1 = new JOptionPane();
    	    		//pop-up "Mauvais utilisateur et/ou mot de passe"
    	    		jop1.showMessageDialog(null, "Mauvais utilisateur et/ou mot de passe", "Erreur", JOptionPane.ERROR_MESSAGE);		
    			}
    			
	    	}else {
	    		//Si je n'ai rien rentré ou uniquement partiellement
	    		//pop-up message ERROR "Champ(s) Vide(s)
	    		System.out.println("utilisateur et/ou mot de passe vide");
	    		JOptionPane jop3 = new JOptionPane();
	    		
	    		jop3.showMessageDialog(null, "Champ(s) Vide(s)", "Erreur", JOptionPane.ERROR_MESSAGE);	
	    	}
	    }
	}
	
	
}
