package po;

import java.io.Serializable;

import vo.UserVo;

public class UserPo implements Serializable {

	private static final long serialVersionUID = 001;
	
	private String id;
	private String role;
	private String password;
	private String name;
	private String gender;
	private String birthday;
	private String ic;
	private String department;
	private int registerYear;
	private int grade;
	private String contact;
	
	public UserPo(String i, String r, String p, String n, String ge, String b, String ic, String d, int g, String c) {
		id = i;
		role = r;
		password = p;
		name = n;
		gender = ge;
		birthday = b;
		this.ic = ic;
		department = d;
		registerYear = g;
		contact = c;
	}
	
	public UserPo(UserVo vo) {
		id = vo.getID();
		role = vo.getRole();
		password = vo.getPassword();
		name = vo.getName();
		gender = vo.getGender();
		birthday = vo.getBirthday();
		ic = vo.getIc();
		department = vo.getDepartment();
		registerYear = vo.getRegisterYear();
		contact = vo.getContact();
	}
	
	public String getRole(){
		return role;
	}
	
	public String getID(){
		return id;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String p){
		password = p;
	}
	
	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getIc() {
		return ic;
	}

	public String getDepartment() {
		return department;
	}

	public int getRegisterYear() {
		return registerYear;
	}

	public int getGrade() {
		return grade;
	}

	public String getContact() {
		return contact;
	}
}
