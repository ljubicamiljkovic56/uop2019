package knjizicaPaket;

import enumeracije.Kategorija;
import pacijentPaket.Pacijent;

public class Knjizica {
	private Pacijent pacijent;
	private String brojKnjizice;
	private String datumIsteka;
	private Kategorija kategorija;
	
	public Knjizica() {
		this.pacijent = null;
		this.brojKnjizice = "";
		this.datumIsteka = "";
		this.kategorija = Kategorija.druga;
	}
	
	public Knjizica(Pacijent pacijent, String brojKnjizice, String datumIsteka, Kategorija kategorija) {
		super();
		this.pacijent = pacijent;
		this.brojKnjizice = brojKnjizice;
		this.datumIsteka = datumIsteka;
		this.kategorija = kategorija;
	} 
	
	public Knjizica(Knjizica original) {
		this.pacijent = original.pacijent;
		this.brojKnjizice = original.brojKnjizice;
		this.datumIsteka = original.datumIsteka;
		this.kategorija = original.kategorija;
	}

	public String getBrojKnjizice() {
		return brojKnjizice;
	}

	public void setBrojKnjizice(String brojKnjizice) {
		this.brojKnjizice = brojKnjizice;
	}

	public String getDatumIsteka() {
		return datumIsteka;
	}

	public void setDatumIsteka(String datumIsteka) {
		this.datumIsteka = datumIsteka;
	}

	public Kategorija getKategorija() {
		return kategorija;
	}

	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}
	

	public Pacijent getPacijent() {
		return pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}

	@Override
	public String toString() {
		return "Knjizica pacijent: " + pacijent 
				+ "brojKnjizice: " + brojKnjizice + 
				" datumIsteka: " + datumIsteka + 
				" kategorija: " + kategorija
				+ " ";
	}
	
}
