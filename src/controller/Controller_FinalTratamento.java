package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import view.Telas;

public class Controller_FinalTratamento {
	@FXML
	private Button voltar;

	@FXML
	public void voltar(ActionEvent event) throws Exception {
		Telas.listarAnimal();
	}
}
