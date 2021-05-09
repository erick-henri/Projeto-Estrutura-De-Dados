package Model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.DAO.ClienteInterDAO;
import Model.DAO.PessoaDAO;
import Model.VO.ClienteVO;

public class ClienteDAO extends PessoaDAO<ClienteVO> implements ClienteInterDAO{
	public void cadastrar(ClienteVO cliente) {
		try {
			super.cadastrar(cliente);
			String sql = "insert into Cliente (idPessoa) values (?);";
			PreparedStatement ptst;
			ptst = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ptst.setLong(1, cliente.getIdPessoa());
			int linhas = ptst.executeUpdate();
			if (linhas == 0) {
				throw new SQLException("Nenhuma linha foi alterada.");
			}
			
			ResultSet rs = ptst.getGeneratedKeys();
			
			if(rs.next()) {
				cliente.setId(rs.getLong(1));
			} else {
				throw new SQLException("Incersão falhou.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.closeConnection();
	}

	public void excluir(ClienteVO cliente) {
		//excluir um cliente por id
		String sql = "delete from cliente where idcliente = ?";
		PreparedStatement ptst;
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, cliente.getId());
			System.out.println(cliente.getId());
			ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		super.excluir(cliente);//deletar da tabela pessoas também
		super.closeConnection();
	}

	public void editar(ClienteVO cliente) {
		//editar um cliente
		super.editar(cliente);
	}

	public ResultSet findById(ClienteVO cliente) {
		String sql = "select p.nome, p.cpf, p.telefone, p.endereco, p.idpessoa, c.idcliente " 
				+ "from pessoa as p, cliente as c "
				+ "where p.idpessoa = c.idpessoa and c.idcliente = ?";
		PreparedStatement ptst;
		ResultSet rs = null;
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, cliente.getId());
			rs = ptst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.closeConnection();
		return rs;
	}
	
	public ResultSet findByIdPessoa(ClienteVO cliente) {
		String sql = "select p.nome, p.cpf, p.telefone, p.endereco, p.idpessoa, c.idcliente " 
				+ "from pessoa as p, cliente as c "
				+ "where p.idpessoa = ?";
		PreparedStatement ptst;
		ResultSet rs = null;
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, cliente.getIdPessoa());
			rs = ptst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.closeConnection();
		return rs;
	}

	public ResultSet findByName(ClienteVO cliente) {
		String sql = "select p.nome, p.cpf, p.telefone, p.endereco, c.idcliente, p.idpessoa "
				+ "from pessoa as p, cliente as c "
				+ "where p.idpessoa = c.idpessoa and p.nome ilike ?";
		PreparedStatement ptst;
		ResultSet rs = null;
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setString(1, "%" + cliente.getNome() + "%");
			rs = ptst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.closeConnection();
		return rs;
	}
	
	public ResultSet findByCpf(ClienteVO resp) {
		String sql = "select p.nome, p.cpf, p.telefone, p.endereco, c.idcliente, p.idpessoa "
				+ "from pessoa as p, cliente as c "
				+ "where p.idpessoa = c.idpessoa and p.cpf like ?";
		PreparedStatement ptst;
		ResultSet rs = null;
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setString(1, resp + "%");
			rs = ptst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.closeConnection();
		return rs;
	}

	public ResultSet listar() {
		String sql = "select p.nome, p.cpf, p.endereco, p.telefone, c.idcliente, p.idpessoa "
				+ "from Pessoa as p, cliente as c "
				+ "where p.idpessoa = c.idpessoa;";
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

	@Override
	public ResultSet findByCpf(String cpf) {
		// TODO Auto-generated method stub
		return null;
	}
}
