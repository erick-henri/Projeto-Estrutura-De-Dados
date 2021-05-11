package Model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import Model.DAO.ProdutoDAO;
import Model.VO.ProdutoVO;
import myList.ListaEncadeadaDupla;
import myList.MyInterfaceList;
import Exception.ExceptionCampoInvalido;

public class ProdutoBO extends BaseBO<ProdutoVO> implements ProdutoInterBO{
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

	public	MyInterfaceList<ProdutoVO> listar () {
		//Listar todos os produtos existentes
		ResultSet rs = aux.listar();
		MyInterfaceList<ProdutoVO> produtos = new ListaEncadeadaDupla<ProdutoVO>();
		try {
			while (rs.next()) {
				ProdutoVO p = new ProdutoVO();
				p.setNome(rs.getString("nome"));
				p.setPreco(rs.getDouble("preco"));
				p.setPeso(rs.getDouble("peso"));
				p.setQuantidade(rs.getInt("quantidade"));
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
				produto.setQuantidade(rs.getInt("quantidade"));
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
	
	public MyInterfaceList<ProdutoVO> findByName(ProdutoVO prod){
		ResultSet rs = aux.findByName(prod);
		MyInterfaceList<ProdutoVO> produtos = new ListaEncadeadaDupla<ProdutoVO>();
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
	
	public MyInterfaceList<ProdutoVO> findByCode(ProdutoVO prod){
		ResultSet rs = aux.findByCode(prod);
		MyInterfaceList<ProdutoVO> produtos = new ListaEncadeadaDupla<ProdutoVO>();
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
