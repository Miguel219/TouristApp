package LoginIn;

import java.sql.*;
import java.util.Scanner;
import java.util.Random;

public class Main {
	private String driver;


	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		Informacion Usuario =  new Informacion();
		Random rand = new Random();

		for (int i=1; i<=1; i++) {
			int userid = rand.nextInt(1000000);
			System.out.println("Ingrese el nombre de Usuario : ");
			String username = teclado.nextLine();
			teclado.nextLine();
			System.out.println("Ingrese un numero de telefono: ");
			int phone = teclado.nextInt();
			teclado.nextLine();
			System.out.println("Ingrese un correo electronico: ");
			String email = teclado.nextLine();
			teclado.nextLine();
			int acct = 1;
			System.out.println("Ingrese una fecha de nacimiento: ");
			String birthdate = teclado.nextLine();
			Usuario.llenarInformacion(i, userid, username, phone, email, acct, birthdate);
		}

		Usuario.Info();
			
	}
}