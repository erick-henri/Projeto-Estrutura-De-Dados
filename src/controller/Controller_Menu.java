package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import view.Telas;

public class Controller_Menu {
	@FXML
    private Button sair;

    @FXML
    private Button produtos;

    @FXML
    private Button vendas;

    @FXML
    private Button funcionarios;

    @FXML
    private Button clientes;

    @FXML
    public void vendas(ActionEvent event) throws Exception {
    	Telas.venda();
    }

    @FXML
    public void sair(ActionEvent event) throws Exception {
    	Telas.telaLogin();
    }

    @FXML
    public void produto(ActionEvent event) throws Exception {
    	Telas.listarProduto();
    }

    @FXML
    public void clientes(ActionEvent event) throws Exception {
    	Telas.listarCliente();
    }

    @FXML
    public void funcionarios(ActionEvent event) throws Exception {
    	Telas.listarFuncionario();
    }
}
