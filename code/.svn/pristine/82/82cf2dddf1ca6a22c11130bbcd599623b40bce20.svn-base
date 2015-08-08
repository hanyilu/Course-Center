package presentation.mainui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import vo.UserVo;
import businesslogic.adminbl.AdminController;
import businesslogic.userbl.User;
import businesslogicservice.adminblservice.AdminBLService;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class PersonalInfo extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	
	AdminBLService ad = new AdminController();
	UserVo user = ad.getUserById(User.getUser().getUserID());
	
	public PersonalInfo() {
		setOpaque(false);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(135, 76, 74, 16);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(209, 74, 121, 21);
		textField.setEditable(false);
		add(textField);
		textField.setColumns(10);
		textField.setText(user.getID());
		
		JLabel lblNewLabel_1 = new JLabel("姓名");
		lblNewLabel_1.setBounds(422, 76, 73, 16);
		add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(507, 74, 108, 21);
		add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(user.getName());
		
		JLabel lblNewLabel_2 = new JLabel("性别");
		lblNewLabel_2.setBounds(135, 127, 98, 16);
		add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(209, 125, 121, 21);
		add(textField_2);
		textField_2.setColumns(10);
		textField_2.setText(user.getGender());
		
		JLabel lblNewLabel_3 = new JLabel("出生年月");
		lblNewLabel_3.setBounds(422, 127, 85, 16);
		add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(507, 125, 108, 21);
		add(textField_3);
		textField_3.setColumns(10);
		textField_3.setText(user.getBirthday());
		
		JLabel lblNewLabel_4 = new JLabel("身份证号");
		lblNewLabel_4.setBounds(135, 179, 74, 16);
		add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		textField_4.setBounds(209, 177, 121, 21);
		add(textField_4);
		textField_4.setColumns(10);
		textField_4.setText(user.getIc());
		
		JLabel lblNewLabel_5 = new JLabel("院系");
		lblNewLabel_5.setBounds(422, 179, 85, 16);
		add(lblNewLabel_5);
		
		textField_5 = new JTextField();
		textField_5.setBounds(507, 177, 108, 21);
		textField_5.setEditable(false);
		add(textField_5);
		textField_5.setColumns(10);
		textField_5.setText(user.getDepartment());
		
		JLabel lblNewLabel_6 = new JLabel("注册年份");
		lblNewLabel_6.setBounds(135, 228, 98, 16);
		add(lblNewLabel_6);
		
		textField_6 = new JTextField();
		textField_6.setBounds(209, 226, 121, 21);
		textField_6.setEditable(false);
		add(textField_6);
		textField_6.setColumns(10);
		textField_6.setText(user.getRegisterYear() + "");
		
		JLabel lblNewLabel_7 = new JLabel("联系方式");
		lblNewLabel_7.setBounds(422, 228, 85, 16);
		add(lblNewLabel_7);
		
		textField_7 = new JTextField();
		textField_7.setBounds(507, 226, 108, 21);
		add(textField_7);
		textField_7.setColumns(10);
		textField_7.setText(user.getContact() + "");
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(372, 290, 50, 50);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setIcon(new ImageIcon(PersonalInfo.class.getResource("/image/ok.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField_1.getText();
				String gen = textField_2.getText();
				String birth = textField_3.getText();
				String ic = textField_4.getText();
				String phone = (String)textField_7.getText();
				
				if ((user.getName().equals(name)) && (user.getGender().equals(gen)) && (user.getBirthday().equals(birth)) && 
						(user.getIc().equals(ic)) && (user.getContact().equals(phone))) {
					Methods.showTip("你没有修改任何信息");
				}
				else {
					user.setName(name);
					user.setGender(gen);
					user.setBirthday(birth);
					user.setIc(ic);
					user.setContact(phone);
					
					String result = ad.modifyUser(user);
					Methods.showTip(result);
				}
			}
		});
		add(btnNewButton);
	}
}
