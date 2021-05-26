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
import java.util.ResourceBundle;

import Model.VO.AnimalVO;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
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
		AnimalVO aux1 = new AnimalVO();
		aux1.setCliente(cliente);
		ObservableList<AnimalVO> animais = FXCollections.observableArrayList(aux.listar(aux1));
		nome.setCellValueFactory(new PropertyValueFactory<AnimalVO, String>("nome"));
		id.setCellValueFactory(new PropertyValueFactory<AnimalVO, Long>("id"));
		lista.setItems(animais);

		List<String> categorias = new ArrayList<String>();
		categorias.add("ID");
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
		AnimalVO editavel = lista.getSelectionModel().getSelectedItem();
		if (editavel != null) {
			AnimalBO aux = new AnimalBO();
			Controller_EditarAnimal.setDono(cliente);
			Controller_EditarAnimal.setEditavel(aux.findById(editavel));
			Telas.editarAnimal();
		}
		mensagem.setText("Selecione um animal para editar");
		mensagem.setTextFill(Color.web("red"));
		mensagem.setVisible(true);
	}

	@FXML
	public void excluir(ActionEvent event) throws Exception {
		AnimalVO deletavel = lista.getSelectionModel().getSelectedItem();
		if (deletavel != null) {
			AnimalBO aux = new AnimalBO();
			Controller_ExcluirAnimal.setDono(cliente);
			Controller_ExcluirAnimal.setDeletavel(aux.findById(deletavel));
			Telas.excluirAnimal();
		}
		mensagem.setText("Selecione um animal para excluir");
		mensagem.setTextFill(Color.web("red"));
		mensagem.setVisible(true);
	}

	@FXML
	public void tratamento(ActionEvent event) throws Exception {
		AnimalVO tratavel = lista.getSelectionModel().getSelectedItem();
		if (tratavel != null) {
			AnimalBO aux = new AnimalBO();
			Controller_Tratamento.setDono(cliente);
			Controller_Tratamento.setAnimal(aux.findById(tratavel));
			Telas.telaTratamento();
		}
		mensagem.setText("Selecione um animal para tratar");
		mensagem.setTextFill(Color.web("red"));
		mensagem.setVisible(true);
	}
	
	@FXML
	public void pesquisar(ActionEvent event) {

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
