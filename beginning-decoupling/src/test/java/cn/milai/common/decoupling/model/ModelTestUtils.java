package cn.milai.common.decoupling.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.milai.common.base.Randoms;

/**
 * 模型相关测试工具类
 * @author milai
 * @date 2021.03.05
 */
public class ModelTestUtils {

	/**
	 * 断言指定 {@code Person} 为指定 {@code User} 复制后的结果 
	 * @param u
	 * @param p
	 * @param isDeep 是否为深拷贝
	 */
	public static void assertUserPersonEquals(User u, Person p, boolean isDeep) {
		assertEquals(u.getId(), p.getId());
		assertEquals(u.getName(), p.getName());
		assertNull(p.getNickname());
		assertEmailsEqual(u.getEmails(), p.getEmails(), isDeep);
		assertNotSame(u, p);
	}

	/**
	 * 创建一个信息随机的用户
	 * @param maxNameLen name 属性最大长度
	 * @param maxScoreSize scores 属性最大大小
	 * @param maxEmailSize email 属性最大大小
	 * @return
	 */
	public static User randUser(int maxNameLen, int maxScoreSize, int maxEmailSize) {
		return new User(
			Randoms.nextInt(),
			Randoms.randLowerDigit(maxNameLen),
			randDepartment(maxNameLen),
			randScores(maxNameLen, maxScoreSize),
			randEmails(maxEmailSize, maxNameLen)
		);
	}

	private static Department randDepartment(int maxNameLen) {
		int departmentId = Randoms.nextInt();
		String departmenName = Randoms.randLowerDigit(maxNameLen);
		return new Department(departmentId, departmenName);
	}

	private static Map<String, Integer> randScores(int maxSize, int maxKeyLen) {
		Map<String, Integer> scores = new HashMap<>();
		int scoreSize = Randoms.nextInt(maxSize);
		for (int j = 0; j < scoreSize; j++) {
			scores.put(Randoms.randLowerDigit(maxKeyLen), Randoms.nextInt());
		}
		return scores;
	}

	private static List<Email> randEmails(int emailSize, int maxEmailLen) {
		List<Email> emails = new ArrayList<>();
		for (int j = 0; j < emailSize; j++) {
			emails.add(new Email(Randoms.randLowerDigit(maxEmailLen)));
		}
		return emails;
	}

	public static void assertUserEqual(User u1, User u2, boolean isDeep) {
		if (isDeep) {
			assertNotSame(u1, u2);
		}
		assertEquals(u1.getId(), u2.getId());
		assertEquals(u1.getName(), u2.getName());
		assertDepartmentEqual(u1.getDepartment(), u2.getDepartment(), isDeep);
		assertScoresEqual(u1.getScores(), u2.getScores(), isDeep);
		assertEmailsEqual(u1.getEmails(), u2.getEmails(), isDeep);
	}

	public static void assertDepartmentEqual(Department d1, Department d2, boolean isDeep) {
		if (isDeep) {
			assertNotSame(d1, d2);
		}
		assertEquals(d1.getId(), d2.getId());
		assertEquals(d1.getName(), d2.getName());
	}

	public static void assertScoresEqual(Map<String, Integer> s1, Map<String, Integer> s2, boolean isDeep) {
		if (isDeep) {
			assertNotSame(s1, s2);
		}
		assertEquals(s1, s2);
	}

	public static void assertEmailsEqual(List<Email> e1, List<Email> e2, boolean isDeep) {
		if (isDeep) {
			assertNotSame(e1, e2);
		}
		assertEquals(e1.size(), e2.size());
		for (int i = 0; i < e1.size(); i++) {
			Email email1 = e1.get(i);
			Email email2 = e2.get(i);
			assertEquals(email1.getAddress(), email2.getAddress());
			if (isDeep) {
				assertNotSame(email1, email2);
			}
		}
	}

}
