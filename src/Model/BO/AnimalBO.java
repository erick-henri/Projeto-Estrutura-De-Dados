package Model.BO;

<<<<<<< HEAD
import java.sql.ResultSet;
import java.sql.SQLException;

import Exception.ExceptionCampoInvalido;
import Model.DAO.AnimalDAO;

import Model.VO.AnimalVO;
import Model.VO.ClienteVO;
import myList.ListaEncadeadaDupla;
import myList.MyInterfaceList;

public class AnimalBO extends BaseBO<AnimalVO> {

	AnimalDAO animal = new AnimalDAO();

	@Override
	public void cadastrar(AnimalVO vo) {
		animal.cadastrar(vo);
=======
import Model.VO.AnimalVO;
import myList.MyInterfaceList;

public class AnimalBO extends BaseBO<AnimalVO>{
>>>>>>> 6280a7a6360fa37a62f32520cb4d0a1fc49356d0

	@Override
	public void cadastrar(AnimalVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editar(AnimalVO vo) {
<<<<<<< HEAD
		animal.editar(vo);

=======
		// TODO Auto-generated method stub
		
>>>>>>> 6280a7a6360fa37a62f32520cb4d0a1fc49356d0
	}

	@Override
	public void excluir(AnimalVO vo) {
<<<<<<< HEAD
		animal.excluir(vo);
=======
		// TODO Auto-generated method stub
		
	}
>>>>>>> 6280a7a6360fa37a62f32520cb4d0a1fc49356d0

	@Override
	public AnimalVO findById(AnimalVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
<<<<<<< HEAD
	public AnimalVO findById(AnimalVO vo) {
		//procurar um animal no banco de dados que possua o id digitado
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
		//Procurar um animal no banco de dados que possua o nome digitado.
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

=======
	public MyInterfaceList<AnimalVO> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyInterfaceList<AnimalVO> findByName(AnimalVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

>>>>>>> 6280a7a6360fa37a62f32520cb4d0a1fc49356d0
}
