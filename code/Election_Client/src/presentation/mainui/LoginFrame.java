package presentation.mainui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.*;

import javax.swing.*;

import businesslogic.adminbl.AdminController;
import businesslogic.userbl.User;
import businesslogicservice.adminblservice.AdminBLService;
import businesslogicservice.userblservice.UserBLService;
import presentation.adminui.AdminPane;
import presentation.userui.*;

public class LoginFrame {
	private int xOld;
	private int yOld;

	private JFrame frmLogin;
	private JTextField textField;
	private JPasswordField passwordField;
	JLabel lblPasswordCannotBe = new JLabel("");
	JLabel lblIdCannotBe = new JLabel("");
	JLabel lblAnswer = new JLabel("");
	
	/**
	 * Launch the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		String lookAndFeel =UIManager.getCrossPlatformLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Font font = new Font("Libian SC",Font.PLAIN,15);
		Font font = new Font("微软雅黑", Font.PLAIN, 15);
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while(keys.hasMoreElements()){
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if(value instanceof javax.swing.plaf.FontUIResource){
				UIManager.put(key, font);
			}
		}
		
		UIManager.put("TabbedPane.contentOpaque", Boolean.FALSE);
		@SuppressWarnings("unused")
		LoginFrame m= new LoginFrame();
	}

	/**
	 * Create the application.
	 */
	public LoginFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Broad broad = new Broad();
		frmLogin = new JFrame();
		frmLogin.setTitle("JWSystem");
		frmLogin.setSize(440, 256);
		frmLogin.setLocationRelativeTo(null);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLayeredPane layeredPane = new JLayeredPane();
		frmLogin.getContentPane().add(layeredPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(0, 0, 440, 256);
		layeredPane.add(panel);
		panel.setLayout(null);
		
		JButton closeButton = new JButton();
        closeButton.setBounds(420, 0, 20, 20);
        closeButton.setContentAreaFilled(false);
        closeButton.setBorderPainted(false);
        closeButton.setIcon(new ImageIcon(LoginFrame.class.getResource("/image/close.png")));
        closeButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
	    });
        panel.add(closeButton);
		
		textField = new JTextField();
		textField.setBounds(159, 76, 146, 31);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		//lblId.setFont(new Font("Batang", Font.BOLD | Font.ITALIC, 15));
		lblId.setBounds(97, 85, 30, 15);
		panel.add(lblId);
		
		JLabel lblPassword = new JLabel("Password");
		//lblPassword.setFont(new Font("Batang", Font.BOLD | Font.ITALIC, 15));
		lblPassword.setBounds(68, 136, 79, 15);
		panel.add(lblPassword);
		
		JButton btnLogin = new JButton("");
		//btnLogin.setOpaque(false);
		btnLogin.setContentAreaFilled(false);
		
		btnLogin.setBorderPainted(false);
		btnLogin.setIcon(new ImageIcon(LoginFrame.class.getResource("/image/ok.png")));
		btnLogin.addActionListener(broad);
		btnLogin.setBounds(190, 184, 50, 50);
		panel.add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(159, 127, 146, 31);
		panel.add(passwordField);
		
		lblIdCannotBe.setForeground(Color.red);
		lblIdCannotBe.setBackground(new Color(240, 240, 240));
		lblIdCannotBe.setFont(new Font("SimSun", Font.PLAIN, 12));
		lblIdCannotBe.setLabelFor(lblId);
		lblIdCannotBe.setBounds(159, 85, 146, 15);
		panel.add(lblIdCannotBe);
		
		lblPasswordCannotBe.setForeground(Color.red);
		lblPasswordCannotBe.setFont(new Font("宋体", Font.PLAIN, 12));
		lblPasswordCannotBe.setBackground(SystemColor.menu);
		lblPasswordCannotBe.setBounds(159, 136, 146, 15);
		panel.add(lblPasswordCannotBe);
		
		lblAnswer.setForeground(Color.RED);
		lblAnswer.setBounds(50, 202, 340, 15);
		lblAnswer.setHorizontalAlignment(JTextField.CENTER);
		panel.add(lblAnswer);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 440, 256);
		layeredPane.add(panel_1);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(LoginFrame.class.getResource("/image/login.jpg")));
		panel_1.add(lblNewLabel);
		
		//frmLogin.pack();
		frmLogin.setUndecorated(true);
		frmLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xOld = e.getX();
				yOld = e.getY();
			}
		});
		frmLogin.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int xOnScreen = e.getXOnScreen();
				int yOnScreen = e.getYOnScreen();
				int xx = xOnScreen - xOld;
				int yy = yOnScreen - yOld;
				frmLogin.setLocation(xx, yy);
			}
		});
		
		frmLogin.setVisible(true);
	}
	
	class Broad implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String id = textField.getText();
			@SuppressWarnings("deprecation")
			String password = passwordField.getText();
			
			if(id.equals("admin")){
				
				//管理员登录
				AdminBLService ad = new AdminController();
				if(ad.login(password)){
					MainFrame m = new MainFrame(new AdminPane());
					m.show();
					frmLogin.dispose();
				}
				else{
					Methods.showTip("密码错误");
					
					//清空内容
					textField.setText("");
					passwordField.setText("");
				}
			}
			else {
				UserBLService ubls = new User();
				String result = ubls.login(id, password);
				if(!result.contains("success")) {
					Methods.showTip(result);
					
					//清空内容
					textField.setText("");
					passwordField.setText("");
				}
				else{
					MainFrame m;
					String role = result.split(" ")[0];
					if (role.equals("JW"))
						m = new MainFrame(new JWTeacherPane());
					else if (role.equals("YXJW"))
						m = new MainFrame(new AJWTeacherPane());
					else if (role.equals("Teacher"))
						m = new MainFrame(new TeacherPane());
					else //Student
						m = new MainFrame(new StudentPane());
					
					m.show();
					frmLogin.dispose();
				}
			}
		}
	}
}
