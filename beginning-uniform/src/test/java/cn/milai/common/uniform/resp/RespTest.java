package cn.milai.common.uniform.resp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * {@link Resp} 测试类
 * @author milai
 * @date 2021.11.07
 */
public class RespTest {

	@Test
	public void testBuildResp() {
		String data = "this is data";
		Resp<String> success = Resp.success(data);
		assertEquals(Resp.SUCCESS, success.getCode());
		assertEquals("", success.getDesc());
		assertEquals(data, success.getData());

		int errorCode = 1;
		String errorDesc = "数据库错误: %s";
		String descArg = "表名: xxx_table";
		Resp<Object> fail = Resp.fail(new RespCode() {
			@Override
			public String getDesc() { return errorDesc; }

			@Override
			public int getCode() { return errorCode; }
		}, descArg);
		assertEquals(errorCode, fail.getCode());
		assertEquals(String.format(errorDesc, descArg), fail.getDesc());
		assertNull(fail.getData());

		Resp<Object> copyFail = Resp.fail(fail);
		assertEquals(fail.getCode(), copyFail.getCode());
		assertEquals(fail.getDesc(), copyFail.getDesc());
		assertEquals(fail.getData(), copyFail.getData());
	}

	@Test
	public void testThrowOrGet() {
		Resp<Object> resp1 = Resp.success();
		assertSame(null, resp1.orThrow());
		String msg = "This is args: %s";
		String arg = "~~";
		Resp<Object> resp2 = Resp.fail(1, msg, arg);
		try {
			resp2.orThrow();
		} catch (RespFailedException e) {
			assertEquals(String.format(msg, arg), e.getResp().getDesc());
			return;
		}
		fail();
	}

}
