package cn.milai.common.uniform.serialize;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

import cn.milai.common.uniform.JSONHolder;
import cn.milai.common.uniform.model.Author;
import cn.milai.common.uniform.model.Book;
import cn.milai.common.uniform.resp.Resp;

/**
 * {@link JSON} 测试类
 * @author milai
 * @date 2021.12.31
 */
public class JSONTest {

	@Test
	public void testWriteJSON() throws JsonMappingException, JsonProcessingException {
		String bookName = "一本有趣的 book";
		int page = 100;
		String authorName = "某个大文豪123";
		String email = "777@123aaa.com";
		Book book = new Book(bookName, page, authorName, email);
		JSONHolder.MAPPER.readValue(JSON.write(book), Book.class);
		assertBook(book, bookName, page, authorName, email);
	}

	@Test
	public void testSimpleMapJSON() throws JsonMappingException, JsonProcessingException {
		String[] keys = { "this is KeY1", "key2 is this", "key 3" };
		Object[] values = { 123, "456", 99988876655L };
		for (int i = 0; i < keys.length; i++) {
			Object value = values[i];
			if (value instanceof String) {
				value = String.format("\"%s\"", value);
			}
			assertEquals(String.format("{\"%s\":%s}", keys[i], value), JSON.write(keys[i], values[i]));
		}

		Author author = new Author("authorName", "~~~");
		String key = "object key";
		Map<?, ?> m = JSONHolder.MAPPER.readValue(JSON.write(key, author), Map.class);
		assertEquals(1, m.size());
		Map<?, ?> o = (Map<?, ?>) m.get(key);
		assertEquals(author.getName(), o.get("name"));
		assertEquals(author.getEmail(), o.get("email"));
	}

	@Test
	public void testRead() throws JsonMappingException, JsonProcessingException {
		String bookName = "bookName";
		int page = 0;
		String authorName = "aaa";
		String email = null;
		String json = bookJSON(bookName, null, authorName);
		Book book = JSON.read(json, Book.class);
		assertBook(book, bookName, page, authorName, email);
		assertBook(JSON.read(JSONHolder.MAPPER.readTree(json), Book.class), bookName, page, authorName, email);
		assertNull(JSON.read((JsonNode) null, Book.class));
	}

	@Test
	public void testReadByTypeReference() {
		String bookName = "bookA";
		String authorName = "authorB";
		String json = String.format("{\"data\":%s}", bookJSON(bookName, null, authorName));
		Resp<Book> resp = JSON.read(JSON.readTree(json), new TypeReference<Resp<Book>>() {});
		assertNotNull(resp.getData());
		assertTrue(resp.getData() instanceof Book);
		assertEquals(bookName, resp.getData().getName());
		assertEquals(authorName, resp.getData().getAuthor().getName());
		assertNull(JSON.read((JsonNode) null, new TypeReference<Resp<String>>() {}));
	}

	private static String bookJSON(String bookName, Integer page, String authorName) {
		return String.format(
			"{\"name\":\"%s\", \"page\": %s	, \"author\":{\"name\":\"%s\"}}", bookName, page, authorName
		);
	}

	private void assertBook(Book book, String bookName, int page, String authorName, String authorEmail) {
		assertEquals(bookName, book.getName());
		assertEquals(page, book.getPage());
		assertEquals(authorName, book.getAuthor().getName());
		assertEquals(authorEmail, book.getAuthor().getEmail());
	}

}
