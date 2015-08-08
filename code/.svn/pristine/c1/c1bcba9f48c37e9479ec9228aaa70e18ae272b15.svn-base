package presentation.courseui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import businesslogic.coursebl.CourseController;
import businesslogic.userbl.User;
import businesslogicservice.courseblservice.CourseBLService;
import presentation.mainui.Methods;
import vo.CourseVo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class CourseCom extends JPanel{
	private JTable table;
	private JTextField textField;
	Color original;
	
	private String[] co = {"课程号", "课程名", "课程学分", "周学时", "上课时间"};
	private DefaultTableModel tableCourse = new DefaultTableModel(null,co);
	private JTextArea textArea_1;
	private JTextArea textArea;
	private JTextField textCourse;
	private JButton button;

	/**
	 * Create the application.
	 */
	public CourseCom() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setOpaque(false);
		setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "课程列表", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(35, 16, 466, 515);
		add(panel_3);
		
		JPanel panel_6 = new JPanel();
		panel_6.setOpaque(false);
		panel_6.setBorder(new TitledBorder(null, "填写信息", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setBounds(513, 78, 245, 396);
		add(panel_6);
		panel_6.setLayout(null);
		
		JLabel label = new JLabel("教材");
		label.setBounds(23, 72, 48, 16);
		panel_6.add(label);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(83, 66, 134, 28);
		panel_6.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("课程大纲");
		label_1.setBounds(95, 234, 61, 16);
		panel_6.add(label_1);
		
		JLabel label_2 = new JLabel("参考书目");
		label_2.setBounds(95, 95, 61, 16);
		panel_6.add(label_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(0, 0, 0, 103);
		scrollPane_1.setViewportView(textArea_1);
		scrollPane_1.setBounds(23, 123, 194, 107);
		panel_6.add(scrollPane_1);
		
		textArea_1.setBounds(0, 0, 0, 103);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(25, 262, 194, 125);
		scrollPane_2.setViewportView(textArea);
		panel_6.add(scrollPane_2);
		
		JLabel label_4 = new JLabel("\u8BFE\u7A0B\u53F7");
		label_4.setBounds(23, 30, 61, 16);
		panel_6.add(label_4);
		
		textCourse = new JTextField();
		textCourse.setEditable(false);
		textCourse.setBounds(83, 24, 134, 28);
		panel_6.add(textCourse);
		textCourse.setColumns(10);
		
		button = new JButton("");
		button.setContentAreaFilled(false);
		button.setIcon(new ImageIcon(CourseCom.class.getResource("/image/ok.png")));
		button.setEnabled(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String referBook = textArea_1.getText();
				String textBook = textField.getText();
				String outline = textArea.getText();
				if (referBook.length() == 0 || textBook.length() == 0 || outline.length() == 0) {
					Methods.showTip("请填写完整信息再提交");
					return;
				}
				
				CourseBLService con = new CourseController();
				CourseVo vo = con.getCourse((String)table.getValueAt(row, 0));
				vo.setReferbook(referBook);
				vo.setTextbook(textBook);
				vo.setOutline(outline);
				String result = con.addCourseInfo(vo);
				if(result.equals("")||result == null)
					;
				else{
					Methods.showTip(result);
				}
				button.setEnabled(false);
				textArea_1.setText("");
				textArea_1.setEditable(false);
				textField.setText("");
				textField.setEditable(false);
				textArea.setText("");
				textArea.setEditable(false);
			}
		});
		button.setBounds(626, 481, 50, 50);
		button.setBorderPainted(false);
		add(button);
		
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_3.add(scrollPane);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
		setCourse();
		table.setModel(tableCourse);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(4).setPreferredWidth(200);
		
		JButton button_2 = new JButton("");
		button_2.setBorderPainted(false);
		button_2.setContentAreaFilled(false);
		button_2.setIcon(new ImageIcon(CourseCom.class.getResource("/image/fill.png")));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if (row == -1) {
					Methods.showTip("请选择要填写信息的课程");
					return;
				}
				textCourse.setText((String)table.getValueAt(row, 0));
				button.setEnabled(true);
				textArea.setEditable(true);
				textArea_1.setEditable(true);
				textField.setEditable(true);
			}
		});
		button_2.setBounds(664, 16, 50, 50);
		add(button_2);
		
		JButton button_1 = new JButton("");
		button_1.setBorderPainted(false);
		button_1.setContentAreaFilled(false);
		button_1.setIcon(new ImageIcon(CourseCom.class.getResource("/image/refresh.png")));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCourse();
			}
		});
		button_1.setBounds(560, 16, 50, 50);
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
