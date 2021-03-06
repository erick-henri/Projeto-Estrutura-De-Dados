package controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.VO.ClienteVO;
import java.util.ArrayList;
import java.util.List;

import Exception.ExceptionCampoInvalido;
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
import Model.BO.ClienteBO;
import Model.VO.PessoaVO;
import view.Telas;

public class Controller_ListarCliente implements Initializable{
	private ObservableList<String> cb;
	
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
	private ComboBox<String> escolha;
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
		ClienteBO aux = new ClienteBO();
		ObservableList<ClienteVO> clientes = FXCollections.observableArrayList(aux.listar());
		nome.setCellValueFactory(new PropertyValueFactory<ClienteVO, String>("nome"));
		telefone.setCellValueFactory(new PropertyValueFactory<ClienteVO, String>("telefone"));
		id.setCellValueFactory(new PropertyValueFactory<ClienteVO, Long>("id"));
		lista.setItems(clientes);
		
		List<String> categorias = new ArrayList<String>();
		categorias.add("Nome");
		categorias.add("CPF");
		cb = FXCollections.observableArrayList(categorias);
		escolha.setItems(cb);
	}

	@FXML
	public void cadastrar(ActionEvent event) throws Exception {
		Telas.cadastrarCliente();
	}

	@FXML
	public void editar(ActionEvent event) throws Exception {
		ClienteVO editando = lista.getSelectionModel().getSelectedItem();
		if (editando != null) {
			ClienteBO aux = new ClienteBO();
			Controller_EditarCliente.setEditando(aux.findById(editando));
			Telas.editarCliente();
		}
		mensagem.setTextFill(Color.web("red"));
		mensagem.setText("Selecione um cliente para editar");
		mensagem.setVisible(true);
	}

	@FXML
	public void excluir(ActionEvent event) throws Exception {
		ClienteVO excluindo = lista.getSelectionModel().getSelectedItem();
		if (excluindo != null) {
			ClienteBO aux = new ClienteBO();
			Controller_ExcluirCliente.setExcluindo(aux.findById(excluindo));
			Telas.excluirCliente();
		}
		mensagem.setTextFill(Color.web("red"));
		mensagem.setText("Selecione um cliente para excluir");
		mensagem.setVisible(true);
	}

	@FXML
	public void pesquisar(ActionEvent event) {
		if ((escolha.getSelectionModel().getSelectedItem() != null)
				&& (escolha.getSelectionModel().getSelectedItem().equals("Nome"))) {
			if (!pesquisa.getText().isEmpty()) {
				mensagem.setVisible(false);
				ClienteVO cli = new ClienteVO();
				ClienteBO aux = new ClienteBO();
				try {
					cli.setNome(pesquisa.getText());
				} catch (ExceptionCampoInvalido e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				ObservableList<ClienteVO> responsaveis = FXCollections.observableArrayList(aux.findByName(cli));
				nome.setCellValueFactory(new PropertyValueFactory<ClienteVO, String>("nome"));
				telefone.setCellValueFactory(new PropertyValueFactory<ClienteVO, String>("telefone"));
				id.setCellValueFactory(new PropertyValueFactory<ClienteVO, Long>("id"));
				lista.setItems(responsaveis);
			} else {
				mensagem.setTextFill(Color.web("red"));
				mensagem.setText("Por favor, digite algo para pesquisar");
				mensagem.setVisible(true);
			}
		} else if ((escolha.getSelectionModel().getSelectedItem() != null)
				&& (escolha.getSelectionModel().getSelectedItem().equals("CPF"))) {
			if (!pesquisa.getText().isEmpty()) {
				mensagem.setVisible(false);
				String cpf = pesquisa.getText();
				ClienteBO aux = new ClienteBO();
				ObservableList<ClienteVO> responsaveis = FXCollections.observableArrayList(aux.findByCpf(cpf));
				nome.setCellValueFactory(new PropertyValueFactory<ClienteVO, String>("nome"));
				telefone.setCellValueFactory(new PropertyValueFactory<ClienteVO, String>("telefone"));
				id.setCellValueFactory(new PropertyValueFactory<ClienteVO, Long>("id"));
				lista.setItems(responsaveis);
			} else {
				mensagem.setTextFill(Color.web("red"));
				mensagem.setText("Por favor, digite algo para pesquisar");
				mensagem.setVisible(true);
			}
		} else {
			mensagem.setTextFill(Color.web("red"));
			mensagem.setText("Por favor, selecionar um tipo de pesquisa");
			mensagem.setVisible(true);
		}
	}
	
	@FXML
	public void animais(ActionEvent event) throws Exception {
		ClienteVO cliente = lista.getSelectionModel().getSelectedItem();
		if (cliente != null) {
			ClienteBO aux = new ClienteBO();
			Controller_ListarAnimal.setCliente(aux.findById(cliente));
			Telas.listarAnimal();
		}
		mensagem.setTextFill(Color.web("red"));
		mensagem.setText("Selecione um cliente para verificar os animais");
		mensagem.setVisible(true);
	}
	
	@FXML
	public void voltar(ActionEvent event) throws Exception {
		Telas.telaMenu();
	}

}
