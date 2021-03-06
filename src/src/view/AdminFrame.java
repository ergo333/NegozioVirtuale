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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.FilterListener;
import controller.MenuListener;
import controller.RowListener;
import controller.SortingMenu;
import model.CD;
import model.Disco;
import model.Magazzino;
import model.ModelViewTable;
import model.OccorrenzeDisco;
import model.PersonaleAutorizzato;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.JButton;

public class AdminFrame extends JFrame implements SortingMenu{
	
	private PersonaleAutorizzato admin;

	private JMenuBar menuBar;
	
	private JMenu file;
	private JMenuItem logout;
	private JMenuItem exit;
	
	private JMenu edit;
	private JMenu sort;
	private JMenuItem sortByGenere;
	private JMenuItem sortByTitolare;
	private JMenuItem sortByPrezzo;
	private JMenu add;
	private JMenuItem addDisco;
	private JMenuItem addMusicista;
	
	private ViewTable tabella;
	
	private Magazzino magazzino;
	private MenuListener menuListener;
	private JTextField filtro;
	
	private JButton btnCerca;
	private JRadioButton titolareRadio;
	private JRadioButton partecipanteRadio;
	private JRadioButton prezzoRadio;
	private JRadioButton genereRadio;
	
	private RowListener listener;
	private JButton btnAnnulla;

	/**
	 * Create the frame.
	 */
	public AdminFrame(String titolo, Magazzino magazzino, PersonaleAutorizzato admin, MainFrame login) {
		super(titolo);
		
		this.admin = admin;
		this.magazzino = magazzino;
		
		menuListener = new MenuListener(magazzino, this, login);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 852, 549);
		
		menuBar = new JMenuBar();
		
		file = new JMenu("File");
		logout = new JMenuItem("Logout");
		exit = new JMenuItem("Exit");
		file.add(logout);
		file.add(exit);
		
		edit = new JMenu("Edit");
		
		add = new JMenu("Add...");
		addDisco = new JMenuItem("Disco");
		addMusicista = new JMenuItem("Musicista");
		add.add(addDisco);
		add.add(addMusicista);

		
		sort = new JMenu("Ordina per...");
		sortByGenere = new JMenuItem("Genere");
		sortByTitolare = new JMenuItem("Titolare");
		sortByPrezzo = new JMenuItem("Prezzo");
		sort.add(sortByGenere);
		sort.add(sortByTitolare);
		sort.add(sortByPrezzo);
		
		edit.add(sort);
		edit.add(add);
		menuBar.add(file);
		menuBar.add(edit);
		
		logout.addActionListener(menuListener);
		exit.addActionListener(menuListener);
		sortByGenere.addActionListener(menuListener);
		sortByPrezzo.addActionListener(menuListener);
		sortByTitolare.addActionListener(menuListener);
		addDisco.addActionListener(menuListener);
		addMusicista.addActionListener(menuListener);
		
		
		tabella = new ViewTableFactory().getTable(ViewTableFactory.ADMIN_TABLE);
	 
		List<OccorrenzeDisco> dischi = magazzino.getCatalogo();
		TableModel model = new ModelViewTable(magazzino);
		
	    tabella.setBounds(30,40,300,300);
	    tabella.setModel(model);
	    tabella.addMouseListener(listener);
	    tabella.setRowHeight(50);
	    
	    updateTable(magazzino.getCatalogo());
	   	        
	    JScrollPane sp=new JScrollPane(tabella);
	    
	    filtro = new JTextField();
	    filtro.setColumns(50);
	    
	    btnCerca = new JButton("Cerca");
	    genereRadio = new JRadioButton("Genere");
	    titolareRadio = new JRadioButton("Titolare");
	    partecipanteRadio = new JRadioButton("Partecipante");
	    prezzoRadio = new JRadioButton("Prezzo");
	    	    
	    ButtonGroup group = new ButtonGroup();
	    group.add(genereRadio);
	    group.add(titolareRadio);
	    group.add(partecipanteRadio);
	    group.add(prezzoRadio);
	    
	    genereRadio.setSelected(true);
	    
	    btnAnnulla = new JButton("Annulla");
	    
	    FilterListener filterListener = new FilterListener(magazzino, this);
	    btnCerca.addActionListener(filterListener);	    
	    btnAnnulla.addActionListener(filterListener);
	
	    GroupLayout groupLayout = new GroupLayout(getContentPane());
	    groupLayout.setHorizontalGroup(
	    	groupLayout.createParallelGroup(Alignment.LEADING)
	    		.addGroup(groupLayout.createSequentialGroup()
	    			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
	    				.addGroup(groupLayout.createSequentialGroup()
	    					.addContainerGap()
	    					.addComponent(sp, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE))
	    				.addGroup(groupLayout.createSequentialGroup()
	    					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
	    						.addGroup(groupLayout.createSequentialGroup()
	    							.addGap(20)
	    							.addComponent(btnCerca)
	    							.addGap(18)
	    							.addComponent(filtro, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE))
	    						.addGroup(groupLayout.createSequentialGroup()
	    							.addGap(133)
	    							.addComponent(btnAnnulla)))
	    					.addGap(52)
	    					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
	    						.addComponent(genereRadio)
	    						.addComponent(titolareRadio))
	    					.addGap(26)
	    					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
	    						.addComponent(prezzoRadio)
	    						.addComponent(partecipanteRadio))))
	    			.addContainerGap())
	    );
	    groupLayout.setVerticalGroup(
	    	groupLayout.createParallelGroup(Alignment.LEADING)
	    		.addGroup(groupLayout.createSequentialGroup()
	    			.addGap(35)
	    			.addComponent(sp, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE)
	    			.addGap(48)
	    			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(filtro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(genereRadio)
	    				.addComponent(btnCerca)
	    				.addComponent(partecipanteRadio))
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(titolareRadio)
	    				.addComponent(prezzoRadio)
	    				.addComponent(btnAnnulla))
	    			.addContainerGap(40, Short.MAX_VALUE))
	    );
	    getContentPane().setLayout(groupLayout);
		
		this.setJMenuBar(menuBar);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		
	}
	
	@Override
	public void updateTable(List<OccorrenzeDisco> elementi){
				
		ModelViewTable model = new ModelViewTable(elementi);
		tabella.setModel(model);
		tabella.removeMouseListener(listener);
		listener = new RowListener(this, elementi, magazzino, admin, null);
		tabella.addMouseListener(listener);
		
	}

	@Override
	public boolean isForGenere() {
		return genereRadio.isSelected();
	}

	@Override
	public boolean isForPartecipante() {
		return partecipanteRadio.isSelected();
	}

	@Override
	public boolean isForPrezzo() {
		return prezzoRadio.isSelected();
	}

	@Override
	public boolean isForTitolare() {
		return titolareRadio.isSelected();
	}

	@Override
	public String getFilter() {
		return filtro.getText();
	}
	
	@Override
	public void resetFilter(){
		filtro.setText("");
	}
}