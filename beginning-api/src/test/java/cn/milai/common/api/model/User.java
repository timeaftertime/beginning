package cn.milai.common.api.model;

import java.util.List;
import java.util.Map;

/**
 * 用户 数据模型
 * @author milai
 * @date 2021.03.02
 */
public class User {

	private int id;

	private String name;

	private Department department;

	private Map<String, Integer> scores;

	private List<Email> emails;

	public int getId() { return id; }

	public void setId(int id) { this.id = id; }

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

	public Department getDepartment() { return department; }

	public void setDepartment(Department department) { this.department = department; }

	public Map<String, Integer> getScores() { return scores; }

	public void setScores(Map<String, Integer> scores) { this.scores = scores; }

	public List<Email> getEmails() { return emails; }

	public void setEmails(List<Email> emails) { this.emails = emails; }

	public User() {}

	public User(int id, String name, Department department, Map<String, Integer> scores, List<Email> emails) {
		this.id = id;
		this.name = name;
		this.department = department;
		this.scores = scores;
		this.emails = emails;
	}
}