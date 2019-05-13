package mats;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.ScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
/*
Grensesnitt laget med windowbuilder i Eclipse
*/

public class VisOrdreVindu extends JDialog {
	
	DefaultTableModel ordreInnhold;
	private final JPanel contentPanel = new JPanel();
	private JTextField soktxt;
	private JTable table;
	private JScrollPane scrollpane;
	private String [] kolonneNavn = {"Ordrenummer", "Varenummer", "Pris per enhet", "Antall"};
	private Object[][] tabell = {{null, null, null, null},{null,null,null,null}};
	private JTextArea tekstOmraade;
	
	/**
	 * Create the dialog.
	 */
	public VisOrdreVindu(JFrame Grensesnitt) {
		super(Grensesnitt, "Ordrevindu", true);
		
		setBounds(100, 100, 694, 409);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		soktxt = new JTextField();
		soktxt.setBounds(176, 19, 130, 26);
		contentPanel.add(soktxt);
		soktxt.setColumns(10);
		
		JLabel soklbl = new JLabel("Søk på ordrenummer:");
		soklbl.setBounds(6, 24, 158, 16);
		contentPanel.add(soklbl);
		
		JButton sokbtn = new JButton("Søk");
		sokbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputOrdreNr = soktxt.getText();
				Grensesnitt grensesnitt = new Grensesnitt();
				if(inputOrdreNr.equals("")) {
					try {
						lagTabell(grensesnitt.sokOrdre(0));
						tekstOmraade.setText("");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					int n = Integer.parseInt(inputOrdreNr);
					try {
						lagTabell(grensesnitt.sokOrdre(n));
						lagTextArea(grensesnitt.sokKundeInfo(n));
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
			}
		});
		sokbtn.setBounds(318, 19, 64, 29);
		contentPanel.add(sokbtn);
		
		tekstOmraade = new JTextArea();
		tekstOmraade.setEditable(false);
		tekstOmraade.setBounds(391, 47, 232, 88);
		contentPanel.add(tekstOmraade);
		
		ordreInnhold = new DefaultTableModel(tabell, kolonneNavn);
		table = new JTable(ordreInnhold);
		table.setBounds(10,200,200,-200);
		
		scrollpane = new JScrollPane(table);
		scrollpane.setBounds(16, 46, 366, 292);
		contentPanel.add(scrollpane);
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
	
	public void visOrdreVinduKontroll(ResultSet resultat) {
		try {
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			lagTabell(resultat);
			setVisible(true);
			tekstOmraade.setText("");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void lagTabell(ResultSet resultat) throws SQLException {
		Object[][] liste = lagListe(resultat);
		table.setModel(new DefaultTableModel(liste, kolonneNavn));
	}
	
	public Object[][] lagListe(ResultSet resultat) throws SQLException{
		int size = 0;
		int cnt = 0;
		if (resultat != null) 
		{
		  resultat.last();
		  size = resultat.getRow();
		  resultat.beforeFirst();
		}
		Object[][] innhold = new Object[size][4];
		while(resultat.next()) {
			String ordreNummer = resultat.getString(1);
			String vareNummer = resultat.getString(2);
			String prisPerEnhet = resultat.getString(3);
			String antall = resultat.getString(4);
			innhold[cnt][0] = ordreNummer;
			innhold[cnt][1] = vareNummer;
			innhold[cnt][2] = prisPerEnhet;
			innhold[cnt][3] = antall;
			cnt++;
		}
		return innhold;
		
	}
	
	public void lagTextArea(ResultSet resultat) throws SQLException {
		resultat.next();
		String dato = resultat.getString(1);
		String kundeNummer = resultat.getString(2);
		String fornavn = resultat.getString(3);
		String etternavn = resultat.getString(4);
		
		String inputTekst = ("Dato: " + dato + "\n" + "kundenumme: " + kundeNummer + "\n" + "Fornavn: " + fornavn + "\n" + "Etternavn: " + etternavn);
		
		tekstOmraade.setText(inputTekst);
		
	}

	
}
