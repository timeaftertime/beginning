package cn.milai.common.uniform.model;

public class Book {

	private Author author;
	private String name;
	private int page;

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

	public int getPage() { return page; }

	public void setPage(int page) { this.page = page; }

	public Author getAuthor() { return author; }

	public void setAuthor(Author author) { this.author = author; }

	public Book(String name, int page, String authorName, String email) {
		this.name = name;
		this.page = page;
		this.author = new Author(authorName, email);
	}

	public Book() {
	}

}