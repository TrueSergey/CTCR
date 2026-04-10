package by.vstu.isap.zamok.lab5.dao.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import by.vstu.isap.zamok.lab5.dao.StudentDao;
import by.vstu.isap.zamok.lab5.entity.Group;
import by.vstu.isap.zamok.lab5.entity.Student;

@Repository
public class StudentDaoImpl implements StudentDao {

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
	public List<Student> read(Group group) {
		return repository.findByGroup(group);
	}

	@Override
	public void save(Student entity) {
		repository.save(entity);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

}
