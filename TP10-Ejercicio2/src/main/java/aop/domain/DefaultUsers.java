package aop.domain;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Component;

import aop.almacenamiento.UserDAO;
import aop.domain.portsin.User;
import aop.domain.portsin.Users;

@Component
public class DefaultUsers implements Users {

    private static List<User> users = List.of(new User(1, "emolinari"), new User(2, "nabaldo"), new User(3, "japalco"));

    private UserDAO registro;

    public DefaultUsers(UserDAO registro) {
	this.registro = registro;
    }

    @Override
    public List<User> users() {
	return users;
    }

    @Override
    public User userById(int id) {
	Optional<User> ouser = users.stream().filter(u -> u.sameId(id)).findFirst();

	return ouser.orElseThrow(() -> new RuntimeException("Id de usuario inválido"));
    }

    @Override
    @Trace
    public User usersByName(String userName) {
	Optional<User> ouser = users.stream().filter(u -> u.sameUsername(userName)).findFirst();

	return ouser.orElseThrow(() -> new RuntimeException("nombre de usuario inexistente"));
    }

    @Override
    @Time
    public void agregarUsers(Integer cantidad) {

	for (int i = 0; i < cantidad; i++) {
	    this.registro.guardarUser(users.get(getIndexRandom()));
	}
    }

    private static Integer getIndexRandom() {
	Random r = new Random();
	Integer res = Math.abs(r.nextInt()) % users.size();
	return res;
    }

}
