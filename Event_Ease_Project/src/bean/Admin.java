package bean;



public class Admin extends Staff {

	private boolean validated = false;
	
	
	private int companyId;
	private String iban;
	
	public Admin() {
		super();
	}
	public Admin(String id, String password, String email, int tlf_number) {
		super(id, password, email, tlf_number);
	}

	
	public boolean BankTransfer (String to) {
		return false;
	}

	public String getIBAN() {
		return iban;
	}

	public void setIBAN(String iban) {
		
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int CompanyId) {
		this.companyId = CompanyId;
	}

	public boolean isValidated() {
		return validated;
	}

	public void setValidated(boolean validated) {
		this.validated = validated;
	}

}
