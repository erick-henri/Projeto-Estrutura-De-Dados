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

public class Controller_CarrinhoVenda implements Initializable {
	private static ClienteVO cliente;
	private static MyInterfaceList<ProdutoVO> carrinhoVenda = new ListaEncadeadaDupla<ProdutoVO>();
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
	private Button concluir;
	@FXML
	private Button carrinho;
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
	@FXML
	private ComboBox<String> escolha;

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

		List<String> categorias = new ArrayList<String>();
		categorias.add("Nome");
		categorias.add("Código");
		cb = FXCollections.observableArrayList(categorias);
		escolha.setItems(cb);
	}

	@FXML
	public void adicionarAoCarrinho(ActionEvent event) {
		//esse metodo irá adicionar um produto selecionado da lista no carrinho
		//com uma quantidade não maior do que a que tem em estoque
		ProdutoVO adicionar = lista.getSelectionModel().getSelectedItem();
		if (adicionar != null) {
			if (quantidadeAdicionada.getText() != null && !quantidadeAdicionada.getText().isEmpty()) {
				try {
				adicionar.setQuantiPedido(Integer.parseInt(quantidadeAdicionada.getText()));
				if (carrinhoVenda.contains(adicionar)) {
					int id = carrinhoVenda.indexOf(adicionar);
					carrinhoVenda.set(id, adicionar);
				} else {
					carrinhoVenda.add(adicionar);
				}
				mensagem.setTextFill(Color.web("green"));
				mensagem.setText("Produto adicionado ao carrinho");
				mensagem.setVisible(true);
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
	public void verCarrinho(ActionEvent event) throws Exception {
		Controller_Carrinho.setCliente(cliente);
		Controller_Carrinho.setCarrinhoVenda(carrinhoVenda);
		Telas.carrinho();
	}
	
	@FXML
	public void concluir(ActionEvent event) throws Exception {
		
	}

	@FXML
	public void pesquisar(ActionEvent event) throws ExceptionCampoInvalido {
		if ((pesquisa.getText() != null) && (!pesquisa.getText().isEmpty())) {
			if (escolha.getSelectionModel().getSelectedItem() != null) {

				ProdutoBO pesquisando = new ProdutoBO();
				ProdutoVO prod = new ProdutoVO();
				if (escolha.getSelectionModel().getSelectedItem().equals("Nome")) {
					// para caso escolha pesquisar pelo nome
					mensagem.setVisible(true); // caso a mensagem não esteja ativa
					mensagem.setTextFill(Color.web("green"));
					mensagem.setText("Pesquisa executada");
					prod.setNome(pesquisa.getText());
					ObservableList<ProdutoVO> produtos = FXCollections
							.observableArrayList(pesquisando.findByName(prod));
					nome.setCellValueFactory(new PropertyValueFactory<ProdutoVO, String>("nome"));
					quantidade.setCellValueFactory(new PropertyValueFactory<ProdutoVO, Integer>("quantidade"));
					preco.setCellValueFactory(new PropertyValueFactory<ProdutoVO, Double>("preco"));
					peso.setCellValueFactory(new PropertyValueFactory<ProdutoVO, Double>("peso"));
					id.setCellValueFactory(new PropertyValueFactory<ProdutoVO, Long>("id"));
					lista.setItems(produtos);
				}
				if (escolha.getSelectionModel().getSelectedItem().equals("Código")) {
					// para caso escolha pesquisar pelo nome
					mensagem.setVisible(true); // caso a mensagem não esteja ativa
					mensagem.setTextFill(Color.web("green"));
					mensagem.setText("Pesquisa executada");
					prod.setNome(pesquisa.getText());
					ObservableList<ProdutoVO> produtos = FXCollections
							.observableArrayList(pesquisando.findByCode(prod));
					nome.setCellValueFactory(new PropertyValueFactory<ProdutoVO, String>("nome"));
					quantidade.setCellValueFactory(new PropertyValueFactory<ProdutoVO, Integer>("quantidade"));
					preco.setCellValueFactory(new PropertyValueFactory<ProdutoVO, Double>("preco"));
					peso.setCellValueFactory(new PropertyValueFactory<ProdutoVO, Double>("peso"));
					id.setCellValueFactory(new PropertyValueFactory<ProdutoVO, Long>("id"));
					lista.setItems(produtos);
				}
			} else {
				mensagem.setTextFill(Color.web("red"));
				mensagem.setText("Selecione um tipo de pesquisa");
				mensagem.setVisible(true);
			}
		} else {
			mensagem.setTextFill(Color.web("red"));
			mensagem.setText("Digite algo para pesquisar");
			mensagem.setVisible(true);
		}
	}

	@FXML
	public void voltar(ActionEvent event) throws Exception {
		Telas.venda();
	}

	
	public static MyInterfaceList<ProdutoVO> getCarrinhoVenda() {
		return carrinhoVenda;
	}

	
	public static void setCarrinhoVenda(MyInterfaceList<ProdutoVO> carrinhoVenda) {
		Controller_CarrinhoVenda.carrinhoVenda = carrinhoVenda;
	}

	public static ClienteVO getCliente() {
		return cliente;
	}

	public static void setCliente(ClienteVO cliente) {
		Controller_CarrinhoVenda.cliente = cliente;
	}

}
