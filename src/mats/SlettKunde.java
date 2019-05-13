package mats;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

/*
Grensesnitt laget med windowbuilder i Eclipse
*/

public class SlettKunde extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField sokinputtxtfield;
	private JTextArea tekstBoks;
	private JButton slett;
	private String kundenummer;
	private int kundeInt;

	/**
	 * Create the dialog.
	 */
	public SlettKunde(JFrame Grensesnitt) {
		super(Grensesnitt, "Slett kunde", true);
		setBounds(100, 100, 409, 194);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel kundeNrlbl = new JLabel("Kundenummer:");
			kundeNrlbl.setBounds(6, 24, 105, 16);
			contentPanel.add(kundeNrlbl);
		}
		{
			sokinputtxtfield = new JTextField();
			sokinputtxtfield.setBounds(123, 19, 130, 26);
			contentPanel.add(sokinputtxtfield);
			sokinputtxtfield.setColumns(10);
		}
		{
			JButton sokbtn = new JButton("Søk");
			sokbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String kundenummer = sokinputtxtfield.getText();
					Grensesnitt grensesnitt = new Grensesnitt();
					if(!kundenummer.equals("")) {
						kundeInt = Integer.parseInt(kundenummer);
						try {
							lagTextField(grensesnitt.sokKunde(kundeInt));
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else {
						slett.setEnabled(false);
						tekstBoks.setText("");
					}
					
					
				}
			});
			sokbtn.setBounds(265, 19, 117, 29);
			contentPanel.add(sokbtn);
		}
		{
			slett = new JButton("Slett");
			slett.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Grensesnitt grensesnitt = new Grensesnitt();
					try {
						grensesnitt.slett(kundeInt);
						JOptionPane.showMessageDialog(null, "Kunde slettet");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			slett.setEnabled(false);
			slett.setBounds(136, 98, 117, 29);
			contentPanel.add(slett);
		}
		{
			tekstBoks = new JTextArea();
			tekstBoks.setEditable(false);
			tekstBoks.setBounds(16, 52, 356, 44);
			contentPanel.add(tekstBoks);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton tilbakebtn = new JButton("Tilbake");
				tilbakebtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				tilbakebtn.setActionCommand("Cancel");
				buttonPane.add(tilbakebtn);
			}
		}
	}
	
	public void slettKundeKontroll() {
		try {
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			setVisible(true);
			tekstBoks.setText("");
			sokinputtxtfield.setText("");
			slett.setEnabled(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void lagTextField(ResultSet resultat) throws SQLException {
		if(resultat.next()) {
			String fornavn = resultat.getString(1);
			String etternavn = resultat.getString(2);
			String adresse = resultat.getString(3);
			
			String inputText = ("Fornavn: " + fornavn + " Etternavn: " + etternavn + "\n" + "adresse: " + adresse);
			tekstBoks.setText(inputText);
			slett.setEnabled(true);
		}
		else {
			tekstBoks.setText("Finner ikke kunde på oppgitt kundenummer");
		}

		
	}

}
