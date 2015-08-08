package presentation.adminui;

import javax.swing.*;

import presentation.controlui.ControlPane;
import presentation.mainui.ChangePassword;

@SuppressWarnings("serial")
public class AdminPane extends JTabbedPane{

	/**
	 * Create the application.
	 */
	public AdminPane() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JPanel user = new UserManage();
		addTab("用户管理",null,user,null);
		
		JPanel password = new ChangePassword(1);
		addTab("修改密码", null, password, null);
		JPanel control = new ControlPane();
		addTab("状态控制",null,control,null);
	}
}
