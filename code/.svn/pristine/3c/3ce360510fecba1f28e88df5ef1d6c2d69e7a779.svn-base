package presentation.mainui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Methods {
	static JDialog tip;
	static int xOld;
	static int yOld;

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void showTip(String result) {
		tip = new JDialog();
		xOld=0;
		yOld=0;
		JLayeredPane layeredPane = new JLayeredPane();
		
		tip.setTitle("~~~");
		tip.setSize(350, 200);
		tip.setLocationRelativeTo(null);
		
		tip.getContentPane().add(layeredPane);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 350, 200);
		layeredPane.add(panel);
		JLabel label = new JLabel(result);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton closeButton = new JButton();
		closeButton.setContentAreaFilled(false);
		closeButton.setBorderPainted(false);
        closeButton.setBounds(330, 0, 20, 20);
        closeButton.setIcon(new ImageIcon(LoginFrame.class.getResource("/image/close.png")));
        closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tip.dispose();
			}
	    });
        panel.add(closeButton);
		
		panel.setOpaque(false);
		panel.setLayout(new BorderLayout(0, 0));
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 400, 200);
		layeredPane.add(panel_1);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(LoginFrame.class.getResource("/image/dialog.jpg")));
		panel_1.add(lblNewLabel);
		
		tip.setUndecorated(true);
		tip.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xOld = e.getX();
				yOld = e.getY();
			}
		});
		tip.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int xOnScreen = e.getXOnScreen();
				int yOnScreen = e.getYOnScreen();
				int xx = xOnScreen - xOld;
				int yy = yOnScreen - yOld;
				tip.setLocation(xx, yy);
			}
		});
		
		tip.setVisible(true);
	}
	
	public static String cToE(String identity){
		if (identity.equals("教务处老师")){
			return"JW";
		}
		else if (identity.equals("院系教务老师"))
			return"YXJW";
		else if (identity.equals("任课老师"))
			return"Teacher";
		else //学生
			return"Student";
		
	}
	
	public static String eToC(String identity){
		if (identity.equals("JW")){
			return "教务处老师";
		}
		else if (identity.equals("YXJW"))
			return"院系教务老师";
		else if (identity.equals("Teacher"))
			return"任课老师";
		else //学生
			return"学生";	
	}
}
