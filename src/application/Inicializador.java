package application;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.ObservableMap;


public class Inicializador {
	
	File pasta_mem;
	File pasta_musica;
	HashMap<String, Integer> playlistMap;
	ArrayList<Organizador> lib; // pos[0] = biblioteca
	ArrayList<MusicaInfo> info_musica;
	HashMap<String, Integer> map_playlist;
	
	Inicializador(){
		
		pasta_mem = new File(System.getProperty("user.dir"));
		lib = new ArrayList<Organizador>();
		playlistMap = new HashMap<String, Integer>();
		info_musica = new ArrayList<MusicaInfo>();
		map_playlist = new HashMap<String, Integer>();
		
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
		readPlaylists();
		// COLOCAR FUNÇÂO DE SELECIONAR DIRETORIO DE MUSICA
		
	}
	
	private void iniciaMusicas(){
		System.out.println(pasta_musica);
		lib.add(new Organizador(pasta_musica.getAbsolutePath()));
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
			String s = "", nomeMus = ""
						, nomeArq = ""
						, nomeAlb = ""
						, nomeArt = "";
			
			while((c = fr.read()) != -1) {
				if(c == ';') {
					if(atributo == 0) {
						nomeArq = s;
						atributo++;
						s = "";
					}
					else if(atributo == 1) {
						nomeMus = s;
						atributo++;
						s = "";
					}
					else if(atributo == 2) {
						nomeAlb = s;
						atributo++;
						s = "";
					}
					else if(atributo == 3) {
						nomeArt = s;
						atributo++;
						s = "";
					}
				}
				else if(c == '\n') {
					atributo = 0;
					info_musica.add(new MusicaInfo(nomeMus, nomeAlb, nomeArt, nomeArq));
					nomeArt = "";
					nomeMus = "";
					nomeAlb = "";
					nomeArq = "";
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
	
	public void readPlaylists() {
		int i = 1, num;
		String s;
		ArrayList<Musica> lista;
		File pasta_playlists = new File(pasta_mem.getAbsolutePath() + "/playlists");
		try {
			for(File playlist : pasta_playlists.listFiles()) {
				FileReader _fr = new FileReader(playlist);
				BufferedReader fr = new BufferedReader(_fr);
				lista = new ArrayList<Musica>();
				num = 0;
				while((s = fr.readLine()) != null) {
					num = Integer.parseInt(s);
					System.out.println(lib.get(0).getMusica(num));
					lista.add(lib.get(0).getMusica(num));
				}
				fr.close();
				_fr.close();
				lib.add(new Organizador(lista));
				map_playlist.put(playlist.getName(), i);
				i++;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Organizador getPlaylistOrganizer(String arquivo) {
		
		if (map_playlist.containsKey(arquivo))
			return lib.get(map_playlist.get(arquivo));
		return null;
	}
}
