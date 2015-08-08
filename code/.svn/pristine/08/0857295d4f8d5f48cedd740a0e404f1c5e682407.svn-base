package presentation.userui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import presentation.courseui.CourseCom;
import presentation.mainui.ChangePassword;
import presentation.mainui.InfoPane;
import presentation.mainui.PersonalInfo;
import presentation.scoreui.ScoreRec;

@SuppressWarnings("serial")
public class TeacherPane extends JTabbedPane{	
	/**
	 * Create the application.
	 */
	public TeacherPane() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JPanel panel = new CourseCom();
		addTab("填写课程信息", null, panel, null);
		
		JPanel panel_1 = new ScoreRec();
		addTab("登记成绩", null, panel_1, null);
		
		JPanel panel_2 = new InfoPane();
		addTab("查看信息", null, panel_2, null);
		
		JPanel panel_5 = new ChangePassword(0);
		addTab("修改密码", null, panel_5, null);
		
		JPanel info = new PersonalInfo();
		addTab("个人信息", null, info, null);
	}
}
