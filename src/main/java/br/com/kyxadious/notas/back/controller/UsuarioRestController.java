package br.com.kyxadious.notas.back.controller;

import br.com.kyxadious.notas.back.common.response.Response;
import br.com.kyxadious.notas.back.domain.Usuario;
import br.com.kyxadious.notas.back.dto.UsuarioDto;
import br.com.kyxadious.notas.back.service.UsuarioService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;
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
        response.setResult(convertToDto(usuarioSaved));
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{usuario-id}")
    public ResponseEntity<Response<UsuarioDto>> findById(@PathVariable("usuario-id") Long id) {
        Response<UsuarioDto> response = new Response<>();

        Usuario usuario = this.usuarioService.findById(id);
        response.setStatus(HttpStatus.OK.value());
        response.setResult(convertToDto(usuario));
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{usuario-id}")
    public void remove(@PathVariable("usuario-id") Long id) {
        this.usuarioService.delete(id);
    }

    @GetMapping("")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "string", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "string", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    public ResponseEntity<Response<Page<UsuarioDto>>> findAll(Pageable pageable) {
        Response<Page<UsuarioDto>> response = new Response<>();

        Page<Usuario> usuarios = this.usuarioService.findAll(pageable);
        Page<UsuarioDto> usuarioDtoPage = usuarios.map(u -> convertToDto(u));

        response.setResult(usuarioDtoPage);
        response.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok().body(response);
    }

    private void valid(UsuarioDto usuarioDto, BindingResult result) {
        Optional.ofNullable(usuarioDto.getId())
                .ifPresent(u -> result.addError(new ObjectError("Usuário", "Não deve passar o Id.")));

        Usuario byUsername = this.usuarioService.findByUsername(usuarioDto.getUsername());
        Optional.ofNullable(byUsername)
                .ifPresent(u -> result.addError(new ObjectError("Usuário", "Já existe usuário com esse username.")));
    }

    private UsuarioDto convertToDto(Usuario usuario) {
        if (Objects.isNull(usuario)) return null;

        UsuarioDto usuarioDto = new ModelMapper().map(usuario, UsuarioDto.class);
        return usuarioDto;
    }

    private Usuario convertToEntity(UsuarioDto usuarioDto) {
        if (Objects.isNull(usuarioDto)) return null;

        Usuario usuario = new ModelMapper().map(usuarioDto, Usuario.class);
        return usuario;
    }
}
