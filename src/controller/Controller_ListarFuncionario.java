package controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.VO.UsuarioVO;
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

public class Controller_ListarFuncionario implements Initializable{
	
	@FXML
	private TableView<UsuarioVO> lista;
	@FXML
	private TableColumn<UsuarioVO, String> telefone;
	@FXML
	private TableColumn<UsuarioVO, String> nome;
	@FXML
	private TableColumn<UsuarioVO, Long> id;
	
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
		Telas.cadastrarFuncionario();
	}

	@FXML
	public void editar(ActionEvent event) throws Exception {
		Telas.editarFuncionario();
	}

	@FXML
	public void excluir(ActionEvent event) throws Exception {
		Telas.excluirFuncionario();
	}

	@FXML
	public void pesquisar(ActionEvent event) {
		
	}
	
	@FXML
	public void voltar(ActionEvent event) throws Exception {
		Telas.telaMenu();
	}

	
}
