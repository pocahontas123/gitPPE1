package test;

import static org.junit.Assert.*;
import model.classe.TypeAdhesion;

import org.junit.Test;

public class TypeAdhesionTest {

	
	@Test
		        public void TestReductionTarif()
		        {int tarif;
		            TypeAdhesion t = new TypeAdhesion(1, "loisir", 100);
		            //On applique une réduction de 20% au tarif.
		            tarif=t.reductionTarif(20);
		            //le tarif doit maintenant être égal à 80 en arrondissant.
		            if (tarif != 80)
		            {
		    		fail("tarif et pourcentage de remise positifs");
		            }
		        }
	@Test
			        public void TestReductionTarif1(){
			        	int tarif;
			        	TypeAdhesion t = new TypeAdhesion(1, "loisir", 12);
		            //On applique une réduction de 20% au tarif.
		            tarif= t.reductionTarif(0);
		            //le tarif doit maintenant être égal à 10 en arrondissant.
		           
		            
		            if (tarif != 12)
		            {
		    		fail("tarif positif et pourcentage de remise null");
		            }
			        }
		       
	@Test
		            public void TestReductionTarif2(){
		            	int tarif;
		            
		            	TypeAdhesion t = new TypeAdhesion(1, "loisir", 0);
        //On applique une réduction de 20% au tarif.
        tarif= t.reductionTarif(-10);
        //le tarif doit maintenant être égal à 10 en arrondissant.
       
        
        if (tarif != 0)
        {System.out.println(tarif);
		fail("tarif null et pourcentage de remise négatif");
        }
		            }
    
   
}
