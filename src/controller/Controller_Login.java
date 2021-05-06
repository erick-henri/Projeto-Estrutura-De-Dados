package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import view.Telas;

public class Controller_Login {
	
    @FXML
    private PasswordField senha;

    @FXML
    private Label mensagem;

    @FXML
    private TextField usuario;

    @FXML
    private Button login;

    @FXML
    public void logar(ActionEvent event) throws Exception {
    	Telas.telaMenu();
    }

}
