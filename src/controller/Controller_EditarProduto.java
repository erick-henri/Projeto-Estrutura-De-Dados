package controller;

import java.net.URL;
import java.util.ResourceBundle;

import Exception.ExceptionCampoInvalido;
import Exception.ExceptionCampoVazio;
import Model.BO.ProdutoBO;
import Model.VO.ProdutoVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import view.Telas;

public class Controller_EditarProduto implements Initializable{
	private static ProdutoVO editando;
	private String codigoInicial;
	
	@FXML
	private TextField preco;
	@FXML
	private TextField codigo;
	@FXML
	private TextField peso;
	@FXML
	private Button editar;
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
		nome.setText(editando.getNome());
		preco.setText(editando.getPreco()+"");
		codigo.setText(editando.getCodigo());
		peso.setText(editando.getPeso()+"");
		quantidade.setText(editando.getQuantidade()+"");
		descricao.setText(editando.getDescricao());
		codigoInicial = editando.getCodigo();
	}
	
	public void editar(ActionEvent e) throws Exception{
		try {
			verificarCampo(nome);
			verificarCampo(codigo);
			verificarCampo(preco);
			verificarCampo(peso);
			verificarCampo(quantidade);
			verificarArea(descricao);
			ProdutoVO prod = new ProdutoVO();
			ProdutoBO editar = new ProdutoBO();
			prod.setNome(nome.getText());
			if (!codigoInicial.equals(codigo.getText())) {
				prod.setCodigoAux(codigo.getText());
			}
			prod.setCodigo(codigo.getText());
			prod.setPreco(Double.parseDouble(preco.getText()));
			prod.setPeso(Double.parseDouble(peso.getText()));
			prod.setQuantidade(Integer.parseInt(quantidade.getText()));
			prod.setDescricao(descricao.getText());
			prod.setId(editando.getId());
			editar.editar(prod);
			Telas.listarProduto();			
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
	

	public static ProdutoVO getEditando() {
		return editando;
	}

	public static void setEditando(ProdutoVO editando) {
		Controller_EditarProduto.editando = editando;
	}

	public String getCodigoInicial() {
		return codigoInicial;
	}

	public void setCodigoInicial(String codigoInicial) {
		this.codigoInicial = codigoInicial;
	}
	
	
	
}
