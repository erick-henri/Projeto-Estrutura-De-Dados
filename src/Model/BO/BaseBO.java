package Model.BO;

<<<<<<< HEAD
import Model.VO.AnimalVO;
import Model.VO.ClienteVO;
=======
>>>>>>> 6280a7a6360fa37a62f32520cb4d0a1fc49356d0
import myList.MyInterfaceList;

public abstract class BaseBO<VO> implements BaseInterBO<VO>{
	public abstract void cadastrar(VO vo);
	public abstract void editar(VO vo);
	public abstract void excluir(VO vo);
	public abstract VO findById(VO vo);
	public abstract MyInterfaceList<VO> listar();
	public abstract MyInterfaceList<VO> findByName(VO vo);
<<<<<<< HEAD
	public MyInterfaceList<AnimalVO> listar(AnimalVO VO) {
		// TODO Auto-generated method stub
		return null;
	}
=======
>>>>>>> 6280a7a6360fa37a62f32520cb4d0a1fc49356d0
	
}
