package application;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.File;


public class Organizador {
	ArrayList<Musica> fila;
	ArrayList<Musica> historico;
	ArrayList<Musica> listaInicial;
	String path;
	File arquivo;
	final int ATUAL = 0;
	final int TAMANHO = 0;
	
	Organizador(String path) {
		fila = new ArrayList<Musica>();
		listaInicial = new ArrayList<Musica>();
		historico = new ArrayList<Musica>(TAMANHO);
		this.path = path;
		this.arquivo = new File(path);
		this.encontrarMusica();
	}
	
	public void setPath(String s){
		this.path = s;
	}
	
	public ArrayList<Musica> getFila(){
		return this.fila;
	}
	
	public ArrayList<Musica> getHistorico(){
		return this.historico;
	}
	
	public Musica getMusica(int index) {
		if(index >= 0 && index < fila.size()) {
			return this.fila.get(index);
		}
		else
			return null;
	}
	
	public String getMusica_path(int index) {
		
		char barra;
		
		//Verifica o sistema operacional
		if(System.getProperty("os.name").contains("Windows"))
			barra = '\\';
		else
			barra = '/';
		
		if (arquivo.getName().charAt(arquivo.getName().length()-1) == 'c')
			return arquivo.getName() + barra + barra + this.getMusica(index).getNome_musica();
		return arquivo.getName() + barra + this.getMusica(index).getNome_musica();
	}
	
	public void encontrarMusica() {
		System.out.println("Musicas desordenadas:");
		char barra;
		
		//Verifica o sistema operacional
		if(System.getProperty("os.name").contains("Windows"))
			barra = '\\';
		else
			barra = '/';
		
		int i = 0;
		for (File file : arquivo.listFiles()) {
			if (!file.isDirectory()) {
				String nome_arquivo = arquivo.getName();
				System.out.println(i + ": " + arquivo.getName() + barra + barra + file.getName());
				if (nome_arquivo.charAt(nome_arquivo.length()-1) == 'c')
					fila.add(new Musica(file.getName(), arquivo.getName() + barra + barra + file.getName()));
				else
					fila.add(new Musica(file.getName(), arquivo.getName() + barra + file.getName()));
				i++;
			}
		}
		listaInicial.addAll(fila);
	}
	
	public void adicionarMusica(Musica m) {
		fila.add(m);
	}
	
	public void filaNormal() {
		Collections.sort(fila, new Comparator<Musica>() {
			@Override
			public int compare(Musica a, Musica b) {
				return a.getNome_musica().compareTo(b.getNome_musica());
			}
		});
	}
	
	public Musica next(boolean repeat) {
		if(size() > 1) {
			ArrayList<Musica> aux = new ArrayList<Musica>(TAMANHO);
			aux.add(fila.get(ATUAL));
			aux.addAll(historico);
			fila.remove(ATUAL);
			historico = aux;
			historico.trimToSize();
		}
		else if (size() <= 1 && repeat) {
			fila.clear();
			fila.addAll(listaInicial);
		}
		
		return fila.get(ATUAL);
	}
	
	public Musica prev() {
		if(!historico.isEmpty()) {
			ArrayList<Musica> aux = new ArrayList<Musica>();
			aux.add(historico.get(ATUAL));
			aux.addAll(fila);
			historico.remove(ATUAL);
			fila = aux;
		}
		
		return fila.get(ATUAL);
	}
	
	public void filaRandom() {
		if(!fila.isEmpty()) {
			ArrayList<Musica> aux = new ArrayList<Musica>();
			aux.addAll(fila);
			aux.remove(ATUAL);
			fila.removeAll(aux);
			Collections.shuffle(aux);
			fila.addAll(aux);
		}
	}
	
	public void listaFila() {
		for (Musica m : fila) {
			System.out.println(m.getNome_musica());
		}
	}
	
	public int size() {
		return this.fila.size();
	}
}