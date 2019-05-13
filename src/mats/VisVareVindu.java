package mats;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.ScrollPane;
import java.awt.Dimension;
/*
Grensesnitt laget med windowbuilder i Eclipse
*/

public class VisVareVindu extends JDialog {
	DefaultTableModel vareInnhold;
	private final String[] kolonneNavn = {"Varenummer", "Betegnelse", "Pris", "Kategorinavn", "Kategorinummer", "Antall", "Hylle"};
	private final Object[][] tabell = new Object[][] {{null,null,null,null,null,null,null},{null,null,null,null,null,null,null}};
	private JTable table;
	private JScrollPane scrollpane;
	
	public VisVareVindu(JFrame Grensesnitt) {
		super(Grensesnitt, "Vareliste", true);
		
		setBounds(100, 100, 589, 301);
		getContentPane().setLayout(null);
		{
			JButton tilbakeBtn = new JButton("Tilbake");
			tilbakeBtn.setBounds(493, 244, 90, 29);
			getContentPane().add(tilbakeBtn);
			tilbakeBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
			tilbakeBtn.setActionCommand("Cancel");
		}
		vareInnhold = new DefaultTableModel(tabell, kolonneNavn);
		table = new JTable(vareInnhold);
		table.setBounds(10, 209, 430, -192);
		getContentPane().add(table);
		
		scrollpane = new JScrollPane(table);
		scrollpane.setBounds(10, 10, 573, 227);
		getContentPane().add(scrollpane);
		
	}
	
	public void visVareVinduKontroll(ResultSet resultat) {
		try {
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			lagTabell(resultat);
			setVisible(true);
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
		Object[][] innhold = new Object[size][7];
		while(resultat.next()) {
			String vareNummer = resultat.getString(1);
			String betegnelse = resultat.getString(2);
			String pris = resultat.getString(3);
			String katNavn = resultat.getString(4);
			String katNr = resultat.getString(5);
			String antall = resultat.getString(6);
			String hylle = resultat.getString(7);
			innhold[cnt][0] = vareNummer;
			innhold[cnt][1] = betegnelse;
			innhold[cnt][2] =pris;
			innhold[cnt][3] =katNavn;
			innhold[cnt][4] =katNr;
			innhold[cnt][5] =antall;
			innhold[cnt][6] =hylle;
			cnt ++;
		}
		return innhold;
	}
	

}
