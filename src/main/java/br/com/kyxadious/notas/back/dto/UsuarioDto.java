package br.com.kyxadious.notas.back.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.HashSet;
import java.util.Set;

public class UsuarioDto {

    private Long id;

    @NotEmpty(message = "Username não pode ser vazio.")
    @Length(min = 8, max = 20, message = "Username deve conter entre 8 e 20 caracteres.")
    private String username;

    @NotEmpty(message = "Password não pode ser vazio.")
    @Length(min = 6, max = 10, message = "Password deve conter entre 6 e 10 caracteres.")
    private String password;

    private Set<NotaDto> notas = new HashSet<>();

    public UsuarioDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<NotaDto> getNotas() {
        return notas;
    }

    public void setNotas(Set<NotaDto> notas) {
        this.notas = notas;
    }
}
