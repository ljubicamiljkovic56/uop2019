package guiIzmeniBrisi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import enumeracije.Pol;
import enumeracije.Sluzba;
import enumeracije.Uloga;
import krajnjiPaket.Liste;
import net.miginfocom.swing.MigLayout;
import zaposleniPaket.Lekar;

public class LekarForma extends JFrame {
	private JLabel lblIme = new JLabel("Ime");
	private JTextField txtIme = new JTextField(20);
	
	private JLabel lblPrezime = new JLabel("Prezime");
	private JTextField txtPrezime = new JTextField(20);
	
	private JLabel lblJmbg = new JLabel("Jmbg");
	private JTextField txtJmbg = new JTextField(20);
	
	private JLabel lblPol = new JLabel("Pol");
	private JComboBox<Pol> cbPol = new JComboBox<Pol>(Pol.values());
	
	private JLabel lblAdresa = new JLabel("Adresa");
	private JTextField txtAdresa = new JTextField(20);
	
	private JLabel lblBrojTel = new JLabel("Broj tel");
	private JTextField txtBrojTel = new JTextField(20);
	
	private JLabel lblKorisnickoIme = new JLabel("Korisnicko ime");
	private JTextField txtKorisnickoIme = new JTextField(20);
	
	private JLabel lblLozinka = new JLabel("Lozinka");
	private JPasswordField pfLozinka = new JPasswordField(20);
	
	private JLabel lblUloga = new JLabel("Uloga");
	private JComboBox<Uloga> cbUloga = new JComboBox<Uloga>(Uloga.values());
	
	private JLabel lblPlata = new JLabel("Plata");
	private JTextField txtPlata = new JTextField(20);
	
	private JLabel lblSluzba = new JLabel("Sluzba");
	private JComboBox<Sluzba> cbSluzba = new JComboBox<Sluzba>(Sluzba.values());
	
	private JLabel lblSpec = new JLabel("Specijalizacija");
	private JTextField txtSpec = new JTextField(20);
	
	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private Liste liste;
	private Lekar lekar;
	
	public LekarForma(Liste liste, Lekar lekar) {
		this.liste = liste;
		this.lekar = lekar;
		if(lekar == null) {
			setTitle("Dodavanje lekara");
		}else {
			setTitle(lekar.getIme() + "- Izmena podataka");
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		initActions();
		pack();
	}
	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2");
		setLayout(layout);
		
		if(lekar != null) {
			popuniLekara();
		}else {
		add(lblIme);
		add(txtIme);
		add(lblPrezime);
		add(txtPrezime);
		add(lblJmbg);
		add(txtJmbg);
		add(lblPol);
		add(cbPol);
		add(lblAdresa);
		add(txtAdresa);
		add(lblBrojTel);
		add(txtBrojTel);
		add(lblKorisnickoIme);
		add(txtKorisnickoIme);
		add(lblLozinka);
		add(pfLozinka);
		add(lblUloga);
		add(cbUloga);
		add(lblPlata);
		add(txtPlata);
		add(lblSluzba);
		add(cbSluzba);
		add(lblSpec);
		add(txtSpec);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnCancel);
		}
	}
	private void initActions() {
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(validacija() == true) {
					String ime = txtIme.getText().trim();
					String prezime = txtPrezime.getText().trim();
					String jmbg  = txtJmbg.getText().trim();
					Pol pol = (Pol) cbPol.getSelectedItem();
					String adresa = txtAdresa.getText().trim();
					String brojTel = txtBrojTel.getText().trim();
					String korisnickoIme = txtKorisnickoIme.getText().trim();
					String lozinka = new String(pfLozinka.getPassword()).trim();
					Uloga uloga = (Uloga) cbUloga.getSelectedItem();
					int plata = Integer.parseInt(txtPlata.getText().trim());
					Sluzba sluzba = (Sluzba) cbSluzba.getSelectedItem();
					String spec =  txtSpec.getText().trim();
					if(lekar == null) {
						lekar = new Lekar(ime,prezime,jmbg,pol,adresa,brojTel,korisnickoIme,lozinka,uloga,plata,sluzba,spec);
						liste.dodajLekara(lekar);
					}else {
						lekar.setIme(ime);
						lekar.setPrezime(prezime);
						lekar.setJmbg(jmbg);
						lekar.setPol(pol);
						lekar.setAdresa(adresa);
						lekar.setBrojTel(brojTel);
						lekar.setKorisnickoIme(korisnickoIme);
						lekar.setLozinka(lozinka);
						lekar.setUloga(uloga);
						lekar.setPlata(plata);
						lekar.setSluzba(sluzba);
						lekar.setSpecijalizacija(spec);
					}
					liste.snimiLekara();
					LekarForma.this.dispose();
					LekarForma.this.setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "Lekar vec postoji.", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LekarForma.this.dispose();
				LekarForma.this.setVisible(false);
				
			}
		});
	}
	private void popuniLekara() {
		txtIme.setText(this.lekar.getIme());
		txtPrezime.setText(this.lekar.getPrezime());
		txtJmbg.setText(this.lekar.getJmbg());
		cbPol.setSelectedItem(this.lekar.getPol());
		txtAdresa.setText(this.lekar.getAdresa());
		txtBrojTel.setText(this.lekar.getBrojTel());
		txtKorisnickoIme.setText(this.lekar.getKorisnickoIme());
		pfLozinka.setText(this.lekar.getLozinka());
		cbUloga.setSelectedItem(this.lekar.getUloga());
		txtPlata.setText((String.valueOf(this.lekar.getPlata())));
		cbSluzba.setSelectedItem(this.lekar.getSluzba());
		txtSpec.setText(this.lekar.getSpecijalizacija());
		
		add(txtIme);
		add(txtPrezime);
		add(txtJmbg);
		add(cbPol);
		add(txtAdresa);
		add(txtBrojTel);
		add(txtKorisnickoIme);
		add(pfLozinka);
		add(cbUloga);
		add(txtPlata);
		add(cbSluzba);
		add(txtSpec);
		add(btnOK);
		add(btnCancel);
	}
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molim popravite sledece greske u unosu.";
		if(txtIme.getText().trim().equals("")) {
			poruka += "- Unesite ime\n";
			ok = false;
		}
		if(txtPrezime.getText().trim().equals("")) {
			poruka += "- Unesite prezime\n";
			ok = false;
		}
		
		if(txtJmbg.getText().trim().equals("")) {
			poruka += "- Unesite jmbg\n";
			ok = false;
		}
		
		if(txtAdresa.getText().trim().equals("")) {
			poruka += "- Unesite adresu\n";
			ok = false;
		}
		
		if(txtBrojTel.getText().trim().equals("")) {
			poruka += "- Unesite broj telefona\n";
			ok = false;
		}
		
		if(txtKorisnickoIme.getText().trim().equals("")) {
			poruka += "- Unesite korisnicko ime\n";
			ok = false;
		}
		String lozinka = new String(pfLozinka.getPassword()).trim();
		if(lozinka.trim().equals("")) {
			poruka += "- Unesite lozinku\n";
			ok = false;
		}
		
		if(txtPlata.getText().trim().equals("")) {
			poruka += "- Unesite platu\n";
			ok = false;
		}
		if(txtSpec.getText().trim().equals("")) {
			poruka += "- Unesite specijalizaciju\n";
			ok = false;
		}
		if(ok == false) {
			JOptionPane.showMessageDialog(null, "poruka", "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}
}
