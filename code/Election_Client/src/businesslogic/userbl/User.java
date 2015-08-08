package businesslogic.userbl;

import java.io.IOException;
import java.rmi.RemoteException;

import po.UserPo;
import rmi.Client;
import businesslogic.controlbl.Controller;
import businesslogicservice.controlblservice.ControllerBLService;
import businesslogicservice.userblservice.UserBLService;
import dataservice.datafactoryservice.DataFactoryService;

public class User implements UserBLService {
	
	//静态的user对象，存储当前使用系统的用户的id，身份，密码，院系，年级等信息
	private static User user = new User();
	private String id;
	private String role;
	private String password;
	private String department;
	private int grade;
	
	private DataFactoryService service;
	private Client client;
	
	public User() {
		this("", "", "");
	}
	
	public User(String id, String r, String pass) {
		client = new Client();
		service = client.getFactory();
		this.id = id;
		role = r;
		password = pass;
	}
	
	public static User getUser() {
		return user;
	}
	
	public String getUserRole() {
		return this.role;
	}
	
	public String getUserID() {
		return this.id;
	}
	
	public String getUserPassword() {
		return this.password;
	}
	
	public String getUserDepartment() {
		return this.department;
	}
	
	public int getUserGrade() {
		return this.grade;
	}
	
	@Override
	public String changePassword(String old, String new1, String new2) {
		// TODO Auto-generated method stub
		if (old.length() == 0) {
			return "请输入旧密码";
		}
		else if(old.equals(user.password)) {
			if(new1.equals(new2)) {
				if (new1.length() == 0) {
					return "请输入新密码";
				}
				else if(new1.length() < 6) {
					return "新密码长度至少为6";
				}
				else {
					
					//更新静态对象的密码信息
					user.password = new1;
					try {
						UserPo po = service.getUserDatabase().find(user.id);
						po.setPassword(new1);
						service.getUserDatabase().update(po);
						return "密码修改成功";
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return null;
					}
				}
			}
			else {
				return "两次新密码不一致";
			}
		}
		else {
			return "旧密码错误";
		}
	}

	@Override
	public String login(String id, String password) {
		// TODO Auto-generated method stub
		try {
			UserPo po = service.getUserDatabase().find(id);
			if(po == null) {
				return "用户不存在";
			}
			if(password.equals(po.getPassword())) {
				
				/*
				 * 初始化当前用户的各项信息
				 */
				user.id = id;
				user.password = password;
				user.role = po.getRole();
				user.department = po.getDepartment();
				ControllerBLService con = new Controller();
				user.grade = con.getGrade(po.getRegisterYear());
				
				String result = user.role + " success";
				return result;
			}
			else {
				return "密码错误";
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (@SuppressWarnings("hiding") IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
