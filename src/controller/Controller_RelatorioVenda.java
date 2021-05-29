package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import Model.BO.VendaBO;
import Model.VO.VendaVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import view.Telas;

public class Controller_RelatorioVenda implements Initializable {
	private ObservableList<String> cb;

	@FXML
	private TableView<VendaVO> lista;
	@FXML
	private TableColumn<VendaVO, String> codigo;
	@FXML
	private TableColumn<VendaVO, Long> id;
	@FXML
	private TableColumn<VendaVO, String> data;

	@FXML
	private Label mensagem;

	@FXML
	private DatePicker fim;
	@FXML
	private DatePicker comeco;

	@FXML
	private TextField pesquisa;

	@FXML
	private ComboBox<String> escolha;

	@FXML
	private Button pesquisar;
	@FXML
	private Button voltar;
	@FXML
	private Button tratamentos;
	@FXML
	private Button notaFiscal;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		VendaBO aux = new VendaBO();
		ObservableList<VendaVO> clientes = FXCollections.observableArrayList(aux.listar());
		codigo.setCellValueFactory(new PropertyValueFactory<VendaVO, String>("codigo"));
		data.setCellValueFactory(new PropertyValueFactory<VendaVO, String>("string"));
		id.setCellValueFactory(new PropertyValueFactory<VendaVO, Long>("id"));
		lista.setItems(clientes);

		List<String> categorias = new ArrayList<String>();
		categorias.add("Data");
		categorias.add("CPF do Cliente");
		categorias.add("CPF do Funcionário");

		cb = FXCollections.observableArrayList(categorias);
		escolha.setItems(cb);
	}

	@FXML
	public void voltar(ActionEvent event) throws Exception {
		Telas.telaMenu();
	}

	@FXML
	public void pesquisar(ActionEvent event) throws ParseException {
		VendaBO aux = new VendaBO();
		if ((escolha.getSelectionModel().getSelectedItem() != null)) {
			if (escolha.getSelectionModel().getSelectedItem().equals("Data")) {
				if (comeco.getValue() != null) {
					// primeiro estará pegando o valor que vem do DatePicker e o transformando em
					// date
					SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd"); // aqui é para saber o formato da data
					String dataInicio = "";
					String dataFim = "";
					LocalDate value = comeco.getValue(); // pega o que tem dentro do DatePicker
					Date data1 = (Date) form
							.parse(value.getYear() + "-" + value.getMonthValue() + "-" + value.getDayOfMonth());
					dataInicio = value.getYear() + "-" + value.getMonthValue() + "-" + value.getDayOfMonth();
					Calendar com = Calendar.getInstance();
					com.setTime(data1); // e adiciona dentro da variavel calendar
					Calendar fi = Calendar.getInstance();
					if (fim.getValue() != null) { // se tiver sido selecionado um valor para a data final
						// irá adicionar ele da mesma forma
						value = fim.getValue();
						data1 = (Date) form
								.parse(value.getYear() + "-" + value.getMonthValue() + "-" + value.getDayOfMonth());
						fi.setTime(data1);
						dataFim = value.getYear() + "-" + value.getMonthValue() + "-" + value.getDayOfMonth();
					} else {
						// caso não tenha sido selecionado nada
						// na data final, então ela recebe o valor da data inicial
						fi.setTime(data1);
						dataFim = value.getYear() + "-" + value.getMonthValue() + "-" + value.getDayOfMonth();
					}
					// e adiciona tudo dentro da lista
					ObservableList<VendaVO> vendas = FXCollections.observableArrayList(aux.relatorio(com, fi));
					codigo.setCellValueFactory(new PropertyValueFactory<VendaVO, String>("codigo"));
					data.setCellValueFactory(new PropertyValueFactory<VendaVO, String>("string"));
					id.setCellValueFactory(new PropertyValueFactory<VendaVO, Long>("id"));
					lista.setItems(vendas);
					mensagem.setTextFill(Color.web("green"));
					if(dataInicio.equals(dataFim)) {
						mensagem.setText("Mostrando relatório de compras feitas em " + dataInicio);
					} else {
						mensagem.setText("Mostrando relatório de vendas feitas de " + dataInicio + " até " + dataFim);
					}
					mensagem.setVisible(true);
				} else {
					mensagem.setTextFill(Color.web("red"));
					mensagem.setText("Por favor, selecione pelo menos a data inicial para poder processeguir");
					mensagem.setVisible(true);
				}
			} else if (escolha.getSelectionModel().getSelectedItem().equals("CPF do Cliente")) {
				if (!pesquisa.getText().isEmpty()) {
					mensagem.setVisible(false);
					ObservableList<VendaVO> vendas = FXCollections.observableArrayList(aux.listarCliente(pesquisa.getText()));
					codigo.setCellValueFactory(new PropertyValueFactory<VendaVO, String>("codigo"));
					data.setCellValueFactory(new PropertyValueFactory<VendaVO, String>("string"));
					id.setCellValueFactory(new PropertyValueFactory<VendaVO, Long>("id"));
					lista.setItems(vendas);
				} else {
					mensagem.setTextFill(Color.web("red"));
					mensagem.setText("Por favor, digite o CPF que deseja pesquisar");
					mensagem.setVisible(true);
				}
			} else if (escolha.getSelectionModel().getSelectedItem().equals("CPF do Funcionário")) {
				if (!pesquisa.getText().isEmpty()) {
					mensagem.setVisible(false);
					ObservableList<VendaVO> vendas = FXCollections.observableArrayList(aux.listarFuncionario(pesquisa.getText()));
					codigo.setCellValueFactory(new PropertyValueFactory<VendaVO, String>("codigo"));
					data.setCellValueFactory(new PropertyValueFactory<VendaVO, String>("string"));
					id.setCellValueFactory(new PropertyValueFactory<VendaVO, Long>("id"));
					lista.setItems(vendas);
				} else {
					mensagem.setTextFill(Color.web("red"));
					mensagem.setText("Por favor, digite o CPF que deseja pesquisar");
					mensagem.setVisible(true);
				}
			}
		} else {
			mensagem.setTextFill(Color.web("red"));
			mensagem.setText("Por favor, selecione um tipo de pesquisa");
			mensagem.setVisible(true);
		}
	}

	@FXML
	public void notaFiscal(ActionEvent event) throws IOException {
		VendaVO aux = lista.getSelectionModel().getSelectedItem();
		if (aux != null) {
			File pdf = new File("./Notas/"+aux.getCodigo()+".pdf");
			Desktop.getDesktop().open(pdf);
		} else {
			mensagem.setTextFill(Color.web("red"));
			mensagem.setText("Por favor, selecione uma linha para ver a nota fiscal");
			mensagem.setVisible(true);
		}
	}

	@FXML
	public void tratamento(ActionEvent event) throws Exception {
		Telas.relatorioTratamento();
	}
}
