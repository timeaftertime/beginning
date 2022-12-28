package cn.milai.common.uniform.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import cn.milai.common.uniform.model.Department;
import cn.milai.common.uniform.model.Email;
import cn.milai.common.uniform.model.User;
import cn.milai.common.uniform.model.UserDO;
import cn.milai.common.uniform.serialize.JSON;

/**
 * {@link BaseMapper} 测试类
 * @author milai
 * @date 2022.10.29
 */
public class BaseMapperTest {

	@Test
	public void testUse() {
		int id = 99;
		String name = "use use";
		int departmentId = 10;
		String address = "XXX@";
		List<Email> emails = Arrays.asList(new Email(address));
		String emailJSON = String.format("[{\"address\":\"%s\"}]", address);
		Department department = new Department(departmentId, "XXX department");
		String departmentStr = departmentString(department);
		User user = new User(id, name, department, null, emails);

		Mapper<User, UserDO> mapper = new BaseMapper<>(User.class, UserDO.class);
		UserDO user1 = mapper.map(user);
		assertEquals(id, user1.getId());
		assertEquals(name, user1.getName());
		assertNull(user1.getDepartment());
		assertNull(user1.getScores());
		assertNull(user1.getEmails());

		@SuppressWarnings("rawtypes")
		Mapper<List, String> listMapper = new BaseMapper<List, String>(List.class, String.class) {
			@Override
			public String map(List source) {
				return JSON.write(source);
			}
		};
		mapper.use(listMapper);
		UserDO user2 = mapper.map(user);
		assertEquals(id, user2.getId());
		assertEquals(name, user2.getName());
		assertNull(user2.getDepartment());
		assertNull(user2.getScores());
		assertEquals(emailJSON, user2.getEmails());

		Mapper<Department, String> departmentMapper = new BaseMapper<Department, String>(
			Department.class, String.class
		) {
			@Override
			public String map(Department source) {
				return departmentString(source);
			}
		};
		mapper.use(departmentMapper);
		UserDO user3 = mapper.map(user);
		assertEquals(id, user3.getId());
		assertEquals(name, user3.getName());
		assertEquals(departmentStr, user3.getDepartment());
		assertNull(user3.getScores());
		assertEquals(emailJSON, user3.getEmails());
	}

	private static String departmentString(Department d) {
		return String.format("department id = %d", d.getId());
	}

}
