package po;

import java.io.Serializable;

public class AdminPo implements Serializable {
	
	private static final long serialVersionUID = 007;
	
	private String password;
	
	public AdminPo(String p){
		password=p;
	}
		
	public String getPassword(){
		return this.password;
	}
}
