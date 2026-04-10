package by.vstu.isap.zamok.lab5.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import by.vstu.isap.zamok.lab5.dao.impl.GroupJpaRepository;
import by.vstu.isap.zamok.lab5.entity.Group;
import by.vstu.isap.zamok.lab5.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {

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
	public void delete(Group entity) {
		repository.delete(entity.getId());
	}

	@Override
	public Group readByName(String name) {
		return repository.findByName(name);
	}

}
