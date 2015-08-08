package businesslogic.electionbl;

import java.rmi.RemoteException;
import java.util.*;

import dataservice.datafactoryservice.DataFactoryService;
import po.CoursePo;
import po.ElectionPo;
import po.UserPo;
import rmi.Client;
import vo.CourseVo;
import vo.ElectionVo;
import vo.ScoreVo;
import vo.UserVo;
import businesslogic.controlbl.Controller;
import businesslogic.coursebl.CourseController;
import businesslogic.coursebl.CourseTime;
import businesslogic.scorebl.ScoreController;
import businesslogic.userbl.User;
import businesslogicservice.controlblservice.ControllerBLService;
import businesslogicservice.courseblservice.CourseBLService;
import businesslogicservice.scoreblservice.ScoreBLService;

public class Election {
	
	private DataFactoryService service;
	private Client client;

	public Election() {
		client = new Client();
		service = client.getFactory();
	}
	
	public void decideEle() {
		try {
			ArrayList<ElectionPo> allEle = service.getElectionDatabase().findAllElection();
			
			/*
			 * 先按年级划分，高年级的学生优先
			 * 14个学分已修完的不参与划分，单独成一个list，最后随机分配
			 */
			ArrayList<ElectionPo> grade1 = new ArrayList<ElectionPo>();
			ArrayList<ElectionPo> grade2 = new ArrayList<ElectionPo>();
			ArrayList<ElectionPo> grade3 = new ArrayList<ElectionPo>();
			ArrayList<ElectionPo> grade4 = new ArrayList<ElectionPo>();
			ArrayList<ElectionPo> complete = new ArrayList<ElectionPo>();
			
			ElectionPo po;
			for (int i = 0; i < allEle.size(); i++) {
				po = allEle.get(i);
				
				/*
				 * 先检测14个学分是否已修满
				 */
				if (getCredit(po.getStudent()) >= 14) {
					complete.add(po);
					continue;
				}
				
				ControllerBLService cbls = new Controller();
				int grade = cbls.getGrade(po.getRegisterYear());
				switch (grade) {
				case 1: grade1.add(po); break;
				case 2: grade2.add(po); break;
				case 3: grade3.add(po); break;
				case 4: grade4.add(po); break;
				}
			}
			
			/*
			 * 同年级的学生再按已获得的选修课总学分划分，学分低的优先
			 * 用HashMap来存储此次划分的结果，Key为选修课总学分数，Value为（选修课总学分=Key）的所有ElectionPo对象
			 */
			HashMap<Integer, ArrayList<ElectionPo>> hash1 = divideByCredit(grade1);
			HashMap<Integer, ArrayList<ElectionPo>> hash2 = divideByCredit(grade2);
			HashMap<Integer, ArrayList<ElectionPo>> hash3 = divideByCredit(grade3);
			HashMap<Integer, ArrayList<ElectionPo>> hash4 = divideByCredit(grade4);
			
			/*
			 * 对同年级同学分的学生进行随机分配
			 */
			randomDivide(hash4);
			randomDivide(hash3);
			randomDivide(hash2);
			randomDivide(hash1);
			
			/*
			 * 对14学分已修满的学生进行随机分配
			 */
			randomDivide(complete);
			
			/*
			 * 将暂时存储选课信息的表清空
			 */
			service.getElectionDatabase().clearTemEle();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//添加选课
	public String addCourse(ArrayList<ElectionVo> election) {
		// TODO Auto-generated method stub
		String check = checkElection(election);
		if(check.equals("添加成功")) {
			try {
				
				/*
				 * 清空之前的选课信息
				 */
				ArrayList<ElectionPo> ele = service.getElectionDatabase().findTempEle(User.getUser().getUserID());
				for(int i = 0; i < ele.size(); i++) {
					service.getElectionDatabase().delete(ele.get(i));
				}
				
				/*
				 * 插入新的选课信息
				 */
				ElectionPo po;
				for(int i = 0; i < election.size(); i++) {
					po = new ElectionPo(election.get(i));
					service.getElectionDatabase().insertTemp(po);
				}
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return check;
	}
	
	public String add(ArrayList<ElectionVo> election) {
		String check = checkElection(election);
		if (check.equals("添加成功")) {
			ElectionPo po;
			for (int i = 0; i < election.size(); i++) {
				po = new ElectionPo(election.get(i));
				try {
					service.getElectionDatabase().insert(po);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "啊哦~有错误~";
				}
			}
		}
		
		return check;
	}

	//退选
	@SuppressWarnings("unused")
	public String deleteCourse(ArrayList<ElectionVo> ele) {
		// TODO Auto-generated method stub
		for(int i = 0; i < ele.size(); i++) {
			ElectionPo po = new ElectionPo(ele.get(i));
			try {
				service.getElectionDatabase().delete(po);
				return "退选成功";
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "啊哦~有错误~";
			}
		}
		return "请选择要退选的课程";
	}

	//得到可选信息
	public ArrayList<CourseVo> getAvilCourse() {
		// TODO Auto-generated method stub
		try {
			ArrayList<CoursePo> po = service.getElectionDatabase().findAll();
			ArrayList<CourseVo> result = new ArrayList<CourseVo>();
			for(int i = 0; i < po.size(); i++) {
				result.add(new CourseVo(po.get(i)));
			}
			return result;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<CourseVo> getComCourse() {
		// TODO Auto-generated method stub
		try {
			ArrayList<CoursePo> course = service.getElectionDatabase().findAll();
			ArrayList<CourseVo> comCourse = new ArrayList<CourseVo>();
			
			/*
			 * 判断所有可选课程的已选人数，若小于最大人数则可以进行补选
			 */
			CourseBLService cbls = new CourseController();
			CoursePo po;
			for (int i = 0; i < course.size(); i++) {
				po = course.get(i);
				String cNo = po.getPlan().getCourseNo();
				if (cbls.getCurStuNum(cNo) < po.getStudentNum()) {
					comCourse.add(new CourseVo(po));
				}
			}
			
			return comCourse;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//得到课程的选课信息
	public ArrayList<UserVo> getCouElection(String courseNo) {
		// TODO Auto-generated method stub
		try {
			ArrayList<UserPo> c = service.getElectionDatabase().find(courseNo);
			ArrayList<UserVo> result = new ArrayList<UserVo>();
			for(int i = 0; i < c.size(); i++) {
				result.add(new UserVo(c.get(i)));
			}
			return result;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	//根据学生id查找已选课程
	public ArrayList<ElectionVo> getElection(String id) {
		// TODO Auto-generated method stub
		try {
			ArrayList<ElectionPo> my = service.getElectionDatabase().findMine(id,1);
			ArrayList<ElectionVo> result = new ArrayList<ElectionVo>();
			for(int i = 0; i < my.size(); i++) {
				result.add(new ElectionVo(my.get(i)));
			}
			return result;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<ElectionVo> getTemEle(String id) {
		// TODO Auto-generated method stub
		try {
			ArrayList<ElectionPo> po = service.getElectionDatabase().findTempEle(id);
			ArrayList<ElectionVo> ele = new ArrayList<ElectionVo>();
			
			for (int i = 0; i < po.size(); i++) {
				ele.add(new ElectionVo(po.get(i)));
			}
			return ele;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<CourseVo> getMyCourse(String id) {
		// TODO Auto-generated method stub
		try {
			ArrayList<CoursePo> po = service.getElectionDatabase().findMyCourse(id);
			ArrayList<CourseVo> result = new ArrayList<CourseVo>();
			for(int i = 0; i < po.size(); i++) {
				result.add(new CourseVo(po.get(i)));
			}
			return result;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	private String checkElection(ArrayList<ElectionVo> election) {
		String result = null;
		
		try {
			//得到该学生的已有课程信息
			ArrayList<CoursePo> course = service.getElectionDatabase().findMyCourse(election.get(0).getStudent());
			
			StringBuilder s = new StringBuilder("");
			CourseBLService cbls = new CourseController();
			
			//一门一门核对
			for(int i = 0; i < election.size(); i++) {
				CourseTime[] time = cbls.getTime(election.get(i).getCourse().getTime());
				
				/*
				 * 核对所选的课程
				 */
				for(int j = i+1; j < election.size(); j++){
					CourseTime[] time1 = cbls.getTime(election.get(j).getCourse().getTime());
				    if (cbls.checkTime(time,time1)){//冲突
				    	s.append(election.get(i).getCourse().getPlan().getCourseName());
				    	s.append(" 与 ");
				    	s.append(election.get(j).getCourse().getPlan().getCourseName());
				    	s.append(" 上课时间冲突\n");
				    }
				}
				
				/*
				 * 核对已有课程的时间
				 */
				for (int j = 0; j < course.size(); j++) {
					CourseTime[] time1 = cbls.getTime(course.get(j).getTime());
					if (cbls.checkTime(time, time1)) {
						s.append(election.get(i).getCourse().getPlan().getCourseName());
						s.append(" 与 ");
						s.append(course.get(j).getPlan().getCourseName());
						s.append(" 上课时间冲突\n");
					}
				}
								
				if(s.toString().equals("")||s.toString()==null)
					result = "添加成功";
				else
					result = s.toString();
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "啊哦~有错误~";
		}
		
		return result;
	}
	
	/**
	 * 将一个ElectionPo的List按选修课学分数进行划分
	 * @param po 装载ElectionPo的容器
	 * @return 划分结果
	 */
	private HashMap<Integer, ArrayList<ElectionPo>> divideByCredit(ArrayList<ElectionPo> po) {
		HashMap<Integer, ArrayList<ElectionPo>> map = new HashMap<Integer, ArrayList<ElectionPo>>();
		
		ElectionPo ele;
		String stu;
		int credit;
		for (int i = 0; i < po.size(); i++) {
			ele = po.get(i);
			stu = ele.getStudent();
			
			//得到学分信息
			credit = getCredit(stu);
			
			/*
			 * 加入到HashMap中
			 */
			if (map.containsKey(credit)) {
				map.get(credit).add(ele);
			}
			else {
				ArrayList<ElectionPo> value = new ArrayList<ElectionPo>();
				value.add(ele);
				map.put(credit, value);
			}
		}
		
		return map;
	}

	/**
	 * 根据学生的id得到其已修得的总选修课学分数
	 * @param stu 学生id
	 * @return 选修课总学分数
	 */
	private int getCredit(String stu) {
		// TODO Auto-generated method stub
		ScoreBLService sbls = new ScoreController();
		ArrayList<ScoreVo> vo = sbls.showMyScore(stu);
		int credit = 0;
		if (vo.size() != 0) {
			credit = vo.get(0).getOptCre();
		}
		
		return credit;
	}
	
	/**
	 * 随机分配方法，原则：按80%的概率分配
	 * @param map
	 */
	private void randomDivide(HashMap<Integer, ArrayList<ElectionPo>> map) {
		// TODO Auto-generated method stub
		Integer[] credit = map.keySet().toArray(new Integer[map.size()]);
		
		//将学分按从低到高排序
		sort(credit);
		
		/*
		 * 按排序后的顺序为学生分配选课
		 */
		ArrayList<ElectionPo> list;
		for (int i = 0; i < credit.length; i++) {
			list = map.get(credit[i]);
			randomDivide(list);
		}
	}

	/**
	 * 随机分配方法，原则：按80%的概率分配
	 * @param list
	 */
	private void randomDivide(ArrayList<ElectionPo> list) {
		// TODO Auto-generated method stub
		CourseBLService cbls = new CourseController();
		for (int j = 0; j < list.size(); j++) {
			if (Math.random() < 0.8) {
				
				/*
				 * 检测该课程人数是否已满,没满则添加选课
				 */
				ElectionPo po = list.get(j);
				String courseNo = po.getCourse().getPlan().getCourseNo();
				if (cbls.getCurStuNum(courseNo) < cbls.getMaxStuNum(courseNo))
					add(po);
			}
		}
	}
	
	private void sort(Integer[] credit) {
		// TODO Auto-generated method stub
		int min;
		for (int i = 0; i < credit.length - 1; i++) {
			min = i;
			for (int j = i + 1; j < credit.length; j++) {
				if (credit[j] < credit[min])
					min = j;
			}
			
			if (min != i) {
				int temp = credit[i];
				credit[i] = credit[min];
				credit[min] = temp;
			}
		}
	}
	
	/**
	 * 将分配好的选课添加进最终的选课列表中
	 * @param po 选课信息
	 */
	private void add(ElectionPo po) {
		try {
			service.getElectionDatabase().insert(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
