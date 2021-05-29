package controller;

import Exception.ExceptionCampoInvalido;
import Exception.ExceptionCampoVazio;
import Exception.ExceptionLoginExistente;
import Model.BO.AnimalBO;
import Model.VO.AnimalVO;
import Model.VO.ClienteVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.paint.Color;

import view.Telas;

public class Controller_CadastrarAnimal {
	private static ClienteVO dono;
	
    @FXML
    private Label mensagem;

    @FXML
    private Button voltar;
    @FXML
    private Button cadastrar;
    
    @FXML
    private TextField nome;
    @FXML
    private TextArea cuidados;
    @FXML
    private TextArea descricao;
	
    @FXML
    public void cadastrar(ActionEvent event) throws Exception {
    	Controller_ListarAnimal.setCliente(dono);
    	try {
			verificarCampo(nome);
			verificarCampo(cuidados);
			verificarCampo(descricao);
			
			AnimalVO ani = new AnimalVO();
			AnimalBO salvar = new AnimalBO();
			ani.setNome(nome.getText());
			ani.setCliente(dono);
			ani.setCuidados(cuidados.getText());
			ani.setDescricao(descricao.getText());
			salvar.cadastrar(ani);
			Telas.listarAnimal();
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
    
    private void verificarCampo(TextField tf) throws ExceptionCampoVazio {
		if (tf.getText().isEmpty()) {
			mensagem.setTextFill(Color.web("red"));
			mensagem.setText("Preenxa todos os campos antes de salvar");
			mensagem.setVisible(true);
			throw new ExceptionCampoVazio("Complete todos os campos.");
		} else
			return;
	}
    
    private void verificarCampo(TextArea tf) throws ExceptionCampoVazio {
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
    	Controller_ListarAnimal.setCliente(dono);
    	Telas.listarAnimal();
    }

	public static ClienteVO getDono() {
		return dono;
	}

	public static void setDono(ClienteVO dono) {
		Controller_CadastrarAnimal.dono = dono;
	}

}
