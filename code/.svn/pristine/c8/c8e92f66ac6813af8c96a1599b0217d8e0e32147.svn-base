package businesslogic.controlbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.ControlPo;
import rmi.Client;
import dataservice.datafactoryservice.DataFactoryService;
import businesslogic.adminbl.AdminController;
import businesslogic.electionbl.ElectionController;
import businesslogicservice.adminblservice.AdminBLService;
import businesslogicservice.controlblservice.ControllerBLService;
import businesslogicservice.electionblservice.ElectionBLService;
import vo.ControlVo;
import vo.UserVo;

public class Controller implements ControllerBLService{
	private ControlVo con;
	private DataFactoryService service;
	private Client client;
	
	public Controller(){
		client = new Client();
		service = client.getFactory();
		getControlVo();
	}	

	@Override
	public String change(ControlVo c){
		con = c;
		return update();
	}
	
	@Override
	//下一状态
	public void advance(){
		int f = con.getFlag();
		int s = con.getState();
		
		if(s == 7){
			if (f % 10 == 2)
				// 第二学期
				con.setFlag(f+9);
			else
				// 第一学期
				con.setFlag(f+1);
			
			con.setState(0);
		}
		else
			con.setState(s+1);
		update();
	}

	@Override
	//上一状态
	public void back(){
		int f = con.getFlag();
		int s = con.getState();
		
		if (s == 0){
			if (f % 10 == 2)
				con.setFlag(f-1);
			else
				con.setFlag(f-9);
			
			con.setState(7);
		}
		else
			con.setState(s-1);
		update();
	}
	
	@Override
	//0下学期，1上学期
	public int getTerm(){
		return con.getFlag()%2;
	}
	
	@Override
	//year-stu.Year得到学生的年级
	public int getYear(){
		return con.getFlag()/10;
	}
	
	@Override
	public int getState(){
		return con.getState();
	}

	@Override
	//返回当前ControlVo的String型数据
	public String getCurrent() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		int f = con.getFlag();
		int year = f/10;
		String t = (f%10==1)?"一":"二";
		sb.append(year-1);
		sb.append("~");
		sb.append(year);
		sb.append("学年第");
		sb.append(t);
		sb.append("学期;");
		sb.append(getState(con.getState()));
		
		return sb.toString();
	}
	
	@Override
	public int getGrade(int register) {
		// TODO Auto-generated method stub
		int year = getYear();
		int grade = year - register;
		
		return grade;
	}
	
	@Override
	public void adjustGrade(ArrayList<UserVo> user) {
		// TODO Auto-generated method stub
		if (user == null)
			return;
		
		for (int i = 0; i < user.size(); i++) {
			if (user.get(i).getRole().equals("Student")) {
				int grade = getGrade(user.get(i).getRegisterYear());
				user.get(i).setGrade(grade);
			}
		}
	}
	
	@Override
	public void adjustGrade(UserVo user) {
		// TODO Auto-generated method stub
		if (user != null && user.getRole().equals("Student")) {
			int grade = getGrade(user.getRegisterYear());
			user.setGrade(grade);
		}
	}
	
	private void getControlVo() {
		// TODO Auto-generated method stub
		//数据库
		try {
			ControlPo po= service.getControllerDatabase().find();
			con = new ControlVo(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String update(){
		int flag = con.getFlag();
		int state = con.getState();
		if(state == 4){
			
			//选课阶段结束，调用选课算法
			ElectionBLService ebls = new ElectionController();
			ebls.decideEle();
		}
		if (flag % 10 == 1 && state == 0) {
			
			//新学年开始，删除大四学生数据
			AdminBLService ad = new AdminController();
			ad.deleteUser(flag / 10);
		}
		try {
			service.getControllerDatabase().update(new ControlPo(con));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//数据库
		return "状态更改成功";
	}

	private String getState(int state) {
		// TODO Auto-generated method stub
		switch(state){
		case 0:return "制定框架策略";
		case 1:return "制定教学计划";
		case 2:return "发布课程";
		case 3:return "选课阶段";
		case 4:return "补选退选";
		case 5:return "学期进行中";
		case 6:return "登记成绩";
		default:return "假期进行中";
		}
	}
}
