package application;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.ObservableMap;
import javafx.scene.image.Image;


public class Inicializador {
	
	File pasta_mem;
	File pasta_musica;
	ArrayList<Organizador> lib; // pos[0] = biblioteca
	ArrayList<MusicaInfo> map_musica;
	
	
	Inicializador(){
		
		pasta_mem = new File(System.getProperty("user.dir"));
		lib = new ArrayList<Organizador>();
		map_musica = new ArrayList<MusicaInfo>();
		
		for (File arq : pasta_mem.listFiles()) {
			if (arq.isDirectory() && arq.getName().equals("mem")) {
				pasta_mem = arq;
				readData();
			}
			
			// PARA TESTE, APAGAR DEPOIS
			if (arq.isDirectory() && arq.getName().equals("Music")) {
				pasta_musica = arq;
			}
		}
		
		iniciaMusicas();
		
		// COLOCAR FUNÇÂO DE SELECIONAR DIRETORIO DE MUSICA
		
	}
	
	private void iniciaMusicas(){
		System.out.println(pasta_musica);
		lib.add(new Organizador(this.pasta_musica.getName()));
		Organizador superOrg = lib.get(0);
		
		for (Musica m : superOrg.getListaInicial()) {
			
		}
	}
	
	public Organizador getSuperOrg() {
		return this.lib.get(0);
	}
	
	public void processFiles(ObservableMap<String, Object> metadados, String path, String name) {
		File db = new File("./mem/db.csv");
		
		String titulo = "null", 
				album = "null", 
				artista = "null";
		
		for (String key : metadados.keySet()) {
			key = key.toLowerCase();
			if (key.contains("title") && !key.contains("album") && !key.contains("ep")) {
				titulo = (String)metadados.get(key);
			}
			else if (key.contains("album") || key.contains("ep")) {
				album = (String)metadados.get(key);
			}
			else if (key.contains("artist") || key.contains("band")) {
				artista = (String)metadados.get(key);
			}
		}
		
		try {
			FileWriter fw = new FileWriter(db, true);
	
			fw.write(	name + ";" + 
						titulo + ";" + 
						album + ";" + 
						artista + "\n");
		
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		readData();
	}
	
	public void readData(){
		File db = new File("./mem/db.csv");
		try {
			FileReader fr = new FileReader(db);
			int c, atributo = 0;
			String s = "";
			MusicaInfo mi = new MusicaInfo();
			
			while((c = fr.read()) != -1) {
				if(c == ';') {
					if(atributo == 0) {
						mi.setNome_arquivo(s);
						atributo++;
						s = "";
					}
					else if(atributo == 1) {
						mi.setNome_musica(s);
						atributo++;
						s = "";
					}
					else if(atributo == 2) {
						mi.setNome_album(s);
						atributo++;
						s = "";
					}
					else if(atributo == 3) {
						mi.setNome_artista(s);
						atributo++;
						s = "";
					}
				}
				else if(c == '\n') {
					atributo = 0;
					map_musica.add(mi);
					mi = new MusicaInfo();
					s = "";
				}
				else
					s += (char)c;
				
			}
			
			fr.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
