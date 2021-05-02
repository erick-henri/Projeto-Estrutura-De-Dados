package controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.BO.ProdutoBO;
import Model.VO.ProdutoVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import view.Telas;

public class Controller_ListarProduto implements Initializable {
	private ObservableList<String> cb;

	@FXML
	private TableView<ProdutoVO> lista;
	@FXML
	private TableColumn<ProdutoVO, Double> peso;
	@FXML
	private TableColumn<ProdutoVO, Double> preco;
	@FXML
	private TableColumn<ProdutoVO, String> nome;
	@FXML
	private TableColumn<ProdutoVO, Long> id;
	@FXML
	private TableColumn<ProdutoVO, Integer> quantidade;

	@FXML
	private Button adicionar;
	@FXML
	private Button excluir;
	@FXML
	private Button editar;
	@FXML
	private Button voltar;
	
	@FXML
	private Label mensagem;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ProdutoBO aux = new ProdutoBO();
		ObservableList<ProdutoVO> produtos = FXCollections.observableArrayList(aux.listar());
		nome.setCellValueFactory(new PropertyValueFactory<ProdutoVO, String>("nome"));
		quantidade.setCellValueFactory(new PropertyValueFactory<ProdutoVO, Integer>("quantidade"));
		preco.setCellValueFactory(new PropertyValueFactory<ProdutoVO, Double>("preco"));
		peso.setCellValueFactory(new PropertyValueFactory<ProdutoVO, Double>("peso"));
		id.setCellValueFactory(new PropertyValueFactory<ProdutoVO, Long>("id"));
		lista.setItems(produtos);
	}

	@FXML
	public void adicionar(ActionEvent event) throws Exception {
		Telas.adicionarProduto();
	}

	@FXML
	public void editar(ActionEvent event) throws Exception {
		ProdutoVO editando = lista.getSelectionModel().getSelectedItem();
		if (editando != null) {
			ProdutoBO aux = new ProdutoBO();
			Controller_EditarProduto.setEditando(aux.findById(editando));
			Telas.editarProduto();
		}
		mensagem.setTextFill(Color.web("red"));
		mensagem.setText("Selecione um item para editar");
		mensagem.setVisible(true);
	}

	@FXML
	public void excluir(ActionEvent event) throws Exception {
		ProdutoVO editando = lista.getSelectionModel().getSelectedItem();
		if (editando != null) {
			ProdutoBO aux = new ProdutoBO();
			Controller_ExcluirProduto.setExcluindo(aux.findById(editando));
			Telas.excluirProduto();
		}
		mensagem.setTextFill(Color.web("red"));
		mensagem.setText("Selecione um item para excluir");
		mensagem.setVisible(true);
	}

	@FXML
	public void voltar(ActionEvent event) {
		
	}

}
