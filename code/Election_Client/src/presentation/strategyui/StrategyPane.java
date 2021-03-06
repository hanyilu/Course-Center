package presentation.strategyui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

import java.awt.Color;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import businesslogic.strategybl.StrategyController;
import businesslogicservice.strategyblservice.StrategyBLService;
import presentation.mainui.Methods;
import vo.StrategyVo;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class StrategyPane extends JPanel{
	
	private JTable table;
	private JTextField module;
	private JTextField category;
	private JSpinner minCredit;
	private JSpinner maxCredit;
	private int type = -1;
	private JButton btns;
	
	private String[] column = new String[] {"课程模块", "课程分类", "课程性质", "开设学期", "学分范围"};
	private DefaultTableModel tableMo = new DefaultTableModel(null,column);
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton radioButton;
	private JRadioButton radioButton_1;
	private JSpinner maxTerm;
	private JSpinner minTerm;

	/**
	 * Create the application.
	 */
	public StrategyPane() {
		setOpaque(false);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTableMo();
		setLayout(null);

		JPanel panel_6 = new JPanel();
		panel_6.setOpaque(false);
		panel_6.setBorder(new TitledBorder(null, "操作", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setBounds(512, 54, 226, 96);
		add(panel_6);

		JButton btna = new JButton("");
		btna.setBorderPainted(false);
		btna.setContentAreaFilled(false);
		btna.setIcon(new ImageIcon(StrategyPane.class.getResource("/image/add.png")));
		btna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type = 0;
				btns.setEnabled(true);
				
				module.setText("");
				module.setEditable(true);
				category.setText("");
				category.setEditable(true);
				radioButton.setSelected(true);
				minTerm.setValue(1);
				maxTerm.setValue(1);
				
				minCredit.setValue(1);
				maxCredit.setValue(1);
				
				module.setEditable(true);
				category.setEditable(true);
			}
		});
		panel_6.add(btna);
		
		JButton btnm = new JButton("");
		btnm.setBorderPainted(false);
		btnm.setContentAreaFilled(false);
		btnm.setIcon(new ImageIcon(StrategyPane.class.getResource("/image/modify.png")));
		btnm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				module.setText((String)table.getValueAt(row, 0));
				module.setEditable(false);
				category.setText((String)table.getValueAt(row, 1));
				category.setEditable(false);
				if(((String)table.getValueAt(row, 2)).equals("选修")){
					radioButton_1.setSelected(true);
				}
				else{
					radioButton.setSelected(true);
				}
				String credit = (String)table.getValueAt(row, 4);
				String term = (String)table.getValueAt(row, 3);
				if(credit.contains("~")){
					String[] sp =credit.split("~");
					minCredit.setValue(Integer.parseInt(sp[0]));
					maxCredit.setValue(Integer.parseInt(sp[1]));
				}
				else{
					minCredit.setValue(Integer.parseInt(credit));
					maxCredit.setValue(Integer.parseInt(credit));
				}
				if(term.contains("~")){
					String[] sp = term.split("~");
					minTerm.setValue(Integer.parseInt(sp[0]));
					maxTerm.setValue(Integer.parseInt(sp[1]));
				}
				else{
					minTerm.setValue(Integer.parseInt(term));
					maxTerm.setValue(Integer.parseInt(term));
				}
				
				type = 1;
				btns.setEnabled(true);
			}
		});
		panel_6.add(btnm);

		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		panel_4.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "整体框架策略列表", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_4.setBounds(23, 30, 466, 465);
		add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_4.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"\u8BFE\u7A0B\u6A21\u5757", "\u8BFE\u7A0B\u5206\u7C7B", "\u8BFE\u7A0B\u6027\u8D28", "\u5F00\u8BBE\u5B66\u671F", "\u5B66\u5206\u8303\u56F4"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);

		JPanel panel_5 = new JPanel();
		panel_5.setOpaque(false);
		panel_5.setBounds(512, 179, 226, 212);
		panel_5.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "模块信息", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_5.setLayout(null);
		add(panel_5);	

		JLabel label = new JLabel("课程模块");
		label.setBounds(24, 33, 79, 16);
		panel_5.add(label);	

		module = new JTextField();
		module.setEditable(false);
		module.setBounds(86, 27, 134, 28);
		panel_5.add(module);
		module.setColumns(10);	

		JLabel label_1 = new JLabel("课程分类");
		label_1.setBounds(24, 66, 64, 16);
		panel_5.add(label_1);	

		category = new JTextField();
		category.setEditable(false);
		category.setBounds(86, 60, 134, 28);
		panel_5.add(category);
		category.setColumns(10);

		JLabel label_2 = new JLabel("课程性质");
		label_2.setBounds(24, 99, 79, 16);
		panel_5.add(label_2);

		JLabel label_3 = new JLabel("开设学期");
		label_3.setBounds(24, 132, 64, 16);
		panel_5.add(label_3);

		JLabel label_4 = new JLabel("学分范围");
		label_4.setBounds(24, 165, 64, 16);
		panel_5.add(label_4);

		minCredit = new JSpinner();
		minCredit.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		minCredit.setBounds(86, 159, 37, 28);
		panel_5.add(minCredit);

		JLabel label_5 = new JLabel("~");
		label_5.setBounds(133, 163, 8, 16);
		panel_5.add(label_5);

		maxCredit = new JSpinner();
		maxCredit.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		maxCredit.setBounds(161, 159, 37, 28);
		panel_5.add(maxCredit);	
		
		radioButton = new JRadioButton("必修");
		buttonGroup.add(radioButton);
		radioButton.setBounds(86, 95, 64, 23);
		panel_5.add(radioButton);
		
		radioButton_1 = new JRadioButton("选修");
		buttonGroup.add(radioButton_1);
		radioButton_1.setBounds(151, 95, 69, 23);
		panel_5.add(radioButton_1);
		radioButton.setSelected(true);
		
		JLabel label_6 = new JLabel("~");
		label_6.setBounds(133, 132, 19, 16);
		panel_5.add(label_6);
		
		minTerm = new JSpinner();
		minTerm.setModel(new SpinnerNumberModel(1, 1, 8, 1));
		minTerm.setBounds(86, 126, 37, 28);
		panel_5.add(minTerm);
		
		maxTerm = new JSpinner();
		maxTerm.setModel(new SpinnerNumberModel(1, 1, 8, 1));
		maxTerm.setBounds(161, 126, 37, 28);
		panel_5.add(maxTerm);
		
		btns = new JButton("");
		btns.setIcon(new ImageIcon(StrategyPane.class.getResource("/image/publish.png")));
		btns.setBounds(601, 403, 50, 50);
		btns.setBorderPainted(false);
		btns.setContentAreaFilled(false);
		add(btns);
		btns.setEnabled(false);
		btns.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mo = module.getText();
				String ca = category.getText();
				String pro = "必修";
				if(radioButton_1.isSelected()){
					pro = "选修";
				}
				
				int maxT = (Integer)maxTerm.getValue();
				int minT = (Integer)minTerm.getValue();
				if (minT > maxT) {
					Methods.showTip("最低学期应小于最高学期");
					return;
				}
				
				/*
				 * 选修课程的开设学期只可选择1或2（上、下学期）
				 */
				if (pro.equals("选修") && (minT > 2 || maxT > 2)) {
					Methods.showTip("选修课程的开设学期应为1或2");
					return;
				}
				
				int maxC = (Integer)maxCredit.getValue();
				int minC = (Integer)minCredit.getValue();
				if (minC > maxC) {
					Methods.showTip("最低学分应小于最高学分");
					return;
				}
				
				if (mo.length() == 0 || ca.length() == 0 ) {
					Methods.showTip("请将信息填写完整再提交");
					return;
				}
				
				StrategyVo strategy = new StrategyVo(mo,ca,pro, maxC, minC,maxT, minT);
				StrategyBLService controller = new StrategyController();
				String result;

				switch (type) {
				case 0:result = controller.setStrategy(strategy);break;
				case 1:result = controller.updateStrategy(strategy);break;
				default:result = "Please choose A or W";break;
				}
				
				String output = copeResult(result);
				Methods.showTip(output);
				module.setEditable(true);
				category.setEditable(true);

				btns.setEnabled(false);
				type = -1;

				setTableMo();
				table.setModel(tableMo);
				table.invalidate();
				
				//清空提交的信息
				module.setText("");
				module.setEditable(false);
				category.setText("");
				category.setEditable(false);
				radioButton.setSelected(true);
				
				minTerm.setValue(1);
				maxTerm.setValue(1);
				minCredit.setValue(1);
				maxCredit.setValue(1);
			}
		});
	}
	
	private String copeResult(String result) {
		// TODO Auto-generated method stub
		if(result.equals("success")){
			return "操作成功";
		}
		else if(result.equals("k_success")){
			return "操作成功，但有些项没有填";
		}
		else if(result.equals("null")){
			return "有空白项";
		}
		else if(result.equals("module_null")){
			return "课程模块、分类即性质不能为空";
		}
		else if(result.equals("exist")) {
			return "该框架策略信息已存在";
		}
		else if(result.equals("error"))
			return "啊哦~有错误~";
		else
			return result;
	}

	private void setTableMo() {
		tableMo.setRowCount(0);
		
		StrategyBLService s = new StrategyController();
		ArrayList<StrategyVo> strategy = s.getStrategy();
		if (strategy != null && strategy.size() != 0) {
			
			// 填充数据
			for (int i = 0; i < strategy.size(); i++) {
				Object[] arr = new Object[6];
			    arr[0] = strategy.get(i).getModule();
			    arr[1] = strategy.get(i).getCategory();
			    arr[2] = strategy.get(i).getProperty();
			    int minT = strategy.get(i).getMinTerm();
			    int maxT = strategy.get(i).getMaxTerm();
			    
			    String term = String.valueOf(minT);
			    if (minT!=maxT)
			    	term = term + "~" + maxT;
			    arr[3] = term;
			    int minC = strategy.get(i).getMinCredit();
			    int maxC = strategy.get(i).getMaxCredit();
			    String credit = String.valueOf(minC);
			    if (minC!=maxC)
			    	credit = credit + "~" + maxC;
			    arr[4] = credit;
			    
			    // 添加数据到表格
			    tableMo.addRow(arr);
			}
		}
	}
}
