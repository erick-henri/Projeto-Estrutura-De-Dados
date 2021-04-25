package Model.VO;

import Exception.ExceptionCampoInvalido;

public class TratamentoVO {
	public long id;
	public AnimalVO animal;
	public String descricao;
	public double valor;
	
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
			System.out.println("Operação inválida");
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
			throw new ExceptionCampoInvalido("Digite alguma descrição.");
		}
	}
	
	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
}
