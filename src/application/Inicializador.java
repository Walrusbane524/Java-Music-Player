package application;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Inicializador {
	
	File pasta_mem;
	File pasta_musica;
	ArrayList<Organizador> lib; // pos[0] = biblioteca
	ArrayList<String> map_musica;
	
	
	Inicializador(){
		
		pasta_mem = new File(System.getProperty("user.dir"));
		lib = new ArrayList<Organizador>();
		map_musica = new ArrayList<String>();
		
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
	
	public static void processFiles(File f) {
		File db = new File("./mem/db.csv");
		try {
			FileWriter fw = new FileWriter(db, true);
			if(f.isDirectory()) {
				try {
					
					for(File arq : f.listFiles()) {
						if(arq.getName().contains(".mp3")) {
							System.out.println(arq.getPath());
							Musica musica = new Musica(arq);
							musica.organizaDados();
							MusicaInfo mi = musica.getMusica_info();
							fw.write(	mi.getNome_arquivo() + "," + 
										mi.getNome_musica() + "," + 
										mi.getNome_album() + "," + 
										mi.getNome_artista() + "," +
										mi.getPath() + "\n");
						}
					}
					
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else if(f.getName().contains(".mp3")) {
				System.out.println(f.getPath());
				Musica musica = new Musica(f);
				musica.organizaDados();
				MusicaInfo mi = musica.getMusica_info();
				fw.write(	mi.getNome_arquivo() + "," + 
							mi.getNome_musica() + "," + 
							mi.getNome_album() + "," + 
							mi.getNome_artista() + "," +
							mi.getPath() + "\n");
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void readData(){
		File db = new File("./mem/db.csv");
		try {
			FileReader fr = new FileReader(db);
			int c;
			String s = "";
			
			while((c = fr.read()) != -1) {
				if(c == ',') {
					map_musica.add(s);
					s = "";
					//Pula uma linha inteira
					do{
						c = fr.read();
					} while(c != -1 && c != '\n');
					
					if(c == -1) 
						break;
				}
				else {
					s += (char)c;
				}
			}
			
			fr.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(String s : map_musica)
			System.out.println(s);
	}
	
}
