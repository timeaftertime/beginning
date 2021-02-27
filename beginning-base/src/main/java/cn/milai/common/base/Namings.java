package cn.milai.common.base;

/**
 * 命名相关
 * @author milai
 * @date 2021.02.27
 */
public class Namings {

	/**
	 * 返回蛇形（下划线分割）的字符串的首字母大写后的驼峰形式
	 * @param snakeStr
	 * @return
	 */
	public static String toUpperCamel(String snakeStr) {
		String[] splits = snakeStr.split("_");
		StringBuilder sb = new StringBuilder();
		for (String word : splits) {
			sb.append(toFirstUpper(word));
		}
		return toFirstUpper(sb.toString());
	}

	/**
	 * 返回蛇形（下划线分割）的字符串的首字母小写后的驼峰形式
	 * @param snakeStr
	 * @return
	 */
	public static String toLowerCamel(String snakeStr) {
		return toFirstLower(toUpperCamel(snakeStr));
	}

	/**
	 * 返回驼峰字符串的蛇形（下划线分割）形式
	 * @param camelStr
	 * @return
	 */
	public static String toSnake(String camelStr) {
		StringBuilder sb = new StringBuilder();
		char[] chs = toFirstLower(camelStr).toCharArray();
		for (int i = 0; i < chs.length; i++) {
			if (Character.isUpperCase(chs[i])) {
				sb.append('_');
			}
			sb.append(Character.toLowerCase(chs[i]));
		}
		return sb.toString();
	}

	/**
	 * 返回字符串的首字母大写形式
	 * @param word
	 * @return
	 */
	public static String toFirstUpper(String word) {
		char[] chs = word.toCharArray();
		chs[0] = Character.toUpperCase(chs[0]);
		return new String(chs);
	}

	/**
	 * 返回字符串的首字小写形式
	 * @param word
	 * @return
	 */
	public static String toFirstLower(String word) {
		char[] chs = word.toCharArray();
		chs[0] = Character.toLowerCase(chs[0]);
		return new String(chs);
	}

}
