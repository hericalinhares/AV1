package Avaliacao1;

public class Operacao {
	
	private String timestamp;		// HORA DA OPERACAO
	private String tipoOperacao;	// OPERACAO DE COMPRA OU VENDA
	private char ativo;				// QUAL ATIVO FOI COMPRADO
	private int qtde;				// QTDE COMPRADA
	private double valorAtivo;		// CUSTO DE COMPRA OU VENDA DO ATIVO	
	private int cliente;			// QUAL CLIENTE COMPROU
	private double valorTotal;		// VALOR TOTAL DA OPERAÇAO
	
	// CONSTRUTOR: OPERAÇÃO PARA CLIENTES
	public Operacao(String timestamp, String tipoOp, char ativo, int qtde, double valorAtivo) {
		
		this.timestamp = timestamp;
		this.tipoOperacao = tipoOp;
		this.ativo = ativo;
		this.valorAtivo = valorAtivo;
		this.valorTotal = qtde*valorAtivo;
	}

	// CONSTRUTOR: OPERAÇÕES PARA CORRETORA
	public Operacao(String timestamp, String tipoOp, char ativo, double valorTotal, int cliente) {
		
		this.timestamp = timestamp;
		this.tipoOperacao = tipoOp;
		this.ativo = ativo;
		this.valorTotal = valorTotal;
		this.cliente = cliente;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getTipoOperacao() {
		return tipoOperacao;
	}

	public char getAtivo() {
		return ativo;
	}

	public int getQtde() {
		return qtde;
	}

	public double getValorAtivo() {
		return valorAtivo;
	}

	public int getCliente() {
		return cliente;
	}

	public double getValorTotal() {
		return valorTotal;
	}
}
