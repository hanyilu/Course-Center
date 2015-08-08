package businesslogic.adminbl;

import java.util.ArrayList;

import vo.UserVo;
import businesslogic.controlbl.Controller;
import businesslogicservice.adminblservice.AdminBLService;

public class AdminController implements AdminBLService{
	private Admin ad;
	private Controller con;
	
	public AdminController(){
		ad = new Admin();
		con = new Controller();	
	}

	@Override
	public String addUser(UserVo user) {
		// TODO Auto-generated method stub
		if(con.getState() == 7){	//假期，可调整用户
			
			/*
			 * 根据当前学期确定学生的注册年份
			 */
			if (user.getRole().equals("Student")) {
				int year = con.getYear();
				int term = con.getTerm();
				if (term == 0)
					user.setRegisterYear(year);
				else
					user.setRegisterYear(year - 1);
			}
			
			return ad.addUser(user);
		}
		return "当前不在更改用户时间内，详情参见页面状态控制";
	}

	@Override
	public String deleteUser(UserVo user) {
		// TODO Auto-generated method stub
		if(con.getState()==7){
			return ad.deleteUser(user);
		}
		return "当前不在更改用户时间内，详情参见页面状态控制";
	}
	
	@Override
	public void deleteUser(int registerYear) {
		// TODO Auto-generated method stub
		ad.deleteUser(registerYear);
	}

	/*@Override
	public String teacherGiveDel(String oldT, String newT) {
		// TODO Auto-generated method stub
		if(con.getState()==7){
			return ad.teacherGiveDel(oldT,newT);
		}
		return "当前不在更改用户时间内，详情参见页面状态控制";
	}*/

	@Override
	public String modifyUser(UserVo user) {
		// TODO Auto-generated method stub
	//	if(con.getState()==7){
			return ad.modifyUser(user);
		//}
		//return "当前不在更改用户时间内，详情参见页面状态控制";
	}

	@Override
	public ArrayList<UserVo> getUser() {
		// TODO Auto-generated method stub
		ArrayList<UserVo> user = ad.getUser();
		
		/*
		 * 调整学生的年级信息
		 */
		con.adjustGrade(user);
		
		return user;
	}

	@Override
	public ArrayList<UserVo> getUser(String role) {
		// TODO Auto-generated method stub
		ArrayList<UserVo> user = ad.getUser(role);
		
		/*
		 * 若是学生，则调整其年级信息
		 */
		if (role.equals("Student")) {
			con.adjustGrade(user);
		}
		
		return user;
	}

	@Override
	public ArrayList<UserVo> getDeUser(String de, String role) {
		// TODO Auto-generated method stub
		ArrayList<UserVo> user = ad.getDeUser(de, role);
		
		if (role.equals("Student")) {
			con.adjustGrade(user);
		}
		
		return user;
	}
	
	@Override
	public ArrayList<UserVo> getDeUser(String department) {
		// TODO Auto-generated method stub
		return ad.getDeUser(department);
	}

	@Override
	public UserVo getUserById(String id) {
		// TODO Auto-generated method stub
		UserVo user = ad.getUserById(id);
		con.adjustGrade(user);
		return user;
	}

	@Override
	public ArrayList<String> getDepartment() {
		// TODO Auto-generated method stub
		return ad.getDepartment();
	}
	
	@Override
	public boolean login(String password) {
		// TODO Auto-generated method stub
		return ad.login(password);
	}

	@Override
	public String changePassword(String old, String new1, String new2) {
		// TODO Auto-generated method stub
		return ad.changePassword(old,new1,new2);
	}
}
