package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexion {
	private Connection conn;
	private String driver;
	private String url;
	private String user;
	private String password;
	private String baseDatos;
	

	public Conexion() {
		// TODO Auto-generated constructor stub
		driver = "com.mysql.cj.jdbc.Driver";
		baseDatos = "TouristAppDataBase";
		url = "jdbc:mysql://localhost/"+baseDatos;
		user = "root";
		password = "";
		//Class.forName(driver);
	}
	
	
	
	/**
	 * @param conn
	 * @param driver
	 * @param url
	 * @param user
	 * @param password
	 * @param baseDatos
	 */
	public Conexion(Connection conn, String driver, String url, String user, String password, String baseDatos) {
		this.conn = conn;
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
		this.baseDatos = baseDatos;
	}



	public Connection getConexion() throws SQLException {
		if (conn == null)
			conn = DriverManager.getConnection(url, user, password);
		return conn;
	}
	
	public void cerrarConexion() throws SQLException {
		conn.close();
	}

}
