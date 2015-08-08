package data.coursedata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import po.CoursePo;
import po.PlanPo;
import data.datafactory.DataFactory;
import dataservice.coursedataservice.CourseDataService;

@SuppressWarnings("serial")
public class CourseData extends UnicastRemoteObject implements CourseDataService {

	@SuppressWarnings("unused")
	private Connection con;
	private Statement sta;
	private DataFactory df;
	
	public CourseData() throws RemoteException {
		con = null;
		sta = null;
		df = new DataFactory();
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("finally")
	@Override
	public ArrayList<CoursePo> findT(String id) throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		ArrayList<CoursePo> c = new ArrayList<CoursePo>();
		try {
			ResultSet set = sta.executeQuery("select * from Course,Plan where Course.courseNo = Plan.courseNo "
					+ "and teacher='" + id + "'");
			while (set.next()) {
				c.add(getCoursePo(set));
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			df.closeConnection();
			return c;
		}
	}

	@SuppressWarnings("finally")
	@Override
	public ArrayList<CoursePo> findAll() throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		ArrayList<CoursePo> c = new ArrayList<CoursePo>();
		try {
			ResultSet set = sta.executeQuery("select * from Course,Plan where Course.courseNo = Plan.courseNo");
			while (set.next()) {
				CoursePo po = getCoursePo(set);
				c.add(po);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			df.closeConnection();
			return c;
		}
	}

	@SuppressWarnings("finally")
	@Override
	public ArrayList<CoursePo> findElection() throws RemoteException {
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		ArrayList<CoursePo> c = new ArrayList<CoursePo>();
		try {
			ResultSet set = sta.executeQuery("select * from Course,Plan where Course.courseNo = Plan.courseNo and "
					+ "optional=1");
			while (set.next()) {
				c.add(getCoursePo(set));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			df.closeConnection();
			return c;
		}
	}

	@SuppressWarnings("finally")
	@Override
	public String insert(CoursePo po) throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		String result = "该课程已存在";
		try {
			int op = df.boolToInt(po.isOptional());
			
			sta.execute("insert into Course values('" + po.getPlan().getCourseNo() + "','" + 
					po.getTime() + "','" + po.getTeacher() + "'," + op + ",'" + po.getTextbook() + "','" +
					po.getReferbook() + "','" + po.getOutline() + "',"+ po.getStudentNum() + ")");
			result = "操作成功";
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
	public void update(CoursePo po) throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		try {
			int op = df.boolToInt(po.isOptional());
			
			sta.execute("update Course set time='" + po.getTime() + "',teacher='"+po.getTeacher()+"',optional="+
					op+",textbook='"+po.getTextbook()+"',referencebook='"+po.getReferbook()+"',outline='"+po.getOutline()+
					 "',studentNum=" + po.getStudentNum() + " where courseNo='"+po.getPlan().getCourseNo()+"'");
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
	public ArrayList<CoursePo> findJW(String department) throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		ArrayList<CoursePo> c = new ArrayList<CoursePo>();
		try {
			ResultSet set = sta.executeQuery("select * from Course,Plan where Course.courseNo = Plan.courseNo and "
					+ "department='" + department + "'");
			while (set.next()) {
				c.add(getCoursePo(set));
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			df.closeConnection();
			return c;
		}
	}

	@SuppressWarnings("finally")
	@Override
	public CoursePo findCourse(String courseNo) throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		CoursePo po = null;
		try {
			ResultSet set = sta.executeQuery("select * from Course,Plan where Course.courseNo = Plan.courseNo "
					+ "and Course.courseNo='" + courseNo + "'");
			while (set.next()) {
				po = getCoursePo(set);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			df.closeConnection();
			return po;
		}
	}
	
	@SuppressWarnings("finally")
	@Override
	public ArrayList<CoursePo> findDeCourse(String department, int term) throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		ArrayList<CoursePo> course = new ArrayList<CoursePo>();
		try {
			ResultSet set = sta.executeQuery("select * from Course,Plan where Course.courseNo=Plan.courseNo and " + 
					"department='" + department + "' and term=" + term);
			CoursePo po;
			while (set.next()) {
				po = getCoursePo(set);
				
				if (po != null & !po.isOptional()) {
					course.add(po);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			df.closeConnection();
			return course;
		}
	}
	
	@SuppressWarnings("finally")
	/**
	 * 根据数据库信息封装CoursePo对象
	 * @param set 数据库信息
	 * @return 封装的CoursePo对象
	 */
	private CoursePo getCoursePo(ResultSet set){
		CoursePo po = null;
		try {
			String courseNo = set.getString("Course.courseNo");
			String teacher = set.getString("teacher");
			String time = set.getString("time");
			int op = set.getInt("optional");
			boolean optional = df.intToBool(op);
			
			PlanPo plan = new PlanPo(set.getString("module"), set.getString("category"), set.getString("property"), 
					courseNo, set.getString("courseName"), set.getString("department"),set.getInt("credit"), 
					set.getInt("weekHour"), set.getInt("term"));
			po = new CoursePo(plan, teacher, time, optional, set.getString("textbook"), set.getString("referencebook"), 
					set.getString("outline"), set.getInt("studentNum"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			return po;
		}
	}
}
