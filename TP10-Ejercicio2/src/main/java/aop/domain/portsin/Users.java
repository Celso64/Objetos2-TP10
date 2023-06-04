package aop.domain.portsin;

import java.util.List;

public interface Users {
    List<User> users();

    User userById(int id);

    User usersByName(String userName);

    void agregarUsers(Integer cantidad);
}
