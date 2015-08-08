package presentation.userui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import presentation.courseui.CourseAJW;
import presentation.mainui.ChangePassword;
import presentation.mainui.InfoPane;
import presentation.mainui.PersonalInfo;
import presentation.planui.PlanPane;

@SuppressWarnings("serial")
public class AJWTeacherPane extends JTabbedPane{

	/**
	 * Create the application.
	 */
	public AJWTeacherPane() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JPanel panel = new PlanPane();
		addTab("发布教学计划", null, panel, null);
		
		JTabbedPane tabbedPane = new CourseAJW();
		addTab("课程管理", null, tabbedPane, null);
		
		JPanel panel_1 = new InfoPane();
		addTab("查看信息", null, panel_1, null);
		
		JPanel panel_3 = new ChangePassword(0);
		addTab("修改密码", null, panel_3, null);
		
		JPanel info = new PersonalInfo();
		addTab("个人信息", null, info, null);
	}
}
