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
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import businesslogic.adminbl.AdminController;
import businesslogic.coursebl.CourseController;
import businesslogic.coursebl.CourseTime;
import businesslogic.planbl.PlanController;
import businesslogic.userbl.User;
import businesslogicservice.adminblservice.AdminBLService;
import businesslogicservice.courseblservice.CourseBLService;
import businesslogicservice.planblservice.PlanBLService;
import presentation.mainui.Methods;
import vo.CourseVo;
import vo.PlanVo;
import vo.UserVo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerListModel;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class CoursePublish extends JPanel{
	private JTable table_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField time;
	private String[] planColumn = new String[] {"课程号", "课程名", "课程模块", "课程分类", "课程性质", "课程学分","开设学期","周学时"};
	private DefaultTableModel tablePlan = new DefaultTableModel(null,planColumn);
	private final ButtonGroup buttonGroup = new ButtonGroup();
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	private JRadioButton radioButton;
	@SuppressWarnings("rawtypes")
	private DefaultComboBoxModel c = new DefaultComboBoxModel();
	private JButton btns_1;
	private JSpinner num;
	private JSpinner start;
	private JSpinner day;
	private JButton btnNewButton;
	private JRadioButton radioButton_1;
	private JTextField textField_2;
	
	int row;

	/**
	 * Create the application.
	 */
	public CoursePublish() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		setOpaque(false);
		setTablePlan();
		setComboBox();
		setLayout(null);
		
		JPanel panel_7 = new JPanel();
		panel_7.setOpaque(false);
		panel_7.setBounds(43, 16, 466, 483);
		panel_7.setBorder(new TitledBorder(null, "教学计划", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_7.add(scrollPane_1, BorderLayout.CENTER);
		
		table_1 = new JTable();
		table_1.setModel(tablePlan);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(120);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane_1.setViewportView(table_1);
		
		JButton btna_1 = new JButton("");
		btna_1.setIcon(new ImageIcon(CoursePublish.class.getResource("/image/publish.png")));
		btna_1.setBorderPainted(false);
		btna_1.setContentAreaFilled(false);
		btna_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				row = table_1.getSelectedRow();
				if (row == -1) {
					Methods.showTip("请选择要发布的信息");
					return;
				}
				textField.setText((String) table_1.getValueAt(row, 0));
				textField_1.setText((String)table_1.getValueAt(row, 1));
				comboBox.setSelectedIndex(0);
				String com = (String)table_1.getValueAt(row, 4);
				if (com.equals("选修")) {
					radioButton_1.setSelected(true);
					textField_2.setEditable(true);
				}
				else {
					radioButton.setSelected(true);
					textField_2.setEditable(false);
				}
				btns_1.setEnabled(true);
				
				//课程号与课程名，课程性质不允许修改
				textField.setEditable(false);
				textField_1.setEditable(false);
				radioButton.setEnabled(false);
				radioButton_1.setEnabled(false);
			}
		});
		btna_1.setBounds(650, 16, 50, 50);
		add(btna_1);
		
		JPanel panel_8 = new JPanel();
		panel_8.setOpaque(false);
		panel_8.setBorder(new TitledBorder(null, "课程信息", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_8.setBounds(521, 90, 226, 347);
		add(panel_8);
		panel_8.setLayout(null);
		
		JLabel label = new JLabel("课程号");
		label.setBounds(14, 33, 70, 16);
		panel_8.add(label);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(79, 27, 134, 28);
		panel_8.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("课程名");
		label_1.setBounds(14, 61, 70, 16);
		panel_8.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(79, 55, 134, 28);
		panel_8.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_2 = new JLabel("任课老师");
		label_2.setBounds(14, 97, 70, 16);
		panel_8.add(label_2);
		
		comboBox = new JComboBox();
		comboBox.setBounds(79, 93, 134, 27);
		comboBox.setModel(c);
		panel_8.add(comboBox);
		
		JLabel label_3 = new JLabel("上课时间");
		label_3.setBounds(14, 131, 70, 16);
		panel_8.add(label_3);
		
		time = new JTextField();
		time.setBounds(79, 125, 134, 28);
		time.setEditable(false);
		panel_8.add(time);
		time.setColumns(10);
		
		radioButton = new JRadioButton("必修");
		radioButton.setBounds(46, 271, 78, 23);
		panel_8.add(radioButton);
		
		radioButton_1 = new JRadioButton("选修");
		radioButton_1.setBounds(123, 271, 75, 23);
		panel_8.add(radioButton_1);
		
		buttonGroup.add(radioButton);
		buttonGroup.add(radioButton_1);
		
		day = new JSpinner();
		day.setModel(new SpinnerListModel(new String[] {"\u5468\u4E00", "\u5468\u4E8C", "\u5468\u4E09", "\u5468\u56DB", "\u5468\u4E94", "\u5468\u516D", "\u5468\u65E5"}));
		day.setBounds(6, 165, 52, 28);
		panel_8.add(day);
		
		start = new JSpinner();
		start.setModel(new SpinnerNumberModel(1, 1, 9, 1));
		start.setBounds(90, 165, 47, 28);
		panel_8.add(start);
		
		num = new JSpinner();
		num.setModel(new SpinnerNumberModel(1, 1, 4, 1));
		num.setBounds(166, 165, 47, 28);
		panel_8.add(num);
		
		JButton button_2 = new JButton("");
		button_2.setIcon(new ImageIcon(CoursePublish.class.getResource("/image/add.png")));
		button_2.setBorderPainted(false);
		button_2.setContentAreaFilled(false);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				String text  = time.getText();
				
				int d = CourseTime.dayToInt(((String)day.getValue()).charAt(1));
				int s = (Integer) start.getValue();
				int n = (Integer) num.getValue();
				CourseTime  t = new CourseTime(d,s,n);

				if(text==null||text.equals("")){
					time.setText(t.getTime());
					btnNewButton.setEnabled(true);
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
				
				time.setText(text+";"+t.getTime());
			}
		});
		button_2.setBounds(49, 205, 50, 50);
		panel_8.add(button_2);
		
		btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(CoursePublish.class.getResource("/image/delete.png")));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = time.getText();
				if(text.contains(";")){
					String[] ss= text.split(";");
					int l = ss[ss.length-1].length();
					time.setText(text.substring(0, text.length()-l-1));
				}
				else{
					time.setText("");
					btnNewButton.setEnabled(false);
				}
			}
		});
		btnNewButton.setBounds(130, 205, 50, 50);
		panel_8.add(btnNewButton);
		
		JLabel label_15 = new JLabel("首节");
		label_15.setBounds(63, 170, 61, 16);
		panel_8.add(label_15);
		
		JLabel label_16 = new JLabel("节数");
		label_16.setBounds(140, 170, 61, 16);
		panel_8.add(label_16);
		
		JLabel lblNewLabel = new JLabel("上课人数");
		lblNewLabel.setBounds(14, 310, 70, 15);
		panel_8.add(lblNewLabel);
		
		textField_2 = new JTextField();
		textField_2.setBounds(79, 306, 134, 22);
		panel_8.add(textField_2);
		textField_2.setColumns(10);
		
		btns_1 = new JButton("");
		btns_1.setIcon(new ImageIcon(CoursePublish.class.getResource("/image/ok.png")));
		btns_1.setBorderPainted(false);
		btns_1.setContentAreaFilled(false);
		btns_1.setEnabled(false);
		btns_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (((String)comboBox.getSelectedItem()).equals("【请指定】") || time.getText().length() == 0) {
					Methods.showTip("请将信息填写完整再提交");
					return;
				}

				int no = 0;
				if (radioButton_1.isSelected()) {
					
					/*
					 * 若为选修则判断上课人数
					 */
					String studentNum = (String) textField_2.getText();
					try {
						no = Integer.valueOf(studentNum);
					} catch (NumberFormatException n) {
						Methods.showTip("上课人数不应为空且应为整数");
						return;
					}
					if (no <= 0) {
						Methods.showTip("上课人数应大于0");
						return;
					}
				}
				
				/*
				 * 判断上课时间是否符合周学时的限制
				 */
				String check = checkTime(time.getText(), (Integer)table_1.getValueAt(row, 7));
				if (!check.equals("符合")) {
					Methods.showTip(check);
					return;
				}
				
				int term = (Integer)table_1.getValueAt(row, 6);
				String department = User.getUser().getUserDepartment();
				PlanVo p = new PlanVo("", "", "", textField.getText(), textField_1.getText(), department, 0, 0, term);
				CourseVo c = new CourseVo(p,((String)comboBox.getSelectedItem()).split(" ")[0],time.getText(), 
						!radioButton.isSelected(), "", "", "", no);
				CourseBLService ct = new CourseController();
				String result = ct.publish(c);
				
				//对话框
				if(result.equals("")||result == null)
					;
				else{
					Methods.showTip(result);
				}
				btns_1.setEnabled(false);
				
				//清空所填项
				textField.setText("");
				textField_1.setText("");
				comboBox.setSelectedIndex(0);
				time.setText("");
				day.setValue("周一");
				start.setValue(1);
				num.setValue(1);
				buttonGroup.clearSelection();
				textField_2.setText("");
			}
		});
		btns_1.setBounds(571, 449, 50, 50);
		add(btns_1);
		
		JButton btnd_1 = new JButton("");
		btnd_1.setIcon(new ImageIcon(CoursePublish.class.getResource("/image/rewrite.png")));
		btnd_1.setBorderPainted(false);
		btnd_1.setContentAreaFilled(false);
		btnd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.setSelectedIndex(0);
				time.setText("");
				day.setValue("周一");
				start.setValue(1);
				num.setValue(1);
				textField_2.setText("");
			}
		});
		btnd_1.setBounds(650, 449, 50, 50);
		add(btnd_1);
		
		JButton button = new JButton("");
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setIcon(new ImageIcon(CoursePublish.class.getResource("/image/refresh.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTablePlan();
			}
		});
		button.setBounds(571, 16, 50, 50);
		add(button);
	}
	
	private void setTablePlan() {
		// TODO Auto-generated method stub
		tablePlan.setRowCount(0);
		PlanBLService p = new PlanController();
		ArrayList<PlanVo> list = p.getPlan(User.getUser().getUserDepartment());
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				PlanVo v = list.get(i);
				tablePlan.addRow(new Object[] { v.getCourseNo(), v.getCourseName(), v.getModule(), v.getCategory(),
						v.getProperty(), v.getCredit(), v.getTerm(),v.getWeekHour()});
			}
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
	
	public String checkTime(String text, int wh) {
		// TODO Auto-generated method stub
		int num = 0;
		if (text.contains(";")) {
			String[] s = text.split(";");
			CourseTime[] time = new CourseTime[s.length];
			for (int i = 0; i < s.length; i++) {
				time[i] = new CourseTime(s[i]);
				num += time[i].getNum();
			}
		}
		else {
			CourseTime time = new CourseTime(text);
			num = time.getNum();
		}
		
		if (num == wh) {
			return "符合";
		}
		else {
			return "上课时间应符合周学时的要求";
		}
	}
}