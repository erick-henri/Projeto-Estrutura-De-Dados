package Model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class BaseDAO<VO> implements BaseInterDAO<VO>{
	private static Connection conn = null;
	private static final String url = "jdbc:postgresql://localhost:5432/Projeto_ED";
	private static final String user = "postgres";
	private static final String senha = "vouverine";
	
	public Connection getConnection() {
		if (conn == null) {
			try {
				conn = DriverManager.getConnection(url, user, senha);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return conn;
		} else return conn;
	}
	
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		conn = null;
	}
	
	public abstract void cadastrar(VO vo);
	public abstract void editar(VO vo);
	public abstract void excluir(VO vo);
	public abstract ResultSet findById(VO vo);
	public abstract ResultSet listar();
	public abstract ResultSet findByName(VO vo);
	
}
