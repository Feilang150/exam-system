package ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import DAL.DBHelper;
import model.Question;
import model.student;

public class Test_GUI extends JFrame implements java.awt.event.MouseListener {
	/**
	 * 考试主界面类序列化版本UID
	 */
	private static final long serialVersionUID = 1L;
	// 菜单条清单
	JMenuBar bar;// 菜单条
	JMenu mn_help, mn_exit;// 菜单
	JMenuItem mi_version, mi_about;// 菜单项
	// 窗口屏幕居中相关类
	Toolkit tool;
	Dimension ds;
	// 界面其他控件清单
	JLabel totaltime;// 显示“考试时间”标签
	JLabel ttimeshow;// 显示考试总时间标签
	JLabel lefttime;// 显示“剩余时间”标签
	JLabel ltimeshow;// 显示考试剩余时间标签
	JButton btnstart;// 开始考试按钮
	JLabel lbltotal;// 显示共多少题标签
	JLabel lblcount;// 显示当前为第几题标签
	JLabel lbluserinfo;// 显示当前正在考试的用户名标签
	JTextArea area;// 显示试题信息和成绩单文本区域
	JScrollPane sp;// 滚动面板
	JRadioButton[] rbtn = new JRadioButton[4];// 单选题四个选项
	ButtonGroup bg;// 逻辑按钮组
	JPanel panel;// 物理按钮组
	Box[] boxes = new Box[4];// 盒式布局
	JButton btnback, btnnext, btnsubmit;// 上一题、下一题、交卷按钮
	TestActionListener actionlistener = new TestActionListener();// 考试界面中所有动作事件的事件处理者
//定义考试倒计时相关变量
	int total_time = 1;// 单位：分钟
//考试用户信息
	student stu;
	ArrayList<Question> quesList = null;
	int current = 0, total;
	Question ques = new Question();// 考生当前答题
	ClockThread clock;

	public Test_GUI(student stu) {
		this.setTitle("学生在线考试系统");
		this.stu = stu;
		// 创建菜单相关对象
		bar = new JMenuBar();
		mn_help = new JMenu("帮助(H)");
		mn_exit = new JMenu("退出(E)");
		mi_version = new JMenuItem("版本");
		mi_about = new JMenuItem("关于");
		// 设置菜单热键 ALT+
		mn_help.setMnemonic(KeyEvent.VK_H);
		mn_exit.setMnemonic(KeyEvent.VK_E);
		// 设置菜单项快捷键字母
		mi_version.setAccelerator(KeyStroke.getKeyStroke('v'));
		mi_about.setAccelerator(KeyStroke.getKeyStroke('a'));
		// 添加菜单和菜单项
		mn_help.add(mi_version);
		mn_help.addSeparator();
		mn_help.add(mi_about);
		bar.add(mn_help);
		bar.add(mn_exit);
		// 向界面添加菜单
		this.setJMenuBar(bar);
		// 界面其他控件
		totaltime = new JLabel("考试时间：");
		ttimeshow = new JLabel("00:00:00");
		lefttime = new JLabel("剩余时间：");
		ltimeshow = new JLabel("00:00:00");
		btnstart = new JButton("开始考试");
		lbltotal = new JLabel("共XX题");
		lblcount = new JLabel("第X题");
		lbluserinfo = new JLabel("考生：" + stu.getUsername());
		area = new JTextArea(10, 40);
		sp = new JScrollPane(area);// 将文本区域组件添加到滚动给面板中
		bg = new ButtonGroup();// 四个按钮的逻辑组
		panel = new JPanel();// 四个单选按钮的物理组合
		String[] s = { "A", "B", "C", "D" };
		for (int i = 0; i < 4; i++) {
			rbtn[i] = new JRadioButton(s[i]);
			bg.add(rbtn[i]);
			panel.add(rbtn[i]);
			rbtn[i].addActionListener(actionlistener);
		}
		panel.setBorder(BorderFactory.createTitledBorder("选项"));// 为容纳四个选项按钮的面板添加边框
		btnback = new JButton("上一题");
		btnnext = new JButton("下一题");
		btnsubmit = new JButton("提交试卷");
//监听所有菜单单项按钮
		this.mi_version.addActionListener(actionlistener);
		this.mi_about.addActionListener(actionlistener);
		this.btnstart.addActionListener(actionlistener);
		this.btnback.addActionListener(actionlistener);
		this.btnnext.addActionListener(actionlistener);
		this.btnsubmit.addActionListener(actionlistener);
		this.mn_exit.addMouseListener(this);
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
		// 设置考试之前状态
		this.btnback.setEnabled(false);
		this.btnnext.setEnabled(false);
		this.btnsubmit.setEnabled(false);

		for (int i = 0; i < 4; i++) {
			boxes[i] = Box.createHorizontalBox();
		}
		boxes[0].add(totaltime);
		boxes[0].add(Box.createHorizontalStrut(10));
		boxes[0].add(ttimeshow);
		boxes[0].add(Box.createHorizontalStrut(10));
		boxes[0].add(lefttime);
		boxes[0].add(Box.createHorizontalStrut(10));
		boxes[0].add(ltimeshow);
		boxes[0].add(Box.createHorizontalStrut(45));
		boxes[0].add(btnstart);
		boxes[1].add(Box.createHorizontalStrut(150));
		boxes[1].add(lblcount);
		boxes[1].add(Box.createHorizontalStrut(10));
		boxes[1].add(lbltotal);
		boxes[1].add(Box.createHorizontalStrut(30));
		boxes[1].add(lbluserinfo);
		boxes[1].add(Box.createHorizontalStrut(200));
		area.setText("考场规则：\n一、考试前15分钟，凭准考证和身份证进入考场，对号入座，将准考证和身份证放在桌面右上角，"
				+ "便于监考人员检查。\n二、考试开考30分钟后不得入场，答题结束后提交试卷后可以申请离场。\n三、考生要爱惜" + "考场的机器和相关设备，严格按照固定的操作说明进行操作，如有人为损坏，照价赔偿。");
		// 设置文本区域不可编辑
		area.setEditable(false);
		// 设置文本区域的字体为楷体、加粗、20磅
		area.setFont(new Font("楷体", Font.BOLD, 20));
		boxes[2].add(sp);
		boxes[3].add(panel);
		boxes[3].add(Box.createHorizontalStrut(10));
		boxes[3].add(btnback);
		boxes[3].add(Box.createHorizontalStrut(10));
		boxes[3].add(btnnext);
		boxes[3].add(Box.createHorizontalStrut(10));
		boxes[3].add(btnsubmit);
		for (int i = 0; i < 4; i++) {
			this.add(boxes[i]);
		}
		// 为考试相关按钮添加事件监听器
		// 设置界面整体布局及显示
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
		tool = Toolkit.getDefaultToolkit();
		ds = tool.getScreenSize();
		this.setBounds(ds.width / 2 - 250, ds.height / 2 - 240, 500, 480);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
	}

	// 考试倒计时类；倒计时与学生考试答题并发执行->线程类
	class ClockThread extends Thread {
		int left_time;// 秒

		public ClockThread(int left_time) {
			super();
			this.left_time = left_time;
		}

		@Override
		public void run() {// 运行状态
			NumberFormat f = NumberFormat.getInstance();
			f.setMinimumIntegerDigits(2);
			// 倒计时的过程
			int h, m, s;
			h = this.left_time / 3600;
			m = this.left_time % 3600 / 60;
			s = left_time % 60;
			ttimeshow.setText(f.format(h) + ":" + f.format(m) + ":" + f.format(s));
			while (true) {
				ltimeshow.setText(f.format(h) + ":" + f.format(m) + ":" + f.format(s));
				if (this.left_time <= 0) {
					break;
				}
//			一秒后
				try {
					Thread.sleep(1000);// 休眠1秒
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					System.out.print("放弃倒计时！");
					return;
				}
				this.left_time--;
				h = this.left_time / 3600;
				m = this.left_time % 3600 / 60;
				s = left_time % 60;

			}
			JOptionPane.showMessageDialog(null, "考试结束!", "结束", JOptionPane.PLAIN_MESSAGE);
			area.setText("本次考试的成绩为：");
			int right = 0;
			for (Question ques : quesList) {
				if (ques.getAnswer() == ques.getKey()) {
					right++;
				}
			}
			area.append("本次考试共" + total + "题,你回答正确" + right + "题\n");
			area.append("成绩为:" + Math.round(right * 1f / total * 100));
			// 全部按钮不可用
			btnback.setEnabled(false);
			btnnext.setEnabled(false);
			btnsubmit.setEnabled(false);
			bg.clearSelection();
			for (int i = 0; i < rbtn.length; i++) {
				rbtn[i].setEnabled(false);
			}
		} // 死亡状态
	}

//考试主界面的动作事件事件监听器类：处理当前界面所有按钮的单击事件
	class TestActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {// 所有按钮单击事件的事件处理方法
			// TODO Auto-generated method stub

			if (e.getSource() == mi_version) {
				JOptionPane.showMessageDialog(null, "桌面1.0版", "版本信息", JOptionPane.PLAIN_MESSAGE);
				return;
			}
			if (e.getSource() == mi_about) {
				JOptionPane.showMessageDialog(null, "学号:2123521 \n姓名：李良奇", "关于", JOptionPane.PLAIN_MESSAGE);
				return;
			}
			if (e.getSource() == btnstart) {// 开始考试按钮事件处理
//倒计时开始：新建子线程
				clock = new ClockThread(total_time * 60);// 分配内存,实参：
				clock.start();// 进入就绪状态：排队抢CPU
//开始答题：主线程->读取数据库中试题,显示在窗口中
				quesList = DBHelper.getQuestions();
				if (quesList == null) {
					JOptionPane.showMessageDialog(null, "抱歉,电子试卷生成失败!请与管理员联系");
					clock.interrupt();
					return;
				}
				total = quesList.size();// 试题总数：集合中包含元素的数量
				lbltotal.setText("共" + total + "题");

				// 电子试卷生成成功后，读取第一道试题，并显示在考试主界面的
				// Question ques=new Question();
				ques = quesList.get(current);
				lblcount.setText("第" + (current + 1) + "题");
				area.setText((current + 1) + "." + ques.getTm() + "\n");
				area.append("A." + ques.getChiceA() + "\n");
				area.append("B." + ques.getChoiceB() + "\n");
				area.append("C." + ques.getChoiceC() + "\n");
				area.append("D." + ques.getChoiceD() + "\n");
				// 修改考试状态
				btnstart.setEnabled(false);
				btnnext.setEnabled(true);
				btnsubmit.setEnabled(true);
				for (int i = 0; i < rbtn.length; i++)
					rbtn[i].setEnabled(true);
			}
			if (e.getSource() == btnback) {
				if (!btnnext.isEnabled())
					btnnext.setEnabled(true);
				current--;
				if (current == 0) {
					btnback.setEnabled(false);
				}
//        	Question ques=new Question();
				ques = quesList.get(current);
				lblcount.setText("第" + (current + 1) + "题");
				area.setText((current + 1) + "." + ques.getTm() + "\n");
				area.append("A." + ques.getChiceA() + "\n");
				area.append("B." + ques.getChoiceB() + "\n");
				area.append("C." + ques.getChoiceC() + "\n");
				area.append("D." + ques.getChoiceD() + "\n");
				if (ques.getAnswer() == '\0')
					bg.clearSelection();
				else {
					for (int i = 0; i < rbtn.length; i++) {
						if (ques.getAnswer() == rbtn[i].getText().charAt(0)) {
							rbtn[i].setSelected(true);
							break;
						}
					}
				}
			}
			if (e.getSource() == btnnext) {
				if (!btnback.isEnabled())
					btnback.setEnabled(true);
				current++;
				if (current == total - 1) {
					btnnext.setEnabled(false);
				}
				// Question ques=new Question();
				ques = quesList.get(current);
				lblcount.setText("第" + (current + 1) + "题");
				area.setText((current + 1) + "." + ques.getTm() + "\n");
				area.append("A." + ques.getChiceA() + "\n");
				area.append("B." + ques.getChoiceB() + "\n");
				area.append("C." + ques.getChoiceC() + "\n");
				area.append("D." + ques.getChoiceD() + "\n");
				if (ques.getAnswer() == '\0')
					bg.clearSelection();
				else {
					for (int i = 0; i < rbtn.length; i++) {
						if (ques.getAnswer() == rbtn[i].getText().charAt(0)) {
							rbtn[i].setSelected(true);
							break;
						}
					}
				}
			}
			if (e.getSource() == btnsubmit) {// 提交试卷按钮
				// 确认是否提交
				int r = JOptionPane.showConfirmDialog(null, "你确定要交卷吗?", "确认交卷", JOptionPane.OK_CANCEL_OPTION);
				if (r == JOptionPane.CANCEL_OPTION)
					return;
				// 结束考试,打印成绩单
				clock.interrupt();
				area.setText("本次考试的成绩为：");
				int right = 0;
				for (Question ques : quesList) {
					if (ques.getAnswer() == ques.getKey()) {
						right++;
					}
				}
				area.append("本次考试共" + total + "题,你回答正确" + right + "题\n");
				area.append("成绩为:" + Math.round(right * 1f / total * 100));
				// 全部按钮不可用
				btnback.setEnabled(false);
				btnnext.setEnabled(false);
				btnsubmit.setEnabled(false);
				bg.clearSelection();
				for (int i = 0; i < rbtn.length; i++) {
					rbtn[i].setEnabled(false);
				}
			}
			for (int i = 0; i < 4; i++)
				if (e.getSource() == rbtn[i]) {
					ques.setAnswer(rbtn[i].getText().charAt(0));
				}

		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int r;
		r = JOptionPane.showConfirmDialog(null, "你确认要退出吗？", "确认对话框", JOptionPane.YES_NO_OPTION);
		if (r == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
