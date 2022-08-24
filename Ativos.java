package Avaliacao1;

import java.util.ArrayList;

public class Ativos {

	// CONTA QUANTOS DADOS FORAM ADICIONADOS AO ATIVO
	private int n;		
	private char id;

	// ARRAYLISTS PARA GUARDAR OS DADOS DA PLANILHA EXPORTADA DO METATRADER
	private ArrayList<String> timestamp = new ArrayList<String>();
	private ArrayList<Double> open = new ArrayList<>();
	private ArrayList<Double> high = new ArrayList<>();
	private ArrayList<Double> low = new ArrayList<>();
	private ArrayList<Double> close = new ArrayList<>();

	// ARRAYLISTS PARA GUARDAR OS VALORES DAS MEDIAS E DESVIO
	private ArrayList<Media> mediaSimples = new ArrayList<>();
	private ArrayList<Media> mediaExponencial = new ArrayList<>();
	private ArrayList<Double> desvioPadrao = new ArrayList<>();

	// CONSTRUTOR 1
	public Ativos(char id) {	
		this.id = id;
		n = 0;
	}
	
	// CONSTRUTOR 2
	public Ativos() {	
		n = 0;
	}

	// METODO QUE INSERE OS DADOS LIDOS DA PLANILHA EXPORTADA PARA DENTRO DO OBJETO
	public void addLine(String time, double open, double high, double low, double close){

		this.timestamp.add(time);
		this.open.add(open);
		this.high.add(high);
		this.low.add(low);
		this.close.add(close);

		n++;
	}

	// METODO QUE CALCULA TODAS AS MEDIAS
	public void calcularMedias() {
		
		Media ms = new Media();
		ms.calcularMediaSimples(close);
		mediaSimples.add(ms);
		
		Media mme = new Media();
		mme.calcularMediaExponencial(this);
		mediaExponencial.add(mme);
		
		calcularDesvioPadrao();
		
	}
	
	private void calcularDesvioPadrao() {
		
		double soma = 0;
		double dp;
		
		for(int i=0; i<n; i++) {
			soma += close.get(i);
		}
		
		dp = Math.sqrt((soma - Math.pow(soma/n,2))/n);	
		desvioPadrao.add(dp);
		
	}

	// METODOS GET
	public int getN() {
		return n;
	}

	public ArrayList<String> getTimestamp() {
		return timestamp;
	}

	public ArrayList<Double> getClose() {
		return close;
	}

	public ArrayList<Media> getMediaSimples() {
		return mediaSimples;
	}

	public ArrayList<Media> getMediaExponencial() {
		return mediaExponencial;
	}

	public ArrayList<Double> getDesvioPadrao() {
		return desvioPadrao;
	}	
	
	public ArrayList<Double> getOpen() {
		return open;
	}

	public ArrayList<Double> getHigh() {
		return high;
	}

	public ArrayList<Double> getLow() {
		return low;
	}

	public char getId() {
		return id;
	}
	
	
}
