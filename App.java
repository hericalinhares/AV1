package Avaliacao1;
import java.util.Random;

public class App {
	
	public static void main(String[] args) {
		
		// LEITURA DOS ARQUIVOS .CSV A PARTIR DA CLASSE IMPORTFILE
		ImportFile reader = new ImportFile();
		
		reader.readFile("AUDUSD_M30.csv");
		reader.readFile("EURCAD_M30.csv");
		reader.readFile("EURUSD_M30.csv");
		reader.readFile("NZDUSD_M30.csv");
				
		// CRIA MINHA CORRETORA
		Corretora minhaCorretora = new Corretora();
		
		// ADICIONA 10 NOVOS CLIENTES COM SALDO LIMITE DE 500 A 1500 REAIS.
		Random r = new Random();
		
		for (int i=0; i < 10; i++) {
			
			double valor = 0;
			valor = r.nextInt(1001) + 500;
			
			new Cliente(valor, minhaCorretora);			
		} 
				
	}

}
