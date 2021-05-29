package Model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import Model.DAO.VendaDAO;
import Model.VO.VendaVO;
import myList.ListaEncadeadaDupla;
import myList.MyInterfaceList;

public class VendaBO extends BaseBO<VendaVO>{
	VendaDAO venda = new VendaDAO();
	@Override
	public void cadastrar(VendaVO vo) {
		venda.cadastrar(vo);
	}
	
	@Override
	public MyInterfaceList<VendaVO> listar() {
		ResultSet rs = venda.listar();
		MyInterfaceList<VendaVO> vendas = new ListaEncadeadaDupla<VendaVO>();
		try {
			while (rs.next()) {
				VendaVO aux = new VendaVO();
				aux.setId(rs.getLong("idvenda"));
				aux.setValor(rs.getDouble("valor"));
				Date data = (rs.getDate("data"));
				Calendar aux1 = Calendar.getInstance();
				aux1.setTime(data);
				aux.setData(aux1);
				aux.setCodigo(rs.getString("codigo"));
				aux.setCpfCli(rs.getString("cpfCli"));
				aux.setCpfFunc(rs.getString("cpffunc"));
				vendas.add(aux);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return vendas;

	}

	public MyInterfaceList<VendaVO> relatorio(Calendar comeco, Calendar fim){
		ResultSet rs = venda.periodo(comeco, fim);
		MyInterfaceList<VendaVO> vendas = new ListaEncadeadaDupla<VendaVO>();

		try {
			while (rs.next()) {
				VendaVO aux = new VendaVO();
				aux.setId(rs.getLong("idvenda"));
				aux.setValor(rs.getDouble("valor"));
				Date data = (rs.getDate("data"));
				Calendar aux1 = Calendar.getInstance();
				aux1.setTime(data);
				aux.setData(aux1);
				aux.setCodigo(rs.getString("codigo"));
				aux.setCpfCli(rs.getString("cpfCli"));
				aux.setCpfFunc(rs.getString("cpffunc"));
				vendas.add(aux);
			}		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vendas;
	}
	
	public MyInterfaceList<VendaVO> listarCliente(String cpf){
		ResultSet rs = venda.listarCliente(cpf);
		MyInterfaceList<VendaVO> vendas = new ListaEncadeadaDupla<VendaVO>();

		try {
			while (rs.next()) {
				VendaVO aux = new VendaVO();
				aux.setId(rs.getLong("idvenda"));
				aux.setValor(rs.getDouble("valor"));
				Date data = (rs.getDate("data"));
				Calendar aux1 = Calendar.getInstance();
				aux1.setTime(data);
				aux.setData(aux1);
				aux.setCodigo(rs.getString("codigo"));
				aux.setCpfCli(rs.getString("cpfCli"));
				aux.setCpfFunc(rs.getString("cpffunc"));
				vendas.add(aux);
			}		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vendas;
	}
	
	public MyInterfaceList<VendaVO> listarFuncionario(String cpf){
		ResultSet rs = venda.listarFuncionario(cpf);
		MyInterfaceList<VendaVO> vendas = new ListaEncadeadaDupla<VendaVO>();

		try {
			while (rs.next()) {
				VendaVO aux = new VendaVO();
				aux.setId(rs.getLong("idvenda"));
				aux.setValor(rs.getDouble("valor"));
				Date data = (rs.getDate("data"));
				Calendar aux1 = Calendar.getInstance();
				aux1.setTime(data);
				aux.setData(aux1);
				aux.setCodigo(rs.getString("codigo"));
				aux.setCpfCli(rs.getString("cpfCli"));
				aux.setCpfFunc(rs.getString("cpffunc"));
				vendas.add(aux);
			}		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vendas;
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
	public VendaVO findById(VendaVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public MyInterfaceList<VendaVO> findByName(VendaVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

}
