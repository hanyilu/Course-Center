package data.userdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;

import po.UserPo;
import data.datafactory.DataFactory;
import dataservice.userdataservice.UserDataService;

@SuppressWarnings("serial")
public class UserData extends UnicastRemoteObject implements UserDataService {
	
	@SuppressWarnings("unused")
	private Connection con;
	private Statement sta;
	private DataFactory df;

	public UserData() throws RemoteException {
		// TODO Auto-generated constructor stub
		con = null;
		sta = null;
		df = new DataFactory();
	}
		
	@Override
	public void delete(UserPo po) throws RemoteException {
		// TODO Auto-generated method stub
		String id = po.getID();
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		try {
			ResultSet set = sta.executeQuery("select role from User where id='" + id + "'");
			while (set.next()) {
				String role = set.getString("role");
				
				//判断用户角色，根据角色删除该用户的所有相关信息
				if (role.equals("Student")) {
					sta.executeUpdate("delete from Election where student='" + id + "'");
					sta.executeUpdate("delete from Score where student='" + id + "'");
					sta.executeUpdate("update User set valid=0 where id='" + id + "'");
				}			
				else {
					
					//直接删除该用户
					sta.executeUpdate("update User set valid=0 where id='" + id + "'");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			df.closeConnection();
		}
	}
	
	@Override
	public void delete(int year) throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		
		int registerYear = year - 5;
		try {
			ResultSet set = sta.executeQuery("select * from User where grade=" + registerYear + " and valid=1");
			
			/*
			 * 得到相关学生信息
			 */
			ArrayList<String> id = new ArrayList<String>();
			while (set.next()) {
				String s = set.getString("id");
				id.add(s);
			}
			
			for (int i = 0; i < id.size(); i++) {
				sta.executeUpdate("delete from Election where student='" + id.get(i) + "'");
				sta.executeUpdate("delete from Score where student='" + id.get(i) + "'");
				sta.executeUpdate("update User set valid=0 where id='" + id.get(i) + "'");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			df.closeConnection();
		}
	}

	@SuppressWarnings("finally")
	@Override
	public UserPo find(String id) throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		UserPo po = null;
		try {
			ResultSet set = sta.executeQuery("select * from User where id='" + id+"'");
			while(set.next()){
				if (set.getInt("valid") == 1) {
					po = new UserPo(set.getString(1), set.getString(3), set.getString(2), set.getString("name"),
							set.getString("gender"), set.getString("birthday"), set.getString("ic"), 
							set.getString("department"), set.getInt("grade"), set.getString("contact"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			df.closeConnection();
			return po;
		}
	}

	@SuppressWarnings("finally")
	@Override
	public String insert(UserPo po) throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		String result = "ID已存在";
		try {
			sta.executeUpdate("insert into User values('" + po.getID() + "','" + po.getPassword() + "','" + po.getRole()
					+ "','" + po.getName() + "','" + po.getGender() + "','" + po.getBirthday() + "','" + po.getIc() + 
					"','" + po.getDepartment() + "'," + po.getRegisterYear() + ",'" + po.getContact() + "', 1)");
			result = "添加成功";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			df.closeConnection();
			return result;
		}
	}

	@Override
	public void update(UserPo po) throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		try {
			sta.executeUpdate("update User set role='" + po.getRole() + "',password='" + po.getPassword() + 
					"',name='" + po.getName() + "',gender='" + po.getGender() + "',birthday='" + po.getBirthday() + 
					"',ic='" + po.getIc() + "',department='" + po.getDepartment() + "',grade=" + po.getRegisterYear() + 
					",contact='" + po.getContact() + "' where id='" + po.getID() + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			df.closeConnection();
		}
	}

	@SuppressWarnings("finally")
	@Override
	public ArrayList<UserPo> findAll() throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		ArrayList<UserPo> user = new ArrayList<UserPo>();
		try {
			ResultSet set = sta.executeQuery("select * from User");
			while(set.next()) {
				if (set.getInt("valid") == 1) {
					user.add(new UserPo(set.getString(1), set.getString(3), set.getString(2), set.getString("name"), 
							set.getString("gender"), set.getString("birthday"), set.getString("ic"),
							set.getString("department"), set.getInt("grade"), set.getString("contact")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			df.closeConnection();
			return user;
		}
	}

	@SuppressWarnings("finally")
	@Override
	public ArrayList<UserPo> findUser(String role) throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		ArrayList<UserPo> user = new ArrayList<UserPo>();
		try {
			ResultSet set = sta.executeQuery("select * from User where role='" + role + "'");
			while(set.next()) {
				if (set.getInt("valid") == 1) {
					user.add(new UserPo(set.getString(1), set.getString(3), set.getString(2), set.getString("name"), 
							set.getString("gender"), set.getString("birthday"), set.getString("ic"),
							set.getString("department"), set.getInt("grade"), set.getString("contact")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			df.closeConnection();
			return user;
		}
	}

	@SuppressWarnings("finally")
	@Override
	public ArrayList<UserPo> findDeUser(String de, String role) throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		ArrayList<UserPo> user = new ArrayList<UserPo>();
		try {
			ResultSet set = sta.executeQuery("select * from User where department='" + de + "' and role='" + role + "'");
			while(set.next()) {
				if (set.getInt("valid") == 1) {
					user.add(new UserPo(set.getString(1), set.getString(3), set.getString(2), set.getString("name"), 
							set.getString("gender"), set.getString("birthday"), set.getString("ic"),
							set.getString("department"), set.getInt("grade"), set.getString("contact")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			df.closeConnection();
			return user;
		}
	}

	@SuppressWarnings("finally")
	@Override
	public ArrayList<UserPo> findDeUser(String de) throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		ArrayList<UserPo> user = new ArrayList<UserPo>();
		try {
			ResultSet set = sta.executeQuery("select * from User where department='" + de + "'");
			while(set.next()) {
				if (set.getInt("valid") == 1) {
					user.add(new UserPo(set.getString(1), set.getString(3), set.getString(2), set.getString("name"), 
							set.getString("gender"), set.getString("birthday"), set.getString("ic"),
							set.getString("department"), set.getInt("grade"), set.getString("contact")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			df.closeConnection();
			return user;
		}
	}
	
	/*@Override
	public void giveDelTeacher(String oldT, String newT) throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		try {
			
			//移交
			sta.executeUpdate("update Course set teacher='" + newT + "' where teacher='" + oldT + "'");
			
			//删除
			sta.executeUpdate("update User set valid=0 where id='" + oldT + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
