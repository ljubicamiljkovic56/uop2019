package krajnjiPaket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import enumeracije.Kategorija;
import enumeracije.Pol;
import enumeracije.Sluzba;
import enumeracije.Status;
import enumeracije.Uloga;
import knjizicaPaket.Knjizica;
import osnovniPaket.Osoba;
import pacijentPaket.Pacijent;
import pregledPaket.Pregled;
import zaposleniPaket.Lekar;
import zaposleniPaket.MedicinskaSestra;

public class Liste {
	private ArrayList<MedicinskaSestra> sestre;
	private ArrayList<Lekar> lekari;
	private ArrayList<Pacijent> pacijenti;
	private ArrayList<Pregled> pregledi;
	private ArrayList<Knjizica> knjizice;
	
	public static SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy.");
	
	public Liste() {
		this.sestre = new ArrayList<MedicinskaSestra>();
		this.lekari = new ArrayList<Lekar>();
		this.pacijenti = new ArrayList<Pacijent>();
		this.pregledi = new ArrayList<Pregled>();
		this.knjizice = new ArrayList<Knjizica>();

	}

	public ArrayList<MedicinskaSestra> getSestre() {
		return sestre;
	}

	public void dodajMedicinskuSestru(MedicinskaSestra mSestra) {
		this.sestre.add(mSestra);
	}
	
	public void obrisiMedicinskuSestru(MedicinskaSestra mSestra) {
		this.sestre.remove(mSestra);
	}
	
	public void setSestre(ArrayList<MedicinskaSestra> sestre) {
		this.sestre = sestre;
	}

	public ArrayList<Lekar> getLekari() {
		return lekari;
	}

	public void dodajLekara(Lekar lekar) {
		this.lekari.add(lekar);
	}
	
	public void obrisiLekara(Lekar lekar) {
		this.lekari.remove(lekar);
	}
	
	public void setLekari(ArrayList<Lekar> lekari) {
		this.lekari = lekari;
	}

	public ArrayList<Pacijent> getPacijenti() {
		return pacijenti;
	}
	
	
	public void dodajPacijenta(Pacijent pacijent) {
		this.pacijenti.add(pacijent);
	}
	
	public void obrisiPacijenta(Pacijent pacijent) {
		this.pacijenti.remove(pacijent);
	}

	public void setPacijenti(ArrayList<Pacijent> pacijenti) {
		this.pacijenti = pacijenti;
	}

	public ArrayList<Pregled> getPregledi() {
		return pregledi;
	}
	
	public void dodajPregled(Pregled pregled) {
		this.pregledi.add(pregled);
	}
	public void obrisiPregled(Pregled pregled) {
		this.pregledi.remove(pregled);
	}

	public ArrayList<Knjizica> getKnjizice() {
		return knjizice;
	}
	
	public void dodajKnjizicu(Knjizica knjizica) {
		this.knjizice.add(knjizica);
	}
	public void obrisiKnjizicu(Knjizica knjizica) {
		this.knjizice.remove(knjizica);
	}

	public void setKnjizice(ArrayList<Knjizica> knjizice) {
		this.knjizice = knjizice;
	}

	public void setPregledi(ArrayList<Pregled> pregledi) {
		this.pregledi = pregledi;
	}

	public MedicinskaSestra nadjiSestru(String korisnickoIme) {
		for(MedicinskaSestra mSestra : sestre) {
			if(mSestra.getKorisnickoIme().equals(korisnickoIme)) {
				return mSestra;
			}
		}
		return null;
	}
	public Lekar nadjiLekara(String korisnickoIme) {
		for(Lekar lekar : lekari) {
			if(lekar.getKorisnickoIme().equals(korisnickoIme)) {
				return lekar;
			}
		}
		return null;
	}
	public Pacijent nadjiPacijenta(String korisnickoIme) {
		for(Pacijent pacijent : pacijenti) {
			if(pacijent.getKorisnickoIme().equals(korisnickoIme)) {
				return pacijent;
			}
		}
		return null;
	}
	
	public Pregled nadjiPregled(Pacijent pacijent) {

		for(Pregled pregled : pregledi) {
			if(pregled.getPacijent().equals(pacijent)) {
				return pregled;
			}
		}
		return null;
	}
	
	
	public Knjizica nadjiKnjizicu(String brojKnjizice) {
		for(Knjizica knjizica : knjizice) {
			if(knjizica.getBrojKnjizice().equals(brojKnjizice)) {
				return knjizica;
			}
		}
		return null;
	}
	
	public Osoba login(String korisnickoIme, String lozinka) {
		for (Lekar l : lekari) {
			if(l.getKorisnickoIme().equals(korisnickoIme) &&
					l.getLozinka().equals(lozinka)) {
				return l;
			}
		}
		
		for (Pacijent p : pacijenti) {
			if(p.getKorisnickoIme().equals(korisnickoIme) &&
					p.getLozinka().equals(lozinka)) {
				return p;
			}
		}
		
		for (MedicinskaSestra ms : sestre) {
			if(ms.getKorisnickoIme().equals(korisnickoIme) &&
					ms.getLozinka().equals(lozinka)) {
				return ms;
			}
		}
		
		return null;
	}

	public void ucitajSestru() {
		try {
			File mSestraFile = new File("src/fajlovi/msestra.txt");
			BufferedReader reader  = new BufferedReader(new FileReader(mSestraFile));
			String line = null;
			while((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				String ime = split[0];
				String prezime = split[1];
				String jmbg = split[2];
				int polInt = Integer.parseInt(split[3]);
				Pol pol = Pol.valueOf(polInt);
				String adresa = split[4];
				String brojTel = split[5];
				String korisnickoIme = split[6];
				String lozinka = split[7];
				int ulogaInt = Integer.parseInt(split[8]);
				Uloga uloga = Uloga.valueOf(ulogaInt);
				String plataS = split[9];
				int plata = Integer.parseInt(plataS);
				int sluzbaInt = Integer.parseInt(split[10]);
				Sluzba sluzba = Sluzba.valueOf(sluzbaInt);
				MedicinskaSestra mSestra = new MedicinskaSestra(ime,prezime,
						jmbg, pol, adresa, brojTel, korisnickoIme,lozinka, uloga,plata, sluzba);
				sestre.add(mSestra);
			}
			reader.close();
		}catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public void ucitajLekara() {
		try {
			File lekarFile = new File("src/fajlovi/lekar.txt");
			BufferedReader reader = new BufferedReader(new FileReader(lekarFile));
			String line = null;
			while((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				String ime = split[0];
				String prezime = split[1];
				String jmbg = split[2];
				int polInt = Integer.parseInt(split[3]);
				Pol pol = Pol.valueOf(polInt);
				String adresa = split[4];
				String brojTel = split[5];
				String korisnickoIme = split[6];
				String lozinka = split[7];
				int ulogaInt = Integer.parseInt(split[8]);
				Uloga uloga = Uloga.valueOf(ulogaInt);
				String plataS = split[9];
				int plata = Integer.parseInt(plataS);
				int sluzbaInt = Integer.parseInt(split[10]);
				Sluzba sluzba = Sluzba.valueOf(sluzbaInt);
				String specijalizacija = split[11];
				Lekar lekar = new Lekar(ime,prezime,jmbg,pol,adresa,brojTel,
						korisnickoIme,lozinka,uloga,plata,sluzba,specijalizacija);
				lekari.add(lekar);
		}
			reader.close();
	}catch(Exception e) {
		e.printStackTrace();
	}
}
	public void ucitajPacijenta() {
		try {
			File pacijentFile = new File("src/fajlovi/pacijent.txt");
			BufferedReader reader = new BufferedReader(new FileReader(pacijentFile));
			String line = null;
			while((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				String ime = split[0];
				String prezime = split[1];
				String jmbg = split[2];
				int polInt = Integer.parseInt(split[3]);
				Pol pol = Pol.valueOf(polInt);
				String adresa = split[4];
				String brojTel = split[5];
				String korisnickoIme = split[6];
				String lozinka = split[7];
				int ulogaInt = Integer.parseInt(split[8]);
				Uloga uloga = Uloga.valueOf(ulogaInt);
				String izLekar = split[9];
				Knjizica knjizica = nadjiKnjizicu(split[10]);
				Pacijent pacijent = new Pacijent(ime,prezime,jmbg,pol,adresa,
						brojTel,korisnickoIme,lozinka,uloga,izLekar,knjizica);
				pacijenti.add(pacijent);
			}
			reader.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void ucitajPregled() {
		try {
			File pregledFile = new File("src/fajlovi/pregled.txt");
			BufferedReader reader = new BufferedReader(new FileReader(pregledFile));
			String line = null;
			while((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				Pacijent pacijent = nadjiPacijenta(split[0]);
				//System.out.println(split[0]);
				Lekar lekar = nadjiLekara(split[1]);
				Date termin = formatter.parse(split[2]);
				String soba = split[3];
				String opis = split[4];
				int statusInt = Integer.parseInt(split[5]);
				Status status = Status.valueOf(statusInt);
				Pregled pregled = new Pregled(pacijent, lekar, termin, soba,opis, status);
				pregledi.add(pregled);
			}
			reader.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void ucitajKnjzicu() {
		try {
			File knjizicaFile = new File("src/fajlovi/knjizica.txt");
			BufferedReader reader = new BufferedReader(new FileReader(knjizicaFile));
			String line = null;
			while((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				Pacijent pacijent = nadjiPacijenta(split[0]);
				String brojKnjizice = split[1];
				String datumIsteka = split[2];
				int katInt = Integer.parseInt(split[3]);
				Kategorija kategorija = Kategorija.valueOf(katInt);
				Knjizica knjizica = new Knjizica(pacijent,brojKnjizice,datumIsteka,kategorija);
				knjizice.add(knjizica);
			}
			reader.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void snimiSestru() {
		try {
			File mSestraFile = new File("src/fajlovi/msestra.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(mSestraFile));
			String content = "";
			for(MedicinskaSestra mSestra : sestre) {
				content += mSestra.getIme() + "|" + mSestra.getPrezime() + "|" + mSestra.getJmbg() + 
						"|" + Pol.toInt(mSestra.getPol()) + "|" + mSestra.getAdresa() + "|" + 
						mSestra.getBrojTel() + "|" + mSestra.getKorisnickoIme() +
						"|" + mSestra.getLozinka() + "|" + Uloga.toInt(mSestra.getUloga()) +
						"|" + mSestra.getPlata() + "|" + Sluzba.toInt(mSestra.getSluzba()) + "\n";
			}
			writer.write(content);
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	public void snimiLekara() {
		try {
			File lekarFile = new File("src/fajlovi/lekar.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(lekarFile));
			String content = "";
			for(Lekar lekar : lekari) {
				content += lekar.getIme() + "|" + lekar.getPrezime() + "|" + lekar.getJmbg() + 
						"|" + Pol.toInt(lekar.getPol()) + "|" + lekar.getAdresa() + "|" + lekar.getBrojTel() + "|" +
						lekar.getKorisnickoIme() + "|" + lekar.getLozinka() + "|" + Uloga.toInt(lekar.getUloga()) + "|" + 
						lekar.getPlata() + "|" + Sluzba.toInt(lekar.getSluzba()) + "|" + lekar.getSpecijalizacija() +  "\n";
		}
		writer.write(content);
		writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void snimiPacijenta() {
		try {
			File pacijentFile = new File("src/fajlovi/pacijent.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(pacijentFile));
			String content = "";
			for(Pacijent pacijent: pacijenti) {
				content += pacijent.getIme() + "|" + pacijent.getPrezime() + "|" + pacijent.getJmbg() + 
						"|" + Pol.toInt(pacijent.getPol()) + "|" + pacijent.getAdresa() + "|" + pacijent.getBrojTel() + "|" +  pacijent.getKorisnickoIme() + "|" +
						pacijent.getLozinka() + "|" + Uloga.toInt(pacijent.getUloga()) + "|" + pacijent.getIzabraniLekar() + "|" + pacijent.getKnjizica() + "\n";

		}
			writer.write(content);
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	public void snimiPregled() {
		try {
			File pregledFile = new File("src/fajlovi/pregled.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(pregledFile));
			String content = "";
			for(Pregled  pregled: pregledi) {
				content += pregled.getPacijent().getKorisnickoIme() + "|" + pregled.getLekar().getKorisnickoIme() + "|"
						+ formatter.format(pregled.getTermin())+ "|" + pregled.getSoba() +"|" + pregled.getOpis() + "|" + Status.toInt(pregled.getStatus())+ "\n";
			}
			writer.write(content);
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void snimiKnjizicu() {
		try {
			File knjizicaFile = new File("src/fajlovi/knjizica.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(knjizicaFile));
			String content = "";
			for(Knjizica knjizica : knjizice) {
				content += knjizica.getPacijent().getKorisnickoIme() + "|" + knjizica.getBrojKnjizice() + "|" + knjizica.getDatumIsteka()
					+ "|" +  Kategorija.toInt(knjizica.getKategorija()) + "\n";
			}
			writer.write(content);
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
