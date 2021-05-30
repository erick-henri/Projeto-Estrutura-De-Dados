package Model.VO;

import Exception.ExceptionCampoInvalido;
import Model.DAO.PessoaDAO;
import Model.VO.UsuarioVO;

public abstract class PessoaVO {
	private String nome;
	private String cpf;
	private String endereco;
	private String telefone;
	private long idPessoa;

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

	public String getCpf() {
		return cpf;
	}

	public void setCpfAux(String cpf) throws ExceptionCampoInvalido {
		PessoaDAO<UsuarioVO> aux = new PessoaDAO<UsuarioVO>();
		UsuarioVO resp = new UsuarioVO();
		resp.setCpf(cpf);
		if (aux.cpf(resp)) {
			throw new ExceptionCampoInvalido("CPF já existe no banco de dados.");
		} else return;
	}
	
	public void setCpf(String cpf) throws ExceptionCampoInvalido {
		// Validando
		if ((cpf != null) && (!cpf.isEmpty())) {
			if (cpf.length() == 11) {
				if (cpf.substring(0, 10).matches("[0-9]*")) {
					if ((!cpf.equals("00000000000")) && (!cpf.equals("11111111111")) && (!cpf.equals("22222222222"))
							&& (!cpf.equals("33333333333")) && (!cpf.equals("44444444444"))
							&& (!cpf.equals("55555555555")) && (!cpf.equals("66666666666"))
							&& (!cpf.equals("77777777777")) && (!cpf.equals("88888888888"))
							&& (!cpf.equals("99999999999"))) {
						this.cpf = cpf;
					} else {
						// Digitar números repetidos
						throw new ExceptionCampoInvalido("CPF não ser apenas números iguais.");
					}
				} else {
					// Digitou uma letra
					throw new ExceptionCampoInvalido("CPF só pode ter números.");
				}
			} else {
				// Tamanho inválido
				throw new ExceptionCampoInvalido("CPF tem que ter pelo menos 11 caracteres.");
			}
		} else {
			// Se a variavel vier vazia
			throw new ExceptionCampoInvalido("CPF inválido.");
		}
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String estado, String cidade, String bairro, String rua, String numero)
			throws ExceptionCampoInvalido {
			//Irá verificar se todos os campos estão preenxidos
		if ((estado != null) && (cidade != null) && (bairro != null) && (rua != null) && (numero != null)) {
			if ((!estado.isEmpty()) && (!cidade.isEmpty()) && (!bairro.isEmpty()) && (!rua.isEmpty())
					&& (!numero.isEmpty())) {
				this.endereco = bairro + ", " + rua + ", " + numero + ", " + cidade + ", " + estado;
				//Salvar tudo separado por , para ficar mais facil de verificar depois
			} else {
				throw new ExceptionCampoInvalido("Erro ao salvar o enderço.");
			}
		} else {
			throw new ExceptionCampoInvalido("Erro ao salvar o endereço.");
		}
	}

	public void setEndereco(String endereco) {
		// Construtor set usado como auxiliar na hora de buscar no BD o endereço
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) throws ExceptionCampoInvalido {
		if ((telefone != null) && (!telefone.isEmpty())) {
			if (telefone.length() == 11) {
				if (telefone.substring(0, 10).matches("[0-9]*")) {
					this.telefone = telefone;
				} else {
					throw new ExceptionCampoInvalido("Digitar apenas números em telefone.");
				}
			} else {
				throw new ExceptionCampoInvalido("Telefone tem que ter 11 digitos.");
			}
		} else {
			throw new ExceptionCampoInvalido("Telefone inválido.");
		}

	}

	public long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(long idPessoa) {
		this.idPessoa = idPessoa;
	}

}
