package controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.BO.ProdutoBO;
import Model.VO.ProdutoVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import view.Telas;

public class Controller_ExcluirProduto implements Initializable{
	private static ProdutoVO excluindo;
	private boolean confirmar;
	
	@FXML
	private TextField preco;
	@FXML
	private TextField codigo;
	@FXML
	private TextField peso;
	@FXML
	private Button deletar;
	@FXML
	private Button voltar;
	@FXML
	private TextField nome;
	@FXML
	private TextField quantidade;
	@FXML
	private TextArea descricao;
	
	@FXML
	private Label mensagem;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setConfirmar(false);
		nome.setText(excluindo.getNome());
		preco.setText(excluindo.getPreco()+"");
		codigo.setText(excluindo.getCodigo());
		peso.setText(excluindo.getPeso()+"");
		quantidade.setText(excluindo.getQuantidade()+"");
		descricao.setText(excluindo.getDescricao());
	}
	
	public void deletar(ActionEvent e) throws Exception{
		if(confirmar) {
			ProdutoBO deletando = new ProdutoBO();
			deletando.excluir(excluindo);
			Telas.listarProduto();
		} else {
			mensagem.setTextFill(Color.web("red"));
			mensagem.setText("Deseja mesmo deletar este item?\n(Essa ação não poderá ser desfeita)");
			mensagem.setVisible(true);
			voltar.setText("Não");
			deletar.setText("Sim");
			setConfirmar(true);
		}
	}
	
	public void voltar(ActionEvent e) throws Exception{
		Telas.listarProduto();
	}

	public static ProdutoVO getExcluindo() {
		return excluindo;
	}

	public static void setExcluindo(ProdutoVO excluindo) {
		Controller_ExcluirProduto.excluindo = excluindo;
	}

	public boolean isConfirmar() {
		return confirmar;
	}

	public void setConfirmar(boolean confirmar) {
		this.confirmar = confirmar;
	}
	
	
	
}
