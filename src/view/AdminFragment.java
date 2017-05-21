package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controller.RowListener;
import model.CD;
import model.Magazzino;
import model.OccorrenzeDisco;
import model.PersonaleAutorizzato;

public class AdminFragment extends JFrame {

	private Magazzino magazzino;
	private PersonaleAutorizzato admin;
	
	private JMenuBar menuBar;
	
	private JMenu file;
	private JMenuItem add;
	private JMenuItem logout;
	private JMenuItem exit;
	
	private JMenu edit;
	private JMenu sort;
	private JMenuItem sortByTitolo;
	private JMenuItem sortByArtista;
	private JMenuItem sortByPrezzo;
	private JMenu search;
	private JMenuItem searchByGenere;
	private JMenuItem searchByTitolare;
	private JMenuItem searchByMusicista;
	private JMenuItem searchByPrezzo;

	/**
	 * Create the frame.
	 */
	public AdminFragment(String titolo, Magazzino magazzino, PersonaleAutorizzato admin) {
		super(titolo);
		
		this.magazzino = magazzino;
		this.admin = admin;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		menuBar = new JMenuBar();
		
		file = new JMenu("File");
		logout = new JMenuItem("Logout");
		exit = new JMenuItem("Exit");
		add = new JMenuItem("Add");
		file.add(logout);
		file.add(exit);
		file.add(add);
		
		edit = new JMenu("Edit");
		
		sort = new JMenu("Ordina per...");
		sortByArtista = new JMenuItem("Artista");
		sortByPrezzo = new JMenuItem("Prezzo");
		sortByTitolo = new JMenuItem("Titolo");
		sort.add(sortByTitolo);
		sort.add(sortByArtista);
		sort.add(sortByPrezzo);
		edit.add(sort);
		
		search = new JMenu("Cerca per...");
		searchByGenere = new JMenuItem("Genere");
		searchByTitolare = new JMenuItem("Titolare");
		searchByMusicista = new JMenuItem("Musicista presente");
		searchByPrezzo = new JMenuItem("Prezzo");
		search.add(searchByGenere);
		search.add(searchByTitolare);
		search.add(searchByMusicista);
		search.add(searchByPrezzo);
		
		edit.add(search);
		menuBar.add(file);
		menuBar.add(edit);
		
		String titoli[]={"Tipo", "Titolo","Titolare","Icona", "Genere", "Prezzo", "Disponibilità"};
		List<OccorrenzeDisco> pezzi = magazzino.viewCatalogo();
		
		Object dati[][] = new String[pezzi.size()][titoli.length];
		
		
		for (int i = 0; i < pezzi.size(); i++){
			if (pezzi.get(i).getDisco() instanceof CD){
				dati[i][0] = "CD";
			}else{
				dati[i][0] = "DVD";
			}
			
			dati[i][1] = pezzi.get(i).getDisco().getTitolo();
			dati[i][2] = pezzi.get(i).getDisco().getTitolare().getNomeArte();
			dati[i][3] = pezzi.get(i).getDisco().getFotografie().get(0);
			dati[i][4] = pezzi.get(i).getDisco().getGenere().toString();
			dati[i][5] = String.valueOf(pezzi.get(i).getDisco().getPrezzo());
			dati[i][6] = String.valueOf(pezzi.get(i).getOccorrenza());
			
		}
		
	    ViewTable tabella=new ViewTable(dati, titoli);
	    tabella.setBounds(30,40,200,300);
	    RowListener listener = new RowListener(pezzi);
        tabella.addMouseListener(listener);
	    
	    JScrollPane sp=new JScrollPane(tabella);    
	    this.add(sp);
		
		this.setJMenuBar(menuBar);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		
	}

}