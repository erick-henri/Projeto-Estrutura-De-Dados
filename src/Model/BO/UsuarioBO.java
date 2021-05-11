package Model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import Model.DAO.UsuarioDAO;
import Model.VO.UsuarioVO;
import myList.ListaEncadeadaDupla;
import myList.MyInterfaceList;
import Exception.ExceptionCampoInvalido;

public class UsuarioBO extends BaseBO<UsuarioVO> implements UsuarioInterBO{
	UsuarioDAO usuario = new UsuarioDAO();

	public void cadastrar(UsuarioVO user){
		usuario.cadastrar(user);
	}

	public void editar(UsuarioVO user) {
		usuario.editar(user);
	}

	public void excluir(UsuarioVO user) {
		usuario.excluir(user);
	}

	public MyInterfaceList<UsuarioVO> findByName(UsuarioVO user) {
		// Pesquisa os responsaveis cadastrados pelo nome
		ResultSet rs = usuario.findByName(user);
		MyInterfaceList<UsuarioVO> responsaveis = new ListaEncadeadaDupla<UsuarioVO>();

		try {
			while (rs.next()) {
				UsuarioVO aux = new UsuarioVO();
				aux.setNome(rs.getString("nome"));
				aux.setCpf(rs.getString("cpf"));
				aux.setTelefone(rs.getString("telefone"));
				aux.setId(rs.getLong("idusuario"));
				aux.setIdPessoa(rs.getLong("idpessoa"));
				responsaveis.add(aux);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionCampoInvalido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return responsaveis;
	}

	public MyInterfaceList<UsuarioVO> findByCpf(String cpf) {
		// Pesquisa os responsaveis cadastrados pelo cpf
		ResultSet rs = usuario.findByCpf(cpf);
		MyInterfaceList<UsuarioVO> responsaveis = new ListaEncadeadaDupla<UsuarioVO>();

		try {
			while (rs.next()) {
				UsuarioVO aux = new UsuarioVO();
				aux.setNome(rs.getString("nome"));
				aux.setCpf(rs.getString("cpf"));
				aux.setTelefone(rs.getString("telefone"));
				aux.setId(rs.getLong("idusuario"));
				aux.setIdPessoa(rs.getLong("idpessoa"));
				responsaveis.add(aux);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionCampoInvalido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return responsaveis;
	}

	public UsuarioVO findById(UsuarioVO user) {
		// Está pesquisando um responsavel por ID
		ResultSet rs = usuario.findById(user);
		try {
			while (rs.next()) {
				user.setCpf(rs.getString("cpf"));
				user.setEndereco(rs.getString("endereco"));
				user.setNome(rs.getString("nome"));
				user.setTelefone(rs.getString("telefone"));
				user.setUsuario(rs.getString("usuario"));
				user.setSenha(rs.getString("senha"));
				user.setId(rs.getLong("idusuario"));
				user.setIdPessoa(rs.getLong("idpessoa"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionCampoInvalido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	public boolean login(UsuarioVO resp) {
		// Tenta logar
		if (usuario.logar(resp)) {
			ResultSet rs = usuario.findById(resp);
			try {
				while (rs.next()) {
					resp.setCpf(rs.getString("cpf"));
					resp.setEndereco(rs.getString("endereco"));
					resp.setNome(rs.getString("nome"));
					resp.setTelefone(rs.getString("telefone"));
					resp.setId(rs.getLong("idresponsavel"));
					resp.setIdPessoa(rs.getLong("idpessoa"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExceptionCampoInvalido e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		} else {
			return false;
		}
	}

	public MyInterfaceList<UsuarioVO> listar() {

		ResultSet rs = usuario.listar();
		MyInterfaceList<UsuarioVO> usuarios = new ListaEncadeadaDupla<UsuarioVO>();

		try {
			while (rs.next()) {
				UsuarioVO aux = new UsuarioVO();
				aux.setNome(rs.getString("nome"));
				aux.setId(rs.getLong("idusuario"));
				aux.setTelefone(rs.getString("telefone"));
				usuarios.add(aux);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionCampoInvalido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return usuarios;
	}

}
