package data.scoredata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import po.CoursePo;
import po.PlanPo;
import po.ScorePo;
import data.datafactory.DataFactory;
import dataservice.scoredataservice.ScoreDataService;

@SuppressWarnings("serial")
public class ScoreData extends UnicastRemoteObject implements ScoreDataService{
	@SuppressWarnings("unused")
	private Connection con;
	private Statement sta;
	private DataFactory df;

	public ScoreData() throws RemoteException {	
		// TODO Auto-generated constructor stub	
		con = null;
		sta = null;
		df = new DataFactory();
	}

	@SuppressWarnings("finally")
	@Override
	public ArrayList<ScorePo> findCourseScore(String courseNo) throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		ArrayList<ScorePo> list = new ArrayList<ScorePo>();
		try{
			ResultSet set = sta.executeQuery("select * from Score where courseNo='" + courseNo + "'");
			CoursePo course = new CoursePo(new PlanPo("","","",courseNo,"","", 0, 0, 0),"","",false,"","","", 0);
			
			while (set.next()) {
				boolean access = df.intToBool(set.getInt("access"));
				list.add(new ScorePo(set.getDouble("score"), set.getDouble("gpa"), set.getInt("comCre"), set.getInt("optCre"), 
						set.getInt("credits"), access, course, set.getString("student")));
			}		
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			df.closeConnection();
			return list;
		}
	}
	
	@SuppressWarnings("finally")
	@Override
	public ArrayList<ScorePo> findMyScore(String id) throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		ArrayList<ScorePo> list = new ArrayList<ScorePo>();
		CoursePo course = null;
		try{
			ResultSet set = sta.executeQuery("select * from Score,Course,Plan,Election where Score.student='" + id + 
					"' and Score.student=Election.student" + " and Election.courseNo=Course.courseNo and Course.courseNo"
					+ "=Plan.courseNo and Score.courseNo=Course.courseNo");
			while (set.next()) {
				boolean optional = df.intToBool(set.getInt("optional"));
				course = new CoursePo(new PlanPo("", "", "", set.getString("Course.courseNo"), set.getString("courseName"), "",
						set.getInt("credit"), set.getInt("weekHour"), set.getInt("term")), set.getString("teacher"), 
						set.getString("time"), optional,"","","", 0);
				
				boolean access = df.intToBool(set.getInt("access"));
			    list.add(new ScorePo(set.getDouble("score"), set.getDouble("gpa"), set.getInt("comCre"), set.getInt("optCre"), 
						set.getInt("credits"), access,course,id));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			df.closeConnection();
			return list;
		}
	}
	
	@SuppressWarnings("finally")
	@Override
	public ArrayList<ScorePo> findMyScore(String id, int term) throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		ArrayList<ScorePo> list = new ArrayList<ScorePo>();
		CoursePo course = null;
		try{
			ResultSet set = sta.executeQuery("select * from Score,Course,Plan,Election where Score.student='" + id + 
					"' and Score.student=Election.student" + " and Election.courseNo=Course.courseNo and Course.courseNo"
					+ "=Plan.courseNo and Score.courseNo=Course.courseNo and Plan.term=" + term);
			while (set.next()) {
				boolean optional = df.intToBool(set.getInt("optional"));
				course = new CoursePo(new PlanPo("", "", "", set.getString("Course.courseNo"), set.getString("courseName"), "",
						set.getInt("credit"), set.getInt("weekHour"), set.getInt("term")), set.getString("teacher"), 
						set.getString("time"), optional,"","","", 0);
				
				boolean access = df.intToBool(set.getInt("access"));
			    list.add(new ScorePo(set.getDouble("score"), set.getDouble("gpa"), set.getInt("comCre"), set.getInt("optCre"), 
						set.getInt("credits"), access,course,id));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			df.closeConnection();
			return list;
		}
	}
	
	@SuppressWarnings("finally")
	@Override
	public ArrayList<ScorePo> findCourseStudent(String courseNo) throws RemoteException {
		//TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		ArrayList<ScorePo> student = new ArrayList<ScorePo>();
		
		try{
			ResultSet set = sta.executeQuery("select * from Election where courseNo='" + courseNo + "'");
			while(set.next()){
				student.add(new ScorePo(-1, -1, 0, 0, 0, false,new CoursePo(),set.getString("student")));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			df.closeConnection();
			return student;
		}
	}

	@Override
	public void insert(ScorePo po) throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		try {
			int access = df.boolToInt(po.getAccess());
			sta.executeUpdate("insert into Score values(" + po.getScore() + "," + po.getGpa() + "," + po.getComCre() + 
					"," + po.getOptCre() + "," + po.getCredits() + "," + access + ",'" + 
					po.getCourse().getPlan().getCourseNo() + "','" + po.getStudent() + "')");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			df.closeConnection();
		}
	}
	
	@Override
	public void update(ScorePo p) throws RemoteException {
		// TODO Auto-generated method stub
		df.init();
		con = df.getConnection();
		sta = df.getStatement();
		try{
			String courseNo = p.getCourse().getPlan().getCourseNo();
			String studentID = p.getStudent();
			
			int access = df.boolToInt(p.getAccess());
			sta.executeUpdate("update Score set score=" + p.getScore() + ",gpa=" + p.getGpa() + ",comCre=" + p.getComCre() + 
					",optCre=" + p.getOptCre() + ",credits=" + p.getCredits() + ",access=" + 
					access + " where student='" + studentID + "' and CourseNo='" + courseNo + "'");
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			df.closeConnection();
		}
	}
}
