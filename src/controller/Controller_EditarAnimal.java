package controller;

import java.net.URL;
import java.util.ResourceBundle;
import Exception.ExceptionCampoInvalido;
import Exception.ExceptionCampoVazio;
import Model.BO.AnimalBO;
import Model.VO.AnimalVO;
import Model.VO.ClienteVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import view.Telas;

public class Controller_EditarAnimal implements Initializable {
	private static ClienteVO dono;
	private static AnimalVO editavel;

	@FXML
	private Label mensagem;

	@FXML
	private Button voltar;
	@FXML
	private Button editar;

	@FXML
	private TextField nome;
	@FXML
	private TextArea cuidados;
	@FXML
	private TextArea descricao;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nome.setText(editavel.getNome());
		cuidados.setText(editavel.getCuidados());
		descricao.setText(editavel.getDescricao());
	}

	@FXML
	public void editar(ActionEvent event) throws Exception {
		Controller_ListarAnimal.setCliente(dono);
		Telas.listarAnimal();
		try {
			verificarCampo(nome);
			verificarArea(cuidados);
			verificarArea(descricao);
			AnimalVO animal = new AnimalVO();
			AnimalBO salvar = new AnimalBO();
			animal.setNome(nome.getText());
			animal.setCuidados(cuidados.getText());
			animal.setDescricao(descricao.getText());
			salvar.editar(animal);
			Telas.listarAnimal();
		} catch (ExceptionCampoVazio e1) {
			mensagem.setTextFill(Color.web("red"));
			mensagem.setText(e1.getMessage());
			mensagem.setVisible(true);
		} catch (ExceptionCampoInvalido e1) {
			mensagem.setTextFill(Color.web("red"));
			mensagem.setText(e1.getMessage());
			mensagem.setVisible(true);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@FXML
	public void voltar(ActionEvent event) throws Exception {
		Controller_ListarAnimal.setCliente(dono);
		Telas.listarAnimal();
	}

	public static ClienteVO getDono() {
		return dono;
	}

	public static void setDono(ClienteVO dono) {
		Controller_EditarAnimal.dono = dono;
	}

	public static AnimalVO getEditavel() {
		return editavel;
	}

	public static void setEditavel(AnimalVO editavel) {
		Controller_EditarAnimal.editavel = editavel;
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

	private void verificarArea(TextArea ta) throws ExceptionCampoVazio {
		// metodo usado para verificar as areas de texto vazias
		if (ta.getText().isEmpty()) {
			mensagem.setTextFill(Color.web("red"));
			mensagem.setText("Preenxa todos os campos antes de salvar");
			mensagem.setVisible(true);
			throw new ExceptionCampoVazio("Complete todos os campos.");
		} else
			return;
	}
}