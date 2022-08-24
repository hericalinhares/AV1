package Avaliacao1;
import java.util.ArrayList;

public class ContaCorrente {
	
	// ATRIBUTOS
	private double saldoInicial;						// SALDO LIMITE
	private double saldoAtual;							// SALDO ATUAL
	private double saldoFinal;							// SALDO FINAL
	
	private Corretora minhaCorretora;					
	private ArrayList<Operacao> movimentacao;			// VETOR QUE ARMAZENA OPERACOES
	
	private int[] qtdeAcoes;							// Armazena quantas ações de cada tipo foram compradas [A B C D]
 	
	// -------------------------------------------------//
	// CONSTRUTOR
	public ContaCorrente(double saldoLimite, Corretora minhaCorretora) {
		
		this.saldoInicial = saldoLimite;
		this.saldoAtual = saldoInicial;
		this.minhaCorretora = minhaCorretora;
		
		this.qtdeAcoes = new int[minhaCorretora.consultaAtivos().size()];
		
		for (int i=0; i<minhaCorretora.consultaAtivos().size(); i++) {
			qtdeAcoes[i] = 0;
		}
		
		movimentacao = new ArrayList<Operacao>();
	}
	
	// -------------------------------------------------//
	// METODO QUE FAZ UMA OPERACAO NA CONTA CORRENTE
	public void novaMovimentacao(String timestamp, String tipoOp, char ativo, int qtde, double valor) {
		
		movimentacao.add(new Operacao(timestamp, tipoOp, ativo, qtde, valor));
		atualizaConta(tipoOp, ativo, qtde, valor);
		
	}
		
	public int[] getQtdeAcoes() {
		return qtdeAcoes;
	}
	
	public Operacao getUltimaOperacao() {
		
		int y = movimentacao.size();
		return movimentacao.get(y-1);
	
	}
 	
	public int totalMovimentacoes() {
		return movimentacao.size();
	}

	public ArrayList<Operacao> historicoMovimentacoes() {
		return movimentacao;
	}
	
	public double meuSaldo() {
		return saldoAtual;
	}

	// METODO QUE RECALCULA O VALOR DA CONTA CORRENTE
	private void atualizaConta(String tipoOp, char ativo, int qtde, double valor) {
		
		int ind = 0;
		
		// Verifica qual ativo foi comprado
		if (ativo == 'A') { ind = 0; } 
		if (ativo == 'B') { ind = 1; }
		if (ativo == 'C') { ind = 2; } 
		if (ativo == 'D') { ind = 3; }
		
		// Atualiza atributos
		if (tipoOp == "VENDA") {
			saldoAtual += valor;
			qtdeAcoes[ind] -= qtde;				
			
		} if (tipoOp == "COMPRA") {
			saldoAtual -= valor;
			qtdeAcoes[ind] += qtde;				
		}
		
	}
	
	public double getSaldo() {
		
		return saldoAtual;
	}
	
	public double getSaldoFinal() {
		
		double valor = 0;
		int n = minhaCorretora.consultaAtivoAtual();
		
		for(int i=0; i<qtdeAcoes.length; i++) {
			valor += qtdeAcoes[i] * minhaCorretora.consultaAtivos().get(i).getClose().get(n);
		}
		
		saldoFinal = saldoAtual + valor;
		return saldoFinal;
	}
	
	public ArrayList<Operacao> getMovimentacao(){
		return movimentacao;
	}
	
}
