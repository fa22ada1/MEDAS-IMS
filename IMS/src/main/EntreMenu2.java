package main;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import entities.Utilisateur;
import utils.Conn;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;

public class EntreMenu2 {
	Display display;
	Shell shell;
	Shell shell1;
	Utilisateur agent;
	int PN;
	
	private Text text;
	private Text text_1;

	public EntreMenu2(Display D, Shell shell,int PN ,Utilisateur agent) {
		display = D;
		shell = new Shell(display, SWT.CLOSE | SWT.TITLE);

		this.agent = agent;

		shell.setSize(700, 590);
		shell.setLocation(480, 300);
		shell.setImage(new Image(display, "src\\icons\\MEDASIMS_LOGO.ico"));
		shell.setText("Menu declaration d'achat");
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
		
		Label lblDeclarerEntreDune = new Label(composite, SWT.NONE);
		lblDeclarerEntreDune.setText("Declarer entre d'une piece");
		new Label(composite, SWT.NONE);
		
		Label Error = new Label(composite, SWT.NONE);
		Error.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setText("Nombre");
		new Label(composite, SWT.NONE);
		
		Spinner spinner = new Spinner(composite, SWT.BORDER);
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setText("Fournisseur");
		new Label(composite, SWT.NONE);
		
		text = new Text(composite, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		Label lblLesNombreDe = new Label(composite, SWT.NONE);
		lblLesNombreDe.setText("les nombre de serie");
		new Label(composite, SWT.NONE);
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label label = new Label(composite, SWT.NONE);
		label.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, true, false, 1, 1));
		label.setText("(veuillez separer les NS par des \";\" si plusieurs pieces)");
		label.setFont(SWTResourceManager.getFont("Segoe UI", 7, SWT.NORMAL));
		
		Label lblNote = new Label(composite, SWT.NONE);
		lblNote.setText("Note");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		StyledText styledText = new StyledText(composite, SWT.BORDER);
		GridData gd_styledText = new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1);
		gd_styledText.widthHint = 633;
		styledText.setLayoutData(gd_styledText);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		composite_1.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, false, false, 1, 1));
		
		Button btnAnnuler = new Button(composite_1, SWT.CENTER);
		btnAnnuler.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.setText("");
				text_1.setText("");
				spinner.setSelection(0);
				styledText.setText("");
				shell.setVisible(false);
			}
		});
		btnAnnuler.setText("annuler");
		
		Button btnConfirmer = new Button(composite_1, SWT.NONE);
		btnAnnuler.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(text.getText().trim().contentEquals("") | spinner.getText().trim().contentEquals("") | text_1.getText().trim().contentEquals("")) {
					Error.setText("veuillez remplir toutes les cases");
					return;
				}
				int N = spinner.getSelection();
				ArrayList<String> four = new ArrayList<String>();
				try {
					Conn conn = new Conn();
					ResultSet rs;
					String q = "Select * from Fournisseur";
					rs = conn.s.executeQuery(q);
					while(rs.next()) {
						four.add(rs.getString("label"));
					}
				} catch(Exception E) {
		    		E.printStackTrace();
		    	}
				
			}			
		});
		btnConfirmer.setText("confirmer");
	}
}