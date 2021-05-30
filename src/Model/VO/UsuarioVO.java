package Model.VO;

import Exception.ExceptionCampoInvalido;
import Exception.ExceptionLoginExistente;
import Model.DAO.UsuarioDAO;
import Model.VO.UsuarioVO;

public class UsuarioVO extends PessoaVO {
	private String usuario;
	private String senha;
	private long id;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuarioAux(String usuario) throws ExceptionLoginExistente {
		//Verificar se o Usuario existe no Banco de Dados.
		UsuarioDAO aux = new UsuarioDAO();
		UsuarioVO x = new UsuarioVO();
		try {
			x.setUsuario(usuario);
		} catch (ExceptionCampoInvalido e) {
			e.printStackTrace();
		}
		
		if (aux.buscarLogin(x)) {
			throw new ExceptionLoginExistente("Usuário já existe.");
		} else return;
	}
	
	public void setUsuario(String usuario) throws ExceptionCampoInvalido {
		if ((usuario != null) && (!usuario.isEmpty())) {
			this.usuario = usuario;
		} else {
			throw new ExceptionCampoInvalido("Usuário inválido.");
		}
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) throws ExceptionCampoInvalido {
		if ((senha != null) && (!senha.isEmpty())) {
			if (senha.length() >= 5) {
				this.senha = senha;
			} else {
				throw new ExceptionCampoInvalido("Senha precisa ter pelo menos 5 caracteres.");
			}
		} else {
			throw new ExceptionCampoInvalido("Senha inválida.");
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
