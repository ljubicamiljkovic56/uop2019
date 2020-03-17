package zaposleniPaket;

import enumeracije.Pol;
import enumeracije.Sluzba;
import enumeracije.Uloga;
import osnovniPaket.Zaposleni;

public class Lekar extends Zaposleni {
	private String specijalizacija;
	
	public Lekar() {
		this.specijalizacija = "";
	}

	public Lekar(String ime, String prezime, String jmbg, Pol pol, String adresa, String brojTel, String korisnickoIme,
			String lozinka, Uloga uloga, int plata, Sluzba sluzba, String specijalizacija) {
		super(ime, prezime, jmbg, pol, adresa, brojTel, korisnickoIme, lozinka, uloga, plata, sluzba);
		this.specijalizacija = specijalizacija;
	}
	
	public Lekar(Lekar original) {
		this.specijalizacija = original.specijalizacija;
	}

	public String getSpecijalizacija() {
		return specijalizacija;
	}

	public void setSpecijalizacija(String specijalizacija) {
		this.specijalizacija = specijalizacija;
	}

	@Override
	public String toString() {
		return "Lekar specijalizacija: " + specijalizacija + 
				" plata: " + plata + 
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
