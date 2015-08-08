package presentation.electionui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import presentation.mainui.Methods;
import vo.CourseVo;
import vo.ElectionVo;
import vo.PlanVo;
import businesslogic.electionbl.ElectionController;
import businesslogic.userbl.User;
import businesslogicservice.electionblservice.ElectionBLService;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class ElectionCom extends JPanel{
	private JTable table_5;

	/**
	 * Create the application.
	 */
	public ElectionCom() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setOpaque(false);
		setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		panel_4.setBorder(new TitledBorder(null, "可选课程", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_4.setBounds(123, 31, 547, 315);
		add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scroll = new JScrollPane();
		panel_4.add(scroll);
		
		table_5 = new JTable();
		table_5.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"选择", "课程号", "课程名", "课程学分", "周学时", "任课老师", "上课时间"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				Boolean.class, Object.class, String.class, Short.class, Short.class, Object.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_5.getColumnModel().getColumn(2).setPreferredWidth(120);
		table_5.getColumnModel().getColumn(6).setPreferredWidth(200);
		fillTable_5();
		table_5.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scroll.setViewportView(table_5);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(ElectionCom.class.getResource("/image/ok.png")));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<ElectionVo> list = new ArrayList<ElectionVo>();
				
				/*
				 * 遍历表格，得到学生选择的课程
				 */
				for (int i = 0; i < table_5.getRowCount(); i++) {
					boolean select = (Boolean)table_5.getValueAt(i, 0);
					if (select) {
						String cNo = (String)table_5.getValueAt(i, 1);
						String cName = (String)table_5.getValueAt(i, 2);
						PlanVo plan = new PlanVo("", "", "", cNo, cName, "", (Integer)table_5.getValueAt(i, 3), 
								0, 0);
						CourseVo vo = new CourseVo();
						vo.setPlan(plan);
						vo.setTime((String)table_5.getValueAt(i, 6));
						list.add(new ElectionVo(vo, User.getUser().getUserID(), User.getUser().getUserGrade()));
					}
				}
				
				if (list.size() == 0) {
					Methods.showTip("请选择课程");
					return;
				}
				
				ElectionBLService ebls = new ElectionController();
				String result = ebls.addCourse(list);
				Methods.showTip(result);
				
				fillTable_5();
			}
		});
		btnNewButton.setBounds(437, 381, 50, 50);
		add(btnNewButton);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon(ElectionCom.class.getResource("/image/refresh.png")));
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fillTable_5();
			}
		});
		button.setBounds(320, 381, 50, 50);
		add(button);
	}
	
	private void fillTable_5() {
		// TODO Auto-generated method stub
		DefaultTableModel tableModel = (DefaultTableModel)table_5.getModel();
		tableModel.setRowCount(0);// 清除原有行

		// 得到数据
		ElectionBLService ebls = new ElectionController();
		ArrayList<CourseVo> course = ebls.getComCourse();
		
		if (course == null || course.size() == 0) {
			Methods.showTip("无课程可以补选");
			return;
		}
		
		// 填充数据
		for (int i = 0; i < course.size(); i++) {
			CourseVo vo = course.get(i);
			Object[] data = new Object[7];
			data[0] = false;
			data[1] = vo.getPlan().getCourseNo();
			data[2] = vo.getPlan().getCourseName();
			data[3] = vo.getPlan().getCredit();
			data[4] = vo.getPlan().getWeekHour();
			data[5] = vo.getTeacher();
			data[6] = vo.getTime();
		    
		    // 添加数据到表格
		    tableModel.addRow(data);
		}
		
		// 更新表格
		table_5.validate();
	}
}
