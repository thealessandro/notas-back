package br.com.kyxadious.notas.back.controller;

import br.com.kyxadious.notas.back.common.response.Response;
import br.com.kyxadious.notas.back.domain.Usuario;
import br.com.kyxadious.notas.back.dto.UsuarioDto;
import br.com.kyxadious.notas.back.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioRestController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("")
    public ResponseEntity<Response<UsuarioDto>> save(@Valid @RequestBody UsuarioDto usuarioDto, BindingResult result) {
        Response<UsuarioDto> response = new Response<>();

        valid(usuarioDto, result);

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.getMessages().add(error.getDefaultMessage()));
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(response);
        }

        Usuario usuario = convertToEntity(usuarioDto);
        Usuario usuarioSaved = this.usuarioService.save(usuario);

        response.setStatus(HttpStatus.OK.value());
        response.setContent(convertToDto(usuarioSaved));
        return ResponseEntity.ok().body(response);
    }

    private void valid(UsuarioDto usuarioDto, BindingResult result) {
        Usuario byUsername = this.usuarioService.findByUsername(usuarioDto.getUsername());
        Optional.ofNullable(byUsername)
                .ifPresent(u -> result.addError(new ObjectError("Usuário", "Já existe usuário com esse username.")));
    }

    private UsuarioDto convertToDto(Usuario usuario) {
        UsuarioDto usuarioDto = new ModelMapper().map(usuario, UsuarioDto.class);
        return usuarioDto;
    }

    private Usuario convertToEntity(UsuarioDto usuarioDto) {
        Usuario usuario = new ModelMapper().map(usuarioDto, Usuario.class);
        return usuario;
    }
}
