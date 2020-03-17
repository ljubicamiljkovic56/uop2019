package main;

import glavniILogin.Login;
import knjizicaPaket.Knjizica;
import krajnjiPaket.Liste;


public class KnjizicaMain {
	public static void main(String[] args) {
		Liste liste  = new Liste();
		liste.ucitajSestru();
		liste.ucitajLekara();
		liste.ucitajPacijenta();
		liste.ucitajKnjzicu();
		liste.ucitajPregled();
		Knjizica knjizica = new Knjizica();
		Login login = new Login(liste, knjizica);
		login.setVisible(true);
		

	}
}
