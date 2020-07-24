package main;

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
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import entities.Utilisateur;
import utils.Conn;
import utils.Utils;

public class EntreMenu {
	Display display;
	Shell shell;
	private Utilisateur agent;

	private Text text;
	private Text text_1;
	private Text text_2;
	private Boolean New = false;

	public EntreMenu(Display D, Utilisateur agent) {
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

		Button btnRadioButton = new Button(composite, SWT.RADIO);
		btnRadioButton.setText("Produit existant");
		new Label(composite, SWT.NONE);

		Label Error1 = new Label(composite, SWT.NONE);
		Error1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		Error1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));

		Label lbl2 = new Label(composite, SWT.NONE);
		lbl2.setText("Nombre de produit");
		new Label(composite, SWT.NONE);

		Combo combo = new Combo(composite, SWT.READ_ONLY);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		Utils.PNCombo(combo);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);

		Button btnRadioButton_1 = new Button(composite, SWT.RADIO);
		btnRadioButton_1.setText("Nouveau produit");
		new Label(composite, SWT.NONE);

		Label Error2 = new Label(composite, SWT.NONE);
		Error2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		Error2.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));

		Label lbl4 = new Label(composite, SWT.NONE);
		lbl4.setText("Type de produit");
		new Label(composite, SWT.NONE);

		text = new Text(composite, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lbl5 = new Label(composite, SWT.NONE);
		lbl5.setText("Marque");
		new Label(composite, SWT.NONE);

		text_1 = new Text(composite, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lbl6 = new Label(composite, SWT.NONE);
		lbl6.setText("Nombre de produit");
		new Label(composite, SWT.NONE);

		text_2 = new Text(composite, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lbl8 = new Label(composite, SWT.NONE);
		lbl8.setText("Note");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);

		StyledText styledText = new StyledText(composite, SWT.BORDER);
		styledText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 2));
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		text.addTraverseListener(new TraverseListener() {
			  @Override
			  public void keyTraversed(TraverseEvent event) {
			    if (event.detail == SWT.TRAVERSE_RETURN) {
			      event.doit = false;
			      text_1.setFocus();
			    }
			  }
		});
		
		text_1.addTraverseListener(new TraverseListener() {
			  @Override
			  public void keyTraversed(TraverseEvent event) {
			    if (event.detail == SWT.TRAVERSE_RETURN) {
			      event.doit = false;
			      text_2.setFocus();
			    }
			  }
		});
		
		text_2.addTraverseListener(new TraverseListener() {
			  @Override
			  public void keyTraversed(TraverseEvent event) {
			    if (event.detail == SWT.TRAVERSE_RETURN) {
			      event.doit = false;
			      styledText.setFocus();
			    }
			  }
		});

		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, false, false, 1, 1));
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));

		Button btnAnnuler = new Button(composite_1, SWT.NONE);
		btnAnnuler.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				combo.setText("");
				styledText.setText("");
				text.setText("");
				text_1.setText("");
				text_2.setText("");
				shell.setVisible(false);
			}
		});
		btnAnnuler.setText("annuler");

		Button btnSuivant = new Button(composite_1, SWT.NONE);
		btnSuivant.setText("suivant");
		btnSuivant.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int PN = 0;
				try {
					Conn conn = new Conn();
					if (New) {
						if (text.getText().trim().contentEquals("") | text_1.getText().trim().contentEquals("")
								| text_1.getText().trim().contentEquals("")) {
							Error2.setText("veuillez remplir toutes les cases");
							return;
						}
						try {
							Long.parseLong(text_2.getText());
						} catch (NumberFormatException ex) {
							Error1.setText("veuillez entrer des nombres dans les cases des nombres");
							return;
						}
						String q1 = "INSERT INTO Prod (Prodnom, Marque, PN, note) VALUES ('"
								+ text.getText().toUpperCase() + "','" + text_1.getText().toUpperCase() + "','"
								+ text_2.getText() + "','" + styledText.getText() + "');";
						conn.s.executeUpdate(q1);

						PN = Integer.parseInt(text_2.getText());
					} else {
						if (combo.getText().trim().contentEquals("")) {
							Error1.setText("veuillez choisir une valeur");
							return;
						}
						PN = Integer.parseInt(combo.getText());
					}
					conn.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				shell.setVisible(false);
				new EntreMenu2(display, shell, PN, agent);
				text.setText("");
				text_1.setText("");
				text_2.setText("");
				styledText.setText("");
			}
		});

		btnRadioButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				New = false;
				Error2.setText("");
				combo.setEnabled(true);
				text.setEnabled(false);
				text_1.setEnabled(false);
				text_2.setEnabled(false);
				styledText.setEnabled(false);
			}
		});

		btnRadioButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				New = true;
				Error1.setText("");
				combo.setEnabled(false);
				text.setEnabled(true);
				text_1.setEnabled(true);
				text_2.setEnabled(true);
				styledText.setEnabled(true);
			}
		});
	}
}
