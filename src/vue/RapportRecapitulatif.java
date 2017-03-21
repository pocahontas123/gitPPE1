package vue;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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

import model.classe.*;
import model.dataaccesslayer.*;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RapportRecapitulatif extends JPanel {
		
	private JPanel contentPan = new JPanel();


	  private Dimension dim2 = new Dimension(500, 400);

	  
	  
	  private	JLabel som;
	  private JLabel som1;
	 
	  private 	JPanel pan1;

	  
	  private Font police ;
	  private  Font police1 ;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public RapportRecapitulatif() {
		 
		    this.setSize(600, 620);
		    this.setVisible(true);
		    contentPan.setPreferredSize(dim2);
			this.setBackground(Color.white);

		    this.initComposant();
	}
	
	
	
	private void initComposant(){
		  police = new Font("Arial", Font.BOLD, 20);
		     police1 = new Font("Arial", Font.BOLD, 16);
		AdherentDB adDb = new AdherentDB();
		ArrayList<ArrayList> k=new ArrayList();
		k=adDb.getRecap();
		int i;
	pan1=new JPanel();

	String tab[]={"Nombre d'Adhérents :","Catégorie :","Montant Total :"};
		int somme =0;
		for(ArrayList p:k)
		{
			JPanel pan= new JPanel();
			pan.setBackground(Color.WHITE);

			pan.setPreferredSize(new Dimension(300, 100));
		    TitledBorder bfL = BorderFactory.createTitledBorder("Catégorie "+p.get(1).toString());
		    pan.setBorder(bfL);
		    bfL.setTitleFont(police);
		    
		    for( i=0;i<p.size();i++)
		    {
		    	  
		    	
			    if(i!=1){
			JLabel lab = new JLabel(tab[p.indexOf(p.get(i))]);
		    lab.setFont(police1);
		    lab.setHorizontalAlignment(JLabel.RIGHT);
		    lab.setPreferredSize(new Dimension(180, 20));
			    
			    
		    JLabel lab1 = new JLabel(p.get(i).toString());
		    lab1.setFont(police1);
		    lab1.setHorizontalAlignment(JLabel.LEFT);
		    lab1.setPreferredSize(new Dimension(40, 20));
			
		    pan.add(lab);    
		    pan.add(lab1);
		    
			    if (i==2)
			    {
			    	somme+=(Integer)p.get(i);
			    }
		    	
		        }
	    	contentPan.add(pan);
		    }
		;}

		
		som = new JLabel("Somme totale :");
		som.setFont(police1);
		som.setHorizontalAlignment(JLabel.RIGHT);
		som.setPreferredSize(new Dimension(160, 20));
		    
		    
		som1 = new JLabel(Integer.toString(somme));
		som1.setFont(police1);
	    som1.setHorizontalAlignment(JLabel.LEFT);
	    som1.setPreferredSize(new Dimension(80, 20));
	    
	    pan1.setPreferredSize(new Dimension(300, 80));
	    TitledBorder bfL = BorderFactory.createTitledBorder("Somme Totale");
	    pan1.setBorder(bfL);
	    bfL.setTitleFont(police);
	   pan1.add(som);
	   pan1.add(som1);
		pan1.setBackground(Color.WHITE);


	    contentPan.add(pan1);
	    contentPan.setBackground(Color.WHITE);

	     this.add(contentPan);
	    
	    
	               
	    
	   
	   
	  
	  }
	
	
	
	
	
}


