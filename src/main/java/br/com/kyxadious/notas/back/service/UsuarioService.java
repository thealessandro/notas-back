package br.com.kyxadious.notas.back.service;

import br.com.kyxadious.notas.back.common.interfaces.IService;
import br.com.kyxadious.notas.back.common.utils.BCryptUtils;
import br.com.kyxadious.notas.back.domain.Usuario;
import br.com.kyxadious.notas.back.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * Created by alessandro on 15/10/17.
 */

@Service
public class UsuarioService implements IService<Usuario, Long> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario save(Usuario usuario) {
        Usuario usuarioResultado = null;

        if (usuario != null && usuario.getId() == null) {
            if (StringUtils.isEmpty(usuario.getUsername()) || StringUtils.isEmpty(usuario.getPassword()))
                return null;

            if (Objects.nonNull(this.usuarioRepository.findByUsername(usuario.getUsername())))
                return null;

            usuario.setPassword(BCryptUtils.generateHash(usuario.getPassword()));
            usuarioResultado = this.usuarioRepository.save(usuario);
        }
        return usuarioResultado;
    }

    @Override
    public Usuario update(Usuario usuario) {
        Usuario usuarioResultado = null;

        if (usuario != null && usuario.getId() != null) {
            if (StringUtils.isEmpty(usuario.getUsername()) || StringUtils.isEmpty(usuario.getPassword()))
                return null;

            usuarioResultado = this.usuarioRepository.save(usuario);
        }
        return usuarioResultado;
    }

    @Override
    public void delete(Long id) {
        id = (id != null) ? id : 0L;

        if (Objects.nonNull(findById(id))) {
            this.usuarioRepository.delete(id);
        }
    }

    public void deleteAll() {
        this.usuarioRepository.deleteAll();
    }

    @Override
    public Usuario findById(Long id) {
        id = (id != null) ? id : 0L;
        return this.usuarioRepository.findOne(id);
    }

    public Usuario findByUsername(String username) {
        if (StringUtils.isEmpty(username))
            return null;

        return this.usuarioRepository.findByUsername(username);
    }

    @Override
    public List<Usuario> findAll() {
        return this.usuarioRepository.findAll();
    }

    @Override
    public Page<Usuario> findAll(Pageable pageable) {
        return this.usuarioRepository.findAll(pageable);
    }
}
