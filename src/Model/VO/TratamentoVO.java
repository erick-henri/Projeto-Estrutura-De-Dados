package Model.VO;

import Exception.ExceptionCampoInvalido;

public class TratamentoVO {
	private long id;
	private AnimalVO animal;
	private String descricao;
	private double valor;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public AnimalVO getAnimal() {
		return animal;
	}

	public void setAnimal(AnimalVO animal) {
		if (animal != null) {
			this.animal = animal; 
		} else {
			System.out.println("Opera��o inv�lida");
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
				throw new ExceptionCampoInvalido("Descri��o n�o pode ter mais do que 500 caracteres");
			}
		} else {
			throw new ExceptionCampoInvalido("Digite alguma descri��o.");
		}
	}
	
	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
}
