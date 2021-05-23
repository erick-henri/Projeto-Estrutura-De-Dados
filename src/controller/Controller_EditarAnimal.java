package controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.VO.ClienteVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import view.Telas;

public class Controller_EditarAnimal implements Initializable{
	private static ClienteVO dono;
	
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
		// TODO Auto-generated method stub
		
	}
	
    @FXML
    public void editar(ActionEvent event) throws Exception {
    	Controller_ListarAnimal.setCliente(dono);
    	Telas.listarAnimal();
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

}
