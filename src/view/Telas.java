package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Telas extends Application {
	private static Stage primaryStage;

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
	
	///TELA DE LOGIN///
	public static void telaLogin() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("telas/Login.fxml"));
		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
	}
	//////////////////
	
	////TELA DE MENU///
	public static void telaMenu() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("telas/Menu.fxml"));
		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
	}
	//////////////////
	
	///TELAS DE FUNCIONARIO///
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
	
	///TELAS DE CLIENTE///
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
	
	///TELAS DE PRODUTO///
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

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void setPrimaryStage(Stage primaryStage) {
		Telas.primaryStage = primaryStage;
	}

}
