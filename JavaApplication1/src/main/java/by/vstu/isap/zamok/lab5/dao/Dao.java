package by.vstu.isap.zamok.lab5.dao;

import java.util.List;
import by.vstu.isap.zamok.lab5.entity.AbstractEntity;

public interface Dao<T extends AbstractEntity> {

	T read(Long id);

	List<T> read();

	void save(T entity);

	void delete(Long id);

}
