package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Question;

public class DBHelper {
	// 连接
	static Connection conn;

	public static boolean getConn() {// 连接数据库，连接成功返回true，否则返回false
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("驱动加载成功！");
//			//连接test数据库
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "sasa");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root", "274019");
			System.out.println("数据库连接成功！");
			return true;
//			getQuestions();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("驱动加载失败！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("数据库连接失败！" + e.getMessage());
		}
		return false;

	}

//查询数据库方法
	public static ArrayList<Question> getQuestions() {
		String strSQL = "SELECT * FROM questions;";
		ArrayList<Question> quesList = new ArrayList<Question>();// 电子试卷
		if (getConn()) {
			Statement stmt;
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(strSQL);// rs指向查询之前结果集中第一条记录之前
				while (rs.next()) {
					Question ques = new Question();
					ques.setTm(rs.getString("tm"));// 双引号中内容为mysql数据表中对应的字段名,rs所指向记录的tm字段的值赋值给试题对象的tm属性
					System.out.print(ques.getTm() + "\t");
					ques.setChiceA(rs.getString("choiceA"));
					System.out.print(ques.getChiceA() + "\t");
					ques.setChoiceB(rs.getString("choiceB"));
					System.out.print(ques.getChoiceB() + "\t");
					ques.setChoiceC(rs.getString("choiceC"));
					System.out.print(ques.getChoiceC() + "\t");
					ques.setChoiceD(rs.getString("choiceD"));
					System.out.print(ques.getChoiceD() + "\n");
					ques.setKey(rs.getString("Key").charAt(0));
					quesList.add(ques);// 将试题添加到电子试卷类(集合对象)中
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("电子试卷生成失败！");
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("数据库关闭失败");
					e.printStackTrace();
				}
			}
		}
		return quesList;

	}
}
