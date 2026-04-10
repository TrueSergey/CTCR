package by.vstu.isap.zamok.lab5.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "studentyi")
public class Student extends AbstractEntity {

	@Column(nullable = false, length = 30)
	@NotNull(message = "Имя студента не может быть null")
	@Size(min = 1, max = 30, message = "Имя студента должно быть от 1 до 30 символов")
	private String name;

	@Column(nullable = false, length = 30)
	@NotNull(message = "Фамилия студента не может быть null")
	@Size(min = 1, max = 30, message = "Фамилия студента должна быть от 1 до 30 символов")
	private String surname;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id", nullable = false)
	@JsonProperty(access = Access.WRITE_ONLY)
	private Group group;

	public Student() {
	}

	public Student(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

}
