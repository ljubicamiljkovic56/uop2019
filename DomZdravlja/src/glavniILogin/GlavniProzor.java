package glavniILogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import guiPrikaz.KnjizicaProzor;
import guiPrikaz.LekarProzor;
import guiPrikaz.MSestraProzor;
import guiPrikaz.PacijentProzor;
import guiPrikaz.PregledProzor;
import knjizicaPaket.Knjizica;
import krajnjiPaket.Liste;
import pacijentPaket.Pacijent;
import pregledPaket.Pregled;
import zaposleniPaket.Lekar;
import zaposleniPaket.MedicinskaSestra;


@SuppressWarnings("serial")
public class GlavniProzor extends JFrame {
	private JMenuBar mainMenu;
	private JMenu preglediMenu;
	private JMenuItem preglediItem;
	private JMenu knjizicaMenu;
	private JMenuItem knjizicaItem;
	private JMenu pacijentMenu;
	private JMenuItem pacijentItem;
	private JMenu mSestraMenu;
	private JMenuItem mSestraItem;
	private JMenu lekarMenu;
	private JMenuItem lekarItem;
	private Knjizica knjizica;
	private Pregled pregled;
	private Pacijent pacijent;
	private MedicinskaSestra mSestra;
	private Lekar lekar;
	private Liste liste;
	
	public GlavniProzor(Liste liste) {
	this.liste = liste;
	setSize(800, 800);
	setLocationRelativeTo(null);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setResizable(false);
	initMenu();
	initActions();
	}
	private void initMenu() {
		this.mainMenu = new JMenuBar();
		this.preglediMenu = new JMenu("Pregledi");
		this.preglediItem = new JMenuItem("Prikaz pregleda");
		this.knjizicaMenu = new JMenu("Knjizica");
		this.knjizicaItem = new JMenuItem("Prikaz knjizica");
		this.pacijentMenu = new JMenu("Pacijent");
		this.pacijentItem = new JMenuItem("Prikaz pacijenata");
		this.mSestraMenu = new JMenu("Medicinska sestra");
		this.mSestraItem = new JMenuItem("Prikaz medicinskih sestara");
		this.lekarMenu = new JMenu("Lekar");
		this.lekarItem = new JMenuItem("Prikaz lekara");
		this.preglediMenu.add(preglediItem);
		this.knjizicaMenu.add(knjizicaItem);
		this.pacijentMenu.add(pacijentItem);
		this.mSestraMenu.add(mSestraItem);
		this.lekarMenu.add(lekarItem);
		this.mainMenu.add(preglediMenu);
		this.mainMenu.add(knjizicaMenu);
		this.mainMenu.add(pacijentMenu);
		this.mainMenu.add(mSestraMenu);
		this.mainMenu.add(lekarMenu);
		
		setJMenuBar(this.mainMenu);
	}
	
	private void initActions() {
		preglediItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Pregledi prozor");
				PregledProzor pp = new PregledProzor(pregled, pacijent, liste);
				pp.setVisible(true);
				
			}
		});
		knjizicaItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				KnjizicaProzor kp = new KnjizicaProzor(knjizica, liste);
				kp.setVisible(true);
				
			}
		});
		pacijentItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PacijentProzor pp = new PacijentProzor(pacijent, liste);
				pp.setVisible(true);
			}
		});
		mSestraItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MSestraProzor mp = new MSestraProzor(mSestra,liste);
				mp.setVisible(true);
				
			}
		});
		lekarItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LekarProzor lp = new LekarProzor(lekar, liste);
				lp.setVisible(true);
				
			}
		});
	}

}
