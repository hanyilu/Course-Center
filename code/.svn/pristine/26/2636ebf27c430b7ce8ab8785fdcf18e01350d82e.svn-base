package businesslogic.adminbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.datafactoryservice.DataFactoryService;
import po.AdminPo;
import po.UserPo;
import rmi.Client;
import vo.UserVo;

public class Admin {
	
	private DataFactoryService service;
	private Client client;
	
	public Admin(){
		client = new Client();
		service = client.getFactory();
	}

	public String addUser(UserVo user) {
		// TODO Auto-generated method stub
		UserPo po = new UserPo(user);
		String result ="添加成功";
		try {
			result = service.getUserDatabase().insert(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public String deleteUser(UserVo user) {
		// TODO Auto-generated method stub
		UserPo po = new UserPo(user);
		try {
			service.getUserDatabase().delete(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "删除成功";
	}
	
	public void deleteUser(int registerYear) {
		// TODO Auto-generated method stub
		try {
			service.getUserDatabase().delete(registerYear);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String modifyUser(UserVo user) {
		// TODO Auto-generated method stub
		UserPo po = new UserPo(user);
		try {
			service.getUserDatabase().update(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "修改成功";
	}

	public ArrayList<UserVo> getUser() {
		// TODO Auto-generated method stub
		ArrayList<UserVo> u = null;
		try {
			ArrayList<UserPo> p = service.getUserDatabase().findAll();
			u = new ArrayList<UserVo>();
			for(int i = 0;i < p.size();i++)
				u.add(new UserVo(p.get(i)));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
	
	public ArrayList<UserVo> getUser(String role){
		ArrayList<UserVo> u=null;
		try {
			ArrayList<UserPo> p= service.getUserDatabase().findUser(role);
			u = new ArrayList<UserVo>();
			for(int i = 0;i<p.size();i++)
				u.add(new UserVo(p.get(i)));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
	
	public UserVo getUserById(String id){
		UserVo u =null;
		try {
			if (service.getUserDatabase().find(id) == null) {
				return null;
			}
			u= new UserVo(service.getUserDatabase().find(id));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;		
	}
	
	/*public String teacherGiveDel(String oldT, String newT) {
		// TODO Auto-generated method stub
		try {
			CourseBLService c = new CourseController();
			String result = c.isConflict(oldT, newT);
			if (result.equals("success")) {
				service.getUserDatabase().giveDelTeacher(oldT, newT);
				return "移交成功";
			}
			else {
				return result;
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "啊哦~有错误~";
		}
	}*/

	public ArrayList<UserVo> getDeUser(String de, String role) {
		// TODO Auto-generated method stub
		try {
			ArrayList<UserPo> user = service.getUserDatabase().findDeUser(de, role);
			ArrayList<UserVo> u = new ArrayList<UserVo>();
			
			for (int i = 0; i < user.size(); i++) {
				u.add(new UserVo(user.get(i)));
			}
			return u;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<UserVo> getDeUser(String department) {
		// TODO Auto-generated method stub
		try {
			ArrayList<UserPo> user = service.getUserDatabase().findDeUser(department);
			ArrayList<UserVo> u = new ArrayList<UserVo>();
			
			for (int i = 0; i < user.size(); i++) {
				u.add(new UserVo(user.get(i)));
			}
			return u;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<String> getDepartment() {
		// TODO Auto-generated method stub
		ArrayList<UserVo> user = getUser();
		ArrayList<String> de = new ArrayList<String>();
		for (int i = 0; i < user.size(); i++) {
			String d = user.get(i).getDepartment();
			if (!de.contains(d)) {
				de.add(d);
			}
		}
		
		return de;
	}

	public boolean login(String password) {
		// TODO Auto-generated method stub
		return check(password);
	}
	
	public String changePassword(String old, String new1, String new2) {
		// TODO Auto-generated method stub
		if (old.length() == 0) {
			return "请输入旧密码";
		}
		else if(check(old)){
			if(new1.equals(new2)){
				try{
					if (new1.length() == 0) {
						return "请输入新密码";
					}
					else if (new1.length() < 6) {
						return "新密码长度至少为6";
					}
					else {
						AdminPo po = new AdminPo(new1);
						service.getAdminDatabase().update(po);
						return "密码修改成功";
					}
				}catch(RemoteException e){
					e.printStackTrace();
					return null;
				}
			}
			else
				return "两次新密码不一致";
		}
		else
			return "旧密码错误";
	}
	
	private boolean check(String old) {
		// TODO Auto-generated method stub
		try {
			if(old.equals(service.getAdminDatabase().find()))
				return true;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}

