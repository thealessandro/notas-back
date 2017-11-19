package br.com.kyxadious.notas.back.dto;

import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UsuarioDto {

    private Long id;

    @NotEmpty(message = "Username não pode ser vazio.")
    @Length(min = 5, max = 20, message = "Username deve conter entre 5 e 20 caracteres.")
    @ApiParam(value = "Username", required = true)
    private String username;

    @NotEmpty(message = "Password não pode ser vazio.")
    @Length(min = 6, max = 10, message = "Password deve conter entre 6 e 10 caracteres.")
    @ApiParam(value = "Password", required = true)
    private String password;

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
}
