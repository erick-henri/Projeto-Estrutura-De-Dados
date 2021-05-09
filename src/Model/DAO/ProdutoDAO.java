package Model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.VO.ProdutoVO;

public class ProdutoDAO extends BaseDAO<ProdutoVO> {

	public void cadastrar(ProdutoVO vo) {
		// Aqui irá cadastrar os produtos utilizando um banco de dados
		String sql = "insert into produto (nome, descricao, peso, preco, quantidade, codigo)"
				+ "values (?,?,?,?,?,?);";
		
		PreparedStatement ptst;
		
			try {
				
				//primeiro adiciona tudo na tabela do banco de dados
				ptst = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ptst.setString(1, vo.getNome());
				ptst.setString(2, vo.getDescricao());
				ptst.setDouble(3, vo.getPeso());
				ptst.setDouble(4, vo.getPreco());
				ptst.setInt(5, vo.getQuantidade());
				ptst.setString(6, vo.getCodigo());

				
				//Depois incrementa a chave primario da tabela, que seria o id
				int linhas = ptst.executeUpdate();
				
				if (linhas == 0) {
					throw new SQLException ("Nada foi adicionado.");
				}
				
				ResultSet rs = ptst.getGeneratedKeys();
				if (rs.next()) {
					vo.setId(rs.getLong(1));
				} else {
					throw new SQLException ("Falhou");
				}
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			super.closeConnection();

	}

	public void editar(ProdutoVO vo) {
		// Aqui irá editar os produtos contatno que existam
		String sql = "update produto set nome = ?, peso = ?, "
				+ "preco = ?, descricao = ?, codigo = ?, quantidade = ? where idproduto = ?";
		
		PreparedStatement ptst;
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setString(1, vo.getNome());
			ptst.setDouble(2, vo.getPeso());
			ptst.setDouble(3, vo.getPreco());
			ptst.setString(4, vo.getDescricao());
			ptst.setString(5, vo.getCodigo());
			ptst.setInt(6, vo.getQuantidade());
			ptst.setLong(7, vo.getId());
			int linhas = ptst.executeUpdate();
			if (linhas == 0) {
				throw new SQLException("Nada foi editado.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.closeConnection();
	}

	@Override
	public void excluir(ProdutoVO vo) {
		// Aqui irá excluir os produtos
		String sql = "delete from produto where idproduto = ?";
		PreparedStatement ptst;
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, vo.getId());
			int linhas = ptst.executeUpdate();
			if (linhas == 0) {
				throw new SQLException ("Nada foi excluido.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.closeConnection();
	}

	@Override
	public ResultSet findById(ProdutoVO vo) {
		// Encontrar um produto a partir do ID (geralmente será usado mais pelas classes, não pelo usuario)
		String sql = "select * from produto where idproduto = ?";
		PreparedStatement ptst;
		ResultSet rs = null;
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setLong(1, vo.getId());
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
		// selecionar todos os itens dentro da tabela produto
		String sql = "select * from produto";
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

	@Override
	public ResultSet findByName(ProdutoVO vo) {
		// Selecionar todos os produtos que tiverem o nome parecido com o digitado
		String sql = "select * from produto where nome ilike ?";
		PreparedStatement ptst;
		ResultSet rs = null;
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setString(1,"%"+ vo.getNome() + "%");
			rs = ptst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.closeConnection();
		return rs;
	}
	
	public boolean verificarCodigo (ProdutoVO vo) {
		//Esse codigo é apenas para verificar os codigos dos produtos
		//para que quando adicionar algum produto novo, eles não tenham o mesmo codigo
		
		String sql = "select * from produto where codigo = ?";
		PreparedStatement ptst;
		ResultSet rs = null;
		
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setString(1, vo.getCodigo());
			rs = ptst.executeQuery();
			while (rs.next()) {
				super.closeConnection();
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.closeConnection();
		return false;
	}
	
	public ResultSet findByCode(ProdutoVO vo) {
		//diferente do anterior, esse aqui vai achar todos os códigos que tenham algo
		//parecido com o que foi digitado
		//ex: digitado : 1113
		//achados: 1113465, 1113548,  1541113
		String sql = "select * from produto where codigo ilike ?";
		PreparedStatement ptst;
		ResultSet rs = null;
		
		try {
			ptst = getConnection().prepareStatement(sql);
			ptst.setString(1,"%"+ vo.getCodigo() + "%");
			rs = ptst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.closeConnection();
		return rs;
	}
	
	

}
