package cn.milai.common.api.model;

/**
 * 邮箱 数据模型
 * @author milai
 * @date 2021.03.02
 */
public class Email {

	private String address;

	public String getAddress() { return address; }

	public void setAddress(String address) { this.address = address; }

	public Email(String address) {
		this.address = address;
	}

	public Email() {}
}