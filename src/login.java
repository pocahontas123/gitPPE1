import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import Classes.Adherent;
import Classes.TypeAdhesion;
import DataAcessLayer.AdherentDB;
import DataAcessLayer.TypeAdhesionDB;

public class login  {
	public static void main(String args[]) throws ParseException, SQLException {

		TypeAdhesionDB typeDB = new TypeAdhesionDB();
		String date = "2000-03-03";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		java.util.Date dateStr = format.parse(date);
		java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());

		
		AdherentDB add = new AdherentDB();
		
		TypeAdhesion x = typeDB.getTypeAdhesion(1);
		System.out.println(x.getIdTypeAdhesion());
		System.out.println(x.getLibelle());
		System.out.println(x.getTarif()+"\n");
		
		x = typeDB.getTypeAdhesion(2);
		System.out.println(x.getIdTypeAdhesion());
		System.out.println(x.getLibelle());
		System.out.println(x.getTarif()+"\n");
		
		x = typeDB.getTypeAdhesion(3);
		System.out.println(x.getIdTypeAdhesion());
		System.out.println(x.getLibelle());
		System.out.println(x.getTarif()+"\n");
		
		TypeAdhesion type = new TypeAdhesion(4, "Niveau JEDI", 1000);
		typeDB.saveTypeAdhesion(type);
		x = typeDB.getTypeAdhesion(4);
		System.out.println(x.getIdTypeAdhesion());
		System.out.println(x.getLibelle());
		System.out.println(x.getTarif()+"\n");
		
		typeDB.supprimerTypeAdhesion(4);
		System.out.println("\n");
		
		System.out.println("selectAdherentS()");
		add.selectAdherents();
		
		System.out.println("selectAdherent(6)");
		add.selectAdherent(6);
		
		
		TypeAdhesion type2 = new TypeAdhesion(5, "Niveau SITH", 2000);
		typeDB.saveTypeAdhesion(type);
		typeDB.saveTypeAdhesion(type2);
	
		Adherent ad = new Adherent(2, "Churchil", "Winston", "303030", "London", dateDB,  type2);
		Adherent ad2 = new Adherent(3, "toto", "popo", "303030", "London", dateDB,  type);
		add.saveAdherent(ad);
		add.saveAdherent(ad2);
		
		ad.setNom("toto");
		ad.setPrenom("popo");
		ad.setId(20);
		
		System.out.println(typeDB.getTotalAdhesion(4));
		System.out.println(typeDB.getTotalAdhesions());
		//add.updateAdherent(ad);
		//add.supprimerAdherent(ad);
		
		
	}
}
