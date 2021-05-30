package Model.VO;

import Exception.ExceptionCampoInvalido;
import Model.DAO.ProdutoDAO;

public class ProdutoVO {
	private String nome;
	private String descricao;
	private double peso;
	private double preco;
	private int quantidade = 0;
	private int quantiPedido = 0;
	private long id;
	private String codigo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws ExceptionCampoInvalido {
		if ((nome != null) && (!nome.isEmpty())) {
			this.nome = nome;
		} else {
			throw new ExceptionCampoInvalido("Digite um nome válido.");
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
				throw new ExceptionCampoInvalido("Descrição do produto não pode ter mais do que 500 caracteres.");
			}
		} else {
			throw new ExceptionCampoInvalido("Digite alguma coisa para descrever o produto.");
		}
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) throws ExceptionCampoInvalido {
		if (preco > 0) {
			this.preco = preco;
		} else {
			throw new ExceptionCampoInvalido("Digite um valor diferente de 0 para o preço.");
		}
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade += quantidade;
	}

	public int getQuantiPedido() {
		return quantiPedido;
	}

	public void setQuantiPedido(int quantiPedido) throws ExceptionCampoInvalido {
		//Verifica se a quantidade pedida na compra do produto é menor ou igual a que tem em estoque. 
		if (quantiPedido > 0) {
			if (quantiPedido + this.quantiPedido <= quantidade) {
				this.quantiPedido += quantiPedido;
			} else {
				throw new ExceptionCampoInvalido("Quantidade do pedido não pode exceder o que tem em estoque.");
			}
		}
	}
	
	public void diminuirQuantiPedido (int quantiPedido) throws ExceptionCampoInvalido {
		if (quantiPedido <= this.quantiPedido) {
			this.quantiPedido -= quantiPedido;
		} else {
			throw new ExceptionCampoInvalido("Só é possivel remover " + this.quantiPedido + " do carrinho.");
		}
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) throws ExceptionCampoInvalido {
		if (peso > 0) {
			this.peso = peso;
			//Verificar se o preço é um valor positivo e maior que 0
		} else {
			throw new ExceptionCampoInvalido("Digite algo maior que 0 para o peso do produto.");
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) throws ExceptionCampoInvalido {
		if ((codigo != null) && (!codigo.isEmpty())) {
			this.codigo = codigo;
		} else {
			throw new ExceptionCampoInvalido("Digite um cófigo válido.");
		}
	}

	public void setCodigoAux(String codigo) throws ExceptionCampoInvalido {
		// metodo usado apenas para que não hajam codigos de barras iguais
		ProdutoDAO aux = new ProdutoDAO();
		ProdutoVO x = new ProdutoVO();
		x.setCodigo(codigo);
		if (aux.verificarCodigo(x)) {
			throw new ExceptionCampoInvalido("Código já existe no banco de dados.");
		}
	}
}
