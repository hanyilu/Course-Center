package presentation.planui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import businesslogic.planbl.PlanController;
import businesslogic.strategybl.StrategyController;
import businesslogic.userbl.User;
import businesslogicservice.planblservice.PlanBLService;
import businesslogicservice.strategyblservice.StrategyBLService;
import presentation.mainui.Methods;
import vo.PlanVo;
import vo.StrategyVo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class PlanPane extends JPanel{
	private JTable table;
	private JTextField courseNo;
	private JTextField courseName;
		
	private String[] planColumn = new String[] {"院系", "课程号", "课程名", "课程模块", "课程分类", "课程性质", "课程学分","开设学期", "周学时"};
	private DefaultTableModel tablePlan = new DefaultTableModel(null,planColumn);
	@SuppressWarnings("rawtypes")
	private JComboBox module;
	@SuppressWarnings("rawtypes")
	private JComboBox category;
	private JSpinner credit;
	private JSpinner weekHour;
	private JButton btns;
	private int type = -1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton op;
	private JRadioButton comp;
	private JSpinner term;
	
	/**
	 * Create the application.
	 */
	public PlanPane() {
		setOpaque(false);
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		setTablePlan();
		
		setLayout(null);
		
		JPanel panel_6 = new JPanel();
		panel_6.setOpaque(false);
		panel_6.setBorder(new TitledBorder(null, "教学计划", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_6.setBounds(23, 30, 466, 482);
		add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_6.add(scrollPane);
		
		table = new JTable();
		table.setModel(tablePlan);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
		
		JPanel panel_5 = new JPanel();
		panel_5.setOpaque(false);
		panel_5.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "填写信息", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_5.setBounds(514, 148, 226, 299);
		add(panel_5);
		panel_5.setLayout(null);
		
		JLabel label_8 = new JLabel("课程号");
		label_8.setBounds(24, 33, 52, 16);
		panel_5.add(label_8);
		
		courseNo = new JTextField();
		courseNo.setEditable(false);
		courseNo.setBounds(86, 27, 134, 28);
		panel_5.add(courseNo);
		courseNo.setColumns(10);
		
		JLabel label_9 = new JLabel("课程名");
		label_9.setBounds(24, 66, 52, 16);
		panel_5.add(label_9);
		
		courseName = new JTextField();
		courseName.setEditable(false);
		courseName.setBounds(86, 60, 134, 28);
		panel_5.add(courseName);
		courseName.setColumns(10);
		
		JLabel label_10 = new JLabel("课程模块");
		label_10.setBounds(24, 99, 74, 16);
		panel_5.add(label_10);
		
		module = new JComboBox();
		module.setBounds(86, 95, 134, 27);
		module.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initCatComboBox();
			}
		});
		initMoComboBox();
		panel_5.add(module);
		
		JLabel label_11 = new JLabel("课程分类");
		label_11.setBounds(24, 132, 74, 16);
		panel_5.add(label_11);
		
		category = new JComboBox();
		category.setBounds(86, 128, 134, 27);
		category.setModel(new DefaultComboBoxModel(new String[] {"【请指定】"}));
		category.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initOther();
			}
		});
		panel_5.add(category);
		
		JLabel label_12 = new JLabel("课程性质");
		label_12.setBounds(24, 165, 74, 16);
		panel_5.add(label_12);
		
		JLabel label_13 = new JLabel("课程学分");
		label_13.setBounds(24, 198, 74, 16);
		panel_5.add(label_13);
		
		credit = new JSpinner();
		credit.setBounds(86, 193, 41, 28);
		credit.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		panel_5.add(credit);
		
		JLabel label_14 = new JLabel("周学时");
		label_14.setBounds(24, 264, 74, 16);
		panel_5.add(label_14);
		
		weekHour = new JSpinner();
		weekHour.setBounds(86, 258, 41, 28);
		weekHour.setModel(new SpinnerNumberModel(1, 1, 4, 1));
		panel_5.add(weekHour);
		
		comp = new JRadioButton("必修");
		buttonGroup.add(comp);
		comp.setBounds(81, 158, 74, 23);
		panel_5.add(comp);
		
		op = new JRadioButton("选修");
		buttonGroup.add(op);
		op.setBounds(151, 158, 141, 23);
		panel_5.add(op);
		
		/*
		 * 必修选修性质只能依据课程模块、分类的改变而改变，不允许手动改变
		 */
		comp.setEnabled(false);
		op.setEnabled(false);
		
		JLabel lblNewLabel = new JLabel("开设学期");
		lblNewLabel.setBounds(24, 231, 74, 16);
		panel_5.add(lblNewLabel);
		
		term = new JSpinner();
		term.setModel(new SpinnerNumberModel(1, 1, 8, 1));
		term.setBounds(86, 225, 41, 28);
		panel_5.add(term);
		
		JPanel panel_61 = new JPanel();
		panel_61.setOpaque(false);
		panel_61.setBorder(new TitledBorder(null, "操作", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_61.setBounds(514, 30, 226, 94);
		add(panel_61);
		
		JButton btna = new JButton("");
		btna.setBorderPainted(false);
		btna.setContentAreaFilled(false);
		btna.setIcon(new ImageIcon(PlanPane.class.getResource("/image/add.png")));
		btna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type = 0;
				btns.setEnabled(true);
				
				//清空原有信息
				courseNo.setText("");
				courseName.setText("");
				module.setSelectedIndex(0);
				category.setSelectedIndex(0);
				comp.setSelected(false);
				op.setSelected(false);
				term.setValue(1);
				credit.setValue(1);
				weekHour.setValue(1);
				
				courseNo.setEditable(true);
				courseName.setEditable(true);
			}
		});
		panel_61.add(btna);
		
		JButton btnm = new JButton("");
		btnm.setContentAreaFilled(false);
		btnm.setIcon(new ImageIcon(PlanPane.class.getResource("/image/fill.png")));
		btnm.setBorderPainted(false);
		btnm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row == -1) {
					Methods.showTip("请选择要修改的信息");
					return;
				}
				courseNo.setText((String) table.getValueAt(row, 1));
				courseNo.setEditable(false);
				courseName.setText((String) table.getValueAt(row, 2));
				courseName.setEditable(false);
				module.setSelectedItem((String)table.getValueAt(row, 3));
				category.setSelectedItem((String)table.getValueAt(row, 4));
				String pro = (String)table.getValueAt(row, 5);
				if(pro.equals("必修"))
					comp.setSelected(true);
				else
					op.setSelected(false);
				credit.setValue(table.getValueAt(row, 6));
				term.setValue(table.getValueAt(row, 7));
				weekHour.setValue(table.getValueAt(row, 8));
				type = 1;
				btns.setEnabled(true);
			}
		});
		panel_61.add(btnm);
		
		btns = new JButton("");
		btns.setContentAreaFilled(false);
		btns.setIcon(new ImageIcon(PlanPane.class.getResource("/image/publish.png")));
		btns.setBorderPainted(false);
		btns.setBounds(606, 462, 50, 50);
		add(btns);
		btns.setEnabled(false);
		btns.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mo = (String)module.getSelectedItem();
				String ca = (String)category.getSelectedItem();
				
				String pro = "必修";
				if(op.isSelected())
					pro = "选修";
				
				String cNo = courseNo.getText();
				String cName = courseName.getText();
				if (mo.equals("【请指定】") || ca.equals("【请指定】")|| cNo.length() == 0 || 
						cName.length() == 0) {
					Methods.showTip("请将信息填写完整再提交");
					return;
				}
				else {
					String result;
					PlanVo plan = new PlanVo(mo, ca, pro, cNo, cName, User.getUser().getUserDepartment(), 
							(Integer)credit.getValue(), (Integer)weekHour.getValue(),(Integer)term.getValue());
					PlanBLService controller = new PlanController();

					switch (type) {
					case 0:result = controller.setPlan(plan);break;
					case 1:result = controller.updatePlan(plan);break;
					default:result = "Please choose A or W";break;
					}
					
					btns.setEnabled(false);
					type = -1;

					Methods.showTip(result);
					
					setTablePlan();
					table.setModel(tablePlan);
					table.invalidate();
				}
				
				//清空所填写的信息
				courseNo.setText("");
				courseNo.setEditable(false);
				courseName.setText("");
				courseName.setEditable(false);
				module.setSelectedIndex(0);
				category.setSelectedIndex(0);
				comp.setSelected(false);
				op.setSelected(false);
				credit.setValue(1);
				weekHour.setValue(1);
				term.setValue(1);
			}
		});
	}
	
	private void initOther() {
		// TODO Auto-generated method stub
		StrategyBLService strategy = new StrategyController();
		ArrayList<StrategyVo> str = strategy.getStrategy();
		if(str == null)
			return;
			
		String mo = (String)module.getSelectedItem();
		String cate = (String)category.getSelectedItem();
		
		for(int i = 0; i < str.size(); i++){
			if(str.get(i).getModule().equals(mo) && str.get(i).getCategory().equals(cate)){
				String pro = str.get(i).getProperty();
				if(pro.equals("必修"))
					comp.setSelected(true);
				else
					op.setSelected(true);
				credit.setModel(new SpinnerNumberModel(str.get(i).getMinCredit(), str.get(i).getMinCredit(), str.get(i).getMaxCredit(), 1));
				term.setModel(new SpinnerNumberModel(str.get(i).getMinTerm(), str.get(i).getMinTerm(), str.get(i).getMaxTerm(), 1));
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initMoComboBox() {
		// TODO Auto-generated method stub
		StrategyBLService strategy = new StrategyController();
		ArrayList<StrategyVo> str = strategy.getStrategy();
		if (str == null)
			return;
		
		ArrayList<String> mo = new ArrayList<String>();
		for(int i = 0; i < str.size(); i++){
			String con = str.get(i).getModule();
			if(mo.indexOf(con) < 0)
				mo.add(con);
		}
		String[] modu = new String[mo.size()+1];
		modu[0] = "【请指定】";
		for(int j = 0; j < mo.size(); j++){
			modu[j+1] = mo.get(j);
		}
		module.setModel(new DefaultComboBoxModel(modu));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initCatComboBox() {
		// TODO Auto-generated method stub
		StrategyBLService strategy = new StrategyController();
		ArrayList<StrategyVo> str = strategy.getStrategy();
		if(str == null)
			return;
			
		String mo = (String)module.getSelectedItem();
		ArrayList<String> cat = new ArrayList<String>();
		for(int i = 0; i < str.size(); i++){
			if(str.get(i).getModule().equals(mo)){
				String con = str.get(i).getCategory();
				if(cat.indexOf(con) < 0)
					cat.add(con);
			}
		}
		String[] cate = new String[cat.size() + 1];
		cate[0] = "【请指定】";
		for(int j = 0; j < cat.size(); j++){
			cate[j+1] = cat.get(j);
		}
		category.setModel(new DefaultComboBoxModel(cate));
	}

	private void setTablePlan() {
		// TODO Auto-generated method stub
		tablePlan.setRowCount(0);
		PlanBLService p = new PlanController();
		ArrayList<PlanVo> list = p.getPlan(User.getUser().getUserDepartment());
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				PlanVo v = list.get(i);
				tablePlan.addRow(new Object[] {v.getDepartment(), v.getCourseNo(), v.getCourseName(), v.getModule(), v.getCategory(),
						v.getProperty(), v.getCredit(), v.getTerm(),v.getWeekHour() });
			}
		}	
	}
}
