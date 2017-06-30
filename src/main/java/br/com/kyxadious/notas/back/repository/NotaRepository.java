package br.com.kyxadious.notas.back.repository;

import br.com.kyxadious.notas.back.entity.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by alessandro on 29/06/17.
 */

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {

}
