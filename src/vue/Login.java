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

public class Login extends JPanel{
	private JPanel contentPan = new JPanel();
	private JPanel LoginPan = new JPanel();
	private JLabel userLabel, passwordLabel;
	private JButton loginButton;
	private JTextField userText, passwordText;
	private Dimension dim = new Dimension(150, 50);
	private Dimension dim2 = new Dimension(450, 200);

	private Font police,police1;
	public static boolean valeur;
	
	public Login() { 
	    this.setSize(500, 200);
	    this.setVisible(true);

	    contentPan.setPreferredSize(dim2);
	  
	    this.initComposant();
	}
	
	public void initComposant() {
		police = new Font("Arial", Font.BOLD, 20);
		police1 = new Font("Arial", Font.BOLD, 14);

		LoginPan.setPreferredSize(dim2);
	    TitledBorder titleBorder = BorderFactory.createTitledBorder("Authentification");
	    LoginPan.setBorder(titleBorder);
	    titleBorder.setTitleFont(police);
	    
	    
	    
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
		loginButton.addActionListener(new BoutonListener());
		
		LoginPan.setBackground(Color.WHITE);
		
		contentPan.setBackground(Color.WHITE);
	    contentPan.setLayout(new BorderLayout());

	    contentPan.add(LoginPan, BorderLayout.CENTER);
	    this.add(contentPan);
	    this.setBackground(Color.WHITE);
	}
	
	class BoutonListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	
	    	if(!userText.getText().isEmpty() && !passwordText.getText().isEmpty()) {
    			System.out.println("Infos rentrées");
    			
    			String utilisateur = userText.getText();
    			String motDePasse = passwordText.getText();
    			
    			AdminDB adminDB = new AdminDB();
    			valeur = adminDB.connexion(utilisateur, motDePasse);

    			if(valeur == true) {
    			
    				
    				
    	    		System.out.println("Connexion OK");
    	    		JOptionPane jop1 = new JOptionPane();
    	    		
    	    		jop1.showMessageDialog(null, "Connexion acceptée", "Information", JOptionPane.INFORMATION_MESSAGE);	
		    		contentPan.removeAll();
		    		contentPan.revalidate();
		    	 	contentPan.repaint();
		    		//Login.this.pagePrincipale.setPreferredSise
		    	 	
		    	 	PagePrincipale.setVisibeMenu();
		    	 	M2L.frame.setSize(600, 600);
		    	 	M2L.frame.setLocationRelativeTo(null);
					//Empêche le changement de taille de la fenêtre
		    	 	M2L.frame.setResizable(false);
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
			    	  
			        contentPan.add(image, BorderLayout.CENTER);	
    	    		
    			}else {
    	    		System.out.println("Connexion PAS OK");
    	    		JOptionPane jop1 = new JOptionPane();
    	    		
    	    		jop1.showMessageDialog(null, "Mauvais utilisateur et/ou mot de passe", "Erreur", JOptionPane.ERROR_MESSAGE);		
    			}
    			
	    	}else {
	    		System.out.println("utilisateur et/ou mot de passe vide");
	    		JOptionPane jop3 = new JOptionPane();
	    		
	    		jop3.showMessageDialog(null, "Champ(s) Vide(s)", "Erreur", JOptionPane.ERROR_MESSAGE);	
	    	}
	    }
	}
}
