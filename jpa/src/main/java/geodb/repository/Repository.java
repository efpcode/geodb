package geodb.repository;

import java.util.List;

public interface Repository<T, V> {

    T save(T city);

    List<T> findAll();

    T findById(V id);

    void deleteById(V id);
}
