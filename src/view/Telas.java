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
		listarProduto();
	}
	
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
