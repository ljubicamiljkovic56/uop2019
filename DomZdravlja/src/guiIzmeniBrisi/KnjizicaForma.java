package guiIzmeniBrisi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import enumeracije.Kategorija;
import knjizicaPaket.Knjizica;
import krajnjiPaket.Liste;
import net.miginfocom.swing.MigLayout;
import pacijentPaket.Pacijent;

@SuppressWarnings("serial")
public class KnjizicaForma extends JFrame {
	private JLabel lblPacijent = new JLabel("Pacijent");
	private JComboBox<String> cbPacijent = new JComboBox<String>();
	
	private JLabel lblBrojKnjizice = new JLabel("Broj knjizice");
	private JTextField txtBrojKnjizice = new JTextField(20);
	
	private JLabel lblDatumIsteka = new JLabel("Datum isteka");
	private JTextField txtDatumIsteka  = new JTextField(20);
	
	private JLabel lblKategorija = new JLabel("Kategorija");
	private JComboBox<Kategorija> cbKategorija = new JComboBox<Kategorija>(Kategorija.values());
	
	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private Liste liste;
	private Knjizica knjizica;
	
	public KnjizicaForma(Liste liste, Knjizica knjizica) {
		this.liste = liste;
		this.knjizica = knjizica;
		if(knjizica == null) {
			setTitle("Dodavanje knjizice.");
		}else {
			setTitle(knjizica.getPacijent() + "- Izmena podataka");
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
		
		if(this.knjizica != null) {
			popuniKnjizicu();
		}
		for(Pacijent pacijent : this.liste.getPacijenti()) {
			cbPacijent.addItem(pacijent.getKorisnickoIme());
		}
		
		
		add(lblPacijent);
		add(cbPacijent);
		add(lblBrojKnjizice);
		add(txtBrojKnjizice);
		add(lblDatumIsteka);
		add(txtDatumIsteka);
		add(lblKategorija);
		add(cbKategorija);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnCancel);
		}
		
	private void initActions() {
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(validacija() == true) {
					String pacijentK = cbPacijent.getSelectedItem().toString();
					Pacijent pacijent = liste.nadjiPacijenta(pacijentK);
					String brojKnjizice = txtBrojKnjizice.getText().trim();
					String datumIsteka = txtDatumIsteka.getText().trim();
					Kategorija kategorija = (Kategorija) cbKategorija.getSelectedItem();
					if(knjizica == null) {
						knjizica = new Knjizica(pacijent,brojKnjizice,datumIsteka,kategorija);
						liste.getKnjizice().add(knjizica);
					}else {
						knjizica.setPacijent(pacijent);
						knjizica.setBrojKnjizice(brojKnjizice);
						knjizica.setDatumIsteka(datumIsteka);
						knjizica.setKategorija(kategorija);
					}
				liste.snimiKnjizicu();
				KnjizicaForma.this.dispose();
				KnjizicaForma.this.setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "Vec postoji knjizica.", "Neispravni podaci.", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				KnjizicaForma.this.dispose();
				KnjizicaForma.this.setVisible(false);
				
			}
		});
		
	}
	private void popuniKnjizicu() {
		cbPacijent.setSelectedItem(this.knjizica.getPacijent().toString());
		txtBrojKnjizice.setText(this.knjizica.getBrojKnjizice());
		txtDatumIsteka.setText(this.knjizica.getDatumIsteka());
		cbKategorija.setSelectedItem(this.knjizica.getKategorija());
	}
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molim unesite ispravne podatke.";

		if(txtBrojKnjizice.getText().trim().equals("")) {
			poruka += "- Unesite broj knjizice\n";
			ok = false;
		}
		
		if(txtDatumIsteka.getText().trim().equals("")) {
			poruka += "- Unesite datum isteka\n";
			ok = false;
		}
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci.", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}
}
