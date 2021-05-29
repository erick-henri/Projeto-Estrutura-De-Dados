package controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Model.BO.ProdutoBO;
import Model.BO.VendaBO;
import Model.VO.ClienteVO;
import Model.VO.ProdutoVO;
import Model.VO.VendaVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import myList.ListaEncadeadaDupla;
import myList.MyInterfaceList;
import view.Telas;

public class Controller_ConcluirVenda implements Initializable {
	private static ClienteVO cliente;
	private static MyInterfaceList<ProdutoVO> carrinhoVenda = new ListaEncadeadaDupla<ProdutoVO>();
	private VendaVO venda = new VendaVO();
	private boolean confirmar;
	private static boolean carrinhoV;

	@FXML
	private TableView<ProdutoVO> lista;
	@FXML
	private TableColumn<ProdutoVO, Double> preco;
	@FXML
	private TableColumn<ProdutoVO, String> nome;
	@FXML
	private TableColumn<ProdutoVO, Long> id;
	@FXML
	private TableColumn<ProdutoVO, Integer> quantidade;

	@FXML
	private Button concluir;
	@FXML
	private Button voltar;

	@FXML
	private Label clienteVenda;
	@FXML
	private Label funcionarioVenda;
	@FXML
	private Label precoVenda;
	@FXML
	private Label codigoVenda;
	@FXML
	private Label mensagem;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setConfirmar(false);
		clienteVenda.setText("Cliente " + cliente.getNome());
		funcionarioVenda.setText("Funcionário: " + Telas.getLogado().getNome());
		venda.setCodigo();
		String codigo = venda.getCodigo();
		venda.setCodigo("PRO," + codigo);
		codigoVenda.setText("Código: " + venda.getCodigo());
		double preco = 0;
		for (int i = 0; i < carrinhoVenda.size(); i++) {
			preco = preco + carrinhoVenda.get(i).getPreco() * carrinhoVenda.get(i).getQuantiPedido();
		}
		venda.setValor(preco);
		precoVenda.setText("Preço: " + preco);

		preenxer();
	}

	public void preenxer() {
		ObservableList<ProdutoVO> produtos = FXCollections.observableArrayList(carrinhoVenda);
		nome.setCellValueFactory(new PropertyValueFactory<ProdutoVO, String>("nome"));
		quantidade.setCellValueFactory(new PropertyValueFactory<ProdutoVO, Integer>("quantiPedido"));
		preco.setCellValueFactory(new PropertyValueFactory<ProdutoVO, Double>("preco"));
		id.setCellValueFactory(new PropertyValueFactory<ProdutoVO, Long>("id"));
		lista.setItems(produtos);
	}

	@FXML
	public void concluir(ActionEvent event) throws Exception {
		if (!isConfirmar()) {
			mensagem.setTextFill(Color.web("red"));
			mensagem.setText("Deseja mesmo concluir essa venda?");
			mensagem.setVisible(true);
			voltar.setText("Não");
			concluir.setText("Sim");
			setConfirmar(true);
		} else {
			MyInterfaceList<ProdutoVO> produtos = new ListaEncadeadaDupla<ProdutoVO>();
			ProdutoBO aux = new ProdutoBO();
			VendaBO vender = new VendaBO();
			for (int i = 0; i < carrinhoVenda.size(); i++) {
				produtos.add(aux.findById(carrinhoVenda.get(i)));
				produtos.get(i).setQuantiPedido(carrinhoVenda.get(i).getQuantiPedido());
			}

			for (int i = 0; i < carrinhoVenda.size(); i++) {
				produtos.get(i).setQuantidade(-produtos.get(i).getQuantiPedido());
				aux.editar(produtos.get(i));
			}
			
			venda.setCpfCli(cliente.getCpf());
			venda.setCpfFunc(Telas.getLogado().getCpf());
			venda.setData();
			vender.cadastrar(venda);
			setCarrinhoV(false);
			PDF(venda);
			Telas.finalVenda();
		}
	}

	public void PDF(VendaVO venda) throws com.itextpdf.text.DocumentException {
		Document doc = new Document();

		try {
			SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy");
			String data = form.format(venda.getData().getTime());
			PdfWriter.getInstance(doc, new FileOutputStream("./Notas/" + venda.getCodigo() + ".pdf"));
			doc.open();
			doc.setPageSize(PageSize.A4);
			Font fontTitulo = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
			Paragraph p = new Paragraph("NOTA FISCAL", fontTitulo);
			doc.add(p);
			Font fontTitulo2 = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLDITALIC, BaseColor.GRAY);
			p = new Paragraph("\nCLIENTE", fontTitulo2);
			doc.add(p);
			Font fontConteudo = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLDITALIC, BaseColor.GREEN);
			Font fontConteudo2 = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.ITALIC, BaseColor.GREEN);
			// Dados do Cliente
			Paragraph tex = new Paragraph("\nNome: ", fontConteudo);
			doc.add(tex);
			tex = new Paragraph(cliente.getNome(), fontConteudo2);
			doc.add(tex);
			tex = new Paragraph("\nCPF: ", fontConteudo);
			doc.add(tex);
			tex = new Paragraph(cliente.getCpf(), fontConteudo2);
			doc.add(tex);
			tex = new Paragraph("\nTelefone: ", fontConteudo);
			doc.add(tex);
			tex = new Paragraph(cliente.getTelefone(), fontConteudo2);
			doc.add(tex);
			tex = new Paragraph("\nEndereço: ", fontConteudo);
			doc.add(tex);
			tex = new Paragraph(cliente.getEndereco(), fontConteudo2);
			doc.add(tex);
			//Dados do Responsavel Logado
			p = new Paragraph("\nRESPONSÁVEL", fontTitulo2);
			doc.add(p);
			tex = new Paragraph("\nNome: ", fontConteudo);
			doc.add(tex);
			tex = new Paragraph(Telas.getLogado().getNome(), fontConteudo2);
			doc.add(tex);
			tex = new Paragraph("\nTelefone: ", fontConteudo);
			doc.add(tex);
			tex = new Paragraph(Telas.getLogado().getTelefone(), fontConteudo2);
			doc.add(tex);
		
			//Dados da Venda
			p = new Paragraph("\nDADOS DA VENDA", fontTitulo2);
			doc.add(p);
			tex = new Paragraph("\nDATA: ", fontConteudo);
			doc.add(tex);
			tex = new Paragraph(data, fontConteudo2);
			doc.add(tex);
			tex = new Paragraph("\nCÓDIGO: ", fontConteudo);
			doc.add(tex);
			tex = new Paragraph(venda.getCodigo() + "\n\n", fontConteudo2);
			doc.add(tex);
			
			PdfPTable table = new PdfPTable(4);
			PdfPCell cel1 = new PdfPCell(new Paragraph("ID", fontConteudo));
			PdfPCell cel2 = new PdfPCell(new Paragraph("Nome", fontConteudo));
			PdfPCell cel3 = new PdfPCell(new Paragraph("Quantidade", fontConteudo));
			PdfPCell cel4 = new PdfPCell(new Paragraph("Preço Unitário", fontConteudo));
			
			table.addCell(cel1);
			table.addCell(cel2);
			table.addCell(cel3);
			table.addCell(cel4);
			
			for (int i = 0; i < carrinhoVenda.size(); i++) {
				cel1 = new PdfPCell(new Paragraph(carrinhoVenda.get(i).getId() + "", fontConteudo2));
				cel2 = new PdfPCell(new Paragraph(carrinhoVenda.get(i).getNome(), fontConteudo2));
				cel3 = new PdfPCell(new Paragraph(""+carrinhoVenda.get(i).getQuantiPedido(), fontConteudo2));
				cel4 = new PdfPCell(new Paragraph("R$ " + carrinhoVenda.get(i).getPreco(), fontConteudo2));
				table.addCell(cel1);
				table.addCell(cel2);
				table.addCell(cel3);
				table.addCell(cel4);
			}
			doc.add(table);
			tex = new Paragraph("\n\nTOTAL: R$ " + venda.getValor() + "\n\n", fontConteudo);
			doc.add(tex);
		} catch (FileNotFoundException er) {
			// TODO Auto-generated catch block
			er.printStackTrace();
		} finally {
			doc.close();
		}
	}

	@FXML
	public void voltar(ActionEvent event) throws Exception {
		if (carrinhoV) {
			setCarrinhoV(false);
			Controller_CarrinhoVenda.setCliente(cliente);
			Telas.carrinhoVenda();
		} else {
			Controller_Carrinho.setCliente(cliente);
			Telas.carrinho();	
		}
	}

	public static MyInterfaceList<ProdutoVO> getCarrinhoVenda() {
		return carrinhoVenda;
	}

	public static void setCarrinhoVenda(MyInterfaceList<ProdutoVO> carrinhoVenda) {
		Controller_ConcluirVenda.carrinhoVenda = carrinhoVenda;
	}

	public static ClienteVO getCliente() {
		return cliente;
	}

	public static void setCliente(ClienteVO cliente) {
		Controller_ConcluirVenda.cliente = cliente;
	}

	public VendaVO getVenda() {
		return venda;
	}

	public void setVenda(VendaVO venda) {
		this.venda = venda;
	}

	public boolean isConfirmar() {
		return confirmar;
	}

	public void setConfirmar(boolean confirmar) {
		this.confirmar = confirmar;
	}

	public boolean isCarrinhoV() {
		return carrinhoV;
	}

	public static void setCarrinhoV(boolean carrinhoV) {
		Controller_ConcluirVenda.carrinhoV = carrinhoV;
	}

}
