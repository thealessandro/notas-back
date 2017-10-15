package br.com.kyxadious.notas.back.service;

import br.com.kyxadious.notas.back.commons.interfaces.IService;
import br.com.kyxadious.notas.back.domain.Nota;
import br.com.kyxadious.notas.back.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 * Created by alessandro on 29/06/17.
 */

@Service
@Transactional
public class NotaService implements IService<Nota, Long> {

    @Autowired
    private NotaRepository notaRepository;

    @Override
    public Nota save(Nota nota) {
        Nota notaResultado = null;

        if (nota != null && nota.getId() == null) {
            nota.setDataCadastro(Calendar.getInstance().getTime());
            notaResultado = this.notaRepository.save(nota);
        }
        return notaResultado;
    }

    @Override
    public Nota update(Nota nota) {
        Nota notaResultado = null;

        if (nota != null && nota.getId() != null) {
            nota.setDataEdicao(Calendar.getInstance().getTime());
            notaResultado = this.notaRepository.save(nota);
        }
        return notaResultado;
    }

    @Override
    public void delete(Long id) {
        id = (id != null) ? id : 0L;
        this.notaRepository.delete(id);
    }

    @Override
    public Nota findById(Long id) {
        id = (id != null) ? id : 0L;
        return this.notaRepository.findOne(id);
    }

    @Override
    public List<Nota> findAll() {
        return this.notaRepository.findAll();
    }

    @Override
    public Page<Nota> findAll(Pageable pageable) {
        return this.notaRepository.findAll(pageable);
    }
}
