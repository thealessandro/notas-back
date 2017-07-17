package br.com.kyxadious.notas.back.common.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by alessandro on 29/06/17.
 */
public interface IRestController<T, PK> {

    T save(T t);

    T update(T t);

    void delete(PK id);

    T findById(PK id);

    Page<T> findAll(Pageable pageable);
}
