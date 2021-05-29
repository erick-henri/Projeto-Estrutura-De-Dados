package controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import Exception.ExceptionCampoInvalido;
import Exception.ExceptionCampoVazio;
import Model.BO.TratamentoBO;
import Model.VO.AnimalVO;
import Model.VO.ClienteVO;
import Model.VO.TratamentoVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import view.Telas;

public class Controller_Tratamento implements Initializable {
	private static ClienteVO dono;
	private static AnimalVO animal;
	private TratamentoVO tratamento = new TratamentoVO();
	private boolean confirmar;

	@FXML
	private Button concluir;
	@FXML
	private Button voltar;

	@FXML
	private Label mensagem;
	@FXML
	private Label nomeCliente;
	@FXML
	private Label nomeAnimal;

	@FXML
	private TextArea descricao;
	@FXML
	private TextField valor;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setConfirmar(false);
		nomeCliente.setText("Dono: " + dono.getNome());
		nomeAnimal.setText("Animal: " + animal.getNome());
	}

	@FXML
	public void concluir(ActionEvent event) {
		if (!isConfirmar()) {
			mensagem.setTextFill(Color.web("red"));
			mensagem.setText("Deseja mesmo concluir o cadastro desse tratamento?");
			mensagem.setVisible(true);
			voltar.setText("Não");
			concluir.setText("Sim");
			setConfirmar(true);
		} else {
			try {
				TratamentoBO aux = new TratamentoBO();
				verificarArea(descricao);
				verificarCampo(valor);
				tratamento.setCpfCli(dono.getCpf());
				tratamento.setCpfFunc(Telas.getLogado().getCpf());
				tratamento.setValor(Double.parseDouble(valor.getText()));
				tratamento.setDescricao(descricao.getText());
				tratamento.setData();
				tratamento.setCodigo();
				tratamento.setCodigo("TRA," + tratamento.getCodigo());
				aux.cadastrar(tratamento);
				PDF(tratamento);
				Telas.finalTratamento();
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
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void PDF(TratamentoVO tratamento) throws com.itextpdf.text.DocumentException {
		Document doc = new Document();

		try {
			SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy");
			String data = form.format(tratamento.getData().getTime());
			PdfWriter.getInstance(doc, new FileOutputStream("./Notas/" + tratamento.getCodigo() + ".pdf"));
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
			tex = new Paragraph(dono.getNome(), fontConteudo2);
			doc.add(tex);
			tex = new Paragraph("\nCPF: ", fontConteudo);
			doc.add(tex);
			tex = new Paragraph(dono.getCpf(), fontConteudo2);
			doc.add(tex);
			tex = new Paragraph("\nTelefone: ", fontConteudo);
			doc.add(tex);
			tex = new Paragraph(dono.getTelefone(), fontConteudo2);
			doc.add(tex);
			tex = new Paragraph("\nEndereço: ", fontConteudo);
			doc.add(tex);
			tex = new Paragraph(dono.getEndereco(), fontConteudo2);
			doc.add(tex);

			// Dados do Animal
			p = new Paragraph("\nANIMAL", fontTitulo2);
			doc.add(p);
			tex = new Paragraph("\nNome: ", fontConteudo);
			doc.add(tex);
			tex = new Paragraph(animal.getNome(), fontConteudo2);
			doc.add(tex);
			tex = new Paragraph("\nCuidados: ", fontConteudo);
			doc.add(tex);
			tex = new Paragraph(animal.getCuidados(), fontConteudo2);
			doc.add(tex);

			// Dados do Responsavel Logado
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

			// Dados do Tratamento
			p = new Paragraph("\nDADOS DO TRATAMENTO", fontTitulo2);
			doc.add(p);
			tex = new Paragraph("\nDATA: ", fontConteudo);
			doc.add(tex);
			tex = new Paragraph(data, fontConteudo2);
			doc.add(tex);
			tex = new Paragraph("\nCÓDIGO: ", fontConteudo);
			doc.add(tex);
			tex = new Paragraph(tratamento.getCodigo() + "\n\n", fontConteudo2);
			doc.add(tex);
			tex = new Paragraph("\nDESCRIÇÃO: ", fontConteudo);
			doc.add(tex);
			tex = new Paragraph(tratamento.getDescricao() + "\n\n", fontConteudo2);
			doc.add(tex);

			tex = new Paragraph("\n\nTOTAL: R$ " + tratamento.getValor() + "\n\n", fontConteudo);
			doc.add(tex);
		} catch (FileNotFoundException er) {
			// TODO Auto-generated catch block
			er.printStackTrace();
		} finally {
			doc.close();
		}
	}

	private void verificarCampo(TextField tf) throws ExceptionCampoVazio {
		// metodo usado para verificar os campos que estão vazios
		if (tf.getText().isEmpty()) {
			mensagem.setTextFill(Color.web("red"));
			mensagem.setText("Preenxa todos os campos antes de salvar");
			mensagem.setVisible(true);
			throw new ExceptionCampoVazio("Complete todos os campos.");
		} else
			return;
	}

	private void verificarArea(TextArea ta) throws ExceptionCampoVazio {
		// metodo usado para verificar as areas de texto vazias
		if (ta.getText().isEmpty()) {
			mensagem.setTextFill(Color.web("red"));
			mensagem.setText("Preenxa todos os campos antes de salvar");
			mensagem.setVisible(true);
			throw new ExceptionCampoVazio("Complete todos os campos.");
		} else
			return;
	}

	@FXML
	public void voltar(ActionEvent event) throws Exception {
		Controller_ListarAnimal.setCliente(dono);
		Telas.listarAnimal();
	}

	public static AnimalVO getAnimal() {
		return animal;
	}

	public static void setAnimal(AnimalVO animal) {
		Controller_Tratamento.animal = animal;
	}

	public static ClienteVO getDono() {
		return dono;
	}

	public static void setDono(ClienteVO dono) {
		Controller_Tratamento.dono = dono;
	}

	public TratamentoVO getTratamento() {
		return tratamento;
	}

	public void setTratamento(TratamentoVO tratamento) {
		this.tratamento = tratamento;
	}

	public boolean isConfirmar() {
		return confirmar;
	}

	public void setConfirmar(boolean confirmar) {
		this.confirmar = confirmar;
	}

}
