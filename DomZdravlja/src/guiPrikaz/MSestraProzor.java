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
import guiIzmeniBrisi.SestraForma;
import krajnjiPaket.Liste;
import zaposleniPaket.MedicinskaSestra;

public class MSestraProzor extends JFrame {
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	private DefaultTableModel tableModel;
	private JTable sestraTabela;
	private MedicinskaSestra mSestra;
	private Liste liste;
	
	public MSestraProzor(MedicinskaSestra mSestra, Liste liste) {
		this.mSestra = mSestra;
		this.liste = liste;
		setTitle("Medicinske sestre");
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
		
		String[] zaglavlja = new String[] {"Ime", "Prezime", "Jmbg", "Pol","Adresa", "Broj tel.",  "Korisnicko ime", "Lozinka", "Uloga", "Plata", "Sluzba"};
		Object[][] sadrzaj = new Object[liste.getSestre().size()][zaglavlja.length];
		
		for(int i = 0; i<sadrzaj.length; i++) {
			MedicinskaSestra mSestra = liste.getSestre().get(i);
			sadrzaj[i][0] = mSestra.getIme();
			sadrzaj[i][1] = mSestra.getPrezime();
			sadrzaj[i][2] = mSestra.getJmbg();
			sadrzaj[i][3] = mSestra.getPol();
			sadrzaj[i][4] = mSestra.getAdresa();
			sadrzaj[i][5] = mSestra.getBrojTel();
			sadrzaj[i][6] = mSestra.getKorisnickoIme();
			sadrzaj[i][7] = mSestra.getLozinka();
			sadrzaj[i][8] = mSestra.getUloga();
			sadrzaj[i][9] = mSestra.getPlata();
			sadrzaj[i][10] = mSestra.getSluzba();
		}
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		sestraTabela = new JTable(tableModel);
		sestraTabela.setRowSelectionAllowed(true);
		sestraTabela.setColumnSelectionAllowed(false);
		sestraTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sestraTabela.setDefaultEditor(Object.class, null);
		JScrollPane scrollPane = new JScrollPane(sestraTabela);
		add(scrollPane);
	}
	private void snimiSestruIzTabele() {
		DefaultTableModel model = (DefaultTableModel) sestraTabela.getModel();
		for(int i = 0; i<sestraTabela.getRowCount(); i++) {
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
			MedicinskaSestra mSestra = liste.nadjiSestru(korisnickoIme);
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
	}
	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SestraForma sf = new SestraForma(liste, mSestra);
				sf.setVisible(true);
				
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = sestraTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli", 
							"Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel) sestraTabela.getModel();
					String korisnickoIme = model.getValueAt(red, 6).toString();
					MedicinskaSestra mSestra = liste.nadjiSestru(korisnickoIme);
					if(mSestra != null) {
						SestraForma sf = new SestraForma(liste, mSestra);
						sf.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci zaposlenog", 
								"Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = sestraTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli", 
							"Greska", JOptionPane.WARNING_MESSAGE);	
				}else {
					DefaultTableModel model = (DefaultTableModel) sestraTabela.getModel();
					String korisnickoIme = model.getValueAt(red, 6).toString();
					MedicinskaSestra mSestra = liste.nadjiSestru(korisnickoIme);
					if(mSestra != null) {
						int izbor = JOptionPane.showConfirmDialog(null, 
								"Da li zelite da obrisete zaposlenog?", mSestra.getKorisnickoIme() + "Brisanje", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							liste.getSestre().remove(mSestra);
							model.removeRow(red);
							liste.snimiSestru();
						}else {
							JOptionPane.showMessageDialog(null, "Nije moguce pronaci zaposlenog", "Greska", 
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				
				
			}
		});
	}
}
