package DAL;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import javax.swing.JOptionPane;

import model.student;

public class FileHelper {
	File f = new File("./res/student.obj");
	ObjectOutputStream oout;
	MyObjectOutputStream myoout;
	FileOutputStream fout;
	FileInputStream fin;
	ObjectInputStream oin;
	student stu;

	public student getStu() {
		return stu;
	}

	// 将注册的学生对象的信息写到文件中
	public boolean register(student stu) {
		try {

			fout = new FileOutputStream(f, true);
			if (f.length() < 1) {
				oout = new ObjectOutputStream(fout);
				oout.writeObject(stu);
			} else {
				myoout = new MyObjectOutputStream(fout);
				myoout.writeObject(stu);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "文件没又找到!");
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "对象无法序列化或写入失败!");
			return false;
		}
		return true;
	}

	// 判断用户是否注册；读文件
	public boolean isExist(String username) {
		// TODO Auto-generated method stub
		try {
			fin = new FileInputStream(f);
			oin = new ObjectInputStream(fin);
			while (true) {
				stu = (student) oin.readObject();
				if (stu.getUsername().equals(username)) {
					return true;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "文件没又找到！");
			return false;
		} catch (Exception e) {
			System.out.println("发生了异常!\n" + e.toString());

		}
		return false;
	}

	public boolean verifyPwd(String password) {
		// TODO Auto-generated method stub
		if (stu.getPassword().equals(password)) {
			return true;
		}
		return false;
	}
}

class MyObjectOutputStream extends ObjectOutputStream {

	@Override
	protected void writeStreamHeader() throws IOException {
		// TODO Auto-generated method stub

	}

	public MyObjectOutputStream(OutputStream out) throws IOException {
		super(out);
		// TODO Auto-generated constructor stub
	}

}