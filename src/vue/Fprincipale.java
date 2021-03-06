package vue;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

public class Fprincipale extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldChemin;
	static String nomFichier="";
	static String erreurFichier="";
	static String chemin="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fprincipale frame = new Fprincipale();
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
	public Fprincipale() {
		Date actuelle = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dat = dateFormat.format(actuelle);
		
		DefaultListModel DLMfichiers = new DefaultListModel();
		DefaultListModel DLMerreurs = new DefaultListModel();
		
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 663, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldChemin = new JTextField();
		textFieldChemin.setBounds(21, 37, 252, 20);
		contentPane.add(textFieldChemin);
		textFieldChemin.setColumns(10);

		JButton btnLire = new JButton("Lire");
		btnLire.setBounds(438, 36, 89, 23);
		contentPane.add(btnLire);
		btnLire.setEnabled(false);
		
		JButton btnChoisirRpertoire = new JButton("Choisir r\u00E9pertoire");
		btnChoisirRpertoire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
			    chooser.setCurrentDirectory(new java.io.File("."));
			    chooser.setDialogTitle("choosertitle");
			    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    chooser.setAcceptAllFileFilterUsed(false);

			    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			      textFieldChemin.setText(chooser.getSelectedFile().getAbsolutePath());
			      btnLire.setEnabled(true);
			      analyse.recupNomFichier(textFieldChemin.getText());
			      DLMfichiers.clear();
			      for (int i = 0; i < analyse.listFichier.size(); i++) {
					DLMfichiers.addElement( analyse.listFichier.get(i));
				  }
			      
			      
			    } else {
			      System.out.println("No Selection ");
			    }
			}
		});
		btnChoisirRpertoire.setBounds(283, 36, 145, 23);
		contentPane.add(btnChoisirRpertoire);
		
		JList listFichiers = new JList();
		listFichiers.setBounds(21, 103, 252, 267);
		contentPane.add(listFichiers);
		
		JList listErreur = new JList();
		listErreur.setBounds(418, 100, 207, 270);
		contentPane.add(listErreur);
		
		JLabel lblErreurDeTransmission = new JLabel("Erreur de transmission");
		lblErreurDeTransmission.setBounds(418, 75, 207, 14);
		contentPane.add(lblErreurDeTransmission);
		
		JLabel lblLectureDeXx = new JLabel("Lecture de XX Fichier(s)");
		lblLectureDeXx.setBounds(21, 78, 252, 14);
		contentPane.add(lblLectureDeXx);
		
		JButton btnAnalyser = new JButton("Analyser");
		btnAnalyser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				analyse.analyse(textFieldChemin.getText());
				System.out.println(analyse.listErreurs.get(0));
			      for (int i = 0; i < analyse.listErreurs.size(); i++) {
						DLMerreurs.addElement( analyse.listErreurs.get(i));
					  }
			}
		});
		btnAnalyser.setBounds(297, 187, 89, 23);
		contentPane.add(btnAnalyser);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(506, 11, 119, 14);
		contentPane.add(lblDate);
		lblDate.setText(dat);
		
		listFichiers.setModel(DLMfichiers);
		listErreur.setModel(DLMerreurs);
		
		listErreur.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {

		            // Double-click detected
		        	nomFichier = listErreur.getSelectedValue().toString();
		        	chemin= textFieldChemin.getText();
		            erreur jf = new erreur();
		            jf.setVisible(true);
		        } else if (evt.getClickCount() == 3) {

		            // Triple-click detected
		            int index = list.locationToIndex(evt.getPoint());
		        }
		    }
		});
		
	}
}
