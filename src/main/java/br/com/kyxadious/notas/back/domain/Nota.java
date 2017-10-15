package br.com.kyxadious.notas.back.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by alessandro on 29/06/17.
 */

@Entity
@Table(name = "nota")
public class Nota implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("data_cadastro")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "America/Fortaleza")
    private Date dataCadastro;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("data_edicao")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "America/Fortaleza")
    private Date dataEdicao;

    private String texto;

    private String cor;

    public Nota() {
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
