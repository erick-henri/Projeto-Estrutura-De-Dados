package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Model.BO.AnimalBO;

import Model.VO.AnimalVO;
import Model.VO.ClienteVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import view.Telas;

public class Controller_ListarAnimal implements Initializable {
	private static ClienteVO cliente;
	private ObservableList<String> cb;
	
	@FXML
	private TableView<AnimalVO> lista;
	@FXML
	private TableColumn<AnimalVO, String> nome;
	@FXML
	private TableColumn<AnimalVO, Long> id;
	
	@FXML
	private ComboBox<String> escolha;
	@FXML
	private Label nomeCli;
	@FXML
	private Label mensagem;
	@FXML
	private TextField pesquisa;
	
	@FXML
	private Button cadastrar;
	@FXML
	private Button excluir;
	@FXML
	private Button pesquisar;
	@FXML
	private Button editar;
	@FXML
	private Button voltar;
	@FXML
	private Button tratamento;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nomeCli.setText("Animais de " + cliente.getNome());
		// TODO Auto-generated method stub
				AnimalBO aux = new AnimalBO();
				ObservableList<AnimalVO> animais = FXCollections.observableArrayList(aux.listar());
				nome.setCellValueFactory(new PropertyValueFactory<AnimalVO, String>("nome"));
				id.setCellValueFactory(new PropertyValueFactory<AnimalVO, Long>("id"));
				lista.setItems(animais);
				
				List<String> categorias = new ArrayList<String>();
				categorias.add("Id");
				categorias.add("Nome");
				cb = FXCollections.observableArrayList(categorias);
				escolha.setItems(cb);
	}

    @FXML
    public void cadastrar(ActionEvent event) throws Exception {
    	Controller_CadastrarAnimal.setDono(cliente);
    	Telas.adicionarAnimal();
    }

    @FXML
    public void editar(ActionEvent event) throws Exception {
    	Controller_EditarAnimal.setDono(cliente);
    	Telas.editarAnimal();
    }

    @FXML
    public void excluir(ActionEvent event) throws Exception {
    	Controller_ExcluirAnimal.setDono(cliente);
    	Telas.excluirAnimal();
    }
    
    @FXML
    public void pesquisar(ActionEvent event) {

    }

    @FXML
    public void tratamento(ActionEvent event) throws Exception {
    	Controller_Tratamento.setDono(cliente);
    	Telas.telaTratamento();
    }
	
    @FXML
    public void voltar(ActionEvent event) throws Exception {
    	Telas.listarCliente();
    }
    
	public static ClienteVO getCliente() {
		return cliente;
	}

	public static void setCliente(ClienteVO cliente) {
		Controller_ListarAnimal.cliente = cliente;
	}

}
