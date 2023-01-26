package guiIzmeniBrisi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import enumeracije.Status;
import krajnjiPaket.Liste;
import net.miginfocom.swing.MigLayout;
import pacijentPaket.Pacijent;
import pregledPaket.Pregled;
import zaposleniPaket.Lekar;


@SuppressWarnings("serial")
public class PregledForma extends JFrame {
	private JLabel lblPacijent = new JLabel("Pacijent");
	private JComboBox<String> cbPacijent = new JComboBox<String>();
	
	private JLabel lblLekar = new JLabel("Lekar");
	private JComboBox<String> cbLekar = new JComboBox<String>();
	
	private JLabel lblTermin= new JLabel("Termin");
	private JTextField txtTermin = new JTextField(20);
	
	private JLabel lblSoba= new JLabel("Soba");
	private JTextField txtSoba = new JTextField(20);
	
	private JLabel lblOpis = new JLabel("Opis");
	private JTextField txtOpis = new JTextField(20);
	
	private JLabel lblStatus = new JLabel("Status");
	private JComboBox<Status> cbStatus = new JComboBox<Status>(Status.values());

	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	private Liste liste;
	private Pregled pregled;
	
	public PregledForma(Liste liste,Pregled pregled) {
		this.pregled = pregled;
		this.liste = liste;
		if(pregled != null) {
			setTitle(pregled.getPacijent() + "Izmena podataka");
		}else {
			setTitle("Dodavanje pacijent");
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
		
		if(this.pregled != null) {
			popuniPregled();
		}
		for(Pacijent pacijent : this.liste.getPacijenti()) {
			cbPacijent.addItem(pacijent.getKorisnickoIme());
		}
		for (Lekar lekar : this.liste.getLekari()) {
			cbLekar.addItem(lekar.getKorisnickoIme());
		}
		
		add(lblPacijent);
		add(cbPacijent);
		add(lblLekar);
		add(cbLekar);
		add(lblTermin);
		add(txtTermin);
		add(lblSoba);
		add(txtSoba);
		add(lblOpis);
		add(txtOpis);
		add(lblStatus);
		add(cbStatus);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnCancel);
		}
	
	private void initActions() {
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(validacija() == true) {
					String pacijentK  = cbPacijent.getSelectedItem().toString();
					Pacijent pacijent = liste.nadjiPacijenta(pacijentK);
					String lekarK = cbLekar.getSelectedItem().toString();
					Lekar lekar = liste.nadjiLekara(lekarK);
					Date termin = null;
					try {
						termin = (Date) Liste.formatter.parse(txtTermin.getText().trim());
					} catch (ParseException e) {
						System.out.println("Format datuma nije u redu.");
					}
					String soba = txtSoba.getText().trim();
					String opis = txtOpis.getText().trim();
					Status status = (Status) cbStatus.getSelectedItem();
					if(pregled == null) {
						pregled = new Pregled(pacijent,lekar,termin,soba,opis,status);

						liste.getPregledi().add(pregled);
					}else {
						pregled.setPacijent(pacijent);
						pregled.setLekar(lekar);
						pregled.setTermin(termin);
						pregled.setSoba(soba);
						pregled.setOpis(opis);
						pregled.setStatus(status);

					}
					liste.snimiPregled();
					PregledForma.this.dispose();
					PregledForma.this.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, 
								"Pregled postoji", "Greska", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PregledForma.this.dispose();
				PregledForma.this.setVisible(false);
				
			}
		});
		
	}
	private void popuniPregled() {

		cbPacijent.setSelectedItem(this.pregled.getPacijent().toString());
		cbLekar.setSelectedItem(this.pregled.getLekar().toString());
		txtTermin.setText(this.pregled.getTermin().toString());
		txtSoba.setText(this.pregled.getSoba());
		txtOpis.setText(this.pregled.getOpis());
		cbStatus.setSelectedItem(this.pregled.getStatus());
		
		add(cbPacijent);
		add(cbLekar);
		add(txtTermin);
		add(txtSoba);
		add(txtOpis);
		add(cbStatus);
		add(btnOK);
		add(btnCancel);
	}
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu.";
		if(txtTermin.getText().equals("")) {
			poruka += "- Unesite termin\n";
			ok = false;
		}
		if(txtSoba.getText().equals("")) {
			poruka += "- Unesite sobu\n";
			ok = false;
		}
		
		if(txtOpis.getText().equals("")) {
			poruka += "- Unesite opis\n";
			ok = false;
		}

		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci.", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}
}
