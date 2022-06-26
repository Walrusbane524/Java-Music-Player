package application;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.File;


public class Organizador {
	ArrayList<Musica> musicas;
	ArrayList<Musica> fila;
	String path;
	File pasta;
	
	Organizador(String path) {
		musicas = new ArrayList<Musica>();
		fila = new ArrayList<Musica>();
		this.path = path;
		this.pasta = new File(path);
		this.encontraMusica();
	}
	
	public void encontraMusica() {
		System.out.println("Musicas:");
		int i = 0;
		for (File file : pasta.listFiles()) {
			if (!file.isDirectory()) {
				String nome_pasta = pasta.getName();
				System.out.println(i + ": " + pasta.getName() + "\\" + "\\" + file.getName());
				if (nome_pasta.charAt(nome_pasta.length()-1) == 'c')
					musicas.add(new Musica(file.getName(), pasta.getName() + "\\" + "\\" + file.getName()));
				else
					musicas.add(new Musica(file.getName(), pasta.getName() + "\\" + file.getName()));
				i++;
				/*
				*/
			}
		}
		this.preencheFila();
	}
	
	public void filaNormal() {
		this.preencheFila();
		Collections.sort(fila, new Comparator<Musica>() {
			@Override
			public int compare(Musica a, Musica b) {
				return a.getNome_musica().compareTo(b.getNome_musica());
			}
		});
	}
	
	public void filaRandom() {
		this.preencheFila();
		Collections.shuffle(fila);
	}
	
	public void listaMusicas() {
		for (Musica m : musicas) {
			System.out.println(m);
		}
	}
	
	public Musica getMusica(int index) {
		return this.fila.get(index);
	}
	
	public String getMusica_path(int index) {
		if (pasta.getName().charAt(pasta.getName().length()-1) == 'c')
			return pasta.getName() + "\\" + "\\" + this.getMusica(index).getNome_musica();
		return pasta.getName() + "\\" + this.getMusica(index).getNome_musica();
	}
	
	public ArrayList<Musica> getMusicas(){
		return this.musicas;
	}
	
	public ArrayList<Musica> getFila(){
		return this.fila;
	}
	
	public int size() {
		return this.getMusicas().size();
	}
	
	private void preencheFila() {
		if (fila.size() != musicas.size()) {
			for (Musica m : musicas) {
				fila.add(m);
			}
		}
	}
}