package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


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
import Exception.ExceptionCampoInvalido;
import Model.BO.ProdutoBO;
import Model.BO.UsuarioBO;
import Model.VO.PessoaVO;
import Model.VO.ProdutoVO;
import Model.VO.UsuarioVO;
import view.Telas;

public class Controller_ListarFuncionario implements Initializable{
	private ObservableList<String> cb;
	
	@FXML
	private TableView<PessoaVO> lista;
	@FXML
	private TableColumn<PessoaVO, String> telefone;
	@FXML
	private TableColumn<PessoaVO, String> nome;
	@FXML
	private TableColumn<PessoaVO, Long> id;
	@FXML
	private TableColumn<PessoaVO, String> cpf;
	
	
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
		UsuarioBO aux = new UsuarioBO();
		ObservableList<PessoaVO> responsaveis = FXCollections.observableArrayList(aux.listar());
		nome.setCellValueFactory(new PropertyValueFactory<PessoaVO, String>("nome"));
		cpf.setCellValueFactory(new PropertyValueFactory<PessoaVO, String>("cpf"));
		telefone.setCellValueFactory(new PropertyValueFactory<PessoaVO, String>("telefone"));
		id.setCellValueFactory(new PropertyValueFactory<PessoaVO, Long>("id"));
		lista.setItems(responsaveis);
		
		List<String> categorias = new ArrayList<String>();
		categorias.add("Nome");
		categorias.add("cpf");
		cb = FXCollections.observableArrayList(categorias);
		escolha.setItems(cb);
	}

	@FXML
	public void cadastrar(ActionEvent event) throws Exception {
		Telas.cadastrarFuncionario();
	}

	@FXML
	public void editar(ActionEvent event) throws Exception {
		PessoaVO editar = lista.getSelectionModel().getSelectedItem();
		if (editar != null) {
			UsuarioVO aux = new UsuarioVO();
			UsuarioBO aux2 = new UsuarioBO();
			Controller_EditarFuncionario.setEditando(aux2.findById(aux));
			Telas.editarFuncionario();
		}
		mensagem.setTextFill(Color.web("red"));
		mensagem.setText("Selecione um item para editar");
		mensagem.setVisible(true);
	}


	@FXML
	public void excluir(ActionEvent event) throws Exception {
		PessoaVO exclui = lista.getSelectionModel().getSelectedItem();
		if (exclui != null) {
			UsuarioVO aux = new UsuarioVO();
			UsuarioBO aux2 = new UsuarioBO();
			aux.setIdPessoa(exclui.getIdPessoa());
			Controller_ExcluirFuncionario.setExcluindo(aux2.findById(aux));
			Telas.excluirProduto();
		}
		mensagem.setTextFill(Color.web("red"));
		mensagem.setText("Selecione um item para excluir");
		mensagem.setVisible(true);
	}

	@FXML
	public void pesquisar(ActionEvent event) {
		if ((escolha.getSelectionModel().getSelectedItem() != null)
				&& (escolha.getSelectionModel().getSelectedItem().equals("Nome"))) {
			if (!pesquisa.getText().isEmpty()) {
				mensagem.setVisible(false);
				UsuarioVO resp = new UsuarioVO();
				UsuarioBO aux = new UsuarioBO();
				try {
					resp.setNome(pesquisa.getText());
				} catch (ExceptionCampoInvalido e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				ObservableList<PessoaVO> responsaveis = FXCollections.observableArrayList(aux.findByNome(resp));
				nome.setCellValueFactory(new PropertyValueFactory<PessoaVO, String>("nome"));
				cpf.setCellValueFactory(new PropertyValueFactory<PessoaVO, String>("cpf"));
				telefone.setCellValueFactory(new PropertyValueFactory<PessoaVO, String>("telefone"));
				id.setCellValueFactory(new PropertyValueFactory<PessoaVO, Long>("id"));
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
				UsuarioVO resp = new UsuarioVO();
				UsuarioBO aux = new UsuarioBO();
				ObservableList<PessoaVO> responsaveis = FXCollections.observableArrayList(aux.findByCpf(resp));
				nome.setCellValueFactory(new PropertyValueFactory<PessoaVO, String>("nome"));
				cpf.setCellValueFactory(new PropertyValueFactory<PessoaVO, String>("cpf"));
				telefone.setCellValueFactory(new PropertyValueFactory<PessoaVO, String>("telefone"));
				id.setCellValueFactory(new PropertyValueFactory<PessoaVO, Long>("id"));
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
