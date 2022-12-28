package cn.milai.common.uniform.model;

public class UserDO {

	private int id;

	private String name;

	private String department;

	private String scores;

	private String emails;

	public UserDO() {
	}

	public UserDO(int id, String name, String department, String scores, String emails) {
		this.id = id;
		this.name = name;
		this.department = department;
		this.scores = scores;
		this.emails = emails;
	}

	public int getId() { return id; }

	public void setId(int id) { this.id = id; }

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

	public String getDepartment() { return department; }

	public void setDepartment(String department) { this.department = department; }

	public String getScores() { return scores; }

	public void setScores(String scores) { this.scores = scores; }

	public String getEmails() { return emails; }

	public void setEmails(String emails) { this.emails = emails; }

}
