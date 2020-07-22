package main;

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

import entities.Produit;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.custom.StyledText;

public class TechnicalSheet {
	Display display;
	Shell shell;
	Produit P;

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
		lblProduit.setText("Produit");
		new Label(composite, SWT.NONE);
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		lblNewLabel_1.setText(P.getType());
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setText("Marque");
		new Label(composite, SWT.NONE);
		
		Label label = new Label(composite, SWT.NONE);
		label.setText(P.getMarque());
		
		Label lblNombreDeProduit = new Label(composite, SWT.NONE);
		lblNombreDeProduit.setText("Nombre de Produit");
		new Label(composite, SWT.NONE);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setText(Integer.toString(P.getPN()));
		
		Label lblQuantiteEnStock = new Label(composite, SWT.NONE);
		lblQuantiteEnStock.setText("Quantite en stock");
		new Label(composite, SWT.NONE);
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setText(Integer.toString(P.getStock()));
		
		Label lblLesNombresDe = new Label(composite, SWT.NONE);
		lblLesNombresDe.setText("Les Nombres de serie en stock");
		new Label(composite, SWT.NONE);
		
		List list = new List(composite, SWT.BORDER | SWT.V_SCROLL);
		list.setEnabled(false);
		list.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 3));
		
		String[] SNs = Produit.getSNs(P.getID());
		for (int i = 0; i < SNs.length; i++) {
		      list.add(SNs[i]);
		}
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label lblNote = new Label(composite, SWT.NONE);
		lblNote.setText("Note");
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		StyledText styledText = new StyledText(composite, SWT.BORDER);
		styledText.setEnabled(false);
		styledText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));
		styledText.setText(P.getNote());
	}
}