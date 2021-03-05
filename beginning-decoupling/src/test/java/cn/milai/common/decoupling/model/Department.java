package cn.milai.common.decoupling.model;

/**
 * 部门 数据模型
 * @author milai
 * @date 2021.03.02
 */
public class Department {

	private int id;

	private String name;

	public int getId() { return id; }

	public void setId(int id) { this.id = id; }

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

	public Department() {}

	public Department(int id, String name) {
		this.id = id;
		this.name = name;
	}

}