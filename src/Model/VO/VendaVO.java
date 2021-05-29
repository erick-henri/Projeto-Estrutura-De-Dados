package Model.VO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

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
		// informaÃ§Ãµes referente a data da compra.
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
		//para auxiliar no dao
		this.data = data;
	}
	
	public String getString() {
		//para passar o calendar como string
		SimpleDateFormat df1 = new SimpleDateFormat("MM");
		String dataFinal = data.get(Calendar.YEAR) + "-" + df1.format(data.get(Calendar.MONTH)) + "-" +data.get(Calendar.DAY_OF_MONTH);
		return dataFinal;
	}

	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		// ajduar na hora de pegar uma venda do bd
		this.valor = valor;
	}

	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		//metodo usado para ajudar no dao
		this.codigo = codigo;
	}
	
	public void setCodigo() {
		// esse código está¡ gerando automaticamente um código para a venda
		// são que antes de adicionar, tera que ser feita uma consulta no
		// banco de dados para saber se o código jÃ¡ foi usado ou não
		// já que estamos falando de valores aleatorios
		Random r = new Random();
		String aux = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String codigo = "";
		while (codigo.length() < 35) {
			// irÃ¡ adicionar caractere por caractere a partir da string aux
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
