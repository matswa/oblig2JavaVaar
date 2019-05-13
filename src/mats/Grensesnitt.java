package mats;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class Grensesnitt extends JFrame {
	private JPanel contentPane;
	Kontroll kontroll = Kontroll.getInstance();
	private ResultSet resultat;
	private int n = 0;
	
	RegistrerVareVindu regVareVindu = new RegistrerVareVindu(this);
	VisVareVindu visVareVindu = new VisVareVindu(this);
	VisOrdreVindu visOrdreVindu = new VisOrdreVindu(this);
	SlettKunde visSlettKundeVindu = new SlettKunde(this);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Grensesnitt frame = new Grensesnitt();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Grensesnitt() {
		setTitle("Hovedvindu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 260, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnRegistrerevare = new JButton("Registrer vare");
		btnRegistrerevare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//regVareVindu.kontrollVareVindu();
				regVareVindu.setVisible(true);
			}
		});
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		contentPane.add(btnRegistrerevare);
		
		JButton btnVisVare = new JButton("Vis varer");
		btnVisVare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					resultat = kontroll.hentVarer();
					visVareVindu.visVareVinduKontroll(resultat);
					//kontroll.lukkForbindelse();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		contentPane.add(btnVisVare);
		
		JButton btnVisOrdre = new JButton("Vis ordre");
		btnVisOrdre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					resultat = kontroll.hentOrdre(n);
					visOrdreVindu.visOrdreVinduKontroll(resultat);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnVisOrdre);
		
		JButton btnSlettKunde = new JButton("Slett kunde");
		btnSlettKunde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visSlettKundeVindu.slettKundeKontroll();
			}
		});
		contentPane.add(btnSlettKunde);
		
		JButton btnAvslutt = new JButton("Avslutt");
		btnAvslutt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		contentPane.add(btnAvslutt);
	}
	
	public void lagVare(String vNr, String betegnelse, double pris, int katNr, int antall, String hylle) throws Exception {
		Vare vare = new Vare(vNr, betegnelse, pris, katNr, antall, hylle);
		
		kontroll.regVare(vare);
	}
	public ResultSet sokOrdre(int n) throws Exception {
		return kontroll.hentOrdre(n);
	}
	public ResultSet sokKundeInfo(int ordreNummer) throws SQLException {
		return kontroll.hentKundeInfo(ordreNummer);
	}
	
	public ResultSet sokKunde(int kundeNummer) throws SQLException {
		return kontroll.hentKunde(kundeNummer);
	}
	public void slett(int kundenummer) throws SQLException {
		kontroll.slettKunde(kundenummer);
	}
}
