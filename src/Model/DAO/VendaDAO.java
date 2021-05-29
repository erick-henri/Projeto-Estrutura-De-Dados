package Model.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import Model.VO.VendaVO;

public class VendaDAO extends BaseDAO<VendaVO>{

	@Override
	public void cadastrar(VendaVO venda) {
		try {
			String sql = "insert into venda (data, valor, codigo, cpfCli, cpfFunc) values (?, ?, ?, ?, ?);";
			PreparedStatement ptst;
			ptst = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ptst.setDate(1, new Date(venda.getData().getTimeInMillis()));
			ptst.setDouble(2, venda.getValor());
			ptst.setString(3, venda.getCodigo());
			ptst.setString(4, venda.getCpfCli());
			ptst.setString(5, venda.getCpfFunc());
			int linhas = ptst.executeUpdate();
			if (linhas == 0) {
				throw new SQLException("Nenhuma linha foi alterada.");
			}
			ResultSet rs = ptst.getGeneratedKeys();
			if (rs.next()) {
				venda.setId(rs.getLong(1));
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
	public ResultSet listar() {
		String sql = "select * from venda";
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

	public ResultSet listarCliente(String cpf) {
		String sql = "select * from venda where cpfcli like ?";
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
	
	public ResultSet listarFuncionario(String cpf) {
		String sql = "select * from venda where cpffunc like ?";
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
	
	public ResultSet periodo(Calendar inicio, Calendar fim) {
		String sql = "select * from venda where data between ? and ?;";
		PreparedStatement ptst;
		ResultSet rs = null;
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setDate(1, new Date (inicio.getTimeInMillis()));
			ptst.setDate(2, new Date (fim.getTimeInMillis()));
			rs = ptst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.closeConnection();
		return rs;
	}
	
	@Override
	public void editar(VendaVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(VendaVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultSet findById(VendaVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet findByName(VendaVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

}
