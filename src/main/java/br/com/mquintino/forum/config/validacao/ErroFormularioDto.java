package br.com.mquintino.forum.config.validacao;

public class ErroFormularioDto {

	private String campo;
	private String mensagem_erro;
	
	public ErroFormularioDto(String campo, String mensagem_erro) {
		super();
		this.campo = campo;
		this.mensagem_erro = mensagem_erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getMensagem_erro() {
		return mensagem_erro;
	}	
}
