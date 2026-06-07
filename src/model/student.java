package model;

import java.io.Serializable;

public class student implements Serializable {
	/**
	 * Student类序列化
	 */
	private static final long serialVersionUID = 1L;
	// 成员变量;私有-外部类中的对象不能直接访问
	private String username, password, classnum;
	private int age, score;
	private char sex;

//	访问成员变量的get和set方法
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getClassnum() {
		return classnum;
	}

	public void setClassnum(String classnum) {
		this.classnum = classnum;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) throws AgeValueExcepton {
		if (age < 10 || age > 30) {
//			年龄值异常
			throw new AgeValueExcepton(this.username, age);
		}
		this.age = age;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

}