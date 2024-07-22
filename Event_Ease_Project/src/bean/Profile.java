package bean;

public abstract class  Profile {
	private String id;
	private String password;
	private String email; 
	private int tlf_number;
	
	public Profile() {
		
	}
	public Profile(String id, String password, String email, int tlf_number) {
		this.id = id;
		this.password  = password;
		this.email = email;
		this.tlf_number = tlf_number;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTlf_number() {
		return tlf_number;
	}

	public void setTlf_number(int tlf_number) {
		this.tlf_number = tlf_number;
	}
	
	//public abstract autorizationCompany() {

	//}
	//public abstract autorizationEvent() {

	//}
	
}
