package Model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import Exception.ExceptionCampoInvalido;
import Model.DAO.AnimalDAO;
import Model.VO.AnimalVO;
import Model.BO.AnimalBO;
import myList.ListaEncadeadaDupla;
import myList.MyInterfaceList;

public class AnimalBO extends BaseBO<AnimalVO> implements AnimalInterBO {

	AnimalDAO animal = new AnimalDAO();

	@Override
	public void cadastrar(AnimalVO vo) {
		animal.cadastrar(vo);
	}

	@Override
	public void editar(AnimalVO vo) {
		animal.editar(vo);
	}

	@Override
	public void excluir(AnimalVO vo) {
		animal.excluir(vo);
	}

	public AnimalVO findById(AnimalVO vo) {
		// procurar um animal no banco de dados que possua o id digitado
		ResultSet rs = animal.findById(vo);
		AnimalVO aux = new AnimalVO();
		try {
			while (rs.next()) {
				aux.setId(rs.getLong("idanimal"));
				aux.setNome(rs.getString("nome"));
				aux.setCuidados(rs.getString("cuidados"));
				aux.setDescricao(rs.getString("descricao"));
				

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionCampoInvalido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aux;
	}

	@Override
	public MyInterfaceList<AnimalVO> listar(AnimalVO vo) {
		// Listar os animais do determinado cliente
		ResultSet rs = animal.listar(vo);
		MyInterfaceList<AnimalVO> animais = new ListaEncadeadaDupla<AnimalVO>();
		AnimalVO aux = new AnimalVO();
		try {
			while (rs.next()) {

				aux.setId(rs.getLong("idanimal"));
				aux.setNome(rs.getString("nome"));
				aux.setCuidados(rs.getString("cuidados"));
				aux.setDescricao(rs.getString("descricao"));
				animais.add(aux);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionCampoInvalido e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return animais;
	}

	@Override
	public MyInterfaceList<AnimalVO> findByName(AnimalVO vo) {
		// Procurar um animal no banco de dados que possua o nome digitado.
		ResultSet rs = animal.findByName(vo);
		MyInterfaceList<AnimalVO> animais = new ListaEncadeadaDupla<AnimalVO>();
		try {
			while (rs.next()) {
				AnimalVO aux = new AnimalVO();
				aux.setId(rs.getLong("idanimal"));
				aux.setNome(rs.getString("nome"));
				aux.setCuidados(rs.getString("cuidados"));
				aux.setDescricao(rs.getString("descricao"));
				animais.add(aux);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionCampoInvalido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return animais;
	}

	@Override
	public MyInterfaceList<AnimalVO> listar() {
		// TODO Auto-generated method stub
		return null;
	}

}
