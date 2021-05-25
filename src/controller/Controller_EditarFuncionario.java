package controller;

import java.net.URL;
import java.util.ResourceBundle;

import Exception.ExceptionCampoInvalido;
import Exception.ExceptionCampoVazio;
import Exception.ExceptionLoginExistente;
import Model.BO.UsuarioBO;
import Model.VO.ProdutoVO;
import Model.VO.UsuarioVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import view.Telas;

public class Controller_EditarFuncionario implements Initializable {
	private static UsuarioVO editando;
	private static String userEditarInicial; // usado para ajudar na verificação do novo usuario
	private static String cpfEditarInicial; // usado para ajudar na verificação do novo cpf

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
	private PasswordField senha;
	@FXML
	private TextField cpf;
	@FXML
	private TextField usuario;
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
		// TODO Auto-generated method stub
		nome.setText(editando.getNome());
		cpf.setText(editando.getCpf());
		telefone.setText(editando.getTelefone());

		setUserEditarInicial(editando.getUsuario());
		setCpfEditarInicial(editando.getCpf());

		String[] enderecoCompleto = editando.getEndereco().split(",");
		bairro.setText(enderecoCompleto[0]);
		rua.setText(enderecoCompleto[1]);
		numero.setText(enderecoCompleto[2]);
		cidade.setText(enderecoCompleto[3]);
		estado.setText(enderecoCompleto[4]);

		usuario.setText(editando.getUsuario());
		senha.setText(editando.getSenha());
	}

	@FXML
	public void editar(ActionEvent event) throws Exception {
		UsuarioBO aux = new UsuarioBO();
		try {
			verificarCampo(nome);
			verificarCampo(cpf);
			verificarCampo(telefone);
			verificarCampo(estado);
			verificarCampo(cidade);
			verificarCampo(bairro);
			verificarCampo(rua);
			verificarCampo(numero);
			verificarCampo(usuario);
			verificarCampo(senha);
			editando.setNome(nome.getText());
			if (!getCpfEditarInicial().equals(cpf.getText())) { // verifica se o cpf foi alterado
				editando.setCpfAux(cpf.getText()); // para verificar se está no bd
			}
			editando.setCpf(cpf.getText());
			editando.setEndereco(estado.getText(), cidade.getText(), bairro.getText(), rua.getText(), numero.getText());
			editando.setTelefone(telefone.getText());
			if (!getUserEditarInicial().equals(usuario.getText())) { // verifica se o usuario foi alterado
				editando.setUsuarioAux(usuario.getText()); // primeiro verifica se está no bd
			}
			editando.setUsuario(usuario.getText()); // para depois salvar
			editando.setSenha(senha.getText());
			aux.editar(editando);
			Telas.listarFuncionario();
		} catch (ExceptionCampoInvalido e1) {
			mensagem.setTextFill(Color.web("red"));
			mensagem.setText(e1.getMessage());
			mensagem.setVisible(true);
		} catch (ExceptionLoginExistente e1) {
			mensagem.setTextFill(Color.web("red"));
			mensagem.setText(e1.getMessage());
			mensagem.setVisible(true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void verificarCampo(TextField tf) throws ExceptionCampoVazio {
		// metodo usado para verificar os campos que estão vazios
		if (tf.getText().isEmpty()) {
			mensagem.setTextFill(Color.web("red"));
			mensagem.setText("Preenxa todos os campos antes de salvar");
			mensagem.setVisible(true);
			throw new ExceptionCampoVazio("Complete todos os campos.");
		} else
			return;
	}

	@FXML
	public void voltar(ActionEvent event) throws Exception {
		Telas.listarFuncionario();
	}

	public static UsuarioVO getEditando() {
		return editando;
	}

	public static void setEditando(UsuarioVO editando) {
		Controller_EditarFuncionario.editando = editando;
	}

	public static String getUserEditarInicial() {
		return userEditarInicial;
	}

	public static void setUserEditarInicial(String userEditarInicial) {
		Controller_EditarFuncionario.userEditarInicial = userEditarInicial;
	}

	public static String getCpfEditarInicial() {
		return cpfEditarInicial;
	}

	public static void setCpfEditarInicial(String cpfEditarInicial) {
		Controller_EditarFuncionario.cpfEditarInicial = cpfEditarInicial;
	}

}
