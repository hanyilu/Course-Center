package businesslogic.coursebl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CoursePo;
import po.ElectionPo;
import po.UserPo;
import rmi.Client;
import dataservice.datafactoryservice.DataFactoryService;
import vo.CourseVo;
import vo.UserVo;
import businesslogic.adminbl.AdminController;
import businesslogic.controlbl.Controller;
import businesslogicservice.adminblservice.AdminBLService;
import businesslogicservice.controlblservice.ControllerBLService;

public class Course {
	
	private DataFactoryService service;
	private Client client;

	public Course() {
		client = new Client();
		service = client.getFactory();
	}

	public String publish(CourseVo course) {
		// TODO Auto-generated method stub
		String result = check(course);
		if(result.equals("")||result==null){
			CoursePo po = new CoursePo(course);
			try {
				result = service.getCourseDatabase().insert(po);
				
				/*
				 * 必修则为相应年级的学生添加课程
				 */
				if(!po.isOptional())
					addElection(po);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return result;
	}
	
	public String modify(CourseVo course) {
		// TODO Auto-generated method stub
		String result = check(course);
		if(result.equals("")||result==null){
			CoursePo po = new CoursePo(course);
			try {
				service.getCourseDatabase().update(po);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "修改成功";
		}
		return result;	
	}
	
	public String addCourseInfo(CourseVo course) {
		// TODO Auto-generated method stub
		try {
			service.getCourseDatabase().update(new CoursePo(course));
			return "添加成功";
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "啊哦~有错误~";
		}
	}
	
	public ArrayList<CourseVo> getCourses() {
		// TODO Auto-generated method stub
		try {
			ArrayList<CoursePo> c = service.getCourseDatabase().findAll();
			ArrayList<CourseVo> a = new ArrayList<CourseVo>();
			for(int i= 0;i <c.size();i++)
				a.add(new CourseVo(c.get(i)));
			return a;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public CourseVo getCourse(String courseNo){
		CourseVo c = null;
		try {
			c = new CourseVo(service.getCourseDatabase().findCourse(courseNo));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return c;
	}

	//发布者
	public ArrayList<CourseVo> showJWCourse(String department) {
		// TODO Auto-generated method stub
		try {
			ArrayList<CoursePo> c = service.getCourseDatabase().findJW(department);
			
			ArrayList<CourseVo> a = new ArrayList<CourseVo>();
			for(int i= 0;i <c.size();i++)
				a.add(new CourseVo(c.get(i)));
			return a;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	//任课老师
	public ArrayList<CourseVo> showTCourse(String id) {
		try {
			ArrayList<CoursePo> c = service.getCourseDatabase().findT(id);
			ArrayList<CourseVo> a = new ArrayList<CourseVo>();
			for(int i= 0;i <c.size();i++)
				a.add(new CourseVo(c.get(i)));
			return a;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//检测两位任课老师的课程是否冲突
	public String isConflict(String tea1, String tea2) {
		try {
			ArrayList<CoursePo> oldC = service.getCourseDatabase().findT(tea1);
			ArrayList<CoursePo> newC = service.getCourseDatabase().findT(tea2);
			for (int i = 0; i < oldC.size(); i++) {
				CourseTime[] oTime = getTime(oldC.get(i).getTime());
				
				for (int j = 0; j < newC.size(); j++) {
					CourseTime[] nTime = getTime(newC.get(i).getTime());
					if (checkTime(oTime, nTime)) {
						return "这两名任课老师的课程时间存在冲突";
					}
				}
			}
			return "success";
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "啊哦~有错误~";
		}
	}
	
	public boolean checkTime(CourseTime[] time, CourseTime[] time1) {
		// TODO Auto-generated method stub
		for(int i = 0; i < time.length; i++){
			for(int j = 0; j < time1.length; j++){
				if(time[i].isColli(time1[j]))
					return true;
			}
		}
		return false;
	}

	public CourseTime[] getTime(String time) {
		// TODO Auto-generated method stub
		CourseTime[] c = null;
		if(time.contains(";")){
			String[] s = time.split(";");
			c = new CourseTime[s.length];
			for(int i = 0; i < s.length; i++){
				c[i] = new CourseTime(s[i]);
			}
		}
		else{
			return  new CourseTime[]{new CourseTime(time)};
		}
		
		return c;
	}
	
	public int getCurSelNum(String courseNo) {
		// TODO Auto-generated method stub
		try {
			ArrayList<UserPo> po = service.getElectionDatabase().findTempStu(courseNo);
			return po.size();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	
	public int getCurStuNum(String courseNo) {
		// TODO Auto-generated method stub
		try {
			ArrayList<UserPo> user = service.getElectionDatabase().find(courseNo);
			return user.size();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	
	public int getMaxStuNum(String courseNo) {
		// TODO Auto-generated method stub
		try {
			CoursePo po = service.getCourseDatabase().findCourse(courseNo);
			return po.getStudentNum();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	
	private void addElection(CoursePo po) {
		// TODO Auto-generated method stub
		try {
			ControllerBLService con = new Controller();
			
			//得到所有本院系的学生
			AdminBLService ad = new AdminController();
			ArrayList<UserVo> s = ad.getDeUser(po.getPlan().getDepartment(), "Student");
			int couTerm = po.getPlan().getTerm();
			for(int i = 0;i < s.size();i++){
				
				/*
				 * 判断年级，若是则为该学生添加必修课程
				 */
				UserVo u = s.get(i);
				int grade = con.getGrade(u.getRegisterYear());
				if (couTerm == grade * 2 || couTerm == grade * 2 - 1) {
					service.getElectionDatabase().insert(new ElectionPo(po, u.getID(), u.getRegisterYear()));
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 检测某老师被指定的课程时间与其已有课程时间是否冲突
	 * 检测该课程时间与本院系已有必修课程时间是否冲突
	 * @param course 要发布的课程信息
	 * @return 检测结果
	 */
	private String check(CourseVo course) {
		// TODO Auto-generated method stub
		CourseTime[] time1 = getTime(course.getTime());
		StringBuilder sb = new StringBuilder("");
		
		try {
			
			/*
			 * 检测老师的时间
			 */
			ArrayList<CoursePo> allCou = service.getCourseDatabase().findT(course.getTeacher());
			for (int i = 0; i < allCou.size(); i++) {
				CourseTime[] time2 = getTime(allCou.get(i).getTime());
				if ((!course.getPlan().getCourseNo().equals(allCou.get(i).getPlan().getCourseNo())) && 
						(checkTime(time1, time2))) {
					sb.append("该课程时间与此老师已有课程的时间冲突，请重新指定");
					return sb.toString();
				}
			}
			
			if (!course.isOptional()) {
				
				/*
				 * 检测院系的必修课时间
				 */
				String department = course.getPlan().getDepartment();
				int term = course.getPlan().getTerm();
				ArrayList<CoursePo> cou = service.getCourseDatabase().findDeCourse(department, term);
				for (int i = 0; i < cou.size(); i++) {
					CoursePo po = cou.get(i);
					CourseTime[] time2 = getTime(po.getTime());
					if ((!course.getPlan().getCourseNo().equals(po.getPlan().getCourseNo())) && (checkTime(time1, time2))) {
						sb.append("该课程时间与院系已有的必修课程的时间冲突，请重新安排");
						return sb.toString();
					}
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sb.append("啊哦~有错误~");
			return sb.toString();
		}
		
		return sb.toString();
	}
}
