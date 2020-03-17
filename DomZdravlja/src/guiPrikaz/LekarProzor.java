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
import enumeracije.Sluzba;
import enumeracije.Uloga;
import guiIzmeniBrisi.LekarForma;
import krajnjiPaket.Liste;
import zaposleniPaket.Lekar;

public class LekarProzor extends JFrame {
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	private DefaultTableModel tableModel;
	private JTable lekarTabela;
	private Lekar lekar;
	private Liste liste;
	
	public LekarProzor(Lekar lekar, Liste liste) {
		this.lekar = lekar;
		this.liste = liste;
		setTitle("Lekari");
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
		
		String[] zaglavlja = new String[] {"Ime", "Prezime", "Jmbg","Pol","Adresa","Broj tel", "Korisnicko ime", "Lozinka", "Uloga", "Plata","Sluzba","Specijalizacija"};
		Object[][] sadrzaj = new Object[liste.getLekari().size()][zaglavlja.length];
		
		for(int i = 0; i<sadrzaj.length; i++) {
			Lekar lekar = liste.getLekari().get(i);
			sadrzaj[i][0] = lekar.getIme();
			sadrzaj[i][1] = lekar.getPrezime();
			sadrzaj[i][2] = lekar.getJmbg();
			sadrzaj[i][3] = lekar.getPol();
			sadrzaj[i][4] = lekar.getAdresa();
			sadrzaj[i][5] = lekar.getBrojTel();
			sadrzaj[i][6] = lekar.getKorisnickoIme();
			sadrzaj[i][7] = lekar.getLozinka();
			sadrzaj[i][8] = lekar.getUloga();
			sadrzaj[i][9] = lekar.getPlata();
			sadrzaj[i][10] = lekar.getSluzba();
			sadrzaj[i][11] = lekar.getSpecijalizacija();
		}
		DefaultTableModel tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		lekarTabela = new JTable(tableModel);
		lekarTabela.setRowSelectionAllowed(true);
		lekarTabela.setColumnSelectionAllowed(false);
		lekarTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lekarTabela.setDefaultEditor(Object.class, null);
		JScrollPane scrollPane = new JScrollPane(lekarTabela);
		add(scrollPane);
	}
	private void snimiLekaraIzTabele() {
		DefaultTableModel model = (DefaultTableModel) lekarTabela.getModel();
		for(int i=0; i<lekarTabela.getRowCount(); i++) {
			String ime = (String) model.getValueAt(i, 0);
			String prezime = (String) model.getValueAt(i, 1);
			String jmbg = (String) model.getValueAt(i, 2);
			Pol pol = (Pol) model.getValueAt(i, 3);
			String adresa = (String) model.getValueAt(i, 4);
			String brojTel = (String) model.getValueAt(i, 5);
			String korisnickoIme = (String) model.getValueAt(i, 6);
			String lozinka = (String) model.getValueAt(i, 7);
			Uloga uloga = (Uloga) model.getValueAt(i, 8);
			int plata = (int) model.getValueAt(i, 9);
			Sluzba sluzba = (Sluzba) model.getValueAt(i, 10);
			String specijalizacija = (String) model.getValueAt(i, 11);
			Lekar lekar = liste.nadjiLekara(korisnickoIme);
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
			lekar.setSpecijalizacija(specijalizacija);
		}
		
	}
	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LekarForma lf = new LekarForma(liste, lekar);
				lf.setVisible(true);
				
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = lekarTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli", 
							"Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel) lekarTabela.getModel();
					String korisnickoIme = model.getValueAt(red, 6).toString();
					Lekar lekar = liste.nadjiLekara(korisnickoIme);
					if(lekar != null) {
						LekarForma lf = new LekarForma(liste, lekar);
						lf.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci lekara",
								"Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = lekarTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, 
							"Morate odabrati red u tabeli", "Greska", JOptionPane.WARNING_MESSAGE);
					
				}else {
					DefaultTableModel model = (DefaultTableModel) lekarTabela.getModel();
					String korisnickoIme = model.getValueAt(red, 6).toString();
					Lekar lekar = liste.nadjiLekara(korisnickoIme);
					if(lekar != null) {
						int izbor = JOptionPane.showConfirmDialog(null, "Da li zelite da obrisete lekara?",
								lekar.getKorisnickoIme() + "Brisanje", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							liste.getLekari().remove(lekar);
							model.removeRow(red);
							liste.snimiLekara();
						}else {
							JOptionPane.showMessageDialog(null, "Nije moguce pronaci lekara", 
									"Greska", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				
			}
		});
		
	}
}
