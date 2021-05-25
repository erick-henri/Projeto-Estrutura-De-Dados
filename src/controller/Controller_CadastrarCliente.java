package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import Exception.ExceptionCampoInvalido;
import Model.BO.ClienteBO;
import Model.VO.ClienteVO;
import Exception.ExceptionCampoVazio;
import Exception.ExceptionLoginExistente;

import view.Telas;

public class Controller_CadastrarCliente {
	private static boolean verificar; //variavel usada para verificar
	// se esta cadastrando a partir do ListarCliente ou a partir de Venda

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
	private Button cadastrar;
	@FXML
	private Button voltar;

	@FXML
	public void cadastrar(ActionEvent event) throws Exception {

		try {
			verificarCampo(nome);
			verificarCampo(cpf);
			verificarCampo(telefone);
			verificarCampo(estado);
			verificarCampo(cidade);
			verificarCampo(bairro);
			verificarCampo(rua);
			verificarCampo(numero);
			ClienteVO cli = new ClienteVO();
			ClienteBO salvar = new ClienteBO();
			cli.setNome(nome.getText());
			cli.setCpfAux(cpf.getText()); // primeiro verifica se está no bd
			cli.setCpf(cpf.getText()); // para depois salvar
			cli.setEndereco(estado.getText(), cidade.getText(), bairro.getText(), rua.getText(), numero.getText());
			cli.setTelefone(telefone.getText());
			salvar.cadastrar(cli);
			if (verificar) {
				verificar = false;
				Telas.venda();
			} else {
				Telas.listarCliente();
			}
		} catch (ExceptionCampoInvalido e) {
			mensagem.setTextFill(Color.web("red"));
			mensagem.setText(e.getMessage());
			mensagem.setVisible(true);
		} catch (ExceptionLoginExistente e1) {
			mensagem.setTextFill(Color.web("red"));
			mensagem.setText(e1.getMessage());
			mensagem.setVisible(true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@FXML
	public void voltar(ActionEvent event) throws Exception {
		if (verificar) {
			verificar = false;
			Telas.venda();
		} else {
			Telas.listarCliente();
		}
	}

	private void verificarCampo(TextField tf) throws ExceptionCampoVazio {
		if (tf.getText().isEmpty()) {
			mensagem.setTextFill(Color.web("red"));
			mensagem.setText("Preenxa todos os campos antes de salvar");
			mensagem.setVisible(true);
			throw new ExceptionCampoVazio("Complete todos os campos.");
		} else
			return;
	}
	
	public static boolean getVerificar() {
		return verificar;
	}
	
	public static void setVerificar(boolean verificar) {
		Controller_CadastrarCliente.verificar = verificar;
	}

}
