package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import view.Telas;

public class Controller_ExcluirCliente implements Initializable{
	
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
		
	}

    @FXML
    public void excluir(ActionEvent event) throws Exception {

    }

    @FXML
    public void voltar(ActionEvent event) throws Exception {
    	Telas.listarCliente();
    }

	
    
    
}
