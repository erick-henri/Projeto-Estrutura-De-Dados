package Model.DAO;

import java.sql.Connection;
import java.sql.ResultSet;

public interface BaseInterDAO<VO> {
	public Connection getConnection();
	public abstract void cadastrar(VO entity);
	public abstract void editar(VO entity);
	public abstract void excluir(VO entity);
	public abstract ResultSet findById(VO entity);
	public abstract ResultSet listar();
	public abstract ResultSet findByName(VO entity);
	
}
