package Model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.VO.UsuarioVO;
public class UsuarioDAO extends PessoaDAO<UsuarioVO>{
	
	public boolean logar(UsuarioVO usuario) {
		// Logar
		if (buscarLogin(usuario)) {
			String sql = "select * from usuario where usuario = ?";
			PreparedStatement ptst;
			ResultSet rs = null;
			String aux = "";
			try {
				ptst = getConnection().prepareStatement(sql);
				ptst.setString(1, usuario.getUsuario());
				rs = ptst.executeQuery();
				if (rs.next()) {
					aux = rs.getString("senha");
					usuario.setId(rs.getLong("idusuario"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			super.closeConnection();
			if ((!usuario.getSenha().isEmpty()) && (usuario.getSenha().equals(aux))) {
				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}
	}

<<<<<<< HEAD
	
=======
>>>>>>> 6280a7a6360fa37a62f32520cb4d0a1fc49356d0
	public boolean buscarLogin(UsuarioVO usuario) {
		// Verifica se o login existe no banco de dados
		String sql = "select * from usuario where usuario = ?;";
		PreparedStatement ptst;
		ResultSet rs = null;
		boolean aux = false;
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setString(1, usuario.getUsuario());
			rs = ptst.executeQuery();
			if (rs.next()) {
				aux = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.closeConnection();
		return aux;
	}
	
	public void cadastrar(UsuarioVO usuario) {
		try {
			super.cadastrar(usuario);
			String sql = "insert into Usuario" + "(usuario, senha, idpessoa) " + "values (?, ?, ?);";
			PreparedStatement ptst;
			ptst = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ptst.setString(1, usuario.getUsuario());
			ptst.setString(2, usuario.getSenha());
			ptst.setLong(3, usuario.getIdPessoa());
			int linhas = ptst.executeUpdate();
			if (linhas == 0) {
				throw new SQLException("Nenhuma linha foi alterada.");
			}
			ResultSet rs = ptst.getGeneratedKeys();
			if (rs.next()) {
				usuario.setId(rs.getLong(1));
			} else {
				throw new SQLException("Incersão falha.");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.closeConnection();
	}

	public void editar(UsuarioVO usuario) {
		try {
			super.editar(usuario);
			String sql = "update usuario set " + "usuario = ?, senha = ? " + "where idusuario = ?";
			PreparedStatement ptst;
			ptst = getConnection().prepareStatement(sql);
			ptst.setString(1, usuario.getUsuario());
			ptst.setString(2, usuario.getSenha());
			ptst.setLong(3, usuario.getId());
			int linhas = ptst.executeUpdate();
			if (linhas == 0) {
				throw new SQLException("Atualização falho.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.closeConnection();
	}

	public void excluir(UsuarioVO usuario) {
		// Excluir um usuario pelo id
		String sql = "delete from usuario where idusuario = ?";
		PreparedStatement ptst;
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, usuario.getId());
			ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.excluir(usuario); // deletar da tabela pessoa também
		super.closeConnection();
	}

	public ResultSet findById(UsuarioVO usuario) {
		// Encontrar um usuario pelo ID
		String sql = "select p.nome, p.cpf, p.telefone, p.endereco, p.idpessoa, r.idusuario, r.usuario, r.senha "
				+ "from pessoa as p, usuario as r where p.idpessoa = r.idpessoa and r.idusuario = ?";
		PreparedStatement ptst;
		ResultSet rs = null;
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, usuario.getId());
			rs = ptst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.closeConnection();
		return rs;
	}

	public ResultSet findByIdPessoa(UsuarioVO usuario) {
		// Encontrar um usuario pelo IdPessoa
		String sql = "select p.nome, p.cpf, p.telefone, p.endereco, p.idpessoa, r.idusuario, r.usuario, r.senha "
				+ "from pessoa as p, usuario as r " + "where p.idpessoa = r.idpessoa and p.idpessoa = ?";
		PreparedStatement ptst;
		ResultSet rs = null;
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, usuario.getIdPessoa());
			rs = ptst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.closeConnection();
		return rs;
	}
	
	public ResultSet listar() {
		// listar todos os responsaveis cadastrados
		String sql = "select p.nome, p.cpf, p.telefone, p.endereco, p.idpessoa, r.idusuario, r.usuario, r.senha "
				+ "from pessoa as p, usuario as r " + "where p.idpessoa = r.idpessoa";
		PreparedStatement ptst;
		ResultSet rs = null;
		try {
			ptst = getConnection().prepareStatement(sql);
			rs = ptst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.closeConnection();
		return rs;
	}

	public ResultSet findByName(UsuarioVO usuario) {
		String sql = "select p.nome, p.cpf, p.telefone, p.endereco, p.idpessoa, r.idusuario, r.usuario, r.senha "
				+ "from pessoa as p, usuario as r " + "where p.idpessoa = r.idpessoa and p.nome ilike ?";
		PreparedStatement ptst;
		ResultSet rs = null;
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setString(1, "%" + usuario.getNome() + "%");
			rs = ptst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.closeConnection();
		return rs;
	}

	public ResultSet findByCpf(String cpf) {
		// Buscar por cpf
		String sql = "select p.nome, p.cpf, p.telefone, p.endereco, p.idpessoa, r.idusuario, r.usuario, r.senha "
				+ "from pessoa as p, usuario as r " + "where p.idpessoa = r.idpessoa and p.cpf like ?";
		PreparedStatement ptst;
		ResultSet rs = null;
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setString(1, cpf + "%");
			rs = ptst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.closeConnection();
		return rs;
	}
	
	
}
