package by.vstu.isap.zamok.lab5.service;

import by.vstu.isap.zamok.lab5.entity.Group;

public interface GroupService extends Service<Group> {
	
	Group readByName(String name);

}
