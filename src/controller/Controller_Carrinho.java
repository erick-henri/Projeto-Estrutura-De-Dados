package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Exception.ExceptionCampoInvalido;
import Model.BO.ProdutoBO;
import Model.VO.ClienteVO;
import Model.VO.ProdutoVO;
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
import javafx.scene.paint.Color;
import myList.ListaEncadeadaDupla;
import myList.MyInterfaceList;
import view.Telas;

public class Controller_Carrinho implements Initializable {
	private static ClienteVO cliente;
	private static MyInterfaceList<ProdutoVO> carrinhoVenda = new ListaEncadeadaDupla<ProdutoVO>();

	@FXML
	private TableView<ProdutoVO> lista;
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
	private Button concluir;
	@FXML
	private Button remover;
	@FXML
	private Button voltar;
	@FXML
	private Button pesquisar;

	@FXML
	private TextField pesquisa;
	@FXML
	private TextField quantidadeAdicionada;

	@FXML
	private Label mensagem;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		preenxer();
	}
	
	public void preenxer () {
		ObservableList<ProdutoVO> produtos = FXCollections.observableArrayList(carrinhoVenda);
		nome.setCellValueFactory(new PropertyValueFactory<ProdutoVO, String>("nome"));
		quantidade.setCellValueFactory(new PropertyValueFactory<ProdutoVO, Integer>("quantiPedido"));
		preco.setCellValueFactory(new PropertyValueFactory<ProdutoVO, Double>("preco"));
		id.setCellValueFactory(new PropertyValueFactory<ProdutoVO, Long>("id"));
		lista.setItems(produtos);
	}

	@FXML
	public void adicionarAoCarrinho(ActionEvent event) {
		// esse metodo irá adicionar um produto selecionado da lista no carrinho
		// com uma quantidade não maior do que a que tem em estoque
		ProdutoVO adicionar = lista.getSelectionModel().getSelectedItem();
		if (adicionar != null) {
			if (quantidadeAdicionada.getText() != null && !quantidadeAdicionada.getText().isEmpty()) {
				try {
					adicionar.setQuantiPedido(Integer.parseInt(quantidadeAdicionada.getText()));
					int id = carrinhoVenda.indexOf(adicionar);
					carrinhoVenda.set(id, adicionar);
					mensagem.setTextFill(Color.web("green"));
					mensagem.setText("Produto adicionado ao carrinho");
					mensagem.setVisible(true);
					preenxer();
				} catch (NumberFormatException e1) {
					mensagem.setTextFill(Color.web("red"));
					mensagem.setText("Digitar apenas números em quantidade.");
					mensagem.setVisible(true);
				} catch (ExceptionCampoInvalido e1) {
					mensagem.setTextFill(Color.web("red"));
					mensagem.setText(e1.getMessage());
					mensagem.setVisible(true);
				}
			} else {
				mensagem.setTextFill(Color.web("red"));
				mensagem.setText("Digite a quantidade que deseja por no carrinho");
				mensagem.setVisible(true);
			}
		} else {
			mensagem.setTextFill(Color.web("red"));
			mensagem.setText("Selecione um produto para adicionar ao carrinho");
			mensagem.setVisible(true);
		}
	}

	@FXML
	public void removerDoCarrinho(ActionEvent event) throws Exception {
		// esse metodo irá remover um produto selecionado da lista no carrinho
		// a partir da quantidade
		ProdutoVO remover = lista.getSelectionModel().getSelectedItem();
		if (remover != null) {
			if (quantidadeAdicionada.getText() != null && !quantidadeAdicionada.getText().isEmpty()) {
				try {
					remover.diminuirQuantiPedido(Integer.parseInt(quantidadeAdicionada.getText()));
					int id = carrinhoVenda.indexOf(remover);
					
					mensagem.setTextFill(Color.web("green"));
					if (Integer.parseInt(quantidadeAdicionada.getText()) == remover.getQuantiPedido() + Integer.parseInt(quantidadeAdicionada.getText())){
						carrinhoVenda.remove(remover);
						mensagem.setText("Produto removido do carrinho");
					} else {
						carrinhoVenda.set(id, remover);
						mensagem.setText("Diminuida a quantidade do produto no carrinho");
					}
					mensagem.setVisible(true);
					preenxer();
				} catch (NumberFormatException e1) {
					mensagem.setTextFill(Color.web("red"));
					mensagem.setText("Digitar apenas números em quantidade.");
					mensagem.setVisible(true);
				} catch (ExceptionCampoInvalido e1) {
					mensagem.setTextFill(Color.web("red"));
					mensagem.setText(e1.getMessage());
					mensagem.setVisible(true);
				}
			} else {
				mensagem.setTextFill(Color.web("red"));
				mensagem.setText("Digite a quantidade que deseja retirar do carrinho");
				mensagem.setVisible(true);
			}
		} else {
			mensagem.setTextFill(Color.web("red"));
			mensagem.setText("Selecione um produto para retirar do carrinho");
			mensagem.setVisible(true);
		}
	}

	@FXML
	public void concluir(ActionEvent event) throws Exception {

	}

	@FXML
	public void voltar(ActionEvent event) throws Exception {
		Controller_CarrinhoVenda.setCliente(cliente);
		Controller_CarrinhoVenda.setCarrinhoVenda(carrinhoVenda);
		Telas.carrinhoVenda();
	}

	public static MyInterfaceList<ProdutoVO> getCarrinhoVenda() {
		return carrinhoVenda;
	}

	public static void setCarrinhoVenda(MyInterfaceList<ProdutoVO> carrinhoVenda) {
		Controller_Carrinho.carrinhoVenda = carrinhoVenda;
	}

	public static ClienteVO getCliente() {
		return cliente;
	}

	public static void setCliente(ClienteVO cliente) {
		Controller_Carrinho.cliente = cliente;
	}

}
