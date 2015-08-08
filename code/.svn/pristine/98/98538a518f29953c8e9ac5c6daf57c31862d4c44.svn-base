package presentation.electionui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import businesslogic.coursebl.CourseController;
import businesslogic.electionbl.*;
import businesslogic.userbl.*;
import businesslogicservice.courseblservice.CourseBLService;
import businesslogicservice.electionblservice.ElectionBLService;
import presentation.mainui.Methods;
import vo.*;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class ElectionSelect extends JPanel{
	private JTable table;
	private JTable table_1;
	
	/**
	 * Create the application.
	 */
	public ElectionSelect() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setOpaque(false);
		setLayout(null);
		
		JPanel panel_6 = new JPanel();
		panel_6.setOpaque(false);
		panel_6.setBorder(new TitledBorder(null, "可选课程", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_6.setBounds(23, 5, 466, 465);
		add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_6.add(scrollPane);	
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"选择", "课程号", "课程名", "课程学分", "周学时", "任课老师", "上课时间", "已选人数"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				Boolean.class, Object.class, String.class, Short.class, Short.class, Object.class, String.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(6).setPreferredWidth(200);
		fillTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
		
		JPanel panel_7 = new JPanel();
		panel_7.setOpaque(false);
		panel_7.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "已选课程", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_7.setBounds(501, 5, 265, 372);
		add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_7.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"课程号", "课程名", "课程学分", "上课时间"
			}
		));
		fillTable_1();
		scrollPane_1.setViewportView(table_1);
		
		//选择课程
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				DefaultTableModel tableModel_1 = (DefaultTableModel)table_1.getModel();
				tableModel_1.setRowCount(0);
				if(((JTable)e.getSource()).columnAtPoint(e.getPoint())==0){
					for(int j = 0;j<table.getRowCount();j++){
						DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
						boolean select = (Boolean)(tableModel.getValueAt(j, 0));  //获得点击单元格数据
							
						String courseNo = (String)tableModel.getValueAt(j, 1);
						if(select){
							String courseName = (String)tableModel.getValueAt(j, 2);
							String time = (String)tableModel.getValueAt(j,6);
						    int credit = (Integer)tableModel.getValueAt(j, 3);
						    
						    tableModel_1.addRow(new Object[]{courseNo, courseName, credit,time});
						}
					}
				}
			}
		});
		
		JButton button = new JButton("");
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setIcon(new ImageIcon(ElectionSelect.class.getResource("/image/delete.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectRows = table_1.getSelectedRows().length;// 取得用户所选行的行数
				DefaultTableModel tableModel = (DefaultTableModel)table_1.getModel();

				if(selectRows == 1){
					int selectedRowIndex = table_1.getSelectedRow(); // 取得用户所选单行 
					
					/*
					 * 将可选课程中该行记录恢复为未勾选状态
					 */
					String cNo = (String)table_1.getValueAt(selectedRowIndex, 0);
					for (int i = 0; i < table.getRowCount(); i++) {
						if (((String)table.getValueAt(i, 1)).equals(cNo)) {
							DefaultTableModel model = (DefaultTableModel)table.getModel();
							model.setValueAt(false, i, 0);
							table.validate();
							break;
						}
					}
					
					//在已选课程中删除该行记录
					tableModel.removeRow(selectedRowIndex);
				}
			}
		});
		button.setBounds(558, 407, 50, 50);
		add(button);
		
		JButton button_1 = new JButton("");
		button_1.setBorderPainted(false);
		button_1.setContentAreaFilled(false);
		button_1.setIcon(new ImageIcon(ElectionSelect.class.getResource("/image/ok.png")));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<ElectionVo> election = new ArrayList<ElectionVo>();
				String user = User.getUser().getUserID();
				
				//检查课程门数是否符合限制
				if (table_1.getRowCount() == 0) {
					Methods.showTip("请选择课程");
					return;
				}
				if (table_1.getRowCount() > 4) {
					Methods.showTip("你最多只能选择4门课程");
					return;
				}
				
				for (int i = 0; i < table_1.getRowCount(); i++) {
					DefaultTableModel tableModel_1 = (DefaultTableModel)table_1.getModel();
					String courseNo = (String)tableModel_1.getValueAt(i, 0);
					String courseName = (String)tableModel_1.getValueAt(i, 1);
					int credit = (Integer)tableModel_1.getValueAt(i, 2);
					
					PlanVo plan = new PlanVo("", "", "", courseNo, courseName, "", credit, 0,0);
					CourseVo course = new CourseVo();
					course.setPlan(plan);
					course.setTime((String) tableModel_1.getValueAt(i,3));
					election.add(new ElectionVo(course, user, User.getUser().getUserGrade()));
				}
				
				ElectionBLService ebls = new ElectionController();
		        String result = ebls.addCourse(election);
		        Methods.showTip(result);
		        
		        fillTable();
		        fillTable_1();
			}
		});
		button_1.setBounds(661, 407, 50, 50);
		add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.setBorderPainted(false);
		button_2.setContentAreaFilled(false);
		button_2.setIcon(new ImageIcon(ElectionSelect.class.getResource("/image/refresh.png")));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fillTable();
			}
		});
		button_2.setBounds(225, 484, 50, 50);
		add(button_2);
	}

	private void fillTable() {
		DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
		tableModel.setRowCount(0);// 清除原有行

		// 得到数据
		ElectionBLService ebls = new ElectionController();
		CourseBLService cbls = new CourseController();
		ArrayList<CourseVo> course = ebls.getAvilCourse();
		
		if (course == null)
			return;
		
		// 填充数据
		for(int i = 0; i < course.size(); i++){
		    Object[] arr=new Object[8];
		    String courseNo = course.get(i).getPlan().getCourseNo();
		    arr[0] = false;
		    arr[1] = courseNo;
		    arr[2] = course.get(i).getPlan().getCourseName();
		    arr[3] = course.get(i).getPlan().getCredit();
		    arr[4] = course.get(i).getPlan().getWeekHour();
		    arr[5] = course.get(i).getTeacher();
		    arr[6] = course.get(i).getTime();
		    
		    //得到已选人数和课程人数
		    int cur = cbls.getCurSelNum(courseNo);
		    int max = cbls.getMaxStuNum(courseNo);
		    arr[7] = cur + "/" + max;
		    
		    // 添加数据到表格
		    tableModel.addRow(arr);
		}

		// 更新表格
		table.invalidate();
	}
	
	private void fillTable_1() {
		DefaultTableModel tableModel = (DefaultTableModel)table_1.getModel();
		tableModel.setRowCount(0);// 清除原有行

		// 得到数据
		ElectionBLService ebls = new ElectionController();
		ArrayList<ElectionVo> election = ebls.getTemEle(User.getUser().getUserID());
		
		if (election == null)
			return;
		
		// 填充数据
		for(int i = 0; i < election.size(); i++){
		    Object[] arr = new Object[4];
		    arr[0] = election.get(i).getCourse().getPlan().getCourseNo();
		    arr[1] = election.get(i).getCourse().getPlan().getCourseName();
		    arr[2] = election.get(i).getCourse().getPlan().getCredit();
		    arr[3] = election.get(i).getCourse().getTime();
		    
		    // 添加数据到表格
		    tableModel.addRow(arr);
		}

		// 更新表格
		table_1.validate();
	}
}
