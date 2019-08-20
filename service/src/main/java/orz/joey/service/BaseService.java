package orz.joey.service;

import java.util.List;

/**
 *
 * @param <T> the type of the entity's dto
 * @param <ID> the type of the entity's identifier
 */
public interface BaseService<T, S, ID> {

    T save(T dto);

    boolean deleteById(ID id);

    S update(S dto);

    T findById(ID id);

    List<T> findAll();
}
