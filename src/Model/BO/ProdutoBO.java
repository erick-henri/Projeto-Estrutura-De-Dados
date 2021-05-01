package Model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.DAO.ProdutoDAO;
import Model.VO.ProdutoVO;
import Exception.ExceptionCampoInvalido;

public class ProdutoBO {
	ProdutoDAO aux = new ProdutoDAO();
	public void cadastrar(ProdutoVO prod) {
		aux.cadastrar(prod);
	}

	public void editar(ProdutoVO prod) {
		aux.editar(prod);
	}

	public void excluir(ProdutoVO prod) {
		aux.excluir(prod);
	}

	public List<ProdutoVO> listar (ProdutoVO prod) {
		//Listar todos os produtos existentes
		ResultSet rs = aux.listar();
		List<ProdutoVO> produtos = new ArrayList<ProdutoVO>();
		try {
			while (rs.next()) {
				ProdutoVO p = new ProdutoVO();
				p.setNome(rs.getString("nome"));
				p.setPreco(rs.getDouble("preco"));
				p.setQuantidade(rs.getInt("quantidadeTotal"));
				p.setDescricao(rs.getString("descricao"));
				p.setId(rs.getLong("idproduto"));
				p.setCodigo(rs.getString("codigo"));
				produtos.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionCampoInvalido e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return produtos;
	}
	
	public ProdutoVO findById(ProdutoVO prod) {
		//procurar um produto no banco de dados que possua o id digitado
		ResultSet rs = aux.findById(prod);
		ProdutoVO produto = new ProdutoVO();
		try {
			while (rs.next()) {
				produto.setNome(rs.getString("nome"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setPeso(rs.getDouble("peso"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setId(rs.getLong("idproduto"));
				produto.setCodigo(rs.getString("codigo"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionCampoInvalido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return produto;
	}
	
	public ArrayList<ProdutoVO> findByName(ProdutoVO prod){
		ResultSet rs = aux.findByName(prod);
		ArrayList<ProdutoVO> produtos = new ArrayList<ProdutoVO>();
		try {
			while (rs.next()) {
				ProdutoVO produto = new ProdutoVO();
				produto.setNome(rs.getString("nome"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setPeso(rs.getDouble("peso"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setId(rs.getLong("idproduto"));
				produto.setCodigo(rs.getString("codigo"));
				produtos.add(produto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionCampoInvalido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return produtos;
	}
	
	public ArrayList<ProdutoVO> findByCode(ProdutoVO prod){
		ResultSet rs = aux.findByCode(prod);
		ArrayList<ProdutoVO> produtos = new ArrayList<ProdutoVO>();
		try {
			while (rs.next()) {
				ProdutoVO produto = new ProdutoVO();
				produto.setNome(rs.getString("nome"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setPeso(rs.getDouble("peso"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setId(rs.getLong("idproduto"));
				produto.setCodigo(rs.getString("codigo"));
				produtos.add(produto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionCampoInvalido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return produtos;
	}
	
}
