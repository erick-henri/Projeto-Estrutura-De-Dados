package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import view.Telas;

public class Controller_CadastrarCliente{
	
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

    }

    @FXML
    public void voltar(ActionEvent event) throws Exception {
    	Telas.listarCliente();
    }
    
    
}
