package cn.milai.common.uniform.mapper;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;

import cn.milai.common.uniform.model.Author;

/**
 * {@link JSONMapper} 测试类
 * @author milai
 * @date 2022.11.06
 */
public class JSONMapperTest {

	@Test
	public void testGenericMapp() {
		Mapper<String, List<Author>> booksMapper = JSONMapper.to(new TypeReference<List<Author>>() {});
		assertEquals(
			Arrays.asList(new Author("AAA", "哈哈@hh.com"), new Author("HHH", "啊哈@.xxx.com")),
			booksMapper.map(
				"[{\"name\":\"AAA\",\"email\":\"哈哈@hh.com\",\"page\":1},{\"name\":\"HHH\",\"email\":\"啊哈@.xxx.com\"}]"
			)
		);
	}

}
