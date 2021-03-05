package cn.milai.common.decoupling.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.milai.common.decoupling.model.ModelTestUtils;
import cn.milai.common.decoupling.model.Person;
import cn.milai.common.decoupling.model.User;

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
			ModelTestUtils.assertUserPersonEquals(user, Mappers.deep(user, Person.class), true);
		}
	}

	@Test
	public void testIgnores() {
		for (int i = 0; i < 10; i++) {
			User u1 = ModelTestUtils.randUser(10, 10, 10);
			User u2 = Mappers.map(u1, User.class, "name");
			assertNotSame(u1, u2);
			assertEquals(u1.getId(), u2.getId());
			assertNotNull(u1.getName());
			assertNull(u2.getName());
			ModelTestUtils.assertEmailsEqual(u1.getEmails(), u2.getEmails(), false);
			ModelTestUtils.assertDepartmentEqual(u1.getDepartment(), u2.getDepartment(), false);
		}
	}

	@Test
	public void testThen() {
		Mapper<User, Person> mapper = Mappers.mapThen(User.class, Person.class).then((u, p, i) -> {
			return null;
		}).build();
		for (int i = 0; i < 10; i++) {
			assertNull(mapper.map(ModelTestUtils.randUser(10, 10, 10)));
		}
	}

	@Test
	public void testDeep() {
		List<User> users = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			users.add(ModelTestUtils.randUser(12, 10, 5));
		}
		for (User u1 : users) {
			User u2 = Mappers.deep(u1, User.class);
			ModelTestUtils.assertUserEqual(u1, u2, true);
			User u3 = Mappers.deepThen(User.class, User.class).build().map(u1);
			ModelTestUtils.assertUserEqual(u1, u3, true);
		}
	}

}
