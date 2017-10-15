package br.com.kyxadious.notas.back.repository;

import br.com.kyxadious.notas.back.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by alessandro on 14/10/17.
 */

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Transactional(readOnly = true)
    Usuario findByUsername(String username);
}
