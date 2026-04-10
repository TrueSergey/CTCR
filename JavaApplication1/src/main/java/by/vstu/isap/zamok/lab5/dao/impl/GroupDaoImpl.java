package by.vstu.isap.zamok.lab5.dao.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import by.vstu.isap.zamok.lab5.dao.GroupDao;
import by.vstu.isap.zamok.lab5.entity.Group;

@Repository
public class GroupDaoImpl implements GroupDao {

	@Autowired
	private GroupJpaRepository repository;

	@Override
	public Group read(Long id) {
		return repository.findOne(id);
	}

	@Override
	public List<Group> read() {
		return repository.findAll();
	}

	@Override
	public void save(Group entity) {
		repository.save(entity);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

}
