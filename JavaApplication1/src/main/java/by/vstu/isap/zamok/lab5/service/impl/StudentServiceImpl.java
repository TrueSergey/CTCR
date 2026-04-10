package by.vstu.isap.zamok.lab5.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import by.vstu.isap.zamok.lab5.dao.impl.StudentJpaRepository;
import by.vstu.isap.zamok.lab5.entity.Student;
import by.vstu.isap.zamok.lab5.entity.Group;
import by.vstu.isap.zamok.lab5.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentJpaRepository repository;

	@Override
	public Student read(Long id) {
		return repository.findOne(id);
	}

	@Override
	public List<Student> read() {
		return repository.findAll();
	}

	@Override
	public void save(Student entity) {
		repository.save(entity);
	}

	@Override
	public void delete(Student entity) {
		repository.delete(entity.getId());
	}

	@Override
	public List<Student> readByGroup(Group group) {
		return repository.findByGroup(group);
	}

	@Override
	public List<Student> readByGroupId(long id) {
		// This method will be implemented in the controller layer
		// by fetching the group first
		return null;
	}

	@Override
	public List<Student> readBySurname(String surname) {
		return repository.findBySurname(surname);
	}

}
