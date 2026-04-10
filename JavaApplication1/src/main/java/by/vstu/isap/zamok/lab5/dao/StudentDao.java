package by.vstu.isap.zamok.lab5.dao;

import java.util.List;
import by.vstu.isap.zamok.lab5.entity.Student;
import by.vstu.isap.zamok.lab5.entity.Group;

public interface StudentDao extends Dao<Student> {

	List<Student> read(Group group);

}
