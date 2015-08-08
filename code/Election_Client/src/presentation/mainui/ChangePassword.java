package presentation.mainui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import businesslogic.adminbl.AdminController;
import businesslogic.userbl.User;
import businesslogicservice.adminblservice.AdminBLService;
import javax.swing.ImageIcon;
import java.awt.Font;

@SuppressWarnings("serial")
public class ChangePassword extends JPanel{
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private int type;

	/**
	 * Create the application.
	 */
	public ChangePassword(int i){
		type = i;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setOpaque(false);
		setLayout(null);
		
		JPanel change = new JPanel();
		change.setOpaque(false);
		change.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "修改密码", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		change.setBounds(122, 100, 528, 275);
		add(change);
		change.setLayout(null);
		
		JButton btnConfirm = new JButton("");
		btnConfirm.setBorderPainted(false);
		btnConfirm.setContentAreaFilled(false);
		btnConfirm.setIcon(new ImageIcon(ChangePassword.class.getResource("/image/ok.png")));
		btnConfirm.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				String result="出错了呢~";
				if(type==0){
					User user = User.getUser();
					result= user.changePassword(passwordField.getText(), passwordField_1.getText(), passwordField_2.getText());
				}
				else if(type==1){
					AdminBLService ad = new AdminController();
					result= ad.changePassword(passwordField.getText(), passwordField_1.getText(), passwordField_2.getText());
				}
				Methods.showTip(result);
				passwordField.setText("");
				passwordField_1.setText("");
				passwordField_2.setText("");
			}
		});
		
		btnConfirm.setBounds(235, 189, 50, 50);
		change.add(btnConfirm);
		
		JLabel lblOldPassword = new JLabel("旧密码");
		lblOldPassword.setBounds(123, 48, 90, 15);
		change.add(lblOldPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(236, 45, 107, 21);
		change.add(passwordField);
		
		JLabel lblNewPassword = new JLabel("新密码");
		lblNewPassword.setBounds(123, 92, 101, 15);
		change.add(lblNewPassword);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(236, 130, 107, 21);
		change.add(passwordField_1);
		
		JLabel lblConfirmPassword = new JLabel("重复新密码");
		lblConfirmPassword.setBounds(123, 133, 120, 15);
		change.add(lblConfirmPassword);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(236, 89, 107, 21);
		change.add(passwordField_2);
		
		JLabel changeInfo = new JLabel();
		changeInfo.setBounds(100,230,300,23);
		changeInfo.setForeground(Color.red);
		change.add(changeInfo);
	}
}
