package Model.VO;

import Exception.ExceptionCampoInvalido;
// import projeto.model.dao.ProdutoDAO;

public class ProdutoVO {
	private String nome;
	private String descricao;
	private double peso;
	private double preco;
	private int quantidade = 0; 
	private int quantiPedido = 0;
	private long id;
	private String prateleira; 
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws ExceptionCampoInvalido {
		if ((nome != null) && (!nome.isEmpty())) {
			this.nome = nome;
		} else {
			throw new ExceptionCampoInvalido("Digite um nome válido");
		}
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) throws ExceptionCampoInvalido {
		if (descricao != null) {
			if ((descricao.length() <= 500) && (!descricao.isEmpty())) {
				this.descricao = descricao;
			} else {
				throw new ExceptionCampoInvalido("Descrição não pode ter mais do que 500 caracteres");
			}
		} else {
			throw new ExceptionCampoInvalido("Digite alguma coisa para descrever o produto");
		}
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) throws ExceptionCampoInvalido {
		if (preco > 0) {
			this.preco = preco;
		} else {
			throw new ExceptionCampoInvalido("Digite um valor diferente de 0 para o preço");
		}
	}

	
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		// esse método é usado em estoqueVO para aumentar
		// ou diminuir a quantidade, e lá mesmo
		// verifica se a quantidade é valida ou não
		this.quantidade += quantidade;
	}

	public int getQuantiPedido() {
		return quantiPedido;
	}

	public void setQuantiPedido(int quantiPedido) throws ExceptionCampoInvalido {
		// médodo usado em vendaVO para vericar se a quantidade
		// pedida é aceita
		if (quantiPedido > 0) {
			if (quantiPedido <= quantidade) {
				this.quantiPedido = quantiPedido;;
			} else {
				throw new ExceptionCampoInvalido("Quantidade do pedido não pode exceder o que tem em estoque");
			}
		}
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) throws ExceptionCampoInvalido {
		if (peso > 0) {
			this.peso = peso;
			// verificar se o preço está sendo um valor positivo e maior que zero
		} else {
			throw new ExceptionCampoInvalido("Digite algo maior que 0 para o peso do produto");
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPrateleira() {
		return prateleira;
	}
	
	public void setPrateleira(String prateleira) throws ExceptionCampoInvalido {
		if ((prateleira != null) && (!prateleira.isEmpty())) {
			this.prateleira = prateleira;
		} else {
			throw new ExceptionCampoInvalido("Digite uma informação válida");
		}
	}
}

