package cn.milai.common.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.milai.common.api.model.Department;
import cn.milai.common.api.model.Email;
import cn.milai.common.api.model.Person;
import cn.milai.common.api.model.User;
import cn.milai.common.base.Randoms;

/**
 * {@link Mappers} 测试类
 * @author milai
 * @date 2021.03.02
 */
public class MappersTest {

	@Test
	public void testMapDifferentClass() {
		for (int i = 0; i < 10; i++) {
			User user = randUser(10, 10, 10);
			assertUserPersonEquals(user, Mappers.map(user, Person.class));
			assertUserPersonEquals(user, Mappers.map(user, new Person()));
		}
	}

	private void assertUserPersonEquals(User u, Person p) {
		assertEquals(u.getId(), p.getId());
		assertEquals(u.getName(), p.getName());
		assertNull(p.getNickname());
		assertEmailsEqual(u.getEmails(), p.getEmails(), false);
		assertNotSame(u, p);
	}

	@Test
	public void testDeepCopy() {
		List<User> users = new ArrayList<>();
		for (int i = 0; i < 10000; i++) {
			users.add(randUser(12, 10, 5));
		}

		for (User u1 : users) {
			User u2 = Mappers.map(u1, User.class);
			assertUserEqualNotSame(u1, u2);
			assertNotSame(u1, u2);
		}
	}

	private User randUser(int maxNameLen, int maxScoreSize, int maxEmailSize) {
		return new User(
			Randoms.nextInt(),
			Randoms.randLowerDigit(maxNameLen),
			randDepartment(maxNameLen),
			randScores(maxNameLen, maxScoreSize),
			randEmails(maxEmailSize, maxNameLen)
		);
	}

	private Department randDepartment(int maxNameLen) {
		int departmentId = Randoms.nextInt();
		String departmenName = Randoms.randLowerDigit(maxNameLen);
		return new Department(departmentId, departmenName);
	}

	private Map<String, Integer> randScores(int maxSize, int maxKeyLen) {
		Map<String, Integer> scores = new HashMap<>();
		int scoreSize = Randoms.nextInt(maxSize);
		for (int j = 0; j < scoreSize; j++) {
			scores.put(Randoms.randLowerDigit(maxKeyLen), Randoms.nextInt());
		}
		return scores;
	}

	private List<Email> randEmails(int emailSize, int maxEmailLen) {
		List<Email> emails = new ArrayList<>();
		for (int j = 0; j < emailSize; j++) {
			emails.add(new Email(Randoms.randLowerDigit(maxEmailLen)));
		}
		return emails;
	}

	private void assertUserEqualNotSame(User u1, User u2) {
		assertEquals(u1.getId(), u2.getId());
		assertEquals(u1.getName(), u2.getName());
		assertDepartmentEqual(u1.getDepartment(), u2.getDepartment(), true);
		assertScoresEqual(u1.getScores(), u2.getScores(), true);
		assertEmailsEqual(u1.getEmails(), u2.getEmails(), true);
	}

	private void assertDepartmentEqual(Department d1, Department d2, boolean notSame) {
		if (notSame) {
			assertNotSame(d1, d2);
		}
		assertEquals(d1.getId(), d2.getId());
		assertEquals(d1.getName(), d2.getName());
	}

	private void assertScoresEqual(Map<String, Integer> s1, Map<String, Integer> s2, boolean notSame) {
		if (notSame) {
			assertNotSame(s1, s2);
		}
		assertEquals(s1, s2);
	}

	private void assertEmailsEqual(List<Email> e1, List<Email> e2, boolean notSame) {
		if (notSame) {
			assertNotSame(e1, e2);
		}
		assertEquals(e1.size(), e2.size());
		for (int i = 0; i < e1.size(); i++) {
			Email email1 = e1.get(i);
			Email email2 = e2.get(i);
			assertEquals(email1.getAddress(), email2.getAddress());
			if (notSame) {
				assertNotSame(email1, email2);
			}
		}
	}

}
