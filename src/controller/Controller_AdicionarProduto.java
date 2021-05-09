package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import view.Telas;
import Exception.ExceptionCampoVazio;
import Exception.ExceptionCampoInvalido;
import Model.BO.ProdutoBO;
import Model.VO.ProdutoVO;

public class Controller_AdicionarProduto implements Initializable{
	@FXML
	private TextField preco;
	
	@FXML
	private TextField codigo;

	@FXML
	private TextField peso;

	@FXML
	private Button salvar;

	@FXML
	private Button voltar;

	@FXML
	private TextField nome;

	@FXML
	private TextField quantidade;

	@FXML
	private TextArea descricao;
	
	@FXML
	private Label mensagem;
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void salvar(ActionEvent e) throws Exception{
		try {
			verificarCampo(nome);
			verificarCampo(codigo);
			verificarCampo(preco);
			verificarCampo(peso);
			verificarCampo(quantidade);
			verificarArea(descricao);
			ProdutoVO prod = new ProdutoVO();
			ProdutoBO salvar = new ProdutoBO();
			prod.setNome(nome.getText());
			prod.setCodigoAux(codigo.getText());
			prod.setCodigo(codigo.getText());
			prod.setPreco(Double.parseDouble(preco.getText()));
			prod.setPeso(Double.parseDouble(peso.getText()));
			prod.setQuantidade(Integer.parseInt(quantidade.getText()));
			prod.setDescricao(descricao.getText());
			salvar.cadastrar(prod);
			mensagem.setTextFill(Color.web("green"));
			mensagem.setText("Produto adicionado com sucesso");
			mensagem.setVisible(true);
			limparCampo(nome);
			limparCampo(codigo);
			limparCampo(preco);
			limparCampo(peso);
			limparCampo(quantidade);
			limparArea(descricao);
		} catch (NumberFormatException e1) {
			mensagem.setTextFill(Color.web("red"));
			mensagem.setText("Digitar apenas números em preço, peso e quantidade.");
			mensagem.setVisible(true);
		} catch (ExceptionCampoVazio e1) {
			mensagem.setTextFill(Color.web("red"));
			mensagem.setText(e1.getMessage());
			mensagem.setVisible(true);
		} catch (ExceptionCampoInvalido e1) {
			mensagem.setTextFill(Color.web("red"));
			mensagem.setText(e1.getMessage());
			mensagem.setVisible(true);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void voltar(ActionEvent e) throws Exception{
		Telas.listarProduto();
	}
	
	private void verificarCampo(TextField tf) throws ExceptionCampoVazio {
		//metodo usado para verificar os campos que estão vazios
		if (tf.getText().isEmpty()) {
			mensagem.setTextFill(Color.web("red"));
			mensagem.setText("Preenxa todos os campos antes de salvar");
			mensagem.setVisible(true);
			throw new ExceptionCampoVazio("Complete todos os campos.");
		} else
			return;
	}
	
	private void verificarArea(TextArea ta) throws ExceptionCampoVazio {
		//metodo usado para verificar as areas de texto vazias
		if (ta.getText().isEmpty()) {
			mensagem.setTextFill(Color.web("red"));
			mensagem.setText("Preenxa todos os campos antes de salvar");
			mensagem.setVisible(true);
			throw new ExceptionCampoVazio("Complete todos os campos.");
		} else
			return;
	}
	
	private void limparCampo(TextField tf) {
		//medodo usado para limpar os campos
		tf.setText("");
	}
	
	private void limparArea(TextArea ta) {
		//medodo usado para limpar a area de texto
		ta.setText("");
	}
}
