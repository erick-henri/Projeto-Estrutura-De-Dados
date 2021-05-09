package controller;

import java.net.URL;
import java.util.ResourceBundle;

import Exception.ExceptionCampoVazio;
import Model.VO.ClienteVO;	
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import Exception.ExceptionCampoInvalido;
import Model.BO.ClienteBO;
import view.Telas;

public class Controller_EditarCliente implements Initializable{
	private static ClienteVO editando;
	private static String cpfEditarInicial; //usado para ajudar na verificação do novo cpf
	
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
    private Button editar;
    @FXML
    private Button voltar;

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	nome.setText(editando.getNome());
		cpf.setText(editando.getCpf());
		setCpfEditarInicial(editando.getCpf());
		telefone.setText(editando.getTelefone());
		String[] enderecoCompleto = editando.getEndereco().split(",");
		bairro.setText(enderecoCompleto[0]);
		rua.setText(enderecoCompleto[1]);
		numero.setText(enderecoCompleto[2]);
		cidade.setText(enderecoCompleto[3]);
		estado.setText(enderecoCompleto[4]);
		
	}

    @FXML
    public void editar(ActionEvent event) throws Exception {
    	ClienteBO aux = new ClienteBO();

		try {
			verificarCampo(nome);
			verificarCampo(cpf);
			verificarCampo(telefone);
			verificarCampo(estado);
			verificarCampo(cidade);
			verificarCampo(bairro);
			verificarCampo(rua);
			verificarCampo(numero);
			editando.setNome(nome.getText());
			if(!getCpfEditarInicial().equals(cpf.getText())) { //verifica se o cpf foi alterado
				editando.setCpfAux(cpf.getText()); //para verificar se está no bd
			}
			editando.setCpf(cpf.getText());
			editando.setEndereco(estado.getText(), cidade.getText(), bairro.getText(), rua.getText(),
					numero.getText());
			editando.setTelefone(telefone.getText());
			aux.editar(editando);

			Telas.listarCliente();
		} catch (ExceptionCampoInvalido e1) {
			mensagem.setTextFill(Color.web("red"));
			mensagem.setText(e1.getMessage());
			mensagem.setVisible(true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }

    @FXML
    public void voltar(ActionEvent event) throws Exception {
    	Telas.listarCliente();
    }

    private void verificarCampo(TextField tf) throws ExceptionCampoVazio {
		if (tf.getText().isEmpty()) {
			mensagem.setTextFill(Color.web("red"));
			mensagem.setText("Preenxa todos os campos antes de salvar");
			mensagem.setVisible(true);
			throw new ExceptionCampoVazio("Complete todos os campos.");
		} else return;
	}	
    
    public static ClienteVO getCliEditavel() {
		return editando;
	}

	public static void setCliEditavel(ClienteVO cliEditavel) {
		Controller_EditarCliente.editando = cliEditavel;
	}
    
	public static String getCpfEditarInicial() {
		return cpfEditarInicial;
	}

	public static void setCpfEditarInicial(String cpfEditarInicial) {
		Controller_EditarCliente.cpfEditarInicial = cpfEditarInicial;
	}
}
