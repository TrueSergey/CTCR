package by.vstu.isap.zamok.lab5.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "gruppyi")
public class Group extends AbstractEntity {

	@Column(nullable = false, unique = true, length = 50)
	@NotNull(message = "Имя группы не может быть null")
	@Size(min = 1, max = 50, message = "Имя группы должно быть от 1 до 50 символов")
	private String name;

	@OneToMany(mappedBy = "group")
	private List<Student> students;

	public Group() {
	}

	public Group(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

}
