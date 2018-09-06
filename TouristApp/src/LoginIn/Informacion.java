package LoginIn;

public class Informacion {
	private login informacion;
	
	public Informacion() {
		informacion = new login();
	}
	
	public void llenarInformacion(int num, int userid, String username, int phone, String email, int accounttype, String birthdate) {
		switch (num) {
			case 1: {
				informacion.setUserID(userid);
				informacion.setUserName(username);
				informacion.setPhone(phone);
				informacion.setEmail(email);
				informacion.setAccT(accounttype);
				informacion.setBirthDate(birthdate);
				break;
			}
			
		}
	}
	
	public void Info() {
		System.out.println("Informacion: ");
		System.out.println("Id de usuario: "+informacion.getUserID());
		System.out.println("Nombre de usuario: "+informacion.getUserName());
		System.out.println("Telefono: "+informacion.getPhone());
		System.out.println("Email: "+informacion.getEmail());
		System.out.println("Tipo de cuenta: "+informacion.getAccT());
		System.out.println("Fecha de nacimiento: "+informacion.getBirthDate());

	}
	
}
	

