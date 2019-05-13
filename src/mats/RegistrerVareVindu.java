package mats;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/*
Grensesnitt laget med windowbuilder i Eclipse
*/
public class RegistrerVareVindu extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	
	public void kontrollVareVindu() {
		try {
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrerVareVindu(JFrame Grensesnitt) {
		super(Grensesnitt, "Ny vare", true);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblVarenummer = new JLabel("Varenummer:");
		lblVarenummer.setBounds(6, 25, 92, 16);
		contentPanel.add(lblVarenummer);
		
		JLabel lblBetegnelse = new JLabel("Betegnelse:");
		lblBetegnelse.setBounds(6, 53, 76, 16);
		contentPanel.add(lblBetegnelse);
		
		JLabel lblPris = new JLabel("Pris:");
		lblPris.setBounds(6, 81, 61, 16);
		contentPanel.add(lblPris);
		
		JLabel lblKategorinummer = new JLabel("Kategorinummer:");
		lblKategorinummer.setBounds(6, 109, 116, 16);
		contentPanel.add(lblKategorinummer);
		
		JLabel lblAntall = new JLabel("Antall:");
		lblAntall.setBounds(6, 137, 61, 16);
		contentPanel.add(lblAntall);
		
		JLabel lblHylle = new JLabel("Hylle:");
		lblHylle.setBounds(6, 165, 61, 16);
		contentPanel.add(lblHylle);
		
		textField = new JTextField();
		textField.setBounds(173, 20, 208, 26);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(173, 48, 208, 26);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(173, 76, 208, 26);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(173, 104, 208, 26);
		contentPanel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(173, 132, 208, 26);
		contentPanel.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(173, 160, 208, 26);
		contentPanel.add(textField_5);
		textField_5.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton regVarebtn = new JButton("Registrer vare");
				regVarebtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							String vNr = textField.getText();
							String betegnelse = textField_1.getText();
							double pris = Double.parseDouble(textField_2.getText());
							int katNr = Integer.parseInt(textField_3.getText());
							int antall = Integer.parseInt(textField_4.getText());
							String hylle = textField_5.getText();
							
							Grensesnitt grensesnitt = new Grensesnitt();
														
							grensesnitt.lagVare(vNr, betegnelse, pris, katNr, antall, hylle);
							
						}catch(Exception ex) {JOptionPane.showMessageDialog(null, ex.getMessage());}
					}
				});
				regVarebtn.setActionCommand("Registerer vare");
				buttonPane.add(regVarebtn);
				getRootPane().setDefaultButton(regVarebtn);
			}
			{
				JButton avsluttbtn = new JButton("Avslutt");
				avsluttbtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				avsluttbtn.setActionCommand("Avslutt");
				buttonPane.add(avsluttbtn);
			}
		}
	}
}
