package glavniILogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import knjizicaPaket.Knjizica;
import krajnjiPaket.Liste;
import net.miginfocom.swing.MigLayout;
import osnovniPaket.Osoba;

@SuppressWarnings("serial")
public class Login extends JFrame {
	private JLabel lblPoruka;
	private JLabel lblKorisnickoIme;
	private JTextField txtKorisnickoIme;
	private JLabel lblLozinka;
	private JPasswordField pfLozinka;
	private JButton btnOK;
	private JButton btnCancel;
	@SuppressWarnings("unused")
	private Knjizica knjizica;
	private Liste liste;
	
	public Login(Liste liste, Knjizica knjizica) {
		this.liste = liste;
		this.knjizica = knjizica;
		setTitle("Prijava");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		initGUI();
		initActions();
		pack();
		
	}
	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][]20[]");
		setLayout(layout);
		this.lblPoruka = new JLabel("Dobrodosli. Molimo da se prijavite.");
		this.lblKorisnickoIme = new JLabel("Korisnicko ime: ");
		this.txtKorisnickoIme = new JTextField(20);
		this.lblLozinka = new JLabel("Lozinka: ");
		this.pfLozinka = new JPasswordField(20);
		this.btnOK = new JButton("OK");
		this.btnCancel = new JButton("Cancel");
		this.getRootPane().setDefaultButton(btnOK);
		
		add(lblPoruka, "span 2");
		add(lblKorisnickoIme);
		add(txtKorisnickoIme);
		add(lblLozinka);
		add(pfLozinka);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnCancel);
	}
	private void initActions() {
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String korisnickoIme = txtKorisnickoIme.getText().trim();
				String lozinka = new String(pfLozinka.getPassword());
				if(korisnickoIme.equals("") || lozinka.equals("")) {
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke.");
				} else {
					Osoba osoba = liste.login(korisnickoIme, lozinka);
					if(osoba != null) {
						
						if(osoba instanceof zaposleniPaket.MedicinskaSestra) {
							GlavniProzor gp = new GlavniProzor(liste);
							gp.setVisible(true);
						}
						
						if(osoba instanceof zaposleniPaket.Lekar) {
							GlavniProzor gp = new GlavniProzor(liste);
							gp.setVisible(true);
						}
						
						if(osoba instanceof pacijentPaket.Pacijent) {
							GlavniProzor gp = new GlavniProzor(liste);
							gp.setVisible(true);
						}
						
					} else {
						JOptionPane.showMessageDialog(null, "Pogresni login podaci!");
					}
					}
			
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Login.this.setVisible(false);
				Login.this.dispose();
				
			}
		});
	}
}
