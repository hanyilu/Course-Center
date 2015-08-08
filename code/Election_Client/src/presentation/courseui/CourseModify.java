package presentation.courseui;

import java.awt.BorderLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import businesslogic.adminbl.AdminController;
import businesslogic.coursebl.CourseController;
import businesslogic.coursebl.CourseTime;
import businesslogic.strategybl.StrategyController;
import businesslogic.userbl.User;
import businesslogicservice.adminblservice.AdminBLService;
import businesslogicservice.courseblservice.CourseBLService;
import businesslogicservice.strategyblservice.StrategyBLService;
import presentation.mainui.Methods;
import vo.CourseVo;
import vo.PlanVo;
import vo.StrategyVo;
import vo.UserVo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.SpinnerNumberModel;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class CourseModify extends JPanel{
	private JTable table_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private String[] courseColumn = new String[] {"课程号", "课程名", "课程学分", "周学时", "任课老师", "上课时间", "选修", "上课人数", "开设学期"};
	private DefaultTableModel tableCourse = new DefaultTableModel(null,courseColumn);
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox_1;
	private JRadioButton radioButton_2;
	@SuppressWarnings("rawtypes")
	private DefaultComboBoxModel c = new DefaultComboBoxModel();

	@SuppressWarnings("rawtypes")
	private JComboBox module;
	@SuppressWarnings("rawtypes")
	private JComboBox category;
	@SuppressWarnings("rawtypes")
	private JComboBox property;
	private JSpinner credit;
	private JButton button;
	private JRadioButton radioButton_3;
	private ButtonGroup b;
	private JTextField textField_6;
	
	int row, number;
	private JSpinner day;
	private JSpinner start;
	private JSpinner num;
	private JButton delete;

	/**
	 * Create the application.
	 */
	public CourseModify() {
		setOpaque(false);
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		setTableCourse();
		setComboBox();
		setLayout(null);
		
		JPanel panel_9 = new JPanel();
		panel_9.setOpaque(false);
		panel_9.setBorder(new TitledBorder(null, "已发布课程", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_9.setBounds(43, 16, 466, 485);
		add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_9.add(scrollPane_2);
		
		table_2 = new JTable();
		table_2.setModel(tableCourse);
		table_2.getColumnModel().getColumn(1).setPreferredWidth(120);
		table_2.getColumnModel().getColumn(5).setPreferredWidth(200);
		table_2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane_2.setViewportView(table_2);
		
		JPanel panel_10 = new JPanel();
		panel_10.setOpaque(false);
		panel_10.setLayout(null);
		panel_10.setBorder(new TitledBorder(null, "课程信息", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_10.setBounds(521, 100, 219, 339);
		add(panel_10);
		
		JLabel label_4 = new JLabel("课程号");
		label_4.setBounds(14, 33, 70, 16);
		panel_10.add(label_4);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(78, 27, 134, 28);
		panel_10.add(textField_3);
		
		JLabel label_5 = new JLabel("课程名");
		label_5.setBounds(14, 61, 70, 16);
		panel_10.add(label_5);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(78, 55, 134, 28);
		panel_10.add(textField_4);
		
		JLabel label_6 = new JLabel("任课老师");
		label_6.setBounds(14, 98, 70, 16);
		panel_10.add(label_6);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(c);
		comboBox_1.setBounds(78, 94, 134, 27);
		panel_10.add(comboBox_1);
		
		JLabel label_7 = new JLabel("上课时间");
		label_7.setBounds(14, 131, 70, 16);
		panel_10.add(label_7);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(78, 125, 134, 28);
		panel_10.add(textField_5);
		
		day = new JSpinner();
		day.setModel(new SpinnerListModel(new String[] {"\u5468\u4E00", "\u5468\u4E8C", "\u5468\u4E09", "\u5468\u56DB", "\u5468\u4E94", "\u5468\u516D", "\u5468\u65E5"}));
		day.setBounds(6, 165, 52, 28);
		panel_10.add(day);
		
		start = new JSpinner();
		start.setModel(new SpinnerNumberModel(1, 1, 9, 1));
		start.setBounds(90, 165, 47, 28);
		panel_10.add(start);
		
		num = new JSpinner();
		num.setModel(new SpinnerNumberModel(1, 1, 4, 1));
		num.setBounds(166, 165, 47, 28);
		panel_10.add(num);
		
		JButton button_2 = new JButton("");
		button_2.setIcon(new ImageIcon(CourseModify.class.getResource("/image/add.png")));
		button_2.setBorderPainted(false);
		button_2.setContentAreaFilled(false);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				String text  = textField_5.getText();
				
				int d = CourseTime.dayToInt(((String)day.getValue()).charAt(1));
				int s = (Integer) start.getValue();
				int n = (Integer) num.getValue();
				CourseTime  t = new CourseTime(d,s,n);

				if(text==null||text.equals("")){
					textField_5.setText(t.getTime());
					delete.setEnabled(true);
					return;
				}
				else if(text.contains(";")){
					String[] ss= text.split(";");
					for(int i = 0;i<ss.length;i++){
						if(t.isColli(new CourseTime(ss[i]))){
							return;
						}
					}
				}
				else if(t.isColli(new CourseTime(text))){
					return;
				}
				
				textField_5.setText(text+";"+t.getTime());
			}
		});
		button_2.setBounds(50, 205, 50, 50);
		panel_10.add(button_2);
		
		delete = new JButton("");
		delete.setIcon(new ImageIcon(CourseModify.class.getResource("/image/delete.png")));
		delete.setBorderPainted(false);
		delete.setContentAreaFilled(false);
		delete.setEnabled(false);
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = textField_5.getText();
				if(text.contains(";")){
					String[] ss= text.split(";");
					int l = ss[ss.length-1].length();
					textField_5.setText(text.substring(0, text.length()-l-1));
				}
				else{
					textField_5.setText("");
					delete.setEnabled(false);
				}
			}
		});
		delete.setBounds(126, 205, 50, 50);
		panel_10.add(delete);
		
		JLabel label_15 = new JLabel("首节");
		label_15.setBounds(63, 170, 61, 16);
		panel_10.add(label_15);
		
		JLabel label_16 = new JLabel("节数");
		label_16.setBounds(140, 170, 61, 16);
		panel_10.add(label_16);
		
		radioButton_2 = new JRadioButton("必修");
		radioButton_2.setBounds(42, 265, 82, 23);
		radioButton_2.setEnabled(false);
		b = new ButtonGroup();
		panel_10.add(radioButton_2);
		
		radioButton_3 = new JRadioButton("选修");
		radioButton_3.setBounds(118, 265, 83, 23);
		radioButton_3.setEnabled(false);
		panel_10.add(radioButton_3);
	
		b.add(radioButton_2);
		b.add(radioButton_3);
		
		JLabel lblNewLabel_1 = new JLabel("上课人数");
		lblNewLabel_1.setBounds(14, 297, 70, 15);
		panel_10.add(lblNewLabel_1);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setBounds(78, 290, 134, 28);
		panel_10.add(textField_6);
		textField_6.setColumns(10);
		
		button = new JButton("");
		button.setIcon(new ImageIcon(CourseModify.class.getResource("/image/publish.png")));
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setEnabled(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (((String)comboBox_1.getSelectedItem()).equals("【请指定】")) {
					Methods.showTip("请指定一名任课老师");
					return;
				}
				
				/*
				 * 若为选修则判断上课人数
				 */
				int num = 0;
				if (radioButton_3.isSelected()) {
					String studentNum = (String) textField_6.getText();
					try {
						num = Integer.valueOf(studentNum);
					} catch (NumberFormatException n) {
						Methods.showTip("上课人数不应为空且应为整数");
						return;
					}
					if (num <= 0) {
						Methods.showTip("上课人数应大于0");
						return;
					}
				}
				
				/*
				 * 判断课程节数
				 */
				String s = textField_5.getText();
				int cn = 0;
				String[] st = s.split(";");
				for (int i = 0; i < st.length; i++) {
					CourseTime ct = new CourseTime(st[i]);
					cn += ct.getNum();
				}
				if (cn != number) {
					Methods.showTip("上课节数不允许改变");
					return;
				}
				
				String department = User.getUser().getUserDepartment();
				int term = (Integer)table_2.getValueAt(row, 8);
				PlanVo p = new PlanVo("", "", "", textField_3.getText(), textField_4.getText(), department, 0, 0, term);
				CourseVo c = new CourseVo(p, ((String)comboBox_1.getSelectedItem()).split(" ")[0], s,
						!radioButton_2.isSelected(), "", "", "", num);
				CourseBLService ct = new CourseController();
				String result = ct.modify(c);
				
				//对话框
				if(result.equals("")||result == null)
					;
				else{
					Methods.showTip(result);
				}
				button.setEnabled(false);
				setTableCourse();
				
				//清空已提交的内容
				textField_3.setText("");
				textField_4.setText("");
				comboBox_1.setSelectedIndex(0);
				textField_5.setText("");
				b.clearSelection();
				textField_6.setText("");
			}
		});
		button.setBounds(571, 451, 50, 50);
		add(button);
		
		JButton button_1 = new JButton("");
		button_1.setIcon(new ImageIcon(CourseModify.class.getResource("/image/rewrite.png")));
		button_1.setBorderPainted(false);
		button_1.setContentAreaFilled(false);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_2.getSelectedRow();
				
				AdminBLService ad = new AdminController();
				String id = (String)table_2.getValueAt(row,4);
				UserVo u = ad.getUserById(id);
				comboBox_1.setSelectedItem(id + " " + u.getName());
				
				textField_6.setText(((Integer)table_2.getValueAt(row, 7)).toString());
			}
		});
		button_1.setBounds(644, 451, 50, 50);
		add(button_1);
		
		JButton btna_2 = new JButton("");
		btna_2.setIcon(new ImageIcon(CourseModify.class.getResource("/image/fill.png")));
		btna_2.setBorderPainted(false);
		btna_2.setContentAreaFilled(false);
		btna_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				row = table_2.getSelectedRow();
				textField_3.setText((String)table_2.getValueAt(row, 0));
				textField_4.setText((String)table_2.getValueAt(row, 1));
				textField_5.setText((String)table_2.getValueAt(row, 5));
				if (((String)table_2.getValueAt(row, 6)).equals("选修")) {
					radioButton_3.setSelected(true);
					textField_6.setEditable(true);
					textField_6.setText(((Integer)table_2.getValueAt(row, 7)).toString());
				}
				else {
					radioButton_2.setSelected(true);
					textField_6.setEditable(false);
					textField_6.setText("");
				}
				
				//存储课程节数，方便提交时检测
				number = 0;
				String s = textField_5.getText();
				String[] st = s.split(";");
				for (int i = 0; i < st.length; i++) {
					CourseTime t = new CourseTime(st[i]);
					number += t.getNum();
				}
				
				AdminBLService ad = new AdminController();
				String id = (String)table_2.getValueAt(row,4);
				UserVo u = ad.getUserById(id);
				comboBox_1.setSelectedItem(id + " " + u.getName());
				button.setEnabled(true);
				delete.setEnabled(true);
			}
		});
		btna_2.setBounds(644, 38,50, 50);
		add(btna_2);
		
		JButton shuaxin = new JButton("");
		shuaxin.setIcon(new ImageIcon(CourseModify.class.getResource("/image/refresh.png")));
		shuaxin.setBorderPainted(false);
		shuaxin.setContentAreaFilled(false);
		shuaxin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTableCourse();
			}
		});
		shuaxin.setBounds(571, 38, 50, 50);
		add(shuaxin);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void initBox() {
		// TODO Auto-generated method stub
		StrategyBLService strategy = new StrategyController();
		ArrayList<StrategyVo> str = strategy.getStrategy();
		if(str == null)
			return;
			
		String mo = (String)module.getSelectedItem();
		String cate = (String)category.getSelectedItem();
		
		for(int i = 0; i < str.size(); i++){
			if(str.get(i).getModule().equals(mo) && str.get(i).getCategory().equals(cate)){
				property.setModel(new DefaultComboBoxModel(new String[]{"【请选择】", str.get(i).getProperty()}));
				credit.setModel(new SpinnerNumberModel(str.get(i).getMinCredit(), str.get(i).getMinCredit(), str.get(i).getMaxCredit(), 1));
			}
		}
	}

	private void setTableCourse(){
		tableCourse.setRowCount(0);
		CourseBLService c = new CourseController();
		
		ArrayList<CourseVo> list = c.showJWCourse(User.getUser().getUserDepartment());
		if(list!=null)
			for(int i = 0;i<list.size();i++){
				CourseVo v = list.get(i);
				String op = (v.isOptional()) ? "选修":"必修";
				tableCourse.addRow(new Object[]{v.getPlan().getCourseNo(),v.getPlan().getCourseName(),v.getPlan().getCredit(),v.getPlan().getWeekHour(),
					v.getTeacher(),v.getTime(), op, v.getStudentNum(), v.getPlan().getTerm()});
			}
	}
	
	@SuppressWarnings("unchecked")
	private void setComboBox(){
		AdminBLService ad = new AdminController();
		c.addElement("【请指定】");
		ArrayList<UserVo> a = ad.getDeUser(User.getUser().getUserDepartment(), "Teacher");
		if(a!=null)
			for(int i = 0;i<a.size();i++)
				c.addElement(a.get(i).getID() + " " + a.get(i).getName());
	}
}
