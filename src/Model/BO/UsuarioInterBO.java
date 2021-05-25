package Model.BO;

import Model.VO.UsuarioVO;
import myList.MyInterfaceList;

public interface UsuarioInterBO {
	public MyInterfaceList<UsuarioVO> findByCpf(String str);
	public boolean login(UsuarioVO vo);
	
}
