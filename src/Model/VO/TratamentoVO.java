package Model.VO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import Exception.ExceptionCampoInvalido;

public class TratamentoVO {
	private long id;
	private Calendar data;
	private String cpfCli;
	private String cpfFunc;
	private String descricao;
	private String codigo;
	private double valor;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public Calendar getData() {
		return data;
	}
	
	public String getString() {
		//para passar o calendar como string
		SimpleDateFormat df1 = new SimpleDateFormat("MM");
		String dataFinal = data.get(Calendar.YEAR) + "-" + df1.format(data.get(Calendar.MONTH)) + "-" +data.get(Calendar.DAY_OF_MONTH);
		return dataFinal;
	}
	
	public void setData (Calendar data) {
		this.data = data;
	}

	public void setData() {
		// Utilizando a classe calendar para gerar todas as
		// informa√ß√µes referente a data da compra.
		Calendar data = Calendar.getInstance();
		data.get(Calendar.YEAR);
		data.get(Calendar.MONTH);
		data.get(Calendar.DAY_OF_MONTH);
		data.get(Calendar.HOUR_OF_DAY);
		data.get(Calendar.MINUTE);
		data.get(Calendar.SECOND);
		this.data = data;
	}

		
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) throws ExceptionCampoInvalido {
		if (descricao != null) {
			if ((descricao.length() <= 500) && (!descricao.isEmpty())) {
				this.descricao = descricao;
			} else {
				throw new ExceptionCampoInvalido("Descri√ß√£o n√£o pode ter mais do que 500 caracteres");
			}
		} else {
			throw new ExceptionCampoInvalido("Digite alguma descri√ß√£o.");
		}
	}
	
	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
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
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		//metodo usado para ajudar no dao
		this.codigo = codigo;
	}
	
	public void setCodigo() {
		// esse cÛdigo est·° gerando automaticamente um cÛdigo para a venda
		// s„o que antes de adicionar, tera que ser feita uma consulta no
		// banco de dados para saber se o cÛdigo j√° foi usado ou n„o
		// j· que estamos falando de valores aleatorios
		Random r = new Random();
		String aux = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String codigo = "";
		while (codigo.length() < 35) {
			// ir√° adicionar caractere por caractere a partir da string aux
			codigo = codigo + aux.charAt(r.nextInt(62));
		}
		this.codigo = codigo;
	}
}
