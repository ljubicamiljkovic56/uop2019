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
import enumeracije.Uloga;
import knjizicaPaket.Knjizica;
import krajnjiPaket.Liste;
import net.miginfocom.swing.MigLayout;
import pacijentPaket.Pacijent;

@SuppressWarnings("serial")
public class PacijentForma extends JFrame {
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
	
	private JLabel lblLekar = new JLabel("Izabrani lekar");
	private JTextField txtLekar = new JTextField(20);
	
	private JLabel lblKnjizica = new JLabel("Knjizica");
	private JComboBox<Knjizica> cbKnjizica = new JComboBox<Knjizica>();
	
	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	private Liste liste;
	private Pacijent pacijent;
	
	public PacijentForma(Liste liste, Pacijent pacijent) {
		this.liste = liste;
		this.pacijent = pacijent;
		if(pacijent == null) {
			setTitle("Dodavanje pacijenta");
		}else {
			setTitle("Izmena podataka - " + this.pacijent.getKorisnickoIme());	
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		setResizable(false);
		pack();
	}
	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2");
		setLayout(layout);
		
		if(this.pacijent != null) {
			popuniPacijenta();
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
		add(lblLekar);
		add(txtLekar);
		add(lblKnjizica);
		add(cbKnjizica);
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
					String izLekar = txtLekar.getText().trim();
					Knjizica knjizica = (Knjizica) cbKnjizica.getSelectedItem();
					if(pacijent == null) {
						pacijent = new Pacijent(ime,prezime,jmbg,pol,adresa,brojTel,korisnickoIme,lozinka,uloga,izLekar,knjizica);
						liste.getPacijenti().add(pacijent);
					}else {
						pacijent.setIme(ime);
						pacijent.setPrezime(prezime);
						pacijent.setJmbg(jmbg);
						pacijent.setPol(pol);
						pacijent.setAdresa(adresa);
						pacijent.setBrojTel(brojTel);
						pacijent.setKorisnickoIme(korisnickoIme);
						pacijent.setLozinka(lozinka);
						pacijent.setUloga(uloga);
						pacijent.setIzabraniLekar(izLekar);
						pacijent.setKnjizica(knjizica);
					}
					liste.snimiPacijenta();
					PacijentForma.this.dispose();
					PacijentForma.this.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Pacijent vec postoji.", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PacijentForma.this.dispose();
				PacijentForma.this.setVisible(false);
				
			}
		});
		
	}
	private void popuniPacijenta() {
		txtIme.setText(this.pacijent.getIme());
		txtPrezime.setText(this.pacijent.getPrezime());
		txtJmbg.setText(this.pacijent.getJmbg());
		cbPol.setSelectedItem(this.pacijent.getPol());
		txtAdresa.setText(this.pacijent.getAdresa());
		txtBrojTel.setText(this.pacijent.getBrojTel());
		txtKorisnickoIme.setText(this.pacijent.getKorisnickoIme());
		pfLozinka.setText(this.pacijent.getLozinka());
		cbUloga.setSelectedItem(this.pacijent.getUloga());
		txtLekar.setText(this.pacijent.getIzabraniLekar());
		cbKnjizica.setSelectedItem(this.pacijent.getKnjizica());
		
		add(txtIme);
		add(txtPrezime);
		add(txtJmbg);
		add(cbPol);
		add(txtAdresa);
		add(txtBrojTel);
		add(txtKorisnickoIme);
		add(pfLozinka);
		add(cbUloga);
		add(txtLekar);
		add(cbKnjizica);
		add(btnOK);
		add(btnCancel);
	}
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		if(txtIme.getText().trim().equals("")) {
			poruka += "- Unesite ime\n";
			ok = false;
		}
		if(txtPrezime.getText().trim().equals("")) {
			poruka += "- Unesite prezime\n";
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
		if(txtLekar.getText().trim().equals("")) {
			poruka += "- Unesite izabranog lekara\n";
			ok = false;
		}

		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci.", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}
}
