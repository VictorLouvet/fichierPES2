package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class analyse {

	public String repertoire;
	public static ArrayList<String> listFichier = new ArrayList<String>();
	public static ArrayList<String> listErreurs = new ArrayList<String>();
	
	
	public static void analyse(String pRep) {
		String line="";
		boolean rep=false;
		String erreur="";
		
		try {
			for (String leFichier:listFichier) {
				File f = new File(pRep+"\\"+leFichier);
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				
				line= br.readLine();
					while((line != null)) { //NullPointerException avec 004 et 007
						
						if(line.contains("<Erreur>")){   
							
							while(!line.contains("</Erreur>")) {
								if(!line.contains("<Erreur>")) {
									erreur=line;
									System.out.println(erreur);
								}
							
							line= br.readLine();
							
							}
							
							System.out.println("Erreur détecté dans le fichier : " + leFichier);
							rep = true;
						}
						line= br.readLine();
						
					}
					if(rep == true) {
						listErreurs.add(leFichier);
					}
					
					if(rep == false) {
						System.out.println("Aucune erreur détectée");
					}
						
					br.close();
					fr.close();	
					
					rep = false;
			}
			
			} catch(Exception e) {
				e.printStackTrace();
			}
		
	}
	
	public static void recupNomFichier(String pRep) {
		File repertoire = new File(pRep);
		File[] filestab = repertoire.listFiles();
		listFichier.clear();
		
		for(int i=0;i<filestab.length;i++) {
			String temp = filestab[i].toString();
			String temp2 = temp.replace(pRep+"\\","");
			listFichier.add(temp2);
			//System.out.println(temp2); //debug
		}
		
	}
	
}
