package Model.BO;

import Model.VO.AnimalVO;
import myList.MyInterfaceList;

public interface AnimalInterBO {
	public MyInterfaceList<AnimalVO> listar (AnimalVO vo);

}
