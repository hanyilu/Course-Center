package data.electiondata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;

import po.CoursePo;
import po.ElectionPo;
import po.PlanPo;
import po.UserPo;
import data.coursedata.CourseData;
import data.datafactory.DataFactory;
import dataservice.coursedataservice.CourseDataService;
import dataservice.electiondataservice.ElectionDataService;

@SuppressWarnings("serial")
public class ElectionData extends UnicastRemoteObject implements ElectionDataService {
	
	@SuppressWarnings("unused")
	private Connection con;
	private Statement sta;
	private DataFactory df;

	public ElectionData() throws RemoteException {
		// TODO Auto-generated constructor stub
		con = null;
		sta = null;
		df = new DataFactory();
	}

	@Override
	public void delete(ElectionPo po) throws RemoteException {
		// TODO Auto-generated method stub
		String courseNo = po.getCourse().getPlan().getCourseNo();
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		try {
			ResultSet set = sta.executeQuery("select * from Election where courseNo='" + courseNo + "'");
			while (set.next()) {
				if (set.getInt("optional") == 1) {
					sta.executeUpdate("delete from Election where courseNo='" + courseNo + "' and student='" + 
							po.getStudent() + "'");
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

	@SuppressWarnings("finally")
	@Override
	//根据课程号查找选课学生
	public ArrayList<UserPo> find(String courseNo) throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		ArrayList<UserPo> list = new ArrayList<UserPo>();
		try {			
			//查看学生
			ResultSet set = sta.executeQuery("select * from Election,User where courseNo='" + courseNo + 
					"' and Election.student=User.id");
			while (set.next()) {
				list.add(new UserPo(set.getString("id"), set.getString("role"), set.getString("password"), set.getString("name"), 
						set.getString("gender"), set.getString("birthday"), set.getString("ic"),
						set.getString("department"), set.getInt("grade"), set.getString("contact")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			df.closeConnection();
			return list;
		}
	}

	@Override
	//查看所有可选
	public ArrayList<CoursePo> findAll() throws RemoteException {
		// TODO Auto-generated method stub
		CourseDataService cd = new CourseData();
		ArrayList<CoursePo> course = cd.findElection();
		return course;
	}

	@SuppressWarnings("finally")
	@Override
	//根据学生id和必修选修查看选课
	public ArrayList<ElectionPo> findMine(String id,int o) throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		ArrayList<ElectionPo> list = new ArrayList<ElectionPo>();
		try {
			ResultSet set = sta.executeQuery("select * from Election,Course,Plan,User where student='" + id + 
					"' and Election.courseNo=Course.courseNo and Course.courseNo=Plan.courseNo and "
							+ "Course.teacher=User.id");
			CoursePo course;
			
			while(set.next()) {
				if (set.getInt("optional") == o) {
					course = getCourse(set);
					if (course != null) {
						list.add(new ElectionPo(course, id, set.getInt("User.grade")));
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			df.closeConnection();
			return list;
		}
	}
	
	@SuppressWarnings("finally")
	@Override
	//根据id查看课程信息
	public ArrayList<CoursePo> findMyCourse(String id) throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		ArrayList<CoursePo> list = new ArrayList<CoursePo>();
		try {
			ResultSet set = sta.executeQuery("select * from Election,Course,Plan,User where student='" + id + 
					"' and Election.courseNo=Course.courseNo and Course.courseNo=Plan.courseNo and "
							+ "Course.teacher=User.id");
			CoursePo course;
			while(set.next()) {
				course = getCourse(set);
				if (course != null) {
					list.add(course);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			df.closeConnection();
			return list;
		}
	}
	
	@SuppressWarnings("finally")
	@Override
	public ArrayList<ElectionPo> findTempEle(String id) throws RemoteException {
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		ArrayList<ElectionPo> list = new ArrayList<ElectionPo>();
		try {
			ResultSet set = sta.executeQuery("select * from tempelection,Course,User,Plan where tempelection.student='" + 
		            id + "' and tempelection.courseNo=Course.courseNo and tempelection.student=User.id and "
		            + "Course.courseNo=Plan.courseNo");
			CoursePo course;
			
			while(set.next()) {
				course = getCourse(set);
				if (course != null) {
					list.add(new ElectionPo(course, id, set.getInt("User.grade")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			df.closeConnection();
			return list;
		}
	}
	
	@Override
	public void insertTemp(ElectionPo po) throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		String courseNo = po.getCourse().getPlan().getCourseNo();
		try {
			sta.executeUpdate("insert into tempelection values('" + courseNo + "','" + po.getStudent() + "')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void insert(ElectionPo po) throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		try {
			String courseNo = po.getCourse().getPlan().getCourseNo();
			ResultSet couSet = sta.executeQuery("select * from Course where courseNo='" + courseNo + "'");
			while(couSet.next()){
				sta.executeUpdate("insert into Election values('" + courseNo + "','" + po.getStudent() + "'," +
					 couSet.getInt("optional") + ")");
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
	public ArrayList<ElectionPo> findAllElection() throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		
		ArrayList<ElectionPo> ele = new ArrayList<ElectionPo>();
		try {
			ResultSet set = sta.executeQuery("select * from tempelection,Course,User,Plan where tempelection.courseNo="
					+ "Course.courseNo and tempelection.student=User.id and Course.courseNo=Plan.courseNo");
			CoursePo course;
			
			while (set.next()) {
				course = getCourse(set);
				if (course != null) {
					ele.add(new ElectionPo(course, set.getString("tempelection.student"), set.getInt("User.grade")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			df.closeConnection();
			return ele;
		}
	}
	
	@Override
	public void clearTemEle() throws RemoteException{
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		try {
			sta.executeUpdate("delete from tempelection");
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
	public ArrayList<UserPo> findTempStu(String courseNo) throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		
		ArrayList<UserPo> po = new ArrayList<UserPo>();
		try {
			ResultSet set = sta.executeQuery("select * from tempelection,User where courseNo='" + courseNo + "'"
					+ " and tempelection.student=User.id");
			while (set.next()) {
				po.add(new UserPo(set.getString("id"), set.getString("role"), set.getString("password"), set.getString("name"), 
							set.getString("gender"), set.getString("birthday"), set.getString("ic"),
							set.getString("department"), set.getInt("grade"), set.getString("contact")));
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

	//根据数据库信息包装cousepo
	private CoursePo getCourse(ResultSet set) {
		PlanPo plan = null;
		try {
			plan = new PlanPo(set.getString("module"), set.getString("category"), set.getString("property"), 
					set.getString("Course.courseNo"), set.getString("courseName"), set.getString("Plan.department"), 
					set.getInt("credit"), set.getInt("weekHour"), set.getInt("term"));
			String teacher = set.getString("User.id");
			
			boolean optional = df.intToBool(set.getInt("Course.optional"));
			CoursePo course = new CoursePo(plan, teacher, set.getString("time"), optional, "", "", "", 
					set.getInt("studentNum"));
			return course;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
