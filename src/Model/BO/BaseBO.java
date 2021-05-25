package Model.BO;

import Model.VO.AnimalVO;
import Model.VO.ClienteVO;
import myList.MyInterfaceList;

public abstract class BaseBO<VO> implements BaseInterBO<VO>{
	public abstract void cadastrar(VO vo);
	public abstract void editar(VO vo);
	public abstract void excluir(VO vo);
	public abstract VO findById(VO vo);
	public abstract MyInterfaceList<VO> listar();
	public abstract MyInterfaceList<VO> findByName(VO vo);
	public MyInterfaceList<AnimalVO> listar(AnimalVO VO) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
