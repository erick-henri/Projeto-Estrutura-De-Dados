package Model.DAO;

import Model.VO.ProdutoVO;
import java.sql.ResultSet;

public interface ProdutoInterDAO {
	
	public ResultSet findByCode(ProdutoVO vo);
	public boolean verificarCodigo(ProdutoVO vo);

}
