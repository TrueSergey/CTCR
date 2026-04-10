package by.vstu.isap.zamok.lab5.service;

import java.util.List;
import by.vstu.isap.zamok.lab5.entity.Student;
import by.vstu.isap.zamok.lab5.entity.Group;

public interface StudentService extends Service<Student> {

	List<Student> readByGroup(Group group);
	
	List<Student> readByGroupId(long id);
	
	List<Student> readBySurname(String surname);

}
