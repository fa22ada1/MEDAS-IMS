package main;

import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

import entities.Produit;

public class TechnicalSheet {
	Display display;
	Shell shell;
	private Produit P;

	public TechnicalSheet(Display D, int ID) {
		display = D;
		shell = new Shell(display, SWT.CLOSE | SWT.TITLE);
		P = new Produit(ID);
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
		
		Label lblFicheTechnique = new Label(composite, SWT.NONE);
		lblFicheTechnique.setText("Fiche technique");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label lblProduit = new Label(composite, SWT.NONE);
		lblProduit.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		lblProduit.setText("Produit");
		new Label(composite, SWT.NONE);
		
		Label lblProduit1 = new Label(composite, SWT.NONE);
		lblProduit1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		lblProduit1.setText(P.getType());
		
		Label lblMarque = new Label(composite, SWT.NONE);
		lblMarque.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		lblMarque.setText("Marque");
		new Label(composite, SWT.NONE);
		
		Label lblMarque1 = new Label(composite, SWT.NONE);
		lblMarque1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		lblMarque1.setText(P.getMarque());
		
		Label lblNombreDeProduit = new Label(composite, SWT.NONE);
		lblNombreDeProduit.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		lblNombreDeProduit.setText("Nombre de Produit");
		new Label(composite, SWT.NONE);
		
		Label lblNombreDeProduit1 = new Label(composite, SWT.NONE);
		lblNombreDeProduit1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		lblNombreDeProduit1.setText(Integer.toString(P.getPN()));
		
		Label lblStock = new Label(composite, SWT.NONE);
		lblStock.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		lblStock.setText("Quantite en stock");
		new Label(composite, SWT.NONE);
		
		Label lblStock1 = new Label(composite, SWT.NONE);
		lblStock1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		lblStock1.setText(Integer.toString(P.getStock()));
		
		Label lblnum = new Label(composite, SWT.NONE);
		lblnum.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		lblnum.setText("Nombres de serie");
		new Label(composite, SWT.NONE);
		
		List list = new List(composite, SWT.BORDER | SWT.V_SCROLL);
		list.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		list.setEnabled(false);
		
		String[] SNs = Produit.getSNs(P.getID());
		for (int i = 0; i < SNs.length; i++) {
		      list.add(SNs[i]);
		}
		
		Label lblNote = new Label(composite, SWT.NONE);
		lblNote.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		lblNote.setText("Note");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		StyledText styledText = new StyledText(composite, SWT.BORDER);
		styledText.setEditable(false);
		styledText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));
		styledText.setText(P.getNote());
	}
}