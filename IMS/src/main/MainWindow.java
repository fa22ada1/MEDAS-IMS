package main;

import java.sql.ResultSet;

import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import entities.Produit;
import entities.Utilisateur;
import utils.Conn;
import utils.Utils;

public class MainWindow {
	Display display;
	Shell shell;
	Utilisateur agent;
	private Table table;
	private Table table_1;
	
	Menu menuBar, fileMenu, newMenu;
	MenuItem fileMenuHeader, fileRefreshItem, newMenuHeader;
	MenuItem fileExitItem, newEntreItem, newSortieItem;
	
	public MainWindow(Display display, Utilisateur agent) {
		this.display = display;
		this.agent = agent;
		
		shell = new Shell(display);
		shell.setImage(new Image(display, "src\\icons\\MEDASIMS_LOGO.ico"));
		shell.setText("Menu declaration d'achat");
		createMenuBar();
		create(shell);
		
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	@PostConstruct
	private void create(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		parent.setBackground(Utils.PC1);
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackground(Utils.PC1);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		lblNewLabel_2.setBackground(Utils.PC1);
		lblNewLabel_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_2.setFont(SWTResourceManager.getFont("Tw Cen MT", 14, SWT.BOLD));
		lblNewLabel_2.setText("MEDASIMS");
		
		Label label = new Label(composite, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setBackground(Utils.PC1);
		label.setAlignment(SWT.RIGHT);
		label.setText(agent.Tag());
		
		Composite composite_1 = new Composite(parent, SWT.NONE);
		composite_1.setLayout(new GridLayout(1, false));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		lblNewLabel.setText("  Stock courant");
		
		table = new Table(composite_1, SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setTouchEnabled(true);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		
		Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		lblNewLabel_1.setText("  Activit\u00E9s r\u00E9centes");
		
		table_1 = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table_1.setHeaderVisible(true);
		
		setTables(table, table_1);
	}
	
	private void setTables(Table table, Table table_1) {
		table.removeAll();
		while ( table.getColumnCount() > 0 ) {
		    table.getColumns()[ 0 ].dispose();
		}
		String[] titles = { "Produit", "Marque", "NP", "Stock", "..."};
	    for (int i = 0; i < titles.length; i++) {
	      TableColumn column = new TableColumn(table, SWT.NONE);
	      column.setWidth(200);
	      column.setText(titles[i]);
	    }
	    int[] IDs = Produit.allIDs();
	    Button buttons[] = new Button[IDs.length];
	    int j=0;
	    for(int ID : IDs) {
	    	Produit P = new Produit(ID);
	    	TableItem item = new TableItem(table,SWT.NONE);
	    	item.setText(new String[]{P.getType() ,P.getMarque() ,Integer.toString(P.getPN()) ,Integer.toString(P.getStock())});
	    	
	    	TableEditor editor = new TableEditor(table);
	    	Button button = new Button(table, SWT.PUSH);
	    	button.setSize(new Point(30,30));
	        editor.minimumWidth = button.getSize().x;
	        button.pack();
	        editor.horizontalAlignment = SWT.LEFT;
	        editor.setEditor(button, item, 4);
	        
	        buttons[j] = button;
	        j++;
	        
	        Utils.buttonF(display, buttons, ID);
	    }
	    table_1.removeAll();
	    while ( table_1.getColumnCount() > 0 ) {
		    table_1.getColumns()[ 0 ].dispose();
		}
		String[] titles1 = { "Type", "NP", "Nombre", "Agent", "Date"};
	    for (int i = 0; i < titles1.length; i++) {
	      TableColumn column = new TableColumn(table_1, SWT.NONE);
	      column.setWidth(200);
	      column.setText(titles1[i]);
	    }
	    try {
	    	Conn conn = new Conn();	 
	    	ResultSet rs;
	    	String q = "Select prodid, agentid, num, dateachat as date, 'achat' as type "
	    					+ "from entre "
	    					+ "UNION "
	    					+ "Select prodid, agentid, num, datesortie, label "
	    					+ "from sortie;";
	    	rs = conn.s.executeQuery(q);
	    	while(rs.next()) {
	    		TableItem item = new TableItem(table_1,SWT.NONE);
	    		String type = rs.getString("type");
	    		item.setText(new String[]{type ,Integer.toString(Produit.PNfromID(rs.getInt("prodid"))) ,Integer.toString(rs.getInt("num")) ,Utilisateur.TagfromID(rs.getInt("agentID")), rs.getString("date")});
	    		item.setForeground(0, SWTResourceManager.getColor(SWT.COLOR_WHITE));
	    		if(type.equals("achat")) {
	    			item.setBackground(0, Utils.PC1);
	    		} else {
	    			item.setBackground(0, Utils.PC2);
	    		}
	    	}
	    	rs.close();
			conn.close();
	    } catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void createMenuBar() {
		menuBar = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menuBar);
		
	    fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
	    fileMenuHeader.setText("&Fichier");
	    fileMenu = new Menu(shell, SWT.DROP_DOWN);
	    fileMenuHeader.setMenu(fileMenu);
	    
	    fileExitItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileExitItem.setText("&Sortir");
	    
	    fileRefreshItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileRefreshItem.setText("&Actualiser");
	    
	    fileExitItem.addSelectionListener(new fileExitItemListener());
	    fileRefreshItem.addSelectionListener(new fileRefreshItemListener());
	    
	    newMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
	    newMenuHeader.setText("&Nouveau");
	    newMenu = new Menu(shell, SWT.DROP_DOWN);
	    newMenuHeader.setMenu(newMenu);
	    
	    newEntreItem = new MenuItem(newMenu, SWT.PUSH);
	    newEntreItem.setText("&D\u00E9clarer une entr\u00E9 de produit");
	    
	    newSortieItem = new MenuItem(newMenu, SWT.PUSH);
	    newSortieItem.setText("&D\u00E9clarer une sortie de produit");
	    
	    newEntreItem.addSelectionListener(new newEntreItemListener());
	    newSortieItem.addSelectionListener(new newSortieItemListener());
	}
	
	class fileExitItemListener implements SelectionListener {
	    public void widgetSelected(SelectionEvent event) {
	    	shell.dispose();
	    }
		public void widgetDefaultSelected(SelectionEvent event) {
			shell.dispose();
		}
	}
	class fileRefreshItemListener implements SelectionListener {
	    public void widgetSelected(SelectionEvent event) {
	    	setTables(table, table_1);
	    }
		public void widgetDefaultSelected(SelectionEvent event) {
			setTables(table, table_1);
		}
	}
	class newEntreItemListener implements SelectionListener {
	    public void widgetSelected(SelectionEvent event) {
	    	new EntreMenu(display, agent);
	    }
		public void widgetDefaultSelected(SelectionEvent event) {
			new EntreMenu(display, agent);
		}
	}
	class newSortieItemListener implements SelectionListener {
	    public void widgetSelected(SelectionEvent event) {
	    	new SortieMenu(display, agent);
	    }
		public void widgetDefaultSelected(SelectionEvent event) {
			new SortieMenu(display, agent);
		}
	}
}
