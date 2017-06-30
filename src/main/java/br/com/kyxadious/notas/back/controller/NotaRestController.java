package br.com.kyxadious.notas.back.controller;

import br.com.kyxadious.notas.back.common.interfaces.IRestController;
import br.com.kyxadious.notas.back.entity.Nota;
import br.com.kyxadious.notas.back.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * Created by alessandro on 29/06/17.
 */

@RestController
@RequestMapping("api/v1/notas")
public class NotaRestController implements IRestController<Nota, Long> {

    @Autowired
    private NotaService notaService;

    @PostMapping("")
    @Override
    public Nota save(@RequestBody Nota nota) {
        return this.notaService.save(nota);
    }

    @PutMapping("")
    @Override
    public Nota update(@RequestBody Nota nota) {
        return this.notaService.update(nota);
    }

    @DeleteMapping("/{nota-id}")
    @Override
    public void delete(@PathVariable("nota-id") Long id) {
        this.notaService.delete(id);
    }

    @GetMapping("/{nota-id}")
    @Override
    public Nota findById(@PathVariable("nota-id") Long id) {
        return this.notaService.findById(id);
    }

    @GetMapping("")
    @Override
    public Page<Nota> findAll(Pageable pageable) {
        return this.notaService.findAll(pageable);
    }
}
