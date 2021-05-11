package Model.DAO;

import Model.VO.PessoaVO;

public interface PessoaInterDAO<VO extends PessoaVO> {
	public boolean cpf(VO vo);
}
