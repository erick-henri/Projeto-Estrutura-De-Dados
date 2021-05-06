package controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.VO.ClienteVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import view.Telas;

public class Controller_ListarCliente implements Initializable{
	
	@FXML
	private TableView<ClienteVO> lista;
	@FXML
	private TableColumn<ClienteVO, String> telefone;
	@FXML
	private TableColumn<ClienteVO, String> nome;
	@FXML
	private TableColumn<ClienteVO, Long> id;
	
	@FXML
	private TextField pesquisa;
	@FXML
	private Label mensagem;
	@FXML
	private ComboBox<?> escolha;

	@FXML
	private Button pesquisar;
	@FXML
	private Button editar;
	@FXML
	private Button animais;
	@FXML
	private Button excluir;
	@FXML
	private Button voltar;
	@FXML
	private Button cadastrar;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	@FXML
	public void cadastrar(ActionEvent event) throws Exception {
		Telas.cadastrarCliente();
	}

	@FXML
	public void editar(ActionEvent event) throws Exception {
		Telas.editarCliente();
	}

	@FXML
	public void excluir(ActionEvent event) throws Exception {
		Telas.excluirCliente();
	}

	@FXML
	public void pesquisar(ActionEvent event) {
		
	}
	
	@FXML
	public void animais(ActionEvent event) {
		
	}
	
	@FXML
	public void voltar(ActionEvent event) throws Exception {
		Telas.telaMenu();
	}

	
}
