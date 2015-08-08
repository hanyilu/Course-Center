package presentation.userui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import presentation.electionui.*;
import presentation.mainui.ChangePassword;
import presentation.mainui.InfoPane;
import presentation.mainui.PersonalInfo;
import presentation.scoreui.ScoreAnal;

@SuppressWarnings("serial")
public class StudentPane extends JTabbedPane{

	/**
	 * Create the application.
	 */
	public StudentPane() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		JPanel panel = new ElectionSelect();
		addTab("选课", null, panel, null);
		
		JPanel panel_1 = new ElectionQuit();
		addTab("退选", null, panel_1, null);
		
		JPanel buxuan = new ElectionCom();
		addTab("补选", null, buxuan, null);
		
		JPanel panel_3 = new ScoreAnal();
		addTab("成绩分析", null, panel_3, null);
		
		JPanel panel_2 = new InfoPane();
		addTab("查看信息", null, panel_2, null);
		
		JPanel panel_5 = new ChangePassword(0);
		addTab("修改密码", null, panel_5, null);
		
		JPanel info = new PersonalInfo();
		addTab("个人信息", null, info, null);
	}
}
