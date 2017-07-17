package br.com.kyxadious.notas.back.service;

import br.com.kyxadious.notas.back.entity.Nota;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by alessandro on 16/07/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class NotaServiceTests {

    @Autowired
    private NotaService notaService;

    @Test
    @Transactional
    public void salvarNota() {
        Nota nota = new Nota();
        nota.setTexto("Nota de teste");
        nota.setCor("#FF0000");
        nota.setDataCadastro(Calendar.getInstance().getTime());

        Nota notaSalva = this.notaService.save(nota);
        assertNotNull(notaSalva);
    }

    @Test
    @Transactional
    public void buscarNota() {
        Nota nota = new Nota();
        nota.setTexto("Nota de teste");
        nota.setCor("#FF0000");
        nota.setDataCadastro(Calendar.getInstance().getTime());

        Nota notaSalva = this.notaService.save(nota);
        assertNotNull(notaSalva);

        Nota notaEncontrada = this.notaService.findById(notaSalva.getId());
        assertNotNull(notaEncontrada);
        assertEquals(nota.getTexto(), notaEncontrada.getTexto());
        assertEquals(nota.getCor(), notaEncontrada.getCor());
    }

    @Test
    @Transactional
    public void buscarNotas() {
        Nota nota = new Nota();
        nota.setTexto("Nota de teste");
        nota.setCor("#FF0000");
        nota.setDataCadastro(Calendar.getInstance().getTime());

        Nota notaSalva = this.notaService.save(nota);
        assertNotNull(notaSalva);

        List<Nota> notas = this.notaService.findAll();
        assertNotNull(notas);
        assertTrue(notas.size() > 0);
    }

    @Test
    @Transactional
    public void atualizarNota() {
        Nota nota = new Nota();
        nota.setTexto("Nota de teste");
        nota.setCor("#FF0000");
        nota.setDataCadastro(Calendar.getInstance().getTime());

        Nota notaSalva = this.notaService.save(nota);
        assertNotNull(notaSalva);

        notaSalva.setTexto("Notas é um bom app!");
        Nota notaAtualizada = this.notaService.update(notaSalva);
        assertNotNull(notaAtualizada);
        assertEquals("Notas é um bom app!", notaAtualizada.getTexto());
    }

    @Test
    @Transactional
    public void excluirNotas() {
        Nota nota = new Nota();
        nota.setTexto("Nota de teste");
        nota.setCor("#FF0000");
        nota.setDataCadastro(Calendar.getInstance().getTime());

        Nota notaSalva = this.notaService.save(nota);
        assertNotNull(notaSalva);

        this.notaService.delete(notaSalva.getId());

        Nota notaEncontrada = this.notaService.findById(notaSalva.getId());
        assertNull(notaEncontrada);
    }
}
