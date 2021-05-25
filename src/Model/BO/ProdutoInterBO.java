package Model.BO;

import Model.VO.ProdutoVO;
import myList.MyInterfaceList;

public interface ProdutoInterBO {
	public MyInterfaceList<ProdutoVO> findByCode(ProdutoVO vo);
	
}
