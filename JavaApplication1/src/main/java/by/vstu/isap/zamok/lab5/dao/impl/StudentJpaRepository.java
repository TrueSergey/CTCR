package by.vstu.isap.zamok.lab5.dao.impl;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import by.vstu.isap.zamok.lab5.entity.Student;
import by.vstu.isap.zamok.lab5.entity.Group;

public interface StudentJpaRepository extends JpaRepository<Student, Long> {
	
	List<Student> findByGroup(Group group);
	
	List<Student> findBySurname(String surname);

}
