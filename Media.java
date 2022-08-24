package Avaliacao1;

import java.util.ArrayList;

public class Media {
	
	public double curta;						// medias curtas n = 7;
	public double intermediaria;				// medias intermediarias = 30;
	public double longa;						// medias longas n = 100;

	public Media() {
		curta = 0;
		intermediaria = 0;
		longa = 0;
	}
	
	// CALCULA MEDIAS SIMPLES
	// Media Movel Simples = (soma do preço de fechamento de n períodos)/n
	public void calcularMediaSimples(ArrayList<Double> valor) {

		int count = 0;				
		double soma = 0;

		for(int i = (valor.size()-1); i >= 0; i--) {	

			count++;
			soma += valor.get(i);

			  if (count == 7) {
				curta = soma/7;
			} if (count == 30) {
				intermediaria = soma / 30;
			} if (count == 100) {
				longa = soma / 100;
				break;
			} 

		}
	}
	
	// CALCULO MEDIAS EXPONENCIAIS
	public void calcularMediaExponencial(Ativos ativo) {
		
		curta = calculoExp(ativo,7);
		intermediaria = calculoExp(ativo,30);
		longa = calculoExp(ativo,100);

	}
	
	private double calculoExp(Ativos ativo, int n) {
		
		double mme;
		
		if (ativo.getN() < n) {			// para o primeiro dado
			return mme = 0;
			
		} if (ativo.getN() == n){		// para o segundo dado
			
			int ind = ativo.getN()-1;
			double mma = ativo.getMediaSimples().get(ind).getCurta();
			double close = ativo.getClose().get(ind);
			
			mme = 2*(close - mma)/(n+1) + mma;
			return mme;
			
		} else {						// para os demais dias
			
			int ind = ativo.getN()-1;
			double mme_anterior = ativo.getMediaExponencial().get(ind-1).getCurta();
			double close = ativo.getClose().get(ind);
			
			mme = 2*(close - mme_anterior)/(n+1) + mme_anterior;
			return mme;
		}
		
	}

	public double getCurta() {
		return curta;
	}

	public double getIntermediaria() {
		return intermediaria;
	}

	public double getLonga() {
		return longa;
	}
}
