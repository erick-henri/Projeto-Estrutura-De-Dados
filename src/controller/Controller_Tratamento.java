package controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.VO.AnimalVO;
import Model.VO.ClienteVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import view.Telas;

public class Controller_Tratamento implements Initializable{
	private static ClienteVO dono;
	private static AnimalVO animal;

    @FXML
    private Button concluir;
    @FXML
    private Button voltar;
    
    @FXML
    private Label mensagem;
    @FXML
    private Label nomeCliente;
    @FXML
    private Label nomeAnimal;

    @FXML
    private TextArea descricao;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		nomeCliente.setText("Dono: " + dono.getNome());
		nomeAnimal.setText("Animal: ");
	}

    @FXML
    public void concluir(ActionEvent event) {

    }

    @FXML
    public void voltar(ActionEvent event) throws Exception {
    	Controller_ListarAnimal.setCliente(dono);
    	Telas.listarAnimal();
    }

	public static AnimalVO getAnimal() {
		return animal;
	}

	public static void setAnimal(AnimalVO animal) {
		Controller_Tratamento.animal = animal;
	}

	public static ClienteVO getDono() {
		return dono;
	}

	public static void setDono(ClienteVO dono) {
		Controller_Tratamento.dono = dono;
	}

}
