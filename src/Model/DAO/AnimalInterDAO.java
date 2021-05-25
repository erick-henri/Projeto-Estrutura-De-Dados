package Model.DAO;

import java.sql.ResultSet;

import Model.VO.AnimalVO;

public interface AnimalInterDAO {
	public ResultSet listar(AnimalVO vo);
}
