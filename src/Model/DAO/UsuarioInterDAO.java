package Model.DAO;

import java.sql.ResultSet;

import Model.VO.UsuarioVO;

public interface UsuarioInterDAO {
	public boolean buscarLogin(UsuarioVO responsavel);
	public boolean logar(UsuarioVO responsavel);
	public ResultSet findByIdPessoa(UsuarioVO responsavel);
	public ResultSet findByCpf(String cpf);
}
