package com.braazjr.model;

import java.util.Date;

public class Comentario {

	private Long codigo;
	private String usuario;
	private String conteudo;
	private Date dataHora;
	private Long codigoPost;

	public Comentario() {
	}

	public Comentario(long codigo, String usuario, String conteudo, Date dataHora, Long codigoPost) {
		this.codigo = codigo;
		this.usuario = usuario;
		this.conteudo = conteudo;
		this.dataHora = dataHora;
		this.codigoPost = codigoPost;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public Long getCodigoPost() {
		return codigoPost;
	}

	public void setCodigoPost(Long codigoPost) {
		this.codigoPost = codigoPost;
	}

}
