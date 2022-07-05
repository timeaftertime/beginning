package cn.milai.common.uniform.generator;

import cn.milai.common.base.Randoms;

/**
 * 通过当前时间和随机算法来生成的 {@link StringGenerator}
 * @author milai
 * @date 2021.12.30
 */
public class TimeRandomIdGenerator implements StringGenerator {

	public static final int LEN = 32;
	private static final int RAND_LEN = 16;

	@Override
	public String next() {
		return fixedString(LEN, Long.toString(System.currentTimeMillis(), 16) + Randoms.fixedLowerDigit(RAND_LEN));
	}

	private static String fixedString(int len, String s) {
		int fix = len - s.length();
		if (fix <= 0) {
			return s;
		}
		StringBuffer res = new StringBuffer(len);
		for (int i = 0; i < fix; i++) {
			res.append('0');
		}
		res.append(s);
		return res.toString();
	}

	/**
	 * 获取给定 id 对应的时间戳
	 * @param id
	 * @return
	 */
	public static long getTime(String id) {
		return Long.parseLong(id.substring(0, LEN - RAND_LEN), 16);
	}

}
