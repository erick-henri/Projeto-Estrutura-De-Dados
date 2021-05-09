package Model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.VO.UsuarioVO;
public class UsuarioDAO extends PessoaDAO<UsuarioVO>{
	public boolean logar(UsuarioVO responsavel) {
		// Logar
		if (buscarLogin(responsavel)) {
			String sql = "select * from responsavel where usuario = ?";
			PreparedStatement ptst;
			ResultSet rs = null;
			String aux = "";
			try {
				ptst = getConnection().prepareStatement(sql);
				ptst.setString(1, responsavel.getUsuario());
				rs = ptst.executeQuery();
				if (rs.next()) {
					aux = rs.getString("senha");
					responsavel.setId(rs.getLong("idResponsavel"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			super.closeConnection();
			if ((!responsavel.getSenha().isEmpty()) && (responsavel.getSenha().equals(aux))) {
				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}
	}

	public boolean buscarLogin(UsuarioVO responsavel) {
		// Verifica se o login existe no banco de dados
		String sql = "select * from responsavel where usuario = ?;";
		PreparedStatement ptst;
		ResultSet rs = null;
		boolean aux = false;
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setString(1, responsavel.getUsuario());
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
	
	public void cadastrar(UsuarioVO responsavel) {
		try {
			super.cadastrar(responsavel);
			String sql = "insert into Responsavel" + "(usuario, senha, idpessoa) " + "values (?, ?, ?);";
			PreparedStatement ptst;
			ptst = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ptst.setString(1, responsavel.getUsuario());
			ptst.setString(2, responsavel.getSenha());
			ptst.setLong(3, responsavel.getIdPessoa());
			int linhas = ptst.executeUpdate();
			if (linhas == 0) {
				throw new SQLException("Nenhuma linha foi alterada.");
			}
			ResultSet rs = ptst.getGeneratedKeys();
			if (rs.next()) {
				responsavel.setId(rs.getLong(3));
			} else {
				throw new SQLException("Incersão falha.");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.closeConnection();
	}

	public void editar(UsuarioVO responsavel) {
		try {
			super.editar(responsavel);
			String sql = "update Responsavel set " + "usuario = ?, senha = ? " + "where idresponsavel = ?";
			PreparedStatement ptst;
			ptst = getConnection().prepareStatement(sql);
			ptst.setString(1, responsavel.getUsuario());
			ptst.setString(2, responsavel.getSenha());
			ptst.setLong(3, responsavel.getId());
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

	public void excluir(UsuarioVO responsavel) {
		// Excluir um responsavel pelo id
		String sql = "delete from responsavel where idresponsavel = ?";
		PreparedStatement ptst;
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, responsavel.getId());
			ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.excluir(responsavel); // deletar da tabela pessoa também
		super.closeConnection();
	}

	public ResultSet findById(UsuarioVO responsavel) {
		// Encontrar um responsavel pelo ID
		String sql = "select p.nome, p.cpf, p.telefone, p.endereco, p.idpessoa, r.idresponsavel, r.usuario, r.senha "
				+ "from pessoa as p, responsavel as r " + "where p.idpessoa = r.idpessoa and r.idresponsavel = ?";
		PreparedStatement ptst;
		ResultSet rs = null;
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, responsavel.getId());
			rs = ptst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.closeConnection();
		return rs;
	}

	public ResultSet findByIdPessoa(UsuarioVO responsavel) {
		// Encontrar um responsavel pelo IdPessoa
		String sql = "select p.nome, p.cpf, p.telefone, p.endereco, p.idpessoa, r.idresponsavel, r.usuario, r.senha "
				+ "from pessoa as p, responsavel as r " + "where p.idpessoa = r.idpessoa and p.idpessoa = ?";
		PreparedStatement ptst;
		ResultSet rs = null;
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, responsavel.getIdPessoa());
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
		String sql = "select p.nome, p.cpf, p.telefone, p.endereco, p.idpessoa, r.idresponsavel, r.usuario, r.senha "
				+ "from pessoa as p, responsavel as r " + "where p.idpessoa = r.idpessoa";
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

	public ResultSet findByName(UsuarioVO responsavel) {
		String sql = "select p.nome, p.cpf, p.telefone, p.endereco, p.idpessoa, r.idresponsavel, r.usuario, r.senha "
				+ "from pessoa as p, responsavel as r " + "where p.idpessoa = r.idpessoa and p.nome ilike ?";
		PreparedStatement ptst;
		ResultSet rs = null;
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setString(1, "%" + responsavel.getNome() + "%");
			rs = ptst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.closeConnection();
		return rs;
	}

	public ResultSet findByCpf(UsuarioVO cpf) {
		// Buscar por cpf
		String sql = "select p.nome, p.cpf, p.telefone, p.endereco, p.idpessoa, r.idresponsavel, r.usuario, r.senha "
				+ "from pessoa as p, responsavel as r " + "where p.idpessoa = r.idpessoa and p.cpf like ?";
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
