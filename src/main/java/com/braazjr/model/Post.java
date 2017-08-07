package com.braazjr.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {

	private Long codigo;
	private String titulo;
	private String conteudo;
	private Date dataHora;

	@SuppressWarnings("unused")
	private String dataHoraFormatada;

	public Post() {
	}

	public Post(long codigo, String titulo, String conteudo, Date dataHora) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.conteudo = conteudo;
		this.dataHora = dataHora;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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

	public String getDataHoraFormatada() {
		return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(dataHora);
	}

	public void setDataHoraFormatada(String dataHoraFormatada) {
		this.dataHoraFormatada = dataHoraFormatada;
	}
}
