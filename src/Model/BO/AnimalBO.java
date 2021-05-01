package Model.BO;

import java.util.ArrayList;
import java.util.List;

import Model.VO.AnimalVO;
import Model.VO.ClienteVO;

public class AnimalBO {
	public void cadastrar(AnimalVO animal) {

	}

	public void editar(AnimalVO animal) {

	}

	public void excluir(AnimalVO animal) {

	}

	public AnimalVO findByOwner(AnimalVO animal) {
		return animal;
	}
	
	public List<AnimalVO> listarPorDono (ClienteVO cliente){
		List<AnimalVO> animais = new ArrayList<AnimalVO>();
		
		return animais;
	}
}
