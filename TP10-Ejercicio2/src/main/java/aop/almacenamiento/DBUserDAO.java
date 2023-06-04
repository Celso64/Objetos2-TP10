package aop.almacenamiento;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import aop.domain.portsin.User;

@Component
public class DBUserDAO implements UserDAO {

    private final String url = "jdbc:sqlite:./src/main/resources/usuarios";
    private Connection conexion;

    public DBUserDAO() {
	try {
	    this.conexion = DriverManager.getConnection(url);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public void guardarUser(User usuario) {

	try {
	    String consulta = "INSERT INTO usuarios(username, fecha_creacion) VALUES(?, DATE())";
	    PreparedStatement st = conexion.prepareStatement(consulta);

	    st.setString(1, usuario.userName());
	    st.executeUpdate();

	} catch (Exception e) {
	    throw new RuntimeException("Error en la DB || " + e.getMessage());
	}
    }

}
