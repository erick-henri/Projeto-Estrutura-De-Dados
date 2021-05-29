package view;

import Model.VO.UsuarioVO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Telas extends Application {
	private static Stage primaryStage;
	private static UsuarioVO logado;

	public static void main(String... args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		setPrimaryStage(primaryStage);
		primaryStage.setResizable(false); // para impedir que o usuário maximize ou diminua a tela
		primaryStage.setTitle("AniMalia");
		primaryStage.show();
		telaLogin();
	}

	/// TELA DE LOGIN///
	public static void telaLogin() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("telas/Login.fxml"));
		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
	}
	//////////////////

	//// TELA DE MENU///
	public static void telaMenu() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("telas/Menu.fxml"));
		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
	}
	//////////////////

	////TELA DE TRATAMENTO///
	public static void telaTratamento() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("telas/Tratamento.fxml"));
		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
	}
	
	public static void finalTratamento() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("telas/FinalTratamento.fxml"));
		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
	}
	//////////////////

	/// TELAS DE FUNCIONARIO///
	public static void cadastrarFuncionario() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("telas/CadastrarFuncionario.fxml"));
		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
	}

	public static void editarFuncionario() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("telas/EditarFuncionario.fxml"));
		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
	}

	public static void excluirFuncionario() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("telas/ExcluirFuncionario.fxml"));
		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
	}

	public static void listarFuncionario() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("telas/Funcionario.fxml"));
		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
	}
	///////////////////////////

	/// TELAS DE CLIENTE///
	public static void cadastrarCliente() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("telas/CadastrarCliente.fxml"));
		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
	}

	public static void editarCliente() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("telas/EditarCliente.fxml"));
		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
	}

	public static void excluirCliente() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("telas/ExcluirCliente.fxml"));
		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
	}

	public static void listarCliente() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("telas/Cliente.fxml"));
		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
	}
	/////////////////

	/// TELAS DE PRODUTO///
	public static void adicionarProduto() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("telas/AdicionarProduto.fxml"));
		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
	}

	public static void editarProduto() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("telas/EditarProduto.fxml"));
		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
	}

	public static void excluirProduto() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("telas/ExcluirProduto.fxml"));
		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
	}

	public static void listarProduto() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("telas/Produto.fxml"));
		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
	}
	/////////////////////////

	/// TELAS DE ANIMAL///
	public static void adicionarAnimal() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("telas/CadastrarAnimal.fxml"));
		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
	}

	public static void editarAnimal() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("telas/EditarAnimal.fxml"));
		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
	}

	public static void excluirAnimal() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("telas/ExcluirAnimal.fxml"));
		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
	}

	public static void listarAnimal() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("telas/Animal.fxml"));
		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
	}
	/////////////////////////
	
	///TELAS DE VENDA///
	public static void venda() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("telas/Venda.fxml"));
		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
	}
	
	public static void carrinhoVenda() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("telas/carrinhoVenda.fxml"));
		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
	}
	
	public static void carrinho() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("telas/Carrinho.fxml"));
		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
	}
	
	public static void concluirVenda() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("telas/ConcluirVenda.fxml"));
		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
	}
	
	public static void finalVenda() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("telas/FinalVenda.fxml"));
		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
	}
	////////////////////////
	
	//TELAS DE RELATORIO///
	public static void relatorioVenda() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("telas/RelatorioVenda.fxml"));
		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
	}
	
	public static void relatorioTratamento() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("telas/RelatorioTratamento.fxml"));
		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
	}
	
	//////////////////////

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void setPrimaryStage(Stage primaryStage) {
		Telas.primaryStage = primaryStage;
	}

	public static UsuarioVO getLogado() {
		return logado;
	}

	public static void setLogado(UsuarioVO logado) {
		Telas.logado = logado;
	}

}
