package Model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.VO.AnimalVO;

public class AnimalDAO extends BaseDAO<AnimalVO> implements AnimalInterDAO{

	public void cadastrar(AnimalVO animal) {
		try {
			String sql = "insert into animal (nome, cuidados, descricao, idcliente) values (?, ?, ?,?);";
			PreparedStatement ptst;
			ptst = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ptst.setString(1, animal.getNome());
			ptst.setString(2, animal.getCuidados());
			ptst.setString(3, animal.getDescricao());
			ptst.setLong(4, animal.getCliente().getId());
			int linhas = ptst.executeUpdate();
			if (linhas == 0) {
				throw new SQLException("Nenhuma linha foi alterada.");
			}
			ResultSet rs = ptst.getGeneratedKeys();
			if (rs.next()) {
				animal.setId(rs.getLong(1));
			} else {
				throw new SQLException("Incersão falha.");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.closeConnection();
	}

	@Override
	public void editar(AnimalVO animal) {
		try {
			String sql = "update animal set nome = ?, cuidados = ?, descricao = ? where idanimal = ?";
			PreparedStatement ptst;
			ptst = getConnection().prepareStatement(sql);
			ptst.setString(1, animal.getNome());
			ptst.setString(2, animal.getDescricao());
			ptst.setLong(3, animal.getId());
			int linhas = ptst.executeUpdate();
			if (linhas == 0) {
				throw new SQLException("Atualização falha.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.closeConnection();
		
	}

	@Override
	public void excluir(AnimalVO animal) {
		// Excluir um animal pelo id
		String sql = "delete from animal where idanimal = ?";
		PreparedStatement ptst;
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, animal.getId());
			ptst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.closeConnection();
		
	}
	
	@Override
	public ResultSet findById(AnimalVO animal) {
		// Encontrar um usuario pelo ID
				String sql = "select * from animal where idanimal = ? and idcliente = ?";
				PreparedStatement ptst;
				ResultSet rs = null;
				try {
					ptst = getConnection().prepareStatement(sql);
					ptst.setLong(1, animal.getId());
					ptst.setLong(2, animal.getCliente().getId());
					rs = ptst.executeQuery();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				super.closeConnection();
				return rs;
	}

	@Override
	public ResultSet findByName(AnimalVO animal) {
		String sql = "select * from animal where nome ilike ? and idcliente = ?";
		PreparedStatement ptst;
		ResultSet rs = null;
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setString(1, "%" + animal.getNome() + "%");
			ptst.setLong(2, animal.getCliente().getId());
			rs = ptst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.closeConnection();
		return rs;
	}

	public ResultSet listar(AnimalVO vo) {
		// listar todos os animais cadastrados
		String sql = "select * from animal where idcliente = ?";
		PreparedStatement ptst;
		ResultSet rs = null;
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, vo.getCliente().getId());
			rs = ptst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.closeConnection();
		return rs;
	}
	
	@Override
	public ResultSet listar() {
		// TODO Auto-generated method stub
		return null;
	}

}
