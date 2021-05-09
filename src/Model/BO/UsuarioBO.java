package Model.BO;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.DAO.UsuarioDAO;
import Model.VO.UsuarioVO;
import Exception.ExceptionCampoInvalido;


public class UsuarioBO {
	UsuarioDAO usuario = new UsuarioDAO();
	
	public void cadastrar (UsuarioVO user) throws IOException {
		usuario.cadastrar(user);
	}


	public void editar (UsuarioVO user) {
		usuario.editar(user);
	}
	
	public void excluir (UsuarioVO user) {
		usuario.excluir(user);
	}
	
	public ArrayList<UsuarioVO> findByNome(UsuarioVO resp) {
		// Pesquisa os responsaveis cadastrados pelo nome
		ResultSet rs = usuario.findByName(resp);
		ArrayList<UsuarioVO> responsaveis = new ArrayList<UsuarioVO>();
		
		try {
			while (rs.next()) {
				UsuarioVO aux = new UsuarioVO();
				aux.setNome(rs.getString("nome"));
				aux.setCpf(rs.getString("cpf"));
				aux.setTelefone(rs.getString("telefone"));
				aux.setId(rs.getLong("idresponsavel"));
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
	
	public ArrayList<UsuarioVO> findByCpf(UsuarioVO cpf) {
		// Pesquisa os responsaveis cadastrados pelo cpf
		ResultSet rs = usuario.findByCpf(cpf);
		ArrayList<UsuarioVO> responsaveis = new ArrayList<UsuarioVO>();
		
		try {
			while (rs.next()) {
				UsuarioVO aux = new UsuarioVO();
				aux.setNome(rs.getString("nome"));
				aux.setCpf(rs.getString("cpf"));
				aux.setTelefone(rs.getString("telefone"));
				aux.setId(rs.getLong("idresponsavel"));
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
	
	public UsuarioVO findById (UsuarioVO user) {
		// Está pesquisando um responsavel por ID
				ResultSet rs = usuario.findById(user);
				try {
					while(rs.next()) {
						user.setCpf(rs.getString("cpf"));
						user.setEndereco(rs.getString("endereco"));
						user.setNome(rs.getString("nome"));
						user.setTelefone(rs.getString("telefone"));
						user.setUsuario(rs.getString("usuario"));
						user.setSenha(rs.getString("senha"));
						user.setId(rs.getLong("idresponsavel"));
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
	public ArrayList<UsuarioVO> cpfs(UsuarioVO cpf) {
		// Pesquisa os responsaveis cadastrados pelo cpf
		ResultSet rs = usuario.findByCpf(cpf);
		ArrayList<UsuarioVO> responsaveis = new ArrayList<UsuarioVO>();
		
		try {
			while (rs.next()) {
				UsuarioVO aux = new UsuarioVO();
				aux.setNome(rs.getString("nome"));
				aux.setCpf(rs.getString("cpf"));
				aux.setTelefone(rs.getString("telefone"));
				aux.setId(rs.getLong("idresponsavel"));
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
	

	public boolean login(UsuarioVO resp) {
		// Tenta logar
		if(usuario.logar(resp)) {
			ResultSet rs = usuario.findById(resp);
			try {
				while(rs.next()) {
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
	
	public List<UsuarioVO> listar(){
		List<UsuarioVO> usuarios = new ArrayList<UsuarioVO>();
		
		return usuarios;
	}

}
