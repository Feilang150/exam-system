package model;

public class AgeValueExcepton extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public AgeValueExcepton(String username, int age) {
		super();
		this.message = username + "的年龄为" + age + ",不符合要求!";
	}

	public String getMessage() {
		return message;
	}

}
