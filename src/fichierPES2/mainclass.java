package fichierPES2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class mainclass {
	public static ArrayList<String> list = new ArrayList<String>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//boolean ok = false;
		Scanner sc = new Scanner(System.in);
		
		/*do {
			System.out.println("Saisir le nom du fichier");
			String saisie = sc.nextLine();
			//ajouter un for de verif
		} while (ok == false);*/
		
		recupNomFichier();
		System.out.println("Saisir nom fichier");
		String str = sc.nextLine();
		System.out.println(isFilesExist(str));
		sc.close();
		if(isFilesExist(str).equals("Fichier trouvé !")) {
			isErrorExist(str);
		}
		
		
	}
	
	public static void isErrorExist(String pNomFichier) {
		String line="";
		boolean rep=false;

		try {
			File f = new File("res/"+pNomFichier);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			
			line= br.readLine();
				while((line != null)) { //NullPointerException avec 004 et 007
					
					if(line.contains("<Erreur>")){   
						
						while(!line.contains("</Erreur>")) {
							if(!line.contains("<Erreur>")) {
								System.out.println(line);
							}
						
						line= br.readLine();
						
						}
						
						System.out.println("Erreur détecté dans le fichier : " + pNomFichier);
						rep = true;
					}
					line= br.readLine();
					
				}	
				if(rep == false) {
					System.out.println("Aucune erreur détectée");
				}
					
				br.close();
				fr.close();	
				
				rep = false;
			} catch(Exception e) {
				e.printStackTrace();
			}
		
	}
	
	public static void recupNomFichier() {
		File repertoire = new File("res/");
		File[] filestab = repertoire.listFiles();
		
		for(int i=0;i<filestab.length;i++) {
			String temp = filestab[i].toString();
			String temp2 = temp.substring(4);
			list.add(temp2);
			System.out.println(temp2);
		}
		
	}
	
	public static String isFilesExist(String pNomFichier) {
		String retour = "Fichier introuvable !";
		for(String m : list) {
			if(pNomFichier.equals(m)) {
				retour = "Fichier trouvé !";
			}
		}
		return retour;
	}
	

}
