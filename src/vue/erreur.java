package vue;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.analyse;
import javax.swing.JTextPane;

public class erreur extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldChemin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					erreur frame = new erreur();
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
	public erreur() {
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 663, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblErreur = new JLabel("Erreur:");
		lblErreur.setBounds(172, 21, 160, 14);
		contentPane.add(lblErreur);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setBounds(326, 317, 89, 23);
		contentPane.add(btnQuitter);
		
		JTextPane textPaneErreurContent = new JTextPane();
		textPaneErreurContent.setBounds(62, 52, 481, 233);
		contentPane.add(textPaneErreurContent);
		
		lblErreur.setText("Erreur: "+Fprincipale.nomFichier);
		String erreur=analyse.getErreurOnFile(Fprincipale.chemin, Fprincipale.nomFichier);
		Font font1 = new Font("SansSerif", Font.BOLD, 10);
		textPaneErreurContent.setFont(font1);
		textPaneErreurContent.setText(erreur);
		
	}
}
