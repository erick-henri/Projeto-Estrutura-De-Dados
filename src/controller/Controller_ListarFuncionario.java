package controller;

import java.net.URL;
import java.util.ResourceBundle;

import Exception.ExceptionCampoInvalido;
import Model.BO.UsuarioBO;
import Model.VO.PessoaVO;
import Model.VO.UsuarioVO;
import java.util.ArrayList;
import java.util.List;

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

public class Controller_ListarFuncionario implements Initializable {
	private ObservableList<String> cb;

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
	private ComboBox<String> escolha;

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
		UsuarioBO aux = new UsuarioBO();
		ObservableList<UsuarioVO> responsaveis = FXCollections.observableArrayList(aux.listar());
		nome.setCellValueFactory(new PropertyValueFactory<UsuarioVO, String>("nome"));
		telefone.setCellValueFactory(new PropertyValueFactory<UsuarioVO, String>("telefone"));
		id.setCellValueFactory(new PropertyValueFactory<UsuarioVO, Long>("id"));
		lista.setItems(responsaveis);

		MyInterfaceList<String> categorias = new ListaEncadeadaDupla<String>();
		categorias.add("Nome");
		categorias.add("CPF");
		cb = FXCollections.observableArrayList(categorias);
		escolha.setItems(cb);
	}

	@FXML
	public void cadastrar(ActionEvent event) throws Exception {
		Telas.cadastrarFuncionario();
	}

	@FXML
	public void editar(ActionEvent event) throws Exception {
		UsuarioVO editar = lista.getSelectionModel().getSelectedItem();
		if (editar != null) {
			UsuarioBO aux = new UsuarioBO();
			if (aux.findById(editar).getUsuario().equals(Telas.getLogado().getUsuario())) {
				Controller_EditarFuncionario.setEditando(aux.findById(editar));
				Telas.editarFuncionario();
			} else {
				mensagem.setTextFill(Color.web("red"));
				mensagem.setText("Você só pode editar sua conta.");
				mensagem.setVisible(true);
			}

			if (Telas.getLogado().getUsuario().equals("Gerente")) {
				Controller_EditarFuncionario.setEditando(aux.findById(editar));
				Telas.editarFuncionario();
			}

		} else {
			mensagem.setTextFill(Color.web("red"));
			mensagem.setText("Selecione um item para excluir");
			mensagem.setVisible(true);
		}
	}

	@FXML
	public void excluir(ActionEvent event) throws Exception {
		UsuarioVO excluir = lista.getSelectionModel().getSelectedItem();
		if (excluir != null) {
			UsuarioBO aux = new UsuarioBO();

			if (Telas.getLogado().getUsuario().equals("Gerente")) {
				if (!aux.findById(excluir).getUsuario().equals("Gerente")) {
					Controller_ExcluirFuncionario.setExcluindo(aux.findById(excluir));
					Telas.excluirFuncionario();
				} else {
					mensagem.setTextFill(Color.web("red"));
					mensagem.setText("Conta não pode ser excluida.");
					mensagem.setVisible(true);
				}
			} else if (aux.findById(excluir).getUsuario().equals(Telas.getLogado().getUsuario())) {
				Controller_ExcluirFuncionario.setExcluindo(aux.findById(excluir));
				Telas.excluirFuncionario();
			} else {
				mensagem.setTextFill(Color.web("red"));
				mensagem.setText("Você só pode excluir sua conta.");
				mensagem.setVisible(true);
			}

		} else {
			mensagem.setTextFill(Color.web("red"));
			mensagem.setText("Selecione um item para excluir");
			mensagem.setVisible(true);
		}
	}

	@FXML
	public void pesquisar(ActionEvent event) throws ExceptionCampoInvalido {
		if ((escolha.getSelectionModel().getSelectedItem() != null)
				&& (escolha.getSelectionModel().getSelectedItem().equals("Nome"))) {
			if (!pesquisa.getText().isEmpty()) {
				mensagem.setVisible(false);
				UsuarioVO resp = new UsuarioVO();
				UsuarioBO aux = new UsuarioBO();
				resp.setNome(pesquisa.getText());
				ObservableList<UsuarioVO> responsaveis = FXCollections.observableArrayList(aux.findByName(resp));
				nome.setCellValueFactory(new PropertyValueFactory<UsuarioVO, String>("nome"));
				telefone.setCellValueFactory(new PropertyValueFactory<UsuarioVO, String>("telefone"));
				id.setCellValueFactory(new PropertyValueFactory<UsuarioVO, Long>("id"));
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
				UsuarioBO aux = new UsuarioBO();
				ObservableList<UsuarioVO> responsaveis = FXCollections.observableArrayList(aux.findByCpf(cpf));
				nome.setCellValueFactory(new PropertyValueFactory<UsuarioVO, String>("nome"));
				telefone.setCellValueFactory(new PropertyValueFactory<UsuarioVO, String>("telefone"));
				id.setCellValueFactory(new PropertyValueFactory<UsuarioVO, Long>("id"));
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
	public void voltar(ActionEvent event) throws Exception {
		Telas.telaMenu();
	}

}
