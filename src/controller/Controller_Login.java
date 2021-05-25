package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import Exception.ExceptionCampoInvalido;
import Model.BO.UsuarioBO;
import Model.VO.UsuarioVO;
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
     		UsuarioVO resp = new UsuarioVO();
    		try {
    			resp.setUsuario(usuario.getText());
    			resp.setSenha(senha.getText());
    		} catch (ExceptionCampoInvalido e2) {
				mensagem.setText("Login inválido.");
				mensagem.setTextFill(Color.web("red"));
    			mensagem.setVisible(true);
    		}
    		
    		UsuarioBO respon = new UsuarioBO();
    		if ((respon != null )&& (respon.login(resp))) {
    			try {
    				Telas.telaMenu();
    			} catch (Exception e1) {
    				mensagem.setText("Não foi possivel abrir a tela");
    				mensagem.setVisible(true);
    			}
    		} else {
    			mensagem.setText("Usuário ou senha inválidos.");
				mensagem.setTextFill(Color.web("red"));
    			mensagem.setVisible(true);
    		}
    	}
    }

