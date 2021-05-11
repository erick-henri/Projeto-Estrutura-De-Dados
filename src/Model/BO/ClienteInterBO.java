package Model.BO;

import Model.VO.ClienteVO;
import myList.MyInterfaceList;

public interface ClienteInterBO {
	public MyInterfaceList<ClienteVO> findByCpf(String str);
	
}
