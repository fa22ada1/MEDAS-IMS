package main;

import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import entities.Utilisateur;
import utils.Utils;

public class Login {
	Display display;
	Shell shell;
	
	private Text text;
	private Text text_1;
	private Label lblComment;

	private Login() {
		display = new Display();
		shell = new Shell(display, SWT.NO_TRIM);

		shell.setSize(800, 445);
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
		parent.setBackground(Utils.PC1);
		parent.setLayout(new FormLayout());

		Canvas canvas = new Canvas(parent, SWT.BORDER);
		canvas.setBackground(Utils.PC1);
		canvas.setLayout(null);
		FormData fd_canvas = new FormData();
		fd_canvas.top = new FormAttachment(0, 28);
		fd_canvas.right = new FormAttachment(100, -24);
		canvas.setLayoutData(fd_canvas);

		Label lblMedasims = new Label(canvas, SWT.CENTER);
		lblMedasims.setBackground(Utils.PC1);
		lblMedasims.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lblMedasims.setFont(SWTResourceManager.getFont("Tw Cen MT", 14, SWT.BOLD));
		lblMedasims.setBounds(0, 69, 427, 39);
		lblMedasims.setText("MEDAS-IMS");

		text = new Text(canvas, SWT.NONE);
		text.setBounds(91, 153, 244, 31);

		text_1 = new Text(canvas, SWT.PASSWORD);
		text_1.setBounds(91, 214, 244, 31);
		
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
			      LOGIN();
			    }
			  }
		});
		
		CLabel lblNewLabel = new CLabel(parent, SWT.NONE);
		fd_canvas.left = new FormAttachment(lblNewLabel, 28);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.top = new FormAttachment(0, 67);
		fd_lblNewLabel.left = new FormAttachment(0, 10);
		lblNewLabel.setLayoutData(fd_lblNewLabel);

		Button btnLogin = new Button(canvas, SWT.NONE);
		btnLogin.setBounds(91, 289, 105, 35);
		btnLogin.setText("Confirmer");

		Button btnAnnuler = new Button(canvas, SWT.NONE);
		btnAnnuler.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
				display.dispose();
			}
		});
		btnAnnuler.setText("Annuler");
		btnAnnuler.setBounds(230, 289, 105, 35);
		
		lblNewLabel.setBackground(Utils.PC1);
		lblNewLabel.setImage(SWTResourceManager.getImage(Login.class, "/icons/medasims2.jpg"));
		lblComment = new Label(parent, SWT.NONE);
		fd_canvas.bottom = new FormAttachment(100, -27);
		lblComment.setAlignment(SWT.RIGHT);
		lblComment.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblComment.setFont(SWTResourceManager.getFont("Segoe UI", 6, SWT.NORMAL));
		lblComment.setBackground(Utils.PC1);
		FormData fd_lblComment = new FormData();
		fd_lblComment.top = new FormAttachment(canvas, 1);
		fd_lblComment.left = new FormAttachment(0, 415);
		fd_lblComment.right = new FormAttachment(100, -24);
		lblComment.setLayoutData(fd_lblComment);
		lblComment.setText("veuillez entrer vos informations ...");	
		

		btnLogin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LOGIN();
			}
		});
	}
	
	private void LOGIN() {
		lblComment.setText("confirmation ...");	
		if(!text.getText().trim().equals("") & !text_1.getText().trim().equals("") ) {
			int ID = Utilisateur.getID(text.getText().trim(), text_1.getText());
			if (ID == -1) {
				lblComment.setText("informations incorrectes");
			} else {
				lblComment.setText("informations correctes");
				Utilisateur agent = new Utilisateur(ID);
				shell.setVisible(false);
				new MainWindow(display, agent);
			}
		} else {
			lblComment.setText("informations incompletes");
		}
	}

	public static void main(String[] args) {
		new Login();
	}
}
