package practice;


public abstract class User{
	protected String username;
	protected String password;
	protected String firstname;
	protected String lastname;
	
	User(String un, String pass, String first, String last){
		this.username=un;
		this.password=pass;
		this.firstname=first;
		this.lastname=last;
		
		
	
	
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getFirstname() {
		return firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

}

	







