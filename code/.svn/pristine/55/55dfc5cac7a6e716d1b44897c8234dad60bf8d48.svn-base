package presentation.courseui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class CourseAJW extends JTabbedPane{

	/**
	 * Create the application.
	 */
	public CourseAJW() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JPanel panel = new CoursePublish();
		addTab("发布课程", null, panel, null);
		
		JPanel panel_1 = new CourseModify();
		addTab("修改课程", null, panel_1, null);
	}
}