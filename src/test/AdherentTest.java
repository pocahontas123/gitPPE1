package test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import model.classe.Adherent;
import model.classe.TypeAdhesion;

import org.junit.Test;

//class de text pour Adherent.java
public class AdherentTest {
		
		//méthode de test du calcul de l'age
		@Test
		public void testCalculAge() {
			String date = "2000-03-03";
			SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
			
			java.util.Date dateStr;
			try {
				dateStr = format.parse(date);

				TypeAdhesion t = new TypeAdhesion(1, "loisir", 100);
				java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());

				Adherent ad = new Adherent("Jean","Dupont","98000","Paris",dateDB,t,"06.98.34.23.23","jean@yahoo.com",false);
				
				if(ad.calculAge()!=17) {
					System.out.println(ad.calculAge());
					fail("Date antérieure");
				}
				
			 } catch (ParseException e) {
				 // TODO Auto-generated catch block
				 e.printStackTrace();
			 }
		}

}
