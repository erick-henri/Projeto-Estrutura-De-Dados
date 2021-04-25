package Model.VO;

import Exception.ExceptionCampoInvalido;

public class AnimalVO {
	public String nome;
	public long id;
	public String cuidados;
	public String descricao;
	public ClienteVO cliente;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws ExceptionCampoInvalido {
		if ((nome != null) && (!nome.isEmpty())) {
			this.nome = nome;
		} else {
			throw new ExceptionCampoInvalido("Nome inválido");
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getCuidados() {
		return cuidados;
	}

	public void setCuidados(String cuidados) throws ExceptionCampoInvalido {
		if (cuidados != null) {
			if ((cuidados.length() <= 500) && (!cuidados.isEmpty())) {
				this.cuidados = cuidados;
			} else {
				throw new ExceptionCampoInvalido("A descrição dos cuidados não pode ter mais do que 500 caracteres");
			}
		} else {
			throw new ExceptionCampoInvalido("Digite algum cuidado.");
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
			throw new ExceptionCampoInvalido("Digite alguma cdescrição.");
		}
	}
	
	public ClienteVO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteVO cliente) {
		if (cliente != null) {
			this.cliente = cliente; 
		} else {
			System.out.println("Operação inválida");
		}
	}
}
