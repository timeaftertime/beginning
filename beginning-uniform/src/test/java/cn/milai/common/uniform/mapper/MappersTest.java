package cn.milai.common.uniform.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import cn.milai.common.uniform.model.ModelTestUtils;
import cn.milai.common.uniform.model.Person;
import cn.milai.common.uniform.model.User;

/**
 * {@link Mappers} 测试类
 * @author milai
 * @date 2021.03.02
 */
public class MappersTest {

	@Test
	public void testMapDifferentClass() {
		for (int i = 0; i < 10; i++) {
			User user = ModelTestUtils.randUser(10, 10, 10);
			ModelTestUtils.assertUserPersonEquals(user, Mappers.map(user, new Person()), false);
		}
	}

	@Test
	public void testIgnores() {
		for (int i = 0; i < 10; i++) {
			User u1 = ModelTestUtils.randUser(10, 10, 10);
			User u2 = Mappers.map(u1, User.class);
			assertNotSame(u1, u2);
			assertEquals(u1.getId(), u2.getId());
			assertNotNull(u1.getName());
			assertEquals(u1.getName(), u2.getName());
			ModelTestUtils.assertEmailsEqual(u1.getEmails(), u2.getEmails(), false);
			ModelTestUtils.assertDepartmentEqual(u1.getDepartment(), u2.getDepartment(), false);
		}
	}

	@Test
	public void testThen() {
		AtomicInteger cnt = new AtomicInteger();
		Mapper<User, Person> mapper = Mappers.mapThen(User.class, Person.class)
			.then((u, p) -> cnt.getAndIncrement())
			.build();
		for (int i = 0; i < 10; i++) {
			assertNotNull(mapper.map(ModelTestUtils.randUser(10, 10, 10)));
		}
		assertEquals(10, cnt.get());
	}

}
