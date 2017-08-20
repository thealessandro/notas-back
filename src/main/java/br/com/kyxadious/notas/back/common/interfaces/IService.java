package br.com.kyxadious.notas.back.common.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by alessandro on 29/06/17.
 */

public interface IService<T, PK> {

    T save(T t);

    T update(T t);

    Boolean delete(PK id);

    T findById(PK id);

    List<T> findAll();

    Page<T> findAll(Pageable pageable);
}
