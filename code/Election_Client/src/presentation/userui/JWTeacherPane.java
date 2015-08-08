package presentation.userui;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;

import presentation.mainui.ChangePassword;
import presentation.mainui.InfoPane;
import presentation.mainui.PersonalInfo;
import presentation.strategyui.StrategyPane;

@SuppressWarnings("serial")
public class JWTeacherPane extends JTabbedPane{
	
	/**
	 * Create the application.
	 */
	public JWTeacherPane() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JPanel panel = new StrategyPane();
		addTab("整体框架策略管理", null, panel, null);

		JPanel panel_1 = new InfoPane();
		addTab("查看信息", null, panel_1, null);

		JPanel panel_3 = new ChangePassword(0);
		addTab("修改密码", null, panel_3, null);
		
		JPanel info = new PersonalInfo();
		addTab("个人信息", null, info, null);
	}
}
