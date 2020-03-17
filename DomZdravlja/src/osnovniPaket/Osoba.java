package osnovniPaket;

import enumeracije.Pol;
import enumeracije.Uloga;

public class Osoba {
	
	protected String ime;
	protected String prezime;
	protected String jmbg;
	protected Pol pol;
	protected String adresa;
	protected String brojTel;
	protected String korisnickoIme;
	protected String lozinka;
	protected Uloga uloga;
	
	public Osoba() {
		this.ime = "";
		this.prezime = "";
		this.jmbg = "";
		this.pol = Pol.Zenski;
		this.adresa = "";
		this.brojTel = "";
		this.korisnickoIme = "";
		this.lozinka = "";
		this.uloga = Uloga.medicinska_sestra;
	}
	public Osoba(String ime, String prezime, String jmbg, Pol pol, String adresa, String brojTel, String korisnickoIme,
			String lozinka, Uloga uloga) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.pol = pol;
		this.adresa = adresa;
		this.brojTel = brojTel;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.uloga = uloga;
	}
	
	public Osoba(Osoba original) {
		this.ime = original.ime;
		this.prezime = original.prezime;
		this.jmbg = original.jmbg;
		this.pol = original.pol;
		this.adresa = original.adresa;
		this.brojTel = original.brojTel;
		this.korisnickoIme = original.korisnickoIme;
		this.lozinka = original.lozinka;
		this.uloga = original.uloga;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getJmbg() {
		return jmbg;
	}
	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}
	public Pol getPol() {
		return pol;
	}
	public void setPol(Pol pol) {
		this.pol = pol;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getBrojTel() {
		return brojTel;
	}
	public void setBrojTel(String brojTel) {
		this.brojTel = brojTel;
	}
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	public Uloga getUloga() {
		return uloga;
	}
	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}
	@Override
	public String toString() {
		return "Osoba ime: " + ime +
				" prezime: " + prezime + 
				" jmbg: " + jmbg + 
				" pol: " + pol + 
				" adresa: " + adresa
				+ " brojTel: " + brojTel + 
				" korisnickoIme: " + korisnickoIme + 
				" lozinka: " + lozinka + 
				" uloga: "+ uloga + " ";
	}
	
	

}
