package Model.DAO;

import Exception.ExceptionCampoInvalido;
import Model.VO.ProdutoVO;

public class Teste {
	public static void main(String[] args) throws ExceptionCampoInvalido {
		ProdutoVO teste = new ProdutoVO();
		
		teste.setNome("Rem�dio");
		teste.setDescricao("Isso � um rem�dio");
		teste.setPeso(10.5);
		teste.setPreco(10.60);
		teste.setQuantidade(5);
		ProdutoDAO aux = new ProdutoDAO();
		aux.cadastrar(teste);
		
	}
}
