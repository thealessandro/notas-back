package br.com.kyxadious.notas.back.controller;

import br.com.kyxadious.notas.back.commons.interfaces.IRestController;
import br.com.kyxadious.notas.back.domain.Nota;
import br.com.kyxadious.notas.back.service.NotaService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
    @Override
    public Page<Nota> findAll(Pageable pageable) {
        return this.notaService.findAll(pageable);
    }
}
