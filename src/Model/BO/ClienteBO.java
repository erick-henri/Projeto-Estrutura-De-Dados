package Model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;

import Model.VO.ClienteVO;
import myList.ListaEncadeadaDupla;
import myList.MyInterfaceList;
import Exception.ExceptionCampoInvalido;
import Model.DAO.ClienteDAO;

public class ClienteBO extends BaseBO<ClienteVO> implements ClienteInterBO{
	ClienteDAO cli = new ClienteDAO();

	public void cadastrar(ClienteVO cliente) {
		cli.cadastrar(cliente);
	}

	public void editar(ClienteVO cliente) {
		cli.editar(cliente);
	}

	public void excluir(ClienteVO cliente) {
		cli.excluir(cliente);
	}

	public MyInterfaceList<ClienteVO> findByName(ClienteVO cliente) {
		// Listar clientes a partir do nome
		ResultSet rs = cli.findByName(cliente);
		MyInterfaceList<ClienteVO> clientes = new ListaEncadeadaDupla<ClienteVO>();

		try {
			while (rs.next()) {
				ClienteVO aux = new ClienteVO();
				aux.setNome(rs.getString("nome"));
				aux.setCpf(rs.getString("cpf"));
				aux.setTelefone(rs.getString("telefone"));
				aux.setId(rs.getLong("idcliente"));
				aux.setIdPessoa(rs.getLong("idpessoa"));
				clientes.add(aux);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionCampoInvalido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clientes;
	}

	public MyInterfaceList<ClienteVO> findByCpf(String cpf) {
		// Listar clientes pelo cpf
		ResultSet rs = cli.findByCpf(cpf);
		MyInterfaceList<ClienteVO> clientes = new ListaEncadeadaDupla<ClienteVO>();

		try {
			while (rs.next()) {
				ClienteVO aux = new ClienteVO();
				aux.setNome(rs.getString("nome"));
				aux.setCpf(rs.getString("cpf"));
				aux.setTelefone(rs.getString("telefone"));
				aux.setId(rs.getLong("idcliente"));
				aux.setIdPessoa(rs.getLong("idpessoa"));
				clientes.add(aux);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionCampoInvalido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clientes;
	}

	public ClienteVO findById(ClienteVO cliente) {
		// Pesquisar um cliente pelo ID
		ResultSet rs = cli.findById(cliente);
		try {
			while (rs.next()) {
				cliente.setCpf(rs.getString("cpf"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setNome(rs.getString("nome"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setId(rs.getLong("idcliente"));
				cliente.setIdPessoa(rs.getLong("idpessoa"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionCampoInvalido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cliente;
	}

	public MyInterfaceList<ClienteVO> listar() {
		// Listar todos os clientes cadastrados
		ResultSet rs = cli.listar();
		MyInterfaceList<ClienteVO> clientes = new ListaEncadeadaDupla<ClienteVO>();

		try {
			while (rs.next()) {
				ClienteVO aux = new ClienteVO();
				aux.setNome(rs.getString("nome"));
				aux.setCpf(rs.getString("cpf"));
				aux.setTelefone(rs.getString("telefone"));
				aux.setId(rs.getLong("idcliente"));
				aux.setIdPessoa(rs.getLong("idpessoa"));
				clientes.add(aux);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionCampoInvalido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return clientes;
	}

}
