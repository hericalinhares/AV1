package Avaliacao1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ImportFile {
	
	private int ind = 0;														// INDICE PARA A ARRAYLIST DE ATIVOS
	private String path;														// ENDEREÇO DO ARQUIVO .CSV
	private static ArrayList<Ativos> dadosAtivos = new ArrayList<Ativos>();		// SERÁ USADA COMO BANCO DE DADOS DE ATIVOS

	// CONSTRUTOR
	public ImportFile() {

	}
	
	// METODO QUE FAZ LEITURA DO ARQUIVO .CSV E ADICIONA OS DADOS A UM ATIVO
	public void readFile(String endereco) {
		
		path = endereco;
		dadosAtivos.add(new Ativos());
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			
			String line = br.readLine();
			line = br.readLine();
			while (line != null) {
				
				String[] vect = line.split(" ");
				
				String time = vect[0] + " " + vect[1];
				double open = Double.parseDouble(vect[2]);
				double high = Double.parseDouble(vect[3]);
				double low = Double.parseDouble(vect[4]);
				double close = Double.parseDouble(vect[5]);
		//		double tickvol = Double.parseDouble(vect[6]);
		//		double vol = Double.parseDouble(vect[7]);
		//		double spread = Double.parseDouble(vect[8]);
				
				dadosAtivos.get(ind).addLine(time, open, high, low, close);
				line = br.readLine();
			}	

			ind++;
			
			}
	
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	// ESSE METODO SERA USADO PARA LEITURA DE DADOS DOS ATIVOS
	public static ArrayList<Ativos> getDadosAtivos() {
		return dadosAtivos;
	}
}
