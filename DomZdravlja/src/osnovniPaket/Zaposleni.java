package osnovniPaket;

import enumeracije.Pol;
import enumeracije.Sluzba;
import enumeracije.Uloga;

public class Zaposleni extends Osoba {
	protected int plata;
	protected Sluzba sluzba;
	
	public Zaposleni() {
		this.plata = 0;
		this.sluzba = Sluzba.Služba_opste_medicine;
	}
	
	public Zaposleni(String ime, String prezime, String jmbg, Pol pol, String adresa, String brojTel,
			String korisnickoIme, String lozinka, Uloga uloga, int plata, Sluzba sluzba) {
		super(ime, prezime, jmbg, pol, adresa, brojTel, korisnickoIme, lozinka, uloga);
		this.plata = plata;
		this.sluzba = sluzba;
	}
	
	public Zaposleni(Zaposleni original) {
		this.plata = original.plata;
		this.sluzba = original.sluzba;
	}

	public int getPlata() {
		return plata;
	}

	public void setPlata(int plata) {
		this.plata = plata;
	}

	public Sluzba getSluzba() {
		return sluzba;
	}

	public void setSluzba(Sluzba sluzba) {
		this.sluzba = sluzba;
	}

	@Override
	public String toString() {
		return "Zaposleni plata: " + plata + 
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
