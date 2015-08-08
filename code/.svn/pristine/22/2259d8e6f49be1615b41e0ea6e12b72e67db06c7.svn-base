package presentation.electionui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import businesslogic.electionbl.*;
import businesslogic.userbl.*;
import businesslogicservice.electionblservice.ElectionBLService;
import presentation.mainui.Methods;
import vo.*;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class ElectionQuit extends JPanel{
	private JTable table_2;
	private JTable table_3;

	/**
	 * Create the application.
	 */
	public ElectionQuit() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setOpaque(false);
		setLayout(null);
		
		JPanel panel_8 = new JPanel();
		panel_8.setOpaque(false);
		panel_8.setBorder(new TitledBorder(null, "已选课程", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_8.setBounds(23, 5, 466, 465);
		add(panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_8.add(scrollPane_2);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"退选", "课程号", "课程名", "课程学分", "周学时", "任课老师", "上课时间"			
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				Boolean.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_2.getColumnModel().getColumn(2).setPreferredWidth(120);
		table_2.getColumnModel().getColumn(6).setPreferredWidth(200);
		fillTable_2();
		table_2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane_2.setViewportView(table_2);
		
		JPanel panel_9 = new JPanel();
		panel_9.setOpaque(false);
		panel_9.setBorder(new TitledBorder(null, "退选课程", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_9.setBounds(501, 20, 265, 335);
		add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		panel_9.add(scrollPane_3);
		
		table_3 = new JTable();
		table_3.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"课程号", "课程名", "课程学分"
			}
		));
		scrollPane_3.setViewportView(table_3);
		
		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int row =((JTable)e.getSource()).rowAtPoint(e.getPoint());       //获得行位置 
				int col=((JTable)e.getSource()).columnAtPoint(e.getPoint());     //获得列位置
			    if (col == 0) {
			    	DefaultTableModel tableModel_2 = (DefaultTableModel)table_2.getModel();
					DefaultTableModel tableModel_3 = (DefaultTableModel)table_3.getModel();
					boolean quit = (Boolean)(tableModel_2.getValueAt(row, col));  //获得点击单元格数据
					
					String courseNo = (String)tableModel_2.getValueAt(row, 1);
					if(!quit) {
						for(int i = 0; i < tableModel_3.getRowCount(); i++) {
							if(((String)tableModel_3.getValueAt(i, 0)).equals(courseNo)) {
								tableModel_3.removeRow(i);
								break;
							}
						}
					}
					else {
						String courseName = (String)tableModel_2.getValueAt(row, 2);
					    int credit = (Integer)tableModel_2.getValueAt(row, 3);
					    tableModel_3.addRow(new Object[]{courseNo, courseName, credit});
					}
				}
			}
		});
		
		JButton button_2 = new JButton("");
		button_2.setIcon(new ImageIcon(ElectionQuit.class.getResource("/image/delete.png")));
		button_2.setBorderPainted(false);
		button_2.setContentAreaFilled(false);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectRows = table_3.getSelectedRows().length;    // 取得用户所选行的行数
				DefaultTableModel tableModel_3 = (DefaultTableModel)table_3.getModel();

				if(selectRows == 1){
					int selectedRowIndex = table_3.getSelectedRow();  // 取得用户所选单行 
					
					/*
					 * 将已选课程中该行记录恢复为未勾选状态
					 */
					String cNo = (String)table_3.getValueAt(selectedRowIndex, 0);
					for (int i = 0; i < table_2.getRowCount(); i++) {
						if (((String)table_2.getValueAt(i, 1)).equals(cNo)) {
							DefaultTableModel model = (DefaultTableModel)table_2.getModel();
							model.setValueAt(false, i, 0);
							table_2.validate();
							break;
						}
					}
					
					//在退选课程中删除该行记录
					tableModel_3.removeRow(selectedRowIndex);
				}
			}
		});
		button_2.setBounds(557, 386, 50, 50);
		add(button_2);
		
		JButton button_3 = new JButton("");
		button_3.setContentAreaFilled(false);
		button_3.setIcon(new ImageIcon(ElectionQuit.class.getResource("/image/ok.png")));
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table_3.getRowCount() == 0) {
					Methods.showTip("请选择要退选的课程");
					return;
				}
				
				/*
				 * 确认退选
				 */
				final JDialog j = new JDialog();
				j.getContentPane().setLayout(null);
				j.setTitle("~~~");
				j.setSize(400, 200);
				j.setLocationRelativeTo(null);
				
				JLabel label = new JLabel("你确定要退选所选课程吗？");
				label.setBounds(120, 50, 200, 20);
				j.getContentPane().add(label);
				
				JButton button = new JButton("");
				button.setBorderPainted(false);
				button.setContentAreaFilled(false);
				button.setIcon(new ImageIcon(ElectionQuit.class.getResource("/image/ok.png")));
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						j.dispose();
						quit();
					}
				});
				button.setBounds(220, 110, 77, 23);
				j.getContentPane().add(button);
				
				JButton button_1 = new JButton("");
				button_1.setBorderPainted(false);
				button_1.setContentAreaFilled(false);
				button_1.setIcon(new ImageIcon(ElectionQuit.class.getResource("/image/close.png")));
				button_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						j.dispose();;
					}
				});
				button_1.setBounds(103, 110, 77, 23);
				j.getContentPane().add(button_1);
				
				j.setVisible(true);
			}
		});
		button_3.setBounds(664, 386, 50, 50);
		button_3.setBorderPainted(false);
		add(button_3);
		
		JButton button = new JButton("");
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setIcon(new ImageIcon(ElectionQuit.class.getResource("/image/refresh.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fillTable_2();
			}
		});
		button.setBounds(237, 482, 50, 50);
		add(button);
	}

	private void fillTable_2() {
		DefaultTableModel tableModel = (DefaultTableModel)table_2.getModel();
		tableModel.setRowCount(0);// 清除原有行

		// 得到数据
		ElectionBLService ebls = new ElectionController();
		ArrayList<ElectionVo> election = ebls.getElection(User.getUser().getUserID());
		
		if (election == null)
			return;
		
		// 填充数据
		for(int i = 0; i < election.size(); i++){
		    Object[] arr = new Object[7];
		    arr[0] = false;
		    arr[1] = election.get(i).getCourse().getPlan().getCourseNo();
		    arr[2] = election.get(i).getCourse().getPlan().getCourseName();
		    arr[3] = election.get(i).getCourse().getPlan().getCredit();
		    arr[4] = election.get(i).getCourse().getPlan().getWeekHour();
		    arr[5] = election.get(i).getCourse().getTeacher();
		    arr[6] = election.get(i).getCourse().getTime();
		    
		    // 添加数据到表格
		    tableModel.addRow(arr);
		}

		// 更新表格
		table_2.validate();
	}
	
	private void quit() {
		ArrayList<ElectionVo> quit = new ArrayList<ElectionVo>();
		String user = User.getUser().getUserID();
		
		DefaultTableModel tableModel_3 = (DefaultTableModel)table_3.getModel();
		for (int i = 0; i < table_3.getRowCount(); i++) {
			String courseNo = (String)tableModel_3.getValueAt(i, 0);
			String courseName = (String)tableModel_3.getValueAt(i, 1);
			int credit = (Integer)tableModel_3.getValueAt(i, 2);
			
			PlanVo plan = new PlanVo("", "", "", courseNo, courseName, "", credit, 0,0);
			CourseVo course = new CourseVo();
			course.setPlan(plan);
			quit.add(new ElectionVo(course, user, User.getUser().getUserGrade()));
		}
		
		ElectionBLService ebls = new ElectionController();
        ebls.deleteCourse(quit);
        Methods.showTip("删除成功");
        
        fillTable_2();
        tableModel_3.setRowCount(0);
	}
}
