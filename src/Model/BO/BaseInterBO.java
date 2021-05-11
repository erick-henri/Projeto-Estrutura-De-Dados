package Model.BO;

import myList.MyInterfaceList;

public interface BaseInterBO<VO> {
	public abstract void cadastrar(VO entity);
	public abstract void editar(VO entity);
	public abstract void excluir(VO entity);
	public abstract VO findById(VO entity);
	public abstract MyInterfaceList<VO> listar();
	public abstract MyInterfaceList<VO> findByName(VO entity);
	
}
