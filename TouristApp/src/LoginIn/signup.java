package LoginIn;

public class signup {
	private int userid;
	private String username;
	private int phone;
	private String email;
	private int acct;
	private String birthdate;
	
	public signup() {
		userid = 0;
		username = "";
		phone = 0;
		email  = "";
		acct = 0;   		//account type
		birthdate = "";
	}

	public void setUserID(int userid) {
		this.userid = userid;
	}
	
	public int getUserID() {
		return userid;
	}
	
	public void setUserName(String username) {
		this.username = username;
	}
	
	public String getUserName() {
		return username;
	}
	
	public void setPhone(int phone) {
		this.phone = phone;
	}
	
	public int getPhone() {
		return phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	void setAccT(int acct) {
		this.acct = acct;
	}
	
	public int getAccT() {
		return acct;
	}
	
	public void setBirthDate(String birthdate) {
		this.birthdate = birthdate;
	}
	
	public String getBirthDate() {
		return birthdate;
	}
	
}
