package presentation.scoreui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

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
import businesslogic.controlbl.Controller;
import businesslogic.scorebl.ScoreController;
import businesslogic.userbl.*;
import businesslogicservice.adminblservice.AdminBLService;
import businesslogicservice.controlblservice.ControllerBLService;
import businesslogicservice.scoreblservice.*;
import presentation.mainui.Methods;
import vo.*;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class ScoreAnal extends JPanel{

	private JTable table_4;
	private JPanel panel_10;
	private JScrollPane scrollPane_4;
	
	@SuppressWarnings("rawtypes")
	DefaultComboBoxModel combo = new DefaultComboBoxModel();
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox_1;

	/**
	 * Create the application.
	 */
	public ScoreAnal() {
		setOpaque(false);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		setLayout(null);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"课程成绩", "学分统计", "学分绩"}));
		comboBox.setBounds(333, 43, 149, 27);
		add(comboBox);
		
		JLabel label = new JLabel("选择信息");
		label.setBounds(218, 47, 61, 16);
		add(label);
		
		JLabel label_1 = new JLabel("学期选择");
		label_1.setBounds(218, 89, 61, 16);
		add(label_1);
		
		comboBox_1 = new JComboBox();
		setComboBox();
		comboBox_1.setModel(combo);
		comboBox_1.setBounds(333, 85, 149, 27);
		add(comboBox_1);
		
		panel_10 = new JPanel();
		panel_10.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "成绩信息", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_10.setBounds(177, 137, 455, 298);
		add(panel_10);
		panel_10.setLayout(new BorderLayout(0, 0));
		
		JButton button_4 = new JButton("");
		button_4.setBorderPainted(false);
		button_4.setContentAreaFilled(false);
		button_4.setIcon(new ImageIcon(ScoreAnal.class.getResource("/image/ok.png")));
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ScoreBLService sc = new ScoreController();
				
				String s = (String)comboBox_1.getSelectedItem();
				ArrayList<ScoreVo> list = new ArrayList<ScoreVo>();
				if (s.equals("【请选择】")) {
					list = sc.showMyScore(User.getUser().getUserID());
				}
				else {
					int term = getTerm(s);
					list = sc.showMyScore(User.getUser().getUserID(), term);
				}
				
				if (list == null || list.size() == 0) {
					Methods.showTip("成绩尚未发布");
					return;
				}
				
				if(comboBox.getSelectedItem().equals("课程成绩")){
					String[] columnNames = new String[]{"课程号","课程名", "课程学分", "课程类型", "成绩"};
					DefaultTableModel model = new DefaultTableModel(null, columnNames);

					for(int i = 0;i < list.size();i++){
						Object[] data = new Object[5];
						data[0] = list.get(i).getCourse().getPlan().getCourseNo();
						data[1] = list.get(i).getCourse().getPlan().getCourseName();
						data[2] = list.get(i).getCourse().getPlan().getCredit();
						if (list.get(i).getCourse().isOptional()) {
							data[3] = "选修";
						}
						else {
							data[3] = "必修";
						}
						data[4] = list.get(i).getScore();
						
						if ((Double)data[4] == -1)
							continue;
						model.addRow(data);
					}
					
					table_4.setModel(model);
				}
				else if(comboBox.getSelectedItem().equals("学分绩")){
					String[] columnNames = new String[]{"课程名", "成绩/学分绩"};
					DefaultTableModel model = new DefaultTableModel(null, columnNames);
					
					for (int i = 0; i < list.size(); i++) {
						Object[] data = new Object[2];
						data[0] = list.get(i).getCourse().getPlan().getCourseName();
						data[1] = list.get(i).getScore();
						
						model.addRow(data);
					}
					
					double gpa = list.get(0).getGpa();
					DecimalFormat df = new DecimalFormat("0.000");
					Object[] data = new Object[2];
					data[0] = "学分绩";
					data[1] = df.format(gpa);
					
					model.addRow(data);
					table_4.setModel(model);
					
				}
				else if (comboBox.getSelectedItem().equals("学分统计")) {
					String[] columnNames = new String[]{"必修学分", "选修学分", "总学分"};
					DefaultTableModel model = new DefaultTableModel(null, columnNames);
									
					Object[] data = new Object[3];
					data[0] = list.get(0).getComCre();
					data[1] = list.get(0).getOptCre();
					data[2] = list.get(0).getCredits();
					
					model.addRow(data);
					table_4.setModel(model);
				}
				else{
					//准入准出课程信息					
				}
			}
		});
		button_4.setBounds(524, 55, 50, 50);
		add(button_4);
		
		table_4 = new JTable();
		
		scrollPane_4 = new JScrollPane();
		scrollPane_4.setViewportView(table_4);
		
		panel_10.add(scrollPane_4, BorderLayout.CENTER);
	}

	@SuppressWarnings("unchecked")
	private void setComboBox() {
		// TODO Auto-generated method stub
		combo.addElement("【请选择】");
		
		/*
		 * 根据当前学生的年级初始化学期列表
		 */
		AdminBLService ad = new AdminController();
		ControllerBLService con = new Controller();
		UserVo user = ad.getUserById(User.getUser().getUserID());
		int grade = user.getGrade();
		int year = con.getYear();
		int term = con.getTerm();
		if (grade == 1) {
			combo.addElement((year-1) + " ~ " + year + " 上");
			if (term == 0) {
				combo.addElement((year-1) + " ~ " + year + " 下");
			}
		}
		if (grade == 2) {
			combo.addElement((year-2) + " ~ " + (year-1) + " 上");
			combo.addElement((year-2) + " ~ " + (year-1) + " 下");
			combo.addElement((year-1) + " ~ " + year + " 上");
			if (term == 0)
				combo.addElement((year-1) + " ~ " + year + " 下");
		}
		if (grade == 3) {
			combo.addElement((year-3) + " ~ " + (year-2) + " 上");
			combo.addElement((year-3) + " ~ " + (year-2) + " 下");
			combo.addElement((year-2) + " ~ " + (year-1) + " 上");
			combo.addElement((year-2) + " ~ " + (year-1) + " 下");
			combo.addElement((year-1) + " ~ " + year + " 上");
			if (term == 0)
				combo.addElement((year-1) + " ~ " + year + " 下");
		}
		if (grade == 4) {
			combo.addElement((year-4) + " ~ " + (year-3) + " 上");
			combo.addElement((year-4) + " ~ " + (year-3) + " 下");
			combo.addElement((year-3) + " ~ " + (year-2) + " 上 ");
			combo.addElement((year-3) + " ~ " + (year-2) + " 下 ");
			combo.addElement((year-2) + " ~ " + (year-1) + " 上 ");
			combo.addElement((year-2) + " ~ " + (year-1) + " 下 ");
			combo.addElement((year-1) + " ~ " + year + " 上 ");
			if (term == 0) {
				combo.addElement((year-1) + " ~ " + year + " 下 ");
			}
		}
	}
	
	private int getTerm(String s) {
		// TODO Auto-generated method stub
		AdminBLService ad = new AdminController();
		UserVo user = ad.getUserById(User.getUser().getUserID());
		int register = user.getRegisterYear();
		
		String[] sp = s.split(" ");
		int year = Integer.valueOf(sp[2]);
		int term;
		if (sp[3].equals("上"))
			term = 1;
		else
			term = 0;
		
		int result = (year - register) * 2 - term;
		return result;
	}
}
