import java.awt.EventQueue;
import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import Classes.Adherent;
import Classes.TypeAdhesion;
import DataAcessLayer.AdherentDB;
import DataAcessLayer.TypeAdhesionDB;
import IHM.AjoutAdherent;

public class M2L  {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjoutAdherent frame = new AjoutAdherent();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
