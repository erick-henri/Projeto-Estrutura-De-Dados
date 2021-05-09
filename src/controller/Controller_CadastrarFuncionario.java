package controller;

import Exception.ExceptionCampoVazio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import Exception.ExceptionCampoInvalido;
import Exception.ExceptionLoginExistente;
import Model.BO.UsuarioBO;
import Model.VO.UsuarioVO;
import view.Telas;

public class Controller_CadastrarFuncionario{
	
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
    private Button cadastrar;
    @FXML
    private Button voltar;



    @FXML
    public void cadastrar(ActionEvent event) throws Exception {
    	try {
			verificarCampo(nome);
			verificarCampo(cpf);
			verificarCampo(telefone);
			verificarCampo(estado);
			verificarCampo(cidade);
			verificarCampo(bairro);
			verificarCampo(rua);
			verificarCampo(numero);
			verificarCampo(usuario);
			verificarCampo(senha);
			UsuarioVO respon = new UsuarioVO();
			UsuarioBO salvar = new UsuarioBO();
			respon.setNome(nome.getText());
			respon.setCpfAux(cpf.getText()); //primeiro verifica se está no bd
			respon.setCpf(cpf.getText()); //para depois salvar
			respon.setEndereco(estado.getText(), cidade.getText(), bairro.getText(), rua.getText(), numero.getText());
			respon.setTelefone(telefone.getText());
			respon.setUsuarioAux(usuario.getText());//primeiro verifica se está no bd
			respon.setUsuario(usuario.getText());// para depois salvar
			respon.setSenha(senha.getText());
			salvar.cadastrar(respon);
			Telas.listarFuncionario();
		} catch (ExceptionCampoInvalido e) {
			mensagem.setTextFill(Color.web("red"));
			mensagem.setText(e.getMessage());
			mensagem.setVisible(true);
		} catch (ExceptionLoginExistente e1) {
			mensagem.setTextFill(Color.web("red"));
			mensagem.setText(e1.getMessage());
			mensagem.setVisible(true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
   

    @FXML
    public void voltar(ActionEvent event) throws Exception {
    	Telas.listarFuncionario();
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
}
