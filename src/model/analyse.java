package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
							
							rep=true;
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
	
	public static String getErreurOnFile(String pRep,String pNomFicher) {
		String line="";
		boolean rep=false;
		String erreur="";
		
		try {
				File f = new File(pRep+"\\"+pNomFicher);
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				
				line= br.readLine();
					while((line != null)) {
						
						if(line.contains("<Erreur>")){   
							
							while(!line.contains("</Erreur>")) {
								if(!line.contains("<Erreur>")) {
									String newLine = System.getProperty("line.separator");
									erreur+=line+newLine;
									
								}
							line= br.readLine();
							}
							return erreur;
						}
						line= br.readLine();
					}
					br.close();
					fr.close();	
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static void recupNomFichier(String pRep) {
		File repertoire = new File(pRep);
		File[] filestab = repertoire.listFiles();
		Date actuelle = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String dat = dateFormat.format(actuelle);
		int date = Integer.parseInt(dat);
		System.out.println(date);
		listFichier.clear();
		
		for(int i=0;i<filestab.length;i++) {
			String temp = filestab[i].toString();
			String temp2 = temp.replace(pRep+"\\","");
			if(temp2.startsWith(String.valueOf(date-1))) {
				listFichier.add(temp2);
			}
			
			//System.out.println(temp2); //debug
		}
		
	}
	
}
