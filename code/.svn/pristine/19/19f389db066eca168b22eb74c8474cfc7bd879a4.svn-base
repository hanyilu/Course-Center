package presentation.mainui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import vo.CourseVo;
import vo.PlanVo;
import vo.StrategyVo;
import vo.UserVo;
import businesslogic.adminbl.AdminController;
import businesslogic.coursebl.CourseController;
import businesslogic.electionbl.ElectionController;
import businesslogic.planbl.PlanController;
import businesslogic.strategybl.StrategyController;
import businesslogic.userbl.User;
import businesslogicservice.adminblservice.AdminBLService;
import businesslogicservice.courseblservice.CourseBLService;
import businesslogicservice.electionblservice.ElectionBLService;
import businesslogicservice.planblservice.PlanBLService;
import businesslogicservice.strategyblservice.StrategyBLService;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class InfoPane extends JPanel{
	
	private JTable table;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	private DefaultTableModel model;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox_1;

	/**
	 * Create the application.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public InfoPane() {
		setOpaque(false);
		setLayout(null);
		
		JLabel label = new JLabel("选择列表");
		label.setBounds(170, 38, 61, 16);
		add(label);
		
		comboBox = new JComboBox();
		initComboBox();
		comboBox.setBounds(262, 34, 147, 27);
		add(comboBox);
		
		JButton button = new JButton("");
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setIcon(new ImageIcon(InfoPane.class.getResource("/image/ok.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String info = (String)comboBox.getSelectedItem();
				String user = User.getUser().getUserRole();
				String department = (String)comboBox_1.getSelectedItem();
				setTable(info, user, department);
			}
		});
		button.setBounds(462, 39, 50, 50);
		add(button);
		
		JLabel label_1 = new JLabel("\u9662\u7CFB\u9009\u62E9");
		label_1.setBounds(170, 66, 61, 16);
		add(label_1);
		
		comboBox_1 = new JComboBox();
		
		/*
		 * 初始化院系选项
		 */
		AdminBLService ad = new AdminController();
		ArrayList<String> de = ad.getDepartment();
		String[] s = new String[de.size() + 1];
		s[0] = "（无）";
		for (int i = 0; i < de.size(); i++) {
			s[i+1] = de.get(i);
		}
		comboBox_1.setModel(new DefaultComboBoxModel(s));
		comboBox_1.setBounds(262, 62, 147, 27);
		add(comboBox_1);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(new TitledBorder(null, "信息列表", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(67, 108, 612, 344);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initComboBox() {
		//根据当前用户角色初始化可选择列表选项
		String user = User.getUser().getUserRole();
		if (user.equals("JW"))
			comboBox.setModel(new DefaultComboBoxModel(
					new String[] {"请选择", "全校课程", "整体框架策略","院系教学计划","学生信息","教师信息"}));
		if (user.equals("YXJW"))
			comboBox.setModel(new DefaultComboBoxModel(
					new String[] {"请选择", "全校课程", "整体框架策略","院系教学计划","学生信息"}));
		if (user.equals("Teacher"))
			comboBox.setModel(new DefaultComboBoxModel(
					new String[] {"请选择", "全校课程", "课程的学生信息"}));
		if (user.equals("Student"))
			comboBox.setModel(new DefaultComboBoxModel(
					new String[] {"请选择", "全校课程", "课程列表"}));
	}
	
	private void setTable(String info, String role, String department) {
		if (info.equals("请选择")) {
			Methods.showTip("请选择列表");
		}
		if (info.equals("全校课程")) {
			initCourseTable();
		}
		if (info.equals("整体框架策略")) {
			initStrategyTable();
		}
		if (info.equals("院系教学计划")) {
			initPlanTable(role, department);
		}
		if (info.equals("学生信息")) {
			initStudentTable(info, department);
		}
		if (info.equals("教师信息")) {
			initTeacherTable(info, department);
		}
		if (info.equals("课程的学生信息")) {
			initCourseStudentTable();
		}
		if (info.equals("课程列表")) {
			initStudentCourseTable();
		}
	}

	private void initCourseTable() {
		// TODO Auto-generated method stub
		CourseBLService c = new CourseController();
		ArrayList<CourseVo> course = c.getCourses();
		if (course != null && course.size() != 0) {
			String[] column = new String[] {"课程号", "课程名", "课程学分", "周学时", "任课老师", "上课时间", "选修", "教科书", "参考书目"};
			model = new DefaultTableModel(null, column);
			
			// 填充数据
			for (int i = 0; i < course.size(); i++) {
				Object[] arr = new Object[9];
			    arr[0] = course.get(i).getPlan().getCourseNo();
			    arr[1] = course.get(i).getPlan().getCourseName();
			    arr[2] = course.get(i).getPlan().getCredit();
			    arr[3] = course.get(i).getPlan().getWeekHour();
			    arr[4] = course.get(i).getTeacher();
			    arr[5] = course.get(i).getTime();
			    arr[6] = course.get(i).isOptional() ? "选修" : "必修";
			    arr[7] = course.get(i).getTextbook();
			    arr[8] = course.get(i).getReferbook();
			    
			    // 添加数据到表格
			    model.addRow(arr);
			}
			
			table.setModel(model);
			table.getColumnModel().getColumn(1).setPreferredWidth(120);
			table.getColumnModel().getColumn(5).setPreferredWidth(200);
			table.getColumnModel().getColumn(7).setPreferredWidth(150);
			table.getColumnModel().getColumn(8).setPreferredWidth(250);
		}
		else {
			Methods.showTip("当前无任何课程");
		}
	}
	
	private void initStrategyTable() {
		// TODO Auto-generated method stub
		StrategyBLService s = new StrategyController();
		ArrayList<StrategyVo> strategy = s.getStrategy();
		if (strategy != null && strategy.size() != 0) {
			String[] column = new String[] {"课程模块", "课程分类", "课程性质", "开设学期", "学分范围"};
			model = new DefaultTableModel(null, column);
			
			// 填充数据
			for (int i = 0; i < strategy.size(); i++) {
				Object[] arr = new Object[6];
			    arr[0] = strategy.get(i).getModule();
			    arr[1] = strategy.get(i).getCategory();
			    arr[2] = strategy.get(i).getProperty();
			    int minT = strategy.get(i).getMinTerm();
			    int maxT = strategy.get(i).getMaxTerm();
			    String term = String.valueOf(minT);
			    if (minT!=maxT)
			    	term = term + "~" + String.valueOf(maxT);
			    arr[3] = term;
			    int minC = strategy.get(i).getMinCredit();
			    int maxC = strategy.get(i).getMaxCredit();
			    String credit = String.valueOf(minC);
			    if (minC!=maxC)
			    	credit = credit + "~" + String.valueOf(maxC);
			    arr[4] = credit;
			    
			    // 添加数据到表格
			    model.addRow(arr);
			}
			
			table.setModel(model);
		}
		else {
			Methods.showTip("整体框架策略还未发布");
		}
	}
	
	private void initPlanTable(String role, String department) {
		PlanBLService p = new PlanController();
		ArrayList<PlanVo> plan = new ArrayList<PlanVo>();
		if (role.equals("YXJW")) {
			plan = p.getPlan(User.getUser().getUserDepartment());
		}
		else {
			if (department.equals("（无）")) {
				plan = p.getAllPlan();
			}
			else {
				plan = p.getPlan(department);
			}
		}
		if (plan != null && plan.size() != 0) {
			String[] column = new String[] {"院系", "课程号", "课程名", "课程模块", "课程分类", "课程性质", "课程学分", "开设学期", "周学时"};
			model = new DefaultTableModel(null, column);
			
			// 填充数据
			for (int i = 0; i < plan.size(); i++) {
				Object[] arr = new Object[9];
				arr[0] = plan.get(i).getDepartment();
			    arr[1] = plan.get(i).getCourseNo();
			    arr[2] = plan.get(i).getCourseName();
			    arr[3] = plan.get(i).getModule();
			    arr[4] = plan.get(i).getCategory();
			    arr[5] = plan.get(i).getProperty();
			    arr[6] = plan.get(i).getCredit();
			    arr[7] = plan.get(i).getTerm();
			    arr[8] = plan.get(i).getWeekHour();
			    
			    // 添加数据到表格
			    model.addRow(arr);
			}
			
			table.setModel(model);
			table.getColumnModel().getColumn(2).setPreferredWidth(120);
		}
		else {
			Methods.showTip("院系教学计划还未发布");
		}
	}
	
	private void initStudentTable(String info, String department) {
		AdminBLService ad = new AdminController();
		ArrayList<UserVo> user = new ArrayList<UserVo>();
		
		if (department.equals("（无）")) {
			user = ad.getUser("Student");
		}
		else {
			user = ad.getDeUser(department, "Student");
		}
		if (user != null && user.size() != 0) {
			String[] column = new String[] {"ID", "角色", "姓名", "性别", "出生年月", "身份证号", "院系", "入学年份", "联系方式"};
			model = new DefaultTableModel(null, column);
			
			// 填充数据
			for (int i = 0; i < user.size(); i++) {
				Object[] arr = new Object[9];
			    arr[0] = user.get(i).getID();
			    arr[1] = Methods.eToC(user.get(i).getRole());
			    arr[2] = user.get(i).getName();
			    arr[3] = user.get(i).getGender();
			    arr[4] = user.get(i).getBirthday();
			    arr[5] = user.get(i).getIc();
			    arr[6] = user.get(i).getDepartment();
			    arr[7] = user.get(i).getRegisterYear();
			    arr[8] = user.get(i).getContact();
			    
			    // 添加数据到表格
			    model.addRow(arr);
			}
			
			table.setModel(model);
			table.getColumnModel().getColumn(5).setPreferredWidth(120);
			table.getColumnModel().getColumn(6).setPreferredWidth(100);
		}
		else {
			Methods.showTip("当前无学生");
		}
	}
	
	private void initTeacherTable(String info, String department) {
		AdminBLService ad = new AdminController();
		ArrayList<UserVo> user = new ArrayList<UserVo>();
		
		if (department.equals("（无）")) {
			user = ad.getUser("Teacher");
		}
		else {
			user = ad.getDeUser(department, "Teacher");
		}
		if (user != null && user.size() != 0) {
			String[] column = new String[] {"ID", "角色", "姓名", "性别", "出生年月", "身份证号", "院系", "联系方式"};
			model = new DefaultTableModel(null, column);
			
			// 填充数据
			for (int i = 0; i < user.size(); i++) {
				Object[] arr = new Object[8];
			    arr[0] = user.get(i).getID();
			    arr[1] = Methods.eToC(user.get(i).getRole());
			    arr[2] = user.get(i).getName();
			    arr[3] = user.get(i).getGender();
			    arr[4] = user.get(i).getBirthday();
			    arr[5] = user.get(i).getIc();
			    arr[6] = user.get(i).getDepartment();
			    arr[7] = user.get(i).getContact();
			    
			    // 添加数据到表格
			    model.addRow(arr);
			}
			
			table.setModel(model);
			table.getColumnModel().getColumn(5).setPreferredWidth(120);
			table.getColumnModel().getColumn(6).setPreferredWidth(100);
		}
		else {
			Methods.showTip("当前无教师");
		}
	}
	
	private void initCourseStudentTable() {
		CourseBLService c = new CourseController();
		ArrayList<CourseVo> course = c.showTCourse(User.getUser().getUserID());
		
		ElectionBLService e = new ElectionController();
		if (course != null && course.size() != 0) {
			String[] column = new String[] {"课程号", "课程名", "课程学分", "学生ID", "学生姓名", "学生院系", "学生入学年份", "学生联系方式"};
			model = new DefaultTableModel(null, column);
			
			for (int i = 0; i < course.size(); i++) {
				String courseNo = course.get(i).getPlan().getCourseNo();
				ArrayList<UserVo> student = e.getCouElection(courseNo);
				
				// 填充数据
				for (int j = 0; j < student.size(); j++) {
					Object[] arr = new Object[8];
				    arr[0] = courseNo;
				    arr[1] = course.get(i).getPlan().getCourseName();
				    arr[2] = course.get(i).getPlan().getCredit();
				    arr[3] = student.get(j).getID();
				    arr[4] = student.get(j).getName();
				    arr[5] = student.get(j).getDepartment();
				    arr[6] = student.get(j).getRegisterYear();
				    arr[7] = student.get(j).getContact();
				    
				    // 添加数据到表格
				    model.addRow(arr);
				}
			}
			
			table.setModel(model);
			table.getColumnModel().getColumn(1).setPreferredWidth(120);
			table.getColumnModel().getColumn(5).setPreferredWidth(100);
			table.getColumnModel().getColumn(6).setPreferredWidth(90);
			table.getColumnModel().getColumn(7).setPreferredWidth(90);
			
		}
		else {
			Methods.showTip("该课程还没有学生");
		}
	}
	
	private void initStudentCourseTable() {
		ElectionBLService e = new ElectionController();
		ArrayList<CourseVo> course = e.getMyCourse(User.getUser().getUserID());
		if (course != null && course.size() != 0) {
			String[] column = new String[] {"课程号", "课程名", "课程学分", "周学时", "任课老师", "上课时间"};
			model = new DefaultTableModel(null, column);
			
			// 填充数据
			for (int i = 0; i < course.size(); i++) {
				Object[] arr = new Object[6];
			    arr[0] = course.get(i).getPlan().getCourseNo();
			    arr[1] = course.get(i).getPlan().getCourseName();
			    arr[2] = course.get(i).getPlan().getCredit();
			    arr[3] = course.get(i).getPlan().getWeekHour();
			    arr[4] = course.get(i).getTeacher();
			    arr[5] = course.get(i).getTime();
			    
			    // 添加数据到表格
			    model.addRow(arr);
			}
			
			table.setModel(model);
			table.getColumnModel().getColumn(1).setPreferredWidth(120);
			table.getColumnModel().getColumn(5).setPreferredWidth(200);
		}
		else {
			Methods.showTip("你还没有课程");
		}
	}
}
