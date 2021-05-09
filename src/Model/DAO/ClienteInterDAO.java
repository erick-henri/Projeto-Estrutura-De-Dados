package Model.DAO;

import java.sql.ResultSet;

import Model.VO.ClienteVO;

public interface ClienteInterDAO {
	public ResultSet findByIdPessoa(ClienteVO cliente);
	public ResultSet findByCpf(String cpf);
	public ResultSet findById(ClienteVO cliente);
	public ResultSet findByName(ClienteVO cliente);
}
