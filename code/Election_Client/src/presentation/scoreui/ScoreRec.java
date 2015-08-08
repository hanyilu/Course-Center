package presentation.scoreui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import businesslogic.adminbl.AdminController;
import businesslogic.coursebl.CourseController;
import businesslogic.scorebl.ScoreController;
import businesslogic.userbl.User;
import businesslogicservice.adminblservice.AdminBLService;
import businesslogicservice.courseblservice.CourseBLService;
import businesslogicservice.scoreblservice.ScoreBLService;
import presentation.mainui.Methods;
import vo.CourseVo;
import vo.PlanVo;
import vo.ScoreVo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class ScoreRec extends JPanel{
	
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	private JTable table_3;
	Color original;
	
	private String[] co = {"课程号", "课程名", "课程学分", "周学时", "上课时间"};
	private DefaultTableModel tableCourse = new DefaultTableModel(null,co);

	/**
	 * Create the application.
	 */
	public ScoreRec() {
		setOpaque(false);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		setLayout(null);
		
		JLabel label_3 = new JLabel("选择课程");
		label_3.setBounds(280, 38, 97, 16);
		add(label_3);
		
		ScoreBLService s = new ScoreController();
		ArrayList<CourseVo> course = s.getMyCourse(User.getUser().getUserID());
		String[] courseName = new String[course.size() +1];
		courseName[0] = "【请选择】";
		for(int i = 0;i < course.size();i++){
			courseName[i+1] = course.get(i).getPlan().getCourseNo() + " " + course.get(i).getPlan().getCourseName();
		}
		
		table_3 = new JTable();
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(courseName));
		comboBox.setBounds(389, 34, 152, 27);
		comboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String choice = (String) comboBox.getSelectedItem();
				if (choice.equals("【请选择】")) {
					Methods.showTip("请选择课程");
					return;
				}
			    String[] s = choice.split(" ");
			    String courseNo = s[0];
			    ScoreBLService sc = new ScoreController();
			    ArrayList<ScoreVo> student = sc.getStudents(courseNo);
			    
			    AdminBLService ad = new AdminController();
			    
			    String[] columnNames = {"学生号", "学生姓名", "成绩"};
				
			    DefaultTableModel model = new DefaultTableModel(null, columnNames);
			    for(int i = 0;i < student.size();i++){
			    	Object[] data = new Object[3];
			    	data[0] = student.get(i).getStudent();
			    	data[1] = ad.getUserById((String)data[0]).getName();
			    	
			    	double score = student.get(i).getScore();
			    	if(score == -1){
				    	data[2] = "";
			    	}
			    	else{
			    		data[2] = score;
			    	}
			    	model.addRow(data);
			    }
			    
			    table_3.setModel(model);			    
			}
		});
		add(comboBox);
		
		JPanel panel_7 = new JPanel();
		panel_7.setOpaque(false);
		panel_7.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "学生列表", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_7.setBounds(160, 91, 447, 346);
		add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setViewportView(table_3);
		setCourse();
		panel_7.add(scrollPane_3, BorderLayout.CENTER);
		
		JButton button_1 = new JButton("");
		button_1.setBorderPainted(false);
		button_1.setContentAreaFilled(false);
		button_1.setIcon(new ImageIcon(ScoreRec.class.getResource("/image/publish.png")));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				ArrayList<ScoreVo> scores = new ArrayList<ScoreVo>();
				int row = table_3.getRowCount();
				String choice = (String) comboBox.getSelectedItem();
				if (choice.equals("【请选择】")) {
					Methods.showTip("请选择课程");
					return;
				}
			    String[] s = choice.split(" ");
			    String courseNo = s[0];
				
			    boolean kong = false;  //是否有空白项
			    
				for(int i = 0; i < row; i++){
					Object sco = table_3.getValueAt(i,2);
					double score;
					if (sco instanceof String) {
						if (((String)sco).length() == 0) {
							kong = true;
							score = -1;
						}
						else {
							score = Double.valueOf((String)sco);
						}
					}
					else {
						score = (Double)table_3.getValueAt(i, 2);
					}
					String studentID = (String)table_3.getValueAt(i,0);
					scores.add(new ScoreVo(score,0,0,0,0,false,new CourseVo(new PlanVo("", "", "", courseNo, "", "", 0, 0,0),
							User.getUser().getUserID(), "", false, "", "", "", 0),studentID));
				}
				
				ScoreBLService sc2 = new ScoreController();
				ArrayList<Integer> wrong = sc2.setScore(scores);
				
				/*
				 * 不在登记成绩时间内
				 */
				if (wrong != null && wrong.size() != 0 && wrong.get(0) == -1) {
					Methods.showTip("当前阶段不可登记成绩，如有疑问请咨询管理员");
					return;
				}
				
				if(wrong == null || wrong.size() == 0){
					if (kong) {
						Methods.showTip("合理成绩已录入，但还有同学成绩尚未登记");
					}
					else {
						Methods.showTip("成绩登记成功！");
					}
				}
				else{
					original = table_3.getBackground();
						 
					for(int i = 0;i < wrong.size();i++){
						int index = wrong.get(i);
						table_3.setRowSelectionInterval(index,index);
						table_3.setSelectionBackground(Color.CYAN);
					}
						
					if (kong) {
						Methods.showTip("合理成绩已录入，但存在不合理成绩项且有同学尚未登记成绩");
					}
					else {
						Methods.showTip("成绩信息存在不合理");
					}
						
					table_3.addMouseListener(new MouseAdapter(){
						public void mouseClicked(MouseEvent e){
							if(table_3.getValueAt(table_3.getSelectedRow(),0)!=null){
								Color now = table_3.getSelectionBackground();
								if(now == Color.CYAN){
									table_3.setSelectionBackground(original);
								}
							}
						}
					});	
				}
			}
		});
		button_1.setBounds(366, 443, 50, 50);
		add(button_1);
	}
	
	public void setCourse(){
		int rowcount=tableCourse.getRowCount();
		while(rowcount!=0){
			tableCourse.removeRow(--rowcount);
		}
		CourseBLService c = new CourseController();
		
		ArrayList<CourseVo> list = c.showTCourse(User.getUser().getUserID());
		if(list!=null)
			for(int i = 0;i<list.size();i++){
				CourseVo v = list.get(i);
				tableCourse.addRow(new Object[]{v.getPlan().getCourseNo(),v.getPlan().getCourseName(),v.getPlan().getCredit(),v.getPlan().getWeekHour(),v.getTime()});
			}	
	}
}
