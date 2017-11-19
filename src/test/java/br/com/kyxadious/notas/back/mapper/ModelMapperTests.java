package br.com.kyxadious.notas.back.mapper;

import br.com.kyxadious.notas.back.domain.Usuario;
import br.com.kyxadious.notas.back.dto.UsuarioDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ModelMapperTests {

    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void testConvertUsuarioDtoToUsuario() {
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setUsername("admin");
        usuarioDto.setPassword("123456");

        Usuario usuario = modelMapper.map(usuarioDto, Usuario.class);
        assertEquals(usuarioDto.getUsername(), usuario.getUsername());
        assertEquals(usuarioDto.getPassword(), usuario.getPassword());
    }

    @Test
    public void testConvertUsuarioToUsuariodTO() {
        Usuario usuario = new Usuario();
        usuario.setUsername("admin");
        usuario.setPassword("123456");

        UsuarioDto usuarioDto = modelMapper.map(usuario, UsuarioDto.class);
        assertEquals(usuario.getUsername(), usuarioDto.getUsername());
        assertEquals(usuario.getPassword(), usuarioDto.getPassword());
    }
}
