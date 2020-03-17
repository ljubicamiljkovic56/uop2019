package guiPrikaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import enumeracije.Pol;
import enumeracije.Uloga;
import guiIzmeniBrisi.PacijentForma;
import knjizicaPaket.Knjizica;
import krajnjiPaket.Liste;
import pacijentPaket.Pacijent;

public class PacijentProzor extends JFrame {
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	private DefaultTableModel tableModel;
	private JTable pacijentTabela;
	private Pacijent pacijent;
	private Liste liste;
	
	public PacijentProzor(Pacijent pacijent, Liste liste) {
		this.pacijent = pacijent;
		this.liste = liste;
		setTitle("Pacijenti");
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
	}
	private void initGUI() {
		ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
		btnAdd.setIcon(addIcon);
		mainToolbar.add(btnAdd);
		ImageIcon editIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
		btnEdit.setIcon(editIcon);
		mainToolbar.add(btnEdit);
		ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/remove.gif"));
		btnDelete.setIcon(deleteIcon);
		mainToolbar.add(btnDelete);
		add(mainToolbar, BorderLayout.NORTH);
		
		String[] zaglavlja = new String[] {"Ime", "Prezime","Jmbg", "Pol","Adresa","Broj tel", "Korisnicko ime", "Lozinka", "Uloga", "Izabrani lekar", "Knjizica" };
		Object[][] sadrzaj = new Object[liste.getPacijenti().size()][zaglavlja.length];
		
		for(int i = 0; i<sadrzaj.length; i ++) {
			Pacijent pacijent = liste.getPacijenti().get(i);
			sadrzaj[i][0] = pacijent.getIme();
			sadrzaj[i][1] = pacijent.getPrezime();
			sadrzaj[i][2] = pacijent.getJmbg();
			sadrzaj[i][3] = pacijent.getPol();
			sadrzaj[i][4] = pacijent.getAdresa();
			sadrzaj[i][5] = pacijent.getBrojTel();
			sadrzaj[i][6] = pacijent.getKorisnickoIme();
			sadrzaj[i][7] = pacijent.getLozinka();
			sadrzaj[i][8] = pacijent.getUloga();
			sadrzaj[i][9] = pacijent.getIzabraniLekar();
			sadrzaj[i][10] = pacijent.getKnjizica();
			
		}
		DefaultTableModel model = new DefaultTableModel(sadrzaj, zaglavlja);
		pacijentTabela = new JTable(model);
		pacijentTabela.setRowSelectionAllowed(true);
		pacijentTabela.setColumnSelectionAllowed(false);
		pacijentTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pacijentTabela.setDefaultEditor(Object.class, null);
		JScrollPane scrollPane = new JScrollPane(pacijentTabela);
		add(scrollPane);
	}
	private void snimiPacijenteIzTabele() {
		DefaultTableModel model = (DefaultTableModel) pacijentTabela.getModel();
		for(int i = 0; i<pacijentTabela.getRowCount(); i++) {
			String ime = (String) model.getValueAt(i, 0);
			String prezime = (String) model.getValueAt(i, 1);
			String jmbg = (String) model.getValueAt(i, 2);
			Pol pol = (Pol) model.getValueAt(i, 3);
			String adresa = (String) model.getValueAt(i, 4);
			String brojTel = (String) model.getValueAt(i, 5);
			String korisnickoIme = (String) model.getValueAt(i, 6);
			String lozinka = (String) model.getValueAt(i, 7);
			Uloga uloga = (Uloga) model.getValueAt(i, 8);
			String izabraniLekar  = (String) model.getValueAt(i, 9);
			Knjizica knjizica = (Knjizica) model.getValueAt(i, 10);
			Pacijent pacijent = liste.nadjiPacijenta(korisnickoIme);
			pacijent.setIme(ime);
			pacijent.setPrezime(prezime);
			pacijent.setJmbg(jmbg);
			pacijent.setPol(pol);
			pacijent.setAdresa(adresa);
			pacijent.setBrojTel(brojTel);
			pacijent.setKorisnickoIme(korisnickoIme);
			pacijent.setLozinka(lozinka);
			pacijent.setUloga(uloga);
			pacijent.setIzabraniLekar(izabraniLekar);
			pacijent.setKnjizica(knjizica);
		}
			
	}
	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PacijentForma pf = new PacijentForma(liste, pacijent);
				pf.setVisible(true);
				
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int red = pacijentTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli", 
							"Greska", JOptionPane.WARNING_MESSAGE);
					
				}else {
					DefaultTableModel model = (DefaultTableModel) pacijentTabela.getModel();
					String korisnickoIme = model.getValueAt(red, 6).toString();
					Pacijent pacijent = liste.nadjiPacijenta(korisnickoIme);
					if(pacijent != null) {
						PacijentForma pf = new PacijentForma(liste, pacijent);
						pf.setVisible(true);
						
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog pacijenta", 
								"Greska", JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
				
		});
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int red = pacijentTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli", 
							"Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String korisnickoIme = pacijentTabela.getValueAt(red, 6).toString();
					Pacijent pacijent = liste.nadjiPacijenta(korisnickoIme);
					if(pacijent != null) {
						int izbor = JOptionPane.showConfirmDialog(null, 
								"Da li ste sigurni da zelite da obrisete pacijenta?",
								pacijent.getIme() + "Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							DefaultTableModel model = (DefaultTableModel) pacijentTabela.getModel();
							if(pacijent instanceof Pacijent) {
								pacijent.getIme();
							}else {
								pacijent.getIme();
							}
							liste.getPacijenti().remove(pacijent);
							model.removeRow(red);
							liste.snimiPacijenta();
							}
					}else {
						JOptionPane.showMessageDialog(null, "Ne moze se naci odabrani pacijent", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
				
		});
	}

}
