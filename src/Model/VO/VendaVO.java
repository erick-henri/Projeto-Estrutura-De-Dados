package Model.VO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import Model.BO.VendaBO;

public class VendaVO {
	private Calendar data;
	private double valor;
	private String codigo;
	private long id;
	private String cpfCli;
	private String cpfFunc;

	public Calendar getData() {
		return data;
	}

	public void setData() {
		// Utilizando a classe calendar para gerar todas as
		// informações referentes a data da compra.
		Calendar data = Calendar.getInstance();
		data.get(Calendar.YEAR);
		data.get(Calendar.MONTH);
		data.get(Calendar.DAY_OF_MONTH);
		data.get(Calendar.HOUR_OF_DAY);
		data.get(Calendar.MINUTE);
		data.get(Calendar.SECOND);
		this.data = data;
	}

	public void setData(Calendar data) {
		// Para auxiliar no Banco de Dados
		this.data = data;
	}

	public String getString() {
		// A data como string
		SimpleDateFormat df1 = new SimpleDateFormat("MM");
		String dataFinal = data.get(Calendar.YEAR) + "-" + df1.format(data.get(Calendar.MONTH)) + "-"
				+ data.get(Calendar.DAY_OF_MONTH);
		return dataFinal;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		// Pegar o valor
		this.valor = valor;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		// metodo usado para ajudar no dao
		this.codigo = codigo;
	}

	public void setCodigo() {
		// Geramento automatico de código de venda
		Random r = new Random();
		String aux = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String codigo = "";
		VendaBO aux1 = new VendaBO();

			while (codigo.length() < 35) {
				// Irá adicionar Caracter por Caractere
				codigo = codigo + aux.charAt(r.nextInt(62));
			}
			
		this.codigo = codigo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCpfCli() {
		return cpfCli;
	}

	public void setCpfCli(String cpfCli) {
		this.cpfCli = cpfCli;
	}

	public String getCpfFunc() {
		return cpfFunc;
	}

	public void setCpfFunc(String cpfFunc) {
		this.cpfFunc = cpfFunc;
	}

}
