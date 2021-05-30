package Model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import Model.DAO.TratamentoDAO;
import Model.VO.TratamentoVO;
import myList.ListaEncadeadaDupla;
import myList.MyInterfaceList;

public class TratamentoBO extends BaseBO<TratamentoVO> {
	TratamentoDAO tratamento = new TratamentoDAO();

	@Override
	public void cadastrar(TratamentoVO vo) {
		tratamento.cadastrar(vo);
	}

	@Override
	public MyInterfaceList<TratamentoVO> listar() {
		ResultSet rs = tratamento.listar();
		MyInterfaceList<TratamentoVO> tratamentos = new ListaEncadeadaDupla<TratamentoVO>();
		try {
			while (rs.next()) {
				TratamentoVO aux = new TratamentoVO();
				aux.setId(rs.getLong("idtratamento"));
				aux.setValor(rs.getDouble("valor"));
				Date data = (rs.getDate("data"));
				Calendar aux1 = Calendar.getInstance();
				aux1.setTime(data);
				aux.setData(aux1);
				aux.setCodigo(rs.getString("codigo"));
				aux.setCpfCli(rs.getString("cpfCli"));
				aux.setCpfFunc(rs.getString("cpffunc"));
				tratamentos.add(aux);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tratamentos;
	}

	public MyInterfaceList<TratamentoVO> relatorio(Calendar comeco, Calendar fim) {
		ResultSet rs = tratamento.periodo(comeco, fim);
		MyInterfaceList<TratamentoVO> tratamentos = new ListaEncadeadaDupla<TratamentoVO>();

		try {
			while (rs.next()) {
				TratamentoVO aux = new TratamentoVO();
				aux.setId(rs.getLong("idtratamento"));
				aux.setValor(rs.getDouble("valor"));
				Date data = (rs.getDate("data"));
				Calendar aux1 = Calendar.getInstance();
				aux1.setTime(data);
				aux.setData(aux1);
				aux.setCodigo(rs.getString("codigo"));
				aux.setCpfCli(rs.getString("cpfCli"));
				aux.setCpfFunc(rs.getString("cpffunc"));
				tratamentos.add(aux);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tratamentos;
	}

	public MyInterfaceList<TratamentoVO> listarCliente(String cpf) {
		ResultSet rs = tratamento.listarCliente(cpf);
		MyInterfaceList<TratamentoVO> tratamentos = new ListaEncadeadaDupla<TratamentoVO>();
		try {
			while (rs.next()) {
				TratamentoVO aux = new TratamentoVO();
				aux.setId(rs.getLong("idtratamento"));
				aux.setValor(rs.getDouble("valor"));
				Date data = (rs.getDate("data"));
				Calendar aux1 = Calendar.getInstance();
				aux1.setTime(data);
				aux.setData(aux1);
				aux.setCodigo(rs.getString("codigo"));
				aux.setCpfCli(rs.getString("cpfCli"));
				aux.setCpfFunc(rs.getString("cpffunc"));
				tratamentos.add(aux);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tratamentos;
	}

	public MyInterfaceList<TratamentoVO> listarFuncionario(String cpf) {
		ResultSet rs = tratamento.listarFuncionario(cpf);
		MyInterfaceList<TratamentoVO> tratamentos = new ListaEncadeadaDupla<TratamentoVO>();
		try {
			while (rs.next()) {
				TratamentoVO aux = new TratamentoVO();
				aux.setId(rs.getLong("idtratamento"));
				aux.setValor(rs.getDouble("valor"));
				Date data = (rs.getDate("data"));
				Calendar aux1 = Calendar.getInstance();
				aux1.setTime(data);
				aux.setData(aux1);
				aux.setCodigo(rs.getString("codigo"));
				aux.setCpfCli(rs.getString("cpfCli"));
				aux.setCpfFunc(rs.getString("cpffunc"));
				tratamentos.add(aux);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tratamentos;
	}

	public TratamentoVO findByCode(String vo) {
		ResultSet rs = tratamento.findByCode(vo);
		TratamentoVO aux = new TratamentoVO();
		try {
			while (rs.next()) {
				aux.setId(rs.getLong("idtratamento"));
				aux.setValor(rs.getDouble("valor"));
				Date data = (rs.getDate("data"));
				Calendar aux1 = Calendar.getInstance();
				aux1.setTime(data);
				aux.setData(aux1);
				aux.setCodigo(rs.getString("codigo"));
				aux.setCpfCli(rs.getString("cpfCli"));
				aux.setCpfFunc(rs.getString("cpffunc"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aux;
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
	public TratamentoVO findById(TratamentoVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyInterfaceList<TratamentoVO> findByName(TratamentoVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

}
