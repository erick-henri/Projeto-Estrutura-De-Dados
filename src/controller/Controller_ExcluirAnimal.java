package controller;

import java.net.URL;
import java.util.ResourceBundle;

<<<<<<< HEAD
import Model.BO.AnimalBO;
import Model.BO.ProdutoBO;
=======
>>>>>>> 1059b7e833bdcf8c01b15aca9ab02b4fcb5787d2
import Model.VO.AnimalVO;
import Model.VO.ClienteVO;
import Model.VO.ProdutoVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import view.Telas;

public class Controller_ExcluirAnimal implements Initializable{
	private static ClienteVO dono;
	private static AnimalVO deletavel;
<<<<<<< HEAD
	private boolean confirmar;
=======
	
>>>>>>> 1059b7e833bdcf8c01b15aca9ab02b4fcb5787d2
    @FXML
    private Label mensagem;

    @FXML
    private Button voltar;
    @FXML
    private Button excluir;
    
    @FXML
    private TextField nome;
    @FXML
    private TextArea cuidados;
    @FXML
    private TextArea descricao;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	nome.setText(deletavel.getNome());
    	cuidados.setText(deletavel.getCuidados());
    	descricao.setText(deletavel.getDescricao());
	}
	
    @FXML
    public void excluir(ActionEvent event) throws Exception {
    	Controller_ListarAnimal.setCliente(dono);
    	if(confirmar) {
			AnimalBO deletando = new AnimalBO();
			deletando.excluir(deletavel);
			Telas.listarAnimal();
		} else {
			mensagem.setTextFill(Color.web("red"));
			mensagem.setText("Deseja mesmo deletar ?\n(Essa ação não poderá ser desfeita)");
			mensagem.setVisible(true);
			voltar.setText("Não");
			excluir.setText("Sim");
			setConfirmar(true);
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
		Controller_ExcluirAnimal.dono = dono;
	}

	public static AnimalVO getDeletavel() {
		return deletavel;
	}

	public static void setDeletavel(AnimalVO deletavel) {
		Controller_ExcluirAnimal.deletavel = deletavel;
	}

<<<<<<< HEAD
	public boolean isConfirmar() {
		return confirmar;
	}

	public void setConfirmar(boolean confirmar) {
		this.confirmar = confirmar;
	}
=======
>>>>>>> 1059b7e833bdcf8c01b15aca9ab02b4fcb5787d2
}
