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
import zaposleniPaket.MedicinskaSestra;

public class SestraForma extends JFrame {
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
	
	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private Liste liste;
	private MedicinskaSestra mSestra;
	
	public SestraForma(Liste liste, MedicinskaSestra mSestra) {
		this.liste = liste;
		this.mSestra = mSestra;
		if(mSestra == null) {
			setTitle("Dodavanje medicinske sestre");
		}else {
			setTitle(mSestra.getIme() + "- Izmena podataka");
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
		
		if(mSestra != null) {
			popuniMSestru();
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
					String jmbg = txtJmbg.getText().trim();
					Pol pol = (Pol) cbPol.getSelectedItem();
					String adresa = txtAdresa.getText().trim();
					String brojTel = txtBrojTel.getText().trim();
					String korisnickoIme = txtKorisnickoIme.getText().trim();
					String lozinka = new String(pfLozinka.getPassword()).trim();
					Uloga uloga = (Uloga) cbUloga.getSelectedItem();
					int plata = Integer.parseInt(txtPlata.getText().trim());
					Sluzba sluzba = (Sluzba) cbSluzba.getSelectedItem();
					if(mSestra == null) {
						mSestra = new MedicinskaSestra(ime,prezime,jmbg,pol,adresa, brojTel, korisnickoIme,lozinka,uloga,plata,sluzba);
						liste.dodajMedicinskuSestru(mSestra);
					}else {
						mSestra.setIme(ime);
						mSestra.setPrezime(prezime);
						mSestra.setJmbg(jmbg);
						mSestra.setPol(pol);
						mSestra.setAdresa(adresa);
						mSestra.setBrojTel(brojTel);
						mSestra.setKorisnickoIme(korisnickoIme);
						mSestra.setLozinka(lozinka);
						mSestra.setUloga(uloga);
						mSestra.setPlata(plata);
						mSestra.setSluzba(sluzba);
					}
					liste.snimiSestru();
					SestraForma.this.dispose();
					SestraForma.this.setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "Medicinska sestra vec postoji.", "Neispravni podaci.", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SestraForma.this.dispose();
				SestraForma.this.setVisible(false);
				
			}
		});
		
	}
	private void popuniMSestru() {
		txtIme.setText(this.mSestra.getIme());
		txtPrezime.setText(this.mSestra.getPrezime());
		txtJmbg.setText(this.mSestra.getJmbg());
		cbPol.setSelectedItem(this.mSestra.getPol());
		txtAdresa.setText(this.mSestra.getAdresa());
		txtBrojTel.setText(this.mSestra.getBrojTel());
		txtKorisnickoIme.setText(this.mSestra.getKorisnickoIme());
		pfLozinka.setText(this.mSestra.getLozinka());
		cbUloga.setSelectedItem(this.mSestra.getUloga());;
		txtPlata.setText((String.valueOf(this.mSestra.getPlata())));
		cbSluzba.setSelectedItem(this.mSestra.getSluzba());
		
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
		add(btnOK);
		add(btnCancel);
	}
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molim popravite sledece greske u unosu. ";
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
		
		if(txtKorisnickoIme.getText().trim().equals("")) {
			poruka += "- Unesite korisnicko ime\n";
			ok = false;
		}
		String lozinka = new String(pfLozinka.getPassword()).trim();
		if(lozinka.trim().equals("")) {
			poruka += "- Unesite lozinku\n";
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
		
		if(txtPlata.getText().trim().equals("")) {
			poruka += "- Unesite platu\n";
			ok = false;
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}

}
