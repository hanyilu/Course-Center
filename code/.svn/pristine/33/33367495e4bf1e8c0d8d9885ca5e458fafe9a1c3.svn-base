package presentation.controlui;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JSpinner;

import java.awt.Color;

import javax.swing.SpinnerListModel;
import javax.swing.JButton;

import businesslogic.controlbl.Controller;
import businesslogicservice.controlblservice.ControllerBLService;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

import presentation.mainui.Methods;
import vo.ControlVo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class ControlPane extends JPanel{

	private JTextField year;
	private JSpinner term;
	@SuppressWarnings("rawtypes")
	private JComboBox state;
	private JLabel current;
	private ControllerBLService con;
	private JButton confirm;
	
	/**
	 * Create the application.
	 */
	public ControlPane() {
		con = new Controller();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		setOpaque(false);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 873, 119);
		panel.setOpaque(false);
		panel.setBorder(new TitledBorder(null, "\u5FEB\u6377\u63A7\u5236", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("当前状态");
		label.setBounds(196, 47, 72, 16);
		panel.add(label);
		
		JButton next = new JButton();
		next.setBounds(439, 63, 66, 50);
		next.setIcon(new ImageIcon(ControlPane.class.getResource("/image/next.png")));
		next.setBorderPainted(false);
		next.setContentAreaFilled(false);
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*
				 * 检测下一状态是否为新学年，若是则提醒管理员大四学生的数据会被删除
				 */
				int term = con.getTerm();
				int state = con.getState();
				if (term == 0 && state == 7) {	//下学期的假期进行中
					showTip();
					return;
				}
				con.advance();
				fill();
			}
		});
		panel.add(next);
		
		JButton before = new JButton();
		before.setBounds(335, 63, 50, 50);
		before.setIcon(new ImageIcon(ControlPane.class.getResource("/image/before.png")));
		before.setBorderPainted(false);
		before.setContentAreaFilled(false);
		before.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				con.back();
				fill();
			}
		});
		panel.add(before);
		
		current = new JLabel("");
		current.setBounds(335, 47, 282, 16);
		
		panel.add(current);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 119, 873, 488);
		panel_1.setOpaque(false);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "\u72B6\u6001\u63A7\u5236", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(panel_1);
		panel_1.setLayout(null);
		
		state = new JComboBox();
		state.setBounds(346, 189, 137, 27);
		state.setEnabled(false);
		state.setModel(new DefaultComboBoxModel(new String[] {"制定框架策略", "制定教学计划", "发布课程", "选课阶段", "补选退选", "学期进行中", "登记成绩", "假期进行中"}));
		panel_1.add(state);
		
		JLabel label_3 = new JLabel("当前学年");
		label_3.setBounds(239, 90, 77, 16);
		panel_1.add(label_3);
		
		year = new JTextField();
		year.setBounds(349, 84, 134, 28);
		year.setEditable(false);
		panel_1.add(year);
		year.setColumns(10);
		
		JLabel label_4 = new JLabel("进度控制");
		label_4.setBounds(239, 194, 77, 16);
		panel_1.add(label_4);
		
		confirm = new JButton("");
		confirm.setBounds(433, 274, 50, 50);
		confirm.setIcon(new ImageIcon(ControlPane.class.getResource("/image/ok.png")));
		confirm.setEnabled(false);
		confirm.setContentAreaFilled(false);
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String y = year.getText();
				String result = "学年格式出错";
				boolean b = false;
				int i = 0;
				
				/*
				 * 验证学年格式
				 */
				if(y.contains("~")){
					String[] sp = y.split("~");
					i = Integer.valueOf(sp[1]);
					if(Integer.valueOf(sp[0])==i-1) {
						b = true;
					}
				}
				if (!b) {
					Methods.showTip(result);
					return;
				}
				
				int j = term.getValue().equals("上")?1:2;
				int ter = i*10+j;
				int sta = state.getSelectedIndex();
				ControlVo vo = new ControlVo(ter,sta);
				
				/*
				 * 验证是否是新学年
				 */
				if (j == 1 && sta == 0) {
					showTip(vo);
					return;
				}
				result = con.change(vo);
				Methods.showTip(result);
				fill();
				
				if(result.equals("状态更改成功")){
					year.setText("");
					term.setValue("上");
					state.setSelectedIndex(0);
					year.setEditable(false);
					term.setEnabled(false);
					state.setEditable(false);
					confirm.setEnabled(false);
				}
			}
		});
		confirm.setBorderPainted(false);
		panel_1.add(confirm);
		
		term = new JSpinner();
		term.setBounds(352, 137, 42, 28);
		term.setEnabled(false);
		panel_1.add(term);
		term.setModel(new SpinnerListModel(new String[] {"\u4E0A", "\u4E0B"}));
		
		JLabel label_2 = new JLabel("学期");
		label_2.setBounds(394, 143, 26, 16);
		panel_1.add(label_2);
		
		JLabel label_5 = new JLabel("学期选择");
		label_5.setBounds(239, 143, 77, 16);
		panel_1.add(label_5);
		
		JLabel label_1 = new JLabel("如：2012~2013");
		label_1.setBounds(496, 90, 114, 16);
		panel_1.add(label_1);
	
		
		JButton modify = new JButton("");
		modify.setBounds(264, 274, 66, 50);
		modify.setIcon(new ImageIcon(ControlPane.class.getResource("/image/modify.png")));
		modify.setContentAreaFilled(false);
		modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				state.setEnabled(true);
				year.setEditable(true);
				term.setEnabled(true);
				confirm.setEnabled(true);
			}
		});
		modify.setBorderPainted(false);
		panel_1.add(modify);
		fill();
	}
	
	/**
	 * 提醒管理员新学年会删除毕业学生
	 */
	private void showTip() {
		// TODO Auto-generated method stub	
		final JDialog j = new JDialog();
		j.getContentPane().setLayout(null);
		j.setTitle("~~~");
	    j.setSize(450, 270);
	    j.setLocationRelativeTo(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setText("你确定要进入新学年并删除所有大四学生吗？\r\n此数据删除后将无法恢复");
		textArea.setBounds(91, 55, 270, 60);
		j.getContentPane().add(textArea);
		
		JButton btnNewButton = new JButton("取消");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				j.dispose();
			}
		});
		btnNewButton.setBounds(91, 179, 93, 23);
		j.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("确定");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				j.dispose();
				con.advance();
				fill();
			}
		});
		btnNewButton_1.setBounds(258, 179, 93, 23);
		j.getContentPane().add(btnNewButton_1);
		
		j.setVisible(true);
	}
	
	private void showTip(final ControlVo vo) {
		final JDialog j = new JDialog();
		j.getContentPane().setLayout(null);
		j.setTitle("~~~");
	    j.setSize(450, 270);
	    j.setLocationRelativeTo(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setText("你确定要进入新学年并删除所有大四学生吗？\r\n此数据删除后将无法恢复");
		textArea.setBounds(91, 55, 270, 60);
		j.getContentPane().add(textArea);
		
		JButton btnNewButton = new JButton("取消");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				j.dispose();
			}
		});
		btnNewButton.setBounds(91, 179, 93, 23);
		j.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("确定");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				j.dispose();
				String result = con.change(vo);
				fill();
				Methods.showTip(result);
				if(result.equals("状态更改成功")){
					year.setText("");
					term.setValue("上");
					state.setSelectedIndex(0);
					year.setEditable(false);
					term.setEnabled(false);
					state.setEditable(false);
					confirm.setEnabled(false);
				}
			}
		});
		btnNewButton_1.setBounds(258, 179, 93, 23);
		j.getContentPane().add(btnNewButton_1);
		
		j.setVisible(true);
	}

	private void fill(){
		current.setText(con.getCurrent());
	}
}
