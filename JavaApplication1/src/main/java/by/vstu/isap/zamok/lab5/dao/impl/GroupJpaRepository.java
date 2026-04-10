package by.vstu.isap.zamok.lab5.dao.impl;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import by.vstu.isap.zamok.lab5.entity.Group;

public interface GroupJpaRepository extends JpaRepository<Group, Long> {
	
	Group findByName(String name);

}
