package Model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.VO.ClienteVO;
import Exception.ExceptionCampoInvalido;
import Model.DAO.ClienteDAO;

public class ClienteBO {
	ClienteDAO cli = new ClienteDAO();
	public void cadastrar (ClienteVO cliente) {
		cli.cadastrar(cliente);
	}
	
	public void editar (ClienteVO cliente) {
		cli.editar(cliente);
	}
	
	public void excluir (ClienteVO cliente) {
		cli.excluir(cliente);
	}
	
	public ArrayList<ClienteVO> findByNome(ClienteVO cliente){
		//Listar clientes a partir do nome
		ResultSet rs = cli.findByName(cliente);
		ArrayList<ClienteVO> clientes = new ArrayList<ClienteVO>();
		
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
	
	public ArrayList<ClienteVO> findByCpf(ClienteVO resp){
		// Listar clientes pelo cpf
		ResultSet rs = cli.findByCpf(resp);
		ArrayList<ClienteVO> clientes = new ArrayList<ClienteVO>();
		
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
	
	public ClienteVO findById (ClienteVO cliente) {
		// Pesquisar um cliente pelo ID
				ResultSet rs = cli.findById(cliente);
				try {
					while(rs.next()) {
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
	
	public List<ClienteVO> listar(){
		// Listar todos os clientes cadastrados
				ResultSet rs = cli.listar();
				ArrayList<ClienteVO> clientes = new ArrayList<ClienteVO>();
				
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
