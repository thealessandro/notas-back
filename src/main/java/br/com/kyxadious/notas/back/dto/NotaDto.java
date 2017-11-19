package br.com.kyxadious.notas.back.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

public class NotaDto {

    private Long id;

    @JsonProperty("data_cadastro")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "America/Fortaleza")
    private Date dataCadastro;

    @JsonProperty("data_edicao")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "America/Fortaleza")
    private Date dataEdicao;

    @NotEmpty(message = "Título não pode ser vazio.")
    private String titulo;

    @NotEmpty(message = "Texto não pode ser vazio.")
    private String texto;

    @NotEmpty(message = "Cor não pode ser vazio.")
    private String cor;

    public NotaDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataEdicao() {
        return dataEdicao;
    }

    public void setDataEdicao(Date dataEdicao) {
        this.dataEdicao = dataEdicao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
