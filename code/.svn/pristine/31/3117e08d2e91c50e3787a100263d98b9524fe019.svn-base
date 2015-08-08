package vo;

import po.UserPo;

public class UserVo {

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
	
	public UserVo(String i, String r, String p, String n, String ge, String b, String ic, String d, int g, String c) {
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
	
	public UserVo(UserPo po) {
		id = po.getID();
		role = po.getRole();
		password = po.getPassword();
		name = po.getName();
		gender = po.getGender();
		birthday = po.getBirthday();
		ic = po.getIc();
		department = po.getDepartment();
		registerYear = po.getRegisterYear();
		contact = po.getContact();
	}
	
	public String getRole(){
		return role;
	}
	
	public void setRole(String r) {
		role = r;
	}
	
	public String getID(){
		return id;
	}
		
	public String getPassword(){
		return password;
	}
		
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return gender;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setIc(String ic) {
		this.ic = ic;
	}

	public String getIc() {
		return ic;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDepartment() {
		return department;
	}

	public int getRegisterYear() {
		return registerYear;
	}
	
	public void setRegisterYear(int r) {
		registerYear = r;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContact() {
		return contact;
	}
}
