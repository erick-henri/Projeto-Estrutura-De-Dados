package controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.BO.UsuarioBO;
import Model.VO.UsuarioVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import view.Telas;

public class Controller_ExcluirFuncionario implements Initializable{
	private static UsuarioVO excluindo;
	private boolean confirmar;
	
	@FXML
    private TextField telefone;
    @FXML
    private TextField estado;
    @FXML
    private TextField cidade;
    @FXML
    private TextField numero;
    @FXML
    private TextField bairro;
    @FXML
    private TextField nome;
    @FXML
    private PasswordField senha;
    @FXML
    private TextField cpf;
    @FXML
    private TextField usuario;
    @FXML
    private TextField rua;
    
    @FXML
    private Label mensagem;

    @FXML
    private Button excluir;
    @FXML
    private Button voltar;

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
    	setConfirmar(false);
    	nome.setText(excluindo.getNome());
		cpf.setText(excluindo.getCpf());
		telefone.setText(excluindo.getTelefone());
		String[] enderecoCompleto = excluindo.getEndereco().split(",");
		bairro.setText(enderecoCompleto[0]);
		rua.setText(enderecoCompleto[1]);
		numero.setText(enderecoCompleto[2]);
		cidade.setText(enderecoCompleto[3]);
		estado.setText(enderecoCompleto[4]);
		usuario.setText(excluindo.getUsuario());
		senha.setText(excluindo.getSenha());
		
	}

    @FXML
    public void excluir(ActionEvent event) throws Exception {
    	if(confirmar) {
			UsuarioBO deletando = new UsuarioBO();
			deletando.excluir(excluindo);
			Telas.listarFuncionario();
		} else {
			mensagem.setTextFill(Color.web("red"));
			mensagem.setText("Deseja mesmo deletar o funcionário? \n(Essa ação não poderá ser desfeita)");
			mensagem.setVisible(true);
			voltar.setText("Não");
			excluir.setText("Sim");
			setConfirmar(true);
		}
    }

    @FXML
    public void voltar(ActionEvent event) throws Exception {
    	Telas.listarFuncionario();
    }

    public static void setExcluindo(UsuarioVO excluindo) {
    	Controller_ExcluirFuncionario.excluindo  = excluindo;
	}
    public boolean isConfirmar() {
		return confirmar;
	}

	public void setConfirmar(boolean confirmar) {
		this.confirmar = confirmar;
	}	
  
    
}
