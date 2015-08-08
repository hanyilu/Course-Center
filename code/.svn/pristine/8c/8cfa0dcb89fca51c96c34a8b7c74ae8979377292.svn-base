package businesslogic.scorebl;

import vo.CourseVo;
import vo.ScoreVo;
import po.CoursePo;
import po.ScorePo;
import rmi.Client;
import dataservice.datafactoryservice.DataFactoryService;

import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogic.coursebl.Course;

public class Score {
	
	private DataFactoryService service;
	private Client client;
	
	public Score(){
		client = new Client();
		service = client.getFactory();
	}
	
	//查看课程
	public  ArrayList<CourseVo> getMyCourse(String id) {
		Course c = new Course();
		return c.showTCourse(id);	
	}
	
	public ArrayList<ScoreVo> getStudents(String courseNo) {		
		try{
			ArrayList<ScorePo> stu = service.getScoreDatabase().findCourseScore(courseNo);
			ArrayList<ScoreVo> studentList = new ArrayList<ScoreVo>();
			
			if(stu.isEmpty()){		//老师未登记过成绩
				stu = service.getScoreDatabase().findCourseStudent(courseNo);
			}
			
			for(int i = 0;i < stu.size();i++){
				studentList.add(new ScoreVo(stu.get(i)));
			}
			return studentList;	
		}catch(RemoteException e){
			e.printStackTrace();
			return null;
		}		
	}
	
	public ArrayList<ScoreVo> showMyScore(String id) {
		try{
			ArrayList<ScorePo> scoList = service.getScoreDatabase().findMyScore(id);
			ArrayList<ScoreVo> scoreList = compute(scoList);
			
			return scoreList;
		}catch(RemoteException e){
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<ScoreVo> showMyScore(String id, int term) {
		// TODO Auto-generated method stub
		try {
			ArrayList<ScorePo> scoList = service.getScoreDatabase().findMyScore(id, term);
			ArrayList<ScoreVo> scoreList = compute(scoList);
			
			return scoreList;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Integer> setScore(ArrayList<ScoreVo> scoList) {
		String courseNo = scoList.get(0).getCourse().getPlan().getCourseNo();
		try{
			ArrayList<ScorePo> stu = service.getScoreDatabase().findCourseScore(courseNo);
			if(stu.isEmpty()){
				
				//添加
				return insertScore(scoList);
			}
			else{
				
				//修改
				return updateScore(scoList);
			}
		}catch(RemoteException e){
			e.printStackTrace();
			return null;
		}
	}
	
	private ArrayList<Integer> checkScore(ArrayList<ScoreVo> scoList){
		ArrayList<Integer> index = new ArrayList<Integer>();
		for(int i = 0;i < scoList.size();i++){
			ScoreVo s = scoList.get(i);
		    double score = s.getScore();
		    if(score < 0 || score > 100){
		    	index.add(i);
		    }
		}
		return index;
	}
	
	private ArrayList<Integer> insertScore(ArrayList<ScoreVo> scoList) {
		// TODO Auto-generated method stub
		ArrayList<Integer> check = checkScore(scoList);
		
		try {
			if (check.size() == 0) {
				for (int i = 0; i < scoList.size(); i++) {
					service.getScoreDatabase().insert(new ScorePo(scoList.get(i)));
				}
				return null;
			} else {
				for (int i = 0; i < scoList.size(); i++) {
					boolean t = true;
					for (int j = 0; j < check.size(); j++) {
						if (i == check.get(j)) {
							t = false;
							break;
						}
					}
					if (t) {
						service.getScoreDatabase().insert(new ScorePo(scoList.get(i)));
					} else {
						ScoreVo scores = new ScoreVo(-1, -1, 0, 0, 0, false, scoList.get(i).getCourse(), scoList.get(i).getStudent());
						service.getScoreDatabase().insert(new ScorePo(scores));
					}
				}
				return check;
			}
		} catch (RemoteException e) {
			// TODO: handle exception
			return null;
		}
	}
	
	private ArrayList<Integer> updateScore(ArrayList<ScoreVo> scoList) {
		try {
			ArrayList<Integer> check = checkScore(scoList);
			if (check.size() == 0) {
				for (int i = 0; i < scoList.size(); i++) {
					service.getScoreDatabase().update(new ScorePo(scoList.get(i)));
				}
				return null;
			} else {
				for (int i = 0; i < scoList.size(); i++) {
					boolean t = true;
					for (int j = 0; j < check.size(); j++) {
						if (i == check.get(j)) {
							t = false;
						}
					}
					if (t) {
						service.getScoreDatabase().update(new ScorePo(scoList.get(i)));
					} else {
						ScoreVo scores = new ScoreVo(-1, -1, 0, 0, 0, false, scoList.get(i).getCourse(), scoList.get(i).getStudent());
						service.getScoreDatabase().update(new ScorePo(scores));
					}
				}
				return check;
			}
		} catch (RemoteException e) {
			// TODO: handle exception
			return null;
		}
	}
	
	/**
	 * 计算gpa,各项学分及总学分
	 * @param scoList 成绩信息
	 */
	private ArrayList<ScoreVo> compute(ArrayList<ScorePo> scoList) {
		// TODO Auto-generated method stub
		double gpa = 0;
		int comCre = 0;
		int optCre = 0;
		int credit = 0;
		int credits = 0;
		double score = 0;
		
		ArrayList<ScoreVo> scoreList = new ArrayList<ScoreVo>();
		for(int i = 0;i < scoList.size();i++){
			CoursePo course = scoList.get(i).getCourse();
			credit  = course.getPlan().getCredit();
			score = scoList.get(i).getScore();
			
			if (score!=-1) {
				gpa = gpa + (score / 20) * credit;
				credits = credits + credit;
				scoreList.add(new ScoreVo(scoList.get(i)));
			}
			
			if (course.isOptional()) {
				optCre += credit;
			}
			else {
				comCre += credit;
			}
		}
		gpa = gpa/credits;
		
		for(int i = 0; i < scoreList.size(); i++){
			scoreList.get(i).setGpa(gpa);
			scoreList.get(i).setCredits(credits);
			scoreList.get(i).setComCre(comCre);
			scoreList.get(i).setOptCre(optCre);
		}
		
		/*
		 * 准入准出access
		 */
	
		return scoreList;
	}
}
