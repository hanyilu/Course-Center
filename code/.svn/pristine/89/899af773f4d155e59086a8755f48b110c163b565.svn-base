package presentation.adminui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import presentation.mainui.Methods;
import businesslogic.adminbl.AdminController;
import businesslogicservice.adminblservice.AdminBLService;
import vo.UserVo;

@SuppressWarnings("serial")
public class UserManage extends JPanel{

	private JTable table;
	private JTextField txtid;
	private JTextField txtid_1;
	private JTextField txtnamestring;
	private JTextField textField;

	private String[] UserInfo={"用户ID", "身份", "密码", "姓名", "性别", "出生年月", "身份证", "院系", "注册年份", "联系方式"};
	private DefaultTableModel tableUser = new DefaultTableModel(null,UserInfo);
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	
	//0：添加，1：修改
	private int type = 0;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox_2;
	private JTextField textField_1;
	
	//管理员修改用户时保存列表中用户完整信息
	private UserVo modiUser;
	@SuppressWarnings({ "rawtypes", "unused" })
	private DefaultComboBoxModel model = new DefaultComboBoxModel();
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox_1;
	
	/**
	 * Create the application.
	 */
	public UserManage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		setOpaque(false);
		setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(529, 32, 246, 65);
		panel_1.setOpaque(false);
		panel_1.setBorder(new TitledBorder(null, "得到用户", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblId = new JLabel("用户ID");
		lblId.setBounds(6, 28, 66, 15);
		panel_1.add(lblId);
		
		txtid = new JTextField();
		txtid.setBounds(72, 24, 89, 21);
		txtid.setText("");
		panel_1.add(txtid);
		txtid.setColumns(10);
		
		JButton button_1 = new JButton("");
		button_1.setBounds(173, 12, 50, 50);
		button_1.setIcon(new ImageIcon(UserManage.class.getResource("/image/ok.png")));
		button_1.setBorderPainted(false);
		button_1.setContentAreaFilled(false);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtid.getText();
				if(id.equals("")|| id==null)
					Methods.showTip("请输入用户账号");
				else{
					AdminBLService ad = new AdminController();
					UserVo v = ad.getUserById(id);
					
					if (v == null) {
						Methods.showTip("该用户不存在");
					}
					else {
						tableUser.setRowCount(0);
						tableUser.addRow(new Object[]{v.getID(),Methods.eToC(v.getRole()), v.getPassword(), v.getName(), v.getGender(), 
								v.getBirthday(), v.getIc(), v.getDepartment(), v.getRegisterYear(), v.getContact()});
					}
				}
				
				txtid.setText("");
			}
		});
		panel_1.add(button_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(529, 249, 246, 283);
		panel_2.setOpaque(false);
		panel_2.setBorder(new TitledBorder(null, "用户信息", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblid = new JLabel("用户ID");
		lblid.setBounds(11, 29, 53, 16);
		panel_2.add(lblid);
		
		txtid_1 = new JTextField();
		txtid_1.setEditable(false);
		txtid_1.setBounds(63, 27, 134, 22);
		panel_2.add(txtid_1);
		txtid_1.setColumns(10);
		
		JLabel label_1 = new JLabel("身份");
		label_1.setBounds(11, 65, 38, 16);
		panel_2.add(label_1);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setBounds(63, 60, 134, 27);
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"【请选择】", "教务处老师", "院系教务老师", "任课老师", "学生"}));
		comboBox_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String role = (String)comboBox_2.getSelectedItem();
				
				//根据用户角色初始化需填的信息
				if (role.equals("教务处老师")) {
					textField_1.setEditable(false);
				}
				if (role.equals("院系教务老师") || role.equals("任课老师")) {
					textField_1.setEditable(true);
				}
				if (role.equals("学生")) {
					textField_1.setEditable(true);
				}
			}
		});
		panel_2.add(comboBox_2);
		
		JLabel label_2 = new JLabel("姓名");
		label_2.setBounds(11, 100, 38, 16);
		panel_2.add(label_2);
		
		txtnamestring = new JTextField();
		txtnamestring.setEditable(false);
		txtnamestring.setBounds(63, 97, 134, 23);
		panel_2.add(txtnamestring);
		txtnamestring.setColumns(10);
		
		JLabel label_3 = new JLabel("密码");
		label_3.setBounds(11, 133, 34, 16);
		panel_2.add(label_3);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(64, 130, 134, 23);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("院系");
		lblNewLabel.setBounds(11, 166, 34, 15);
		panel_2.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(64, 163, 134, 21);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
				
		final JButton btns = new JButton("");
		btns.setIcon(new ImageIcon(UserManage.class.getResource("/image/publish.png")));
		btns.setToolTipText("点击提交");
		btns.setBorderPainted(false);
		btns.setBounds(99, 205, 50, 50);
		btns.setEnabled(false);
		btns.setContentAreaFilled(false);
		btns.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtid_1.getText();
				
				if (((String)comboBox_2.getSelectedItem()).equals("【请选择】")) {
					Methods.showTip("请选择用户身份");
					return;
				}
				String role = Methods.cToE((String)comboBox_2.getSelectedItem());
				String name = txtnamestring.getText();
				String pass = textField.getText();
				String de = textField_1.getText();
				
				if (id.length() == 0 || pass.length() == 0) {
					Methods.showTip("请输入完整信息");
					return;
				}
				
				String result;
				AdminBLService ad = new AdminController();
				switch(type){
				case 0:
					modiUser.setRole(role);
					modiUser.setDepartment(de);
					result = ad.modifyUser(modiUser);break;
				case 1:
					/*
					 * 根据用户的身份判断管理员信息是否添加完整
					 */
					if ((!role.equals("JW")) && de.length() == 0) {
						Methods.showTip("请输入完整信息");
						return;
					}
					UserVo adUser = new UserVo(id, role, pass, name, "", "", "", de, 0, "");
					result = ad.addUser(adUser);break;
				default:
					result = "";
				}
				btns.setEnabled(false);
				if(result.equals("")||result == null)
					;
				else{
					Methods.showTip(result);
				}
				
				//清空提交过的用户信息并恢复文本框为不可编辑
				txtid_1.setText("");
				txtid_1.setEditable(false);
				comboBox_2.setSelectedIndex(0);
				txtnamestring.setText("");
				txtnamestring.setEditable(false);
				textField.setText("");
				textField.setEditable(false);
				textField_1.setEditable(false);
				textField_1.setText("");
			}
		});
		panel_2.add(btns);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(529, 127, 246, 96);
		panel_3.setOpaque(false);
		panel_3.setBorder(new TitledBorder(null, "操作", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_3);
		panel_3.setLayout(null);
		
		JButton btna = new JButton("");
		btna.setBounds(19, 23, 66, 62);
		panel_3.add(btna);
		btna.setToolTipText("点击添加用户");
		btna.setIcon(new ImageIcon(UserManage.class.getResource("/image/add.png")));
		btna.setBorderPainted(false);
		
		JButton btnm = new JButton("");
		btnm.setBounds(90, 23, 66, 62);
		panel_3.add(btnm);
		btnm.setIcon(new ImageIcon(UserManage.class.getResource("/image/fill.png")));
		btnm.setToolTipText("点击修改用户");
		btnm.setBorderPainted(false);
		btnm.setContentAreaFilled(false);
		btnm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				
				/*
				 * 得到用户原始的各项信息，存于modiUser对象中
				 */
				String id = (String) table.getValueAt(row, 0);
				String role = (String) table.getValueAt(row, 1);
				String password = (String) table.getValueAt(row, 2);
				String name = (String) table.getValueAt(row, 3);
				String gender = (String) table.getValueAt(row, 4);
				String birth = (String) table.getValueAt(row, 5);
				String ic = (String) table.getValueAt(row, 6);
				String de = (String) table.getValueAt(row, 7);
				int grade = (Integer) table.getValueAt(row, 8);
				
				txtid_1.setText(id);
				comboBox_2.setSelectedItem(role);
				txtnamestring.setText(name);
				textField.setText(password);
				textField_1.setText(de);
				modiUser = new UserVo(id, role, password, name, gender, birth, ic, de, grade, "");
				
				type = 0;
				btns.setEnabled(true);
				
				/*
				 * 用户的ID、姓名、密码、年级（即注册年份）不允许更改
				 */
				txtid_1.setEditable(false);
				textField.setEditable(false);
				txtnamestring.setEditable(false);
				
				textField_1.setEditable(true);
			}
		});
		
		JButton btnd = new JButton("");
		btnd.setBounds(161, 23, 66, 62);
		
		btnd.setBorderPainted(false);
		btnd.setIcon(new ImageIcon(UserManage.class.getResource("/image/delete.png")));
		btnd.setToolTipText("点击删除用户");
		btnd.setContentAreaFilled(false);
		btnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				
				/*
				 * 根据要删除的用户角色执行不同的操作
				 */
				String role = Methods.cToE((String)table.getValueAt(row, 1));
				if (role.equals("Student") || role.equals("Teacher")) {
					deleteStudent(row);
				}
				/*if (role.equals("Teacher")) {
					deleteTeacher(row);
				}*/
				if (role.equals("YXJW") || role.equals("JW")) {
					deleteJW(row, role);
				}
			}
		});
		panel_3.add(btnd);
		
		btna.setContentAreaFilled(false);
		btna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type = 1;
				
				//清空原有信息
				txtid_1.setText("");
				comboBox_2.setSelectedIndex(0);
				txtnamestring.setText("");
				textField.setText("");
				textField_1.setText("");
				
				btns.setEnabled(true);
				txtid_1.setEditable(true);
				textField.setEditable(true);
				txtnamestring.setEditable(true);
				textField_1.setEditable(true);
			}
		});
		btna.setContentAreaFilled(false);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(6, 32, 515, 500);
		panel_5.setOpaque(false);
		panel_5.setBorder(new TitledBorder(null, "用户列表", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_5);
		panel_5.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 70, 489, 424);
		panel_5.add(scrollPane);
		
		table = new JTable();
		table.setModel(tableUser);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(6).setPreferredWidth(120);
		table.getColumnModel().getColumn(7).setPreferredWidth(100);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
		
		comboBox = new JComboBox();
		comboBox.setBounds(10, 30, 153, 27);
		panel_5.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"身份【全部】", "教务处老师", "院系教务老师", "任课老师", "学生"}));
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(192, 30, 147, 27);
		panel_5.add(comboBox_1);
		
		/*
		 * 初始化院系选项
		 */
		AdminBLService ad = new AdminController();
		ArrayList<String> de = ad.getDepartment();
		String[] department = new String[de.size()+1];
		department[0] = "院系【全部】";
		for (int i = 0; i < de.size(); i++) {
			department[i+1] = de.get(i);
		}
		
		comboBox_1.setModel(new DefaultComboBoxModel(department));
		
		ImageIcon i = new ImageIcon(UserManage.class.getResource("/image/ok.png"));
		JButton button1 = new JButton(i);
		button1.setBounds(376, 12, 50, 50);
		button1.setToolTipText("点击获取筛选结果");
		button1.setOpaque(false);
		button1.setBorderPainted(false);
		button1.setContentAreaFilled(false);
		panel_5.add(button1);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rowcount = tableUser.getRowCount();
				while(rowcount!=0){
					tableUser.removeRow(--rowcount);
				}
				String identity = (String) comboBox.getSelectedItem();
				String department = (String)comboBox_1.getSelectedItem();
				AdminBLService ad = new AdminController();
				ArrayList<UserVo> list;
				if(identity.equals("身份【全部】") && department.equals("院系【全部】")){
					list = ad.getUser();
				}
				else if (identity.equals("身份【全部】"))
					list = ad.getDeUser(department);
				else if (department.equals("院系【全部】"))
					list = ad.getUser(Methods.cToE(identity));
				else
					list = ad.getDeUser(department, Methods.cToE(identity));
				
				if(list != null && list.size() != 0) {
					for(int i = 0;i<list.size();i++){
						UserVo v = list.get(i);
						tableUser.addRow(new Object[]{v.getID(),Methods.eToC(v.getRole()), v.getPassword(), v.getName(), v.getGender(), 
								v.getBirthday(), v.getIc(), v.getDepartment(), v.getRegisterYear(), v.getContact()});
					}
				}
			}
		});
	}
	
	/**
	 * 学生：直接删除
	 * 任课老师，直接删除
	 */
	private void deleteStudent(int row) {
		AdminBLService ad = new AdminController();
		UserVo stu= new UserVo((String)table.getValueAt(row, 0),"","", "", "", "", "", "", 0, "");
		
		String result = ad.deleteUser(stu);
		Methods.showTip(result);
	}
	
	/**
	 * 删除教务处老师或院系教务老师：
	 * 若还有其他该角色的老师则直接删除，
	 * 否则要求用户添加一名新的老师，若不添加则不能删除
	 */
	private void deleteJW(final int row, String role) {
		AdminBLService ad = new AdminController();
		ArrayList<UserVo> user = ad.getUser(role);
		
		if (user.size() > 1) {
			String result = ad.deleteUser(new UserVo((String)table.getValueAt(row, 0),role,"", "", "", "", "", "", 0, ""));
			Methods.showTip(result);
		}
		else {
			JDialog tip = new JDialog();
		    tip.getContentPane().setLayout(null);
		    tip.setTitle("~~~");
		    tip.setSize(350, 200);
		    tip.setLocationRelativeTo(null);
			
			JTextArea area = new JTextArea("请先添加一名新的用户来接管该" + Methods.eToC(role) + "的\r\n工作再执行删除");
			area.setBounds(42, 44, 260, 60);
			tip.getContentPane().add(area);
			
			tip.setVisible(true);
		}
	}	
}
