package main;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;

import utils.Conn;
import utils.Utils;

import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import entities.Utilisateur;

public class SortieMenu {
	Display display;
	Shell shell;
	private Utilisateur agent;
	
	private Text text;
	
	public SortieMenu(Display D, Utilisateur agent) {
		display = D;
		shell = new Shell(display, SWT.CLOSE | SWT.TITLE);
		
		this.agent = agent;
		
		shell.setSize(700,590);
        shell.setLocation(480,300);
		shell.setImage(new Image(display, "src\\icons\\MEDASIMS_LOGO.ico"));
        shell.setText("Menu declaration de sortie de produit");
		create(shell);
		
		shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
	}

	@PostConstruct
	private void create(Composite parent) {
		parent.setLayout(new FormLayout());
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));
		FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(0, 520);
		fd_composite.right = new FormAttachment(0, 670);
		fd_composite.top = new FormAttachment(0, 30);
		fd_composite.left = new FormAttachment(0, 30);
		composite.setLayoutData(fd_composite);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setText("Declarer sortie d'une piece");
		new Label(composite, SWT.NONE);
		
		Label Error = new Label(composite, SWT.NONE);
		Error.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		Error.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setText("Nombre de produit");
		new Label(composite, SWT.NONE);
		
		Combo combo = new Combo(composite, SWT.READ_ONLY);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		Utils.PNCombo(combo);
		
		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		lblNewLabel_2.setText("Cause");
		new Label(composite, SWT.NONE);
		
		Combo combo_1 = new Combo(composite, SWT.READ_ONLY);
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		String[] items = new String[] { "Utilisé","Vendu","Cassé","Perdu" };
		combo_1.setItems(items);
		
		Label lblNewLabel_3 = new Label(composite, SWT.NONE);
		lblNewLabel_3.setText("Nombre de pieces");
		new Label(composite, SWT.NONE);
		
		Spinner spinner = new Spinner(composite, SWT.BORDER);
		
		Label lblNewLabel_5 = new Label(composite, SWT.NONE);
		lblNewLabel_5.setText("Nombre de serie");
		new Label(composite, SWT.NONE);
		
		text = new Text(composite, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label label = new Label(composite, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("(veuillez separer les NS par des \";\" si plusieurs pieces)");
		label.setFont(SWTResourceManager.getFont("Segoe UI", 7, SWT.NORMAL));
		
		Label lblNewLabel_4 = new Label(composite, SWT.NONE);
		lblNewLabel_4.setText("Note");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		StyledText styledText = new StyledText(composite, SWT.BORDER);
		styledText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		text.addTraverseListener(new TraverseListener() {
			  @Override
			  public void keyTraversed(TraverseEvent event) {
			    if (event.detail == SWT.TRAVERSE_RETURN) {
			      event.doit = false;
			      styledText.setFocus();
			    }
			  }
		});
		
		Composite composite_2 = new Composite(composite, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		composite_2.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, false, false, 1, 1));
		
		Button btnAnnuler = new Button(composite_2, SWT.NONE);
		btnAnnuler.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				combo.setText("");
				combo_1.setText("");
				spinner.setSelection(0);
				styledText.setText("");
				shell.setVisible(false);
			}
		});
		btnAnnuler.setText("annuler");
		
		Button btnConfirmer = new Button(composite_2, SWT.NONE);
		btnConfirmer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(text.getText().trim().contentEquals("") | combo.getText().trim().contentEquals("") | combo_1.getText().trim().contentEquals("")) {
					Error.setText("veuillez remplir toutes les cases");
					return;
				}
				
				int PN = Integer.parseInt( combo.getText() );
				String cause = combo_1.getText();
				int N = spinner.getSelection();
				String note = styledText.getText();
				
				String[] SNs = text.getText().trim().split(";");
				if(SNs.length != N) {
					Error.setText("veuillez verifier le nombre de SNs");
					return;
				}
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDateTime now = LocalDateTime.now();
				
				try {
					Conn conn = new Conn();
					ResultSet rs, rs1;
					
					String q = "Select ID from Prod where PN =" + PN;
					rs = conn.s.executeQuery(q);
					rs.next();
					int ProdId = rs.getInt("Id");
					
					q = " insert into sortie (label, ProdID, num, Datesortie, agentid, note) values "
							+ "('"+ cause +"',"+ ProdId +","+ N +",'"+ dtf.format(now) +"',"+ agent.getID() +",'"+ note +"');";
					conn.s.executeUpdate(q);
					rs.close();
					
					q = "Select LAST_INSERT_ID();";
					rs1 = conn.s.executeQuery(q);
					rs1.next();
					int I = rs1.getInt(1);
					
					
					q = "update Prod set Stock = Stock - " +N+ " Where Id =" +ProdId+ ";";
					conn.s.executeUpdate(q);
					
					for(String SN : SNs) {
						q = "update Produit set idsortie = " + I + " Where SN =" + SN + ";";
						conn.s.executeUpdate(q);
					}
					rs.close();
					conn.close();
				} catch(Exception e1){
					e1.printStackTrace();
				}
				shell.dispose();
			}
		});
		btnConfirmer.setText("confirmer");
	}
}
