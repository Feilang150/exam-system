package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

import DAL.FileHelper;
import model.AgeValueExcepton;
import model.student;
import ui.Login_GUI.LoginActionListener;

public class Register_GUI extends JFrame implements ActionListener, KeyListener, FocusListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int WIDTH = 300, HEIGHT = 370;
	private JLabel titleLbl, nameLbl, pwdLbl, cfpwdLbl, genderLbl, ageLbl, classLbl;
	private JTextField nameField, ageField;
	private JPasswordField pwdField, cfpwdField;
	private JButton regBtn, resetBtn, cancelBtn;// 3个按钮
	JRadioButton rbtn1, rbtn2;
	ButtonGroup btnGroup;
	JPanel panel;
	String[] s = { "计技231", "计技232", "计技233", "计技234", "计技235" };
	JComboBox<String> classBox;
	Box[] boxes = new Box[8];
	LoginActionListener actionLIstener;// 动作事件的事件处理者（事件监听器的类事件）
	// 构造方法

	public Register_GUI(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
		this.getContentPane().setBackground(new Color(55, 166, 215));
		titleLbl = new JLabel("用户注册");
		this.titleLbl.setForeground(Color.BLUE);
		this.titleLbl.setFont(new Font("微软雅黑", Font.BOLD, 20));
		nameLbl = new JLabel("用  户  名：");
		pwdLbl = new JLabel("密        码：");
		cfpwdLbl = new JLabel("确认密码： ");
		genderLbl = new JLabel("性        别：");
		ageLbl = new JLabel("年        龄：");
		classLbl = new JLabel("所属班级：");
		this.nameLbl.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		this.pwdLbl.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		this.cfpwdLbl.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		this.genderLbl.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		this.ageLbl.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		this.classLbl.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		nameField = new JTextField(14);
		pwdField = new JPasswordField(14);
		cfpwdField = new JPasswordField(14);
		ageField = new JTextField(14);

		rbtn1 = new JRadioButton("男");
		rbtn2 = new JRadioButton("女");
		panel = new JPanel();
		panel.add(rbtn1);
		panel.add(rbtn2);
		btnGroup = new ButtonGroup();
		btnGroup.add(rbtn1);
		btnGroup.add(rbtn2);
		rbtn1.setSelected(true);
		classBox = new JComboBox<String>(s);
		regBtn = new JButton("注册");
		resetBtn = new JButton("重置");
		cancelBtn = new JButton("取消");
		this.regBtn.addActionListener(this);
		this.resetBtn.addActionListener(this);
		this.cancelBtn.addActionListener(this);

		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				// 空方法体
			}

			@Override
			public void windowClosing(WindowEvent e) {// 窗口（事件源）正在关闭<窗口事件>时的事件处理方法
				// TODO Auto-generated method stub
				int r;
				r = JOptionPane.showConfirmDialog(null, "你确认要关闭吗？", "确认对话框", JOptionPane.YES_NO_OPTION);
				if (r == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
			}
		});

		this.nameField.requestFocus();
		this.pwdField.setForeground(Color.gray);
		this.pwdField.setText("请输入密码");
		this.pwdField.setEchoChar('\0');// 设置密码明文显示
		this.cfpwdField.setText("请输入确认密码");
		this.cfpwdField.setEchoChar('\0');
		this.cfpwdField.setForeground(Color.gray);
		this.ageField.setText("请输入年龄");
		this.ageField.setForeground(Color.gray);

		this.nameField.addKeyListener(this);
		this.pwdField.addKeyListener(this);
		this.cfpwdField.addKeyListener(this);
		this.rbtn1.addKeyListener(this);
		this.rbtn2.addKeyListener(this);
		this.ageField.addKeyListener(this);
		this.classBox.addKeyListener(this);
		this.regBtn.addKeyListener(this);
		this.resetBtn.addKeyListener(this);
		this.cancelBtn.addKeyListener(this);

		this.nameField.addFocusListener(this);
		this.pwdField.addFocusListener(this);
		this.cfpwdField.addFocusListener(this);
		this.rbtn1.addFocusListener(this);
		this.rbtn2.addFocusListener(this);
		this.ageField.addFocusListener(this);
		this.classBox.addFocusListener(this);
		this.regBtn.addFocusListener(this);
		this.resetBtn.addFocusListener(this);
		this.cancelBtn.addFocusListener(this);

		for (int i = 0; i < boxes.length; i++) {
			boxes[i] = Box.createHorizontalBox();
		}
		boxes[0].add(Box.createHorizontalStrut(70));
		boxes[0].add(titleLbl);
		boxes[0].add(Box.createHorizontalStrut(70));
		boxes[1].add(this.nameLbl);
		boxes[1].add(this.nameField);
		boxes[2].add(this.pwdLbl);
		boxes[2].add(this.pwdField);
		boxes[3].add(this.cfpwdLbl);
		boxes[3].add(this.cfpwdField);
		boxes[4].add(this.genderLbl);
		boxes[4].add(this.panel);
		boxes[5].add(this.ageLbl);
		boxes[5].add(this.ageField);
		boxes[6].add(this.classLbl);
		boxes[6].add(this.classBox);
		boxes[7].add(this.regBtn);
		boxes[7].add(Box.createHorizontalStrut(30));
		boxes[7].add(this.resetBtn);
		boxes[7].add(Box.createHorizontalStrut(30));
		boxes[7].add(this.cancelBtn);
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
		for (int i = 0; i < boxes.length; i++) {
			this.add(boxes[i]);
		}
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension screenSize = tool.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		this.setSize(WIDTH, HEIGHT);
		this.setLocation(screenWidth / 2 - WIDTH / 2, (screenHeight - HEIGHT) / 2);
//		this.setResizable(false);//固定窗口大小
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == regBtn) {
			String username = nameField.getText().trim();
			String password1 = new String(pwdField.getPassword()).trim();
			String password2 = new String(this.cfpwdField.getPassword()).trim();
			String age = this.ageField.getText().trim();
			char gender = this.rbtn1.isSelected() == true ? '男' : '女';
//			String classnum=this.classBox.getSelectedItem().toString();
			String classnum = s[this.classBox.getSelectedIndex()];
			// 非空判断
			if (username.equals("请输入用户名")) {
				JOptionPane.showMessageDialog(null, "用户名不能为空！");
				return;
			}
			if (password1.equals("请输入密码·")) {
				JOptionPane.showMessageDialog(null, "密码不能为空！");
				return;
			}
			if (password2.equals("请输入确认密码")) {
				JOptionPane.showMessageDialog(null, "确认密码不能为空！");
				return;
			}
			if (age.equals("请输入年龄")) {
				JOptionPane.showMessageDialog(null, "年龄不能为空！");
				return;
			}
			// 数据有效性判断
			// 判断用户名是否被占用
			FileHelper fh1 = new FileHelper();
			if (fh1.isExist(username)) {
				JOptionPane.showMessageDialog(null, "用户名已被占用，请重新输入！");
				return;
			}
			// 判断两次输入密码是否一致
			if (!password1.equals(password2)) {
				JOptionPane.showMessageDialog(null, "两次输入的密码不一致！");
				return;
			}
			// 判断年龄数据有效性：异常处理
			int ageValue;
			try {
				ageValue = Integer.parseInt(age);
			} catch (NumberFormatException el) {
				JOptionPane.showMessageDialog(null, "年龄必须为数值数据" + el.getMessage());
				return;
			}
			// 实例化User类的对象
			student stu = new student();
			stu.setUsername(username);
			stu.setPassword(password1);
			try {
				stu.setAge(ageValue);
			} catch (AgeValueExcepton e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage());
				return;
			}
			stu.setScore(gender);
			stu.setClassnum(classnum);
			// 将stu对象写入文件，永久保存:文件读写
			FileHelper fh = new FileHelper();
			if (fh.register(stu)) {
				// 提示：注册成功
				JOptionPane.showMessageDialog(null, "注册成功!");
			} else {
				// 提示：注册失败
				JOptionPane.showMessageDialog(null, "注册失败!");
			}
		} else if (e.getSource() == resetBtn) {
			// 清空所有文件筐
			nameField.setText("请输入用户名");
			pwdField.setText("请输入密码");
			cfpwdField.setText("请输入确认密码");
			ageField.setText("请输入年龄");
			// 按钮和下拉列表按钮还原为默认值
			rbtn1.setSelected(true);
			classBox.setSelectedIndex(0);
		} else {
//			JOptionPane.showMessageDialog(null, "取消！");
			this.dispose();
			new Login_GUI("用户登录");
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.nameField) {
			if (this.nameField.getText().trim().equals("请输入用户名")) {
				this.nameField.setForeground(Color.black);
				this.nameField.setText("");
			}
		}
		if (e.getSource() == this.pwdField) {
			if ((new String(this.pwdField.getPassword())).trim().equals("请输入密码")) {
				this.pwdField.setForeground(Color.black);
				this.pwdField.setText("");
				this.pwdField.setEchoChar('*');
			}
		}
		if (e.getSource() == this.cfpwdField) {
			if ((new String(this.cfpwdField.getPassword())).trim().equals("请输入确认密码")) {
				this.cfpwdField.setForeground(Color.black);
				this.cfpwdField.setText("");
				this.cfpwdField.setEchoChar('*');
			}
		}
		if (e.getSource() == this.ageField) {
			if (this.ageField.getText().trim().equals("请输入年龄")) {
				this.ageField.setForeground(Color.black);
				this.ageField.setText("");
			}
		}
		if (e.getSource() == this.regBtn) {
			this.regBtn.setBackground(new Color(55, 166, 21));
		}
		if (e.getSource() == this.resetBtn) {
			this.resetBtn.setBackground(new Color(55, 166, 21));
		}
		if (e.getSource() == this.cancelBtn) {
			this.cancelBtn.setBackground(new Color(55, 166, 21));
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.nameField) {
			if (this.nameField.getText().trim().equals("")) {
				this.nameField.setForeground(Color.gray);
				this.nameField.setText("请输入用户名");
			}
		}
		if (e.getSource() == this.pwdField) {
			if ((new String(this.pwdField.getPassword())).trim().equals("")) {
				this.pwdField.setForeground(Color.gray);
				this.pwdField.setText("请输入密码");
				this.pwdField.setEchoChar('\0');
			}
		}
		if (e.getSource() == this.cfpwdField) {
			if ((new String(this.cfpwdField.getPassword())).trim().equals("")) {
				this.cfpwdField.setForeground(Color.gray);
				this.cfpwdField.setText("请输入确认密码");
				this.cfpwdField.setEchoChar('\0');
			}
		}
		if (e.getSource() == this.ageField) {
			if (this.ageField.getText().trim().equals("")) {
				this.ageField.setForeground(Color.gray);
				this.ageField.setText("请输入年龄");
			}
		}
		if (e.getSource() == this.regBtn) {
			this.regBtn.setBackground(new Color(240, 240, 240));
		}
		if (e.getSource() == this.resetBtn) {
			this.resetBtn.setBackground(new Color(240, 240, 240));
		}
		if (e.getSource() == this.cancelBtn) {
			this.cancelBtn.setBackground(new Color(240, 240, 240));
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
//		System.out.println(e.getKeyCode());
		if (e.getKeyCode() == 10) {
			if (e.getSource() == this.nameField) {
				this.pwdField.requestFocus();
			}
			if (e.getSource() == this.pwdField) {
				this.cfpwdField.requestFocus();
			}
			if (e.getSource() == this.cfpwdField) {
				this.rbtn1.requestFocus();
			}
			if (e.getSource() == this.rbtn1) {
				this.ageField.requestFocus();
			}
			if (e.getSource() == this.rbtn2) {
				this.ageField.requestFocus();
			}
			if (e.getSource() == this.ageField) {
				this.classBox.requestFocus();
			}
			if (e.getSource() == this.classBox) {
				this.regBtn.requestFocus();
			}
		}
		if (e.getKeyCode() == 39) {
			if (e.getSource() == this.regBtn) {
				this.resetBtn.requestFocus();
			}
			if (e.getSource() == this.resetBtn) {
				this.cancelBtn.requestFocus();
			}
		}
		if (e.getKeyCode() == 37) {
			if (e.getSource() == this.cancelBtn) {
				this.resetBtn.requestFocus();
			}
			if (e.getSource() == this.resetBtn) {
				this.regBtn.requestFocus();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}