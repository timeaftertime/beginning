package cn.milai.common.uniform.model;

import java.util.Objects;

public class Author {

	private String name;
	private String email;

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

	public String getEmail() { return email; }

	public void setEmail(String email) { this.email = email; }

	public Author(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public Author() {
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Author other = (Author) obj;
		return Objects.equals(email, other.email) && Objects.equals(name, other.name);
	}

}