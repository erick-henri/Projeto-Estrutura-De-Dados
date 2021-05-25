package controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.BO.ClienteBO;
import Model.VO.ClienteVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import view.Telas;

public class Controller_ExcluirCliente implements Initializable {
	private static ClienteVO excluindo;
	private boolean confirmar;

	@FXML
	private TextField telefone;
	@FXML
	private TextField estado;
	@FXML
	private TextField cidade;
	@FXML
	private TextField numero;
	@FXML
	private TextField bairro;
	@FXML
	private TextField nome;
	@FXML
	private TextField cpf;
	@FXML
	private TextField rua;

	@FXML
	private Label mensagem;

	@FXML
	private Button excluir;
	@FXML
	private Button voltar;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setConfirmar(false);
		nome.setText(excluindo.getNome());
		cpf.setText(excluindo.getCpf());
		telefone.setText(excluindo.getTelefone());
		
		String[] enderecoCompleto = excluindo.getEndereco().split(",");
		
		bairro.setText(enderecoCompleto[0]);
		rua.setText(enderecoCompleto[1]);
		numero.setText(enderecoCompleto[2]);
		cidade.setText(enderecoCompleto[3]);
		estado.setText(enderecoCompleto[4]);

	}

	@FXML
	public void excluir(ActionEvent event) throws Exception {

		if (confirmar) {
			ClienteBO deletando = new ClienteBO();
			deletando.excluir(excluindo);
			Telas.listarCliente();
		} else {
			mensagem.setTextFill(Color.web("red"));
			mensagem.setText("Deseja mesmo deletar este cliente?\n(Essa ação não poderá ser desfeita)");
			mensagem.setVisible(true);
			voltar.setText("Não");
			excluir.setText("Sim");
			setConfirmar(true);
		}
	}

	@FXML
	public void voltar(ActionEvent event) throws Exception {
		Telas.listarCliente();
	}

	public static void setExcluindo(ClienteVO excluindo) {
		Controller_ExcluirCliente.excluindo = excluindo;
	}

	public boolean isConfirmar() {
		return confirmar;
	}

	public void setConfirmar(boolean confirmar) {
		this.confirmar = confirmar;
	}

}
