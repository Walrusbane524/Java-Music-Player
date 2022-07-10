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
	Controller controller;
	
	Inicializador(Controller c){
		this.controller = c;
		pasta_mem = new File(System.getProperty("user.dir"));
		lib = new ArrayList<Organizador>();
		playlistMap = new HashMap<String, Integer>();
		info_musica = new ArrayList<MusicaInfo>();
		map_playlist = new HashMap<String, Integer>();
		
		for (File arq : pasta_mem.listFiles()) {
			if (arq.isDirectory() && arq.getName().equals("mem")) {
				pasta_mem = arq;
			}
			
			// PARA TESTE, APAGAR DEPOIS
			if (arq.isDirectory() && arq.getName().equals("Music")) {
				pasta_musica = arq;
			}
		}
		
		iniciaMusicas();
		readPlaylists();
		
	}
	
	private void iniciaMusicas(){
		System.out.println(pasta_musica);
		lib.add(new Organizador(pasta_musica.getAbsolutePath(), controller));
		Organizador superOrg = lib.get(0);
	}
	
	public Organizador getSuperOrg() {
		return this.lib.get(0);
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
				lib.add(new Organizador(lista, controller));
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
