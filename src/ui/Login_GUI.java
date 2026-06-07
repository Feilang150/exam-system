package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.*;
import mylib.ImagePanel;
import java.util.Random;
import java.awt.event.*;
import javax.swing.JFrame;

import DAL.FileHelper;

public class Login_GUI extends JFrame implements KeyListener, FocusListener {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	// 声明登入面体类的成员变量（对象）
	JLabel titleLbl, nameLbl, pwdlbl, fei, captchaLbl;
	JTextField nameField, sedField;
	JPasswordField pwdField;
	JButton loginBtn, registerBtn, canceIBtn;// 3个按钮
	JPanel panel1, panel121, panel122, panel12, panel13, panel23;
	ImagePanel bgPanel;
	LoginActionListener actionLIstener;// 动作事件的事件处理者（事件监听器的类事件）
	// 构造方法

	public Login_GUI(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
		// 实例化登入窗体类的各个成员对象
		this.getContentPane().setBackground(new Color(55, 166, 215));
		this.titleLbl = new JLabel("欢迎使用考试系统");
		this.titleLbl.setForeground(Color.CYAN);
		Font font = new Font("微软雅黑", Font.PLAIN, 30);
		this.titleLbl.setFont(font);
		this.nameLbl = new JLabel("用户名:");
		this.pwdlbl = new JLabel("  密  码:");
		this.fei = new JLabel("      验证码:");
		this.captchaLbl = new JLabel(this.CaptchaGenerator());
		this.nameLbl.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		this.pwdlbl.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		this.fei.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		this.captchaLbl.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		this.captchaLbl.setForeground(Color.RED);
		this.nameField = new JTextField(16);
		this.pwdField = new JPasswordField(16);
		this.sedField = new JTextField(16);
		this.loginBtn = new JButton("登录");
		this.registerBtn = new JButton("注册");
		this.canceIBtn = new JButton("取消");

		this.nameField.requestFocus();
		this.sedField.requestFocus();
		this.pwdField.setForeground(Color.gray);
		this.pwdField.setText("请输入密码");
		this.pwdField.setEchoChar('\0');// 设置密码明文显示
		this.sedField.setText("请输入验证码");
		this.sedField.setForeground(Color.gray);

		this.nameField.addKeyListener(this);
		this.pwdField.addKeyListener(this);
		this.sedField.addKeyListener(this);
		this.loginBtn.addKeyListener(this);
		this.registerBtn.addKeyListener(this);
		this.canceIBtn.addKeyListener(this);

		this.nameField.addFocusListener(this);
		this.pwdField.addFocusListener(this);
		this.sedField.addFocusListener(this);
		this.loginBtn.addFocusListener(this);
		this.registerBtn.addFocusListener(this);
		this.canceIBtn.addFocusListener(this);

		actionLIstener = new LoginActionListener(); // 登入窗口中动作处理
		this.loginBtn.addActionListener(actionLIstener);// 绑定
		this.registerBtn.addActionListener(actionLIstener);
		this.canceIBtn.addActionListener(actionLIstener);

		// 窗口事件处理
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

		this.panel1 = new JPanel();
		this.panel121 = new JPanel();
		this.panel122 = new JPanel();
		this.panel12 = new JPanel();
		this.panel13 = new JPanel();
		this.panel23 = new JPanel();

		Image image = Toolkit.getDefaultToolkit().createImage("./res/东海.jpg");
		this.bgPanel = new ImagePanel(image);

		this.panel1.setOpaque(false);
		this.panel121.setOpaque(false);
		this.panel122.setOpaque(false);
		this.panel12.setOpaque(false);
		this.panel13.setOpaque(false);
		this.panel23.setOpaque(false);
		// 将各个组件添加到板中
		this.panel1.add(this.titleLbl);
		this.panel121.add(this.nameLbl);
		this.panel121.add(this.nameField);
		this.panel122.add(this.pwdlbl);
		this.panel122.add(this.pwdField);
		this.panel23.add(this.fei);
		this.panel23.add(this.sedField);
		this.panel23.add(this.captchaLbl);
		this.panel12.add(this.panel121);
		this.panel12.add(this.panel122);
		this.panel12.add(this.panel23);
		// 登入页面按钮
		this.panel13.add(this.loginBtn);
		this.panel13.add(this.registerBtn);
		this.panel13.add(this.canceIBtn);
		// 将panel添加到图片面板中
		this.bgPanel.setLayout(new BorderLayout());
		this.bgPanel.add(panel1, BorderLayout.NORTH);
		this.bgPanel.add(panel12, BorderLayout.CENTER);
		this.bgPanel.add(panel13, BorderLayout.SOUTH);
		// 将图片面板放在窗口中
		this.add(bgPanel);
		// 窗体各属性的设置
		this.setLocation(450, 450);
		this.setSize(455, 450);
		this.setResizable(false);// 固定窗口大小
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
	}

	// 验证码
	public String CaptchaGenerator() {
		Random random = new Random();
		int number1 = random.nextInt(10);
		int number2 = random.nextInt(10);
		int number3 = random.nextInt(10);
		int number4 = random.nextInt(10);
		String captcha = String.valueOf(number1) + number2 + number3 + number4;
		return captcha;
	}

	// 定义内部类:登入窗口的动作事件的事件监听器类
	class LoginActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {// 处理动作事件的事件处理方法
			// TODO Auto-generated method stub
			/*
			 * 
			 * 
			 * 
			 */
			if (e.getSource() == loginBtn) {
				String username = nameField.getText().trim();
				String password = new String(pwdField.getPassword());
				String age = sedField.getText().trim();
				if (username.equals("请输入用户名")) {
					JOptionPane.showMessageDialog(null, "用户名不能为空！");
					return;
				}
				if (password.equals("请输入密码")) {
					JOptionPane.showMessageDialog(null, "密码不能为空！");
					return;
				}
				if (age.equals("请输入验证码")) {
					JOptionPane.showMessageDialog(null, "验证码不能为空！");
					return;
				}
				FileHelper fh = new FileHelper();
				if (!fh.isExist(username)) {
					JOptionPane.showMessageDialog(null, "请注册后再登录！");
				} else if (!fh.verifyPwd(password)) {
					JOptionPane.showMessageDialog(null, "密码错误！");
				} else if (!age.equals(captchaLbl.getText())) {
					JOptionPane.showMessageDialog(null, "验证码错误！");
				} else {
					JOptionPane.showMessageDialog(null, "欢迎登入考试系统！");
					new Test_GUI(fh.getStu());
					dispose();// 销毁外部类（登录窗口）的对象，即当前的登录窗口
				}
			}
//			关闭登入窗口,打开注册窗口
			if (e.getActionCommand().equals("注册")) {
				JOptionPane.showMessageDialog(null, "注册！");
				dispose();
				new Register_GUI("用户注册");
			}
			if (e.getSource() == canceIBtn) {
				int r;
				r = JOptionPane.showConfirmDialog(null, "你确认要关闭吗？", "确认对话框", JOptionPane.YES_NO_OPTION);
				if (r == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
//			    dispose();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == 10) {
			if (e.getSource() == this.nameField) {
				this.pwdField.requestFocus();
			}
			if (e.getSource() == this.pwdField) {
				this.sedField.requestFocus();
			}
			if (e.getSource() == this.sedField) {
				this.loginBtn.requestFocus();
			}
		}
		if (e.getKeyCode() == 39) {
			if (e.getSource() == this.loginBtn) {
				this.registerBtn.requestFocus();
			}
			if (e.getSource() == this.registerBtn) {
				this.canceIBtn.requestFocus();
			}
		}
		if (e.getKeyCode() == 37) {
			if (e.getSource() == this.canceIBtn) {
				this.registerBtn.requestFocus();
			}
			if (e.getSource() == this.registerBtn) {
				this.loginBtn.requestFocus();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

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
		if (e.getSource() == this.sedField) {
			if (new String(this.sedField.getText()).trim().equals("请输入验证码")) {
				this.sedField.setForeground(Color.black);
				this.sedField.setText("");
			}
		}
		if (e.getSource() == this.loginBtn) {
			this.loginBtn.setBackground(new Color(240, 240, 240));
		}
		if (e.getSource() == this.registerBtn) {
			this.registerBtn.setBackground(new Color(240, 240, 240));
		}
		if (e.getSource() == this.canceIBtn) {
			this.canceIBtn.setBackground(new Color(240, 240, 240));
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
				this.pwdField.setEchoChar('\0');// 设置密码明文显示
			}
		}
		if (e.getSource() == this.sedField) {
			if (this.sedField.getText().trim().equals("")) {
				this.sedField.setForeground(Color.gray);
				this.sedField.setText("请输入验证码");
			}
		}
		if (e.getSource() == this.loginBtn) {
			this.loginBtn.setBackground(null);
		}
		if (e.getSource() == this.registerBtn) {
			this.registerBtn.setBackground(null);
		}
		if (e.getSource() == this.canceIBtn) {
			this.canceIBtn.setBackground(null);
		}
	}

}