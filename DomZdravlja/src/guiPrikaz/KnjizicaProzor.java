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

import enumeracije.Kategorija;
import guiIzmeniBrisi.KnjizicaForma;
import knjizicaPaket.Knjizica;
import krajnjiPaket.Liste;
import pacijentPaket.Pacijent;


public class KnjizicaProzor extends JFrame {
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	private DefaultTableModel tableModel;
	private JTable knjizicaTabela;
	private Liste liste;
	private Knjizica knjizica;
	
	public KnjizicaProzor(Knjizica knjizica, Liste liste) {
		this.knjizica = knjizica;
		this.liste = liste;
		setTitle("Knjizica");
		setSize(800,800);
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
		
		String[] zaglavlja = new String[] {"Pacijent", "Broj knjizice", "Datum isteka", "Kategorija"};
		Object[][] sadrzaj = new Object[liste.getKnjizice().size()][zaglavlja.length];
		
		for(int i = 0; i<sadrzaj.length; i ++) {
			Knjizica knjizica = liste.getKnjizice().get(i);
			sadrzaj[i][0] = knjizica.getPacijent().getKorisnickoIme();
			sadrzaj[i][1] = knjizica.getBrojKnjizice();
			sadrzaj[i][2] = knjizica.getDatumIsteka();
			sadrzaj[i][3] = knjizica.getKategorija();
			
		}
		DefaultTableModel model = new DefaultTableModel(sadrzaj, zaglavlja);
		knjizicaTabela = new JTable(model);
		knjizicaTabela.setRowSelectionAllowed(true);
		knjizicaTabela.setColumnSelectionAllowed(false);
		knjizicaTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		knjizicaTabela.setDefaultEditor(Object.class, null);
		JScrollPane scrollPane = new JScrollPane(knjizicaTabela);
		add(scrollPane);
	}
	private void snimiKnjizicuIzTabele() {
		DefaultTableModel model = (DefaultTableModel) knjizicaTabela.getModel();
		for(int i = 0; i<knjizicaTabela.getRowCount(); i++) {
			Pacijent pacijent = (Pacijent) model.getValueAt(i, 0);
			String brojKnjizice = (String) model.getValueAt(i, 1);
			String datumIsteka = (String) model.getValueAt(i, 2);
			Kategorija kategorija = (Kategorija) model.getValueAt(i, 3);
			Knjizica knjizica = liste.nadjiKnjizicu(brojKnjizice);
			knjizica.setPacijent(pacijent);
			knjizica.setBrojKnjizice(brojKnjizice);
			knjizica.setDatumIsteka(datumIsteka);
			knjizica.setKategorija(kategorija);
		}
			
	}
	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				KnjizicaForma kf = new KnjizicaForma(liste, knjizica);
				kf.setVisible(true);
				
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = knjizicaTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli", 
							"Greska", JOptionPane.WARNING_MESSAGE);
					
				}else {
					DefaultTableModel model = (DefaultTableModel) knjizicaTabela.getModel();
					String korisnickoIme = model.getValueAt(red, 0).toString();
					String brojKnjizice = model.getValueAt(red, 1).toString();
					Pacijent pacijent = liste.nadjiPacijenta(korisnickoIme);
					Knjizica knjizica = liste.nadjiKnjizicu(brojKnjizice);
					if(knjizica != null) {
						KnjizicaForma kf  = new KnjizicaForma(liste, knjizica);
						kf.setVisible(true);
						
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci knjizicu", 
								"Greska", JOptionPane.ERROR_MESSAGE);
					}
					
				}
				
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = knjizicaTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli", 
							"Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel) knjizicaTabela.getModel();
					String korisnickoIme = model.getValueAt(red, 0).toString();
					String brojKnjizice = model.getValueAt(red, 1).toString();
					Pacijent pacijent = liste.nadjiPacijenta(korisnickoIme);
					Knjizica knjizica = liste.nadjiKnjizicu(brojKnjizice);
					if(knjizica != null) {
						int izbor = JOptionPane.showConfirmDialog(null, 
								"Da li ste sigurni da zelite da obrisete knjizicu?",
								knjizica.getPacijent() + "Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							model.removeRow(red);
							liste.snimiKnjizicu();
							
					}else {
						JOptionPane.showMessageDialog(null, "Ne moze se naci odabranu knjizicu", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
			}
		});
		
	}
}
