package Model.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import Model.VO.TratamentoVO;

public class TratamentoDAO extends BaseDAO<TratamentoVO> {

	@Override
	public void cadastrar(TratamentoVO vo) {
		try {
			String sql = "insert into tratamento (data, valor, codigo, cpfCli, cpfFunc, descricao) values (?, ?, ?, ?, ?, ?);";
			PreparedStatement ptst;
			ptst = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ptst.setDate(1, new Date(vo.getData().getTimeInMillis()));
			ptst.setDouble(2, vo.getValor());
			ptst.setString(3, vo.getCodigo());
			ptst.setString(4, vo.getCpfCli());
			ptst.setString(5, vo.getCpfFunc());
			ptst.setString(6, vo.getDescricao());
			int linhas = ptst.executeUpdate();
			if (linhas == 0) {
				throw new SQLException("Nenhuma linha foi alterada.");
			}
			ResultSet rs = ptst.getGeneratedKeys();
			if (rs.next()) {
				vo.setId(rs.getLong(1));
			} else {
				throw new SQLException("Incersão falha.");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.closeConnection();
	}
	
	public ResultSet periodo(Calendar inicio, Calendar fim) {
		String sql = "select * from tratamento where data between ? and ?;";
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
	public ResultSet listar() {
		String sql = "select * from tratamento";
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
		String sql = "select * from tratamento where cpfcli like ?";
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
		String sql = "select * from tratamento where cpffunc like ?";
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
	
	public ResultSet findByCode(String vo) {
		String sql = "select * from tratamento where codigo = ?";
		PreparedStatement ptst;
		ResultSet rs = null;
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setString(1, vo);
			rs = ptst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.closeConnection();
		return rs;
	}

	@Override
	public void editar(TratamentoVO vo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluir(TratamentoVO vo) {
		// TODO Auto-generated method stub

	}

	@Override
	public ResultSet findById(TratamentoVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public ResultSet findByName(TratamentoVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

}
