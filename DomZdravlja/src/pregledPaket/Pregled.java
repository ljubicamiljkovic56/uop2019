package pregledPaket;

import java.util.Date;

import enumeracije.Status;
import pacijentPaket.Pacijent;
import zaposleniPaket.Lekar;

public class Pregled {
	protected Pacijent pacijent;
	protected Lekar lekar;
	protected Date termin;
	protected String soba;
	protected String opis;
	protected Status status;
	
	public Pregled() {
		this.pacijent = null;
		this.lekar = null;
		this.termin = null;
		this.soba = "";
		this.opis = "";
		this.status = Status.završen;
	}
	
	public Pregled(Pacijent pacijent, Lekar lekar, Date termin, String soba, String opis, Status status) {
		super();
		this.pacijent = pacijent;
		this.lekar = lekar;
		this.termin = termin;
		this.soba = soba;
		this.opis = opis;
		this.status = status;
	}
	
	public Pregled(Pregled original) {
		this.pacijent = original.pacijent;
		this.lekar = original.lekar;
		this.termin = original.termin;
		this.soba = original.soba;
		this.opis = original.opis;
		this.status = original.status;
		
	}

	public Pacijent getPacijent() {
		return pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}

	public Lekar getLekar() {
		return lekar;
	}

	public void setLekar(Lekar lekar) {
		this.lekar = lekar;
	}

	public Date getTermin() {
		return termin;
	}

	public void setTermin(Date termin) {
		this.termin = termin;
	}
	
	

	public String getSoba() {
		return soba;
	}

	public void setSoba(String soba) {
		this.soba = soba;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Pregled pacijent: " + pacijent + 
				" lekar: " + lekar + 
				" termin: " + termin + 
				"soba: " + soba + 
				" opis: " + opis
				+ " status: " + status + " ";
	}
	
	
	

}
