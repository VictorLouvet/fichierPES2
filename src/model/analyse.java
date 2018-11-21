package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class analyse {

	public String repertoire;
	public ArrayList<String> listFichier = new ArrayList<String>();
	public ArrayList<String> listErreurs = new ArrayList<String>();
	
	public void analyse(String pNomFichier) {
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
	
	
	
}
