package zaposleniPaket;

import enumeracije.Pol;
import enumeracije.Sluzba;
import enumeracije.Uloga;
import osnovniPaket.Zaposleni;

public class MedicinskaSestra extends Zaposleni {
	public MedicinskaSestra() {
		super();
	}
	
	public MedicinskaSestra(String ime, String prezime, String jmbg, Pol pol,
			String adresa, String brojTel, String korisnickoIme, String lozinka,
			Uloga uloga, int plata, Sluzba sluzba) {
		super(ime, prezime, jmbg, pol, adresa, brojTel, 
				korisnickoIme, lozinka, uloga, plata, sluzba);
	}
	
	public MedicinskaSestra(MedicinskaSestra original) {
		super(original);
	}

	@Override
	public String toString() {
		return "MedicinskaSestra plata: " + plata + 
				" sluzba: " + sluzba + 
				" ime: " + ime + 
				" prezime: " + prezime +
				" jmbg: " + jmbg + 
				" pol: " + pol + 
				" adresa: " + adresa + 
				" brojTel: " + brojTel + 
				" korisnickoIme: " + korisnickoIme + 
				" lozinka: " + lozinka + 
				" uloga: " + uloga + " ";
	}
	
	
}
