package cn.milai.common.uniform.model;

import java.util.List;
import java.util.Map;

/**
 * 个人 数据模型
 * @author milai
 * @date 2021.03.02
 */
public class Person {

	private int id;

	private String name;

	private String nickname;

	private Map<String, Integer> scores;

	private List<Email> emails;

	public int getId() { return id; }

	public void setId(int id) { this.id = id; }

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

	public String getNickname() { return nickname; }

	public void setNickname(String nickname) { this.nickname = nickname; }

	public Map<String, Integer> getScores() { return scores; }

	public void setScores(Map<String, Integer> scores) { this.scores = scores; }

	public List<Email> getEmails() { return emails; }

	public void setEmails(List<Email> emails) { this.emails = emails; }

	public Person() {
	}

	public Person(int id, String name, String nickname, Map<String, Integer> scores, List<Email> emails) {
		this.id = id;
		this.name = name;
		this.nickname = nickname;
		this.scores = scores;
		this.emails = emails;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", nickname=" + nickname + ", scores=" + scores + ", emails="
			+ emails + "]";
	}

}
