package Avaliacao1;

import java.io.File;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExportFile {
	
	private int contador;	// CONTADOR DE QUANTOS ARQUIVOS FORAM CRIADOS
	
	// CONSTRUTOR
	public ExportFile() {
		contador = 0;
	}
	
	public void writeFile(String caminho, Ativos ativo) {

		try {
		// DEFINIR LOCAL DO ARQUIVO
		WritableWorkbook planilha = Workbook.createWorkbook(new File(caminho));
		
		// CRIAR ABA DA PLANILHA
		WritableSheet aba = planilha.createSheet("Dados do ativo ", 0);
		
		// CABEÇALHOS
		String cabecalho[] = new String[9];
		cabecalho[0] = "Timestamp";
		cabecalho[1] = "Close";
		cabecalho[2] = "Media Simples Curta";
		cabecalho[3] = "Media Simples Intermediaria";
		cabecalho[4] = "Media Simples Longa";
		cabecalho[5] = "Media Exponencial Curta";
		cabecalho[6] = "Media Exponencial Intermediaria";
		cabecalho[7] = "Media Exponencial Longa";
		cabecalho[8] = "Desvio Padrão";
		
		// ESCREVER O CABEÇALHO
		for (int i = 0; i < cabecalho.length; i++) {
			Label label = new Label(i, 0, cabecalho[i]);
			aba.addCell(label);
		}
		
		int ind = 0;

		for (int linha = 1; linha <= ativo.getN(); linha++) {		// DEFINE NUMERO DA LINHA

			Label label = new Label(0, linha, ativo.getTimestamp().get(ind));
			aba.addCell(label);

			Number number = new Number(1, linha, ativo.getClose().get(ind));
			aba.addCell(number);

			number = new Number(2, linha, ativo.getMediaSimples().get(ind).getCurta());
			aba.addCell(number); 
			
			number = new Number(3, linha, ativo.getMediaSimples().get(ind).getIntermediaria());
			aba.addCell(number);  
			
			number = new Number(4, linha, ativo.getMediaSimples().get(ind).getLonga());
			aba.addCell(number); 
			
			number = new Number(5, linha, ativo.getMediaExponencial().get(ind).getCurta());
			aba.addCell(number); 
			
			number = new Number(6, linha, ativo.getMediaExponencial().get(ind).getIntermediaria());
			aba.addCell(number); 
			
			number = new Number(7, linha, ativo.getMediaExponencial().get(ind).getLonga());
			aba.addCell(number);
			
			number = new Number(8, linha, ativo.getDesvioPadrao().get(ind));
			aba.addCell(number);	
			
			ind++;
		}
		
		planilha.write();		// ESCREVE NA PLANILHA
		planilha.close();		// FECHA ARQUIVO
		
		contador++;	
		System.out.println("Planilha criada e escrita para ativo "+contador);

		
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
}
