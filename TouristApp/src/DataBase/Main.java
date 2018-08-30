package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		getConnection();
	}

	public static Connection getConnection() throws Exception {
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost/TouristAppDataBase";
			String user = "root";
			String password = "";
			Class.forName(driver);

			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connected");
			return conn;
		} catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}
}
