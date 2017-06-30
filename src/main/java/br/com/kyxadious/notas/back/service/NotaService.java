package br.com.kyxadious.notas.back.service;

import br.com.kyxadious.notas.back.common.interfaces.IService;
import br.com.kyxadious.notas.back.entity.Nota;
import br.com.kyxadious.notas.back.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by alessandro on 29/06/17.
 */

@Service
@Transactional
public class NotaService implements IService<Nota, Long> {

    private static final int PAGE_SIZE = 100;

    @Autowired
    private NotaRepository notaRepository;

    @Override
    public Nota save(Nota nota) {
        Nota notaResultado = null;

        if (nota != null) {
            notaResultado = this.notaRepository.save(nota);
        }
        return notaResultado;
    }

    @Override
    public Nota update(Nota nota) {
        Nota notaResultado = null;
        Long id = (nota != null) ? nota.getId() : 0L;
        Nota notaSalva = this.notaRepository.findOne(id);

        if (notaSalva != null) {
            this.notaRepository.save(nota);
        }
        return notaResultado;
    }

    @Override
    public void delete(Long id) {
        this.notaRepository.delete(id);
    }

    @Override
    public Nota findById(Long id) {
        return this.notaRepository.findOne(id);
    }

    @Override
    public List<Nota> findAll() {
        return this.notaRepository.findAll();
    }

    @Override
    public Page<Nota> findAll(Pageable pageable) {
        Pageable pageRequest = new PageRequest(pageable.getPageNumber(),
                (pageable.getPageSize() <= PAGE_SIZE) ? pageable.getPageSize() : PAGE_SIZE);
        return this.notaRepository.findAll(pageRequest);
    }
}
