package guiPrikaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import enumeracije.Status;
import guiIzmeniBrisi.PregledForma;
import krajnjiPaket.Liste;
import pacijentPaket.Pacijent;
import pregledPaket.Pregled;
import zaposleniPaket.Lekar;

public class PregledProzor extends JFrame {
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	private DefaultTableModel tableModel;
	private JTable pregledTabela;
	private Liste liste;
	private Pregled pregled;
	private Pacijent pacijent;
	
	public PregledProzor(Pregled pregled,Pacijent pacijent, Liste liste) {
		this.pregled = pregled;
		this.pacijent = pacijent;
		this.liste = liste;
		setTitle("Pregled");
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
		
		String[] zaglavlja = new String[] {"Pacijent", "Lekar", "Termin", "Soba","Opis", "Status"};
		Object[][] sadrzaj = new Object[liste.getPregledi().size()][zaglavlja.length];
		
		for(int i = 0; i<sadrzaj.length; i++) {
			Pregled pregled = liste.getPregledi().get(i);
			sadrzaj[i][0] = pregled.getPacijent().getKorisnickoIme();
			sadrzaj[i][1] = pregled.getLekar().getKorisnickoIme();
			sadrzaj[i][2] = pregled.getTermin();
			sadrzaj[i][3] = pregled.getSoba();
			sadrzaj[i][4] = pregled.getOpis();
			sadrzaj[i][5] = pregled.getStatus();
		}
		DefaultTableModel tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		pregledTabela = new JTable(tableModel);
		pregledTabela.setRowSelectionAllowed(true);
		pregledTabela.setColumnSelectionAllowed(false);
		pregledTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pregledTabela.setDefaultEditor(Object.class, null);
		JScrollPane scrollPane = new JScrollPane(pregledTabela);
		add(scrollPane);
	}
	private void snimiPregledIzTabele() {
		DefaultTableModel model = (DefaultTableModel) pregledTabela.getModel();
		for(int i = 0; i<pregledTabela.getRowCount(); i++) {
			Pacijent pacijent = (Pacijent) model.getValueAt(i, 0);
			Lekar lekar = (Lekar) model.getValueAt(i, 1);
			Date termin =  (Date) model.getValueAt(i, 2);
			String soba = (String) model.getValueAt(i, 3);
			String opis = (String) model.getValueAt(i, 4);
			Status status = (Status) model.getValueAt(i, 5);
			Pregled pregled = liste.nadjiPregled(pacijent);
			pregled.setPacijent(pacijent);
			pregled.setLekar(lekar);
			pregled.setTermin(termin);
			pregled.setSoba(soba);
			pregled.setOpis(opis);
			pregled.setStatus(status);
		}
	}
	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PregledForma pforma = new PregledForma(liste, pregled);
				pforma.setVisible(true);
				
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int red = pregledTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, 
							"Morate odabrati red u tabeli", "Greska", 
							JOptionPane.WARNING_MESSAGE);
				}else {
					String korisnickoIme = pregledTabela.getValueAt(red,0).toString();
					Pacijent pacijent = liste.nadjiPacijenta(korisnickoIme);
					Pregled pregled = liste.nadjiPregled(pacijent);
					if(pregled != null) {
						PregledForma pforma  = new PregledForma(liste, pregled);
						pforma.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci pregled", 
								"Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int red = pregledTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli", 
							"Greska", JOptionPane.WARNING_MESSAGE );
				}else {
					DefaultTableModel model = (DefaultTableModel) pregledTabela.getModel();
					String korisnickoIme = model.getValueAt(red, 0).toString();
					Pacijent pacijent = liste.nadjiPacijenta(korisnickoIme);
					Pregled pregled = liste.nadjiPregled(pacijent);
					if(pregled != null) {
						int izbor = JOptionPane.showConfirmDialog(null, 
								"Da li zelite da obrisete pregled?", 
								pregled.getPacijent() + "Brisanje", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							liste.getPregledi().remove(pregled);
							model.removeRow(red);
							liste.snimiPregled();
						}
					}else {
						JOptionPane.showMessageDialog(null, 
								"Nije pronadjen pregled", "Greska", 
								JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
	}

}
